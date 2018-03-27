package org.cloud.manage.utils;

import java.util.HashMap;
import java.util.Map;


/**
 * 基本常量类
 * @author 
 *
 */
public class Constants {
	
	/**
     * 是否使用缓存
     */
    public static final String USE_CACHE = "system.useCache";
	
    /**
     * 项目名
     */
	public static String SYSTEM_PATH = null;
	
	/**
	 * 项目文件路径
	 */
	public static String ROOT_PATH = null;
	
	/**
	 * 系统返回至前台成功失败标志
	 */
    public static final boolean RESULT_FLAG_SUCCESS = true;
    public static final boolean RESULT_FLAG_FAILURE = false;
    
    /**
     * 默认初始密码
     */
    public static final String PASSWORD = "e10adc3949ba59abbe56e057f20f883e";
    
    /**
     * 上传文件存放路径
     */
    public static final String UPLOAD_FILE_PATH = "/upload";
    
    /**
     * 登录拦截器跳转地址名称
     */
    public static final String NEED_LOGIN_REDIRECT_URL_NAME = "NEED_LOGIN_REDIRECT_URL";
    
    /**
     * 登录拦截器跳转页面名称
     */
    public static final String NEED_LOGIN_REDIRECT_PAGE_NAME = "NEED_LOGIN_REDIRECT_PAGE";
    
    /**
     * 操作成功
     */
    public static final String OPERATOR_SUCCESS = "success";

    /**
     * 操作消息
     */
    public static final String OPERATOR_MESSAGE = "message";
    
    /**
     * 标准Controller返回成功Map数据
     */
    public static final Map<String, Object> standardControllerSuccessReturnMap;
    
    static {
        standardControllerSuccessReturnMap = new HashMap<String, Object>();
        standardControllerSuccessReturnMap.put(Constants.OPERATOR_SUCCESS, true);
    }
    
}
