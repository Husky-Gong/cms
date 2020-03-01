<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Profile</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css">
</head>
<body>
	<div style="margin-left: 30px;margin-top: 30px">
		<!-- 图像默认值是用户的图片 -->
		<p>
			<img  src="${user.img}" style="width:200px;height:200px;border:1px solid #009688" id="userImg"  onerror="javascript:this.src='${pageContext.request.contextPath}/resources/image/user_icon.jpg';" />
		</p>
		<button class="layui-btn" id="btn-upload" style="margin-top: 20px;width:200px">上传图像</button>
	</div>
	<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
	<script type="text/javascript">
		layui.use(['jquery','upload','layer'],function(){
			var $ = layui.jquery;
			var upload = layui.upload;
			var layer = layui.layer;
			
			//渲染上传组件
			var opt = {
				elem:"#userImg",
				url:"sys.do?service=updateImg",//上传的接口
				size:2000, //限制图片的大小	
				accept:"images",//只支持图片  注意 默认就是  图片 可以不指定
				acceptMime:"image/*",//只显示电脑上的图片文件
				auto:false,//不自动上传
				bindAction:'#btn-upload',//绑定触发上传的按钮
				field:'userImg',//文件上传标识的 文件数据name值
				choose:function(obj){ //选择文件后触发的方法
					//进行图片预览处理
					obj.preview(function(index,file,result){
						//修改src的属性值
						$("#userImg").attr("src",result);
					});
				},
				done:function(rs,index,upload){//请求完成触发的方法
					//请求完成进行业务处理
					if(rs.code != 200){
						//将异常业务信息进行展示
						layer.msg(rs.msg);
						return false;
					}
					layer.msg("图片修改成功");
					//修改页面上  用户的头像
					var imgUrl = rs.data;
					//修改头像
					//$("#mainUserImg").attr("src",imgUrl); 由于当前页面在子 iframe中
					//而  img 标签在父页面中  所以 需要操作的父页面的元素
					parent.document.getElementById("mainUserImg").src=imgUrl+"?"+new Date();//由于  imgUrl 的命名是根据userId定义的  userId是固定的 所以 imgUrl是规定的  为避免缓存  拼接随机的字符串
				}
			};
			upload.render(opt);
			
			
		});
	
	
	</script>
</body>
</html>