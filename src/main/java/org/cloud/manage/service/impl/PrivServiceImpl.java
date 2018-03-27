package org.cloud.manage.service.impl;

import java.util.List;

import org.cloud.lang.BaseUtil;
import org.cloud.manage.dao.PrivDao;
import org.cloud.manage.model.Priv;
import org.cloud.manage.model.vo.PrivQuery;
import org.cloud.manage.service.PrivService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


/**
 * 权限Service 实现
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2014-10-08 17:38:10
 * @author 
 *		Cloud
 */
@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class PrivServiceImpl implements PrivService {

	@Autowired
	private PrivDao dao;
	
	@Override
	public PageList<Priv> findPage(PrivQuery query, PageBounds pageBounds) {

		return dao.findPage(query, pageBounds);
	}
	
	@Override
	public List<Priv> findAll() {
		
		return dao.findAll();
	}

	@Override
	public long add(Priv bean) {
		
		dao.add(bean);
		return bean.getId();
	}

	@Override
	public Priv findById(long id) {
		
		return dao.findById(id);
	}

	@Override
	public void update(Priv bean) {
		
		dao.update(bean);
	}

	@Override
	public void delete(long id) {
		
		dao.delete(id);
		dao.deletePrivMenuByPrivId(id);
	}

	@Override
	public void changePrivMenu(long privId, List<Long> menuIds) {
		
		dao.deletePrivMenuByPrivId(privId);
		
		if (BaseUtil.isNotEmpty(menuIds)) {
			dao.addPrivMenu(privId, menuIds);
		}
	}
	
	@Override
	public List<Priv> findByRoleId(long roleId) {
		
		return dao.findByRoleId(roleId);
	}
	
}
