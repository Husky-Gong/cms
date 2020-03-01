<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change password</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css">
</head>
<body>
	<form class="layui-form layui-form-pane" style="margin-left: 30px;margin-top: 30px">
		<div class="layui-form-item">
			<label class="layui-form-label">Old password</label>
			<div class="layui-input-inline">
				<input type="text"  name="password" class="layui-input" lay-verify="required" lay-reqText="Input new password"/>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">New password</label>
			<div class="layui-input-inline">
				<input type="text"  name="newPassword" class="layui-input" lay-verify="required" lay-reqText="Input new password"/>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">确认密码</label>
			<div class="layui-input-inline">
				<input type="text"  name="confirmPassword" class="layui-input" lay-verify="required" lay-reqText="Re-input new passwqord"/>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-inline" style="margin-left: 50px;margin-top: 30px">
				<button type="button" class="layui-btn" lay-submit lay-filter="submitBtnFilter"><i class="layui-icon layui-icon-ok"></i>Confirm</button>
				<button type="reset" class="layui-btn"><i class="layui-icon layui-icon-refresh-1"></i>Reset</button>
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
				layer.msg("Two new passwords are nor consistent!");
				return false;
			}
			//将原始密码和新密码提交给后台
			$.post("sys.do?service=updatePwd",data,function(rs){
				if(rs.code != 200){
					layer.msg(rs.msg);
					return false;
				}
				layer.msg("Successfully, please login again!");
			});
			
			return false;//阻止表单默认提交
		});
		
		
		
	});


</script>
</body>
</html>