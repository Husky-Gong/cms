package com.zx.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.common.Result;
import com.zx.service.ICVisitService;
import com.zx.service.impl.CVisitServiceImpl;
import com.zx.util.RespWriter;

import cn.hutool.core.util.StrUtil;

@WebServlet("/visit.do")
public class CVisitController extends HttpServlet {
	
	private static final long serialVersionUID = -3467524572614917079L;

	
	ICVisitService visitService = new CVisitServiceImpl();
	
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
	

	protected void bar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String minVisitTime = req.getParameter("minVisitTime");
		
		String maxVisitTime = req.getParameter("maxVisitTime");
		
		Map<String,Object> param = new HashMap<>();
		
		if(StrUtil.isNotBlank(minVisitTime)) {
			param.put("minVisitTime", minVisitTime);
		}
		if(StrUtil.isNotBlank(maxVisitTime)) {
			param.put("maxVisitTime", maxVisitTime);
		}
		Result rs = visitService.salesVisitLog(param);
		
		RespWriter.writerJson(resp, rs);
		
	}
}
