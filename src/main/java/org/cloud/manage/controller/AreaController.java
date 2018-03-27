//package org.cloud.manage.controller;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.cloud.lang.BaseUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.zghh.web.dataobject.User;
//import com.zghh.web.service.AreaService;
//import com.zghh.web.service.UserService;
//
//
///**
// * 区域 Controller
// * 
// * @since v1.0
// * @version v1.0, 2014-12-26 17:53:36
// * @author Cloud
// */
//@Controller
//@RequestMapping("/area")
//public class AreaController {
//
//    @Autowired
//    private AreaService service;
//    
//    @Autowired
//    private UserService userService;
//
//    /**
//     * 获取洲
//     */
//    @RequestMapping(value = "/findContinent", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> findContinent(@RequestParam Map<String, String> data, HttpServletResponse response) {
//
//        Map<String, Object> map = new HashMap<String, Object>();
//        System.out.println(service);
//        map.put("list", service.findContinent());
//
//       
//        
//        return map;
//    }
//
//    /**
//     * 获取国家
//     */
//    @RequestMapping(value = "/findCountry", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> findCountry(@RequestParam Map<String, String> data, HttpServletResponse response) {
//
//        Map<String, Object> map = new HashMap<String, Object>();
//
//        String parentId = data.get("parentId");
//        if (BaseUtil.isEmpty(parentId)) {
//            map.put("list", service.findCountryAll());
//        } else {
//            map.put("list", service.findCountry(Long.parseLong(parentId)));
//        }
//
//        return map;
//    }
//
//    /**
//     * 获取省
//     */
//    @RequestMapping(value = "/findProvince", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> findProvince(@RequestParam Map<String, String> data, HttpServletResponse response) {
//
//        Map<String, Object> map = new HashMap<String, Object>();
//
//        map.put("list", service.findProvince(Long.parseLong(data.get("parentId"))));
//
//        return map;
//    }
//
//    /**
//     * 获取市
//     */
//    @RequestMapping(value = "/findCity", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> findCity(@RequestParam Map<String, String> data, HttpServletResponse response) {
//
//        Map<String, Object> map = new HashMap<String, Object>();
//
//        map.put("list", service.findCity(Long.parseLong(data.get("parentId"))));
//
//        return map;
//    }
//
//    /**
//     * 获取乡镇
//     */
//    @RequestMapping(value = "/findTown", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> findTown(@RequestParam Map<String, String> data, HttpServletResponse response) {
//
//        Map<String, Object> map = new HashMap<String, Object>();
//
//        User user = userService.findById(1L);
//        
//        map.put("list", service.findTown(Long.parseLong(data.get("parentId"))));
//
//        return map;
//    }
//}
