package com.mainiway.cloudcut.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mainiway.cloudcut.beans.commons.ParamBean;
import com.mainiway.cloudcut.beans.jsonbean.DeleteJsonBean;
import com.mainiway.cloudcut.beans.jsonbean.InsertJsonBean;
import com.mainiway.cloudcut.beans.jsonbean.MaxIdJsonBean;
import com.mainiway.cloudcut.beans.jsonbean.PageBean;
import com.mainiway.cloudcut.beans.jsonbean.ResultJsonBean;
import com.mainiway.cloudcut.beans.jsonbean.RowsJsonBean;
import com.mainiway.cloudcut.beans.jsonbean.SelectJsonBean;
import com.mainiway.cloudcut.beans.jsonbean.UpdateJsonBean;
import com.mainiway.cloudcut.dao.MWBDPDao;
import com.mainiway.cloudcut.util.DBConstants;
import com.mainiway.cloudcut.util.DbOperaCode;
import com.mainiway.cloudcut.util.Jackson;
import com.mainiway.cloudcut.util.JsonUtil;
import com.mainiway.cloudcut.util.TimeUtil;


/**
 * ***************************************************************************
 *模块名 : MybatisDaoImpl
 *文件名 : MybatisDaoImpl.java
 *创建时间 : 2016年7月23日
 *实现功能 : Mybatis Dao 兼容现有数据访问接口协议。使用该DAO时，放开自身@Repository，注释掉MWBDPDaoImpl的@Repository("mWBDPDao")和applicationContext.xml中的mWBDPDao定义
 *作者 : tang
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年7月23日 v0.0.1 tang 创建
 ****************************************************************************
 */
@Repository
public class MybatisDaoImpl implements MWBDPDao {
	private static Log logger = LogFactory.getLog(MybatisDaoImpl.class);
	private static String dateFormat = "yyyy-MM-dd HH:mm:ss";
	private static final String viewPrefix = "VIEW_";
	private static final String tablePrefix = "IDB_";
	private static final String actViewPrefix = "un_iter";//实际视图前缀
	private static final String actTablePrefix = "t_";//实际表前缀
	private static final String buzIdsuffix = "_id";//业务ID后缀
	private static Map<String, String> tablesAndViewsMap = new HashMap<String, String>();//数据编码对应表和视图、表对应业务ID
	private static Map<String, String> tablesIdsMap = new HashMap<String, String>();//表对应当前业务ID值

	static {
		try {
			Field[] fields = DbOperaCode.class.getFields();
			String value = null;
			String key = null;
			for (Field field : fields) {
				key = field.get(DbOperaCode.class).toString();
				if (key.startsWith(viewPrefix)) {
					value = actViewPrefix + field.getName().replace("_ROWS", "").toLowerCase();
//					System.out.println(key+"="+value);
					tablesAndViewsMap.put(key, value);
				} else if (key.startsWith(tablePrefix)) {
					value = actTablePrefix + field.getName().toLowerCase();
//					System.out.println(key+"="+value);
					tablesAndViewsMap.put(key + DbOperaCode.INSERT, value);
					tablesAndViewsMap.put(key + DbOperaCode.DEL, value);
					tablesAndViewsMap.put(key + DbOperaCode.UPDATE, value);
					tablesAndViewsMap.put(key + DbOperaCode.SELECT, value);
					tablesAndViewsMap.put(key + DbOperaCode.SELECT_ROWS, value);
					tablesAndViewsMap.put(key + DbOperaCode.SELECT_BIIDMAX, value);
				} else if (key.endsWith(buzIdsuffix)) {
					value = field.getName();//表名
					tablesAndViewsMap.put(value, key);
				}

			}
		} catch (Exception e) {
			logger.error("MybatisDaoImpl init tablesAndViewsMap error!", e);
		}
	}

