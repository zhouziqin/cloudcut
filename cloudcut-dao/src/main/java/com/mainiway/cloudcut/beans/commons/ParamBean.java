package com.mainiway.cloudcut.beans.commons;

/**
 * ***************************************************************************
 *模块名 : ParamEntity
 *文件名 : ParamEntity.java
 *创建时间 : 2016年3月14日
 *实现功能 : 用于传递用户名 密码 租户ID 方案ID
 *       传递地阐述 实现与后台数据库的接口的传递
 *作者 : Administrator
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年3月14日 v0.0.1 Administrator 创建
 ****************************************************************************
 */
public class ParamBean {
	private String mstrAccount;// 用户名
	private String mstrPwd;// 密码
	private String strCodeFlag;// 接口识别编码
	private String strRequestJson;// josn拼接字符串

	public String getMstrAccount() {
		return mstrAccount;
	}

	public void setMstrAccount(String mstrAccount) {
		this.mstrAccount = mstrAccount;
	}

	public String getMstrPwd() {
		return mstrPwd;
	}

	public void setMstrPwd(String mstrPwd) {
		this.mstrPwd = mstrPwd;
	}
	public String getStrCodeFlag() {
		return strCodeFlag;
	}

	public void setStrCodeFlag(String strCodeFlag) {
		this.strCodeFlag = strCodeFlag;
	}

	public String getStrRequestJson() {
		return strRequestJson;
	}

	public void setStrRequestJson(String strRequestJson) {
		this.strRequestJson = strRequestJson;
	}

}
