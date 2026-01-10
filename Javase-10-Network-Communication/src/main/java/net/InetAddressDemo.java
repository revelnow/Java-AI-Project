package net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {
      /*
        static InetAddress getByName(String host)   确定主机名称的IP地址(主机名称可以是机器名称，也可以是IP地址)
        String getHostName()                        获取此 IP 地址的主机名
        String getHostAddress()                     返回文本显示中的 IP 地址字符串
     */

    public static void main(String[] args) throws UnknownHostException {
        // 将需要操作的ip或者是主机名封装为 InetAddress对象.
        InetAddress byName = InetAddress.getByName("DESKTOP-NQD17OI");
        System.out.println(byName);

        // 获取此 IP 地址的主机名
        System.out.println(byName.getHostName());

        // 返回文本显示中的 IP 地址字符串
        System.out.println(byName.getHostAddress());

    }

}
