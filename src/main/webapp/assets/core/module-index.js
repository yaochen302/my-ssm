var superior = "";
var parentId = "";
$(function() {
	$(document).bind("contextmenu", function(e) {
		return false;
	});
	var options = $("#grid").datagrid('options');
	options.onDblClickRow = function(rowIndex, rowData) {
		edit();
	};
	options.onLoadSuccess = function(data) {
		superior = data.superior;
		if (hasParent()) {
			$("#back").show();
		} else {
			$("#back").hide();
		}
	};

});

function hasSuperior() {
	if (superior && superior != "")
		return true;
	else
		return false;
}
function hasParent() {
	if (parentId && parentId != "")
		return true;
	else
		return false;
}

/**
 * 打开子记录
 * 
 * @param parent
 */
function openSub(parent) {
	$("#grid").datagrid("reload", {
		_parent : parent,
		page : 1
	});
	parentId = parent;
}

/**
 * 返回
 */
function back() {
	openSub(superior);
}
/**
 * 下级列表
 * 
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {String}
 */

function subFormat(value, rowData, rowIndex) {
	var html = "<a href=\"#\" onclick=\"javascript:openSub('"
			+ eval("rowData." + $("#grid").datagrid("options").idField)
			+ "');\" style=\"text-decoration:none;border: 1px solid #A8B8D1;background:#eee;\">下一级</a>";
	return html;
}
/**
 * 获取当前选中行
 * 
 * @returns
 */
function getSelectItem() {
	var row = $("#grid").datagrid('getSelected');
	if (row)
		return row;
	else
		return null;
}
/**
 * 获取当前选中行ID
 * 
 * @returns
 */
function getSelectItemId() {
	var row = $("#grid").datagrid('getSelected');
	if (row)
		return eval("row." + $("#grid").datagrid("options").idField);
	else
		return null;
}
function add() {
	var parent = "";
	if (hasParent())
		parent = "_" + parentId;
	top.openModule(moduleId + "-add", moduleName + "-新增", moduleUrl + "/new"
			+ parent);
}
function edit() {
	var parent = "";
	if (hasParent())
		parent = "_" + parentId;
	var selectItemId = getSelectItemId();
	if (selectItemId)
		top.openModule(moduleId + "-edit", moduleName + "-编辑", moduleUrl + "/"
				+ selectItemId + "/edit" + parent);
}
function show() {
	var parent = "";
	if (hasParent())
		parent = "_" + parentId;
	var selectItemId = getSelectItemId();
	if (selectItemId)
		top.openModule(moduleId + "-show", moduleName + "-查看", moduleUrl + "/"
				+ selectItemId + "/show" + parent);
}
function isConfirm() {
	var selectItemId = getSelectItem();
	if (selectItemId != null) {
		if (window.confirm('是否继续执行操作？') == true)
			return true;
		else
			return false;
	} else {
		return false;
	}
}

function del() {
	var selectItemId = getSelectItemId();
	if (selectItemId) {
		if (isConfirm()) {
			var baseUrl = moduleUrl.substring(moduleUrl.lastIndexOf("/") + 1);
			$.post(baseUrl + "/" + selectItemId + "/delete", function(data) {
				var msg = "操作失败";
				if (data.state) {
					if (data.info)
						msg = data.info;
					else
						msg = "操作成功";
					$("#grid").datagrid("reload");
				} else {
					if (data.info)
						msg = data.info;
				}
				top.showMsg(msg);
			});
		}

	}
}
function disable() {
	var selectItemId = getSelectItemId();
	if (selectItemId) {
		if (isConfirm()) {
			var baseUrl = moduleUrl.substring(moduleUrl.lastIndexOf("/") + 1);
			$.post(baseUrl + "/" + selectItemId + "/disable", function(data) {
				var msg = "操作成功";
				if (data.state) {
					if (data.info)
						msg = data.info;
					else
						msg = "操作成功";
					$("#grid").datagrid("reload");
				} else {
					if (data.info)
						msg = data.info;
				}
				top.showMsg(msg);
			});
		}

	}
}
function destroy() {
	var selectItemId = getSelectItemId();
	if (selectItemId) {
		if (isConfirm()) {
			var baseUrl = moduleUrl.substring(moduleUrl.lastIndexOf("/") + 1);
			$.post(baseUrl + "/" + selectItemId + "/destroy", function(data) {
				var msg = "操作失败";
				if (data.state) {
					if (data.info)
						msg = data.info;
					else
						msg = "操作成功";
					$("#grid").datagrid("reload");
				} else {
					if (data.info)
						msg = data.info;
				}
				top.showMsg(msg);
			});
		}
	}
}
function reloadGrid() {
	$("#grid").datagrid("reload");
}