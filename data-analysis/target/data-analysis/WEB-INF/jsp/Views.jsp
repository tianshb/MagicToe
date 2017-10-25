<%--
  Created by IntelliJ IDEA.
  User: CrowHawk
  Date: 17/10/23
  Time: 下午12:49
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ECharts</title>
    <!-- 引入 echarts.js -->
    <script type="text/javascript" src="/js/echarts.common.min.js"></script>
    <!-- 引入jquery.js -->
    <script type="text/javascript" src="/js/jquery-3.2.1.min.js"></script>
    <!-- 引入主题 -->
    <script src="/js/theme/infographic.js"></script>
</head>

<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 1100px;height:700px;"></div>

<script type="text/javascript">

    var myChart = echarts.init(document.getElementById('main'),'infographic');
    // 显示标题，图例和空的坐标轴
    myChart.setOption({
        title: {
            text: '主页访问量最高的大V'
        },
        tooltip: {},
        legend: {
            data: ['访问量']
        },
        xAxis: {
            data: []
        },
        yAxis: {},
        series: [
            {
                name: '访问量',
                type: 'bar',
                data: []
            }
        ]
    });

    myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画

    var users = [];    //类别数组（实际用来盛放X轴坐标值）
    //var nums = [];    //销量数组（实际用来盛放Y坐标值）
    var views = [];


    $.ajax({
        type: "post",
        async: true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: "getViews",    //请求发送到TestServlet处
        data: {},
        dataType: "json",        //返回数据形式为json
        success: function (result) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            if (result) {
                for (var i = 0; i < result.length; i++) {
                    users.push(result[i].name);    //挨个取出类别并填入类别数组
                }
                for (var i = 0; i < result.length; i++) {
                    views.push(result[i].views);    //挨个取出销量并填入销量数组
                }

                myChart.hideLoading();    //隐藏加载动画
                myChart.setOption({        //加载数据图表
                    xAxis: {
                        data: users
                    },
                    series: [
                        {// 根据名字对应到相应的系列
                            name: '访问量',
                            data: views
                        }
                    ]
                });
            }

        },
        error: function (errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败!");
            myChart.hideLoading();
        }
    })
</script>
</body>
</html>