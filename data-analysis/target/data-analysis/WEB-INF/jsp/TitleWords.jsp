<%--
  Created by IntelliJ IDEA.
  User: CrowHawk
  Date: 17/10/23
  Time: 下午9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <!-- 引入jquery.js -->
    <script type="text/javascript" src="/js/jquery-3.2.1.min.js"></script>
    <script src='https://cdn.bootcss.com/echarts/3.7.0/echarts.simple.js'></script>
    <script src='/js/echarts-wordcloud.js'></script>
</head>
<body>
<style>
    html, body, #main {
        width: 100%;
        height: 100%;
        margin: 0;
    }
</style>
<div id='main'></div>
<script>
    var chart = echarts.init(document.getElementById('main'));

    var option = {
        tooltip: {},
        backgroundColor: '#F7F7F7',
        series: [ {
            type: 'wordCloud',
            gridSize: 2,
            sizeRange: [30, 150],
            rotationRange: [-45, 90],
            shape: 'circle',
            width: 1000,
            height: 1000,
            drawOutOfBound: true,
            autoSize: {
                enable: true,
                minSize: 10
            },
            textStyle: {
                normal: {
                    color: function () {
                        return 'rgb(' + [
                                Math.round(Math.random() * 160),
                                Math.round(Math.random() * 160),
                                Math.round(Math.random() * 160)
                            ].join(',') + ')';
                    }
                },
                emphasis: {
                    shadowBlur: 10,
                    shadowColor: '#333'
                }
            },
            data: [
                {
                    name: 'Crow S Club',
                    value: 10000,
                    textStyle: {
                        normal: {
                            color: 'black'
                        },
                        emphasis: {
                            color: 'red'
                        }
                    }
                }
            ]
        } ]
    };

    chart.setOption(option);

    chart.showLoading();    //数据加载完之前先显示一段简单的loading动画

    $.ajax({
        type: "post",
        async: true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: "getTitleWord",    //请求发送到TestServlet处
        data: [],
        dataType: "json",        //返回数据形式为json
        success: function (result) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            //if (result) {
            chart.hideLoading();    //隐藏加载动画
            chart.setOption({        //加载数据图表
                series: [
                    {// 根据名字对应到相应的系列
                        data: result
                    }
                ]
            });

            //}

        },
        error: function (errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败!");
            myChart.hideLoading();
        }
    })

    window.onresize = chart.resize;
</script>
</body>
</html>
