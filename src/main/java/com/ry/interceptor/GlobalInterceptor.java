package com.ry.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ry.shiro.ShiroUtil;


/**
 * 全局拦截器（权限类）
 * 
 */
public class GlobalInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String actionName = uri.substring(contextPath.length());
		actionName = actionName.indexOf("/") == 0 ? actionName.substring(1) : actionName;

		// 验证账号是否过期
		if (!ShiroUtil.isAuthenticated() || null == ShiroUtil.getOperatorInfo()) {
			// 看是不是在操作登录
			if (actionName.indexOf("login") < 0 && actionName.indexOf("welcome") < 0) {
				// 过期后直接跳到登录页面
				request.setAttribute("interceptorMsg", "用户帐号登录已过期，请重新登录!");
				if (isAjaxRequest(request)) {
					response.setHeader("interceptorInfo", "ajax_sessiontimeout");
					return false;
				} else {
					if (actionName.indexOf("center") >= 0) {
						request.getRequestDispatcher("/welcome").forward(request, response);
					} else {
						request.getRequestDispatcher("/timeout").forward(request, response);
					}
					return false;
				}
			}
		} else {
			// 判断是否内置账户
//			if (ShiroUtil.getUserInfo().equals(ManagerConstant.systemUser) && actionName.indexOf("system") == 0) {
//				// 内置账户操作系统管理模块
//				return true;
//			}
			
			// 验证权限
			if (!checkPermession(actionName)) {
				// 过期后直接跳到登录页面
				request.setAttribute("interceptorMsg", "你试图非法操作，请登录验证!");
				SecurityUtils.getSubject().logout();
				if (isAjaxRequest(request)) {
					response.setHeader("interceptorInfo", "permissionValid");
					return false;
				} else {
					request.getRequestDispatcher("/timeout").forward(request, response);
					return false;
				}
			}
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// 让用户信息常驻mv
		if (null != modelAndView) {
			// 非ajax请求
			modelAndView.addObject("operatorSession", ShiroUtil.getOperatorInfo());
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub
	}

	/**
	 * 验证当前操作员是否有权限
	 * 
	 * @param actionName
	 * @return
	 */
	private boolean checkPermession(String actionName) {
		// 得到请求验证的权限
		String shiroPermession = actionName.replaceAll("/", ":");

		// 判断权限是否是系统要求验证的
		if (ShiroUtil.isSystemCheck(shiroPermession)) {
			// 使用shiro框架进行验证
			return SecurityUtils.getSubject().isPermitted(shiroPermession);
		} else {
			// 未在sys_menu表中配置url的，不进行权限验证
			return true;
		}
	}

	/**
	 * 判断是不是ajax请求
	 * 
	 * @param request
	 * @return
	 */
	private boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		if (header != null && "XMLHttpRequest".equals(header))
			return true;
		else
			return false;
	}
}
