package com.sxt.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sxt.common.Constant;
import com.sxt.common.Result;
import com.sxt.pojo.CUser;
import com.sxt.service.ICCustomerService;
import com.sxt.service.ICVisitService;
import com.sxt.service.impl.CCustomerServiceImpl;
import com.sxt.service.impl.CVisitServiceImpl;
import com.sxt.util.RespWriter;

import cn.hutool.core.util.StrUtil;

@WebServlet("/customer.do")
public class CustomerController  extends HttpServlet{

	private static final long serialVersionUID = -3296890050381719116L;

	ICCustomerService customerService = new CCustomerServiceImpl();
	
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
	
	
	protected void list(HttpServletRequest req, HttpServletResponse resp) {
		String custName = req.getParameter("custName");
		String custCompany = req.getParameter("custCompany");
		String custPhone = req.getParameter("custPhone");
		String minCustBirth = req.getParameter("minCustBirth");
		String maxCustBirth = req.getParameter("maxCustBirth");
		String custSex = req.getParameter("custSex");
		String userId = req.getParameter("userId");
		
		//Page parameters
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		
		Map<String,Object> param = new HashMap<>();
		if(StrUtil.isNotBlank(custName)) {
			param.put("custName", custName);
		}
		if(StrUtil.isNotBlank(custCompany)) {
			param.put("custCompany", custCompany);
		}
		if(StrUtil.isNotBlank(custPhone)) {
			param.put("custPhone", custPhone);
		}
		if(StrUtil.isNotBlank(minCustBirth)) {
			param.put("minCustBirth", minCustBirth);
		}
		if(StrUtil.isNotBlank(maxCustBirth)) {
			param.put("maxCustBirth", maxCustBirth);
		}
		if(StrUtil.isNotBlank(custSex)) {
			param.put("custSex", custSex);
		}
		if(StrUtil.isNotBlank(userId)) {
			param.put("userId", userId);
		}
		Result rs = customerService.queryPage(param, page, limit);
		//将业务结果进行输出
		RespWriter.writerJson(resp, rs);
	}
	


	protected void add(HttpServletRequest req, HttpServletResponse resp) {
		String custName = req.getParameter("custName");
		String custCompany = req.getParameter("custCompany");
		String custPosition = req.getParameter("custPosition");
		String custPhone = req.getParameter("custPhone");
		String custBirth = req.getParameter("custBirth");
		String custSex = req.getParameter("custSex");
		String userId = req.getParameter("userId");
		String custDesc = req.getParameter("custDesc");
		
		Result rs = customerService.addCustomer(custName,custCompany,custPosition,custPhone,custBirth,custSex,userId,custDesc);
		//deliver result to page
		RespWriter.writerJson(resp, rs);
	}


	protected void update(HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("id");
		String custName = req.getParameter("custName");
		String custCompany = req.getParameter("custCompany");
		String custPosition = req.getParameter("custPosition");
		String custPhone = req.getParameter("custPhone");
		String custBirth = req.getParameter("custBirth");
		String custSex = req.getParameter("custSex");
		String userId = req.getParameter("userId");
		String custDesc = req.getParameter("custDesc");
		
		Result rs = customerService.updateCustomer(id,custName,custCompany,custPosition,custPhone,custBirth,custSex,userId,custDesc);
		RespWriter.writerJson(resp, rs);
	}
	
	
	protected void updateSales(HttpServletRequest req, HttpServletResponse resp) {
		String param = req.getParameter("param");//param format is json
		//transfer json format into Object
		JSONObject jsonObj = JSONObject.parseObject(param);
		//get user id
		Integer userId = jsonObj.getInteger("userId");
		//get custIds list
		JSONArray custIds = jsonObj.getJSONArray("custIds");
		
		Result rs = customerService.updateSales(userId,custIds);
		// return result
		RespWriter.writerJson(resp, rs);
	}
	
	
	protected void addVisitLog(HttpServletRequest req, HttpServletResponse resp) {
		String custId = req.getParameter("id");
		String visitTime = req.getParameter("visitTime");
		//get operator id
		Object obj = req.getSession().getAttribute(Constant.KEY_CURRENT_USER);
		Integer userId = ((CUser)obj).getId();
		Result rs = visitService.addVisitLog(custId, userId, visitTime);
		// return result
		RespWriter.writerJson(resp, rs);
	}
	
	
	
}
