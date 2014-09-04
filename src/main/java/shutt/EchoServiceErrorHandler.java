package shutt;

import com.letv.shuttle.rpc.client.ErrorHandler;
import com.letv.shuttle.rpc.server.ann.RpcClientFactoryBean;

//ErrorHandler接口实例定义示例
public class EchoServiceErrorHandler implements ErrorHandler {
	@Override
	public void handle(Exception e) {
		System.out.println("fuckfuck exception");
	}

	public static void main(String[] args) {
		// 调用方法
		EchoServiceCallback cc = new EchoServiceCallback() ;
		EchoService echoService = RpcClientFactoryBean.create("serverId",
				"clientId", EchoService.class);// 传入rpc服务端Id, rpc调用方Id,
												// 接口class,回调实现类，异常处理实现类
		String result = echoService.echo("letv");// 这个输出不是真正的结果，真正的结果在callback中
		System.out.println(result);
	}
}
