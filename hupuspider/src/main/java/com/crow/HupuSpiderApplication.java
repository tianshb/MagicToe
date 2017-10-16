package com.crow;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



//@RestController
@SpringBootApplication
public class HupuSpiderApplication {

	/**
	 *

	@Qualifier("PostInfoPipeline")
	@Autowired
	PostInfoPipeline postInfoPipeline;

	@GetMapping("/")
	public String index() {
		Spider.create(new PostInfoPageProcessor())
				.addUrl("https://bbs.hupu.com/bxj-1")
				//.addUrl("http://blog.sina.com.cn/s/articlelist_1487828712_0_1.html")
				.addPipeline(postInfoPipeline)
				.thread(5)
				.run();
		return "爬虫开启";
	}
	**/
	public static void main(String[] args) {
		SpringApplication.run(HupuSpiderApplication.class, args);
	}
}
