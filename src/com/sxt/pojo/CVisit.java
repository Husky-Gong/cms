package com.sxt.pojo;

/**
 * @ClassName: CVisit
 * @Description: 拜访记录实体类
 * @author: Mr.T
 * @date: 2020年2月29日 下午2:27:29
 */
public class CVisit {
	/**
	 * 拜访记录ID
	 */
	private Integer id;
	/**
	 * 	用户ID
	 */
	private Integer userId;
	/**
	 * 	客户ID
	 */
	private Integer custId;
	/**
	 * 拜访时间
	 */
	private String visitTime;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 修改时间
	 */
	private String modifyTime;
	/**
	 * 条数
	 */
	private Long count;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	public String getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	

}
