/*
 * Copyright 2017 Workiva
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package frugal

import (
	"fmt"
	"strconv"
	"sync"

	"github.com/apache/thrift/lib/go/thrift"
)

// FAsyncCallback is an internal callback which is constructed by generated
// code and invoked by an FRegistry when a RPC response is received. In other
// words, it's used to complete RPCs. The operation ID on FContext is used to
// look up the appropriate callback. FAsyncCallback is passed an in-memory
// TTransport which wraps the complete message. The callback returns an error
// or throws an exception if an unrecoverable error occurs and the transport
// needs to be shutdown.
type FAsyncCallback func(thrift.TTransport) error

// FRegistry is responsible for multiplexing and handling received messages.
// Typically there is a client implementation and a server implementation. An
// FRegistry is used by an FTransport.
//
// The client implementation is used on the client side, which is making RPCs.
// When a request is made, an FAsyncCallback is registered to an FContext. When
// a response for the FContext is received, the FAsyncCallback is looked up,
// executed, and unregistered.
//
// The server implementation is used on the server side, which is handling
// RPCs. It does not actually register FAsyncCallbacks but rather has an
// FProcessor registered with it. When a message is received, it's buffered and
// passed to the FProcessor to be handled.
type FRegistry interface {
	// Register a channel for the given Context.
	Register(ctx FContext, resultC chan []byte) error
	// Unregister a callback for the given Context.
	Unregister(FContext)
	// Execute helps to dispatch a single Thrift message frame.
	Execute([]byte) error

	// Dispatches a single Thrift message frame.
	dispatch(uint64, []byte) error
}

type FRegistryImpl struct {
	Mu       sync.RWMutex
	Channels map[uint64]chan []byte
}

// NewFRegistry creates a Registry intended for use by Frugal clients.
// This is only to be called by generated code.
func NewFRegistry() FRegistry {
	return &FRegistryImpl{Channels: make(map[uint64]chan []byte)}
}

// Register a channel for the given Context.
func (c *FRegistryImpl) Register(ctx FContext, resultC chan []byte) error {
	// An FContext can be reused for multiple requests. Because of this,
	// FContext's have a monotonically increasing atomic uint64. We check
	// the Channels map to ensure that request is not still in-flight.
	opID, err := getOpID(ctx)

	c.Mu.Lock()
	defer c.Mu.Unlock()
	if err == nil {
		_, ok := c.Channels[opID]
		if ok {
			return fmt.Errorf("frugal: context already registered, opid %d is in-flight for another request", opID)
		}
	}
	c.Channels[opID] = resultC
	return nil
}

// Unregister a callback for the given Context.
func (c *FRegistryImpl) Unregister(ctx FContext) {
	opID, err := getOpID(ctx)
	if err != nil {
		logger().Warnf("Attempted to unregister an FContext with a malformed opid: %s", err)
		return
	}
	c.Mu.Lock()
	delete(c.Channels, opID)
	c.Mu.Unlock()
}

// Execute dispatches a single Thrift message frame.
func (c *FRegistryImpl) Execute(frame []byte) error {
	headers, err := getHeadersFromFrame(frame)
	if err != nil {
		logger().Warn("frugal: invalid protocol frame headers:", err)
		return err
	}

	opid, err := strconv.ParseUint(headers[opIDHeader], 10, 64)
	if err != nil {
		logger().Warn("frugal: invalid protocol frame, op id not a uint64:", err)
		return err
	}

	return c.dispatch(opid, frame)
}

func (c *FRegistryImpl) dispatch(opid uint64, frame []byte) error {
	c.Mu.RLock()
	resultC, ok := c.Channels[opid]
	if !ok {
		logger().Warn("frugal: unregistered context")
		c.Mu.RUnlock()
		return nil
	}
	c.Mu.RUnlock()

	resultC <- frame
	return nil
}
