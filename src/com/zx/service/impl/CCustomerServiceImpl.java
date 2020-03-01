package com.zx.service.impl;

import java.util.List;
import java.util.Map;

import com.zx.common.CodeMsg;
import com.zx.common.PageInfo;
import com.zx.common.Result;
import com.zx.dao.ICCustomerDao;
import com.zx.dao.impl.CCustomerDaoImpl;
import com.zx.pojo.CCustomer;
import com.zx.service.ICCustomerService;

public class CCustomerServiceImpl implements ICCustomerService {
	ICCustomerDao customerDao = new CCustomerDaoImpl();
	
	@Override
	public Result queryPage(Map<String, Object> param, String page, String limit) {
		PageInfo<CCustomer> pageInfo = customerDao.selectPage(param, Integer.parseInt(page), Integer.parseInt(limit));
		return new Result(pageInfo);
	}

	@Override
	public Result addCustomer(String custName, String custCompany, String custPosition, String custPhone,
			String custBirth, String custSex, String userId, String custDesc) {
		//首先进行业务数据校验 ,例如校验手机号
		CCustomer customer = customerDao.selectCCustomers(custPhone);
		if(customer != null) {
			return new Result(CodeMsg.CUSTOMER_PHONE_EXIST_ERROR);
		}
		customerDao.insert(custName,custCompany,custPosition,custPhone,custBirth,custSex,userId,custDesc);
		return new Result();
	}

	@Override
	public Result updateCustomer(String id, String custName, String custCompany, String custPosition, String custPhone,
			String custBirth, String custSex, String userId, String custDesc) {
		CCustomer customer = customerDao.selectCCustomers(custPhone);
		if(customer != null && !customer.getId().toString().equals(id)) {
			return new Result(CodeMsg.CUSTOMER_PHONE_EXIST_ERROR);
		}
		customerDao.update(id,custName,custCompany,custPosition,custPhone,custBirth,custSex,userId,custDesc);
		return new Result();
	}

	@Override
	public Result updateSales(Integer userId, List<Object> custIds) {
		Integer[] custId = new Integer[custIds.size()];
		custId = custIds.toArray(custId);
		customerDao.updateSales(userId,custId);
		return new Result();
	}

}
