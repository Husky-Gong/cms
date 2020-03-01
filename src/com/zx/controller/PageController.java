package com.zx.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * For pages all in WEB-INF folder, servlet cannot jump there directly, so use this servlet as a connection to those pages
 */
@WebServlet("/page.do")
public class PageController extends HttpServlet{

	private static final long serialVersionUID = -337695025543519823L;

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
	 * to main
	 */
	protected void main(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
		req.getRequestDispatcher("WEB-INF/page/main.jsp").forward(req, resp);
	}
	
	/**
	 * To user list
	 */
	protected void userList(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
		req.getRequestDispatcher("WEB-INF/page/user/list.jsp").forward(req, resp);
	}
	/**
	 * To customer list
	 */
	protected void customerList(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
		req.getRequestDispatcher("WEB-INF/page/customer/list.jsp").forward(req, resp);
	}
	/**
	 * To password jsp page
	 */
	protected void updatePwd(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
		req.getRequestDispatcher("WEB-INF/page/sys/pwd.jsp").forward(req, resp);
	}
	/**
	 * To update profile page
	 */
	protected void updateImg(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
		req.getRequestDispatcher("WEB-INF/page/sys/userImg.jsp").forward(req, resp);
	}
	
	
	protected void bar(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
		req.getRequestDispatcher("WEB-INF/page/visit/bar.jsp").forward(req, resp);
	}
}
