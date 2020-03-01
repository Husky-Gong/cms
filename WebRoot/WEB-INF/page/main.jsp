<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>CMS管理系统</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">CMS管理系统</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img id="mainUserImg" src="${user.img}" onerror="javascript:this.src='${pageContext.request.contextPath}/resources/image/user_icon.jpg';" class="layui-nav-img">
			${user.realName}
        </a>
      </li>
      <li class="layui-nav-item"><a href="user.do?service=loginOut">退出</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree" >
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">基本信息</a>
          <c:if test="${user.type == 1}">
         	<dl class="layui-nav-child">
            	<dd><a href="page.do?service=userList" target="content">用户管理</a></dd>
          	</dl>
          </c:if>
          <c:if test="${user.type == 2}">
         	<dl class="layui-nav-child">
            	<dd><a href="page.do?service=customerList" target="content">客户管理</a></dd>
          	</dl>
          </c:if>
        </li>
        <li class="layui-nav-item">
        	<a href="javascript:;">系统设置</a>
        	<dl class="layui-nav-child">
            	<dd><a href="page.do?service=updatePwd" target="content">修改密码</a></dd>
            	<dd><a href="page.do?service=updateImg" target="content">修改图像</a></dd>
          </dl>
        </li>
        <c:if test="${user.type == 1}">
         <li class="layui-nav-item">
        	<a href="javascript:;">统计管理</a>
        	<dl class="layui-nav-child">
            	<dd><a href="page.do?service=bar" target="content">拜访记录</a></dd>
          </dl>
        </li>
        </c:if>
       
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="width: 100%;height: 100%">
    	<iframe style="width: 100%;height: 100%;border:0px" name="content" src="http://nows.fun/"></iframe>
    </div>
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © 尚学堂-Java191118
  </div>
</div>
<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
});
</script>
</body>
</html>