package com.mainiway.cloudcut.common.beans;

import com.mainiway.cloudcut.util.LocalUtil;


/**
 * ***************************************************************************
 *模块名 : JsonEnvEntity
 *文件名 : JsonEnvEntity.java
 *创建时间 : 2016年5月10日
 *实现功能 : 封装参数类
 *作者 : liliangjun
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年5月10日 v0.0.1 liliangjun 创建
 ****************************************************************************
 */
public class JsonEnvEntity {

	private String param;

	public String getParam() {
		if(param!=null){
			return param;
			//return LocalUtil.UTF82ISO(param);
		}else{
			return "";
		}
	}

	public void setParam(String param) {
		this.param = param;
	}

	@Override
	public String toString() {
		return param;
	}

}
