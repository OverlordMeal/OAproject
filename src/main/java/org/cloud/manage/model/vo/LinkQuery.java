package org.cloud.manage.model.vo;

public class LinkQuery {
	
	//服务器Id
	private Long serverId;
	
	//服务器端口
	private Integer serverEth;
	
	//交换机id
	private Long switchesId;
	
	//交换机端口
	private Integer switchesEth;

	@Override
	public String toString() {
		return "LinkQuery [" + (serverId != null ? "serverId=" + serverId + ", " : "")
				+ (serverEth != null ? "serverEth=" + serverEth + ", " : "")
				+ (switchesId != null ? "switchesId=" + switchesId + ", " : "")
				+ (switchesEth != null ? "switchesEth=" + switchesEth : "") + "]";
	}

	public Long getServerId() {
		return serverId;
	}

	public Integer getServerEth() {
		return serverEth;
	}

	public Long getSwitchesId() {
		return switchesId;
	}

	public Integer getSwitchesEth() {
		return switchesEth;
	}

	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}

	public void setServerEth(Integer serverEth) {
		this.serverEth = serverEth;
	}

	public void setSwitchesId(Long switchesId) {
		this.switchesId = switchesId;
	}

	public void setSwitchesEth(Integer switchesEth) {
		this.switchesEth = switchesEth;
	}
	
	
}
