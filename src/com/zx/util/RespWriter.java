package com.zx.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.zx.common.Result;


public abstract class RespWriter {

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
