<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css">
</head>
<body>
<div style="margin-top: 20px;margin-left: 20px">
	<!-- 搜索的form -->
	<form class="layui-form layui-form-pane">
		<!-- 一排3个 -->
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">账号</label>
				<div class="layui-input-inline">
					<input id="userName" class="layui-input" placeholder="账号" />
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">姓名</label>
				<div class="layui-input-inline">
					<input id="realName"  class="layui-input" placeholder="账号" />
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">角色</label>
				<div class="layui-input-inline">
					<select id="type">
						<option value="">用户角色</option>
						<option value="2">业务员</option>
						<option value="1">管理员</option>
					</select>
				</div>
			</div>
		</div>
		<!-- 一排3个 -->
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">时间</label>
				<div class="layui-input-inline" style="width: 84px;">
					<input id="minCreateTime" class="layui-input" placeholder="最小时间" style="padding-left:5px" />
				</div>
				<div class="layui-form-mid">-</div>
				<div class="layui-input-inline" style="width: 84px;">
					<input id="maxCreateTime" class="layui-input" placeholder="最大时间" style="padding-left:5px"/>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">状态</label>
				<div class="layui-input-inline">
					<select id="isDel">
						<option value="">用户状态</option>
						<option value="1">有效</option>
						<option value="2">无效</option>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<div class="layui-input-inline" style="width: 90px;">
					<button type="button" class="layui-btn" id="searchBtn"><i class="layui-icon layui-icon-search"></i>搜索</button>
				</div>
				<div class="layui-input-inline">
					<button class="layui-btn  layui-btn-primary" type="reset"><i class="layui-icon layui-icon-refresh-1"></i>重置</button>
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
		<button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</button>
	</div>
