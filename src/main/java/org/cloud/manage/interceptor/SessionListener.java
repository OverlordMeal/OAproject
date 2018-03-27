package org.cloud.manage.interceptor;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.cloud.lang.concurrent.ConcurrentMap;
import org.cloud.manage.utils.AuthUtil;
import org.cloud.web.session.Session;
import org.cloud.web.session.SessionProperties;

import com.alibaba.fastjson.JSON;


/**
 * Session监听器
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2014-10-08 17:21:06
 * @author 
 *		Cloud
 */
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {

	private static ConcurrentMap<String, OnlineData> loginSession = new ConcurrentMap<String, OnlineData>();
	private static ConcurrentMap<String, HttpSession> serverSession = new ConcurrentMap<String, HttpSession>();
	
	
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
	
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		
		//登出
		HttpSession session = event.getSession();
		String id = session.getId();
		loginSession.remove(id);
		serverSession.remove(id);
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		
		put(event);
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		
		HttpSession session = event.getSession();
		if (SessionProperties.CACHE_SESSION_ID.equals(event.getName())) { //登出
			String id = session.getId();
			loginSession.remove(id);
			serverSession.remove(id);
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		
		put(event);
	}
	
	public static List<OnlineData> getOnlineList() {
		
		return loginSession.getValues();
	}
	
	public static void offline(String sessionId) {
		
		serverSession.get(sessionId).invalidate();
		loginSession.remove(sessionId);
		serverSession.remove(sessionId);		
	}
	
	private void put(HttpSessionBindingEvent event) {
		
		HttpSession serverSession = event.getSession();
		String attributeKey = event.getName();
		if (SessionProperties.SERVER_SESSION.equals(attributeKey)) { 
			String json = (String)serverSession.getAttribute(attributeKey);
			Session session = JSON.parseObject(json, Session.class);
			OnlineData data = session.get(AuthUtil.getSessionFlag(), OnlineData.class);
			if (data != null) { //登录
				String id = serverSession.getId();
				loginSession.put(id, data);
				SessionListener.serverSession.put(id, serverSession);
			}
		}
	}
	
}
