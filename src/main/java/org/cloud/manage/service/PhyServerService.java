package org.cloud.manage.service;


import java.util.List;

import org.cloud.manage.model.PhyServer;
import org.cloud.manage.model.vo.PhyServerQuery;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface PhyServerService {
	
	/**
	 * 
	 *分页查询服务器结果
	 * @param query
	 * @param pageBounds
	 * @return	分页结果。
	 */
	public PageList<PhyServer> findPage(PhyServerQuery query, PageBounds pageBounds);
	
	/**
	 * 增加物理服务器信息
	 */
	public long insert(PhyServer phyServer);
	
	/**
	 * 删除物理服务器信息
	 */
	public long deleteServer(Long id);
	

	/**
	 * 修改物理服务器信息
	 */
	public void update(PhyServerQuery query);
	

	/**
	 * 根据查物理服务器信息
	 */
	public PhyServer findById(PhyServerQuery query);
	
	public PhyServer findServerById(PhyServerQuery query);
	
	//返回全部物理服务器列表
	public  PageList<PhyServer> getAllPhyServer();

	
	
			
	/**
	 * 返回交换机id列表
	 */
	
	public List<Integer> findSwitchesId();
	/**
	 * 返回交换机name列表
	 */
	public List<String> findSwitchesModel();
	
}
