package com.mainiway.cloudcut.common.utils;

import java.util.Properties;

/**
 * ***************************************************************************
 *模块名 : MailSenderInfoEntity
 *文件名 : MailSenderInfoEntity.java
 *创建时间 : 2016年5月12日
 *实现功能 : 记录发送邮件所需的各种信息，如发送邮件服务器的地址、端口号、发件人邮箱、收件人邮箱等等
 *作者 : Administrator
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年5月12日 v0.0.1 Administrator 创建
 ****************************************************************************
 */
public class MailSenderInfoEntity {
	// 发送邮件的服务器的IP(或主机地址)
	 private String mailServerHost;

	 // 发送邮件的服务器的端口
	 private String mailServerPort = "25";

	 // 发件人邮箱地址
	 private String fromAddress;

	 // 收件人邮箱地址
	 private String toAddress;

	 // 登陆邮件发送服务器的用户名
	 private String userName;

	 // 登陆邮件发送服务器的密码
	 private String password;

	 // 是否需要身份验证
	 private boolean validate = false;

	 // 邮件主题
	 private String subject;

	 // 邮件的文本内容
	 private String content;


	 
	 public Properties getProperties() {
	  Properties p = new Properties();
	  p.put("mail.smtp.host", this.mailServerHost);
	  p.put("mail.smtp.port", this.mailServerPort);
	  p.put("mail.smtp.auth", validate ? "true" : "false");
	  return p;
	 }
	public String getMailServerHost() {
		return mailServerHost;
	}


	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}


	public String getMailServerPort() {
		return mailServerPort;
	}


	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}


	public String getFromAddress() {
		return fromAddress;
	}


	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}


	public String getToAddress() {
		return toAddress;
	}


	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isValidate() {
		return validate;
	}


	public void setValidate(boolean validate) {
		this.validate = validate;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
	 
}
