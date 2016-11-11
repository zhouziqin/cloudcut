package com.mainiway.cloudcut.db.model.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * ***************************************************************************
 *模块名 : BaseEntity
 *文件名 : BaseEntity.java
 *创建时间 : 2016年5月20日
 *实现功能 : BaseEntity
 *作者 : liliangjun
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年5月20日 v0.0.1 liliangjun 创建
 ****************************************************************************
 */
@JsonInclude(Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
getterVisibility = JsonAutoDetect.Visibility.NONE)

public class BaseEntity{

	@JsonIgnore
	private String GID;// 主键ID GID bigint
	@JsonIgnore
	private String ACTIVE_FLAG;// 是否有效数据 ACTIVE_FLAG tinyint
	@JsonIgnore
	private String UPDATE_TIME;// 更新时间 UPDATE_TIME datetime
	@JsonIgnore
	private String UPDATE_PERSON;// 更新人员 UPDATE_PERSON varchar
	
	private String CREATE_TIME;// 新增时间 CREATE_TIME datetime
	@JsonIgnore
	private String CREATE_PERSON;// 新增人员 CREATE_PERSON varchar

	public String getGID() {
		return GID;
	}
	public void setGID(String gID) {
		GID = gID;
	}
	public String getACTIVE_FLAG() {
		return ACTIVE_FLAG;
	}
	public void setACTIVE_FLAG(String aCTIVE_FLAG) {
		ACTIVE_FLAG = aCTIVE_FLAG;
	}
	public String getUPDATE_TIME() {
		return UPDATE_TIME;
	}
	public void setUPDATE_TIME(String uPDATE_TIME) {
		UPDATE_TIME = uPDATE_TIME;
	}
	public String getUPDATE_PERSON() {
		return UPDATE_PERSON;
	}
	public void setUPDATE_PERSON(String uPDATE_PERSON) {
		UPDATE_PERSON = uPDATE_PERSON;
	}
	public String getCREATE_TIME() {
		return CREATE_TIME;
	}
	public void setCREATE_TIME(String cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}
	public String getCREATE_PERSON() {
		return CREATE_PERSON;
	}
	public void setCREATE_PERSON(String cREATE_PERSON) {
		CREATE_PERSON = cREATE_PERSON;
	}
}
