package com.crow.utils;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

/**
 * Created by CrowHawk on 17/10/16.
 */
public class IPCheckUtil {
    /**
     * 校验代理IP的有效性，测试地址为：http://www.ip138.com
     * @param ip 代理IP地址
     * @param port  代理IP端口
     * @return  此代理IP是否有效
     */
    public static boolean checkValidIP(String ip,Integer port){
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL("http://www.ip138.com");
            //代理服务器
            InetSocketAddress proxyAddr = new InetSocketAddress(ip, port);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyAddr);
            connection = (HttpURLConnection) url.openConnection(proxy);
            connection.setReadTimeout(4000);
            connection.setConnectTimeout(4000);
            connection.setRequestMethod("GET");

            if(connection.getResponseCode() == 200){
                connection.disconnect();
                return true;
            }

        } catch (Exception e) {
            connection.disconnect();
            return false;
        }
        return false;
    }
}
