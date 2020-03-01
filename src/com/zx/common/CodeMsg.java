package com.zx.common;
/**
 * 
 * To record function code and function message
 * 
 * Restriction:
 * 		format: SUCCESS(200,"success"): represents normal
 */
public enum CodeMsg {
	//Success
	SUCCESS(200,"success"),
	//Different function codes should start with different code
	//001  User part
	USER_ACCOUNT_ERROR(400001001,"Invalid password or username"),
	USER_INVALID_ERROR(400001002,"Account is deleted"),
	USER_CHECK_CODE_ERROR(400001003,"Invalid authorication code"), 
	USER_USERNAME_EXIST_ERROR(400001004,"Username has existed"),
	USER_DELETE_ERROR(400001005,"Cannot delete users connectiong with customers"),
	USER_LOGIN_TIMEOUT_ERROR(400001006,"Please login again"),

	
	//002 System part
	SYS_USER_LOGIN_NVALID_ERROR(400002001,"Invalid user"),
	SYS_USER_PASSWORD_ERROR(400002002,"Incorrect old password"),
	SYS_USER_IMG_SAVE_ERROR(400002003,"Failed updating profile"),
	
	CUSTOMER_PHONE_EXIST_ERROR(400003001,"Telephone number is in use"),
	
	;
	
	
	/**
	 *  Code and message
	 */
	Integer code ;
	
	String msg;
	
	
	CodeMsg(Integer code,String msg){
		this.code = code;
		this.msg = msg;
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
	
	
}
