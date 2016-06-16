package python

import (
	"fmt"
	"io/ioutil"
	"os"
	"path/filepath"

	"github.com/Workiva/frugal/compiler/globals"
	"github.com/Workiva/frugal/compiler/parser"
)

type TornadoGenerator struct {
	*Generator
}

func (t *TornadoGenerator) GenerateServiceImports(file *os.File, s *parser.Service) error {
	imports := "from threading import Lock\n\n"

	imports += "from frugal.middleware import Method\n"
	imports += "from frugal.processor import FBaseProcessor\n"
	imports += "from frugal.processor import FProcessorFunction\n"
	imports += "from frugal.registry import FClientRegistry\n"
	imports += "from thrift.Thrift import TApplicationException\n"
	imports += "from thrift.Thrift import TMessageType\n"
	imports += "from tornado import gen\n"
	imports += "from tornado.concurrent import Future\n\n"

	// Import include modules.
	for _, include := range s.ReferencedIncludes() {
		namespace, ok := t.Frugal.NamespaceForInclude(include, lang)
		if !ok {
			namespace = include
		}
		imports += fmt.Sprintf("import %s\n", namespace)
	}

	// Import this service's modules.
	namespace, ok := t.Frugal.Thrift.Namespace(lang)
	if !ok {
		namespace = t.Frugal.Name
	}
	imports += fmt.Sprintf("from %s.%s import *\n", namespace, s.Name)
	imports += fmt.Sprintf("from %s.ttypes import *\n", namespace)

	_, err := file.WriteString(imports)
	return err
}

func (t *TornadoGenerator) GenerateScopeImports(file *os.File, s *parser.Scope) error {
	imports := "import sys\n"
	imports += "import traceback\n\n"

	imports += "from thrift.Thrift import TApplicationException\n"
	imports += "from thrift.Thrift import TMessageType\n"
	imports += "from thrift.Thrift import TType\n"
	imports += "from tornado import gen\n"
	imports += "from frugal.middleware import Method\n"
	imports += "from frugal.subscription import FSubscription\n\n"

	namespace, ok := t.Frugal.Thrift.Namespace(lang)
	if !ok {
		namespace = t.Frugal.Name
	}
	imports += fmt.Sprintf("from %s.ttypes import *\n", namespace)
	_, err := file.WriteString(imports)
	return err
}

func (t *TornadoGenerator) GenerateService(file *os.File, s *parser.Service) error {
	if err := t.exposeServiceModule(filepath.Dir(file.Name()), s); err != nil {
		return err
	}
	contents := ""
	contents += t.generateServiceInterface(s)
	contents += t.generateClient(s)
	contents += t.generateServer(s)

	_, err := file.WriteString(contents)
	return err
}

func (t *TornadoGenerator) exposeServiceModule(path string, service *parser.Service) error {
	// Expose service in __init__.py.
	// TODO: Generate __init__.py ourselves once Thrift is removed.
	initFile := fmt.Sprintf("%s%s__init__.py", path, string(os.PathSeparator))
	init, err := ioutil.ReadFile(initFile)
	if err != nil {
		return err
	}
	initStr := string(init)
	initStr += fmt.Sprintf("\nimport f_%s\n", service.Name)
	initStr += fmt.Sprintf("from f_%s import *\n", service.Name)
	init = []byte(initStr)
	return ioutil.WriteFile(initFile, init, os.ModePerm)
}

