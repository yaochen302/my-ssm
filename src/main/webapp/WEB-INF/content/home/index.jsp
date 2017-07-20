<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<body>
	<h2>Hello World! 欢迎!！</h2>
	<br>
	<input id="logout" type="button" value="注销">
</body>
<jsp:include page="../core/include-jquery.jsp" flush="true" />
<script type="text/javascript">
document.getElementById("logout").onclick = function() {
	location.href = "../test/logout";
};
</script>
</html>