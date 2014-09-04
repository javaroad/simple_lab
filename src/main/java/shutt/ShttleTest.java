package shutt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.letv.shuttle.rpc.server.RpcServerController;

public class ShttleTest {
	public static void main(String[] args) {
		ApplicationContext ctx = null;
			// 获取上下文
			ctx = new ClassPathXmlApplicationContext("shuttle-service.xml");
			RpcServerController.startRpcServer();
		}
}
