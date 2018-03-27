  package org.cloud.manage.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cloud.manage.model.Link;
import org.cloud.manage.model.PhyServer;
import org.cloud.manage.model.vo.PhyServerQuery;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


public interface PhyServerDao {
	

	/**
	 * 
	 *分页查询服务器结果
	 * @param query
	 * @param pageBounds
	 * @return	分页结果。
	 */
	public PageList<PhyServer> findPage(@Param("query") PhyServerQuery query, PageBounds pageBounds);
	
	/**
	 * 增加物理服务器信息
	 */
	public Long insert(PhyServer phyServer);
	
	/**
	 * 删除物理服务器信息
	 */
	public Long deletePhyServer(Long id);
	
	public Long deleteServerLink(@Param("serverId")Long serverId);
	

	/**
	 * 修改物理服务器信息
	 */
	public Long updateServer(PhyServerQuery query);
	
	
	public Long updateLink(List list);
	

	/**
	 * 根据查物理服务器信息
	 */
	public PhyServer findById(PhyServerQuery query);
	
	
	public PhyServer findServerById(PhyServerQuery query);
	
		//插入连接表
	public Long insertLink(Link Link);
	
	/**
	获取所有物理服务器
	 */
	public PageList<PhyServer> getAllPhyServer();
	
	
	
	public List<Link> findLinkList(Long serverId); 
	
	public PageList<PhyServer> findServerPage(@Param("query") PhyServerQuery query, PageBounds pageBounds); 



	/**
	 * 查找所有的交换机ID
	 * 
	 * 返回交换机集合
	 */
	
	public List<Integer> findSwitchesId();

	public List<String> findSwitchesModel();


}
