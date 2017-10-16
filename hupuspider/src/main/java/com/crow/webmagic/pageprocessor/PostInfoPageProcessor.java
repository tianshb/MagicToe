package com.crow.webmagic.pageprocessor;

import com.crow.domain.HupuBxjPostInfo;
import com.crow.utils.URLGeneratedUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by CrowHawk on 17/10/6.
 */
public class PostInfoPageProcessor implements PageProcessor {

    //public static final String URL_LIST = "http://blog\\.sina\\.com\\.cn/s/articlelist_1487828712_0_\\d+\\.html";
    //public static final String URL_POST = "http://blog\\.sina\\.com\\.cn/s/blog_\\w+\\.html";
    public static final String URL_LIST = "https://bbs\\.hupu\\.com/bxj-\\d+";

    public static final String URL_POST = "https://bbs\\.hupu\\.com/\\d+\\.html";

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36");

    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    @Override
    public void process(Page page) {

        HupuBxjPostInfo hupuBxjPostInfo = new HupuBxjPostInfo();


        //列表页
        if (page.getUrl().regex(URL_LIST).match()) {
            List<String> listUrls = page.getHtml().links().regex("/\\d+\\.html").all();
            listUrls.forEach(e -> URLGeneratedUtil.generatePostURL(e));
            page.addTargetRequests(listUrls);//把所有帖子页的URL加入抓取队列
            String url = page.getUrl().toString();
            page.addTargetRequest(url.substring(0,25).concat((Integer.parseInt(url.substring(25)) + 1) + ""));
            //page.addTargetRequests(URLGeneratedUtil.generateListURL());//把所有列表页的URL加入抓取队列
        }
        //文章页
        if (page.getUrl().regex(URL_POST).match()){
            //hupuBxjPostInfo.setTitle(page.getHtml().xpath("//div[@class='articalTitle']/h2/text()").toString());
            //hupuBxjPostInfo.setAuthor(page.getHtml().xpath("//div[@id='articlebody']//span[@class='time SG_txtc']").regex("\\((.*)\\)").toString());

            hupuBxjPostInfo.setTitle(page.getHtml().xpath("//div[@class='bbs-hd-h1']/h1[@id='j_data']/text()").toString());
            hupuBxjPostInfo.setAuthor(page.getHtml().xpath("//div[@class='author']//a[@class='u'][1]/text()").toString());
            //page.getHtml().xpath("//div[@class='author']//a[@class='u']/text()").toString());
            hupuBxjPostInfo.setText(page.getHtml().replace("(& nbsp;)", "").xpath("//div[@class='quote-content']/tidyText()").toString());

            page.putField("postInfo", hupuBxjPostInfo);

        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
