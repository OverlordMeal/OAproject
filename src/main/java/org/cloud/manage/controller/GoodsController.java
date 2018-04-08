package org.cloud.manage.controller;



import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.cloud.interceptor.annotation.NeedLogin;
import org.cloud.manage.model.Goods;
import org.cloud.manage.model.vo.GoodsQuery;
import org.cloud.manage.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Controller
public class GoodsController {

	@Autowired
	private GoodsService vss;
	

	@RequestMapping(value = "/goods/getGoodsList", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> getPhyServerList(@RequestParam Map<String, String> data, HttpServletRequest request) {

		PageBounds pageBounds = new PageBounds();
		pageBounds.setContainsTotalCount(true);
		pageBounds.setPage(Integer.parseInt(data.get("page")));
		pageBounds.setLimit(Integer.parseInt(data.get("rows")));
		
		GoodsQuery query = new GoodsQuery();
		
		PageList<Goods> page = vss.findPage(query, pageBounds);
		Map<String, Object> result = new HashMap<String, Object>();
		
		
		result.put("rows", page);
		result.put("total", page.getPaginator().getTotalCount());
		return result;
	}


}
