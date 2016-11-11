package com.mainiway.cloudcut.common.businesses.base.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mainiway.cloudcut.beans.commons.ParamBean;
import com.mainiway.cloudcut.beans.jsonbean.DeleteJsonBean;
import com.mainiway.cloudcut.beans.jsonbean.InsertJsonBean;
import com.mainiway.cloudcut.beans.jsonbean.MaxIdJsonBean;
import com.mainiway.cloudcut.beans.jsonbean.PageBean;
import com.mainiway.cloudcut.beans.jsonbean.ResultJsonBean;
import com.mainiway.cloudcut.beans.jsonbean.RowsJsonBean;
import com.mainiway.cloudcut.beans.jsonbean.SelectJsonBean;
import com.mainiway.cloudcut.beans.jsonbean.TransJsonBean;
import com.mainiway.cloudcut.beans.jsonbean.UpdateJsonBean;
import com.mainiway.cloudcut.common.beans.RetObject;
import com.mainiway.cloudcut.common.businesses.id.IdGenerator;
import com.mainiway.cloudcut.common.constant.Constants;
import com.mainiway.cloudcut.common.exception.DBOperationException;
import com.mainiway.cloudcut.common.redis.RedisService;
import com.mainiway.cloudcut.common.utils.Tools;
import com.mainiway.cloudcut.dao.MWBDPDao;
import com.mainiway.cloudcut.util.DBConstants;
import com.mainiway.cloudcut.util.DbOperaCode;
import com.mainiway.cloudcut.util.Jackson;
import com.mainiway.cloudcut.util.JsonUtil;
import com.mainiway.cloudcut.util.LocalUtil;


/**
 * ***************************************************************************
 *模块名 : BaseService
 *文件名 : BaseService.java
 *创建时间 : 2016年5月10日
 *实现功能 : 集中管理注入和封装公共方法
 *作者 : liliangjun
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年5月10日 v0.0.1 liliangjun 创建
 *2016年7月10日 v0.0.1 llning    将成员变量mWBDPDao改成@Resource注解，通过applicationContext.xml
 *                                                 中定义一个id为WBDPDao的bean来确定要实例化的类
 ****************************************************************************
 */
@Service("baseService")
public class BaseService {

	@Autowired
	protected RedisService redisCacheService;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private static IdGenerator idGen = new IdGenerator();

	@Value("${wsdl.db.user.account}")
    protected String  account;

	@Value("${wsdl.db.user.password}")
	protected String password;

	@Value("${wsdl.db.url}")
	protected String url;

	@Value("${sso.ticket.timeout}")
	protected String ticketTimeout;

	@Resource
	protected MWBDPDao mWBDPDao;

	//注册ticket
	public <K, T> String registerTicket(K key, T t, String ticket) {
		if (null == t || null == key) {
			return null;
		}
		String uuid = UUID.randomUUID().toString();
		if (ticket == null) {
//			redisCacheService.set(uuid, t,
//					Integer.parseInt(PropertyUtils.getContextProperty("sso.ticket.timeout")));
			redisCacheService.set(uuid, t,
			Integer.parseInt(ticketTimeout));
		} else {
			uuid = ticket;
//			redisCacheService.set(uuid, t,
//					Integer.parseInt(PropertyUtils.getContextProperty("sso.ticket.timeout")));
			redisCacheService.set(uuid, t,
					Integer.parseInt(ticketTimeout));
		}
		return uuid;
	}

	//清除redis中user
	public void removeRedisUser(String ticket) {
		redisCacheService.remove(ticket);
	}


