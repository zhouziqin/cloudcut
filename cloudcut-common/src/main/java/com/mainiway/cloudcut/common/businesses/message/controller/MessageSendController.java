package com.mainiway.cloudcut.common.businesses.message.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mainiway.cloudcut.common.beans.JsonEnvEntity;
import com.mainiway.cloudcut.common.businesses.message.service.MessageSendService;

/**
 * ***************************************************************************
 *模块名 : MessageSendController
 *文件名 : MessageSendController.java
 *创建时间 : 2016年7月15日
 *实现功能 : 发送验证码至手机或邮箱
 *作者 : tang
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年7月15日 v0.0.1 tang 创建
 ****************************************************************************
 */
@RestController
@RequestMapping("/messageSend")
public class MessageSendController {
	@Resource
	MessageSendService messageSendService;
	
	@RequestMapping("/sendVerCode")
	public String sendVerCode(HttpServletRequest request, JsonEnvEntity jsonEnv) throws Exception{
		String result = "";
		try {
			result = messageSendService.sendVerCode(request,jsonEnv);
		} catch (Exception e) {
			result = "DATA PARSE ERROR !";
			e.printStackTrace();
		}
		return result;
	}
	

}
