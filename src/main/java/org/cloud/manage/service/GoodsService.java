package org.cloud.manage.service;




import org.cloud.manage.model.Goods;
import org.cloud.manage.model.vo.GoodsQuery;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface GoodsService {
	
	/**
	 * 
	 *分页查询服务器结果
	 * @param query
	 * @param pageBounds
	 * @return	分页结果。
	 */
	public PageList<Goods> findPage(GoodsQuery query, PageBounds pageBounds);
	
	
}
