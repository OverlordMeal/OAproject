package org.cloud.manage.dao;



import org.apache.ibatis.annotations.Param;
import org.cloud.manage.model.Goods;
import org.cloud.manage.model.vo.GoodsQuery;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface GoodsDao {

	
	//分页返回结果
	public PageList<Goods> findPage(@Param("query") GoodsQuery query, PageBounds pageBounds); 


}
