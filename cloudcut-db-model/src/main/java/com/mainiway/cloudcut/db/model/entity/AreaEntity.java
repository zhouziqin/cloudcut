package com.mainiway.cloudcut.db.model.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * ***************************************************************************
 *模块名 : AreaEntity
 *文件名 : AreaEntity.java
 *创建时间 : 2016年5月31日
 *实现功能 : 1.4 区(t_area)
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
public class AreaEntity extends BaseEntity{

	private String AREA_ID;//区ID
	private String AREA;//区
	private String FATHER_ID;//所属市

	public String getAREA_ID() {
		return AREA_ID;
	}

	public void setAREA_ID(String aREA_ID) {
		AREA_ID = aREA_ID;
	}

	public String getAREA() {
		return AREA;
	}

	public void setAREA(String aREA) {
		AREA = aREA;
	}

	public String getFATHER_ID() {
		return FATHER_ID;
	}

	public void setFATHER_ID(String fATHER_ID) {
		FATHER_ID = fATHER_ID;
	}

}
