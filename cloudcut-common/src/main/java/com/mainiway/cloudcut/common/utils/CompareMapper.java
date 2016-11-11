package com.mainiway.cloudcut.common.utils;

import java.util.Comparator;

import com.mainiway.cloudcut.common.beans.RequestMappingInfoEntity;

/**
 * ***************************************************************************
 *模块名 : CompareMapper
 *文件名 : CompareMapper.java
 *创建时间 : 2016年5月10日
 *实现功能 :  比较器
 *作者 : liliangjun
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年5月10日 v0.0.1 liliangjun 创建
 ****************************************************************************
 */
public class CompareMapper implements Comparator<RequestMappingInfoEntity> {

	@Override
	public int compare(RequestMappingInfoEntity o1, RequestMappingInfoEntity o2) {

		return o1.getMethodName().compareTo(o2.getMethodName());
	}

}
