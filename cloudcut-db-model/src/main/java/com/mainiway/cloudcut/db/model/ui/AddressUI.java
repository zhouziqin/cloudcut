package com.mainiway.cloudcut.db.model.ui;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mainiway.cloudcut.db.model.entity.AddressEntity;

/**
 * ***************************************************************************
 *模块名 : AddressUI
 *文件名 : AddressUI.java
 *创建时间 : 2016年8月11日
 *实现功能 :收货地址增加省、市、区（县）ID 
 *作者 : tang
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年8月11日 v0.0.1 tang 创建
 ****************************************************************************
 */
@JsonInclude(Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
		getterVisibility = JsonAutoDetect.Visibility.NONE)
public class AddressUI extends AddressEntity{
	private String PROVINCEID;// 所在省
	private String CITYID;// 所在市
	private String AREAID;// 所在区
	public String getPROVINCEID() {
		return PROVINCEID;
	}
	public void setPROVINCEID(String pROVINCEID) {
		PROVINCEID = pROVINCEID;
	}
	public String getCITYID() {
		return CITYID;
	}
	public void setCITYID(String cITYID) {
		CITYID = cITYID;
	}
	public String getAREAID() {
		return AREAID;
	}
	public void setAREAID(String aREAID) {
		AREAID = aREAID;
	}
	
}
