package shutt;

import com.letv.shuttle.rpc.server.ann.RpcServiceAutowried;

public interface EchoService {
     @RpcServiceAutowried("echo") //需要打上此注解，表明是rpc方法
      public String echo(String name);
}