<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>客户列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css">
</head>
<body>
<div style="margin-top: 20px;margin-left: 20px">
	<!-- 搜索的form -->
	<form class="layui-form layui-form-pane">
		<!-- 一排3个 -->
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">客户名称</label>
				<div class="layui-input-inline">
					<input id="custName" class="layui-input" placeholder="客户名称" />
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">公司名称</label>
				<div class="layui-input-inline">
					<input id="custCompany"  class="layui-input" placeholder="公司名称" />
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">手机号</label>
				<div class="layui-input-inline">
					<input id="custPhone"  class="layui-input" placeholder="手机号" />
				</div>
			</div>
		</div>
		<!-- 一排3个 -->
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">生日</label>
				<div class="layui-input-inline" style="width: 84px;">
					<input id="minCustBirth" class="layui-input" placeholder="最小时间" style="padding-left:5px" />
				</div>
				<div class="layui-form-mid">-</div>
				<div class="layui-input-inline" style="width: 84px;">
					<input id="maxCustBirth" class="layui-input" placeholder="最大时间" style="padding-left:5px"/>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">性别</label>
				<div class="layui-input-inline">
					<select id="custSex">
						<option value="">性别</option>
						<option value="1">男</option>
						<option value="2">女</option>
					</select>
				</div>
			</div>
			<div class="layui-inline" >
				<label class="layui-form-label">业务员</label>
				<div class="layui-input-inline" >
					<select id="userId">
						<option value="">请选择业务员</option>
						<%-- 
							<c:forEach items="${users}" var="user">
							<option value="${user.id}">${user.realName}</option>
						</c:forEach> 
						--%>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<div class="layui-input-inline" style="width: 90px;">
					<button type="button" class="layui-btn" id="searchBtn"><i class="layui-icon layui-icon-search"></i>搜索</button>
				</div>
			</div>
		</div>
	</form>
	<hr class="layui-bg-green">
	<table id="dataTable" lay-filter="dataTableFilter"></table>
</div>
<!-- 头工具栏 -->
<script type="text/html" id="headerBtns">
	<div class="layui-btn-group">
		<button class="layui-btn layui-btn-sm" lay-event="add"><i class="layui-icon layui-icon-add-1"></i>新增</button>
		<button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="updateSales"><i class="layui-icon layui-icon-edit"></i>修改业务员</button>
	</div>
</script>
<!-- 行工具栏 -->
<script type="text/html" id="rowBtns">
	<button class="layui-btn layui-btn-sm" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</button>
	<button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="visit"><i class="layui-icon layui-icon-chat"></i>拜访</button>
</script>

<!-- 数据编辑的层 -->
<script type="text/html" id="editTpl">
<div style="width: 650px;margin: auto;margin-top: 20px;">
<!-- form 容器已定义好 -->
<form class="layui-form layui-form-pane" lay-filter="formFilter">
	<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">客户名称:</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" name="custName" placeholder="客户名称" lay-verify="required" lay-reqText="客户名称不能为空" />
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">所属公司:</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" name="custCompany" placeholder="所属公司" lay-verify="required" lay-reqText="所属公司不能为空" />
				</div>
			</div>
	</div>
	<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">职位:</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" name="custPosition" placeholder="客户职位" lay-verify="required" lay-reqText="客户职位不能为空" />
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">手机号:</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" name="custPhone" placeholder="客户手机号" lay-verify="required|phone" lay-reqText="客户手机号不能为空" />
				</div>
			</div>
	</div>
	<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">生日:</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" name="custBirth"  id="custBirth" placeholder="客户生日" lay-verify="required" lay-reqText="客户生日不能为空" readonly />
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">性别:</label>
				<div class="layui-input-inline">
					<input type="radio" class="layui-input" name="custSex" checked value="1" title="男" />
					<input type="radio" class="layui-input" name="custSex"  value="2" title="女" />
				</div>
			</div>
	</div>
	<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">业务员:</label>
				<div class="layui-input-inline">
					<select name="userId" id="editUserId" lay-verify="required" lay-reqText="请选择业务员">
						<option value="">请选择业务员</option>
					</select>
				</div>
			</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">客户简介:</label>
			<div class="layui-input-block">
				<textarea name="custDesc" id="custDesc" style="width:512px"></textarea>
			</div>
	</div>
	<button type="button" id="submitBtn" lay-submit lay-filter="submitBtnFilter" class="display:none"></button>
