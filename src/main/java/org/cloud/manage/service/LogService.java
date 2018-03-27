package org.cloud.manage.service;

import org.cloud.manage.model.Log;
import org.cloud.manage.model.vo.LogQuery;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


/**
 * 日志Service
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2014-10-08 17:29:01
 * @author 
 *		Cloud
 */
public interface LogService {

	public long add(Log bean);
	
	public PageList<Log> findPage(LogQuery query, PageBounds pageBounds);
}
