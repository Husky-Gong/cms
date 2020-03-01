package com.sxt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sxt.common.CodeMsg;
import com.sxt.common.Constant;
import com.sxt.common.Result;
import com.sxt.common.UserCache;
import com.sxt.pojo.CUser;
import com.sxt.util.RespWriter;

import cn.hutool.core.util.StrUtil;



@WebFilter(urlPatterns = {"*.do","*.jsp"})
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse resp = (HttpServletResponse) arg1;
		//get session
		HttpSession session = req.getSession();
		//check whether user has logged in 
		Object user = session.getAttribute(Constant.KEY_CURRENT_USER);
		// if having logged in, then let it pass
		if(user != null ) {
			//从自定义缓存中 获取当前用户  若都存在 则说明 用户状态正常
			CUser u = (CUser) user;
			Integer id = u.getId();
			CUser user2 = UserCache.getUser(id);
			if(user2 != null) {
				chain.doFilter(req, resp);
				return;
			}
		}
		//get request url
		String uri = req.getRequestURI();   
		String service = req.getParameter("service");
		//checkcode page
		if(uri.endsWith("checkCode.do")) {
			chain.doFilter(req, resp);
			return;
		}
		//登录请求  
		if(uri.endsWith("user.do") && StrUtil.equals(service, "login") ) {
			chain.doFilter(req, resp);
			return;
		}
		//而ajax 请求的方式 需要单独处理   ajax的请求方式  需要返回json 数据 
		//该数据表示 用户登录超时   判断是ajax 请求 还是 浏览器 请求
		//请求 此时 存在2种模式:   
		//		浏览器的请求       浏览器发送HTTP请求:  redirect.html  请求 redirect.html内容  然后渲染
		//      js 利用浏览器发送请求  --  ajax请求 : 请求到  redirect.html 的内容  解析 没有拿到解析数据
		//		ajax 无法拿到数据进行解析   所以  基于这样的情况  要单独为ajax请求 返回 json格式数据 让其解析
		//判断用户是否是 ajax 请求
		
		String ajaxFlag = req.getHeader("X-Requested-With");
		//判断是否是ajax 若是ajax 则返回 一个需要登录的提示信息
		if(StrUtil.equals(ajaxFlag, "XMLHttpRequest")) {
			Result rs = new Result(CodeMsg.USER_LOGIN_TIMEOUT_ERROR);
			RespWriter.writerJson(resp,rs);
			return;
		}
		//其他情况  返回登录页面  这种方式 只适合 使用浏览器 请求的方式
		resp.sendRedirect("redirect.html");
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	


}
