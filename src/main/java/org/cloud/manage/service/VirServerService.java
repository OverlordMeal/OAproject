package org.cloud.manage.service;



import java.util.List;

import org.cloud.manage.model.VirServer;
import org.cloud.manage.model.vo.VirServerQuery;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface VirServerService {
	
	/**
	 * 
	 *分页查询服务器结果
	 * @param query
	 * @param pageBounds
	 * @return	分页结果。
	 */
	public PageList<VirServer> findPage(VirServerQuery query, PageBounds pageBounds);
	
	
	//返回全部物理计算机id
	public List<Long> findPhyServerId();


	//插入虚拟服务器对象
	public Long insert(VirServer bean);


	//根据id查找虚拟服务器信息
	public VirServer findById(Long id);


	//更新数据库虚拟服务器信息
	public Long update(VirServer bean);

	//根据id删除信息
	public Long delete(Long id);

	//遍历全部服务器名字
	public List<String> findPhyServerName();


	public List<String> findPhyServerIp();
	
}
