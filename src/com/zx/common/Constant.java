package com.zx.common;
/**
 * Constant in system
 */
public interface Constant {
	
	/**
	 *  authorization key in session
	 */
	String KEY_CHECK_CODE = "checkCode";
	/**
	 * 	valid user status
	 */
	Integer USER_STATE_VALID = 1;
	/**
	 * invalid user status
	 */
	Integer USER_STATE_INVALID = 2;
	/**
	 * 	present user key in session
	 */
	String KEY_CURRENT_USER = "user";
	/**
	 * 	default password
	 */
	String DEFAULT_PASSWORD = "123456";
	/**
	 * date format ：yyyy-MM-dd HH:mm:ss
	 */
	String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 	folder name for image saving 
	 */
	String USER_IMG_FOLDER_NAME = "user-img";
	/**
	 * manager
	 */
	Integer USER_TYPE_ADMIN = 1;
	
	/**
	 *  operator	
	 */
	Integer USER_TYPE_SALES = 2;

}
