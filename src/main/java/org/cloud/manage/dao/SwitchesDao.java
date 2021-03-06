package org.cloud.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cloud.manage.model.Switches;
import org.cloud.manage.model.vo.SwitchesQuery;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface SwitchesDao {
	/**
	 * 获取交换机信息
	 */
	public PageList<Switches> findPage(@Param("query") SwitchesQuery query, PageBounds pageBounds);
	
	/**
	 * 增加交换机信息
	 */
	public void insert(Switches switches);
	
	/**
	 * 删除交换机信息
	 */
	public void delete(Integer id);
	

	/**
	 * 修改交换机信息
	 */
	public void update(Switches switches);
	

	/**
	 * 根据查交换机信息
	 */
	public Switches findById(Integer Id);
	
	
	/**
	 * 获取所有交换机信息
	 */
	public List<Switches> getAllSwitches();

}
