#
# Autogenerated by Frugal Compiler (1.23.0)
#
# DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
#



import asyncio
from datetime import timedelta
import inspect

from frugal.aio.processor import FBaseProcessor
from frugal.aio.processor import FProcessorFunction
from frugal.aio.registry import FClientRegistry
from frugal.exceptions import FRateLimitException
from frugal.middleware import Method
from thrift.Thrift import TApplicationException
from thrift.Thrift import TMessageType
from . import f_BasePinger
from .Pinger import *
from .ttypes import *


class Iface(f_BasePinger.Iface):

    async def ping(self, ctx):
        """
        Args:
            ctx: FContext
        """
        pass


class Client(f_BasePinger.Client, Iface):

    def __init__(self, transport, protocol_factory, middleware=None):
        """
        Create a new Client with a transport and protocol factory.

        Args:
            transport: FTransport
            protocol_factory: FProtocolFactory
            middleware: ServiceMiddleware or list of ServiceMiddleware
        """
        if middleware and not isinstance(middleware, list):
            middleware = [middleware]
        super(Client, self).__init__(transport, protocol_factory,
                                     middleware=middleware)
        self._methods.update({
            'ping': Method(self._ping, middleware),
        })

    async def ping(self, ctx):
        """
        Args:
            ctx: FContext
        """
        return await self._methods['ping']([ctx])

    async def _ping(self, ctx):
        timeout = ctx.get_timeout() / 1000.0
        future = asyncio.Future()
        timed_future = asyncio.wait_for(future, timeout)
        await self._transport.register(ctx, self._recv_ping(ctx, future))
        try:
            await self._send_ping(ctx)
            result = await timed_future
        finally:
            await self._transport.unregister(ctx)
        return result

    async def _send_ping(self, ctx):
        oprot = self._oprot
        async with self._write_lock:
            oprot.write_request_headers(ctx)
            oprot.writeMessageBegin('ping', TMessageType.CALL, 0)
            args = ping_args()
            args.write(oprot)
            oprot.writeMessageEnd()
            await oprot.get_transport().flush()

    def _recv_ping(self, ctx, future):
        def ping_callback(transport):
            iprot = self._protocol_factory.get_protocol(transport)
            iprot.read_response_headers(ctx)
            _, mtype, _ = iprot.readMessageBegin()
            if mtype == TMessageType.EXCEPTION:
                x = TApplicationException()
                x.read(iprot)
                iprot.readMessageEnd()
                if x.type == FRateLimitException.RATE_LIMIT_EXCEEDED:
                    future.set_exception(FRateLimitException(x.message))
                    return
                future.set_exception(x)
                raise x
            result = ping_result()
            result.read(iprot)
            iprot.readMessageEnd()
            future.set_result(None)
        return ping_callback


class Processor(f_BasePinger.Processor):

    def __init__(self, handler, middleware=None):
        """
        Create a new Processor.

        Args:
            handler: Iface
        """
        if middleware and not isinstance(middleware, list):
            middleware = [middleware]

        super(Processor, self).__init__(handler, middleware=middleware)
        self.add_to_processor_map('ping', _ping(Method(handler.ping, middleware), self.get_write_lock()))


class _ping(FProcessorFunction):

    def __init__(self, handler, lock):
        self._handler = handler
        self._write_lock = lock

    async def process(self, ctx, iprot, oprot):
        args = ping_args()
        args.read(iprot)
        iprot.readMessageEnd()
        result = ping_result()
        try:
            ret = self._handler([ctx])
            if inspect.iscoroutine(ret):
                ret = await ret
        except FRateLimitException as ex:
            async with self._write_lock:
                _write_application_exception(ctx, oprot, FRateLimitException.RATE_LIMIT_EXCEEDED, "ping", ex.message)
                return
        except Exception as e:
            async with self._write_lock:
                _write_application_exception(ctx, oprot, TApplicationException.UNKNOWN, "ping", e.args[0] if e.args else 'unknown exception')
            raise
        async with self._write_lock:
            oprot.write_response_headers(ctx)
            oprot.writeMessageBegin('ping', TMessageType.REPLY, 0)
            result.write(oprot)
            oprot.writeMessageEnd()
            oprot.get_transport().flush()


def _write_application_exception(ctx, oprot, typ, method, message):
    x = TApplicationException(type=typ, message=message)
    oprot.write_response_headers(ctx)
    oprot.writeMessageBegin(method, TMessageType.EXCEPTION, 0)
    x.write(oprot)
    oprot.writeMessageEnd()
    oprot.get_transport().flush()


