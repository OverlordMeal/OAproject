package org.cloud.manage.model.vo;

import java.util.Date;

import org.cloud.lang.BaseUtil;

public class LogQuery extends BaseQuery {

	private String type;
	
	private Date createDateStart;
	
	private Date createDateEnd;

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
		
		if (BaseUtil.isEmpty(type)) {
			this.type = null;
		} else {
			this.type = type;
		}
	}

	/**
	 * 获取createDateStart
	 * @return 
	 * 		createDateStart
	 */
	public Date getCreateDateStart() {
		return createDateStart;
	}

	/**
	 * 设置createDateStart
	 * @param createDateStart
	 * 			createDateStart
	 */
	public void setCreateDateStart(Date createDateStart) {
		this.createDateStart = createDateStart;
	}

	/**
	 * 获取createDateEnd
	 * @return 
	 * 		createDateEnd
	 */
	public Date getCreateDateEnd() {
		return createDateEnd;
	}

	/**
	 * 设置createDateEnd
	 * @param createDateEnd
	 * 			createDateEnd
	 */
	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public LogQuery() {
		
		setOrderByField(" t.id desc ");
	}
}
