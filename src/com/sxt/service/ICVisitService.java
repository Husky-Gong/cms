package com.sxt.service;
/**
 * @ClassName: IVisitService 
 * @Description: 拜访记录业务类
 * @author: Mr.T
 * @date: 2020年2月29日 下午2:53:18
 */

import java.util.Map;

import com.sxt.common.Result;

public interface ICVisitService {
	
	/**
	 * @Title: addVisitLog
	 * @author: Mr.T   
	 * @date: 2020年2月29日 下午2:54:20 
	 * @Description: 新增拜访记录
	 * @param custId
	 * @param userId
	 * @param visitTime
	 * @return
	 * @return: Result
	 */
	public Result addVisitLog(String custId,Integer userId,String visitTime);
	
	
	/**
	 * @Title: salesVisitLog
	 * @author: Mr.T   
	 * @date: 2020年2月29日 下午3:17:52 
	 * @Description: 统计每个业务员的总拜访量
	 * @return
	 * @return: Result
	 */
	public Result salesVisitLog(Map<String,Object> param);

}
