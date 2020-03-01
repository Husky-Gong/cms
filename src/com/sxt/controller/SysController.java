package com.sxt.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.sxt.common.CodeMsg;
import com.sxt.common.Constant;
import com.sxt.common.Result;
import com.sxt.pojo.CUser;
import com.sxt.service.ICUserService;
import com.sxt.service.impl.CUserServiceImpl;
import com.sxt.util.RespWriter;


@WebServlet("/sys.do")
@MultipartConfig
public class SysController extends HttpServlet {


	private static final long serialVersionUID = 5731661919883886725L;
	
	private ICUserService userService = new CUserServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String service = req.getParameter("service");
		try {
			Method method  = this.getClass().getDeclaredMethod(service, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this, req,resp);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	/**
	 * Update password
	 */			   
	protected void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String password = req.getParameter("password");
		String newPassword = req.getParameter("newPassword");
		/*
		 * get user from current session and compare its password
		 * only can change password after authorizing the password
		 */
		HttpSession session = req.getSession();
		Object obj = session.getAttribute(Constant.KEY_CURRENT_USER);
		if(obj == null) {
			Result rs =  new Result(CodeMsg.SYS_USER_LOGIN_NVALID_ERROR);
			RespWriter.writerJson(resp, rs);
			return;
		}
		CUser user = (CUser) obj; 
		Result rs = userService.updatePwd(user,password,newPassword);
		//After changing password, user should login again
		if(rs.getCode().equals(CodeMsg.SUCCESS.getCode()) ) {
			session.removeAttribute(Constant.KEY_CURRENT_USER);
			session.invalidate();
		}
		RespWriter.writerJson(resp, rs);
		
	}
	
	/**
	 * Update user profile
	 */
	protected void updateImg(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get profile image
		Part part = req.getPart("userImg");//注意 此时  userImg 的值 是 field 属性值
		//获取当前用户
		//从session中获取当前用户
		HttpSession session = req.getSession();
		Object obj = session.getAttribute(Constant.KEY_CURRENT_USER);
		if(obj == null) {
			Result rs =  new Result(CodeMsg.SYS_USER_LOGIN_NVALID_ERROR);
			RespWriter.writerJson(resp, rs);
			return;
		}
		//转化为当前用户
		CUser user = (CUser) obj;
		//获取用户ID 
		Integer userId = user.getId();
		//图片后缀
		String fileName = this.getFileName(part);//获取图片的真实名称
		String suffix = this.getSuffix(fileName);
		String newFileName = userId + suffix;// 将用户ID 和文件后缀 组成文件名称
		//将图片保存
		String realPath = req.getServletContext().getRealPath(Constant.USER_IMG_FOLDER_NAME);
		//文件的物理路径
		String  filePath = realPath + File.separator + newFileName;
		//图片的网络路径    
		String imgUrl = Constant.USER_IMG_FOLDER_NAME + "/" + newFileName;//网络路径
		System.out.println("物理路径:"+filePath);
		System.out.println("网络路径:"+imgUrl);
		//先修改数据库数据   然后再保存图像
		Result rs = userService.updateImg(userId,imgUrl);
		//更新头像成功
		if(rs.getCode().equals(CodeMsg.SUCCESS.getCode())) {
			try {
				//进行图片保存
				part.write(filePath);
			} catch (Exception e) {
				rs = new Result(CodeMsg.SYS_USER_IMG_SAVE_ERROR);
				RespWriter.writerJson(resp, rs);
				return;
			}
			rs.setData(imgUrl);
			//修改session中  用户的img 属性值
			user.setImg(imgUrl);
		}
		RespWriter.writerJson(resp, rs);
	}
	/**
	 * @Title: getFileName
	 * @author: Mr.T   
	 * @date: 2020年2月28日 下午12:01:06 
	 * @Description: 获取图片名称
	 * @param part
	 * @return
	 * @return: String
	 */
	private String  getFileName(Part part) {
		// 由于文件的原名称 在请求头中
		String header = part.getHeader("Content-Disposition");
		//form-data; name="file"; filename="2.jpeg"
		String[] info = header.split(" ");
		//从数组中获取原名称相关信息 :filename="2.jpeg"
		String name = info[2].trim();
		name = name.substring(10 , name.length() - 1);
		return name;
	}
	
	/**
	 * @Title: getSuffix
	 * @author: Mr.T   
	 * @date: 2020年2月28日 下午12:05:03 
	 * @Description: 获取文件后缀
	 * @param fileName
	 * @return
	 * @return: String
	 */
	private   String getSuffix(String fileName) {
		int index = fileName.lastIndexOf(".");
		return fileName.substring(index);
	}

}