func (t *TornadoGenerator) generateClient(service *parser.Service) string {
	contents := "\n"
	if service.Extends != "" {
		contents += fmt.Sprintf("class Client(%s.Client, Iface):\n\n", t.getServiceExtendsName(service))
	} else {
		contents += "class Client(Iface):\n\n"
	}

	contents += tab + "def __init__(self, transport, protocol_factory, middleware=None):\n"
	contents += t.generateDocString([]string{
		"Create a new Client with a transport and protocol factory.\n",
		"Args:",
		tab + "transport: FTransport",
		tab + "protocol_factory: FProtocolFactory",
		tab + "middleware: ServiceMiddleware or list of ServiceMiddleware",
	}, tabtab)
	contents += tabtab + "if middleware and not isinstance(middleware, list):\n"
	contents += tabtabtab + "middleware = [middleware]\n"
	if service.Extends != "" {
		contents += tabtab + "super(Client, self).__init__(transport, protocol_factory,\n"
		contents += tabtab + "                             middleware=middleware)\n"
		contents += tabtab + "self._methods.update("
	} else {
		contents += tabtab + "transport.set_registry(FClientRegistry())\n"
		contents += tabtab + "self._transport = transport\n"
		contents += tabtab + "self._protocol_factory = protocol_factory\n"
		contents += tabtab + "self._oprot = protocol_factory.get_protocol(transport)\n"
		contents += tabtab + "self._write_lock = Lock()\n"
		contents += tabtab + "self._methods = "
	}
	contents += "{\n"
	for _, method := range service.Methods {
		contents += tabtabtab + fmt.Sprintf("'%s': Method(self._%s, middleware),\n", method.Name, method.Name)
	}
	contents += tabtab + "}"
	if service.Extends != "" {
		contents += ")"
	}
	contents += "\n\n"

	for _, method := range service.Methods {
		contents += t.generateClientMethod(method)
	}
	contents += "\n"

	return contents
}

func (t *TornadoGenerator) generateClientMethod(method *parser.Method) string {
	contents := ""
	contents += t.generateMethodSignature(method)
	contents += tabtab + fmt.Sprintf("return self._methods['%s']([ctx%s])\n\n",
		method.Name, t.generateClientArgs(method.Arguments))

	contents += tab + fmt.Sprintf("def _%s(self, ctx%s):\n", method.Name, t.generateClientArgs(method.Arguments))
	if method.Oneway {
		contents += tabtab + fmt.Sprintf("self._send_%s(ctx%s)\n\n", method.Name, t.generateClientArgs(method.Arguments))
		contents += t.generateClientSendMethod(method)
		return contents
	}
	contents += tabtab + "future = Future()\n"
	contents += tabtab + fmt.Sprintf("self._send_%s(ctx, future%s)\n", method.Name, t.generateClientArgs(method.Arguments))
	contents += tabtab + "return future\n\n"
	contents += t.generateClientSendMethod(method)
	contents += t.generateClientRecvMethod(method)

	return contents
}

func (t *TornadoGenerator) generateClientSendMethod(method *parser.Method) string {
	contents := ""
	if method.Oneway {
		contents += tab + fmt.Sprintf("def _send_%s(self, ctx%s):\n", method.Name, t.generateClientArgs(method.Arguments))
	} else {
		contents += tab + fmt.Sprintf("def _send_%s(self, ctx, future%s):\n", method.Name, t.generateClientArgs(method.Arguments))
	}
	contents += tabtab + "oprot = self._oprot\n"
	if !method.Oneway {
		contents += tabtab + fmt.Sprintf("self._transport.register(ctx, self._recv_%s(ctx, future))\n", method.Name)
	}
	contents += tabtab + "with self._write_lock:\n"
	contents += tabtabtab + "oprot.write_request_headers(ctx)\n"
	contents += tabtabtab + fmt.Sprintf("oprot.writeMessageBegin('%s', TMessageType.CALL, 0)\n", method.Name)
	contents += tabtabtab + fmt.Sprintf("args = %s_args()\n", method.Name)
	for _, arg := range method.Arguments {
		contents += tabtabtab + fmt.Sprintf("args.%s = %s\n", arg.Name, arg.Name)
	}
	contents += tabtabtab + "args.write(oprot)\n"
	contents += tabtabtab + "oprot.writeMessageEnd()\n"
	contents += tabtabtab + "oprot.get_transport().flush()\n\n"

	return contents
}

