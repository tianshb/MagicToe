package com.crow.webmagic.pageprocessor;

import com.crow.domain.CommentList;
import com.crow.domain.Post;
import com.crow.domain.User;
import com.crow.utils.ProxyGeneratedUtil;
import com.crow.utils.URLGeneratedUtil;
import com.crow.utils.UserAgentUtil;
import org.ansj.splitWord.analysis.ToAnalysis;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.*;

/**
 * Created by CrowHawk on 17/10/11.
 */
public class HupuBxjPageProcessor implements PageProcessor {

    public static final String URL_LIST = "https://bbs\\.hupu\\.com/bxj-\\d+";
    public static final String URL_POST = "https://bbs\\.hupu\\.com/\\d+\\.html";
    public static final String URL_USER = "https://my\\.hupu\\.com/\\d+";
    public static final int URL_LENGTH = "https://bbs.hupu.com/bxj-".length();
    private static final String ORDER_NUM = "ZF201710169692T66jkr";
    private static final String SECRET = "3b23ace31a2447baa44d624e9c5fd0f5";

    //抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me()
            .addHeader("Proxy-Authorization", ProxyGeneratedUtil.authHeader(ORDER_NUM, SECRET, (int) (new Date().getTime()/1000)))
            .setDisableCookieManagement(true)
            .setCharset("UTF-8")
            .setTimeOut(30000)
            .setRetryTimes(3)
            .setSleepTime(new Random().nextInt(30)*100)
            .setUserAgent(UserAgentUtil.getRandomUserAgent());

    @Override
    public void process(Page page) {

        //列表页
        if (page.getUrl().regex(URL_LIST).match()) {
            List<String> listUrls = page.getHtml().links().regex("/\\d+\\.html").all();
            listUrls.forEach(e -> URLGeneratedUtil.generatePostURL(e));
            page.addTargetRequests(listUrls);//把所有帖子页的URL加入抓取队列
            String url = page.getUrl().toString();
            page.addTargetRequest(url.substring(0,URL_LENGTH).concat((Integer.parseInt(url.substring(URL_LENGTH)) + 1) + ""));//把所有列表页加入抓取队列，
        }

        //文章页
        if (page.getUrl().regex(URL_POST).match()) {
            page.addTargetRequests(page.getHtml().links().regex(URL_USER).all());//把所有用户主页的URL加入抓取队列
            //帖子标题
            String title = page.getHtml().xpath("//div[@class='bbs-hd-h1']/h1[@id='j_data']/text()").toString();
            //帖子作者
            String postAuthor = page.getHtml().xpath("//div[@class='author']//a[@class='u'][1]/text()").toString();
            //回复数
            //int replyNum = Integer.parseInt(page.getHtml().xpath("//div[@class='bbs-hd-h1']/h1[@id='j_data']/@data-replies").toString());
            int replyNum = Integer.parseInt(page.getHtml().xpath("//div[@class='bbs-hd-h1']/span[1]/span[1]/text()").toString().replaceAll("[\\u4e00-\\u9fa5]+", ""));

            setPost(title, postAuthor, replyNum, page);
            setTitleWord(title, page);

            //评论内容
            List<String> contentList = page.getHtml().xpath("//div[@class='w_reply clearfix']//td/text()").all();
            //评论点亮数
            List<String> litNumList = page.getHtml().xpath("//div[@class='w_reply clearfix']//span[@class='ilike_icon_list']/span[@class='stime']/text()").all();
            //评论作者
            List<String> commentAuthors = page.getHtml().xpath("//div[@class='w_reply clearfix']//div[@class='author']//a[@class='u']/text()").all();

            //for(int i = 0; i < contentList.size(); i++) {
            setComment(title,contentList, litNumList, commentAuthors, page);
            //}

            //page.addTargetRequests(page.getHtml().links().regex(URL_USER).all());//把所有用户主页的URL加入抓取队列
        }

        if (page.getUrl().regex(URL_USER).match()) {
            //用户名
            String name = page.getHtml().xpath("//div[@itemprop='name']/text()").toString();
            //用户所在地
            String address = page.getHtml().xpath("//div[@class='personalinfo']//span[@itemprop='address']/text()").toString();
            //用户主队
            String homeTeam = page.getHtml().xpath("//div[@class='personalinfo']//span[@itemprop='affiliation'][1]/text()").toString();
            //用户性别
            String gender = page.getHtml().xpath("//div[@class='personalinfo']//span[@itemprop='gender']/text()").toString();

            setUser(name, address, homeTeam, gender, page);

        }

    }

    @Override
    public Site getSite() {
        Set<Integer> acceptStatCode = new HashSet<>();
        acceptStatCode.add(200);
        site = site.setAcceptStatCode(acceptStatCode).addHeader("Accept-Encoding", "/");

        return site;
    }


    private void setPost(String title, String author, int replyNum, Page page) {//存放抓取的帖子信息
        Post post = new Post();
        post.setTitle(title);
        post.setAuthor(author);
        post.setReplyNum(replyNum);
        page.putField("postInfo", post);
    }

    private void setComment(String title, List<String> contentList, List<String> litNumList, List<String> commentAuthors, Page page) {//存放帖子下的热评信息
        CommentList commentList = new CommentList();
        commentList.setTitle(title);
        commentList.setContentList(contentList);
        commentList.setLitNumList(litNumList);
        commentList.setCommentAuthors(commentAuthors);
        page.putField("commentInfo", commentList);
    }

    private void setTitleWord(String title, Page page) {//存放抓取的帖子标题的分词
        title = title.replaceAll( "[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]" , "");
        String[] strings = ToAnalysis.parse(title).toStringWithOutNature().split(",");//分词的结果是用","分隔的
        //for(String word: strings) {
          //  TitleWord titleWord = new TitleWord();
            //titleWord.setWord(word);
            page.putField("titleWordInfo", strings);
        //}
    }

    private void setUser(String name, String address, String homeTeam, String gender, Page page) {
        User user = new User();
        user.setName(name);
        user.setAddress(address);
        user.setHomeTeam(homeTeam);
        user.setGender(gender);
        page.putField("userInfo", user);
    }
}
