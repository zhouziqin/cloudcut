package com.mainiway.cloudcut.common.businesses.country.service;

import javax.servlet.http.HttpServletRequest;

import com.mainiway.cloudcut.common.beans.JsonEnvEntity;

/**
 * ***************************************************************************
 *模块名 : CountryManageService
 *文件名 : CountryManageService.java
 *创建时间 : 2016年5月31日
 *实现功能 :
 *作者 : Administrator
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年5月31日 v0.0.1 Administrator 创建
 ****************************************************************************
 */
public interface CountryManageService {

	// 查找省
	public String queryProvince(HttpServletRequest request, JsonEnvEntity jsonEnv) throws Exception;

	// 查找市
	public String queryCity(HttpServletRequest request, JsonEnvEntity jsonEnv) throws Exception;

	// 查找区
	public String queryArea(HttpServletRequest request, JsonEnvEntity jsonEnv) throws Exception;

	// 根据ID返回省名称
	public String ProvinceName(String provinceID) throws Exception;

	// 根据ID返回市名称
	public String CityName(String cityID) throws Exception;

	// 根据ID返回区名称
	public String AreaName(String areaID) throws Exception;

	// 查找所有的省市区的信息
	public String queryAll(HttpServletRequest request, JsonEnvEntity jsonEnv) throws Exception;

}
