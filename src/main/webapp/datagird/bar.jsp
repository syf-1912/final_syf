<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    pageContext.setAttribute("path", request.getContextPath());
%>

<div id="bar_main" style="width: 900px;height:600px;"></div>
<script type="text/javascript">

    var goEasy = new GoEasy({
        appkey: "BS-2219c2c1a43e43f483e21df331a68025"
    });
    goEasy.subscribe({
        channel: "syf",
        onMessage: function (message) {
            alert("Channel:" + message.channel + " content:" + message.content);
        }
    });

    $.ajax({
        url: '${path}/user/bar',
        type: 'post',
        datatype: 'json',
        success: function (data) {
            var myChart = echarts.init(document.getElementById('bar_main'));
            var option = {
                title: {
                    text: '持名法州APP活跃用户'
                },
                tooltip: {},
                legend: {
                    data: ['人数']
                },
                xAxis: {
                    data: data.xAxis
                },
                yAxis: {},
                series: [{
                    name: '人数',
                    type: 'bar',
                    data: data.users
                }]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });

</script>