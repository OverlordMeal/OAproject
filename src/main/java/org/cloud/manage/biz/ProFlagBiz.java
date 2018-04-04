package org.cloud.manage.biz;

import java.util.List;

import org.cloud.manage.dao.ProFlagDao;
import org.cloud.manage.model.ProFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProFlagBiz {

	
	@Autowired
	private ProFlagDao dao;

	public List<ProFlag> findByPid(Long pid) {
		// TODO Auto-generated method stub

		if (pid == null) {
			pid = ProFlag.ROOT_ID;
		}
		
		return dao.findByPid(pid);
		
		
		
	}
	
	
	
	
}
