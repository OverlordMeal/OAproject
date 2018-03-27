//package org.cloud.manage.controller;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.cloud.manage.biz.CategoryBiz;
//import org.cloud.manage.model.Category;
//import org.cloud.manage.utils.Constants;
//import org.cloud.manage.utils.HttpClientUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//
//@Controller
//@RequestMapping(value = "/flower")
//public class FlowerCategoryController {
//	
//	
//	@Autowired
//	private CategoryBiz             categoryBiz;
//	
//
//	@RequestMapping(value = "/getFlowerCategory", method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> getFlowerCategory(@RequestParam Map<String, String> data, HttpServletRequest request) throws Exception{
//		
//		String path = request.getServletContext().getRealPath("/category.html");
//		System.out.println(path);
//		
//		String ret = HttpClientUtils.doGet("http://www.51zhaohua.com/index.php");
//		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(path)));
//		bufferedWriter.write(ret);
//		bufferedWriter.close();
//		return Constants.standardControllerSuccessReturnMap;
//	}
//	
//	public static void main(String[] args) {
//		
//		System.out.println();
//	}
//
//	
//	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> addCategory(@RequestParam Map<String, String> data){
//		String jsonStr = data.get("jsonStr");
//		JSONArray jSONArray = JSON.parseArray(jsonStr);
//		for(int i = 0; i < jSONArray.size(); i++){
//			JSONObject jsonObject_1 = (JSONObject)jSONArray.get(i);
//			Category category_1 = new Category();
//			String category_1_name = jsonObject_1.getString("name");
//			JSONArray jsonArray2 = jsonObject_1.getJSONArray("children");
//			boolean flag = (jsonArray2 != null && jsonArray2.size() > 0);
//			category_1.setLevel(1);
//			category_1.setName(category_1_name);
//			category_1.setParentId(0);
//			category_1.setIsLeaf(flag ? 0 : 1);
//			long level_1_id = categoryBiz.insert(category_1);
//			
//			if (flag) {
//				for(int j = 0; j < jsonArray2.size(); j++){
//					Category category_2 = new Category();
//					JSONObject jsonObject_2 = (JSONObject)jsonArray2.get(j);
//					String category_2_name =jsonObject_2.getString("name");
//					JSONArray jsonArray3 = jsonObject_2.getJSONArray("children");
//					boolean flag_3 = (jsonArray3 != null && jsonArray3.size() > 0);
//					category_2.setLevel(2);
//					category_2.setName(category_2_name);
//					category_2.setParentId(level_1_id);
//					category_2.setIsLeaf(flag_3 ? 0 : 1);
//					long level_2_id = categoryBiz.insert(category_2);
//					
//					if (flag_3) {
//						for(int k = 0; k < jsonArray3.size(); k++){
//							Category category_3 = new Category();
//							String name = (String)jsonArray3.get(k);
//							category_3.setLevel(3);
//							category_3.setName(name);
//							category_3.setParentId(level_2_id);
//							category_3.setIsLeaf(1);
//							long level3_id = categoryBiz.insert(category_3);
//						}
//					}
//				}
//				
//			}else{
//				
//			}
//			
//		}
//		
//		return Constants.standardControllerSuccessReturnMap;
//	}
//	
//}
