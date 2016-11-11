package com.mainiway.cloudcut.db.model.ui;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mainiway.cloudcut.db.model.entity.AreaEntity;

/**
 * ***************************************************************************
 *模块名 : CityUI
 *文件名 : CityUI.java
 *创建时间 : 2016年8月9日
 *实现功能 : 市UI
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
public class CityUI {
	private String CITY_ID;// 市ID
	private String CITY;// 市
	private String FATHER_ID;// 省ID
	private List<AreaEntity> areaList;// 当前市下的所有区

	public String getCITY_ID() {
		return CITY_ID;
	}

	public void setCITY_ID(String cITY_ID) {
		CITY_ID = cITY_ID;
	}

	public String getCITY() {
		return CITY;
	}

	public void setCITY(String cITY) {
		CITY = cITY;
	}

	public String getFATHER_ID() {
		return FATHER_ID;
	}

	public void setFATHER_ID(String fATHER_ID) {
		FATHER_ID = fATHER_ID;
	}

	public List<AreaEntity> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<AreaEntity> areaList) {
		this.areaList = areaList;
	}

}
