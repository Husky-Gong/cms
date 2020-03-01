package com.sxt.dao;

import java.util.List;
import java.util.Map;

import com.sxt.pojo.CVisit;

/**
 * @ClassName: ICVisitDao 
 * @Description: 拜访记录操作接口
 * @author: Mr.T
 * @date: 2020年2月29日 下午2:31:27
 */
public interface ICVisitDao {
	
	/**
	 * @Title: insert
	 * @author: Mr.T   
	 * @date: 2020年2月29日 下午2:30:36 
	 * @Description: 新增拜访记录
	 * @param userId
	 * @param custId
	 * @param visitTime
	 * @return: void
	 */
	public void insert(Integer userId,Integer custId,String visitTime);
	/**
	 * @Title: selectVisitData
	 * @author: Mr.T   
	 * @date: 2020年2月29日 下午3:31:57 
	 * @Description: 根据查询时间的范围 统计业务的拜访量
	 * @param param	时间范围  minVisitTime   maxVisitTime	
	 * @return
	 * @return: List<CVisit>
	 */
	public List<CVisit> selectVisitData(Map<String, Object> param);

}
