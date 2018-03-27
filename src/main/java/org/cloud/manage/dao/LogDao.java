package org.cloud.manage.dao;

import org.apache.ibatis.annotations.Param;
import org.cloud.manage.model.Log;
import org.cloud.manage.model.vo.LogQuery;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


/**
 * 日志Dao
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2014-10-08 17:29:53
 * @author 
 *		Cloud
 */
public interface LogDao {

	public void add(Log bean);
	
	public PageList<Log> findPage(@Param("query")LogQuery query, PageBounds pageBounds);
}
