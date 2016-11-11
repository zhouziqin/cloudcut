package com.mainiway.cloudcut.common.businesses.message.service.impl;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mainiway.cloudcut.common.beans.JsonEnvEntity;
import com.mainiway.cloudcut.common.beans.RetObject;
import com.mainiway.cloudcut.common.businesses.base.service.BaseService;
import com.mainiway.cloudcut.common.businesses.message.service.MessageSendService;
import com.mainiway.cloudcut.common.constant.Constants;
import com.mainiway.cloudcut.common.utils.MailSend;
import com.mainiway.cloudcut.common.utils.MailSenderInfoEntity;
import com.mainiway.cloudcut.common.utils.MessageSend;
import com.mainiway.cloudcut.util.Jackson;
import com.mainiway.cloudcut.util.LocalUtil;

/**
 * ***************************************************************************
 *模块名 : MessageSendServiceImpl
 *文件名 : MessageSendServiceImpl.java
 *创建时间 : 2016年7月15日
 *实现功能 :
 *作者 : tang
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年7月15日 v0.0.1 tang 创建
 ****************************************************************************
 */
@Service("messageSendService")
public class MessageSendServiceImpl extends BaseService implements MessageSendService {
	private static final Logger logger = LoggerFactory.getLogger(MessageSendServiceImpl.class);
	
	@Value("${sso.vercode.timeout}")
	protected String vercodeTimeout;
	
	@Override
	public String sendVerCode(HttpServletRequest request, JsonEnvEntity jsonEnv) throws Exception {
		//http://localhost:8804/idinbaopersonal/accountPrivileges/sendVerCode?param={"USER_CODE":"18912345678","TIMEOUT":"300","TYPE":"2"}
		RetObject ro = new RetObject();
		String jsonStr = "";
		boolean flag = false;//短信是否发送成功
		int tm;//失效时间，单位：分钟
		try {
			if(StringUtils.isBlank(jsonEnv.getParam())) {
				ro.setReCode(Constants.PARAMNOTNULL);
				ro.setReDescr(Constants.PARAMNOTNULLDES);
			}else{
				//解析param参数
				Map<String,Object> paramMap = Jackson.json2map(jsonEnv.getParam());
				String userCode = (String)paramMap.get("USER_CODE");
				String verCode = null;
				String timeout = (String)paramMap.get("TIMEOUT");//过期时间
				String type = (String)paramMap.get("TYPE");//短信模板类型 1-手机号绑定 2-手机号注册3-邮箱注册（SecuritySettingServiceImpl）
				String url = (String)paramMap.get("url");//过期时间

				if (timeout == null) {
//					timeout = PropertyUtils.getContextProperty("sso.vercode.timeout");
					timeout = vercodeTimeout;
				}
				if (timeout == null) {
					timeout = "300";//单位秒，默认5分钟
				}
				tm = Integer.parseInt(timeout) / 60;
				if(StringUtils.isNotBlank(userCode) && StringUtils.isNotBlank(type)){
					//此时为获取验证码
					//随机生成6位验证码
					verCode = LocalUtil.generateVerCode(6);
					logger.info("generate verCode success ! the verCode is :"+verCode);
					//TODO
					//通过手机短信平台发送验证码到手机或者发送到邮箱
                    if(userCode.contains("@")){
                    	//为邮箱 发邮件
                    	MailSenderInfoEntity mailInfo = new MailSenderInfoEntity();
                    	mailInfo.setMailServerHost("mail.mainiway.com");
        				mailInfo.setFromAddress(MessageSend.MAIL);
        				mailInfo.setUserName(MessageSend.MAIL_ACC);
        				mailInfo.setSubject("IDinBao");
        				mailInfo.setPassword(MessageSend.MAIL_PWD);

    					String msgTemplate = MessageSend.mailTemplateMap.get(type);
    					if(msgTemplate!=null){
    						msgTemplate = msgTemplate.replace("var1", verCode);
        					msgTemplate = msgTemplate.replace("var0", userCode);
        					msgTemplate = msgTemplate.replace("var2", String.valueOf(tm));
        					msgTemplate = msgTemplate.replace("{$var3}", url == null ? "" : url);
        					msgTemplate = msgTemplate.replace("{$var4}", LocalUtil.getSDFStr(new Date(), Constants.DTAEFORMAT));
        					mailInfo.setContent(msgTemplate);
        					try {
        						if(url == null){
        							flag = MailSend.sendMail(userCode,msgTemplate.split("\\|")[0], msgTemplate.split("\\|")[1], null, mailInfo);
        						}else{
        							MailSend.sendMailHtml(userCode,"注册信息", null, mailInfo);
        						}
        					} catch (Exception e) {
    							ro.setReCode(Constants.MAILFAILURE);
    							ro.setReDescr(Constants.MAILFAILUREDES);
    							logger.error("邮件发送失败！", e);
    						}
        					if (!flag) {
    							ro.setReCode(Constants.PARAMERROR);
    							ro.setReDescr(Constants.PARAMERRORDES);
    						}
    					}else{
    						ro.setReCode(Constants.NOMESSAGETEMPLATE);
							ro.setReDescr(Constants.NOMESSAGETEMPLATEDES);
    					}
                    }else{
                    	//发送手机短信
                    	MessageSend messageSend = new MessageSend();
                    	String msgTemplate = MessageSend.msgTemplateMap.get(type);
                    	if (msgTemplate != null) {//1-手机号绑定 2-手机号注册
                    		flag = messageSend.post(userCode, verCode, msgTemplate, tm);
                    		if (!flag) {
								ro.setReCode(Constants.MESSAGEFAILURE);
								ro.setReDescr(Constants.MESSAGEFAILUREDES);
							}
						} else {//未知模板
							ro.setReCode(Constants.NOMESSAGETEMPLATE);
							ro.setReDescr(Constants.NOMESSAGETEMPLATEDES);

						}
                    }
                    if (flag) {
						//将验证码保存至redis中
						redisCacheService.set(userCode, verCode,Integer.parseInt(timeout));

						ro.setReCode(Constants.SUCCESS);
						ro.setReDescr(Constants.SUCCESSDES);
						paramMap.clear();
						paramMap.put("VER_CODE",verCode);
						ro.setObj(paramMap);
					}

				}else{
					ro.setReCode(Constants.PARAMERROR);
					ro.setReDescr(Constants.PARAMERRORDES);
				}

			}
		}catch (Exception e) {
			baseHandleException(e,ro,"AccountPrivilegesServiceImpl","sendVerCode()");
		}

		return baseHandleJsonStr(jsonStr,ro,request);
	}

}
