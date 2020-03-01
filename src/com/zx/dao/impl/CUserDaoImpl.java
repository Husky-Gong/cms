package com.zx.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zx.common.Constant;
import com.zx.common.PageInfo;
import com.zx.dao.BaseDao;
import com.zx.dao.ICUserDao;
import com.zx.pojo.CUser;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;

public class CUserDaoImpl extends BaseDao implements ICUserDao {

	@Override
	public PageInfo<CUser> selectPage(Map<String, Object> param, Integer page, Integer limit) {
		StringBuffer sb = new StringBuffer("select id as id,user_name as userName,real_name as realName,type as type,is_del as isDel,create_time as createTime,modify_time as modifyTime from c_user where 1=1 ");
		if(param.containsKey("userName")) {
			sb.append(" and user_name ='").append(param.get("userName").toString()).append("' ");
		}
		if(param.containsKey("realName")) {
			sb.append(" and real_name like '%").append(param.get("realName").toString()).append("%' ");
		}
		if(param.containsKey("type")) {
			sb.append(" and type =").append(param.get("type").toString());
		}
		if(param.containsKey("minCreateTime")) {
			sb.append(" and create_time >='").append(param.get("minCreateTime").toString()+" 00:00:00").append("'");
		}
		if(param.containsKey("maxCreateTime")) {
			sb.append(" and create_time <='").append(param.get("maxCreateTime").toString()+" 23:59:59").append("' ");
		} 
		if(param.containsKey("isDel")) {
			sb.append(" and is_del =").append(param.get("isDel").toString());
		}
		sb.append(" order by id asc");
		
		PageInfo<CUser> pageInfo = super.selectPage(sb.toString(), CUser.class, page, limit);
		
		return pageInfo;
	}
	/**
	 * 	注意 密码是加密存储的
	 * 		由于数据库实际存储的密码 不是用户输入的原始字符串  所以直接使用 用户名和密码去数据查询是不行的
	 * 		需要将 用户输入的密码进行加密  然后再都当做参数进行查询
	 */
	@Override
	public CUser selectUser(String userName, String password) {
		String sql = "select id as id, user_name as userName,password as password,real_name as realName,img as img,type as type from c_user where user_name = ? and password = ?";
		//将password 进行加密   进行MD5加密
		password = SecureUtil.md5(password);
		CUser user = super.selectOne(sql, CUser.class, userName,password);
		return user;
	}
	
	
	@Override
	public void insert(String userName, String password, String realName, String type, Integer isDel, String createTime,
			String modifyTime) {
		String sql = "INSERT INTO `c_user`(`id`, `user_name`, `password`, `real_name`, `type`, `is_del`, `create_time`, `modify_time`) VALUES (0, ?,?, ?, ?, ?,?,?)";
		super.update(sql, userName,password,realName,type,isDel,createTime,modifyTime);
	}
	@Override
	public CUser selectUser(String userName) {
		String sql = "select user_name as userName,password as password,real_name as realName,img as img,type as type from c_user where user_name = ?";
		return super.selectOne(sql, CUser.class, userName);
	}
	@Override
	public void updateState(Integer state, String... ids) {
		StringBuffer sb = new StringBuffer("update c_user set is_del = ?,modify_time=? where id in ");
		sb.append("(");
		String prefix= "";
		for (String id : ids) {
			sb.append(prefix);
			sb.append(id);
			prefix = ",";
		}
		sb.append(")");
		String sql = sb.toString();
		String modifyTime = DateUtil.format(new Date(), Constant.YYYY_MM_DD_HH_MM_SS);
		super.update(sql,state,modifyTime);
	}
	@Override
	public void updatePwd(String id, String password) {
		String modifyTime = DateUtil.format(new Date(), Constant.YYYY_MM_DD_HH_MM_SS);
		String sql = "update c_user set password = ?,modify_time=? where id = ?";
		super.update(sql, password,modifyTime,id);
		
	}
	@Override
	public CUser selectUser(Integer userId) {
		String sql = "select user_name as userName,password as password,real_name as realName,img as img,type as type from c_user where id = ?";
		return super.selectOne(sql, CUser.class, userId);
	}
	@Override
	public void updateImg(Integer userId, String imgUrl) {
		String modifyTime = DateUtil.format(new Date(), Constant.YYYY_MM_DD_HH_MM_SS);
		String sql = "update c_user set img = ?,modify_time=? where id = ?";
		super.update(sql, imgUrl,modifyTime,userId);
	}
	@Override
	public List<CUser> selectUsers(Integer type) {
		String sql = "select id as id,real_name as realName from c_user where type = ? and is_del = 1";
		return super.selectList(sql, CUser.class, type);
	}

}
