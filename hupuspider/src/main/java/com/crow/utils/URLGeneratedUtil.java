package com.crow.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CrowHawk on 17/10/7.
 */
public class URLGeneratedUtil {

    public final static String prefix = "https://bbs.hupu.com";

    public final static int startPos = 2;//爬取的起始目录页位置

    public final static int endPos = 20;//爬取的目录页页数

    public static List<String> generateListURL() {
        List<String> urls = new ArrayList<>();
        for(int i = startPos; i <= endPos; i++) {
            urls.add(prefix + "/bxj-" + i);
        }
        return urls;
    }

    public static String generatePostURL(String url) {
        return prefix + url;
    }
}
