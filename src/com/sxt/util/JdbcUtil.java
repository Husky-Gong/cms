package com.sxt.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName: JdbcUtil 
 * @Description: JDBC连接操作类
 * @author: Mr.T
 * @date: 2020年2月3日 下午5:47:55
 */
public class JdbcUtil {
	/**
	 * 	数据区驱动包
	 */
	private static String driver = null;
	
	/**
	 * 	数据库连接URL
	 */
	private static String url = null;
	/**
	 * 	数据库用户名
	 */
	private static String username = null;
	/**
	 * 	数据库密码
	 */
	private static String password = null;
	
	static {
		//找到properties配置文件
		InputStream in = JdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties prop = new Properties();
		try {
			//加载配置文件
			prop.load(in);
			//读取配置文件信息
			driver = prop.getProperty("jdbc.driver");
			url = prop.getProperty("jdbc.url");
			username = prop.getProperty("jdbc.username");
			password = prop.getProperty("jdbc.password");
			//加载驱动
			Class.forName(driver);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * @Title: getConn
	 * @author: Mr.T   
	 * @date: 2020年2月3日 下午5:55:48 
	 * @Description: 获取连接
	 * @return
	 * @return: Connection
	 */
	public static Connection getConn () {
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void close(Connection conn,PreparedStatement prep) {
		if(prep != null) {
			try {
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * @Title: close
	 * @author: Mr.T   
	 * @date: 2020年2月3日 下午5:58:12 
	 * @Description: 释放资源
	 * @param conn
	 * @param prep
	 * @param rs
	 * @return: void
	 */
	public static void close(Connection conn,PreparedStatement prep,ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
				close(conn, prep);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


}
