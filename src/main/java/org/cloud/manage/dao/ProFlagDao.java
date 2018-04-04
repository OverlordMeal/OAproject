package org.cloud.manage.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cloud.manage.model.ProFlag;
import org.cloud.manage.model.vo.ProFlagQuery;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


public interface ProFlagDao {
	/**
	 * 获取标签信息
	 */
	public PageList<ProFlag> findPage(@Param("query")ProFlagQuery query, PageBounds pageBounds);

	
	/**
	 * 返回全部flag标签
	 * @param pid 
	 * @return
	 */
	public List<ProFlag> findByPid(Long pid);

	/**
	 * 插入标签对象
	 * @param bean
	 * @return
	 */
	public Long insert(ProFlag bean);

	
	
	//标签id查找
	public ProFlag findByTagId(Long id);

	//删除标签
	public Long deleteById(long id);

	//更新标签
	public Long updateByTagId(ProFlag bean);
	
	
}
