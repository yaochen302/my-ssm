$(function() {
	$(document).bind("contextmenu", function(e) {
		return false;
	});
	var baseUrl = moduleUrl.substring(0, moduleUrl.lastIndexOf("/"));
	var url = "";
	if ($("#_id").val() == "") {
		url = "create";
	} else {
		baseUrl = baseUrl.substring(0, baseUrl.lastIndexOf("/"));
		url = "../update";
	}
	$("#form").form({
		url : url,
		onSubmit : function() {
			if (typeof (KindEditor) != "undefined")
				KindEditor.sync(".kindeditor");
			return $(this).form('enableValidation').form('validate');
		},
		success : function(data) {
			var data = eval('(' + data + ')');
			var msg = "操作失败";
			if (data.state) {
				if (data.info)
					msg = data.info;
				else
					msg = "操作成功";
				top.showMsg(msg);
				top.reloadModule(parentModuleId);
				top.closeModule(moduleName, parentModuleName);
			} else {
				if (data.info)
					msg = data.info;
				top.showMsg(msg);
			}
		}
	});
});
function save1() {
	$("#form").submit();
}
/** *保存按钮事件** */
function save() {

	// 验证表单

	var baseUrl = moduleUrl.substring(0, moduleUrl.lastIndexOf("/"));
	var url = "";
	if ($("#_id").val() == "") {
		url = "create";
	} else {
		baseUrl = baseUrl.substring(0, baseUrl.lastIndexOf("/"));
		url = "../update";
	}
	$('#form').form('submit', {
		url : url,
		onSubmit : function() {
			if (typeof (KindEditor) != "undefined")
				KindEditor.sync(".kindeditor");
			return $(this).form('enableValidation').form('validate');
		},
		success : function(data) {
			var data = eval('(' + data + ')');
			var msg = "操作失败";
			if (data.state) {
				if (data.info)
					msg = data.info;
				else
					msg = "操作成功";
				top.showMsg(msg);
				top.reloadModule(parentModuleId);
				top.closeModule(moduleName, parentModuleName);
			} else {
				if (data.info)
					msg = data.info;
				top.showMsg(msg);
			}
		}
	});
}
function savefile() {
	if (!$("#form").valid())
		return;
	var baseUrl = moduleUrl.substring(0, moduleUrl.lastIndexOf("/"));
	var url = "";
	if ($("#_id").val() == "") {
		url = "create";
	} else {
		baseUrl = baseUrl.substring(0, baseUrl.lastIndexOf("/"));
		url = "../update";
	}
	$("#form").ajaxSubmit({
		url : url,
		success : function(data) {
			var msg = "操作失败";
			try {
				data = eval(data);
				if (data.result.state) {
					if (data.result.msg)
						msg = data.result.msg;
					else
						msg = "操作成功";
					top.showMsg(msg);
					top.reloadModule(parentModuleId);
					top.closeModule(moduleName, parentModuleName);
					return;
				} else {
					msg = data.result.msg;
					top.showMsg(msg);
					return;
				}
			} catch (e) {
				if (data.result) {
					if (data.result.msg)
						msg = data.result.msg;
				}
				top.showMsg(msg);
			}
		}
	});

}
/** *取消按钮事件** */
function cancel() {
	top.closeModule(moduleName, parentModuleName);
}