package org.cloud.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.cloud.interceptor.annotation.NeedLogin;
import org.cloud.lang.BaseUtil;
import org.cloud.lang.StringUtil;
import org.cloud.manage.model.Role;
import org.cloud.manage.model.vo.RoleQuery;
import org.cloud.manage.service.RoleService;
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
public class RoleController {

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/role/page", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> findPage(@RequestParam Map<String, String> data, HttpServletRequest request) {

		PageBounds pageBounds = new PageBounds();
		pageBounds.setContainsTotalCount(true);
		pageBounds.setPage(Integer.parseInt(data.get("page")));
		pageBounds.setLimit(Integer.parseInt(data.get("rows")));
		
		RoleQuery query = new RoleQuery();
		query.setName(data.get("name"));
		
		PageList<Role> page = roleService.findPage(query, pageBounds);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("total", page.getPaginator().getTotalCount());
		ret.put("rows", page);
		return ret;
	}
	
	@RequestMapping(value = "/role/getRoleList", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> getRoleList(@RequestParam Map<String, String> data, HttpServletRequest request) {

		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("rows", roleService.findAll());
		return ret;
	}

	@RequestMapping(value = "/role/add", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> add(@RequestParam Map<String, String> data, HttpServletRequest request) {

		Role bean = new Role();
		bean.setComments(data.get("comments"));
		bean.setName(data.get("name"));
		
		roleService.add(bean);
		
		return Constants.standardControllerSuccessReturnMap;
	}

	@RequestMapping(value = "/role/changeRolePriv", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> changeRolePriv(@RequestParam Map<String, String> data, HttpServletRequest request) {
		
		List<Long> privIds = BaseUtil.isEmpty(data.get("privIds")) ? null : StringUtil.toLongList(data.get("privIds").split(","));
		roleService.changeRolePriv(Long.parseLong(data.get("roleId")), privIds);
		return Constants.standardControllerSuccessReturnMap;
	}

	@RequestMapping(value = "/role/remove", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> remove(@RequestParam Map<String, String> data, HttpServletRequest request) {

		Long id = Long.parseLong(data.get("id"));

		roleService.remove(id);
		
		return Constants.standardControllerSuccessReturnMap;
	}

	@RequestMapping(value = "/role/show", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> show(@RequestParam Map<String, String> data) {

		Map<String, Object> map = new HashMap<String, Object>();

		Long id = Long.parseLong(data.get("id"));
		Role role = this.roleService.findById(id);
		map.put("bean", role);

		return map;
	}

	@RequestMapping(value = "/role/edit", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> edit(@RequestParam Map<String, String> data) {

		Role bean = new Role();
		bean.setComments(data.get("comments"));
		bean.setName(data.get("name"));
		bean.setId(Long.parseLong(data.get("id")));
		
		roleService.update(bean);
		
		return Constants.standardControllerSuccessReturnMap;
	}
	
	@RequestMapping(value = "/role/findByUserId", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> findByUserId(@RequestParam Map<String, String> data) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", roleService.findByUserId(Long.parseLong(data.get("userId"))));
		
		return map;
	}
}
