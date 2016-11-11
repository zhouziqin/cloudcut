package com.mainiway.cloudcut.beans.jsonbean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * ***************************************************************************
 *模块名 : ResultJsonBean
 *文件名 : ResultJsonBean.java
 *创建时间 : 2016年5月18日
 *实现功能 : 数据库返回jsonbean
 *作者 : liliangjun
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年5月18日 v0.0.1 liliangjun 创建
 ****************************************************************************
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonInclude(Include.NON_NULL)
public class ResultJsonBean {

	private String StateCode;
	private String StateDiscrip;
	private String Result;

	public String getStateCode() {
		return StateCode;
	}
	public void setStateCode(String stateCode) {
		StateCode = stateCode;
	}
	public String getStateDiscrip() {
		return StateDiscrip;
	}
	public void setStateDiscrip(String stateDiscrip) {
		StateDiscrip = stateDiscrip;
	}
	public String getResult() {
		return Result;
	}
	public void setResult(String result) {
		Result = result;
	}



}
