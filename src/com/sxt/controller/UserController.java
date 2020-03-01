package com.sxt.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
/**
 * @ClassName: UserController 
 * @Description: 用户业务控制类
 * @author: Mr.T
 * @date: 2020年2月25日 下午3:25:18
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sxt.common.CodeMsg;
import com.sxt.common.Constant;
import com.sxt.common.Result;
import com.sxt.common.UserCache;
import com.sxt.pojo.CUser;
import com.sxt.service.ICUserService;
import com.sxt.service.impl.CUserServiceImpl;
import com.sxt.util.RespWriter;

import cn.hutool.core.util.StrUtil;
@WebServlet("/user.do")
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 7723086583204133716L;

	
	private ICUserService userService = new CUserServiceImpl();


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String service = req.getParameter("service");
		System.out.print(service);
		try {
			Method method  = this.getClass().getDeclaredMethod(service, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this, req,resp);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	
	protected void list(HttpServletRequest req, HttpServletResponse resp) {
		String userName = req.getParameter("userName");
		String realName = req.getParameter("realName");
		String type = req.getParameter("type");
		String isDel = req.getParameter("isDel");
		String minCreateTime = req.getParameter("minCreateTime");
		String maxCreateTime = req.getParameter("maxCreateTime");
		//page settings
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		
		Map<String,Object> param = new HashMap<>();
		if(StrUtil.isNotBlank(userName)) {
			param.put("userName", userName);
		}
		if(StrUtil.isNotBlank(realName)) {
			param.put("realName", realName);
		}
		if(StrUtil.isNotBlank(type)) {
			param.put("type", type);
		}
		if(StrUtil.isNotBlank(isDel)) {
			param.put("isDel", isDel);
		}
		if(StrUtil.isNotBlank(minCreateTime)) {
			param.put("minCreateTime", minCreateTime);
		}
		if(StrUtil.isNotBlank(maxCreateTime)) {
			param.put("maxCreateTime", maxCreateTime);
		}
		Result rs = userService.queryPage(param, page, limit);
		//将业务结果进行输出
		RespWriter.writerJson(resp, rs);
	}
	
	
	protected void add(HttpServletRequest req, HttpServletResponse resp) {
		String userName = req.getParameter("userName");
		String realName = req.getParameter("realName");
		String type = req.getParameter("type");
		Result rs = userService.add(userName,realName,type);
		RespWriter.writerJson(resp, rs);
	}


	
	protected void delete(HttpServletRequest req, HttpServletResponse resp) {
		String[] ids = req.getParameterValues("id");
		Result rs = userService.delete(ids);
		UserCache.remove(ids);
		//将业务结果进行输出
		RespWriter.writerJson(resp, rs);
	}
	


	protected void login(HttpServletRequest req, HttpServletResponse resp) {
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String checkCode = req.getParameter("checkCode");
		//check authorization code
		Object codeData = req.getSession().getAttribute(Constant.KEY_CHECK_CODE);	
		if(codeData == null || !StrUtil.equals(checkCode, codeData.toString())) {
			Result rs = new Result(CodeMsg.USER_CHECK_CODE_ERROR);
			RespWriter.writerJson(resp, rs);
			return;
		}
		// check password and username
		Result rs = userService.login(userName, password);
		//use equals
		if(rs.getCode().equals(CodeMsg.SUCCESS.getCode()) ) {
			//put user into map cache
			UserCache.setUser((CUser) rs.getData());
			req.getSession().setAttribute(Constant.KEY_CURRENT_USER, rs.getData());
			rs.setData(null);
		}
		RespWriter.writerJson(resp, rs);
	}


	protected void loginOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//delete user from session
		HttpSession session = req.getSession();
		Object user = session.getAttribute(Constant.KEY_CURRENT_USER);
		if(user != null) {
			CUser u = (CUser) user;
			UserCache.remove(u.getId());
		}
		session.removeAttribute(Constant.KEY_CURRENT_USER);
		// delete session
		session.invalidate();
		// back to index page
		resp.sendRedirect("index.html");
	}
	
	
	protected void reset(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		Result rs = userService.resetPwd(id);
		RespWriter.writerJson(resp, rs);
	}


	
	protected void enable(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		Result rs = userService.enable(id);
		RespWriter.writerJson(resp, rs);
	}
	
	protected void salesman(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Result rs = userService.salesman();
		RespWriter.writerJson(resp, rs);
	}
	
}
