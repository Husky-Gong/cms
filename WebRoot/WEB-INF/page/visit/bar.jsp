<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>拜访记录--柱状图</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css">
</head>
<body>

<!-- 
	echarts 设计的初衷:
	
		十年前,数据即商机  数据即财富.各种应用不断的搜集数据。但是传统展现数据的方式,主要是表格。不够直观,
		没有表现力。
		在这样的情况下,使用更丰富的元素表现数据,就是一种需求。echarts 是百度团队,研发一组图表插件。最终,进行了
		开源。目前有apache基金会维护。
		在早期设计时,考虑到web端可以在多个平台运行,所以使用js + html + css.但是早期,前端相对而言,比较滞后。
		渲染的图表需要数据,而产生数据的后端开发者又不擅长前端程序。基于这样的现状,echarts这个插件,才用了协议的模式。
		即,前端和后台大家约定,后台只需要生产数据即可,但是,数据必须按照前端指定的数据格式传输(前端需要什么数据就传什么数据)。
		然后,前端负责将图表渲染出来,与后台开发者无关。
	在	echarts 中 有几个比较核心的概念 :
		图例 :legend  图表中数据说明标签
		x轴:xAxis	坐标系x轴
		y轴:yAxis	坐标系y轴
		图表系列:series  图表系列是指一系列图表的集合
		一般 只需要返回 以上 4个属性的值,echarts 就可以渲染出需要的图表。
	
	如何使用echarts:
	1. 下载echarts 且在页面引入
	2. 创建图表容器   注意  图表容器 建议使用div  且必须指定宽高
	3. 根据容器初始化 echart 对象
	4. 设置渲染图表各项参数
	5. 根据 配置参数渲染图表
 -->
 
 <div style="width:888px;margin: auto">
 	<div class="layui-form layui-form-pane" style="margin-top: 30px;margin-bottom: 20px">
 		<div class="layui-form-item">
 			<div class="layui-inline">
	 			<label class="layui-form-label">时间:</label>
	 			<div class="layui-input-inline">
	 				<input class="layui-input" placeholder="开始时间" id="minVisitTime" />
	 			</div>
	 			<div class="layui-form-mid">-</div>
	 			<div class="layui-input-inline">
	 				<input class="layui-input" placeholder="截止时间" id="maxVisitTime" />
	 			</div>
	 			<button class="layui-btn" id="searchBtn">查询</button>
 			</div>
 			
 		</div>
 		
 	</div>
  	<div id="content" style="width:700px;height:500px"></div>
 </div>
 
 
 

 <script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/echarts.js"></script>
 <script type="text/javascript">
 	layui.use(['jquery','laydate','layer'],function(){
 		var $ = layui.jquery;
 		var laydate = layui.laydate;
 		var layer = layui.layer;
 		// echarts 是使用原生js 进行页面渲染  所以是不需要依赖 其他js 插件
 	 	var myEcharts = echarts.init(document.getElementById('content'));
 		
 		//渲染时间控件
 		laydate.render({elem:'#minVisitTime'});
 		laydate.render({elem:'#maxVisitTime'});
 		//echarts 默认参数
 		// 设置需要渲染的图表的参数
 	 	var opt = {
 	 			title:{//图表的标题
 	 				text:"业务员拜访统计",
 	 				textStyle:{
 	 					color:'#B03A5B',//主题文字颜色
 	 				},
 	 			},
 	 		    tooltip: {//提示组件
 	 		        trigger: 'axis',
 	 		        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
 	 		            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
 	 		        }
 	 		    },
 	 		   legend:{
 	 			   data:['拜访量']
 	 		   },
 	 		    xAxis: {
 	 		            data: null
 	 		        },
 	 		    yAxis:{
 	 		            type: 'value'
 	 		        },
 	 		    //系列    name: 就是系列名
 	 		    series:{
 	 		            name: '拜访量',
 	 		            type: 'bar',
 	 		            data: null
 	 		        }
 	 		};
 		//为查询按钮绑定事件
 		$("#searchBtn").click(function(){
 			var minVisitTime = $("#minVisitTime").val();
 			var maxVisitTime = $("#maxVisitTime").val();
 			//使用ajax 请求数据
 			$.get("visit.do?service=bar",{minVisitTime:minVisitTime,maxVisitTime:maxVisitTime},function(rs){
 				if(rs.code != 200){
 					layer.msg(rs.msg);
 					return false;
 				}
 				//解析数据
 				var data = rs.data;
 				var seriesData = data.seriesData;
 				var xaxisData = data.xaxisData;
 				//修改配置参数中数据
 				opt.series.data = seriesData;
 				opt.xAxis.data = xaxisData;
 				//根据配置参数 渲染图表
 				myEcharts.setOption(opt);
 			})
 		});
 		//点击查询按钮
 		$("#searchBtn").click();
 		
 	});
 
 
 	
 
 	
 </script>	
</body>
</html>