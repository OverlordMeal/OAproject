package org.cloud.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.cloud.interceptor.annotation.NeedLogin;
import org.cloud.lang.BaseUtil;
import org.cloud.manage.model.Switches;
import org.cloud.manage.model.vo.SwitchesQuery;
import org.cloud.manage.service.SwitchesManageService;
import org.cloud.manage.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * @author chen
 *	交换机相关controller
 */


@Controller
public class SwitchesController {

	@Autowired
	private SwitchesManageService sms;

	
	/**
	 * 
	 * @param 查询相关条件
	 * @param request
	 * @return 返回list 查询结果
	 */
	
	@RequestMapping(value = "/switches/getSwitchesList", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> getSwitchesList(@RequestParam Map<String, String> data, HttpServletRequest request) {

		PageBounds pageBounds = new PageBounds();
		pageBounds.setContainsTotalCount(true);
		pageBounds.setPage(Integer.parseInt(data.get("page")));
		pageBounds.setLimit(Integer.parseInt(data.get("rows")));

		SwitchesQuery query = new SwitchesQuery();
		query.setSwitchesId(BaseUtil.isEmpty(data.get("switchesId")) ? null : Integer.parseInt(data.get("switchesId")));
		query.setSwitchesInterface(BaseUtil.isEmpty(data.get("switchesInterface")) ? null
				: Integer.parseInt(data.get("switchesInterface")));
		query.setSwitchesModel(data.get("switchesModel"));
		query.setNetType(data.get("netType"));

		PageList<Switches> page = sms.findPage(query, pageBounds);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rows", page);
		result.put("total", page.getPaginator().getTotalCount());

		return result;
	}

	@RequestMapping(value = "/switches/add", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> add(@RequestParam Map<String, String> data, HttpServletRequest request) {

		Switches bean = new Switches();
		bean.setSwitchesModel(data.get("switchesModel"));
		bean.setNetType("inner_net".equals(data.get("netType")) ? "inner_net" : "outter_net");
		bean.setSwitchesInterface(Integer.parseInt(data.get("switchesInterface")));
		bean.setSwitchesId(Integer.parseInt(data.get("switchesId")));
		System.out.println(bean.toString());
		sms.insert(bean);

		return Constants.standardControllerSuccessReturnMap;
	}

	@RequestMapping(value = "/switches/edit", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> edit(@RequestParam Map<String, String> data) {

		int id = Integer.parseInt(data.get("id"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bean", sms.findById(id));
		return map;
	}

	/**
	 * 提交菜单修改
	 */
	@RequestMapping(value = "/switches/update", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> update(@RequestParam Map<String, String> data, HttpServletRequest request) {

		Switches bean = new Switches();
		bean.setSwitchesModel(data.get("switchesModel"));
		bean.setNetType("inner_net".equals(data.get("netType")) ? "inner_net" : "outter_net");
		bean.setSwitchesInterface(Integer.parseInt(data.get("switchesInterface")));
		bean.setSwitchesId(Integer.parseInt(data.get("switchesId")));
		bean.setId(Integer.parseInt(data.get("id")));

		sms.update(bean);

		return Constants.standardControllerSuccessReturnMap;
	}

	/**
	 * 提交菜单删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/switches/delete", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> delete(@RequestParam Map<String, String> data, HttpServletRequest request) {
		this.sms.delete(Integer.parseInt(data.get("id")));
		return Constants.standardControllerSuccessReturnMap;
	}

}