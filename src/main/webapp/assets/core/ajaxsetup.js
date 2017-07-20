// 用与设置全局的AJAX请求，目的是处理全局的AJAX事件，比如请求会话超时等
$.ajaxSetup({
	contentType : "application/x-www-form-urlencoded;charset=utf-8",
	complete : function(XMLHttpRequest, textStatus) {
		// 通过XMLHttpRequest取得响应头，interceptorInfo
		var sessionstatus = XMLHttpRequest.getResponseHeader("interceptorInfo");
		if (sessionstatus == "ajax_sessiontimeout") {
			// 这里怎么处理在你，这里跳转的登录页面
			window.parent.sessionTimeout("由于您太久没操作，会话已过期，请重新登录！");
		} else if (sessionstatus == "permissionValid") {
			window.parent.sessionTimeout("由于您当前没有操作权限，请重新登录！");
		}
	}
});