	//获取redis中保存的用户信息
	protected Object getLoginUser(HttpServletRequest request,String userType){
//		String ticket = request.getHeader("");//TODO 获取head中的ticket
//		if(StringUtils.isBlank(ticket)){
//			ticket = CookieUtils.getCookieValue(request, LoginConst.COOKIE_NAME);
//			if(StringUtils.isBlank(ticket)){
//				return null;
//			}
//		}
		Object obj = request.getSession().getAttribute(Constants.SESSION_USER);
		return obj;
	}
//		switch(userType)
//		{
//			case Constants.PERSONAL:
//				obj = redisCacheService.get(String.valueOf(ticket),
//						new TypeReference<UserEntity>(){});
//				return obj;
//
//			case Constants.FACTORY:
//				obj = redisCacheService.get(String.valueOf(ticket),
//						new TypeReference<FactoryUserEntity>(){});
//				return obj;
//
//			case Constants.OPERATE:
//				obj = redisCacheService.get(String.valueOf(ticket),
//						new TypeReference<OperateUserEntity>(){});
//				return obj;
//
//			case Constants.DISTRIBUTOR:
//				obj = redisCacheService.get(String.valueOf(ticket),
//						new TypeReference<DistributorEntity>(){});
//				return obj;
//			default:
//				return null;
//		}



	//设置账户信息
	protected ParamBean setParamBean(String strCodeFlag,String strRequestJson){
		//设置接口账户信息
		ParamBean para = new ParamBean();
		para.setMstrAccount(account);// TODO
		para.setMstrPwd(password);
		para.setStrCodeFlag(strCodeFlag);
		if(strRequestJson!=null){
			para.setStrRequestJson(LocalUtil.UTF82ISO(strRequestJson));
		}
		return para;
	}

	//开启事务
	protected String beginTransaction() throws Exception{
		ParamBean para = setParamBean(DbOperaCode.TRANS_BEGIN,null);
		String retjson = mWBDPDao.transOperate(para);
		return Jackson.json2pojo(retjson, ResultJsonBean.class).getResult();
	}

	//提交事务
	protected void commitTransaction(String transCode) throws Exception{
		TransJsonBean transJsonBean = new TransJsonBean();
		transJsonBean.setTransCode(transCode);
		ParamBean para = setParamBean(DbOperaCode.TRANS_COMMIT,Jackson.obj2json(transJsonBean));
		mWBDPDao.transOperate(para);
	}

	//回滚事务
	protected void rollbackTransaction(String transCode) throws Exception{
		//to do
	}

	//查询最新最大业务ID
	protected List<String> selectMaxId(String strCodeFlag,Integer idCount) throws Exception{
		MaxIdJsonBean maxIdjsonBean = new MaxIdJsonBean();
		maxIdjsonBean.setIDCOUNT(String.valueOf(idCount));
		//ParamBean para = setParamBean(strCodeFlag, Jackson.obj2json(maxIdjsonBean));
		//List<String> idList = mWBDPDao.selectBiidMaxString(para);
		List<String> idList = new ArrayList<String>();
		for (int i = 0; i < idCount; i++) {
			idList.add(String.valueOf(idGen.generateUUID()));
		}

		if(idList.size()>0){
			return idList;
		}else{
			throw new RuntimeException("call db service failed, ID is null");
		}
	}

	//查询行数
	protected int selectRows(String strCodeFlag,String where) throws Exception{
		RowsJsonBean rowsJsonBean =  new RowsJsonBean();
		if(where==null||where.isEmpty()){
			where=DBConstants.ALLRECORDS;
		}
		rowsJsonBean.setWhere(where);
		String rowsJson = Jackson.obj2json(rowsJsonBean);
		ParamBean entity = setParamBean(strCodeFlag,rowsJson);
		logger.info(strCodeFlag+"-----:"+rowsJson);
		String retjson = mWBDPDao.selectRows(entity);
		return Integer.valueOf(Jackson.json2pojo(retjson, ResultJsonBean.class).getResult());
	}

