package org.cloud.manage.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.cloud.interceptor.annotation.NeedLogin;
import org.cloud.lang.BaseUtil;
import org.cloud.manage.model.Org;
import org.cloud.manage.service.OrgService;
import org.cloud.manage.utils.Constants;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * 组织架构Controller
 */
@Controller
public class OrgController {

	@Autowired
	private OrgService orgService;

	/**
	 * 查询组织架构
	 * 
	 * @return
	 */
	@RequestMapping(value = "/org/getOrgTreeList")
	@ResponseBody
	@NeedLogin
	public Map<String, Object> getOrgTreeList(@RequestParam Map<String, String> data, HttpServletRequest request) {

		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		
		String pid = data.get("pid");

		List<Org> orgList = orgService.findByParentId(BaseUtil.isEmpty(pid) ? null : Long.parseLong(pid), null);

		for (Org orgInfo : orgList) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", orgInfo.getId() + "");
			map.put("name", orgInfo.getName());
			map.put("isParent", orgInfo.isHasChild() ? "true" : "false");
			map.put("nocheck", "false");
			if(orgInfo.isHasChild()){
				map.put("drag", "false");
			}

			list.add(map);
		}
		
		result.put("list", list);

		return result;
	}

	/**
	 * 查询组织架构
	 * 
	 * @return
	 */
	@RequestMapping(value = "/org/getOrgList")
	@ResponseBody
	@NeedLogin
	public Map<String, Object> getOrgList(@RequestParam Map<String, String> data, HttpServletRequest request) {

		Map<String, Object> result = new HashMap<String, Object>();

		String pid = data.get("pid");
		PageBounds pageBounds = new PageBounds();
		pageBounds.setContainsTotalCount(true);
		pageBounds.setPage(Integer.parseInt(data.get("page")));
		pageBounds.setLimit(Integer.parseInt(data.get("rows")));
		
		PageList<Org> page = orgService.findByParentIdPage(BaseUtil.isEmpty(pid) ? null : Long.parseLong(pid), pageBounds);

		result.put("total", page.getPaginator().getTotalCount());
		result.put("rows", page);
		return result;
	}

	/**
	 * 删除组织架构
	 * 
	 * @return
	 */
	@RequestMapping(value = "/org/deleteOrg")
	@ResponseBody
	@NeedLogin
	public Map<String, Object> deleteOrg(@RequestParam Map<String, String> data) {

		orgService.delete(Long.parseLong(data.get("orgId")));
		
		return Constants.standardControllerSuccessReturnMap;
	}

	/**
	 * 获取组织架构
	 */
	@RequestMapping(value = "/org/detail", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> detail(@RequestParam Map<String, String> data) {

		Map<String, Object> result = new HashMap<String, Object>();

		Org org = this.orgService.findById(Long.parseLong(data.get("orgId")));

		result.put("bean", org);

		return result;
	}

	/**
	 * 修改组织架构
	 * 
	 * @return
	 */
	@RequestMapping(value = "/org/updateOrg", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> updateOrg(@RequestParam Map<String, String> data) {

		Org bean = new Org();
		bean.setComments(data.get("comments"));
		bean.setCreateDate(new Date());
		bean.setHasChild(Integer.parseInt(data.get("hasChild")) == 1);
		bean.setName(data.get("name"));
		bean.setParentId(data.get("parentId"));
		bean.setId(Long.parseLong(data.get("id")));
		
		orgService.update(bean);
		
		return Constants.standardControllerSuccessReturnMap;
	}

	/**
	 * 修改组织架构
	 * 
	 * @return
	 */
	@RequestMapping(value = "/org/addOrg", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> addOrg(@RequestParam Map<String, String> data, HttpServletRequest request) {

		Org bean = new Org();
		bean.setComments(data.get("comments"));
		bean.setCreateDate(new Date());
		bean.setHasChild(Integer.parseInt(data.get("hasChild")) == 1);
		bean.setName(data.get("name"));
		bean.setParentId(data.get("parentId"));
		
		orgService.add(bean);
		
		return Constants.standardControllerSuccessReturnMap;
	}
}
