package org.cloud.manage.biz;

import java.util.List;

import org.cloud.lang.BaseUtil;
import org.cloud.manage.dao.RoleDao;
import org.cloud.manage.model.Role;
import org.cloud.manage.model.vo.RoleQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


/**
 * 角色Biz
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2014-10-08 17:40:51
 * @author 
 *		Cloud
 */
@Component
public class RoleBiz {

	@Autowired
	private RoleDao dao;
	
	/**
	 * 根据条件查找记录
	 * @param query
	 * @return
	 */
	public List<Role> findList(RoleQuery query) {
		
		return dao.findList(query);
	}
	
	/**
	 * 查找单页记录
	 * @param query
	 * @return
	 */
	public PageList<Role> findPage(RoleQuery query, PageBounds pageBounds) {
		
		return dao.findPage(query, pageBounds);
	}
	
	/**
	 * 增加权限
	 * @param bean
	 * @return
	 */
	public long add(Role bean) {
		
		dao.add(bean);
		
		return bean.getId();
	}

	public void changeRolePriv(Long roleId, List<Long> privIds) {
		
		dao.removeRolePrivByRoleId(roleId);
		
		if (BaseUtil.isNotEmpty(privIds)) {
			dao.addRolePriv(roleId, privIds);
		}
	}
	
	public void remove(Long roleId) {
		
		dao.remove(roleId);
		dao.removeRolePrivByRoleId(roleId);
	}
	
	public Role findById(Long id) {
		return dao.findById(id);
	}
	
	public void update(Role role) {
		
		dao.update(role);
	}
	
	/**
	 * 获取权限选中的角色
	 */
	public List<Role> findByUserId(Long userId) {
		
		return dao.findByUserId(userId);
	}
}
