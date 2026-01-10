package udp;

import java.net.*;

public class send {
    public static void main(String[] args) throws SocketException, UnknownHostException {

        //1.创建发送端对象
        DatagramSocket socket = new DatagramSocket(8888);

        //2.填写发送数据
        String msg = "hello";
        byte[] data = msg.getBytes();

        //3.打包发送数据
        DatagramPacket packet = new DatagramPacket(
                data,
                data.length,
                InetAddress.getByName("localhost"),
                9999
        );

        //4.发送数据
        try {
            socket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5.关闭资源
            socket.close();
        }
    }
}
