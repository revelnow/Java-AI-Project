package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class receive {
    public static void main(String[] args) throws SocketException {

        //1.创建接收端对象
        DatagramSocket socket = new DatagramSocket(9999);

        //2.接收数据
        byte[] data = new byte[1024];

        //打包接收数据
        DatagramPacket packet = new DatagramPacket(data, data.length);
        try {
            socket.receive(packet);

            //3.解析数据
            int length = packet.getLength();
            String msg = new String(data, 0, length);
            System.out.println("接收到的数据: " + msg);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //4.关闭资源
            socket.close();
        }
    }
}
