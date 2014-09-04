package shutt;

import com.letv.shuttle.rpc.server.ann.RpcServiceAutowried;

public class EchoServiceImpl implements EchoService {
     @Override
     @RpcServiceAutowried("echo")
     public String echo(String name) {
        return name ==null || name.length()<1 ? "welcome null" : "welcome "+ name;
     }
}