	@Resource
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public String select(ParamBean entity) throws Exception {
		ResultJsonBean resultJsonBean = new ResultJsonBean();
		resultJsonBean.setStateCode(DBConstants.FAIL);
		String requestJson = entity.getStrRequestJson();
		String codeFlag = entity.getStrCodeFlag();

		SelectJsonBean selectJsonBean = Jackson.json2pojo(requestJson, SelectJsonBean.class);
		String where = selectJsonBean.getWhere();
		List<String> fields = selectJsonBean.getField();
		StringBuilder sql = new StringBuilder("select ");
		if (fields == null || fields.isEmpty()) {
			sql.append("* ");
		} else {
			for (int i = 0; i < fields.size(); i++) {
				sql.append(fields.get(i));
				if (i != fields.size() - 1) {
					sql.append(",");
				}
				sql.append(" ");
			}
		}
		sql.append("from ");
		String tableName = codeFlagToTable(codeFlag);
		sql.append(tableName);
		sql.append(" where ACTIVE_FLAG=1 ");
		if (where != null && !DBConstants.ALLRECORDS.equals(where)) {
			sql.append("and ").append(where);
		}

		SqlParam sqlParam = new SqlParam();
		sqlParam.setSql(sql.toString());
		logger.info("select sql={}" + sqlParam.getSql());

		List<HashMap<String, Object>> list = qryCommons(sqlParam);
		resultJsonBean.setResult(JsonUtil.obj2json(list));
		resultJsonBean.setStateCode(DBConstants.SUCCESS);
		return Jackson.obj2json(resultJsonBean);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String insert(ParamBean entity) throws Exception {
		ResultJsonBean resultJsonBean = new ResultJsonBean();
		resultJsonBean.setStateCode(DBConstants.FAIL);
		String requestJson = entity.getStrRequestJson();
		String codeFlag = entity.getStrCodeFlag();

		StringBuilder sql = null;
		String tableName = codeFlagToTable(codeFlag);

		InsertJsonBean insertJsonBean = Jackson.json2pojo(requestJson, InsertJsonBean.class);
		List<?> list =insertJsonBean.getField();
		if (list.isEmpty()) {
			throw new RuntimeException("insert while there is no data");
		}
		List<Map> list2 = Jackson.json2list(Jackson.obj2json(list), Map.class);
		SqlParam sqlParam = new SqlParam();
		Object[] tmp = null;
		int index;
		for (Map map : list2) {
			index = 0;
			sql = new StringBuilder("insert into ");
			sql.append(tableName).append("(EXTRACT_DATE, ACTIVE_FLAG, UPDATE_TIME, UPDATE_PERSON, CREATE_TIME, CREATE_PERSON");
			tmp = new Object[map.size()];
			for (Object key : map.keySet()) {
				sql.append(", ").append(key);
				tmp[index] = map.get(key);
				index++;
			}
			sql.append(") values('0000-00-00 00:00:01', 1, '").append(TimeUtil.date2String(new Date(), dateFormat)).append("', 'sys', '");
			sql.append(TimeUtil.date2String(new Date(), dateFormat)).append("', 'sys'");
			for (Object object : tmp) {
				sql.append(", '").append(object).append("'");
			}
			sql.append(")");

			sqlParam.setSql(sql.toString());
			logger.info("insert sql={" + sqlParam.getSql() +"}");

			try {
				insertCommons(sqlParam);
				resultJsonBean.setStateCode(DBConstants.SUCCESS);
			} catch (Exception e) {
				throw e;
			}
		}
		return Jackson.obj2json(resultJsonBean);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String update(ParamBean entity) throws Exception {
		ResultJsonBean resultJsonBean = new ResultJsonBean();
		resultJsonBean.setStateCode(DBConstants.FAIL);
		String requestJson = entity.getStrRequestJson();
		String codeFlag = entity.getStrCodeFlag();

		UpdateJsonBean updateJsonBean = Jackson.json2pojo(requestJson, UpdateJsonBean.class);
		Object field = updateJsonBean.getField();
		String where = updateJsonBean.getWhere();
		Map<String, Object> fieldMap = null;
		if (field instanceof Map) {//目前更新只涉及Map
			fieldMap = (Map<String, Object>) field;
			StringBuilder sql = new StringBuilder("update ");
			String tableName = codeFlagToTable(codeFlag);
			sql.append(tableName).append(" set ");
			for (String key : fieldMap.keySet()) {
				sql.append(key).append("='").append(fieldMap.get(key)).append("', ");
			}
//			sql.deleteCharAt(sql.length() - 2);
			sql.append("UPDATE_TIME='").append(TimeUtil.date2String(new Date(), dateFormat)).append("', ");
			sql.append("UPDATE_PERSON='sys' ");
			if (where != null) {
				sql.append("where ACTIVE_FLAG=1 and ").append(where);
			} else {
				throw new RuntimeException("update while where is null!");
			}

			SqlParam sqlParam = new SqlParam();
			sqlParam.setSql(sql.toString());
			logger.info("update sql={" + sqlParam.getSql() +"}");

			try {
				updateCommons(sqlParam);
				resultJsonBean.setStateCode(DBConstants.SUCCESS);
			} catch (Exception e) {
				throw e;
			}
		}

		return Jackson.obj2json(resultJsonBean);
	}

	@Override
	public String delete(ParamBean entity) throws Exception {
		ResultJsonBean resultJsonBean = new ResultJsonBean();
		resultJsonBean.setStateCode(DBConstants.FAIL);
		String requestJson = entity.getStrRequestJson();
		String codeFlag = entity.getStrCodeFlag();

		DeleteJsonBean deleteJsonBean = Jackson.json2pojo(requestJson, DeleteJsonBean.class);
		String where = deleteJsonBean.getWhere();
		if (where != null) {
			StringBuilder sql = new StringBuilder("update ");
			String tableName = codeFlagToTable(codeFlag);
			sql.append(tableName).append(" set ACTIVE_FLAG=2, ");
			sql.append("UPDATE_TIME='").append(TimeUtil.date2String(new Date(), dateFormat)).append("', ");
			sql.append("UPDATE_PERSON='sys' ");
			sql.append("where ACTIVE_FLAG=1 and ").append(where);

			SqlParam sqlParam = new SqlParam();
			sqlParam.setSql(sql.toString());
			logger.info("delete sql={" + sqlParam.getSql() +"}");

			try {
				updateCommons(sqlParam);
				resultJsonBean.setStateCode(DBConstants.SUCCESS);
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new RuntimeException("delete while where is null!");
		}
		return Jackson.obj2json(resultJsonBean);
	}

	@Override
	public String transOperate(ParamBean entity) throws Exception {
		ResultJsonBean resultJsonBean = new ResultJsonBean();
		resultJsonBean.setStateCode(DBConstants.SUCCESS);
		resultJsonBean.setResult("000000");
		return Jackson.obj2json(resultJsonBean);
	}

	@Override
	public String selectRows(ParamBean entity) throws Exception {
		ResultJsonBean resultJsonBean = new ResultJsonBean();
		resultJsonBean.setStateCode(DBConstants.FAIL);
		String requestJson = entity.getStrRequestJson();
		String codeFlag = entity.getStrCodeFlag();

		RowsJsonBean rowsJsonBean = Jackson.json2pojo(requestJson, RowsJsonBean.class);
		String where = rowsJsonBean.getWhere();
		StringBuilder sql = new StringBuilder("select count(1) cnt from ");
		String tableName = codeFlagToTable(codeFlag);
		sql.append(tableName);
		sql.append(" where ACTIVE_FLAG=1 ");
		if (where != null && !DBConstants.ALLRECORDS.equals(where)) {
			sql.append("and ").append(where);
		}

		SqlParam sqlParam = new SqlParam();
		sqlParam.setSql(sql.toString());
		logger.info("select sql={" + sqlParam.getSql() +"}");

		List<HashMap<String, Object>> list = qryCommons(sqlParam);

		if(list == null || list.size() == 0){
			resultJsonBean.setResult("0");
		}else{
			resultJsonBean.setResult(list.get(0).get("cnt").toString());

		}
		resultJsonBean.setStateCode(DBConstants.SUCCESS);
		return Jackson.obj2json(resultJsonBean);
	}

	@Override
	public String commonOperate(ParamBean entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> selectObject(ParamBean entity, Class<T> clazz) throws Exception {
		String requestJson = entity.getStrRequestJson();
		String codeFlag = entity.getStrCodeFlag();

		SelectJsonBean selectJsonBean = Jackson.json2pojo(requestJson, SelectJsonBean.class);

		List<String> fieldList = selectJsonBean.getField();
		String where = selectJsonBean.getWhere();
		PageBean pageBean = selectJsonBean.getPage();

		StringBuilder sql = new StringBuilder("select ");
		if (fieldList == null || fieldList.isEmpty()) {
			sql.append("* ");
		} else {
			for (int i = 0; i < fieldList.size(); i++) {
				if (i == 0 && DBConstants.ALLFIELDS.equals(fieldList.get(0))) {
					sql.append("* ");
					break;
				}
				sql.append(fieldList.get(i));
				if (i != fieldList.size() - 1) {
					sql.append(", ");
				} else {
					sql.append(" ");
				}
			}
		}
		String tableName = codeFlagToTable(codeFlag);
		sql.append("from ").append(tableName);

		sql.append(" where ACTIVE_FLAG=1 ");
		if (where != null && !DBConstants.ALLRECORDS.equals(where)) {
			sql.append("and ").append(where);
		}

		if (pageBean != null && pageBean.getStart() != null && pageBean.getEnd() != null) {
			try {
				int start = Integer.parseInt(pageBean.getStart());//start 1 开始
				int end = Integer.parseInt(pageBean.getEnd());
				sql.append(" limit ").append(start - 1).append(", ").append(end - start + 1);//start 0 开始
			} catch (Exception e) {
			}
		}
		SqlParam sqlParam = new SqlParam();
		sqlParam.setSql(sql.toString());
		logger.info("select sql={" + sqlParam.getSql() +"}");

		return qryCommons(sqlParam, clazz);
	}

	@Override
	public List<String> selectBiidMaxString(ParamBean entity) throws Exception {//业务id存在内存中，首次使用查数据库
		String requestJson = entity.getStrRequestJson();
		String codeFlag = entity.getStrCodeFlag();
		MaxIdJsonBean maxIdJsonBean = Jackson.json2pojo(requestJson, MaxIdJsonBean.class);
		int count = Integer.parseInt(maxIdJsonBean.getIDCOUNT());
		ArrayList<String> list = new ArrayList<String>(count);
		String tableName = codeFlagToTable(codeFlag);
		int idMax;
		synchronized (tableName) {
			if (tablesIdsMap.containsKey(tableName)) {
				idMax = Integer.parseInt(tablesIdsMap.get(tableName));
				for (int i = 0; i < count; i++) {
					idMax++;
					list.add(String.valueOf(idMax));
				}
				tablesIdsMap.put(tableName, String.valueOf(idMax));
			} else {//查库，返回最大业务ID
				String idField = tablesAndViewsMap.get(tableName);
				if (idField == null) {
					throw new RuntimeException("未定义表的业务ID！");
				}
				StringBuilder sql = new StringBuilder("select IFNULL(max(cast(");
				sql.append(idField).append(" as signed)),0) maxId from ").append(tableName);

				SqlParam sqlParam = new SqlParam();
				sqlParam.setSql(sql.toString());
				logger.info("select sql={" + sqlParam.getSql() +"}");

				List<HashMap<String, Object>> list2 = qryCommons(sqlParam);
				Object cnt = list2.get(0).get("maxId");
				if (cnt != null) {
					idMax = Integer.parseInt(cnt.toString());
					for (int i = 0; i < count; i++) {
						idMax++;
						list.add(String.valueOf(idMax));
					}
				} else {
					idMax = 0;
					for (int i = 0; i < count; i++) {
						idMax++;
						list.add(String.valueOf(idMax));
					}
				}
				tablesIdsMap.put(tableName, String.valueOf(idMax));
			}
		}

		return list;
	}

	/**
	 * ====================================================================
	 *函 数 名： @param codeFlag 数据库识别编码
	 *函 数 名： @return
	 *功 能： 将数据库识别编码转换成对应的表或视图名称
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *2016年7月23日 v0.0.1 tang 创建
	====================================================================
	 */
	private String codeFlagToTable(String codeFlag) {
		String tableName = null;
		tableName = tablesAndViewsMap.get(codeFlag);
		if (tableName == null) {
			logger.error("codeFlagToTable codeFlag={" + codeFlag +"}");
			throw new RuntimeException("数据库识别编码无效！");
		}

		return tableName;
	}

	/**
	 * ====================================================================
	 *函 数 名： @param sqlParam
	 *函 数 名： @param clazz
	 *函 数 名： @return
	 *函 数 名： @throws InstantiationException
	 *函 数 名： @throws IllegalAccessException
	 *功 能： 查询数据
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *2016年7月23日 v0.0.1 tang 创建
	====================================================================
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	private <T> List<T> qryCommons(SqlParam sqlParam, Class<T> clazz) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method[] methods = clazz.getMethods();
		List<HashMap<String, Object>> list = qryCommons(sqlParam);
		List<T> resultList = new ArrayList<T>();
		String key = null;
		String methodName = null;
		HashMap<String, Method> fMap = new HashMap<String, Method>();
		for (int i = 0; i < methods.length; i++) {
			methodName = methods[i].getName();
			if (methodName.startsWith("set") && methodName.length() > 3) {
				key = methodName.substring(3).toUpperCase();
				fMap.put(key, methods[i]);
			}
		}

		Set<String> set =null;
		for (HashMap<String, Object> map : list) {//map中，null值不返回该字段
			T t = clazz.newInstance();
			set = new HashSet<String>();//存放每次调用的set方法
			for (String string : map.keySet()) {
				if (fMap.containsKey(string.toUpperCase())) {
					fMap.get(string.toUpperCase()).invoke(t, obj2String(map.get(string)));
					set.add(string.toUpperCase());
				}
			}
			//设置未调用的set方法，使得值为空串
			for (String string : fMap.keySet()) {
				if (!set.contains(string)&& fMap.get(string).getParameterTypes().length==1
						&& fMap.get(string).getParameterTypes()[0].isInstance("")) {
					fMap.get(string).invoke(t, "");
				}
			}
			resultList.add(t);
		}
		return resultList;

	}
	private List<HashMap<String, Object>> qryCommons(SqlParam sqlParam) {
		return sqlSessionTemplate.selectList("common.getCommon", sqlParam);

	}
	private void insertCommons(SqlParam sqlParam) {
		sqlSessionTemplate.insert("common.insertCommon", sqlParam);
	}
//	private void deleteCommons(SqlParam sqlParam) {//只逻辑删除
//		sqlSessionTemplate.delete("common.deleteCommon", sqlParam);
//	}
	private void updateCommons(SqlParam sqlParam) {
		sqlSessionTemplate.update("common.updateCommon", sqlParam);
	}

	private String obj2String(Object obj) {
		String result = null;
		try {
			if (obj instanceof java.sql.Date) {
				result = ((java.sql.Date) obj).toString();
			} else if (obj instanceof Timestamp) {
				result = TimeUtil.date2String(new Date(((Timestamp) obj).getTime()), dateFormat);
			} else if (obj instanceof Integer) {
				result = String.valueOf((Integer) obj);
			} else if (obj instanceof Long) {
				result = String.valueOf((Long) obj);
			} else {
				result = String.valueOf(obj);
			}
		} catch (Exception e) {
			logger.error("obj2String error!", e);
			return obj.toString();
		}
		return result;
	}
}