</form>
</div>
</script>
<!-- 修改业务员的模板 -->
<script type="text/html" id="setSalesTpl">
<div style="width: 320px;margin: auto;margin-top: 20px;">
<!-- form 容器已定义好 -->
<form class="layui-form layui-form-pane" lay-filter="formFilter">
	<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">业务员:</label>
				<div class="layui-input-inline">
					<select name="userId" id="editUserId" lay-verify="required" lay-reqText="请选择业务员">
						<option value="">请选择业务员</option>
					</select>
				</div>
			</div>
	</div>
	<button type="button" id="submitBtn" lay-submit lay-filter="submitBtnFilter" class="display:none"></button>
</form>
</div>
</script>

<script type="text/html" id="visitTpl">
<div style="width: 320px;margin: auto;margin-top: 20px;">
<!-- form 容器已定义好 -->
<form class="layui-form layui-form-pane" lay-filter="formFilter">
	<input name="id" type="hidden" />
	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label">客户:</label>
				<div class="layui-input-inline">
					<input name="custName" class="layui-input" readonly />
				</div>
			</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label">时间:</label>
				<div class="layui-input-inline">
					<input name="visitTime" id="visitTime"  class="layui-input" readonly />
				</div>
			</div>
	</div>
	<button type="button" id="submitBtn" lay-submit lay-filter="submitBtnFilter" class="display:none"></button>
</form>
</div>
</script>

