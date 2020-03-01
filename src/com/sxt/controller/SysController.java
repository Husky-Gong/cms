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
		//get current user from session
		HttpSession session = req.getSession();
		Object obj = session.getAttribute(Constant.KEY_CURRENT_USER);
		if(obj == null) {
			Result rs =  new Result(CodeMsg.SYS_USER_LOGIN_NVALID_ERROR);
			RespWriter.writerJson(resp, rs);
			return;
		}
		// transfer type
		CUser user = (CUser) obj;
		// get user id 
		Integer userId = user.getId();
		// get picture suffix
		String fileName = this.getFileName(part);//get picture name
		String suffix = this.getSuffix(fileName);
		String newFileName = userId + suffix;// get picture full name
		// set saving path
		String realPath = req.getServletContext().getRealPath(Constant.USER_IMG_FOLDER_NAME);
		String  filePath = realPath + File.separator + newFileName;
		String imgUrl = Constant.USER_IMG_FOLDER_NAME + "/" + newFileName;
		System.out.println("absolute path:"+filePath);
		System.out.println("url path:"+imgUrl);
		//first update database then save image
		Result rs = userService.updateImg(userId,imgUrl);
		//get update image result
		if(rs.getCode().equals(CodeMsg.SUCCESS.getCode())) {
			try {
				//save picture
				part.write(filePath);
			} catch (Exception e) {
				rs = new Result(CodeMsg.SYS_USER_IMG_SAVE_ERROR);
				RespWriter.writerJson(resp, rs);
				return;
			}
			rs.setData(imgUrl);
			//update user image url
			user.setImg(imgUrl);
		}
		RespWriter.writerJson(resp, rs);
	}

	
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
	

	private   String getSuffix(String fileName) {
		int index = fileName.lastIndexOf(".");
		return fileName.substring(index);
	}

}
