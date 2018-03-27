package org.cloud.manage.service.impl;

import java.util.List;
import org.apache.log4j.Logger;
import org.cloud.lang.BaseUtil;
import org.cloud.manage.dao.PhyServerDao;
import org.cloud.manage.model.Link;
import org.cloud.manage.model.PhyServer;
import org.cloud.manage.model.vo.PhyServerQuery;
import org.cloud.manage.service.PhyServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
public class PhyServerServiceImpl implements PhyServerService {

	@Autowired
	private PhyServerDao dao;

	private Logger log = Logger.getLogger(PhyServerServiceImpl.class.getName());

	// 返回全部服务器列表
	@Override
	public PageList<PhyServer> getAllPhyServer() {

		PageList<PhyServer> list = dao.getAllPhyServer();
		return list;
	}

	@Override
	public PageList<PhyServer> findPage(PhyServerQuery query, PageBounds pageBounds) {
		PageList<PhyServer> list = dao.findServerPage(query, pageBounds);
		for (int i = 0; i < list.size(); i++) {
			List<Link> linkList = dao.findLinkList(list.get(i).getId());
			list.get(i).setLinkList(linkList);
		}
		return list;

	}

	@Override
	public long insert(PhyServer phyServer) {
			dao.insert(phyServer);
			List<Link> list= phyServer.getLinkList();
			for (Link link : list) {
				link.setServerId(phyServer.getId());
				dao.insertLink(link);
			}
			return phyServer.getId();
	}

	@Override
	public long deleteServer(Long id) {
		dao.deleteServerLink(id);
		
		
		return dao.deletePhyServer(id);

	}

	@Override
	public Object update(PhyServerQuery query) {
		log.error(query);
		
		dao.updateServer(query);
		List<Link> list = query.getLinkList();
		// 若无信息则直接删除全部信息 退出
		if (BaseUtil.isEmpty(list)) {
			dao.deleteServerLink(query.getId());
			return null;
		}

		// 若有信息 则删除后进行覆盖
		else {
			dao.deleteServerLink(query.getId());
			for (Link link : list) {
				link.setServerId(query.getId());
				dao.insertLink(link);
			}
			return null;
		}

	}

	@Override
	public PhyServer findById(PhyServerQuery query) {
		PhyServer bean = dao.findById(query);
		List<Link> linkList = dao.findLinkList(query.getId());
		bean.setLinkList(linkList);
		return bean;
	}

	@Override
	public PhyServer findServerById(PhyServerQuery query) {
		// TODO Auto-generated method stub
		return dao.findServerById(query);
	}

	@Override
	public List<Integer> findSwitchesId() {
		// TODO Auto-generated method stub
		return dao.findSwitchesId();
	}

	@Override
	public List<String> findSwitchesModel() {
		// TODO Auto-generated method stub
		return dao.findSwitchesModel();
	}

}
