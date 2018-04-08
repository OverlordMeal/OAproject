package org.cloud.manage.model;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.cloud.lang.BaseUtil;
import org.cloud.lang.date.DateUtil;
import org.cloud.lang.date.DefaultDateFormat;

/**
 * 
 * @author chen 物理服务器相关属性
 */
public class PhyServer {

	// 服务器主键id
	private Long id;

	// ip地址
	private String ipAddress;

	// 上线时间
	private Date onlineTime;

	// 物理服务器型号
	private String serverModel;

	// 服务器内存
	private String memory;

	// 服务器cpu
	private String cpu;

	// 服务器端口数量
	private Integer ethernets;
	// 备注
	private String comment;

	//标签
	private String flag;
	
	//标签名字
	private String flagName;
	// 连接表集合
	private List<Link> linkList;
	
	public String getLinkToStr() {
		if (BaseUtil.isEmpty(linkList)) {
			return "无连接";
		} else {
			String str = "";
			for (Link link : linkList) {
				str +=  link.getServerEth() + "-服务端口" +
						link.getSwitchesId() + "-号交换机" + 
						link.getSwitchesEth() + "-交换端口</br>";
			}
			return str;
		}
	}

	public String getLinkListStr(){
		if (BaseUtil.isEmpty(linkList)) {
			return "无连接";
		} else {
			String str = "";
			for (Link link : linkList) {
				str += StringUtils.rightPad(
						link.getServerEth() + "服务端口" +
						link.getSwitchesId() + "号交换机" + 
						link.getSwitchesEth() + "交换端口", 21, ' ');
			}
			return str;
		}
	}
	
	
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

	public String getComment() {
		return comment;
	}

	public List<Link> getLinkList() {
		return linkList;
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

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setLinkList(List<Link> linkList) {
		this.linkList = linkList;
	}

	public String getOnlineTimeStr() {

		if (onlineTime == null) {
			return "";
		} else {
			return DateUtil.dateToString(onlineTime, DefaultDateFormat.DAY);
		}
	}

	@Override
	public String toString() {
		return "PhyServer [" + (id != null ? "id=" + id + ", " : "")
				+ (ipAddress != null ? "ipAddress=" + ipAddress + ", " : "")
				+ (onlineTime != null ? "onlineTime=" + onlineTime + ", " : "")
				+ (serverModel != null ? "serverModel=" + serverModel + ", " : "")
				+ (memory != null ? "memory=" + memory + ", " : "") + (cpu != null ? "cpu=" + cpu + ", " : "")
				+ (ethernets != null ? "ethernets=" + ethernets + ", " : "")
				+ (comment != null ? "comment=" + comment + ", " : "") + (flag != null ? "flag=" + flag + ", " : "")
				+ (linkList != null ? "linkList=" + linkList : "") + "]";
	}

	public String getFlag() {
		return flag;
	}
	public String getFlagString() {
		if(BaseUtil.isEmpty(this.flag)){
			return "无标签";
		}
		else{
			String str = "";
			String[] str1 = flag.split(",");
			for (int i = 0; i < str1.length; i++) {
				if(!BaseUtil.isEmpty(str1[i])){
					str +=str1[i]+",";
				}
			}
		return str;
		}
		
	
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlagName() {
		return flagName;
	}

	public void setFlagName(String flagName) {
		this.flagName = flagName;
	}
	
	
	public String getFlagNameString() {
		if(BaseUtil.isEmpty(this.flagName)){
			return "无标签";
		}
		else{
			String str = "";
			String[] str1 = flagName.split(",");
			for (int i = 0; i < str1.length; i++) {
				if(!BaseUtil.isEmpty(str1[i])){
					str +=str1[i]+",";
				}
			}
		return str;
		}
		
	
	}


}

