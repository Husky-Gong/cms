package com.zx.pojo;
/**
 * @ClassName: CUser 
 * @Description:  
 * private String private String private String 关于表的命名:
 * private String private String private String private String 由于数据库是存储数据必须品,而表则是数据的具体载体。表的数量和项目的大小,业务的复杂度息息相关。
 * private String private String private String 越大越复杂的项目,表的数量会越多,关系会越复杂。而为了,让表的关系清晰,可以从命名入手。
 * private String private String private String 将关联的业务的表,可以使用一样的前缀,这样,处理表时,同样前缀的表会排列在一起。若涉及这块业务,
 * private String private String private String 则只需要关心相关表即可。其他前缀不一样的表,可以不关心。 
 * private String private String private String 
 * private String private String 例如:private String 尚学堂内部ERP
 * private String private String private String 用户管理
 * private String private String private String private String 学生private String private String 
 * private String private String private String private String 讲师
 * private String private String private String private String 运营
 * private String private String private String private String 后勤
 * private String private String private String private String 财务
 * private String private String private String 学科管理
 * private String private String private String private String 学科  : 后台   web开发  大数据    人工智能  数据挖掘  数据分析
 * private String private String private String private String 语言  : Java   python   C   Cprivate String ++  php 
 * private String private String private String private String private String 前端  UI设计  H5  安卓  IOS  混合开发private String 
 * private String private String private String 班级管理 :
 * private String private String private String private String 班级  : 语言   讲师   周期
 * 
 * private String private String private String 排课 :
 * private String private String private String private String private String 人数    讲师  周期   教室  : 用户   教室   学科  班级  但是 和 考勤  工资是没有关系
 * private String private String private String private String 
 * private String private String private String 默认表格排列方式,按名称排列。private String user ---> 排在字母中靠后    class 班级 字母中 靠前.
 * private String private String private String 从classes这个表中看完了数据   再需要去user表看数据，但是排列没有排在一起，需要人为去查找。
 * private String private String private String 如果  这两种表 都使用  u开头。 u_user    u_class  将  u作为业务模块表示。
 * private String private String private String 这样 同类的表就放在一起。让开发者注意力更加集中,提高效率。
 * private String private String private String 考请管理
 * private String private String private String private String 学生考勤 
 * private String private String private String private String 讲师考勤
 * private String private String private String private String 市场人员考勤
 * private String private String private String private String 行政人员考勤
 * private String private String private String private String 
 * private String private String private String 工资管理
 * @author: Mr.T
 * @date: 2020年2月25日 下午2:24:21
 */
public class CUser {
	/**
	 * 	用户ID
	 */
	private Integer id;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 	真实名称
	 */
	private String realName;
	/**
	 * 	用户头像
	 */
	private String img;
	/**
	 *  用户类型   ： 1 管理员            2 	业务员
	 */
	private Integer type;
	/**
	 *  数据是否有效： 1 有效   2 无效
	 */
	private Integer isDel;
	/**
	 *  创建时间
	 */
	private String createTime;
	/**
	 * 	修改时间
	 */
	private String modifyTime;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
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
	
	

}
