package com.mainiway.cloudcut.common.businesses.message.service;

import javax.servlet.http.HttpServletRequest;

import com.mainiway.cloudcut.common.beans.JsonEnvEntity;

public interface MessageSendService {
	/**
	 * ====================================================================
	 *函 数 名： @param request
	 *函 数 名： @param jsonEnv
	 *函 数 名： @return
	 *函 数 名： @throws Exception
	 *功 能： 发送短信或邮箱验证码
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *2016年7月15日 v0.0.1 tang 创建
	====================================================================
	 */
	String sendVerCode(HttpServletRequest request,JsonEnvEntity jsonEnv) throws Exception;

}
