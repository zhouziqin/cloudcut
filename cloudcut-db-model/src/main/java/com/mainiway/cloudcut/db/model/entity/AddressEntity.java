package com.mainiway.cloudcut.db.model.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * ***************************************************************************
 *模块名 : AddressEntity
 *文件名 : AddressEntity.java
 *创建时间 : 2016年5月24日
 *实现功能 : 3.2 收货地址表(t_address)
 *作者 : Administrator
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年5月24日 v0.0.1 Administrator 创建
 ****************************************************************************
 */
@JsonInclude(Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
		getterVisibility = JsonAutoDetect.Visibility.NONE)

public class AddressEntity extends BaseEntity {

	private String ADDR_ID;// 地址ID
	private String ADDR_TYPE;// 地址类型
	private String ADDR_OBJ_ID;// 地址对象ID
	private String RECV_PERSON_NAME;// 收货人姓名
	private String PROVINCE;// 所在省
	private String CITY;// 所在市
	private String AREA;// 所在区
	private String ADDR;// 详细地址
	private String MOBILE;// 手机号码
	private String TEL;// 电话号码
	private String POSTAL_CODE;// 邮政编码
	private String EMAIL;// 电子邮箱
	private String DEFAULT_ADDR;// 默认地址

	public String getADDR_ID() {
		return ADDR_ID;
	}

	public void setADDR_ID(String aDDR_ID) {
		ADDR_ID = aDDR_ID;
	}

	public String getADDR_TYPE() {
		return ADDR_TYPE;
	}

	public void setADDR_TYPE(String aDDR_TYPE) {
		ADDR_TYPE = aDDR_TYPE;
	}

	public String getADDR_OBJ_ID() {
		return ADDR_OBJ_ID;
	}

	public void setADDR_OBJ_ID(String aDDR_OBJ_ID) {
		ADDR_OBJ_ID = aDDR_OBJ_ID;
	}

	public String getRECV_PERSON_NAME() {
		return RECV_PERSON_NAME;
	}

	public void setRECV_PERSON_NAME(String rECV_PERSON_NAME) {
		RECV_PERSON_NAME = rECV_PERSON_NAME;
	}

	public String getPROVINCE() {
		return PROVINCE;
	}

	public void setPROVINCE(String pROVINCE) {
		PROVINCE = pROVINCE;
	}

	public String getCITY() {
		return CITY;
	}

	public void setCITY(String cITY) {
		CITY = cITY;
	}

	public String getAREA() {
		return AREA;
	}

	public void setAREA(String aREA) {
		AREA = aREA;
	}

	public String getADDR() {
		return ADDR;
	}

	public void setADDR(String aDDR) {
		ADDR = aDDR;
	}

	public String getMOBILE() {
		return MOBILE;
	}

	public void setMOBILE(String mOBILE) {
		MOBILE = mOBILE;
	}

	public String getTEL() {
		return TEL;
	}

	public void setTEL(String tEL) {
		TEL = tEL;
	}

	public String getPOSTAL_CODE() {
		return POSTAL_CODE;
	}

	public void setPOSTAL_CODE(String pOSTAL_CODE) {
		POSTAL_CODE = pOSTAL_CODE;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getDEFAULT_ADDR() {
		return DEFAULT_ADDR;
	}

	public void setDEFAULT_ADDR(String dEFAULT_ADDR) {
		DEFAULT_ADDR = dEFAULT_ADDR;
	}

}