func (t *TornadoGenerator) generateClientRecvMethod(method *parser.Method) string {
	contents := tab + fmt.Sprintf("def _recv_%s(self, ctx, future):\n", method.Name)
	contents += tabtab + fmt.Sprintf("def %s_callback(transport):\n", method.Name)
	contents += tabtabtab + "iprot = self._protocol_factory.get_protocol(transport)\n"
	contents += tabtabtab + "iprot.read_response_headers(ctx)\n"
	contents += tabtabtab + "_, mtype, _ = iprot.readMessageBegin()\n"
	contents += tabtabtab + "if mtype == TMessageType.EXCEPTION:\n"
	contents += tabtabtabtab + "x = TApplicationException()\n"
	contents += tabtabtabtab + "x.read(iprot)\n"
	contents += tabtabtabtab + "iprot.readMessageEnd()\n"
	contents += tabtabtabtab + "future.set_exception(x)\n"
	contents += tabtabtabtab + "raise x\n"
	contents += tabtabtab + fmt.Sprintf("result = %s_result()\n", method.Name)
	contents += tabtabtab + "result.read(iprot)\n"
	contents += tabtabtab + "iprot.readMessageEnd()\n"
	for _, err := range method.Exceptions {
		contents += tabtabtab + fmt.Sprintf("if result.%s is not None:\n", err.Name)
		contents += tabtabtabtab + fmt.Sprintf("future.set_exception(result.%s)\n", err.Name)
		contents += tabtabtabtab + "return\n"
	}
	if method.ReturnType == nil {
		contents += tabtabtab + "future.set_result(None)\n"
	} else {
		contents += tabtabtab + "if result.success is not None:\n"
		contents += tabtabtabtab + "future.set_result(result.success)\n"
		contents += tabtabtabtab + "return\n"
		contents += tabtabtab + fmt.Sprintf(
			"x = TApplicationException(TApplicationException.MISSING_RESULT, \"%s failed: unknown result\")\n", method.Name)
		contents += tabtabtab + "future.set_exception(x)\n"
		contents += tabtabtab + "raise x\n"
	}
	contents += tabtab + fmt.Sprintf("return %s_callback\n\n", method.Name)

	return contents
}

func (t *TornadoGenerator) generateServer(service *parser.Service) string {
	contents := ""
	contents += t.generateProcessor(service)
	for _, method := range service.Methods {
		contents += t.generateProcessorFunction(method)
	}

	return contents
}

func (t *TornadoGenerator) generateProcessorFunction(method *parser.Method) string {
	contents := ""
	contents += fmt.Sprintf("class _%s(FProcessorFunction):\n\n", method.Name)
	contents += tab + "def __init__(self, handler, lock):\n"
	contents += tabtab + "self._handler = handler\n"
	contents += tabtab + "self._lock = lock\n\n"

	contents += tab + "@gen.coroutine\n"
	contents += tab + "def process(self, ctx, iprot, oprot):\n"
	contents += tabtab + fmt.Sprintf("args = %s_args()\n", method.Name)
	contents += tabtab + "args.read(iprot)\n"
	contents += tabtab + "iprot.readMessageEnd()\n"
	if !method.Oneway {
		contents += tabtab + fmt.Sprintf("result = %s_result()\n", method.Name)
	}
	indent := tabtab
	if len(method.Exceptions) > 0 {
		indent += tab
		contents += tabtab + "try:\n"
	}
	if method.ReturnType == nil {
		contents += indent + fmt.Sprintf("yield gen.maybe_future(self._handler.%s(ctx%s))\n",
			method.Name, t.generateServerArgs(method.Arguments))
	} else {
		contents += indent + fmt.Sprintf("result.success = yield gen.maybe_future(self._handler.%s(ctx%s))\n",
			method.Name, t.generateServerArgs(method.Arguments))
	}
	for _, err := range method.Exceptions {
		contents += tabtab + fmt.Sprintf("except %s as %s:\n", err.Type, err.Name)
		contents += tabtabtab + fmt.Sprintf("result.%s = %s\n", err.Name, err.Name)
	}
	if !method.Oneway {
		contents += tabtab + "with self._lock:\n"
		contents += tabtabtab + "oprot.write_response_headers(ctx)\n"
		contents += tabtabtab + fmt.Sprintf("oprot.writeMessageBegin('%s', TMessageType.REPLY, 0)\n", method.Name)
		contents += tabtabtab + "result.write(oprot)\n"
		contents += tabtabtab + "oprot.writeMessageEnd()\n"
		contents += tabtabtab + "oprot.get_transport().flush()\n"
	}
	contents += "\n\n"

	return contents
}

