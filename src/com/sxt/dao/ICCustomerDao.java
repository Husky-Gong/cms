package com.sxt.dao;

import java.util.List;
import java.util.Map;

import com.sxt.common.PageInfo;
import com.sxt.pojo.CCustomer;

/**
 * @ClassName: ICCustomerDao 
 * @Description: 客户数据操作接口
 * @author: Mr.T
 * @date: 2020年2月28日 下午2:47:30
 */
public interface ICCustomerDao {
	/**
	 * @Title: selectPage
	 * @author: Mr.T   
	 * @date: 2020年2月28日 下午2:48:13 
	 * @Description: 查询客户列表
	 * @param param
	 * @param page
	 * @param limit
	 * @return
	 * @return: PageInfo<CCustomer>
	 */
	public PageInfo<CCustomer> selectPage(Map<String,Object> param,Integer page,Integer limit);

	/**
	 * @Title: selectCCustomers
	 * @author: Mr.T   
	 * @date: 2020年2月28日 下午4:55:00 
	 * @Description: 根据手机号查询业务
	 * @param custPhone
	 * @return
	 * @return: CCustomer
	 */
	public CCustomer selectCCustomers(String custPhone);
	/**
	 * @Title: insert
	 * @author: Mr.T   
	 * @date: 2020年2月28日 下午4:58:47 
	 * @Description: 新增客户
	 * @param custName
	 * @param custCompany
	 * @param custPosition
	 * @param custPhone
	 * @param custBirth
	 * @param custSex
	 * @param userId
	 * @param custDesc
	 * @return: void
	 */
	public void insert(String custName, String custCompany, String custPosition, String custPhone, String custBirth,
			String custSex, String userId, String custDesc);
	/**
	 * @Title: update
	 * @author: Mr.T   
	 * @date: 2020年2月28日 下午5:48:43 
	 * @Description: 根据ID 更新客户信息
	 * @param id
	 * @param custName
	 * @param custCompany
	 * @param custPosition
	 * @param custPhone
	 * @param custBirth
	 * @param custSex
	 * @param userId
	 * @param custDesc
	 * @return: void
	 */
	public void update(String id, String custName, String custCompany, String custPosition, String custPhone,
			String custBirth, String custSex, String userId, String custDesc);
	/**
	 * @Title: updateSales
	 * @author: Mr.T   
	 * @date: 2020年2月29日 上午10:06:18 
	 * @Description: 批量修改业务员
	 * @param userId
	 * @param custId
	 * @return: void
	 */
	public void updateSales(Integer userId, Integer... custId);
	
	/**
	 * @Title: selectCustomers
	 * @author: Mr.T   
	 * @date: 2020年2月29日 上午10:25:38 
	 * @Description: 根据业务员ID  查询客户信息
	 * @param userId
	 * @return
	 * @return: List<CCustomer>
	 */
	public List<CCustomer> selectCustomers(String... userId);
}
