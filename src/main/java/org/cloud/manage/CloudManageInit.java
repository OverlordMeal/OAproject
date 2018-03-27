package org.cloud.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;

import org.cloud.cache.CacheClient;
import org.cloud.cache.CacheParameter;
import org.cloud.interceptor.def.NeedLoginDefault;
import org.cloud.lang.LangProperties;
import org.cloud.lang.file.FileUtil;
import org.cloud.lang.init.InitAware;
import org.cloud.lang.init.InitParam;
import org.cloud.manage.constant.SystemDataCache;
import org.cloud.manage.model.PhyServer;
import org.cloud.manage.model.Switches;
import org.cloud.manage.model.User;
import org.cloud.manage.service.ManageUserService;
import org.cloud.manage.service.PhyServerService;
import org.cloud.manage.service.SwitchesManageService;
import org.cloud.manage.utils.Constants;
import org.cloud.spring.BeanFactory;
import org.cloud.web.session.SessionProperties;
import org.cloud.web.session.service.AutoLoginService;

import net.rubyeye.xmemcached.MemcachedClient;

@InitParam(path="/config.properties")
public class CloudManageInit implements InitAware {

	private ManageUserService userService;
	
	
	private Logger log = Logger.getLogger(CloudManageInit.class.getName());
	
	@Override
	public void init(Map<String, Object> paramMap) throws Exception {
		
		
		
		ServletConfig sc = (ServletConfig) paramMap.get(LangProperties.SEVLET_CONFIG_NAME);
		
		//获取自动登录Service
		SessionProperties.setAutoLoginService(BeanFactory.getBean(AutoLoginService.class));
		
		//获取系统路径
		Constants.ROOT_PATH = FileUtil.formatPath(sc.getServletContext().getRealPath("/"));
		Constants.SYSTEM_PATH = sc.getServletContext().getContextPath();
		//获取登录验证失败跳转地址
		NeedLoginDefault.redirectUrl = (String)paramMap.get(Constants.NEED_LOGIN_REDIRECT_URL_NAME);
		NeedLoginDefault.redirectPage = (String)paramMap.get(Constants.NEED_LOGIN_REDIRECT_PAGE_NAME);
		
		// 缓存设置
		CacheParameter.setUseCache(new Boolean(paramMap.get(Constants.USE_CACHE).toString()));
	    if (CacheParameter.isUseCache()) {
	        CacheClient.setClient(BeanFactory.getBean("memcachedClient", MemcachedClient.class));
	    }
	    
	    
	    
	    
    	//处理后台管理系统用户(操作人)的缓存，缓存map的键是用户的id，值是用户的名字
  		userService =  BeanFactory.getBean("userService",ManageUserService.class);
  		List<User> userList = userService.getAllUser();
  		Map<Long, String> userMap = new HashMap<Long, String>();
  		for (User user : userList) {
  			userMap.put(user.getId(), user.getRealName());
  		}
  		SystemDataCache.userMap = userMap;
	}

	@Override
	public void destroy(Map<String, Object> paramMap) throws Exception {
		System.out.println("====CloudManageInit.destroy()====");
		
	}
	
}
