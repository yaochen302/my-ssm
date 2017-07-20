var tab_count = 0;
var selectTab = undefined;
$(function() {
	$(document).bind("contextmenu", function(e) {
		return false;
	});
	// easyloader.load("messager", function() {
	// $.messager.show({
	// title : "提示",
	// msg : "ssss"
	// });
	// });
	// using("tabs", function() {
	// // <div title="欢迎" href="welcome"></div>
	//		
	// $("#workbench").tabs({
	// onAdd : function(title) {
	// tab_count = tab_count + 1;
	// alert("sf");
	// },
	// onClose : function(title) {
	// tab_count = tab_count - 1;
	// },
	// onSelect : function(title) {
	// selectTab = title;
	// }
	// });
	// openModule("welcome","欢迎","welcome");
	// });
	$(".module").click(function() {
		var id = $(this).attr("id");
		var title = $(this).children(".word").html();
		var url = $(this).attr("value");
		openModule(id, title, url);
		$(".a3").removeClass("a3");
		$(this).addClass("a3");
	});
	$(".module").mouseenter(function() {
		$(this).addClass("a2");
	});
	$(".module").mouseleave(function() {
		$(this).removeClass("a2");
	});
});

/**
 * 打开模块
 * 
 * @param module
 *            模块名
 * @param url
 *            地址
 */
function openModule(id, module, url, reload) {
	/**
	if ($("#workbench").tabs("exists", module)) {
		$("#workbench").tabs("select", module);
		if (reload) {
			if ($("#" + id).src != url)
				$("#" + id).src = url;
		}
	} else {
		if (tab_count >= 5) {
			$.messager.alert("提示", "为了保证运行效率,请关闭部分标签!");
			return;
		}
		var content = '<iframe id="' + id + '" name="' + id + '" title="' + module + '" scrolling="auto" frameborder="0"  src="'
				+ url + '" style="width:100%;height:100%;"></iframe>';
		$("#workbench").tabs("update", {
			title : module,
			content : content,
			closable : true,
			id : "tabs_" + id
		});

		$("#tabs_" + id).css("overflow", "hidden");
	}
	*/
	
	var content = '<iframe id="' + id + '" name="' + id + '" title="' + module + '" scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
	var obj = document.getElementById("tabs_operate");
	if (obj != null) {
		$("#workbench").tabs("close", 1);
	}
	
	$("#workbench").tabs("add", {
		title : module,
		content : content,
		closable : true,
		id : "tabs_operate"
	});
	
	$("#tabs_operate").css("overflow", "hidden");
}
/**
 * 关闭模块
 * 
 * @param module
 */
function closeModule(module) {
	$("#workbench").tabs("close", module);
}
function closeModule(module, parentModule) {
	$("#workbench").tabs("close", module);
	$("#workbench").tabs("select", parentModule);
}
function selectModule(module) {
	$("#workbench").tabs("select", module);
}
function existsModule(module) {
	return $("#workbench").tabs("exists", module);
}
function reloadModule(moduleId) {
	window.frames[moduleId].reloadGrid();
}
function showMsg(msg) {
	$.messager.show({
		title : "提示",
		msg : msg
	});
}

function logout() {

}
