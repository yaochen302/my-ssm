var moduleId = moduleId();
var moduleName = moduleName();
var moduleUrl = moduleUrl();
var parentModuleId = parentModuleId();
var parentModuleName = parentModuleName();
/**
 * 获取当前模块ID
 */
function moduleId() {
	var frame = self.frameElement;
	if (frame)
		return frame.id;
	else
		return "";
}
/**
 * 获取当前模块名称
 */
function moduleName() {
	var frame = self.frameElement;
	if (frame)
		return frame.title;
	else
		return "";
}
/**
 * 获取当前模块URL
 * 
 * @returns
 */
function moduleUrl() {
	var frame = self.frameElement;
	if (frame)
		return frame.src;
	else
		return "";
}

function parentModuleId() {
	var parentModuleId = moduleId.substring(0, moduleId.lastIndexOf("-"));
	return parentModuleId;
}
function parentModuleName() {
	var parentModuleName = moduleName.substring(0, moduleName.lastIndexOf("-"));
	return parentModuleName;
}