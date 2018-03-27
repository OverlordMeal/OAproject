package org.cloud.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cloud.interceptor.annotation.NeedLogin;
import org.cloud.lang.BaseUtil;
import org.cloud.lang.date.DateUtil;
import org.cloud.lang.date.DefaultDateFormat;
import org.cloud.manage.model.Log;
import org.cloud.manage.model.vo.LogQuery;
import org.cloud.manage.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


@Controller
@RequestMapping("/log")
public class LogController {

	@Autowired
	private LogService service;
	
	@RequestMapping(value="/findPage",method=RequestMethod.POST)
    @ResponseBody
    @NeedLogin
	public Map<String,Object> findPage(@RequestParam Map<String, String> data, HttpServletRequest request,HttpServletResponse response){
		
		String type = data.get("type");
		String createDateStart = data.get("createDateStart");
		String createDateEnd = data.get("createDateEnd");
		
		LogQuery query = new LogQuery();
		query.setCreateDateEnd(BaseUtil.isEmpty(createDateEnd) ? null : DateUtil.stringToDate(createDateEnd, DefaultDateFormat.DAY));
		query.setCreateDateStart(BaseUtil.isEmpty(createDateStart) ? null : DateUtil.stringToDate(createDateStart, DefaultDateFormat.DAY));
		query.setType(type);
		
		PageBounds pageBounds = new PageBounds();
		pageBounds.setContainsTotalCount(true);
		pageBounds.setPage(Integer.parseInt(data.get("page")));
		pageBounds.setLimit(Integer.parseInt(data.get("rows")));
		
		PageList<Log> page = service.findPage(query, pageBounds);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rows", page);
		result.put("total", page.getPaginator().getTotalCount());
		
		return result;
	}
}
