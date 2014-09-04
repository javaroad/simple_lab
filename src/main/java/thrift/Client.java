package thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class Client {
	public static void main(String[] args) throws TException {
		 TTransport transport  = new TSocket("127.0.0.1",9090);
		  TProtocol protocol = new  TBinaryProtocol(transport);
		  Test.Client client = new Test.Client(protocol);
		  transport.open();
		  System.out.println(client.hello());
		  transport.close();
	}
}
