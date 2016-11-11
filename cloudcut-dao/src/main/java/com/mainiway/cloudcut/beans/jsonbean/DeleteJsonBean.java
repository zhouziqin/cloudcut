package com.mainiway.cloudcut.beans.jsonbean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
/**
 * ***************************************************************************
 *模块名 : DeleteJsonBean
 *文件名 : DeleteJsonBean.java
 *创建时间 : 2016年5月17日
 *实现功能 : 删除jsonbean
 *作者 : liliangjun
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年5月17日 v0.0.1 liliangjun 创建
 ****************************************************************************
 */
@JsonInclude(Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
getterVisibility = JsonAutoDetect.Visibility.NONE)
public class DeleteJsonBean {

	private String TransCode;
	private String Where;

	public String getTransCode() {
		return TransCode;
	}
	public void setTransCode(String transCode) {
		TransCode = transCode;
	}
	public String getWhere() {
		return Where;
	}
	public void setWhere(String where) {
		Where = where;
	}

}
