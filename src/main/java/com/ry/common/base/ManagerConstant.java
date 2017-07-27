package com.ry.common.base;


public class ManagerConstant {
	/**
	 * 启用状态
	 */
	public static final short ENABLED = 1;// 启用
	/**
	 * 禁用状态
	 */
	public static final short DISABLED = 0;// 禁用

	/**
	 * 分组类型
	 * 
	 */
	// 文本分组
	public static final short BASE_GROUP_TEXT = 1;
	// 命令配置分组
	public static final short BASE_GROUP_AUTOANSWER = 7;

	// 是否是方法菜单
	public static final short ISFUNCTION_YES = 1;
	public static final short ISFUNCTION_NO = 0;

	// JSON字段名称
	public static final String JSON_COL_ISSUCCESS = "isSuccess";
	public static final String JSON_COL_MSG = "msg";
	public static final String JSON_COL_IMGURL = "imgUrl";
	public static final String JSON_COL_TOTAL = "total";
	public static final String JSON_COL_ROWS = "rows";

	// 存用户实体的session字段
	public static final String SESSION_USER = "loginUser";

	// 微信菜单类型
	public static final int WEIXIN_MENUTYPE_PUSH = 1;
	public static final int WEIXIN_MENUTYPE_LINK = 2;

	// 签到相关
	public static final int SIGN_STATIC_999 = -1; // 签到失败
	public static final int SIGN_STATIC_0 = 0; // 签到成功 且 成功生成签到记录
	public static final int SIGN_STATIC_1 = 1; // 重复签到
	public static final int SIGN_STATIC_2 = 2; // 未签到
	public static final int SIGN_STATIC_3 = 3; // 签到成功 且 生成签到记录失败

	// 内置菜单
	public static final String SYSMENU_SIGN = "SYSMENU_SIGN";// 签到
	public static final String SYSMENU_JOIN = "SYSMENU_JOIN";// 入会
	public static final String SYSMENU_VCARD = "SYSMENU_VCARD";// 微贺卡
	
	//群发任务状态
	public static final int TASK_STATUS_1 = 1; //未发送
	public static final int TASK_STATUS_2 = 2; //已经发出（未反馈状态）
	public static final int TASK_STATUS_3 = 3; //发送成功
	public static final int TASK_STATUS_4 = 4; //发送失败
	public static final int TASK_STATUS_5 = 5; //取消发送
	
	/** 微信WAP端URL地址 */ 
	public static String WX_WAP_URL = "";
	
}
