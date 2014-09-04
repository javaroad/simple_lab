package shutt;

import com.letv.shuttle.rpc.client.Callback;
import com.letv.shuttle.rpc.client.ErrorHandler;

//Callback接口实例定义示例
public class EchoServiceCallback implements Callback {
    @Override
    public void call(Object result) {
        String output = (String) result;
        System.out.println("success callback,output="+output);
    }
}
