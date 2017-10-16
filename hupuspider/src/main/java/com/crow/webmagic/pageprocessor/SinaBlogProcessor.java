package com.crow.webmagic.pageprocessor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by CrowHawk on 17/10/7.
 */
public class SinaBlogProcessor implements PageProcessor {

    public static final String URL_LIST = "http://blog\\.sina\\.com\\.cn/s/articlelist_1487828712_0_\\d+\\.html";

    public static final String URL_POST = "http://blog\\.sina\\.com\\.cn/s/blog_\\w+\\.html";

    private Site site = Site
            .me()
            .setDomain("blog.sina.com.cn")
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Override
    public void process(Page page) {
        //列表页
        if (page.getUrl().regex(URL_LIST).match()) {
            page.addTargetRequests(page.getHtml().xpath("//div[@class=\"articleList\"]").links().regex(URL_POST).all());
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
        //文章页
        } else {
            page.putField("title", page.getHtml().xpath("//div[@class='articalTitle']/h2"));
            page.putField("content", page.getHtml().xpath("//div[@id='articlebody']//div[@class='articalContent']"));
            page.putField("date",
                    page.getHtml().xpath("//div[@id='articlebody']//span[@class='time SG_txtc']").regex("\\((.*)\\)"));
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        //Spider.create(new SinaBlogProcessor()).addUrl("http://blog.sina.com.cn/s/articlelist_1487828712_0_1.html")
          //      .run();
        /*
        String str = "拍下室友偷东西的视频发到朋友圈 却被辅导员告知是违法的???？" ;
        str = str.replaceAll( "[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]" , "");

        String[] strings = ToAnalysis.parse(str).toStringWithOutNature().split(",");
        for(String word: strings) {
            System.out.print(word + " ");
        }
        */
        String url = "https://bbs.hupu.com/bxj-11";
        String url1 = url.substring(25);
        String url2 = url.substring(0,25);
        String url3 = url.substring(0,25).concat((Integer.parseInt(url.substring(25)) + 1) + "");
        System.out.println(url3);


    }
}
