<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<body>
	<h2>Hello World! 欢迎登录！</h2>
	<form>
		用户名： <input id="username" type="text" name="user_name"><br>
		密 码：<input id="password" type="password" name="user_password"><br>
		<input id="login" type="button" value="登录" />
	</form>
</body>
<jsp:include page="../core/include-jquery.jsp" flush="true" />
<jsp:include page="../core/include-easyui.jsp" flush="true" />
<script type="text/javascript" src="../assets/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
	document.getElementById("login").onclick = function() {
		$.ajax({
			url : "login",
			type : "post",
			data : {
				"user_name" : $("#username").val(),
				"user_password" : $("#password").val()
			},
			dataType : "json",
			success : function(json) {
				if (json.isSuccess) {
					location.href = "../home/index";
				} else {
					alert(json.msg);
				}
			},
			error : function(json) {
				$.messager.alert("提示", "服务器繁忙，请稍候再试！！");
			}
		});
	};
</script>
</html>