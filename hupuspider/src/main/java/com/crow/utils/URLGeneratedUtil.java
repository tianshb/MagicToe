package com.crow.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CrowHawk on 17/10/7.
 */
public class URLGeneratedUtil {

    public final static String PREFIX = "https://bbs.hupu.com";

    public final static int START_POS = 2;//爬取的起始目录页位置

    public final static int END_POS = 10;//爬取的目录页页数

    /*
    public static List<String> generateListURL() {
        List<String> urls = new ArrayList<>();
        for(int i = START_POS; i <= END_POS; i++) {
            urls.add(PREFIX + "/bxj-" + i);
        }
        return urls;
    }
    */

    public static List<String> generatePostURLs(String postURL) {
        List<String> urls = new ArrayList<>();
        for(int i = START_POS; i <= END_POS; i++) {
            urls.add(postURL.substring(0, postURL.length() - 5) + "-" + i + ".html");
        }
        return urls;
    }

    public static String generatePostURL(String url) {
        return PREFIX + url;
    }
}
