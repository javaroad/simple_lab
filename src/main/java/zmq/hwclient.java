package zmq;

import org.zeromq.ZMQ;

/**
 * @author 吕桂强
 * @email larry.lv.word@gmail.com
 * @version 创建时间：2012-4-26 下午7:07:19
 */
public class hwclient{
    // java编写的Hello World 客户端
    // REQ 发送完消息后，必须接收一个回应消息后，才能发送新的消息。
    // 使用REQ模式进行主机间通信 连接到：tcp://localhost:5555
    // 发送 "Hello"到服务端, 接收 "World"
    public static void main(String[] args) {
    
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket socket = context.socket(ZMQ.REQ);
        socket.connect("tcp://localhost:5555");
        
        for (int request_nbr = 0; request_nbr <= 10; request_nbr++) {
            // 创建一个末尾为0的字符串
            String requestString = "Hello" + " ";
            byte[] request = requestString.getBytes();
            request[request.length - 1] = 0;
            // 发送
            socket.send(request, ZMQ.NOBLOCK);
            
            // 获得返回值
            byte[] reply = socket.recv(0);
            // 输出去掉末尾0的字符串
            System.out.println("Received reply " + request_nbr + ": [" + new String(reply, 0, reply.length - 1) + "]");
        }
    }
}
