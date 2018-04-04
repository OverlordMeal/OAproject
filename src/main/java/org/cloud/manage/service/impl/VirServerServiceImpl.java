package org.cloud.manage.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.cloud.lang.BaseUtil;
import org.cloud.manage.dao.VirServerDao;
import org.cloud.manage.model.VirServer;
import org.cloud.manage.model.vo.VirServerQuery;
import org.cloud.manage.service.VirServerService;
import org.cloud.manage.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
public class VirServerServiceImpl implements VirServerService {

	@Autowired
	private VirServerDao dao;

	private Logger log = Logger.getLogger(VirServerServiceImpl.class.getName());

	@Override
	public PageList<VirServer> findPage(VirServerQuery query, PageBounds pageBounds) {
		return dao.findPage(query, pageBounds);
	}


	@Override
	public List<Long> findPhyServerId() {
		return dao.findPhyServerId();
	}


	@Override
	public Long insert(VirServer bean) {
		if(!BaseUtil.isEmpty(bean.getFlag())){
			String str = bean.getFlag();
			String flag = ","+str+",";
			bean.setFlag(flag);
		}
		bean.setIpType(AuthUtil.strToIpJson(bean.getIpType()));
		bean.setApp(AuthUtil.strToAppJson(bean.getApp()));
		return dao.insert(bean);
	}


	@Override
	public VirServer findById(Long id) {
		return dao.findById(id);
	}


	@Override
	public Long update(VirServer bean) {
		if(!BaseUtil.isEmpty(bean.getFlag())){
			String str = bean.getFlag();
			String flag = ","+str+",";
			bean.setFlag(flag);
		}
		bean.setIpType(AuthUtil.strToIpJson(bean.getIpType()));
		bean.setApp(AuthUtil.strToAppJson(bean.getApp()));
		dao.update(bean);
		return null;
	}


	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return dao.delete(id);
	}

	
	
	// 返回全部服务器列表
	@Override
	public List<String> findPhyServerName() {
		// TODO Auto-generated method stub
		return dao.findPhyServerName();
	}


	@Override
	public List<String> findPhyServerIp() {
		// TODO Auto-generated method stub
		return dao.findPhyServerIp();
	}

	

}
