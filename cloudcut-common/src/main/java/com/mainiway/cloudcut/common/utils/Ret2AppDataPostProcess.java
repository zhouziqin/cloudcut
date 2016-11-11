package com.mainiway.cloudcut.common.utils;

import com.mainiway.cloudcut.common.beans.RetObject;
import com.mainiway.cloudcut.common.constant.Constants;
import com.mainiway.cloudcut.util.Jackson;
import com.mainiway.cloudcut.util.JsonUtil;

/**
 * ***************************************************************************
 *模块名 : Ret2AppDataPostProcess
 *文件名 : Ret2AppDataPostProcess.java
 *创建时间 : 2016年7月21日
 *实现功能 : 处理返回给移动端（App）的数据
 *作者 : tang
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年7月21日 v0.0.1 tang 创建
 ****************************************************************************
 */
public class Ret2AppDataPostProcess {
	/**
	 * ====================================================================
	 *函 数 名： @param result 返回App数据
	 *函 数 名： @param clazz 需要返回给移动端数据的Bean定义
	 *函 数 名： @return
	 *功 能： 返回App数据进一步处理 ，将不需要数据去除
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *2016年7月20日 v0.0.1 tang 创建
	====================================================================
	 */
	public static String postProcessData(String result, Class<?> clazz) {
		try {
			RetObject ro = Jackson.json2pojo(result, RetObject.class);
			if (ro.getReCode() == Constants.SUCCESS) {
				String tmp = JsonUtil.obj2json(ro.getObj());
				Object object = tmp.startsWith("[") ? JsonUtil.json2list(tmp, clazz) : JsonUtil.json2Obj(tmp, clazz);
				ro.setObj(object);
				return Jackson.obj2json(ro);
			} else {
				return result;
			}

		} catch (Exception e) {
//			System.out.println(e);
			return result;
		}

	}
}