</script>
<!-- 行工具栏 -->
<script type="text/html" id="rowBtns">
	<button class="layui-btn layui-btn-sm" lay-event="reset"><i class="layui-icon layui-icon-about"></i>重置密码</button>
	
	{{# if(d.isDel == 2){ }}
		<button class="layui-btn layui-btn-sm layui-btn-warm" lay-event="enable"><i class="layui-icon layui-icon-edit"></i>启用</button>
	{{# } }}		
	
</script>

<!-- 数据编辑的层 -->
<script type="text/html" id="addTpl">
<div style="width: 400px;margin: auto;margin-top: 20px;">
<!-- form 容器已定义好 -->
<form class="layui-form layui-form-pane" lay-filter="formFilter">
	<div class="layui-form-item">
		<label class="layui-form-label">用户名</label>
		<div class="layui-input-inline">
			<input name="userName" class="layui-input" placeholder="用户名" lay-varify="required" lay-reqText="请输入用户名" />
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">密码</label>
		<div class="layui-input-inline">
			<input name="password" readonly   value="123456" class="layui-input"  />
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">姓名</label>
		<div class="layui-input-inline">
			<input name="realName" class="layui-input" placeholder="姓名" lay-varify="required" lay-reqText="请输入用户姓名" />
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">用户角色</label>
		<div class="layui-input-block">
			<input type="radio" name="type" value="2" title="业务员"/>
			<input type="radio" name="type" value="1" title="管理员"/>
		</div>
	</div>
	<button type="button" id="submitBtn" lay-submit lay-filter="submitBtnFilter" class="display:none"></button>
</form>
</div>

</script>
<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script type="text/javascript">
	layui.use(['form','jquery','layer','laydate','table'],function(){
		var form = layui.form;
		var $ = layui.jquery;
		var layer = layui.layer;
		var laydate = layui.laydate;
		var table = layui.table;
		//渲染时间组件
		laydate.render({
			elem:"#minCreateTime",
		});
		laydate.render({
			elem:"#maxCreateTime",
		});
		//渲染table数据表格
		var t = table.render({
			id:"dataTableId",
			elem:"#dataTable",
			url:"user.do?service=list",//数据地址
			page:true,//开启分页
			toolbar:"#headerBtns",//
			height:480,
			parseData:function(rs){//数据解析
				console.log("返回的数据是:");
				console.log(rs);
				
				
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
				{field:'id',title:'用户ID',width:'85'},
				{field:'userName',title:'用户账号',width:'110'},
				{field:'realName',title:'用户名称',width:'110'},
				{field:'type',title:'用户类型',width:'100',templet:function(d){
					if(d.type == 1){
						return "<b style='color:green'>管理员</b>";
					}else if(d.type == 2){
						return "<b style='color:red'>业务员</b>";
					}
				}},
				{field:'isDel',title:'用户状态',width:'100',templet:function(d){
					if(d.isDel == 1){
						return "<b style='color:green'>有效</b>";
					}else if(d.isDel == 2){
						return "<b style='color:gray'>无效</b>";
					}
				}},
				{field:'createTime',title:'创建时间',width:'160'},
				{field:'modifyTime',title:'修改时间',width:'160'},
				{title:'操作',toolbar:'#rowBtns',fixed:'right',width:'270'}
			]]
		});
		//搜索按钮事件
		$("#searchBtn").click(function(){
			var userName = $("#userName").val();
			var realName = $("#realName").val();
			var type = $("#type").val();
			var minCreateTime = $("#minCreateTime").val();
			var maxCreateTime = $("#maxCreateTime").val();
			var isDel = $("#isDel").val();
			//进行表格数据重载
			t.reload({
				where:{
					userName:userName,
					realName:realName,
					type:type,
					minCreateTime:minCreateTime,
					maxCreateTime:maxCreateTime,
					isDel:isDel
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
			}else if(event == "del"){
				del();
			}
		});
		//-----具体添加方法------------
		function add(){
			layer.open({
				type:1,//html
				title:'新增用户',
				content:$("#addTpl").html(),//弹层内容
				area:['400px','400px'],//宽高
				btn:['确认','取消'],
				btnAlign:'c',//按钮居中
				btn1:function(index,layero){//点击确认时触发的方法
					//点击确认时 提交form表单 
					$("#submitBtn").click();//使用js点击隐藏的提交按钮
				},
				success:function(layero,index){//当弹出层  弹出时 调用的函数
					form.render();//重新渲染 表单元素
					//为表单绑定提交监听事件
					form.on("submit(submitBtnFilter)",function(d){
						//获取表单数据
						var param = d.field;
						//使用ajax提交数据
						$.post("user.do?service=add",param,function(rs){
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
		//----删除--------------------
		function del(){
			//获取被选中的数据
			var checkStatus = table.checkStatus("dataTableId");
			var data = checkStatus.data;
			//如果没有数据被选中 则提示要先选中数据
			if(data == null || data.length == 0){
				layer.alert("请先选中需要删除的数据",{icon:7});
				return false;
			}
			//删除的二次确认
			layer.confirm("你确定要删除吗?",{icon:5},function(index){
				//将需要删除的数据的ID 传给后  将这些数据的有效状态修改为无效
				//一种使用普通的表单格式   一种还可以使用json 数据进行传输
				var param = "";
				//此时 循环执行完 则param 就是 id的参数拼接的字符串
				$.each(data,function(index,value){
					var id = value.id;
					param = param +"id="+id+"&";
				});
				$.get("user.do?service=delete",param,function(rs){
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
			});
		};
	//==行监听事件=============================================	
		table.on("tool(dataTableFilter)",function(d){
			var event = d.event;
			var data = d.data;
			if(event == "reset"){
				//重置密码
				resetPwd(data);
			}else if(event == "enable"){
				//让用户数据生效
				enable(data);
			}
		});	
		//--重置密码-----------------------------
		function resetPwd(data){
			//使用二次确认
			layer.confirm("确定要重置密码吗?",function(index){
				//将需要重置的用户ID 传给后台
				$.post("user.do?service=reset",{id:data.id},function(rs){
					//校验业务码
					if(rs.code != 200){
						//显示异常信息
						layer.msg(rs.msg);
						return false;
					}
					layer.msg("重置成功");
					//关闭弹出层
					layer.close(index);
					//重载数据列表
					$("#searchBtn").click();
				});
			});
		}
		//--使用户信息生效-----------------------------
		function enable(data){
			//使用二次确认
			layer.confirm("确定启用该数据吗?",function(index){
				//将需要重置的用户ID 传给后台
				$.post("user.do?service=enable",{id:data.id},function(rs){
					//校验业务码
					if(rs.code != 200){
						//显示异常信息
						layer.msg(rs.msg);
						return false;
					}
					layer.msg("启用成功");
					//关闭弹出层
					layer.close(index);
					//重载数据列表
					$("#searchBtn").click();
				});
			});
		}
	});
</script>
</body>
</html>