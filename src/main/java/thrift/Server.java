package thrift;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

public class Server {
	static void start() throws TTransportException {
		TServerTransport serverTransport = new TServerSocket(9090);
		Test.Processor processor = new Test.Processor(new TestImpl());
		TServer server = new TSimpleServer(
				new Args(serverTransport).processor(processor).processor(processor));
		 System.out.println("Starting the simple server...");
	     server.serve();
	}
	public static void main(String[] args) throws TTransportException {
		start();
	}
}
