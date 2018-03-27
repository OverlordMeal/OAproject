package org.cloud.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.cloud.lang.BaseUtil;
import org.cloud.manage.interceptor.OnlineData;
import org.cloud.manage.interceptor.OnlineDataVo;
import org.cloud.manage.interceptor.SessionListener;
import org.cloud.manage.service.OnlineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;


@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class OnlineServiceImpl implements OnlineService {

	@Override
	public PageList<OnlineDataVo> findPage(String name, PageBounds pageBounds) {
		
		List<OnlineData> list =  SessionListener.getOnlineList();;
		
		List<OnlineData> ret = new ArrayList<OnlineData>();
		List<OnlineDataVo> retVo = new ArrayList<OnlineDataVo>();
		int page = pageBounds.getPage();
		page = page > 0 ? page : 1;
		int limit = pageBounds.getLimit();
		int start = (page - 1) * limit;
		int end = start + limit;
		int size = end - start;
		int listSize = list.size();
		if (end > listSize) {
			end = listSize;
		}
		if (BaseUtil.isEmpty(name)) {
			ret = list.subList(start, end);
		} else {
			int i = 0;
			for (OnlineData bean : list) {
				if (bean.getUser().getUserName().contains(name)) {
					if (i < size) {
						ret.add(bean);
						i++;
					} else {
						break;
					}
				}
			}
		}
		
		for (OnlineData bean : ret) {
			OnlineDataVo vo = new OnlineDataVo(bean);
			vo.setUserName(bean.getUser().getUserName());
			retVo.add(vo);
		}
		
		return new PageList<OnlineDataVo>(retVo, new Paginator(page, limit, listSize));
	}

	@Override
	public void offline(String sessionId) {
		
		SessionListener.offline(sessionId);
	}
}
