# MagicToe
MagicToe是一个基于Java爬虫框架[WebMagic](https://github.com/code4craft/webmagic)的Java爬虫实战案例，MagicToe提供了从获取数据到数据持久化、可视化分析以及构建简单的代理池等一系列完整流程，旨在为初涉Java爬虫的程序员提供一个参考教程和一整套完整的解决方案。

## 仓库目录
+ [hupu-spider](https://github.com/CrowHawk/MagicToe/tree/master/hupu-spider)：爬虫功能实现模块，使用**WebMagic + SpringBoot + MyBatis**基础架构，NLP工具包是[Ansj中文分词](https://github.com/NLPchina/ansj_seg)，定制抽取逻辑，将爬取的数据持久化到**MySQL**数据库中，本仓库中的代码示例爬取的是虎扑步行街。
+ [data-analysis](https://github.com/CrowHawk/MagicToe/tree/master/data-analysis)：数据分析及可视化模块，使用**Spring + SpringMVC + MyBatis**的基础架构，数据可视化采用的前端技术是 **jsp +** [Echarts](http://www.echartsjs.com/)。
+ [ip-spider](https://github.com/CrowHawk/MagicToe/tree/master/data-analysis)（可选）：爬取代理网站模块，技术选型同hupu-spider，将代理网站上的免费代理地址爬取到本地数据库中，实现一个简单的IP池，以供hupu-spider作为代理使用。

## QuickStart

**爬虫模块环境准备：**
+ JDK 1.8+
+ maven 4.0.0+
+ webmagic 0.7.3+
+ ansj_seg 5.1.1+
+ springboot 1.5.7+
+ mybatis 1.3.1+
+ mysql 5.1.21+

**运行爬虫：**
以爬取虎扑步行街的帖子、用户和评论为例。
1. 初始化数据库
在本地MySQL中创建自己的schema，执行初始化数据库的脚本 [`hupu-spider/src/main/resources/db.sql`](https://github.com/CrowHawk/MagicToe/blob/master/hupu-spider/src/main/resources/db.sql) ，并根据自己的数据库信息修改配置文件 [`hupu-spider/src/main/resources/application.yml`](https://github.com/CrowHawk/MagicToe/blob/master/hupu-spider/src/main/resources/application.yml) 中的数据源信息。
2. 启动爬虫
hupuspider通过URL请求的方式运行，在浏览器中键入 **localhost:8080/**（默认端口为8080，如果遇到端口冲突，可以在配置文件 [`hupu-spider/src/main/resources/application.yml`](https://github.com/CrowHawk/MagicToe/blob/master/hupu-spider/src/main/resources/application.yml) 中修改端口），爬虫即可开始运行了。
3. 运行数据可视化模块
将数据爬取到数据库中后，直接在Tomcat中运行[data-analysis](https://github.com/CrowHawk/MagicToe/tree/master/data-analysis)模块即可，通过在浏览器中输入不同的URL可以得到不同的图表，具体请查看 `data-analysis/src/main/java/com/crow/web/EchartsController.java` 。

## 效果展示
以虎扑用户的地域分布为例：

<center><img src="http://pic.yupoo.com/crowhawk/a5b549cf/ae262b02.png"></center>

更多详细的分析请参考我的博客[《数据不说谎：用网络爬虫探秘虎扑步行街》](https://crowhawk.github.io/2017/10/25/hupuspider/)。

## TODO
* [ ] 使用Redis分布式队列实现分布式爬取。
* [ ] 使用Quarts实现定时更新数据。

## 联系作者
+ Personal Website：[Crow Home](https://crowhawk.github.io/)
+ 知乎：[Martin Crow](https://www.zhihu.com/people/martin-crow/activities)

