package org.cloud.manage.model.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 需求查询对象
 * @author lnj
 * @date 2016年8月30日     上午10:07:47
 */
public class DemandQuery extends BaseQuery implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1492309906994663129L;


	/**
	 * 主键
	 */
	private long                id = -1;
	
	/**
	 * 标题
	 */
	private String              title;
	
	/**
	 * 需求类型
	 */
	private int                 type = -1;
	
	/**
	 * 需求提出人
	 */
	private long                demandForward = -1;
	
	/**
	 * 状态
	 */
	private int                 state = -1;
	
	/**
	 * 时间类型
	 */
	private int                 timeType = -1;
	
	/**
	 * 开始时间
	 */
	private Date                startTime;
	
	/**
	 * 结束时间
	 */
	private Date                endTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getDemandForward() {
		return demandForward;
	}

	public void setDemandForward(long demandForward) {
		this.demandForward = demandForward;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getTimeType() {
		return timeType;
	}

	public void setTimeType(int timeType) {
		this.timeType = timeType;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	

}
