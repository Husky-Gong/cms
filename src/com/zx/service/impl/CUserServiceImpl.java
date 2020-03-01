package com.zx.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zx.common.CodeMsg;
import com.zx.common.Constant;
import com.zx.common.PageInfo;
import com.zx.common.Result;
import com.zx.dao.ICCustomerDao;
import com.zx.dao.ICUserDao;
import com.zx.dao.impl.CCustomerDaoImpl;
import com.zx.dao.impl.CUserDaoImpl;
import com.zx.pojo.CCustomer;
import com.zx.pojo.CUser;
import com.zx.service.ICUserService;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;

public class CUserServiceImpl implements ICUserService {
	
	private ICUserDao cuserDao = new CUserDaoImpl();
	
	private ICCustomerDao customerDao = new CCustomerDaoImpl();

	@Override
	public Result login(String userName, String password) {
		//根据用户名和密码查询用户
		CUser user = cuserDao.selectUser(userName, password);
		// 如果根据账号 密码 没有查到用户  说明  用户名 和密码 错误
		if(user == null) {
			return new Result(CodeMsg.USER_ACCOUNT_ERROR);
		}
		//判断用户状态 如果是无效的数据  则提示用户账号已经失效
		if(user.getIsDel() == Constant.USER_STATE_INVALID) {
			return new Result(CodeMsg.USER_INVALID_ERROR);
		}
		return new Result(user);
	}

	@Override
	public Result queryPage(Map<String, Object> param, String page, String limit) {
		PageInfo<CUser> pageInfo = cuserDao.selectPage(param, Integer.parseInt(page), Integer.parseInt(limit));
		return new Result(pageInfo);
	}

	@Override
	public Result add(String userName, String realName, String type) {
		//业务数据校验
		//校验用户名 不能重复
		CUser user = cuserDao.selectUser(userName);
		if(user != null) {
			return new Result(CodeMsg.USER_USERNAME_EXIST_ERROR);
		}
		String password = SecureUtil.md5(Constant.DEFAULT_PASSWORD);
		Integer isDel = Constant.USER_STATE_VALID;
		String createTime = DateUtil.format(new Date(), Constant.YYYY_MM_DD_HH_MM_SS);
		String modifyTime = createTime;
		cuserDao.insert(userName,password,realName,type,isDel,createTime,modifyTime);
		return new Result();
	}

	@Override
	public Result delete(String... ids) {
		//判断业务员是否关联客户,若关联客户则不让其修改
		//根据业务员查询客户
		List<CCustomer> customers = customerDao.selectCustomers(ids);
		if(customers != null && !customers.isEmpty()) {
			return new Result(CodeMsg.USER_DELETE_ERROR);
		}
		
		cuserDao.updateState(Constant.USER_STATE_INVALID,ids);
		return new Result();
	}

	@Override
	public Result resetPwd(String id) {
		String password = SecureUtil.md5(Constant.DEFAULT_PASSWORD);
		cuserDao.updatePwd(id,password);
		return new Result();
	}

	@Override
	public Result enable(String id) {
		cuserDao.updateState(Constant.USER_STATE_VALID, id);
		return new Result();
	}

	@Override
	public Result updatePwd(CUser user, String password, String newPassword) {
		Integer userId = user.getId();
		user = cuserDao.selectUser(userId);
		//比较原始密码
		if(!StrUtil.equals(user.getPassword(),SecureUtil.md5(password))) {
			return new Result(CodeMsg.SYS_USER_PASSWORD_ERROR);
		}
		//修改新密码
		newPassword = SecureUtil.md5(newPassword); 
		cuserDao.updatePwd(userId.toString(), newPassword);
		return new Result();
	}

	@Override
	public Result updateImg(Integer userId, String imgUrl) {
		cuserDao.updateImg(userId, imgUrl);
		return new Result();
	}

	@Override
	public Result salesman() {
		List<CUser> users = cuserDao.selectUsers(Constant.USER_TYPE_SALES);
		return new Result(users);
	}

}
