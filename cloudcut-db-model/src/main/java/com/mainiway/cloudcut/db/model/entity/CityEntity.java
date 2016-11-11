package com.mainiway.cloudcut.db.model.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * ***************************************************************************
 *模块名 : CityEntity
 *文件名 : CityEntity.java
 *创建时间 : 2016年5月31日
 *实现功能 : 1.3 市(t_city)
 *作者 : Administrator
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年5月31日 v0.0.1 Administrator 创建
 ****************************************************************************
 */
@JsonInclude(Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
		getterVisibility = JsonAutoDetect.Visibility.NONE)
public class CityEntity extends BaseEntity{

	private String CITY_ID;
	private String CITY;
	private String FATHER_ID;

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

}
