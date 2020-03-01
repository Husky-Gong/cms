package com.zx.common;
/**
 * @ClassName: Result 
 * @Description: 业务结果封装类 
 * @author: Mr.T
 * @date: 2020年2月25日 上午10:58:21
 */
public class Result {
	/**
	 * function code
	 */
	private Integer code;
	/**
	 * function message
	 */
	private String msg;
	/**
	 * function data
	 */
	private Object data;
	/**
	 * success result
	 */
	public Result() {
		this.code = CodeMsg.SUCCESS.getCode();
		this.msg = CodeMsg.SUCCESS.getMsg();
	}
	
	public Result(Object data) {
		this();
		this.data = data;
	}
	/**
	 * abnormal result
	 */
	public Result(CodeMsg codeMsg) {
		this.code = codeMsg.getCode();
		this.msg = codeMsg.getMsg();
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	

}
