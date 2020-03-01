package com.zx.service;

import java.util.List;
import java.util.Map;

import com.zx.common.Result;

/**
 * @ClassName: ICCustomerService 
 * @Description: 客户业务类
 * @author: Mr.T
 * @date: 2020年2月25日 下午3:15:19
 */
public interface ICCustomerService {
	/**
	 * @Title: queryPage
	 * @author: Mr.T   
	 * @date: 2020年2月26日 上午9:54:19 
	 * @Description: 分页查询用户信息
	 * @param param
	 * @param page
	 * @param limit
	 * @return
	 * @return: Result
	 */
	Result queryPage(Map<String,Object> param,String page,String limit);
	/**
	 * @Title: addCustomer
	 * @author: Mr.T   
	 * @date: 2020年2月28日 下午4:53:34 
	 * @Description: 新增客户信息
	 * @param custName
	 * @param custCompany
	 * @param custPosition
	 * @param custPhone
	 * @param custBirth
	 * @param custSex
	 * @param userId
	 * @param custDesc
	 * @return
	 * @return: Result
	 */
	Result addCustomer(String custName, String custCompany, String custPosition, String custPhone, String custBirth,
			String custSex, String userId, String custDesc);
	/**
	 * @Title: updateCustomer
	 * @author: Mr.T   
	 * @date: 2020年2月28日 下午5:47:06 
	 * @Description: 根据ID 修改客户信息
	 * @param id
	 * @param custName
	 * @param custCompany
	 * @param custPosition
	 * @param custPhone
	 * @param custBirth
	 * @param custSex
	 * @param userId
	 * @param custDesc
	 * @return
	 * @return: Result
	 */
	Result updateCustomer(String id, String custName, String custCompany, String custPosition, String custPhone,
			String custBirth, String custSex, String userId, String custDesc);
	/**
	 * @Title: updateSales
	 * @author: Mr.T   
	 * @date: 2020年2月29日 上午10:02:35 
	 * @Description: 批量修改
	 * @param userId  业务员ID 
	 * @param custIds  客户ID
	 * @return
	 * @return: Result
	 */
	Result updateSales(Integer userId, List<Object> custIds);
	

}
