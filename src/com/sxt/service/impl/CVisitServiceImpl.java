package com.sxt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sxt.common.Constant;
import com.sxt.common.EchartsData;
import com.sxt.common.Result;
import com.sxt.dao.ICUserDao;
import com.sxt.dao.ICVisitDao;
import com.sxt.dao.impl.CUserDaoImpl;
import com.sxt.dao.impl.CVisitDaoImpl;
import com.sxt.pojo.CUser;
import com.sxt.pojo.CVisit;
import com.sxt.service.ICVisitService;

public class CVisitServiceImpl implements ICVisitService {
	
	ICVisitDao cvisitDao = new CVisitDaoImpl();
	
	ICUserDao  cuserDao = new CUserDaoImpl();

	@Override
	public Result addVisitLog(String custId, Integer userId, String visitTime) {
		cvisitDao.insert(userId, Integer.parseInt(custId), visitTime);
		return new Result();
	}

	@Override
	public Result salesVisitLog(Map<String,Object> param) {
		/*
		 	每个业务员的总拜访量       直接在拜访记录表 进行 分组统计
		 		刘德华   :  1  
		 		张学友  :
		 	1. 如果业务员没有拜访记录 则显示的数据为  0 
		 	2. 如果业务员目前的状态是 已离职,则其拜访记录 放在其他中存储 (若多个业务员离职 则其他是多个业务员记录和)	
		 */
		//获取已拜访的客户的拜访数量
		List<CVisit>  visitData =  cvisitDao.selectVisitData(param);
		//图例数据 : 因为这个图是写死的 所以无需处理
		
		//x轴数据 : 显示所有的业务员  所以需要处理。所有的业务员名称  +  其他。
		//y轴数据 : 在柱状图中,echarts 会根据 数据值 自动处理y轴的值  默认使用图表系列中数据值  进行渲染
		//图表系列数据  将业务员的拜访量
		/*
		  visitData 中含有的已拜访的业务员的数据,可能出现当前业务员从来没有拜访过  也可能某些业务员已经离职。
		  	当前业务员从没有拜访,则其值 应该为0
		  	已离职的业务员   取出其值  放入 其他业务员中
		 */
		/*
		 1. 查询出有效所有的业务员
		 2. 根据所有有效的业务员构建一个map 容器   key - 业务员ID  value 默认是0   表示业务员的拜访量
		 3. 遍历visitData 获取 业务员的Id  从上面map中查找是否存在这个id.
		 	如果 存在 说明 这个visitData 中count 就是业务员的拜访量。修改这个key 对应的 值
		 	如果不存在 说明:说明这个业务员已经失效了。这个数值 是 属于 其他。
		 */
		//1. 获取所有有效的业务员
		List<CUser> users = cuserDao.selectUsers(Constant.USER_TYPE_SALES);
		//2. 构建一个map 容器   key - 业务员ID  value 默认是0   表示业务员的拜访量
		Map<Integer,Integer> data = new HashMap<Integer, Integer>();
		for (CUser cUser : users) {
			data.put(cUser.getId(), 0);
		}
		int otherCount = 0;
		//3.遍历visitData
		for (CVisit visit : visitData) {
			//业务员ID
			Integer userId = visit.getUserId();
			//业务员拜访数量
			Integer count = visit.getCount().intValue();
			//包含 则将0 修改为其具体的拜访数值
			if(data.containsKey(userId)) {
				data.put(userId, count);
				continue;
			}
			//不包含 ,说明是其他的
			otherCount = otherCount + count;
		}
		//构建echarts 需要的数据
		//x 轴数据容器   业务员名称
		List<Object> xaxisData = new ArrayList<Object>();
		//图表系列数据容器  业务员的访问量
		List<Object> seriesData = new ArrayList<Object>();
		for (CUser cUser : users) {
			Integer userId = cUser.getId();
			String realName = cUser.getRealName();
			//将名称放入容器
			xaxisData.add(realName);
			//访问量放入容器
			seriesData.add(data.get(userId));
		}
		//将所有离职的业务员数据放入容器
		xaxisData.add("其他");
		seriesData.add(otherCount);
		
		EchartsData echartsData = new EchartsData();
		echartsData.setXaxisData(xaxisData);
		echartsData.setSeriesData(seriesData);
		
		return new Result(echartsData);
	}

}
