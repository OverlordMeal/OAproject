package org.cloud.manage.service.impl;


import java.util.List;

import org.cloud.manage.dao.SwitchesDao;
import org.cloud.manage.model.Switches;
import org.cloud.manage.model.vo.SwitchesQuery;
import org.cloud.manage.service.SwitchesManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class SwitchesManageServiceImpl implements SwitchesManageService {
	@Autowired
	private SwitchesDao dao;

	@Override
	public PageList<Switches> findPage(SwitchesQuery query, PageBounds pageBounds) {
		// TODO Auto-generated method stub
		return dao.findPage(query, pageBounds);
	}

	@Override
	public void insert(Switches switches) {
		// TODO Auto-generated method stub
		dao.insert(switches);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public void update(Switches switches) {
		// TODO Auto-generated method stub
		 dao.update(switches);
	}

	@Override
	public Switches findById(Integer Id) {
		// TODO Auto-generated method stub
		return dao.findById(Id);
	}

	
	
	/**
	 * @return 返回所有交换机信息
	 */
	@Override
	public List<Switches> getAllSwitches() {
		// TODO Auto-generated method stub
		return dao.getAllSwitches();
	}

	
	
	
	
	
}
