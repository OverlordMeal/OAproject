package org.cloud.manage.service;

import java.util.List;

import org.cloud.manage.model.ProFlag;
import org.cloud.manage.model.vo.ProFlagQuery;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface ProFlagService {
	
	/**
	 * 获取全部标签信息
	 * @param query
	 * @param pageBounds
	 * @return
	 */
	public PageList<ProFlag> findPage(ProFlagQuery query, PageBounds pageBounds);
	
	
	
	List<ProFlag> findByPid(Long pid);



	public Long insert(ProFlag bean);



	public ProFlag findByTagId(Long id);



	public Long deleteById(long id);



	public Long updateByTagId(ProFlag bean);
}
