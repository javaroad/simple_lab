package zmq;

import org.zeromq.ZMQ;

/**
 * @author 吕桂强
 * @email larry.lv.word@gmail.com
 * @version 创建时间：2012-4-26 下午7:32:34
 */
public class hwserver{
    // java编写的Hello World 服务端
    // 进程间通信ipc:///11111, 跨主机通信使用tcp://10.5.0.170:26666
    // REP当接收消息时，都会返回一个消息
    // 使用REQ模式进行主机间通信 连接到：tcp://localhost:5555
    // 发送 "Hello"到服务端, 接收 "World"
    public static void main(String[] args) {
    
        // 创建上下文, 初始化一个io_thread
        // 创建一个(socket最大数目 + io线程数目 + 3)的slots指针数组
        // a. 每个socket对象有自身的mailbox.
        // b. 每个io_thread对象也有自身的mailbox.
        // c. 另外的3个分别是zmq_term thread, reaper thread, log thread的mailbox.
        
        // 相应的zermq的socket, 比如req, rep, pair, push, pull...等等,本例创建REP类型
        
        // 绑定到端口,并且在io_thread中accept连接
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket socket = context.socket(ZMQ.REP);
        socket.bind("tcp://*:5555");
        while (true) {
            byte[] request;
            // 等待下一个client端的请求
            // 等待一个以0结尾的字符串
            // 忽略最后一位的0打印
            request = socket.recv(0);
            System.out.println("Received request: [" + new String(request, 0, request.length - 1) + "]");
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            // 返回一个最后一位为0的字符串到客户端
            String replyString = "World" + " ";
            byte[] reply = replyString.getBytes();
            reply[reply.length - 1] = 0;
            socket.send(reply, ZMQ.NOBLOCK);
        }
    }
}