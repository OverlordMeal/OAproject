package org.cloud.manage.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.cloud.interceptor.annotation.NeedLogin;
import org.cloud.lang.BaseUtil;
import org.cloud.manage.model.PhyServer;
import org.cloud.manage.model.vo.PhyServerQuery;
import org.cloud.manage.service.PhyServerService;
import org.cloud.manage.utils.AuthUtil;
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
public class PhyServerController {

	@Autowired
	private PhyServerService pss;
	
	private Logger log = Logger.getLogger(PhyServerController.class.getName());

	@RequestMapping(value = "/phyServer/getPhyServerList", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> getPhyServerList(@RequestParam Map<String, String> data, HttpServletRequest request) {

		PageBounds pageBounds = new PageBounds();
		pageBounds.setContainsTotalCount(true);
		pageBounds.setPage(Integer.parseInt(data.get("page")));
		pageBounds.setLimit(Integer.parseInt(data.get("rows")));

		PhyServerQuery query = new PhyServerQuery();
		query.setIpAddress(data.get("ipAddress"));
		query.setServerModel(data.get("serverModel"));
		query.setMemory(data.get("memory"));
		query.setCpu(data.get("cpu"));
		query.setComment(data.get("comment"));
		query.setEthernets(BaseUtil.isEmpty(data.get("ethernets")) ? null : Integer.parseInt(data.get("ethernets")));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (data.get("onlineTime") != null) {
			try {
				query.setOnlineTime(sdf.parse(data.get("onlineTime")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		PageList<PhyServer> page = pss.findPage(query, pageBounds);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rows", page);
		result.put("total", page.getPaginator().getTotalCount());
		return result;
	}

	@RequestMapping(value = "/phyServer/add", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> add(@RequestParam Map<String, String> data, HttpServletRequest request) {

		PhyServer bean = new PhyServer();
		bean.setIpAddress(data.get("ipAddress"));
		bean.setServerModel(data.get("serverModel"));
		bean.setMemory(data.get("memory"));
		bean.setCpu(data.get("cpu"));
		bean.setComment(data.get("comment"));
		bean.setEthernets(BaseUtil.isEmpty(data.get("ethernets")) ? null : Integer.parseInt(data.get("ethernets")));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (data.get("onlineTime") != null) {
			try {
				bean.setOnlineTime(sdf.parse(data.get("onlineTime")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		String str = data.get("arry");
		//当数据为空 或者 数据格式不对时不插入数据
		if (!BaseUtil.isEmpty(str) ) {
			bean.setLinkList(AuthUtil.linkChangeList(str));
		}
		pss.insert(bean);
		return Constants.standardControllerSuccessReturnMap;
	}

	@RequestMapping(value = "/phyServer/edit", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> edit(@RequestParam Map<String, String> data) {
		Long id = Long.parseLong(data.get("id"));
		PhyServerQuery query = new PhyServerQuery();
		query.setId(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bean", pss.findById(query));
		return map;
	}

	@RequestMapping(value = "/phyServer/update", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> update(@RequestParam Map<String, String> data, HttpServletRequest request) {

		PhyServerQuery query = new PhyServerQuery();
		query.setId(Long.parseLong(data.get("id")));
		query.setIpAddress(data.get("ipAddress"));
		query.setServerModel(data.get("serverModel"));
		query.setMemory(data.get("memory"));
		query.setCpu(data.get("cpu"));
		query.setComment(data.get("comment"));
		query.setEthernets(BaseUtil.isEmpty(data.get("ethernets")) ? null : Integer.parseInt(data.get("ethernets")));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (data.get("onlineTime") != null) {
			try {
				query.setOnlineTime(sdf.parse(data.get("onlineTime")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		
		String str = data.get("arry");
		//当数据为空 或者 数据格式不对时不插入数据
		if (!BaseUtil.isEmpty(str) ) {
			query.setLinkList(AuthUtil.linkChangeList(str));
		}
		  
		pss.update(query);
		return Constants.standardControllerSuccessReturnMap;
	}

	@RequestMapping(value = "/phyServer/deleteServer", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> delete(@RequestParam Map<String, String> data, HttpServletRequest request) {
		this.pss.deleteServer(Long.parseLong(data.get("id")));
		
		return Constants.standardControllerSuccessReturnMap;
	}

	@RequestMapping(value = "/phyServer/findSwichesList", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> findSwichesList(@RequestParam Map<String, String> data, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("switchesIdList", pss.findSwitchesId());
		result.put("switchesModelList", pss.findSwitchesModel());
		
		return result;
	}

}
