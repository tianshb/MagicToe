package com.crow.webmagic.pageprocessor;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Created by CrowHawk on 17/10/16.
 */
public class TestDynamic {
    public static String authHeader(String orderno, String secret, int timestamp){
        //拼装签名字符串
        String planText = String.format("orderno=%s,secret=%s,timestamp=%d", orderno, secret, timestamp);

        //计算签名
        String sign = org.apache.commons.codec.digest.DigestUtils.md5Hex(planText).toUpperCase();

        //拼装请求头Proxy-Authorization的值
        String authHeader = String.format("sign=%s&orderno=%s&timestamp=%d", sign, orderno, timestamp);
        return authHeader;
    }

    public static void main(String[] args) throws IOException {
        final String url = "https://www.baidu.com/";
        //final String url ="http://1212.ip138.com/ic.asp";
        //final String url = "http://www.hao123.com/";

        final String ip = "forward.xdaili.cn";//这里以正式服务器ip地址为准
        final int port = 80;//这里以正式服务器端口地址为准

        int timestamp = (int) (new Date().getTime()/1000);
        //以下订单号，secret参数 须自行改动
        final String authHeader = authHeader("ZF2017974xxxxxxx", "cb65091847adxxxxxxxxxxxx", timestamp);
        ExecutorService thread = Executors.newFixedThreadPool(1);

        for (int i=0;i<1;i++) {
            thread.execute(new Runnable() {
                @Override
                public void run() {
                    Document doc = null ;
                    try {
                        doc = Jsoup.connect(url)
                                .proxy(ip, port)
                                .validateTLSCertificates(false) //忽略证书认证,每种语言客户端都有类似的API
                                .header("Proxy-Authorization", authHeader)
                                .get();

                        System.out.println(" 返回结果:"+doc.text() );
                    } catch (HttpStatusException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        thread.shutdown();
    }
}
