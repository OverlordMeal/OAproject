package org.cloud.manage.service.impl;

import java.util.List;

import org.cloud.lang.BaseUtil;
import org.cloud.lang.security.DigestAlgorithm;
import org.cloud.lang.security.DigestUtil;
import org.cloud.manage.constant.SystemDataCache;
import org.cloud.manage.dao.ManageUserDao;
import org.cloud.manage.model.User;
import org.cloud.manage.model.vo.UserQuery;
import org.cloud.manage.service.ManageUserService;
import org.cloud.manage.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


/**
 * 用户Service 实现
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2014-10-08 17:24:20
 * @author 
 *		Cloud
 */
@Service("userService")
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class ManageUserServiceImpl implements ManageUserService  {

	@Autowired
	private ManageUserDao dao;
	
	@Override
	public long add(User user) {

		dao.add(user);
		SystemDataCache.userMap.put(user.getId(), user.getRealName());
		return user.getId();
	}
	
	@Override
	public User findUserByName(String userName) {
		
		return dao.findUserByName(userName);
	}
	
	@Override
	public boolean changePassword(long id, String orgPassword, String newPassword) {
		
		String passwordDb = dao.getPassword(id);
		if (DigestUtil.getDigest(DigestAlgorithm.MD5, orgPassword).equals(passwordDb)) {
			dao.changePassword(DigestUtil.getDigest(DigestAlgorithm.MD5, newPassword), id);
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public PageList<User> findPage(UserQuery query, PageBounds pageBounds) {
		
		return dao.findPage(query, pageBounds);
	}
	
	@Override
	public User findUserById(long id) {
		
		return dao.findUserById(id);
	}
	
	@Override
	public void update(User bean) {
	
		dao.update(bean);
		SystemDataCache.userMap.put(bean.getId(), bean.getRealName());
	}
	
	@Override
	public void delete(long id) {
		
		dao.delete(id);
		dao.removeUserRoleByUserId(id);
		SystemDataCache.userMap.remove(id);
	}
	
	@Override
	public void changeUserRole(long userId, List<Long> roleIds) {
		
		dao.removeUserRoleByUserId(userId);
		
		if (BaseUtil.isNotEmpty(roleIds)) {
			dao.addUserRole(userId, roleIds);
		}
	}
	
	@Override
	public void resetPassword(long userId) {
		
		dao.changePassword(Constants.PASSWORD, userId);
	}

	@Override
	public List<User> getAllUser() {
		
		return dao.getAllUser();
	}
}
