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
		try {
			Method method  = this.getClass().getDeclaredMethod(service, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this, req,resp);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	/**
	 * @Title: list
	 * @author: Mr.T   
	 * @date: 2020年2月26日 上午9:51:04 
	 * @Description: 用户列表
	 * @param req
	 * @param resp
	 * @return: void
	 */
	protected void list(HttpServletRequest req, HttpServletResponse resp) {
		String userName = req.getParameter("userName");
		String realName = req.getParameter("realName");
		String type = req.getParameter("type");
		String isDel = req.getParameter("isDel");
		String minCreateTime = req.getParameter("minCreateTime");
		String maxCreateTime = req.getParameter("maxCreateTime");
		//分页参数
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
	/**
	 * @Title: add
	 * @author: Mr.T   
	 * @date: 2020年2月26日 上午11:02:08 
	 * @Description: 新增用户信息
	 * @param req
	 * @param resp
	 * @return: void
	 */
	protected void add(HttpServletRequest req, HttpServletResponse resp) {
		String userName = req.getParameter("userName");
		String realName = req.getParameter("realName");
		String type = req.getParameter("type");
		Result rs = userService.add(userName,realName,type);
		//将业务结果进行输出
		RespWriter.writerJson(resp, rs);
	}
	/**
	 * @Title: delete
	 * @author: Mr.T   
	 * @date: 2020年2月26日 上午11:51:40 
	 * @Description: 删除用户  将用户设置为无效
	 * @param req
	 * @param resp
	 * @return: void
	 */
	protected void delete(HttpServletRequest req, HttpServletResponse resp) {
		String[] ids = req.getParameterValues("id");
		Result rs = userService.delete(ids);
		UserCache.remove(ids);
		//将业务结果进行输出
		RespWriter.writerJson(resp, rs);
	}
	
	/**
	 * @Title: login
	 * @author: Mr.T   
	 * @date: 2020年2月25日 下午3:38:11 
	 * @Description: 处理用户登录的方法
	 * @param req
	 * @param resp
	 * @return: void
	 */
	protected void login(HttpServletRequest req, HttpServletResponse resp) {
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String checkCode = req.getParameter("checkCode");
		//先校验验证码
		Object codeData = req.getSession().getAttribute(Constant.KEY_CHECK_CODE);	
		//session 存储的验证码为空  或者 与用户输入的不匹配 此时都是验证码不正确
		if(codeData == null || !StrUtil.equals(checkCode, codeData.toString())) {
			Result rs = new Result(CodeMsg.USER_CHECK_CODE_ERROR);
			RespWriter.writerJson(resp, rs);
			return;
		}
		//校验账号密码
		Result rs = userService.login(userName, password);
		//由于 code值 大于 127  所以使用  == 进行判断容易出现BUG 
		if(rs.getCode().equals(CodeMsg.SUCCESS.getCode()) ) {
			//将用户放入自定义缓存中
			UserCache.setUser((CUser) rs.getData());
			//如果code值为  200 说明 登录是成功的  所以要将 当前用户放入session
			req.getSession().setAttribute(Constant.KEY_CURRENT_USER, rs.getData());
			rs.setData(null);
		}
		//将业务结果进行输出
		RespWriter.writerJson(resp, rs);
	}
	/**
	 * @Title: loginOut
	 * @author: Mr.T   
	 * @date: 2020年2月25日 下午4:30:03 
	 * @Description: 退出登录
	 * @param req
	 * @param resp
	 * @return: void
	 * @throws IOException 
	 */
	protected void loginOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//1. 将session中当前用户删除
		HttpSession session = req.getSession();
		Object user = session.getAttribute(Constant.KEY_CURRENT_USER);
		if(user != null) {
			CUser u = (CUser) user;
			UserCache.remove(u.getId());
		}
		session.removeAttribute(Constant.KEY_CURRENT_USER);
		//删除session
		session.invalidate();
		//去登录页面
		resp.sendRedirect("index.html");
	}
	
	/**
	 * @Title: reset
	 * @author: Mr.T   
	 * @date: 2020年2月26日 下午2:20:15 
	 * @Description: 重置密码
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @return: void
	 */
	protected void reset(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		Result rs = userService.resetPwd(id);
		//将数据输出
		RespWriter.writerJson(resp, rs);
	}
	/**
	 * @Title: enable
	 * @author: Mr.T   
	 * @date: 2020年2月26日 下午2:26:33 
	 * @Description: 修改数据状态  数据生效
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @return: void
	 */
	protected void enable(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		Result rs = userService.enable(id);
		//将数据输出
		RespWriter.writerJson(resp, rs);
	}
	
	protected void salesman(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Result rs = userService.salesman();
		//将数据输出
		RespWriter.writerJson(resp, rs);
	}
	
}
