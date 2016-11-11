package com.mainiway.cloudcut.common.businesses.country.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mainiway.cloudcut.common.beans.JsonEnvEntity;
import com.mainiway.cloudcut.common.businesses.country.service.CountryManageService;

/**
 * ***************************************************************************
 *模块名 : CountryController
 *文件名 : CountryController.java
 *创建时间 : 2016年5月31日
 *实现功能 : 省 市 区 查询
 *作者 : Administrator
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年5月31日 v0.0.1 Administrator 创建
 ****************************************************************************
 */
@RestController
@RequestMapping("/countryManage")
public class CountryManageController {
	@Autowired
	CountryManageService countryManageService;

	// 查找省
	@RequestMapping("/queryProvince")
	public String queryProvince(HttpServletRequest request, JsonEnvEntity jsonEnv) throws Exception {
		String result = "";
		try {
			result = countryManageService.queryProvince(request, jsonEnv);
		} catch (Exception e) {
			result = "DATA PARSE ERROR !";
			e.printStackTrace();
		}
		return result;
	}

	// 查找市
	@RequestMapping("/queryCity")
	public String queryCity(HttpServletRequest request, JsonEnvEntity jsonEnv) throws Exception {
		String result = "";
		try {
			result = countryManageService.queryCity(request, jsonEnv);
		} catch (Exception e) {
			result = "DATA PARSE ERROR !";
			e.printStackTrace();
		}
		return result;
	}

	// 查找区
	@RequestMapping("/queryArea")
	public String queryArea(HttpServletRequest request, JsonEnvEntity jsonEnv) throws Exception {
		String result = "";
		try {
			result = countryManageService.queryArea(request, jsonEnv);
		} catch (Exception e) {
			result = "DATA PARSE ERROR !";
			e.printStackTrace();
		}
		return result;
	}

	// 查找所有省市区的信息
	@RequestMapping("/queryAll")
	public String queryAll(HttpServletRequest request, JsonEnvEntity jsonEnv) throws Exception {
		String result = "";
		try {
			result = countryManageService.queryAll(request, jsonEnv);
		} catch (Exception e) {
			result = "DATA PARSE ERROR !";
			e.printStackTrace();
		}
		return result;
	}

}