func (t *TornadoGenerator) GenerateSubscriber(file *os.File, scope *parser.Scope) error {
	subscriber := ""
	subscriber += fmt.Sprintf("class %sSubscriber(object):\n", scope.Name)
	if scope.Comment != nil {
		subscriber += t.generateDocString(scope.Comment, tab)
	}
	subscriber += "\n"

	subscriber += tab + fmt.Sprintf("_DELIMITER = '%s'\n\n", globals.TopicDelimiter)

	subscriber += tab + "def __init__(self, provider, middleware=None):\n"
	subscriber += t.generateDocString([]string{
		fmt.Sprintf("Create a new %sSubscriber.\n", scope.Name),
		"Args:",
		tab + "provider: FScopeProvider",
		tab + "middleware: ServiceMiddleware or list of ServiceMiddleware",
	}, tabtab)
	subscriber += "\n"
	subscriber += tabtab + "if middleware and not isinstance(middleware, list):\n"
	subscriber += tabtabtab + "middleware = [middleware]\n"
	subscriber += tabtab + "self._middleware = middleware\n"
	subscriber += tabtab + "self._transport, self._protocol_factory = provider.new()\n\n"

	for _, op := range scope.Operations {
		subscriber += t.generateSubscribeMethod(scope, op)
		subscriber += "\n\n"
	}

	_, err := file.WriteString(subscriber)
	return err
}

func (t *TornadoGenerator) generateSubscribeMethod(scope *parser.Scope, op *parser.Operation) string {
	args := ""
	docstr := []string{}
	if len(scope.Prefix.Variables) > 0 {
		docstr = append(docstr, "Args:")
		prefix := ""
		for _, variable := range scope.Prefix.Variables {
			docstr = append(docstr, tab+fmt.Sprintf("%s: string", variable))
			args += prefix + variable
			prefix = ", "
		}
		args += ", "
	}
	docstr = append(docstr, tab+fmt.Sprintf("%s_handler: function which takes FContext and %s", op.Name, op.Type))
	if op.Comment != nil {
		docstr[0] = "\n" + tabtab + docstr[0]
		docstr = append(op.Comment, docstr...)
	}
	method := tab + "@gen.coroutine\n"
	method += tab + fmt.Sprintf("def subscribe_%s(self, %s%s_handler):\n", op.Name, args, op.Name)
	method += t.generateDocString(docstr, tabtab)
	method += "\n"

	method += tabtab + fmt.Sprintf("op = '%s'\n", op.Name)
	method += tabtab + fmt.Sprintf("prefix = %s\n", generatePrefixStringTemplate(scope))
	method += tabtab + fmt.Sprintf("topic = '{}%s{}{}'.format(prefix, self._DELIMITER, op)\n\n", scope.Name)

	method += tabtab + fmt.Sprintf(
		"yield self._transport.subscribe(topic, self._recv_%s(self._protocol_factory, op, %s_handler))\n\n",
		op.Name, op.Name)

	method += tab + fmt.Sprintf("def _recv_%s(self, protocol_factory, op, handler):\n", op.Name)
	method += tabtab + "method = Method(handler, self._middleware)\n\n"

	method += tabtab + "def callback(transport):\n"
	method += tabtabtab + "iprot = protocol_factory.get_protocol(transport)\n"
	method += tabtabtab + "ctx = iprot.read_request_headers()\n"
	method += tabtabtab + "mname, _, _ = iprot.readMessageBegin()\n"
	method += tabtabtab + "if mname != op:\n"
	method += tabtabtabtab + "iprot.skip(TType.STRUCT)\n"
	method += tabtabtabtab + "iprot.readMessageEnd()\n"
	method += tabtabtabtab + "raise TApplicationException(TApplicationException.UNKNOWN_METHOD)\n"
	method += tabtabtab + fmt.Sprintf("req = %s()\n", op.Type.Name)
	method += tabtabtab + "req.read(iprot)\n"
	method += tabtabtab + "iprot.readMessageEnd()\n"
	method += tabtabtab + "try:\n"
	method += tabtabtabtab + "method([ctx, req])\n"
	method += tabtabtab + "except:\n"
	method += tabtabtabtab + "traceback.print_exc()\n"
	method += tabtabtabtab + "sys.exit(1)\n\n"

	method += tabtab + "return callback\n\n"

	return method
}