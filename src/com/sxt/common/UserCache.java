package com.sxt.common;

import java.util.HashMap;
import java.util.Map;

import com.sxt.pojo.CUser;

/**
 * Use map to store users, <user id, user>
 * when delete user, remove user from map
 * in filter, get user both from session and map. If exist in both, then let it login.

 */
public abstract class UserCache {
	
	public static Map<Integer,CUser> users = new HashMap<>();
	
	
	public static CUser getUser(Integer userId) {
		return users.get(userId);
	}
	
	public static void setUser(CUser user) {
		users.put(user.getId(), user);
	}
	
	public static void remove(Integer userId) {
		users.remove(userId);
	}
	
	public static void remove(String... userId) {
		for (String id : userId) {
			users.remove(Integer.parseInt(id));
		}
		
	}
	
}
