package org.cloud.manage.service.impl;

import org.cloud.manage.dao.LogDao;
import org.cloud.manage.model.Log;
import org.cloud.manage.model.vo.LogQuery;
import org.cloud.manage.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


/**
 * 日志Service 实现
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2014-10-08 17:29:14
 * @author 
 *		Cloud
 */
@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class LogServiceImpl implements LogService {

	@Autowired
	private LogDao dao;
	
	@Override
	public long add(Log bean) {
		
		dao.add(bean);
		return bean.getId();
	}

	@Override
	public PageList<Log> findPage(LogQuery query, PageBounds pageBounds) {
		
		return dao.findPage(query, pageBounds);
	}
}
