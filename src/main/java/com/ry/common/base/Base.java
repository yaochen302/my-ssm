package com.ry.common.base;

import java.util.UUID;

/**
 * 公用基础父类
 * 
 * @author jiangbo
 *
 */
public class Base {
	protected BaseResult generateResult(boolean isSuccess, String msg) {
		return generateResult(isSuccess, msg, null);
	}

	protected BaseResult generateResult(boolean isSuccess, String msg, Object result) {
		BaseResult obj = new BaseResult();
		obj.setMsg(msg);
		obj.setIsSuccess(isSuccess);
		obj.setResult(result);

		return obj;
	}

	/**
	 * 生成指纹信息
	 * 
	 * @return
	 */
	protected String generateFingerprint() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
