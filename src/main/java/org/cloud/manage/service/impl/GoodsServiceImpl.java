package org.cloud.manage.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.cloud.manage.dao.GoodsDao;
import org.cloud.manage.dao.VirServerDao;
import org.cloud.manage.model.Goods;
import org.cloud.manage.model.VirServer;
import org.cloud.manage.model.vo.GoodsQuery;
import org.cloud.manage.model.vo.VirServerQuery;
import org.cloud.manage.service.GoodsService;
import org.cloud.manage.service.VirServerService;
import org.cloud.manage.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsDao dao;

	private Logger log = Logger.getLogger(GoodsServiceImpl.class.getName());

	@Override
	public PageList<Goods> findPage(GoodsQuery query, PageBounds pageBounds) {
		// TODO Auto-generated method stub
		return dao.findPage(query, pageBounds);
	}

	

}
