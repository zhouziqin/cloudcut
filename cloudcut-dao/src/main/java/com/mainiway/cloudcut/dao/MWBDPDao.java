package com.mainiway.cloudcut.dao;

import java.util.List;

import com.mainiway.cloudcut.beans.commons.ParamBean;


public interface MWBDPDao {
	// 查询方法
	String select(ParamBean entity) throws Exception;

	// 新增方法
	String insert(ParamBean entity) throws Exception;

	// 修改方法
	String update(ParamBean entity) throws Exception;

	// 删除方法
	String delete(ParamBean entity) throws Exception;

	// 事务操作接口
	String transOperate(ParamBean entity) throws Exception;

	// 查询行数
	String selectRows(ParamBean entity) throws Exception;

	// 统一通用操作接口
	String commonOperate(ParamBean entity) throws Exception;

	// 查询方法 返回实体类集合
	<T> List<T> selectObject(ParamBean entity, Class<T> clazz) throws Exception;

	// 查询最新最大业务ID
	List<String> selectBiidMaxString(ParamBean entity) throws Exception;
}
