package org.cloud.manage.service;

import org.cloud.manage.interceptor.OnlineDataVo;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


/**
 * 在线Service
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2014-10-08 17:34:30
 * @author 
 *		Cloud
 */
public interface OnlineService {

	/**
	 * 分页查询
	 */
	public PageList<OnlineDataVo> findPage(String name, PageBounds pageBounds);
	
	/**
	 * 强制下线
	 */
	public void offline(String sessionId);
}
