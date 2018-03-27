package org.cloud.manage.service;

import java.util.List;

import org.cloud.manage.model.Priv;
import org.cloud.manage.model.vo.PrivQuery;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


/**
 * 权限Service
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2014-10-08 17:37:52
 * @author 
 *		Cloud
 */
public interface PrivService {
	
	/**
	 * 获取权限信息
	 */
	public PageList<Priv> findPage(PrivQuery query, PageBounds pageBounds);
	
	/**
	 * 获取权限信息(全部)
	 */
	public List<Priv> findAll();

	/**
	 * 增加
	 */
	public long add(Priv bean);

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
	 * 修改权限
	 */
	public void changePrivMenu(long privId, List<Long> menuIds);
	
	/**
	 * 获取权限选中的权限
	 */
	public List<Priv> findByRoleId(long roleId);

}