	//查询行数(去除重复)
	protected int selectRows(String strCodeFlag,String where,List<String> field)throws Exception{
		int rows = 0;
		//获取默认的selectBean
		SelectJsonBean selectBean = getSelectBean(null,null,null);
		//设置where条件
		if(where==null||where.isEmpty()){
			where=DBConstants.ALLRECORDS;
		}
		selectBean.setField(field);
		selectBean.setWhere(where);
		//selectBean转为json串
		String selectJson = Jackson.obj2json(selectBean);
		//封装db接口参数
		ParamBean para = setParamBean(strCodeFlag,selectJson);
		//调用db接口
		logger.info(strCodeFlag+"-----:"+selectJson);
		String retjson = mWBDPDao.select(para);
		ResultJsonBean resultJsonBean = JsonUtil.json2Obj(LocalUtil.ISO2UTF8(retjson),ResultJsonBean.class);
		//"Result":[{"COUNT(DISTINCT ORDER_ID)":"2"}]
		Map<String,Object> map = Jackson.json2map(resultJsonBean.getResult().substring(1, resultJsonBean.getResult().length()-1));
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			rows = Integer.valueOf(entry.getValue().toString());
		}
		return rows;
	}

	//获取默认配置的 selectJsonBean
	//{"Field":["ALLFIELDS"],"Page":{"Start":"1","End":"10"},"TransCode":"NOTRANS"}
	protected SelectJsonBean getSelectBean(List<String> fileds,PageBean pageBean,String transCode ){
		SelectJsonBean selectBean = new SelectJsonBean();
		if(fileds==null){
			fileds = new ArrayList<String>();
			fileds.add(DBConstants.ALLFIELDS);
		}
		selectBean.setField(fileds);
		if(pageBean==null){
			pageBean = new PageBean(DBConstants.PAGESTART,DBConstants.PAGEEND);
		}
		selectBean.setPage(pageBean);
		if(transCode==null){
			transCode=DBConstants.NOTRANS;
		}
		selectBean.setTransCode(transCode);
		return selectBean;
	}

	//获取默认配置的 UpdateJsonBean、deleteJsonBean、insertJsonBean
	//{"TransCode":"NOTRANS"}
	protected Object getCommonBean(String transCode,String type){
		if(transCode==null){
			transCode=DBConstants.NOTRANS;
		}
		switch(type)
		{
			case Constants.UPDATE:
				UpdateJsonBean updateJsonBean = new UpdateJsonBean();
				updateJsonBean.setTransCode(transCode);
				return updateJsonBean;

			case Constants.DELETE:
				DeleteJsonBean deleteJsonBean = new DeleteJsonBean();
				deleteJsonBean.setTransCode(transCode);
				return deleteJsonBean;

			case Constants.INSERT:
				InsertJsonBean insertJsonBean = new InsertJsonBean();
				insertJsonBean.setTransCode(transCode);
				return insertJsonBean;
			default:
				return null;
		}
	}


	//基础查询方法
	protected <T> List<T> baseSelectObject(String where,PageBean page, Class<T> clazz,String strCodeFlag,String selectName)throws Exception{
		//获取默认的selectBean
		SelectJsonBean selectBean = getSelectBean(null,page,null);
		//设置where条件
		if(where==null||where.isEmpty()){
			where=DBConstants.ALLRECORDS;
		}
		selectBean.setWhere(where);
		//selectBean转为json串
		String selectJson = Jackson.obj2json(selectBean);
		//封装db接口参数
		ParamBean para = setParamBean(strCodeFlag,selectJson);
		//调用db接口
		logger.info(selectName+"---selectJson:"+selectJson);
		List<T> list = mWBDPDao.selectObject(para,clazz);
		return list;
	}
	/**
	 * ====================================================================
	 *函 数 名： @param fileds
	 *函 数 名： @param where
	 *函 数 名： @param page
	 *函 数 名： @param clazz
	 *函 数 名： @param strCodeFlag
	 *函 数 名： @param selectName
	 *函 数 名： @return
	 *函 数 名： @throws Exception
	 *功 能： 查询具体字段
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *2016年8月8日 v0.0.1 tang 创建
	====================================================================
	 */
	protected <T> List<T> selectObject(List<String> fileds, String where,PageBean page, Class<T> clazz,String strCodeFlag,String selectName)throws Exception{
		//获取默认的selectBean
		SelectJsonBean selectBean = getSelectBean(fileds,page,null);
		//设置where条件
		if(where==null||where.isEmpty()){
			where=DBConstants.ALLRECORDS;
		}
		selectBean.setWhere(where);
		//selectBean转为json串
		String selectJson = Jackson.obj2json(selectBean);
		//封装db接口参数
		ParamBean para = setParamBean(strCodeFlag,selectJson);
		//调用db接口
		logger.info(selectName+"---selectJson:"+selectJson);
		List<T> list = mWBDPDao.selectObject(para,clazz);
		return list;
	}

	//基础新增方法
	@SuppressWarnings("rawtypes")
	protected ResultJsonBean baseInsert( Object obj,String strCodeFlag,String insertName,String transCode) throws Exception{
		InsertJsonBean insertJsonBean = (InsertJsonBean)getCommonBean(transCode, Constants.INSERT);
		List<Object> entityList = new ArrayList<Object>();
		//设置对象list
		if( obj instanceof java.util.List){
			insertJsonBean.setField((ArrayList)obj);
		}else if(obj instanceof InsertJsonBean){
			insertJsonBean = (InsertJsonBean)obj;
		}else{
			entityList.add(obj);
			insertJsonBean.setField(entityList);
		}
		//设置TransCode
		if(transCode==null){
			transCode=DBConstants.NOTRANS;
		}
		insertJsonBean.setTransCode(transCode);
		//insertJsonBean转为json串
		String insertJson = Jackson.obj2json(insertJsonBean);
		//封装db接口参数
		ParamBean para = setParamBean(strCodeFlag,insertJson);
		//调用db接口
		logger.info(insertName+"---insertJson:"+insertJson);
		String retjson = mWBDPDao.insert(para);
		//处理返回json
		ResultJsonBean responseJsonBean = Jackson.json2pojo(retjson,ResultJsonBean.class);
		return responseJsonBean;
	}

	//基础更新方法
	protected ResultJsonBean baseUpdate(Object obj,String where,String strCodeFlag,String updateName,String transCode) throws Exception{
		UpdateJsonBean updateJsonBean = (UpdateJsonBean)getCommonBean(transCode, Constants.UPDATE);
		if(obj instanceof UpdateJsonBean){
			updateJsonBean = (UpdateJsonBean)obj;
		}else if(obj instanceof Map){
			updateJsonBean.setField(obj);
		}else{
			updateJsonBean.setField(obj);
		}
		//设置where条件
		if(where==null||where.isEmpty()){
			throw new RuntimeException("call db service failed, where is null");
		}
		updateJsonBean.setWhere(where);
		//设置TransCode
		if(transCode==null){
			transCode=DBConstants.NOTRANS;
		}
		updateJsonBean.setTransCode(transCode);
		//updateBean转为json串
		String updateJson = Jackson.obj2json(updateJsonBean);
		//封装db接口参数
		ParamBean para = setParamBean(strCodeFlag,updateJson);
		//调用db接口
		logger.info(updateName+"---updateJson:"+updateJson);
		String retjson = mWBDPDao.update(para);
		 //处理返回json
		 ResultJsonBean responseJsonBean = Jackson.json2pojo(retjson,ResultJsonBean.class);
		 return responseJsonBean;
	}

	//基础删除方法
	protected ResultJsonBean baseDelete(String where,String strCodeFlag,String deleteName,String transCode) throws Exception{
		//获取默认的deleteJsonBean
		DeleteJsonBean deleteBean = (DeleteJsonBean)getCommonBean(transCode,Constants.DELETE);
		//设置where条件
		if(where==null||where.isEmpty()){
			throw new RuntimeException("call db service failed, where is null");
		}
		deleteBean.setWhere(where);
		//deleteJson转为json串
		String deleteJson = Jackson.obj2json(deleteBean);
		//封装db接口参数
		ParamBean para = setParamBean(strCodeFlag,deleteJson);
		//调用db接口
		logger.info(deleteName+"---deleteJson:"+deleteJson);
		String retjson = mWBDPDao.delete(para);
		//处理返回json
		ResultJsonBean responseJsonBean = Jackson.json2pojo(retjson,ResultJsonBean.class);
		return responseJsonBean;
	}


	//基础异常处理方法
	protected void baseHandleException(Exception e,RetObject ro,String fileName,String methodName){
		logger.error("baseHandleException:", e);
		if(e instanceof JsonParseException ||e instanceof JsonMappingException||e instanceof IllegalArgumentException){
			ro.setReCode(Constants.JSONFORMATERROR);
//			ro.setReDescr(Constants.JSONFORMATERRORDES+" Error reason is: "+e.getMessage());
			ro.setReDescr(Constants.JSONFORMATERRORDES);
		}else if(e instanceof DBOperationException){
			ro.setReCode(Constants.DBOPERATEFAILURE);
//			ro.setReDescr(Constants.DBOPERATEFAILUREDES+" Error reason is: "+e.getMessage());
			ro.setReDescr(Constants.DBOPERATEFAILUREDES);
		} else{
			ro.setReCode(Constants.EXCEPTIONSYSTEM);
//			ro.setReDescr(e.getMessage());
			ro.setDescr();
		}
	}


	//基础处理jsonStr方法
	protected String baseHandleJsonStr(String jsonStr,RetObject ro,HttpServletRequest request) throws Exception{
		jsonStr = Jackson.obj2json(ro);
		if (Tools.notEmpty(request.getParameter("callback"))) {
			jsonStr = request.getParameter("callback") + "(" + jsonStr + ")";
		}
		return jsonStr;
	}

	//解析并封装page条件
	protected PageBean setPageParam(RetObject ro,Map<String,Object> paramMap,PageBean page,String strCodeFlag,String where) throws Exception{
		//检查page条件
		String START = (String)paramMap.get("START");
		String SIZE = (String)paramMap.get("SIZE");
		if(StringUtils.isNotBlank(START) && StringUtils.isNotBlank(SIZE)){
			//查询记录条数
			int rows = selectRows(strCodeFlag, where);
			int start = (Integer.valueOf(START));
			int size = (Integer.valueOf(SIZE));
			//计算并设置起始记录数
			page.setStart(String.valueOf((start-1)*size+1));
			//计算并设置结束记录数
			page.setEnd((start*size>rows)? String.valueOf(rows) : String.valueOf(start*size));
			//安全性检测。。。防止数据库报错！
			if(Integer.valueOf(page.getEnd())<Integer.valueOf(page.getStart())){
				page.setStart(DBConstants.PAGESTART);
				page.setEnd(DBConstants.PAGEEND);
			}
			//计算并设置总页数
			//ro.setTotal(String.valueOf((rows+size-1)/ size));
			ro.setTotal(String.valueOf(rows));
		}
		return page;
	}

	//处理视图的分页参数问题
	protected RetObject handlePageForView(RetObject ro,List<?> UIs,Map<String,Object> paramMap)throws Exception{
		if(UIs.size()>0){
			if(paramMap!=null){
				String START = (String)paramMap.get("START");
				String SIZE = (String)paramMap.get("SIZE");
				if(StringUtils.isNotBlank(START) && StringUtils.isNotBlank(SIZE)){
					//查询记录条数
					int rows = UIs.size();
					int start = (Integer.valueOf(START));
					int size = (Integer.valueOf(SIZE));
					//ro.setTotal(String.valueOf((rows+size-1)/ size));
					ro.setTotal(String.valueOf(rows));
					int fromIndex = (start-1)*size;
					int toIndex = (start*size>rows)? rows : start*size;
					if(fromIndex>=toIndex){
						ro.setReCode(Constants.PAGEPARAMFALSE);
						ro.setReDescr(Constants.PAGEPARAMFALSEDES);
						return ro;
					}else{
						UIs = UIs.subList(fromIndex,toIndex);
					}
				}
			}
			ro.setObj(UIs);
			ro.setReCode(Constants.SUCCESS);
			ro.setReDescr(Constants.SUCCESSDES);
		}else{
			ro.setReCode(Constants.RESULTNULL);
			ro.setReDescr(Constants.RESULTNULLDES);
		}
		return ro;
	}


	//获取平台通用日期格式的当前日期时间
	protected  String getCurDateTime(){
		return LocalUtil.getSDFStr(new Date(), Constants.DTAEFORMAT);
	}
	//获取加日期格式的当前日期时间
	protected  String getCurDateTime(String format){
		return LocalUtil.getSDFStr(new Date(), format);
	}
}
