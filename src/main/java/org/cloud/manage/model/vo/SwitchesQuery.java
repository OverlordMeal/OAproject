package org.cloud.manage.model.vo;

import org.cloud.lang.BaseUtil;

/**
 * 交换机查询对象
 * @author chen
 *
 */
public class SwitchesQuery {
	
	
	
	//交换机主键id
	private Integer id;
	
	@Override
	public String toString() {
		return "SwitchesQuery [" + (id != null ? "id=" + id + ", " : "")
				+ (switchesModel != null ? "switchesModel=" + switchesModel + ", " : "")
				+ (switchesId != null ? "switchesId=" + switchesId + ", " : "")
				+ (switchesInterface != null ? "switchesInterface=" + switchesInterface + ", " : "")
				+ (netType != null ? "netType=" + netType : "") + "]";
	}

	//交换机型号
	private String switchesModel;
	
	//交换机序号id
	private Integer switchesId;
	
	//交换机接口数量
	private Integer switchesInterface;
	
	//交换机网络类型
	private String netType;

	public Integer getId() {
		return id;
	}


	public String getNetType() {
		return netType;
	}

	public void setSwitchesModel(String switchesModel) {
		if (BaseUtil.isEmpty(switchesModel)) {
			this.switchesModel = null;
			
		} else {
			this.switchesModel = switchesModel;
		}
	}

	public String getSwitchesModel() {
		return switchesModel;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public void setNetType(String netType) {
		if (BaseUtil.isEmpty(netType)) {
			this.netType = null;
		} else {
			this.netType = netType;
		}
	}

	public Integer getSwitchesInterface() {
		return switchesInterface;
	}

	public void setSwitchesInterface(Integer switchesInterface) {
		this.switchesInterface = switchesInterface;
	}

	public Integer getSwitchesId() {
		return switchesId;
	}

	public void setSwitchesId(Integer switchesId) {
		this.switchesId = switchesId;
	}
	
	
}
