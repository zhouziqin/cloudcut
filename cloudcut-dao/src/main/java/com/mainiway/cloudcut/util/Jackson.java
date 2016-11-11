package com.mainiway.cloudcut.util;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * jsonson utils
 * json、pojo和xml互转
 * @author changyong
 *
 */
public class Jackson {

	private static ObjectMapper objectMapper = new ObjectMapper();
	private static XmlMapper xmlMapper = new XmlMapper();

	/**
	 * javaBean,list,array convert to json string
	 */
	public static String obj2json(Object obj) throws Exception{
		return objectMapper.writeValueAsString(obj);
	}

	/**
	 * json string convert to javaBean
	 */
	public static <T> T json2pojo(String jsonStr,Class<T> clazz) throws Exception{
		return objectMapper.readValue(jsonStr, clazz);
	}

	/**
	 * json string convert to map
	 */
	@SuppressWarnings("unchecked")
	public static <T> Map<String,Object> json2map(String jsonStr)throws Exception{
		return objectMapper.readValue(jsonStr, Map.class);
	}

	/**
	 * json string convert to map with javaBean
	 */
	public static <T> Map<String,T> json2map(String jsonStr,Class<T> clazz)throws Exception{
		Map<String,Map<String,Object>> map =  objectMapper.readValue(jsonStr, new TypeReference<Map<String,T>>() {
		});
		Map<String,T> result = new HashMap<String, T>();
		for (Entry<String, Map<String,Object>> entry : map.entrySet()) {
			result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
		}
		return result;
	}

	/**
	 * json array string convert to list with javaBean
	 */
	public static <T> List<T> json2list(String jsonArrayStr,Class<T> clazz)throws Exception{
		List<Map<String,Object>> list = objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {
		});
		List<T> result = new ArrayList<>();
		for (Map<String, Object> map : list) {
			result.add(map2pojo(map, clazz));
		}
		return result;
	}

	/**
	 * map convert to javaBean
	 */
	public static <T> T map2pojo(Map<?,?> map,Class<T> clazz){
		return objectMapper.convertValue(map, clazz);
	}

	/**
	 * json string convert to xml string
	 */
	public static String json2xml(String jsonStr)throws Exception{
		JsonNode root = objectMapper.readTree(jsonStr);
		String xml = xmlMapper.writeValueAsString(root);
		return xml;
	}

	/**
	 * xml string convert to json string
	 */
	public static String xml2json(String xml)throws Exception{
		StringWriter w = new StringWriter();
        JsonParser jp = xmlMapper.getFactory().createParser(xml);
        JsonGenerator jg = objectMapper.getFactory().createGenerator(w);
        while (jp.hasCurrentToken()) {
            jg.copyCurrentEvent(jp);
        }
        jp.close();
        jg.close();
        return w.toString();
	}

	/**
	 * field to json
	 * @param <T>
	 * @param obj
	 * @return
	 * @throws Exception
	 */
/*	public static String field2json(Object obj, Map<String, String> fieldName) throws Exception{
		Class<?> clzz = obj.getClass();
		Field[] field = clzz.getDeclaredFields();
		List<Field2Json> list = new ArrayList<Field2Json>();
		for (Field f : field) {
			f.setAccessible(true);
			if (Tools.notEmpty(fieldName.get(f.getName()))) {
				list.add(new Field2Json(fieldName.get(f.getName()), ""+f.get(obj)));
			}
		}
		return obj2json(list);
	}*/

	/**
	 * javaBean,list,array convert to json string have total
	 * json{'total':28,'rows':[]}
	 */
	public static String list2jsonHtotal(List<?> obj) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", obj.size());
		map.put("rows", obj);
		return obj2json(map);
	}

	/**
	 * javaBean,list,array convert to json string have total
	 * json{'total':28,'rows':[]}
	 */
	public static String list2jsonHtotal(int size, List<?> obj) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", size);
		map.put("rows", obj);
		return obj2json(map);
	}

}

/**
 * 对象属性转字符串使用
 * @author changyong
 *
 */
/*class Field2Json {
	public String attr;
	public String value;
	public Field2Json(String attr, String value) {
		super();
		this.attr = attr;
		this.value = value;
	}
}*/