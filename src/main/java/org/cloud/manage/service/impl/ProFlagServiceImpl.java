package org.cloud.manage.service.impl;

import java.util.List;

import org.cloud.manage.biz.MenuBiz;
import org.cloud.manage.biz.ProFlagBiz;
import org.cloud.manage.controller.PhyServerController;
import org.cloud.manage.dao.ProFlagDao;
import org.cloud.manage.model.ProFlag;
import org.cloud.manage.model.vo.ProFlagQuery;
import org.cloud.manage.service.ProFlagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.sun.istack.internal.logging.Logger;


@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class ProFlagServiceImpl implements ProFlagService {
	
	@Autowired
	private ProFlagDao dao;
	
	
	@Autowired
	private ProFlagBiz biz;
	

	@Override
	public PageList<ProFlag> findPage(ProFlagQuery query, PageBounds pageBounds) {
		return dao.findPage(query, pageBounds);
	}

	@Override
	public List<ProFlag> findByPid(Long pid) {
		
		return biz.findByPid(pid);
	}

	@Override
	public Long insert(ProFlag bean) {
		// TODO Auto-generated method stub
		return dao.insert(bean);
	}

	@Override
	public ProFlag findByTagId(Long id) {
		// TODO Auto-generated method stub
		return dao.findByTagId(id);
	}

	@Override
	public Long deleteById(long id) {
		// TODO Auto-generated method stub
		return dao.deleteById(id);
	}

	@Override
	public Long updateByTagId(ProFlag bean) {
		// TODO Auto-generated method stub
		return dao.updateByTagId(bean);
	}

}
