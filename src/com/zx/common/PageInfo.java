package com.zx.common;
/**
 * @ClassName: PageInfo 
 * @Description: 分页查询结果封装类
 * @author: Mr.T
 * @date: 2020年2月25日 上午10:58:44
 */

import java.util.List;

public class PageInfo<T> {
	/**
	 * page number
	 */
	private Integer page;
	/**
	 * 	page length
	 */
	private Integer limit;
	/**
	 * 	search result number
	 */
	private Integer count;
	/**
	 * 	search result data
	 */
	private List<T> data;
	
	public PageInfo() {}
	
	public PageInfo(Integer page, Integer limit, Integer count, List<T> data) {
		super();
		this.page = page;
		this.limit = limit;
		this.count = count;
		this.data = data;
	}
	
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
}
