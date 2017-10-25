package com.crow.web;

import com.crow.webmagic.pageprocessor.ProxyPoolProcessor1;
import com.crow.webmagic.pageprocessor.ProxyPoolProcessor2;
import com.crow.webmagic.pipeline.IPSpiderPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Spider;

/**
 * Created by CrowHawk on 17/10/8.
 */
@RestController
public class StartUpController {

    @Autowired
    IPSpiderPipeline ipSpiderPipeline;

    @GetMapping("/pool1")
    public String index1() {

        Spider.create(new ProxyPoolProcessor1())
                .addUrl("http://www.xicidaili.com/nn")
                //.addUrl("http://blog.sina.com.cn/s/articlelist_1487828712_0_1.html")
                .addPipeline(ipSpiderPipeline)
                .thread(4)
                .run();
        return "爬虫开启1";
    }

    @GetMapping("/pool2")
    public String index2() {

        Spider.create(new ProxyPoolProcessor2())
                .addUrl("http://www.kuaidaili.com/free/")
                //.addUrl("http://blog.sina.com.cn/s/articlelist_1487828712_0_1.html")
                .addPipeline(ipSpiderPipeline)
                .thread(4)
                .run();
        return "爬虫开启2";
    }
}
