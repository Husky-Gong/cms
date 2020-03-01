package com.sxt.service;

import java.util.Map;

import com.sxt.common.Result;
import com.sxt.pojo.CUser;

/**
 * @ClassName: ICUserService 
 * @Description: 用户业务类
 * @author: Mr.T
 * @date: 2020年2月25日 下午3:15:19
 */
public interface ICUserService {
	/**
	 * @Title: login
	 * @author: Mr.T   
	 * @date: 2020年2月25日 下午3:16:00 
	 * @Description: 用户登录方法
	 * @param userName
	 * @param password
	 * @return
	 * @return: Result
	 */
	Result login(String userName,String password);
	
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
	 * @Title: add
	 * @author: Mr.T   
	 * @date: 2020年2月26日 上午11:03:05 
	 * @Description: 新增用户
	 * @param userName
	 * @param realName
	 * @param type
	 * @return
	 * @return: Result
	 */
	Result add(String userName, String realName, String type);
	/**
	 * @Title: delete
	 * @author: Mr.T   
	 * @date: 2020年2月26日 上午11:52:18 
	 * @Description: 删除用户  将用户状态设置为无效
	 * @param ids
	 * @return
	 * @return: Result
	 */
	Result delete(String... ids);
	/**
	 * @Title: resetPwd
	 * @author: Mr.T   
	 * @date: 2020年2月26日 下午2:21:12 
	 * @Description: 根据用户ID 重置密码
	 * @param id
	 * @return
	 * @return: Result
	 */
	Result resetPwd(String id);
	/**
	 * @Title: enable
	 * @author: Mr.T   
	 * @date: 2020年2月26日 下午2:24:25 
	 * @Description: 根据ID 启用数据
	 * @param id
	 * @return
	 * @return: Result
	 */
	Result enable(String id);
	/**
	 * @Title: updatePwd
	 * @author: Mr.T   
	 * @date: 2020年2月26日 下午3:39:04 
	 * @Description: 修改密码
	 * @param user
	 * @param password
	 * @param newPassword
	 * @return
	 * @return: Result
	 */
	Result updatePwd(CUser user, String password, String newPassword);
	/**
	 * @Title: updateImg
	 * @author: Mr.T   
	 * @date: 2020年2月28日 下午12:14:44 
	 * @Description: 修改用户头像
	 * @param userId
	 * @param imgUrl
	 * @return
	 * @return: Result
	 */
	Result updateImg(Integer userId, String imgUrl);
	/**
	 * @Title: salesman
	 * @author: Mr.T   
	 * @date: 2020年2月28日 下午3:56:50 
	 * @Description: 获取所有有效业务员
	 * @return
	 * @return: Result
	 */
	Result salesman();
	

}
