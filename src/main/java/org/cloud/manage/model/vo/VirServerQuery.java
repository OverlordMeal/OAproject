package org.cloud.manage.model.vo;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.cloud.manage.model.VirServer;
import org.cloud.manage.utils.JsonUtil;

import com.alibaba.fastjson.JSONArray;

public class VirServerQuery {
	
	Logger log = Logger.getLogger(VirServer.class.getName());
	//主键ID
	private Long id;
	
	//对应物理服务器类型
	private String phyServerId;
	
	//ip类型
	private String ipType;
	
	//服务器操作系统
	private String systemOs;
	
	//虚拟服务器内存
	private String memory;
	
	//服务器cpu
	private String cpu;
	
	//服务器硬盘
	private String disk;
	
	//项目标签
	private String proTag;

	//app对应地址
	private String app;
	
	//备注
	private String comment;

	//标签
	private String flag;
	
	//标签名字
	private String flagName;
	
	
	//将得到json格式的字符串转化为 标准格式输出到网页上
	public String getIpTypeString(){
		String str = "";
		List<Map<String, Object>> list = JsonUtil.jsonToList(this.ipType);
			for (Map<String, Object> map : list) {
				String netType = "0".equals(String.valueOf(map.get("netType")))? "内网" : "外网";
				str += netType+"-ip地址 :"+ map.get("ipAddress")+"</br>";
			}
		return str;
	}
	
	//将得到json格式的字符串转化为 标准格式输出到网页上
	public String getAppToString(){
		String str = "";
		List<Map<String, Object>> list = JsonUtil.jsonToList(this.app);
			for (Map<String, Object> map : list) {
				str += "类型:"+map.get("appType")+"-路径 :"+ map.get("address")+"</br>";
			}
		return str;
	}
	
	
	
	
	public Long getId() {
		return id;
	}
	
	
	
	
	public String getPhyServerId() {
		return phyServerId;
	}

	public String getIpType() {
		return ipType;
	}

	public String getSystemOs() {
		return systemOs;
	}

	public String getMemory() {
		return memory;
	}

	public String getCpu() {
		return cpu;
	}

	public String getDisk() {
		return disk;
	}

	public String getProTag() {
		return proTag;
	}

	public String getApp() {
		return app;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPhyServerId(String phyServerId) {
		this.phyServerId = phyServerId;
	}

	public void setIpType(String ipType) {
		this.ipType = ipType;
	}



	public void setSystemOs(String systemOs) {
		this.systemOs = systemOs;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public void setDisk(String disk) {
		this.disk = disk;
	}

	public void setProTag(String proTag) {
		this.proTag = proTag;
	}

	public void setApp(String app) {
		this.app = app;
	} 
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

		@Override
		public String toString() {
			return "VirServer [" + (id != null ? "id=" + id + ", " : "")
					+ (ipType != null ? "ipType=" + ipType + ", " : "")
					+ (systemOs != null ? "systemOs=" + systemOs + ", " : "")
					+ (memory != null ? "memory=" + memory + ", " : "") + (cpu != null ? "cpu=" + cpu + ", " : "")
					+ (disk != null ? "disk=" + disk + ", " : "") + (proTag != null ? "proTag=" + proTag + ", " : "")
					+ (app != null ? "app=" + app : "") + "]";
		}

		public String getFlag() {
			return flag;
		}

		public String getFlagName() {
			return flagName;
		}

		public void setFlag(String flag) {
			this.flag = flag;
		}

		public void setFlagName(String flagName) {
			this.flagName = flagName;
		}
		
		

}
