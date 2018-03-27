package org.cloud.manage.model;

import java.util.Date;

public class Log {

	public static final String TYPE_LOGIN = "登录";
	
	private Long id;
	
	private Long userId;
	
	private String userName;
	
	private String ip;
	
	private String type;
	
	private String remark;
	
	private Date createDate;

	/**
	 * 获取id
	 * @return 
	 * 		id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置id
	 * @param id
	 * 			id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取userId
	 * @return 
	 * 		userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置userId
	 * @param userId
	 * 			userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取userName
	 * @return 
	 * 		userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置userName
	 * @param userName
	 * 			userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 获取ip
	 * @return 
	 * 		ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * 设置ip
	 * @param ip
	 * 			ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 获取type
	 * @return 
	 * 		type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置type
	 * @param type
	 * 			type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取remark
	 * @return 
	 * 		remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置remark
	 * @param remark
	 * 			remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取createDate
	 * @return 
	 * 		createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 设置createDate
	 * @param createDate
	 * 			createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
