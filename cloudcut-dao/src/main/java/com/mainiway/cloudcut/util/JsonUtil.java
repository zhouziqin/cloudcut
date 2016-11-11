package com.mainiway.cloudcut.util;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class JsonUtil {

	//对象转为json
	public static String obj2json(Object obj) throws Exception{
		return JSONObject.toJSONString(obj);
	}

	//json转为对象
	public static <T> T json2Obj(String jsonString,Class<T> clazz) throws Exception{
		return JSONObject.parseObject(jsonString,clazz);
	}

	//json转为list集合
	public static <T> List<T>  json2list(String jsonString,Class<T> clazz) throws Exception{
		return JSONObject.parseArray(jsonString, clazz);
	}


//	public static void main(String[] args) throws Exception{
//		String json = "{\"Field\":[\"ALLFIELDS\"],\"Where\":\"USER_CODE='server@10' and PASSWORD='123456'\",\"Page\":{\"Start\":\"1\",\"End\":\"10\"},\"TransCode\":\"NOTRANS\"}";
//		String json  = "{\"StateCode\":\"SUCCESS\",\"StateDiscrip\":\"Operation is successful\",\"Result\":[{\"GID\":\"1\",\"EXTRACT_DATE\":\"0000-00-00 00:00:00\",\"USER_ID\":\"1\",\"USER_CODE\":\"server@10\",\"NAME_CN\":\"1\",\"NAME_EN\":\"1\",\"PASSWORD\":\"123456\",\"PHOTO\":\"\",\"NICKNAME\":\"\",\"USER_SEX\":\"1\",\"USER_IDENTIFYCARDNO\":\"1\",\"USER_MOBILE\":\"1\",\"USER_MAIL\":\"1\",\"DESIGNER\":\"\",\"USER_TYPE\":\"1\",\"USER_REMARK\":\"1\",\"USER_STATE\":\"1\",\"ACTIVE_FLAG\":\"1\",\"UPDATE_TIME\":\"\",\"UPDATE_PERSON\":\"\",\"CREATE_TIME\":\"\",\"CREATE_PERSON\":\"\"}]}";
//		String json1  = "{\"StateCode\":\"SUCCESS\",\"StateDiscrip\":\"Operation is successful\",\"Result\":}";
//		ResultJsonBean dbResponseModel = json2Obj(json,ResultJsonBean.class);
//		List<UserEntity> list = json2list(dbResponseModel.getResult(),UserEntity.class);
//		System.out.println(dbResponseModel.getResult());
//		String json = "[{\"USER_CODE\":\"testInsert01\",\"PASSWORD\":\"123456\",\"NAME_CN\":\"testcn1\",\"NAME_EN\":\"testen1\",\"USER_SEX\":\"1\",\"EMPLOYEE_ID\":\"1\",\"DEPT_ID\":\"1\",\"POSITION\":\"pos1\",\"USER_IDENTIFYCARDNO\":\"131789\",\"USER_MOBILE\":\"864578\",\"USER_MAIL\":\"tetet@qq\",\"USER_REMARK\":\"remark1\",\"USER_STATE\":\"1\"}]";
//		List<UserEntity> users4insert = (List<UserEntity>)JsonUtil.json2list(json,UserEntity.class);
//		RequestJsonBean requestJsonBean  = new RequestJsonBean();
//		requestJsonBean.setField(users4insert);
//		String requsetJson = JsonUtil.obj2json(requestJsonBean);
//		System.out.println(requsetJson);
//	}
}
