package com.zx.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zx.common.Constant;
import com.zx.common.PageInfo;
import com.zx.dao.BaseDao;
import com.zx.dao.ICCustomerDao;
import com.zx.pojo.CCustomer;

import cn.hutool.core.date.DateUtil;

public class CCustomerDaoImpl extends BaseDao implements ICCustomerDao {

	@Override
	public PageInfo<CCustomer> selectPage(Map<String, Object> param, Integer page, Integer limit) {
		
		StringBuffer sb = new StringBuffer("select cu.id as id,cu.cust_name as custName,cu.cust_company as custCompany,cu.cust_position as custPosition,cu.cust_phone as custPhone,cu.cust_birth as custBirth,cu.cust_sex as custSex,cu.user_id as userId,cu.create_time as createTime,cu.modify_time as  modifyTime  ,us.real_name as realName,cu.cust_desc as custDesc from c_customer cu left join c_user  us on cu.user_id = us.id where  1=1 ");
		if(param.containsKey("custName")) {
			sb.append(" and cu.cust_name like '%").append(param.get("custName").toString()).append("%' ");
		}
		if(param.containsKey("custCompany")) {
			sb.append(" and cu.cust_company like '%").append(param.get("custCompany").toString()).append("%' ");
		}
		if(param.containsKey("custPhone")) {
			sb.append(" and cu.cust_phone =").append(param.get("custPhone").toString());
		}
		if(param.containsKey("minCustBirth")) {
			sb.append(" and cu.cust_birth >='").append(param.get("minCustBirth").toString()).append("'");
		}
		if(param.containsKey("maxCustBirth")) {
			sb.append(" and cu.cust_birth <='").append(param.get("maxCustBirth").toString()).append("' ");
		} 
		if(param.containsKey("custSex")) {
			sb.append(" and cu.cust_sex =").append(param.get("custSex").toString());
		}
		if(param.containsKey("userId")) {
			sb.append(" and cu.user_Id =").append(param.get("userId").toString());
		}
		sb.append(" order by id asc");
		
		PageInfo<CCustomer> pageInfo = super.selectPage(sb.toString(), CCustomer.class, page, limit);
		
		return pageInfo;
	}

	@Override
	public CCustomer selectCCustomers(String custPhone) {
		String sql = " select id from c_customer where cust_phone = ?";
		return super.selectOne(sql, CCustomer.class, custPhone);
	}

	@Override
	public void insert(String custName, String custCompany, String custPosition, String custPhone, String custBirth,
			String custSex, String userId, String custDesc) {
		String createTime = DateUtil.format(new Date(), Constant.YYYY_MM_DD_HH_MM_SS);
		String modifyTime = createTime;
		String sql = "insert into c_customer value (0,?,?,?,?,?,?,?,?,?,?)";
		super.update(sql,custName,custCompany,custPosition,custPhone,custBirth,custSex,userId,custDesc,createTime,modifyTime);
	}

	@Override
	public void update(String id, String custName, String custCompany, String custPosition, String custPhone,
			String custBirth, String custSex, String userId, String custDesc) {
		String modifyTime = DateUtil.format(new Date(), Constant.YYYY_MM_DD_HH_MM_SS);
		String sql = "UPDATE `c_customer` SET `cust_name` = ?, `cust_company` = ?, `cust_position` = ?, `cust_phone` = ?, `cust_birth` = ?, `cust_sex` = ?, `user_id` = ?, `cust_desc` = ?, `modify_time` = ? WHERE `id` = ?";
		super.update(sql,custName,custCompany,custPosition,custPhone,custBirth,custSex,userId,custDesc,modifyTime,id);
	}

	@Override
	public void updateSales(Integer userId, Integer... custId) {
		StringBuffer sb = new StringBuffer("update c_customer set user_id = ? where id in ");
		sb.append("(");
		String prefix= "";
		for (Integer id : custId) {
			sb.append(prefix);
			sb.append(id);
			prefix = ",";
		}
		sb.append(")");
		
		super.update(sb.toString(), userId);
	}

	@Override
	public List<CCustomer> selectCustomers(String... userId) {
		StringBuffer sb = new StringBuffer(" select id from c_customer where user_id in");
		sb.append("(");
		String prefix= "";
		for (String id : userId) {
			sb.append(prefix);
			sb.append(id);
			prefix = ",";
		}
		sb.append(")");
		return super.selectList(sb.toString(), CCustomer.class);
	}

}
