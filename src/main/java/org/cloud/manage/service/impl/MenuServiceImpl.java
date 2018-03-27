package org.cloud.manage.service.impl;

import java.util.List;

import org.cloud.lang.BaseUtil;
import org.cloud.manage.biz.MenuBiz;
import org.cloud.manage.dao.MenuDao;
import org.cloud.manage.dao.PrivDao;
import org.cloud.manage.model.Menu;
import org.cloud.manage.model.vo.MenuQuery;
import org.cloud.manage.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


/**
 * 菜单Service 实现
 * 
 * @since v1.0
 * @version v1.0, 2013-07-29 16:18:04
 * @author Cloud
 */
@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class MenuServiceImpl  implements MenuService {

	@Autowired
	private MenuBiz biz;
	
	@Autowired
	private MenuDao dao;
	
	@Autowired
	private PrivDao privDao;

	@Override
	public PageList<Menu> findPage(MenuQuery query, PageBounds pageBounds) {

		return dao.findPage(query, pageBounds);
	}

	@Override
	public long insert(Menu menu) {
		
		dao.insert(menu);
		return menu.getId();
	}

	@Override
	public Menu findById(long id) {
		
		return dao.findById(id);
	}

	@Override
	public void update(Menu menu) {
		
		dao.update(menu);
	}

	@Override
	public void delete(long id) {
		
		List<Menu> list = dao.findByPid(id);
		if (BaseUtil.isNotEmpty(list)) {
			for (Menu bean : list) {
				delete(bean.getId());
			}
		}
		dao.delete(id);
		privDao.deletePrivMenuByMenuId(id);
	}

	@Override
	public List<Menu> findByPid(Long pid) {

		return biz.findByPid(pid);
	}

	@Override
	public List<Menu> findByPidAndUserId(Long pid, long userId) {

		return biz.findByPidAndUserId(pid, userId);
	}

	@Override
	public List<Menu> findByPrivId(long privId) {
		
		return dao.findByPrivId(privId);
	}

}
