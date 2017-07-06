package com.ry.common.base;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ry.module.platform.entity.User;
import com.ry.shiro.ShiroUtil;


public class BaseController extends Base {

	/**
	 * 得到request对象
	 */
	protected HttpServletRequest getRequest() {
		return ((ServletRequestAttributes)  RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * 得到response对象
	 */
	protected HttpServletResponse getResponse() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	}

	/**
	 * 把request值封装成Map
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Map<String, Object> requestParamsToMap(HttpServletRequest request) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		Map<String, String[]> tmp = request.getParameterMap();
		if (tmp != null) {
			for (String key : tmp.keySet()) {
				String[] values = tmp.get(key);
				String value = StringUtils.isEmpty(values[0].trim()) ? "" : values[0].trim();
				paraMap.put(key, makeRequestParamValue(key, value));
			}
		}
		return paraMap;
	}

	/**
	 * 根据key重新构建request参数值，默认不做逻辑处理，返回原值<br>
	 * 如遇特殊情况需对request请求参数特殊处理，可在子类中重写些方法
	 * 
	 * @param key
	 *            request参数key
	 * @param value
	 *            request参数value
	 * @return String
	 */
	protected String makeRequestParamValue(String key, String value) {
		return value;
	}

	/**
	 * 获取request参数及当前用户登录信息 增加参数(operatorId-操作员编号;createTime-操作时间)
	 * 
	 * @param request
	 * @return
	 */
	protected Map<String, Object> getParamsAndUser(HttpServletRequest request) {
		Map<String, Object> params = this.requestParamsToMap(request);
		// copyUserFieldToMap(request, params);
		User ose = ShiroUtil.getOperatorInfo();
		params.put("modifyUserId", ose.getUserId());
		params.put("modifyUserName", ose.getUserName());
		params.put("modifyTime", new Date());
		return params;
	}
	/**
	 * 生成返回结果
	 * 
	 * @param msgCode
	 * @param msg
	 * @return
	 */
	protected Map<String, String> generateResultMap(String msgCode, String msg) {
		Map<String, String> result = new HashMap<String, String>();

		result.put("msgcode", msgCode);
		result.put("msg", msg);

		return result;
	}
}
