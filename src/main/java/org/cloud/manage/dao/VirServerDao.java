package org.cloud.manage.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cloud.manage.model.VirServer;
import org.cloud.manage.model.vo.VirServerQuery;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface VirServerDao {

	
	//分页返回结果
	public PageList<VirServer> findPage(@Param("query") VirServerQuery query, PageBounds pageBounds); 

	//返回全部物理服务器id 数组
	public List<Long> findPhyServerId();

	//插入虚拟服务器对象
	public Long insert(VirServer virServer);

	//根据id查找vir对象
	public VirServer findById(Long id);

	//更新数据
	public Long update(VirServer virServer);

	//根据id 删除信息
	public Long delete(Long id);

	
	//查找出所以的服务器名字
	public List<String> findPhyServerName();
	
	
	//查找出所有的服务器IP信息
	public List<String> findPhyServerIp();
	
	
	
	//查找出所有的服务器IP信息
	public List<String> findPhyServer();
}
