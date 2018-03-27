package org.cloud.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cloud.manage.model.Priv;
import org.cloud.manage.model.vo.PrivQuery;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


/**
 * 权限Dao
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2014-10-08 17:12:25
 * @author 
 *		Cloud
 */
public interface PrivDao {
	
	/**
	 * 获取权限信息
	 */
	public PageList<Priv> findPage(@Param("query")PrivQuery query, PageBounds pageBounds);
	
	/**
	 * 获取权限信息(全部)
	 */
	public List<Priv> findAll();

	/**
	 * 增加
	 */
	public void add(Priv bean);

	/**
	 * 根据主键,获取信息
	 */
	public Priv findById(long id);

	/**
	 * 更新
	 */
	public void update(Priv bean);
	
	/**
	 * 删除
	 */
	public void delete(long id);
	
	/**
	 * 添加权限菜单关联
	 */
	public void addPrivMenu(@Param("privId")Long privId, @Param("menuIds")List<Long> menuIds);
	
	/**
	 * 根据权限Id删除权限菜单关联
	 */
	public void deletePrivMenuByPrivId(long privId);
	
	/**
	 * 根据菜单Id删除权限菜单关联
	 */
	public void deletePrivMenuByMenuId(long menuId);
	
	/**
	 * 获取权限选中的权限
	 */
	public List<Priv> findByRoleId(long roleId);

}
