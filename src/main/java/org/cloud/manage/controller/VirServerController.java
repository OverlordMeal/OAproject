package org.cloud.manage.controller;



import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.cloud.interceptor.annotation.NeedLogin;
import org.cloud.lang.BaseUtil;
import org.cloud.manage.model.VirServer;
import org.cloud.manage.model.vo.PhyServerQuery;
import org.cloud.manage.model.vo.VirServerQuery;
import org.cloud.manage.service.VirServerService;
import org.cloud.manage.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Controller
public class VirServerController {

	@Autowired
	private VirServerService vss;
	
	private Logger log = Logger.getLogger(VirServerController.class.getName());

	@RequestMapping(value = "/virServer/getVirServerList", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> getPhyServerList(@RequestParam Map<String, String> data, HttpServletRequest request) {

		PageBounds pageBounds = new PageBounds();
		pageBounds.setContainsTotalCount(true);
		pageBounds.setPage(Integer.parseInt(data.get("page")));
		pageBounds.setLimit(Integer.parseInt(data.get("rows")));
		
		VirServerQuery query = new VirServerQuery();
		query.setProTag(data.get("proTag"));
		query.setIpType(data.get("ipType"));
		query.setDisk(data.get("disk"));
		query.setPhyServerId(data.get("phyServerId"));
		query.setFlag(BaseUtil.isEmpty(data.get("flag")) ? null : data.get("flag"));
		
		PageList<VirServer> page = vss.findPage(query, pageBounds);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rows", page);
		result.put("total", page.getPaginator().getTotalCount());
		return result;
	}

	@RequestMapping(value = "/virServer/add", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> add(@RequestParam Map<String, String> data, HttpServletRequest request) {
		
		VirServer bean = new VirServer();
		bean.setPhyServerId(data.get("phyServerId"));
		bean.setSystemOs(data.get("systemOs"));
		bean.setCpu(data.get("cpu"));
		bean.setMemory(data.get("memory"));
		bean.setDisk(data.get("disk"));
		bean.setComment(data.get("comment"));
		bean.setProTag(data.get("proTag"));
		bean.setIpType(data.get("ipArry"));
		bean.setApp(data.get("appArry"));
		bean.setFlag(data.get("flag"));
		bean.setFlagName(data.get("flagName"));
		vss.insert(bean);
		
		return Constants.standardControllerSuccessReturnMap;
	}
	
	@RequestMapping(value = "/virServer/edit", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> edit(@RequestParam Map<String, String> data) {
		Long id = Long.parseLong(data.get("id"));
		VirServerQuery query = new VirServerQuery();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bean", vss.findById(id));
		return map;
	}
	
	@RequestMapping(value = "/virServer/update", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> update(@RequestParam Map<String, String> data, HttpServletRequest request) {
		VirServer bean = new VirServer();
		bean.setId(Long.parseLong(data.get("id")));
		bean.setPhyServerId(data.get("phyServerId"));
		bean.setSystemOs(data.get("systemOs"));
		bean.setCpu(data.get("cpu"));
		bean.setMemory(data.get("memory"));
		bean.setDisk(data.get("disk"));
		bean.setComment(data.get("comment"));
		bean.setProTag(data.get("proTag"));
		bean.setIpType(data.get("ipArry"));
		bean.setApp(data.get("appArry"));
		bean.setFlag(data.get("flag"));
		bean.setFlagName(data.get("flagName"));
		vss.update(bean);
		return Constants.standardControllerSuccessReturnMap;
	}
	
	
	@RequestMapping(value = "/virServer/delete", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> delete(@RequestParam Map<String, String> data, HttpServletRequest request) {
		@SuppressWarnings("unused")
		VirServer bean = new VirServer();
		Long id = Long.parseLong(data.get("id"));
		vss.delete(id);
		return Constants.standardControllerSuccessReturnMap;
	}

	@RequestMapping(value = "/virServer/findPhyServerId", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> findPhyServerId(@RequestParam Map<String, String> data, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("phyServerIdArr", vss.findPhyServerId());
		return result;
	}
	@RequestMapping(value = "/virServer/findPhyServerName", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> findPhyServerName(@RequestParam Map<String, String> data, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("phyServerIdArr", vss.findPhyServerName());
		return result;
	}
	
	@RequestMapping(value = "/virServer/findPhyServerIp", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> findPhyServerIp(@RequestParam Map<String, String> data, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("phyServerIdArr", vss.findPhyServerIp());
		return result;
	}
	

}
