package switches.test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.cloud.manage.utils.JsonUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;

public class TestJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String test = "0&192.168.11.1,1&10.0.0.1";
		String[] ipTypeStrs = test.split(",");
		JSONArray jsArr = new JSONArray();
		
		for (int i = 0; i < ipTypeStrs.length; i++) {
			String[] ipTypeStr = ipTypeStrs[i].split("&");
			JSONObject jsObj = new JSONObject();
			jsObj.put("netType", ipTypeStr[0]);
			jsObj.put("ipAddress", ipTypeStr[1]);
			jsArr.add(jsObj);
		}
		
		System.out.println(jsArr.toJSONString());
		
		
		
		
		
		
		
		
	}
	
	

}





