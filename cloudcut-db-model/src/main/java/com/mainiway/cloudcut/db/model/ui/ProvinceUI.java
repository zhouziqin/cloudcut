package com.mainiway.cloudcut.db.model.ui;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * ***************************************************************************
 *模块名 : ProvinceUI
 *文件名 : ProvinceUI.java
 *创建时间 : 2016年8月9日
 *实现功能 : 省UI
 *作者 : Administrator
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年8月9日 v0.0.1 Administrator 创建
 ****************************************************************************
 */
@JsonInclude(Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
		getterVisibility = JsonAutoDetect.Visibility.NONE)
public class ProvinceUI {
	private String PROVINCE_ID;// 省ID
	private String PROVINCE;// 省
	private List<CityUI> cityList;// 当前省下的所有市

	public String getPROVINCE_ID() {
		return PROVINCE_ID;
	}

	public void setPROVINCE_ID(String pROVINCE_ID) {
		PROVINCE_ID = pROVINCE_ID;
	}

	public String getPROVINCE() {
		return PROVINCE;
	}

	public void setPROVINCE(String pROVINCE) {
		PROVINCE = pROVINCE;
	}

	public List<CityUI> getCityList() {
		return cityList;
	}

	public void setCityList(List<CityUI> cityList) {
		this.cityList = cityList;
	}

}
