package org.cloud.manage.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.cloud.lang.BaseUtil;
import org.cloud.manage.interceptor.OnlineData;
import org.cloud.manage.model.Link;
import org.cloud.manage.model.ProFlag;
import org.cloud.manage.model.User;
import org.cloud.web.session.Session;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public class AuthUtil {

	public static Long getUserId() {
		return Session.getCurrent().getUserId();
	}
	
	public static void setUser(User user, boolean remember) {

		Session session = Session.getCurrent();
		HttpServletRequest request = session.getRequest();
		String ip = BaseUtil.getIp(request);
		HttpSession httpSession = request.getSession();
		OnlineData data = new OnlineData();
		data.setCreateDate(new Date());
		data.setIp(ip);
		data.setSessionId(httpSession.getId());
		data.setUser(user);
		data.setRemember(remember);
		
		
		session.saveUser(user.getId());
		session.put(getSessionFlag(), data);
		
		if (remember) {
			session.remember();
		}
	}
	
	public static void removeUser() {
		
		Session.getCurrent().remove();
	}
	
	public static String getSessionFlag() {
		
		return "onlineData";
	}
	
	
	
	/**
	 * 将link字符串转为为map的集合
	 * @param str
	 * @return
	 */
	
	public  static List<Link> linkChangeList(String str){
		List<Link> list = new ArrayList<Link>();
		String[] linkStr = str.split(",");
		for (int i = 0; i < linkStr.length; i++) {
			String[] linkAttr = linkStr[i].split("&");
			if(linkAttr.length<3){
				return null;
			}
			Link link =new Link();
			link.setServerEth(Integer.parseInt(linkAttr[0]));
			link.setSwitchesId(Long.parseLong(linkAttr[1]));	
			link.setSwitchesEth(Integer.parseInt(linkAttr[2]));	
			list.add(link);
		}
		return list;
		
	}
	
	/**
	 * 将link字符串转为为map的集合
	 * @param str
	 * @return
	 */
	
	public  static List<ProFlag> flagChangeList(String str){
		return null;
	}
	
	
	
	
	/**
	 * 将网页上的ip信息字符串转换为json格式
	 * @author chen 
	 * @return
	 * 
	 */
	
	public static String strToIpJson(String app){
		
		if(BaseUtil.isEmpty(app)){
			return null;
		}
		
		String[] ipTypeStrs = app.split(",");
		JSONArray jsArr = new JSONArray();
		
		for (int i = 0; i < ipTypeStrs.length; i++) {
			String[] ipTypeStr = ipTypeStrs[i].split("&");
			JSONObject jsObj = new JSONObject();
			jsObj.put("netType", ipTypeStr[0]);
			jsObj.put("ipAddress", ipTypeStr[1]);
			jsArr.add(jsObj);
		}
		return jsArr.toJSONString();
	}
	
	
	
	/**
	 * 将网页上的app信息字符串转换为json格式
	 * @author chen 
	 * @return
	 * 
	 */
	
	public static String strToAppJson(String app){
		if(BaseUtil.isEmpty(app)){
			return null;
		}
		String[] appTypeStrs = app.split(",");
		JSONArray jsArr = new JSONArray();
		for (int i = 0; i < appTypeStrs.length; i++) {
			String[] appTypeStr = appTypeStrs[i].split("&");
			JSONObject jsObj = new JSONObject();
			jsObj.put("appType", appTypeStr[0]);
			jsObj.put("address", appTypeStr[1]);
			jsArr.add(jsObj);
		}
		return jsArr.toJSONString();
	}
	
	
	
	
	
	
	
	
	
	
	

}
