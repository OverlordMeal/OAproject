package org.cloud.manage.model.vo;

import java.util.Date;
import java.util.List;

public class PhyServerQuery extends BaseQuery{
	//服务器主键id
		private Long id;
		
		//ip地址
		private String ipAddress;
		
		//上线时间
		private Date onlineTime;
		
		//物理服务器型号
		private String serverModel;
		
		//服务器内存
		private String memory;
		
		//服务器cpu
		private String cpu;
		
		//服务器端口数量
		private Integer ethernets;
		
		public List getLinkList() {
			return linkList;
		}

		public void setLinkList(List linkList) {
			this.linkList = linkList;
		}

		private String comment;
		
		//正在使用的服务器端口
		private Integer serverEth;
		
		//连接的交换机id
		private Integer switchesId; 
		
		//连接的交换机的Id
		private Integer switchesEth;
		
		
		//连接集合
		private List linkList;
		

		public Long getId() {
			return id;
		}

		public String getIpAddress() {
			return ipAddress;
		}

		public Date getOnlineTime() {
			return onlineTime;
		}

		public String getServerModel() {
			return serverModel;
		}

		public String getMemory() {
			return memory;
		}

		public String getCpu() {
			return cpu;
		}

		public Integer getEthernets() {
			return ethernets;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public void setIpAddress(String ipAddress) {
			this.ipAddress = ipAddress;
		}

		public void setOnlineTime(Date onlineTime) {
			this.onlineTime = onlineTime;
		}

		public void setServerModel(String serverModel) {
			this.serverModel = serverModel;
		}

		public void setMemory(String memory) {
			this.memory = memory;
		}

		public void setCpu(String cpu) {
			this.cpu = cpu;
		}

		public void setEthernets(Integer ethernets) {
			this.ethernets = ethernets;
		}


		public Integer getSwitchesId() {
			return switchesId;
		}

		public Integer getSwitchesEth() {
			return switchesEth;
		}


		public void setSwitchesId(Integer switchesId) {
			this.switchesId = switchesId;
		}

		public void setSwitchesEth(Integer switchesEth) {
			this.switchesEth = switchesEth;
		}



		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public Integer getServerEth() {
			return serverEth;
		}

		public void setServerEth(Integer serverEth) {
			this.serverEth = serverEth;
		}

		@Override
		public String toString() {
			return "PhyServerQuery [" + (id != null ? "id=" + id + ", " : "")
					+ (ipAddress != null ? "ipAddress=" + ipAddress + ", " : "")
					+ (onlineTime != null ? "onlineTime=" + onlineTime + ", " : "")
					+ (serverModel != null ? "serverModel=" + serverModel + ", " : "")
					+ (memory != null ? "memory=" + memory + ", " : "") + (cpu != null ? "cpu=" + cpu + ", " : "")
					+ (ethernets != null ? "ethernets=" + ethernets + ", " : "")
					+ (comment != null ? "comment=" + comment + ", " : "")
					+ (linkList != null ? "linkList=" + linkList : "") + "]";
		}
		
		
		
		
}
