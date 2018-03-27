package org.cloud.manage.model;


/**
 * 
 * @author chen
 *	交换机对象javabean
 *	
 */
public class Switches {
	
	
	public Switches() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((netType == null) ? 0 : netType.hashCode());
		result = prime * result + ((switchesId == null) ? 0 : switchesId.hashCode());
		result = prime * result + ((switchesInterface == null) ? 0 : switchesInterface.hashCode());
		result = prime * result + ((switchesModel == null) ? 0 : switchesModel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Switches other = (Switches) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (netType == null) {
			if (other.netType != null)
				return false;
		} else if (!netType.equals(other.netType))
			return false;
		if (switchesId == null) {
			if (other.switchesId != null)
				return false;
		} else if (!switchesId.equals(other.switchesId))
			return false;
		if (switchesInterface == null) {
			if (other.switchesInterface != null)
				return false;
		} else if (!switchesInterface.equals(other.switchesInterface))
			return false;
		if (switchesModel == null) {
			if (other.switchesModel != null)
				return false;
		} else if (!switchesModel.equals(other.switchesModel))
			return false;
		return true;
	}

	/**
	 * id主键
	 */
	private Integer id;
	
	/**
	 * 交换机id
	 */
	private Integer switchesId;
	
	/**
	 * 交换机型号
	 */
	private String switchesModel;
	
	/**
	 * 交换机接口
	 */
	private Integer switchesInterface;
	
	/**
	 * 交换机网络类型
	 * outer-net  外网
	 * inner-net  内网
	 */
	private String netType;

	public Integer getId() {
		return id;
	}

	public Integer getSwitchesId() {
		return switchesId;
	}

	public String getSwitchesModel() {
		return switchesModel;
	}

	public Integer getSwitchesInterface() {
		return switchesInterface;
	}

	public String getNetType() {
		return netType;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setSwitchesId(Integer switchesId) {
		this.switchesId = switchesId;
	}

	public void setSwitchesModel(String switchesModel) {
		this.switchesModel = switchesModel;
	}

	public void setSwitchesInterface(Integer switchesInterface) {
		this.switchesInterface = switchesInterface;
	}

	public void setNetType(String netType) {
		this.netType = netType;
	}

	@Override
	public String toString() {
		return "Switches [id=" + id + ", switchesId=" + switchesId + ", switchesModel=" + switchesModel
				+ ", switchesInterface=" + switchesInterface + ", netType=" + netType + "]";
	}
	
}
