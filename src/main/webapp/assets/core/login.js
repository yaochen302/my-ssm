/**
 * 登录页面js
 */
$(function() {
	$("#kaptcha").click(function() {
		$(this).attr("src", "kaptcha.jpg?" + Math.floor(Math.random() * 100));
	});
	$("#loginButton").click(function() {
		var account = $("#account").val();
		var pwd = $("#pwd").val();
		var code = $("#code").val();

		if (typeof (account) == "undefined" || null == account || "" == account.trim()) {
			$.messager.show({
				title : "提示",
				msg : "请输入用户名！"
			});
			return false;
		}

		if (typeof (pwd) == "undefined" || null == pwd || "" == pwd.trim()) {
			$.messager.show({
				title : "提示",
				msg : "请输入用户密码！"
			});
			return false;
		}

		if (typeof (code) == "undefined" || null == code || "" == code.trim()) {
			$.messager.show({
				title : "提示",
				msg : "请输入验证码！"
			});
			return false;
		}

		$.ajax({
			url : "login",
			data : {
				account : account,
				pwd : pwd,
				code : code
			},
			type : "post",
			dataType : "json",
			success : function(json) {
				if (json.result.state) {
					location.href = "center";
				} else {
					$.messager.show({
						title : "提示",
						msg : json.result.msg
					});
				}
			},
			error : function() {

			}
		});
	});
	
	$("#account").keydown(function(event) {
		switch (event.keyCode) {
		case 13:
			$("#pwd").focus();
			break;
		default:
			break;
		}
	});
	
	$("#pwd").keydown(function(event) {
		switch (event.keyCode) {
		case 13:
			$("#code").focus();
			break;
		default:
			break;
		}
	});
	
	$("#code").keydown(function(event) {
		switch (event.keyCode) {
		case 13:
			$("#loginButton").click();
			break;
		default:
			break;
		}
	});
});