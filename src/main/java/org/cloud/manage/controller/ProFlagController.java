package org.cloud.manage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.cloud.interceptor.annotation.NeedLogin;
import org.cloud.manage.model.Menu;
import org.cloud.manage.model.ProFlag;
import org.cloud.manage.model.vo.ProFlagQuery;
import org.cloud.manage.service.ProFlagService;
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
public class ProFlagController {

	@Autowired
	private ProFlagService pfs;
	
	private Logger log = Logger.getLogger(ProFlagController.class.getName());

	@RequestMapping(value = "/proFlag/getProFlagList", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> getProFlagList(@RequestParam Map<String, String> data, HttpServletRequest request) {

		PageBounds pageBounds = new PageBounds();
		pageBounds.setContainsTotalCount(true);
		pageBounds.setPage(Integer.parseInt(data.get("page")));
		pageBounds.setLimit(Integer.parseInt(data.get("rows")));

		ProFlagQuery query = new ProFlagQuery();
		
		PageList<ProFlag> page = pfs.findPage(query, pageBounds);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rows", page);
		result.put("total", page.getPaginator().getTotalCount());
		return result;
	}
	
	@RequestMapping(value = "/proFlag/add", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> add(@RequestParam Map<String, String> data, HttpServletRequest request) {


		ProFlag bean = new ProFlag();
		bean.setHasChild("1".equals(data.get("hasChild")) ? true : false);
		bean.setName(data.get("name"));
		bean.setParentId(Long.parseLong(data.get("superId")));
		
		pfs.insert(bean);
		return Constants.standardControllerSuccessReturnMap;
	}
	
	
	@RequestMapping(value = "/proFlag/edit", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> edit(@RequestParam Map<String, String> data, HttpServletRequest request) {
	   
		
		long id = Long.parseLong(data.get("tagId"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bean", pfs.findByTagId(id));
		
		return map;
	}
	
	@RequestMapping(value = "/proFlag/update", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> update(@RequestParam Map<String, String> data, HttpServletRequest request) {
	   
		ProFlag bean = new ProFlag();
		bean.setHasChild("1".equals(data.get("hasChild")) ? true : false);
		bean.setName(data.get("name"));
		bean.setParentId(Long.parseLong(data.get("superName")));
		bean.setTagId(Long.parseLong(data.get("tagId")));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bean", pfs.updateByTagId(bean));
		
		return map;
	}
	
	
	

	@RequestMapping(value = "/proFlag/delete", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> delete(@RequestParam Map<String, String> data, HttpServletRequest request) {
	   
		
		long id = Long.parseLong(data.get("tagId"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bean", pfs.deleteById(id));
		
		return map;
	}
	
	
	
	@RequestMapping(value = "/proFlag/ztree/find/check", method = RequestMethod.GET)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> findByZtreeCheck(@RequestParam Map<String, String> data, HttpServletRequest request) {

		String pid = data.get("pid");
		List<ProFlag> list = pfs.findByPid(StringUtils.isEmpty(pid) ? null : Long.parseLong(pid));
		
		List<Map<String, String>> menuList = new ArrayList<Map<String, String>>();
		for (ProFlag proFlag : list) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", proFlag.getTagId().toString());
			map.put("name", proFlag.getName()); 
			map.put("isParent", proFlag.isHasChild() ? "true" : "false");
			menuList.add(map);
		}
		
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("list", menuList);
		return ret;
	}


	@RequestMapping(value = "/proFlag/ztree/find/radio", method = RequestMethod.GET)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> findByZtreeRadio(@RequestParam Map<String, String> data, HttpServletRequest request) {
		String pid = data.get("pid");
		List<ProFlag> list = pfs.findByPid(StringUtils.isEmpty(pid) ? null : Long.parseLong(pid));
		List<Map<String, String>> proFlagList = new ArrayList<Map<String, String>>();
		for (ProFlag proFlag : list) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", proFlag.getTagId().toString());
			map.put("name", proFlag.getName()); 
			map.put("isParent", proFlag.isHasChild() ? "true" : "false");
			map.put("nocheck", "false");
			proFlagList.add(map);
		}
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("list", proFlagList);
		return ret;
	}

}
