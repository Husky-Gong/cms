package com.sxt.dao;


import java.util.List;
import java.util.Map;

import com.sxt.common.PageInfo;
import com.sxt.pojo.CUser;
/**
 * @ClassName: ICUserDao 
 * @Description: 用户表数据操作接口
 * @author: Mr.T
 * @date: 2020年2月25日 下午2:49:41
 */
public interface ICUserDao  {
	
	
	/**
	 * @Title: selectPage
	 * @author: Mr.T   
	 * @date: 2020年2月25日 下午2:51:48 
	 * @Description: 分页查询用户信息
	 * @param param
	 * @param page
	 * @param limit
	 * @return
	 * @return: PageInfo<CUser>
	 */
	public PageInfo<CUser> selectPage(Map<String,Object> param,Integer page,Integer limit);

	/**
	 * @Title: selectUser
	 * @author: Mr.T   
	 * @date: 2020年2月25日 下午2:52:30 
	 * @Description: 根据用户名和密码查询用户信息
	 * @param userName
	 * @param password
	 * @return
	 * @return: CUser
	 */
	public CUser selectUser(String userName,String password);
	/**
	 * @Title: insert
	 * @author: Mr.T   
	 * @date: 2020年2月26日 上午11:08:18 
	 * @Description: 新增用户
	 * @param userName
	 * @param password
	 * @param realName
	 * @param type
	 * @param isDel
	 * @param createTime
	 * @param modifyTime
	 * @return: void
	 */
	public void insert(String userName, String password, String realName, String type, Integer isDel, String createTime,
			String modifyTime);
	
	/**
	 * @Title: selectUser
	 * @author: Mr.T   
	 * @date: 2020年2月26日 上午11:12:03 
	 * @Description: 根据用户名查询用户
	 * @param userName
	 * @return
	 * @return: CUser
	 */
	public CUser selectUser(String userName);
	/**
	 * @Title: updateState
	 * @author: Mr.T   
	 * @date: 2020年2月26日 上午11:53:23 
	 * @Description: 根据ID 修改用户状态
	 * @param state  用户状态  1 有效  2  无效
	 * @param ids  id
	 * @return: void
	 */
	public void updateState(Integer state, String... ids);
	/**
	 * @Title: updatePwd
	 * @author: Mr.T   
	 * @date: 2020年2月26日 下午2:22:17 
	 * @Description: 根据ID 修改密码
	 * @param id
	 * @param password
	 * @return: void
	 */
	public void updatePwd(String id, String password);
	
	/**
	 * @Title: selectUser
	 * @author: Mr.T   
	 * @date: 2020年2月26日 下午3:40:18 
	 * @Description: 根据ID 查询用户
	 * @param userId
	 * @return
	 * @return: CUser
	 */
	public CUser selectUser(Integer userId);
	/**
	 * @Title: updateImg
	 * @author: Mr.T   
	 * @date: 2020年2月28日 下午12:15:30 
	 * @Description: 修改用户头像
	 * @param userId
	 * @param imgUrl
	 * @return: void
	 */
	public void updateImg(Integer userId, String imgUrl);
	
	/**
	 * @Title: selectUsers
	 * @author: Mr.T   
	 * @date: 2020年2月28日 下午3:39:10 
	 * @Description: 根据用户类型查询有效用户
	 * @param type
	 * @return
	 * @return: List<CUser>
	 */
	public List<CUser> selectUsers(Integer type);

	
}
