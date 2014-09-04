package thrift;

import org.apache.thrift.TException;

public class TestImpl implements Test.Iface{

	@Override
	public String hello() throws TException {
		System.out.println("hello world");
		return "hello world";
	}

}
