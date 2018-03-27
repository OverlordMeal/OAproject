package org.cloud.manage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.cloud.interceptor.annotation.NeedLogin;
import org.cloud.lang.BaseUtil;
import org.cloud.lang.StringUtil;
import org.cloud.lang.security.DigestAlgorithm;
import org.cloud.lang.security.DigestUtil;
import org.cloud.manage.model.Log;
import org.cloud.manage.model.User;
import org.cloud.manage.model.vo.UserQuery;
import org.cloud.manage.service.LogService;
import org.cloud.manage.service.ManageUserService;
import org.cloud.manage.utils.AuthUtil;
import org.cloud.manage.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Controller
@RequestMapping("/user")
public class ManageUserController {

	private static Logger logger = Logger.getLogger(ManageUserController.class);

	@Autowired
	private ManageUserService service;

	@Autowired
	private LogService logService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(User loginUser, HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> result = new HashMap<String, Object>();
		String autoLogin = request.getParameter("autoLogin");

		try {
			User user = service.findUserByName(loginUser.getUserName());

			if (user != null
					&& user.getPassword().equals(DigestUtil.getDigest(DigestAlgorithm.MD5, loginUser.getPassword()))) {
				AuthUtil.setUser(user, "on".equals(autoLogin));

				Log bean = new Log();
				bean.setCreateDate(new Date());
				bean.setIp(BaseUtil.getIp(request));
				bean.setRemark("登录成功");
				bean.setType(Log.TYPE_LOGIN);
				bean.setUserId(user.getId());
				bean.setUserName(user.getUserName());

				logService.add(bean);

				result.put("redirectURL", Constants.SYSTEM_PATH + "/index.html");
				result.put("result", Constants.RESULT_FLAG_SUCCESS);
				result.put("userName", user.getRealName());
			} else {
				Log bean = new Log();
				bean.setCreateDate(new Date());
				bean.setIp(BaseUtil.getIp(request));
				bean.setRemark("登录失败");
				bean.setType(Log.TYPE_LOGIN);
				bean.setUserName(loginUser.getUserName());

				logService.add(bean);

				result.put("result", Constants.RESULT_FLAG_FAILURE);
			}

		} catch (Exception e) {
			logger.error("account login error...", e);
			result.put("result", Constants.RESULT_FLAG_FAILURE);
		}
		return result;
	}

	@RequestMapping(value = "/logout")
	@ResponseBody
	@NeedLogin
	public Map<String, Object> logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();

		AuthUtil.removeUser();

		result.put("redirectURL", Constants.SYSTEM_PATH);

		return result;
	}

	@RequestMapping(value = "/changePassword")
	@ResponseBody
	@NeedLogin
	public Map<String, Object> changePassword(@RequestParam Map<String, String> data, HttpServletRequest request)
			throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();
		String orgPassword = data.get("orgPassword");
		String newPassword = data.get("newPassword");
		String newPasswordAgain = data.get("newPasswordAgain");

		if (!newPassword.equals(newPasswordAgain)) {
			throw new RuntimeException("两次密码不一致");
		}

		result.put("success", service.changePassword(AuthUtil.getUserId(), orgPassword, newPassword));

		return result;
	}

	@RequestMapping(value = "/getUserInfo")
	@ResponseBody
	@NeedLogin
	public Map<String, Object> getUserInfo(@RequestParam Map<String, String> data, HttpServletRequest request)
			throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("user", service.findUserById(AuthUtil.getUserId()));

		return result;
	}

	@RequestMapping(value = "/findPage")
	@ResponseBody
	@NeedLogin
	public Map<String, Object> findPage(@RequestParam Map<String, String> data, HttpServletRequest request)
			throws Exception {

		PageBounds pageBounds = new PageBounds();
		pageBounds.setContainsTotalCount(true);
		pageBounds.setPage(Integer.parseInt(data.get("page")));
		pageBounds.setLimit(Integer.parseInt(data.get("rows")));

		UserQuery query = new UserQuery();
		query.setRealName(data.get("realName"));
		query.setUserName(data.get("userName"));
		query.setOrgId(BaseUtil.isEmpty(data.get("orgId")) ? null : Long.parseLong(data.get("orgId")));

		PageList<User> page = service.findPage(query, pageBounds);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rows", page);
		result.put("total", page.getPaginator().getTotalCount());

		return result;
	}

	@RequestMapping(value = "/add")
	@ResponseBody
	@NeedLogin
	public Map<String, Object> add(@RequestParam Map<String, String> data, HttpServletRequest request)
			throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();

		User user = new User();
		user.setRealName(data.get("realName"));
		user.setUserName(data.get("userName"));
		user.setOrgId(Long.parseLong(data.get("orgId")));
		user.setPassword(Constants.PASSWORD);

		service.add(user);
		result.put("result", true);
		return result;
	}

	@RequestMapping(value = "/findById")
	@ResponseBody
	@NeedLogin
	public Map<String, Object> findById(@RequestParam Map<String, String> data, HttpServletRequest request)
			throws Exception {

		Long userId = Long.parseLong(data.get("id"));
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("bean", service.findUserById(userId));
		result.put("result", "success");

		return result;
	}

	@RequestMapping(value = "/update")
	@ResponseBody
	@NeedLogin
	public Map<String, Object> update(@RequestParam Map<String, String> data, HttpServletRequest request)
			throws Exception {

		User user = new User();
		user.setId(Long.parseLong(data.get("id")));
		user.setRealName(data.get("realName"));
		user.setUserName(data.get("userName"));
		user.setOrgId(Long.parseLong(data.get("orgId")));

		service.update(user);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> delete(@RequestParam Map<String, String> data, HttpServletRequest request)
			throws Exception {

		Long id = Long.parseLong(data.get("userId"));
		service.delete(id);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "success");

		return result;
	}

	@RequestMapping(value = "/changeUserRole", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> changeUserRole(@RequestParam Map<String, String> data, HttpServletRequest request) {

		List<Long> roleIds = BaseUtil.isEmpty(data.get("roleIds")) ? null
				: StringUtil.toLongList(data.get("roleIds").split(","));

		service.changeUserRole(Long.parseLong(data.get("userId")), roleIds);

		return Constants.standardControllerSuccessReturnMap;
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> resetPassword(@RequestParam Map<String, String> data, HttpServletRequest request) {

		service.resetPassword(Long.parseLong(data.get("userId")));

		return Constants.standardControllerSuccessReturnMap;
	}

}