<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script type="text/javascript">
	layui.use(['form','jquery','layer','laydate','table','layedit'],function(){
		var form = layui.form;
		var $ = layui.jquery;
		var layer = layui.layer;
		var laydate = layui.laydate;
		var table = layui.table;
		var layedit = layui.layedit;
		//渲染时间组件
		laydate.render({
			elem:"#minCustBirth",
		});
		laydate.render({
			elem:"#maxCustBirth",
		});
		//方案二: 使用ajax 获取 所有的业务员  然后将业务员信息 使用js 放入到下拉框中 重新渲染form表单
		function getSalesmans(elem){
			//获取所有的业务员
			$.ajax({
				url:"user.do?service=salesman",
				async:false,// 使用同步请求
				/* 
					为什么使用同步?
					因为在数据编辑中,会对客户数据进行赋值操作.若使用异步,很可能页面的dom 没有渲染完成
					而编辑中需要设置这个dom状态 基于这种情况,由于都不存在这个dom,肯定无法设置状态。
					所以,使用同步模式。当dom真正的渲染完成了,后面在设置dom状态。这样就不会出现设置状态无效的问题。
				
				*/
				success:function(rs){
					if(rs.code != 200){
						layer.msg(rs.msg);
						return false;
					}
					var data = rs.data;//获取业务员数据
					var select = $(elem);
					var optHtml = "";
					$.each(data,function(index,value){
						var userId = value.id;
						var realName = value.realName;
						optHtml = optHtml + "<option value='"+userId+"'>"+realName+"</option>";
					});
					select.append(optHtml);
					form.render("select");
				}
			});
		}
		//执行
		getSalesmans("#userId");
		//渲染table数据表格
		var t = table.render({
			id:"dataTableId",
			elem:"#dataTable",
			url:"customer.do?service=list",//数据地址
			page:true,//开启分页
			toolbar:"#headerBtns",//
			height:480,
			parseData:function(rs){//数据解析
				if(rs.code == 200){
					return {
						"code":rs.code,
						"msg":rs.msg,
						"count":rs.data.count,
						"data":rs.data.data
					}
				}
			},
			response:{//重新定义响应码
				"statusCode":200
			},
			cols:[[//数据表头
				{type:'checkbox'},
				{field:'id',title:'客户ID',width:'80'},
				{field:'custName',title:'客户名称',width:'100'},
				{field:'custCompany',title:'客户公司',width:'110'},
				{field:'custPosition',title:'客户职位',width:'120'},
				{field:'custPhone',title:'客户电话',width:'120'},
				{field:'custBirth',title:'客户生日',width:'110'},
				{field:'custSex',title:'客户性别',width:'90',style:"text-align:center",templet:function(d){
					if(d.custSex == 1){
						return "<b style='color:green'>男</b>";
					}else if(d.custSex == 2){
						return "<b style='color:red'>女</b>";
					}
				}},
				{field:'realName',title:'业务员',width:'110'},
				{field:'createTime',title:'创建时间',width:'160'},
				{field:'modifyTime',title:'修改时间',width:'160'},
				{title:'操作',toolbar:'#rowBtns',fixed:'right',width:'270'}
			]]
		});
		//搜索按钮事件
		$("#searchBtn").click(function(){
			var custName = $("#custName").val();
			var custCompany = $("#custCompany").val();
			var custPhone = $("#custPhone").val();
			var minCustBirth = $("#minCustBirth").val();
			var maxCustBirth = $("#maxCustBirth").val();
			var custSex = $("#custSex").val();
			var userId = $("#userId").val();
			//进行表格数据重载
			t.reload({
				where:{
					custName:custName,
					custCompany:custCompany,
					custPhone:custPhone,
					minCustBirth:minCustBirth,
					maxCustBirth:maxCustBirth,
					custSex:custSex,
					userId:userId
				},
				page:1
			});
		});
		//=====头监听事件=====================================================================	
		table.on('toolbar(dataTableFilter)',function(d){
			//获取具体的事件类型
			var event = d.event;
			if(event == "add"){
				//调用具体的添加方法
				add();
			}else if(event == "updateSales"){
				updateSales();
			}
		});
		//-----具体添加方法------------
		function add(){
			layer.open({
				type:1,//html
				title:'编辑客户',
				content:$("#editTpl").html(),//弹层内容
				area:['780px','550px'],//宽高
				btn:['确认','取消'],
				btnAlign:'c',//按钮居中
				btn1:function(index,layero){//点击确认时触发的方法
					//点击确认时 提交form表单 
					$("#submitBtn").click();//使用js点击隐藏的提交按钮
				},
				success:function(layero,index){//当弹出层  弹出时 调用的函数
					//渲染layedit	 编辑器											 
					var layeidtIndex = layedit.build("custDesc", {hideTool:['image'],height:100})
					//渲染时间组件
					laydate.render({elem:'#custBirth'});
					//渲染业务员
					getSalesmans("#editUserId");
					form.render();//重新渲染 表单元素
					//为表单绑定提交监听事件
					form.on("submit(submitBtnFilter)",function(d){
						//获取表单数据
						var param = d.field;
						//获取编辑器内容
						param.custDesc = layedit.getContent(layeidtIndex);
						//使用ajax提交数据
						$.post("customer.do?service=add",param,function(rs){
							//校验业务码
							if(rs.code != 200){
								//显示异常信息
								layer.msg(rs.msg);
								return false;
							}
							//关闭弹出层
							layer.close(index);
							//重载数据列表
							$("#searchBtn").click();
						});
						//阻止表单的默认提交行为
						return false;
					});
				}
			});
		}
		//修改业务员=================================================
		function updateSales(){
			//获取选中的数据
			var checkStatus = table.checkStatus("dataTableId");
			var data = checkStatus.data;
			//如果没有数据被选中 则提示要先选中数据
			if(data == null || data.length == 0){
				layer.alert("请先选中需要修改的数据",{icon:7});
				return false;
			}
			//获取所有的客户的ID
			//定义一个装客户ID 的容器
			var custIds = new Array();
			//使用循环  将客户Id 放入容器
			$.each(data,function(index,value){
				custIds.push(value.id);
			});
			//使用弹出层 将所有的业务员列出来
			layer.open({
				type:1,//html
				content:$("#setSalesTpl").html(),//
				area:['400px','400px'],
				btn:['确认','取消'],
				btnAlign:'c',//按钮居中
				btn1:function(index,layero){//点击确认时触发的方法
					//点击确认时 提交form表单 
					$("#submitBtn").click();//使用js点击隐藏的提交按钮
				},
				success:function(layero,index){
					//获取业务员 渲染下拉框
					getSalesmans("#editUserId");
					//为表单绑定提交监听事件
					form.on("submit(submitBtnFilter)",function(d){
						//具体的业务员Id
						var userId = d.field.userId;
						//创建json 对象
						var param = new Object();
						param.custIds = custIds;
						param.userId = userId;
						//转化为json字符串  进行传输
						var paramStr = JSON.stringify(param);
						//使用ajax提交数据
						$.post("customer.do?service=updateSales",{param:paramStr},function(rs){
							//校验业务码
							if(rs.code != 200){
								//显示异常信息
								layer.msg(rs.msg);
								return false;
							}
							//关闭弹出层
							layer.close(index);
							//重载数据列表
							$("#searchBtn").click();
						});
						//阻止表单的默认提交行为
						return false;
					});
				}
			});
			
		}	
	
	//==行监听事件=============================================	
		table.on("tool(dataTableFilter)",function(d){
			var event = d.event;
			var data = d.data;
			if(event == "edit"){
				//重置密码
				edit(data);
			}else if(event == "visit"){
				//让用户数据生效
				visit(data);
			}
		});	
		//--编辑数据-----------------------------
		function edit(data){
			layer.open({
				type:1,//html
				title:'编辑客户',
				content:$("#editTpl").html(),//弹层内容
				area:['780px','550px'],//宽高
				btn:['确认','取消'],
				btnAlign:'c',//按钮居中
				btn1:function(index,layero){//点击确认时触发的方法
					//点击确认时 提交form表单 
					$("#submitBtn").click();//使用js点击隐藏的提交按钮
				},
				success:function(layero,index){//当弹出层  弹出时 调用的函数
					//渲染时间组件
					laydate.render({elem:'#custBirth'});
					//渲染业务员
					getSalesmans("#editUserId");
					//为表单赋值
					form.val("formFilter",data);//此时都没有  option 值为2的选项   肯定无法选中option 
					//渲染layedit	 编辑器											 
					var layeitIndex = layedit.build("custDesc", {hideTool:['image'],height:100})
					form.render();//重新渲染 表单元素
					//为表单绑定提交监听事件
					form.on("submit(submitBtnFilter)",function(d){
						//获取表单数据
						var param = d.field;
						//获取编辑器内容
						param.custDesc = layedit.getContent(layeitIndex);
						param.id = data.id;//客户id
						//使用ajax提交数据
						$.post("customer.do?service=update",param,function(rs){
							//校验业务码
							if(rs.code != 200){
								//显示异常信息
								layer.msg(rs.msg);
								return false;
							}
							//关闭弹出层
							layer.close(index);
							//重载数据列表
							$("#searchBtn").click();
						});
						//阻止表单的默认提交行为
						return false;
					});
				}
			});
		}
		//--拜访客户-----------------------------
		function visit(data){
			layer.open({
				type:1,//html
				title:'拜访客户',
				content:$("#visitTpl").html(),//弹层内容
				area:['500px','350px'],//宽高
				offset:'t',//靠上方  因为时间控件存在BUG
				btn:['确认','取消'],
				btnAlign:'c',//按钮居中
				btn1:function(index,layero){//点击确认时触发的方法
					//点击确认时 提交form表单 
					$("#submitBtn").click();//使用js点击隐藏的提交按钮
				},
				success:function(layero,index){//当弹出层  弹出时 调用的函数
					//为表单赋值
					form.val("formFilter",data);//此时都没有  option 值为2的选项   肯定无法选中option 
					form.render();//重新渲染 表单元素
					//渲染时间组件
					laydate.render({elem:'#visitTime',zIndex:99999999})
					
					
					//为表单绑定提交监听事件
					form.on("submit(submitBtnFilter)",function(d){
						//获取表单数据
						var param = d.field;
						//使用ajax提交数据
						$.post("customer.do?service=addVisitLog",param,function(rs){
							//校验业务码
							if(rs.code != 200){
								//显示异常信息
								layer.msg(rs.msg);
								return false;
							}
							//关闭弹出层
							layer.close(index);
						});
						//阻止表单的默认提交行为
						return false;
					});
				}
			});
		}
	});
</script>
</body>
</html>