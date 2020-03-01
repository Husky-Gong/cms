package com.zx.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zx.common.Constant;
import com.zx.dao.BaseDao;
import com.zx.dao.ICVisitDao;
import com.zx.pojo.CVisit;

import cn.hutool.core.date.DateUtil;


public class CVisitDaoImpl extends BaseDao implements ICVisitDao {

	@Override
	public void insert(Integer userId, Integer custId, String visitTime) {
		String sql = "insert into c_visit value(0,?,?,?,?,?)";
		String createTime = DateUtil.format(new Date(), Constant.YYYY_MM_DD_HH_MM_SS);
		String modifyTime = createTime;
		super.update(sql, userId,custId,visitTime,createTime,modifyTime);
	}

	@Override
	public List<CVisit> selectVisitData(Map<String, Object> param) {
		String sql = "SELECT user_id as userId ,count(1) as count from c_visit WHERE 1=1 ";
		if(param.containsKey("minVisitTime")) {
			sql = sql + " and visit_time >= '"+param.get("minVisitTime").toString()+"' ";
		}
		if(param.containsKey("maxVisitTime")) {
			sql = sql + " and visit_time <= '"+param.get("maxVisitTime").toString()+"' ";
		}
		sql = sql +" GROUP BY user_id ";
		return super.selectList(sql, CVisit.class);
	}

}
