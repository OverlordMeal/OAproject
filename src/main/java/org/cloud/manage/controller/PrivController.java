package org.cloud.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.cloud.interceptor.annotation.NeedLogin;
import org.cloud.lang.BaseUtil;
import org.cloud.lang.StringUtil;
import org.cloud.manage.model.Priv;
import org.cloud.manage.model.vo.PrivQuery;
import org.cloud.manage.service.PrivService;
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
public class PrivController {

	@Autowired
	private PrivService service;

	/**
	 * 权限列表
	 */
	@RequestMapping(value = "/priv/getPrivList", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> getPrivList(@RequestParam Map<String, String> data, HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		
		PageBounds pageBounds = new PageBounds();
		pageBounds.setContainsTotalCount(true);
		pageBounds.setPage(Integer.parseInt(data.get("page")));
		pageBounds.setLimit(Integer.parseInt(data.get("rows")));
		
		PrivQuery query = new PrivQuery();
		query.setName(data.get("name"));
		
		PageList<Priv> page = service.findPage(query, pageBounds);
		map.put("total", page.getPaginator().getTotalCount());
		map.put("rows", page);
		return map;
	}
	
	/**
	 * 权限列表(全部)
	 */
	@RequestMapping(value = "/priv/getAllPrivList", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> getAllPrivList(@RequestParam Map<String, Object> data, HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", service.findAll());
		return map;
	}

	/**
	 * 新增
	 * 
	 * @param data
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/priv/add", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> add(@RequestParam Map<String, String> data, HttpServletRequest request) {
		
		Priv bean = new Priv();
		bean.setComments(data.get("comments"));
		bean.setName(data.get("name"));
		
		service.add(bean);
		
		return Constants.standardControllerSuccessReturnMap;
	}

	/**
	 * 权限编辑获取初始值
	 * 
	 * @return
	 */
	@RequestMapping(value = "/priv/edit", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> edit(@RequestParam Map<String, String> data) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bean", service.findById(Long.parseLong(data.get("id"))));
		
		return map;
	}

	/**
	 * 提交权限修改
	 * 
	 * @return
	 */
	@RequestMapping(value = "/priv/update", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> update(@RequestParam Map<String, String> data, HttpServletRequest request) {
		
		Priv bean = new Priv();
		bean.setComments(data.get("comments"));
		bean.setName(data.get("name"));
		bean.setId(Long.parseLong(data.get("id")));
		
		service.update(bean);
		
		return Constants.standardControllerSuccessReturnMap;
	}
	
	/**
	 * 提交权限删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/priv/delete", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> delete(@RequestParam Map<String, String> data, HttpServletRequest request) {
		
		service.delete(Long.parseLong(data.get("privId")));
		
		return Constants.standardControllerSuccessReturnMap;
	}

	/**
	 * 提交权限菜单修改
	 * 
	 * @return
	 */
	@RequestMapping(value = "/priv/changePrivMenu", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> changePrivMenu(@RequestParam Map<String, String> data, HttpServletRequest request) {
		
		List<Long> menuIds = BaseUtil.isEmpty(data.get("menuIds")) ? null : StringUtil.toLongList(data.get("menuIds").split(","));
		service.changePrivMenu(Long.parseLong(data.get("privId")), menuIds);
		
		return Constants.standardControllerSuccessReturnMap;
	}
	
	@RequestMapping(value = "/priv/findByRoleId", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> findByRoleId(@RequestParam Map<String, String> data, HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", service.findByRoleId(Long.parseLong(data.get("roleId"))));
		
		return map;
	}
}
