package com.sxt.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.sxt.common.Result;

/**
 * @ClassName: RespWriter 
 * @Description: 响应对象输出工具类
 * @author: Mr.T
 * @date: 2020年2月23日 上午10:50:47
 */
public abstract class RespWriter {

	/**
	 * @Title: writerJson
	 * @author: Mr.T   
	 * @date: 2020年2月23日 上午10:51:45 
	 * @Description: 将result 对象处理为json 进行输出
	 * @param resp
	 * @param rs
	 * @return
	 * @return: String
	 */
	public static String writerJson(HttpServletResponse resp,Result rs) {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("json/text;charset=UTF-8");
		PrintWriter writer = null;
		String data = JSONObject.toJSONString(rs);
		try {
			writer = resp.getWriter();
			writer.write(data);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			writer.close();
		}
		return data;
	}
}
