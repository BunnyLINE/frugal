package frugal

import (
	"bytes"
	"encoding/binary"
	"errors"
	"fmt"
	"io"

	"git.apache.org/thrift.git/lib/go/thrift"
)

const protocolV0 = 0x00

// FProtocolFactory is a factory for FProtocol.
type FProtocolFactory struct {
	protoFactory thrift.TProtocolFactory
}

func NewFProtocolFactory(protoFactory thrift.TProtocolFactory) *FProtocolFactory {
	return &FProtocolFactory{protoFactory}
}

func (f *FProtocolFactory) GetProtocol(tr thrift.TTransport) *FProtocol {
	return &FProtocol{f.protoFactory.GetProtocol(tr)}
}

// FProtocol is an extension of thrift TProtocol with the addition of headers
type FProtocol struct {
	thrift.TProtocol
}

// WriteRequestHeader writes the request headers set on the given Context
// into the protocol
func (f *FProtocol) WriteRequestHeader(ctx *FContext) error {
	return f.writeHeader(ctx.RequestHeaders())
}

// ReadRequestHeader reads the request headers on the protocol into a
// returned Context
func (f *FProtocol) ReadRequestHeader() (*FContext, error) {
	headers, err := readHeader(f.Transport())
	if err != nil {
		return nil, err
	}

	ctx := &FContext{
		requestHeaders:  make(map[string]string),
		responseHeaders: make(map[string]string),
	}

	for name, value := range headers {
		ctx.AddRequestHeader(name, value)
	}

	// Put op id in response headers
	opid, ok := headers[opID]
	if !ok {
		return nil, errors.New("frugal: request missing op id")
	}
	ctx.setResponseOpID(opid)

	return ctx, nil
}

// WriteResponseHeader writes the response headers set on the given Context
// into the protocol
func (f *FProtocol) WriteResponseHeader(ctx *FContext) error {
	return f.writeHeader(ctx.ResponseHeaders())
}

// ReadResponseHeader reads the response headers on the protocol into a
// provided Context
func (f *FProtocol) ReadResponseHeader(ctx *FContext) error {
	headers, err := readHeader(f.Transport())
	if err != nil {
		return err
	}

	for name, value := range headers {
		ctx.addResponseHeader(name, value)
	}

	return nil
}

func (f *FProtocol) writeHeader(headers map[string]string) error {
	size := int32(0)
	for name, value := range headers {
		size += int32(8 + len(name) + len(value))
	}

	// Header buff = [version (1 byte), size (4 bytes), headers (size bytes)]
	// Headers = [size (4 bytes) name (size bytes) size (4 bytes) value (size bytes)*]
	buff := make([]byte, size+5)

	// Write version
	buff[0] = protocolV0

	// Write size
	binary.BigEndian.PutUint32(buff[1:5], uint32(size))

	// Write headers
	i := 5
	for name, value := range headers {
		binary.BigEndian.PutUint32(buff[i:i+4], uint32(len(name)))
		i += 4
		for k := 0; k < len(name); k++ {
			buff[i] = name[k]
			i++
		}
		binary.BigEndian.PutUint32(buff[i:i+4], uint32(len(value)))
		i += 4
		for k := 0; k < len(value); k++ {
			buff[i] = value[k]
			i++
		}
	}

	if n, err := f.Transport().Write(buff); err != nil {
		return fmt.Errorf("frugal: error writing protocol headers: %s", err.Error())
	} else if n != len(buff) {
		return errors.New("frugal: failed to write complete protocol headers")
	}

	return nil
}

func readHeader(reader io.Reader) (map[string]string, error) {
	buff := make([]byte, 5)
	if _, err := io.ReadFull(reader, buff); err != nil {
		return nil, fmt.Errorf("frugal: error reading protocol headers: %s", err.Error())
	}

	if buff[0] != protocolV0 {
		return nil, fmt.Errorf("frugal: unsupported protocol version %d", buff[0])
	}

	size := int32(binary.BigEndian.Uint32(buff[1:]))
	return readHeadersFromReader(reader, size)
}

func getHeadersFromFrame(frame []byte) (map[string]string, error) {
	if frame[0] != protocolV0 {
		return nil, fmt.Errorf("frugal: unsupported protocol version %d", frame[0])
	}
	size := int32(binary.BigEndian.Uint32(frame[1:5]))
	// TODO: Don't allocate new buffer, just use index offset.
	reader := bytes.NewBuffer(frame[5 : size+5])
	return readHeadersFromReader(reader, size)
}

func readHeadersFromReader(reader io.Reader, size int32) (map[string]string, error) {
	buff := make([]byte, size)
	if _, err := io.ReadFull(reader, buff); err != nil {
		return nil, fmt.Errorf("frugal: error reading protocol headers: %s", err.Error())
	}

	headers := make(map[string]string)
	for i := int32(0); i < size; {
		nameSize := int32(binary.BigEndian.Uint32(buff[i : i+4]))
		i += 4
		if i > size || i+nameSize > size {
			return nil, errors.New("frugal: invalid protocol header name")
		}
		name := string(buff[i : i+nameSize])
		i += nameSize

		valueSize := int32(binary.BigEndian.Uint32(buff[i : i+4]))
		i += 4
		if i > size || i+valueSize > size {
			return nil, errors.New("frugal: invalid protocol header value")
		}
		value := string(buff[i : i+valueSize])
		i += valueSize

		headers[name] = value
	}

	return headers, nil
}
