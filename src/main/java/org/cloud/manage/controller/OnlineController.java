package org.cloud.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cloud.interceptor.annotation.NeedLogin;
import org.cloud.manage.interceptor.OnlineDataVo;
import org.cloud.manage.service.OnlineService;
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
@RequestMapping("/online")
public class OnlineController {

	@Autowired
	private OnlineService service;
	
	@RequestMapping(value="/findPage",method=RequestMethod.POST)
    @ResponseBody
    @NeedLogin
	public Map<String,Object> findPage(@RequestParam Map<String, String> data, HttpServletRequest request,HttpServletResponse response) {
    	
		PageBounds pageBounds = new PageBounds();
		pageBounds.setContainsTotalCount(true);
		pageBounds.setPage(Integer.parseInt(data.get("page")));
		pageBounds.setLimit(Integer.parseInt(data.get("rows")));
		
		String name = data.get("name");
		
		PageList<OnlineDataVo> page = service.findPage(name, pageBounds);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("total", page.getPaginator().getTotalCount());
		ret.put("rows", page);
		return ret;
	}
	
	@RequestMapping(value="/offline",method=RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String,Object> offline(@RequestParam Map<String, String> data, HttpServletRequest request,HttpServletResponse response) {
    	
		String sessionId = data.get("sessionId");
		
		service.offline(sessionId);
		
		return Constants.standardControllerSuccessReturnMap;
	}
}
