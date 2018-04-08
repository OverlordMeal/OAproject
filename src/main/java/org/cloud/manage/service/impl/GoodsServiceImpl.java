package org.cloud.manage.service.impl;


import org.cloud.manage.dao.GoodsDao;
import org.cloud.manage.model.Goods;
import org.cloud.manage.model.vo.GoodsQuery;
import org.cloud.manage.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsDao dao;


	@Override
	public PageList<Goods> findPage(GoodsQuery query, PageBounds pageBounds) {
		// TODO Auto-generated method stub
		return dao.findPage(query, pageBounds);
	}

	

}
