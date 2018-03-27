package switches.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestExp {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "1服务端口-1交换机-3交换端口 1服务端口-2交换机-4交换端口 1服务端口-3交换机-3交换端口";
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		String[]  linkAllArr = str.split(" ");
		for (String string : linkAllArr) {
			String[] linkOneAll = string.split("\\D+");
			Map<String, String> map =new HashMap<String, String>(); 
			map.put("server_eth", linkOneAll[0]);
			map.put("switches_id", linkOneAll[1]);
			map.put("switches_eth", linkOneAll[2]);
			result.add(map);
		}
		
			
		
	}

}
