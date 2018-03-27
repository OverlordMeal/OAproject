package org.cloud.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cloud.manage.model.Role;
import org.cloud.manage.model.vo.RoleQuery;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


/**
 * 角色Dao
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2013-08-02 09:28:56
 * @author 
 *		Cloud
 */
public interface RoleDao {

	/**
	 * 根据条件查找记录
	 */
	public List<Role> findList(RoleQuery query);
	
	/**
	 * 查找单页记录
	 */
	public PageList<Role> findPage(@Param("query")RoleQuery query, PageBounds pageBounds);
	
	/**
	 * 新增
	 */
	public void add(Role bean);

	/**
	 * 添加角色权限关系 
	 *  
	 * @return
	 */
	public void addRolePriv(@Param("roleId")long roleId, @Param("privIds")List<Long> privIds);

	/**
	 * 删除角色
	 */
	public boolean remove(long id);
	
	/**
	 * 删除角色权限关系
	 */
	public boolean removeRolePrivByRoleId(long roleId);
	
	/**
	 * 根据id获取角色
	 */
	public Role findById(long id);
	
	/**
	 * 更新数据
	 */
	public void update(Role bean);
	
	/**
	 * 获取权限选中的角色
	 */
	public List<Role> findByUserId(long userId);
}
