<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改密码</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css">
</head>
<body>
	<form class="layui-form layui-form-pane" style="margin-left: 30px;margin-top: 30px">
		<div class="layui-form-item">
			<label class="layui-form-label">原始密码</label>
			<div class="layui-input-inline">
				<input type="text"  name="password" class="layui-input" lay-verify="required" lay-reqText="请输入原始密码"/>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">新密码</label>
			<div class="layui-input-inline">
				<input type="text"  name="newPassword" class="layui-input" lay-verify="required" lay-reqText="请输入新密码"/>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">确认密码</label>
			<div class="layui-input-inline">
				<input type="text"  name="confirmPassword" class="layui-input" lay-verify="required" lay-reqText="请再次输入新密码"/>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-inline" style="margin-left: 50px;margin-top: 30px">
				<button type="button" class="layui-btn" lay-submit lay-filter="submitBtnFilter"><i class="layui-icon layui-icon-ok"></i>确认</button>
				<button type="reset" class="layui-btn"><i class="layui-icon layui-icon-refresh-1"></i>重置</button>
			</div>
		</div>
	</form>

<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script type="text/javascript">
	layui.use(['form','layer','jquery'],function(){
		var form  = layui.form;
		var layer = layui.layer;
		var $ = layui.jquery;
		// 表单提交监听
		form.on("submit(submitBtnFilter)",function(d){
			//校验新新密码和确认密码是否一致
			var data = d.field;
			var newPassword = data.newPassword;
			var confirmPassword = data.confirmPassword;
			if(newPassword != confirmPassword){
				layer.msg("两次新密码不一致");
				return false;
			}
			//将原始密码和新密码提交给后台
			$.post("sys.do?service=updatePwd",data,function(rs){
				if(rs.code != 200){
					layer.msg(rs.msg);
					return false;
				}
				layer.msg("密码修改成功!建议重新登录");
			});
			
			return false;//阻止表单默认提交
		});
		
		
		
	});


</script>
</body>
</html>