package com.mainiway.cloudcut.beans.jsonbean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * ***************************************************************************
 *模块名 : SelectJsonBean
 *文件名 : SelectJsonBean.java
 *创建时间 : 2016年5月17日
 *实现功能 :  查询JsonBean
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
public class SelectJsonBean {

	private List<String> Field;
	private String Where;
	private PageBean Page;
	private String TransCode;


	public List<String> getField() {
		return Field;
	}
	public void setField(List<String> field) {
		Field = field;
	}

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
	public PageBean getPage() {
		return Page;
	}
	public void setPage(PageBean page) {
		Page = page;
	}


}
