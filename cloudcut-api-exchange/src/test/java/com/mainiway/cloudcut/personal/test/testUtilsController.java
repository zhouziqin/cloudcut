package com.mainiway.cloudcut.personal.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.mainiway.cloudcut.common.beans.RequestMappingInfoEntity;
import com.mainiway.cloudcut.common.utils.CompareMapper;
import com.mainiway.cloudcut.common.utils.Tools;
import com.mainiway.cloudcut.util.Jackson;

/**
 * ***************************************************************************
 *模块名 :接口测试类
 *文件名 : testUtilsController.java
 *创建时间 : 2016年3月3日
 *实现功能 :
 *作者 : laizw
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年3月3日 v0.0.1 laizw 创建
 ****************************************************************************
 */
@RestController
@RequestMapping(value="/testUtils")
public class testUtilsController {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @RequestMapping(value="/")
    public ModelAndView defaultPage()
    {
    	return new ModelAndView("/views/testUtils.html");
    }

    @RequestMapping(value="/testPost")
    public ModelAndView testPost()
    {
    	return new ModelAndView("/views/testPost.html");
    }


    /**
     * 查看项目所有URL对应的Controller和方法
     */
    @RequestMapping(value = "/getUrlList")
    public String getUrlList(HttpServletRequest request,String moduleName) throws Exception {

    	Map<String, Object> listMap = new HashMap<String, Object>();
        List<RequestMappingInfoEntity> listMapping=null;
        RequestMappingInfoEntity mappingQuery  = null;
		try {
			Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
			listMapping = new ArrayList<RequestMappingInfoEntity>();
			int i=0;
			for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
			    RequestMappingInfo info = m.getKey();
			    HandlerMethod method = m.getValue();
			    RequestMappingInfoEntity mapping=new RequestMappingInfoEntity();
			    mapping.setId(i++);
			    mapping.setMethodName(method.getMethod().getName());//方法名
			    mapping.setUrl(info.getPatternsCondition().toString().replaceAll("\\[|\\]",""));//url路径
			    mapping.setClassName(method.getMethod().getDeclaringClass().toString()); //类名
			    if(StringUtils.isNotBlank(moduleName)&&(!"null".equals(moduleName))){
			    	 if(mapping.getUrl().split("/")[1].equals(moduleName)){
			    		 listMapping.add(mapping);
			    	 }
			    }else{
			    	 listMapping.add(mapping);
			    }

			    //对Query结尾url另外添加一条有参数的记录
			    mappingQuery = null;
			    if(mapping.getMethodName().endsWith("Query")){
			    	 mappingQuery = new RequestMappingInfoEntity();
			    	if(mapping.getUrl().split("/")[1].equals("REC01")){
			    		mappingQuery.setUrl(info.getPatternsCondition().toString().replaceAll("\\[|\\]","")+
				    			"?param={\"Field\":[],\"Where\":\"\",\"Page\":[2,10]}");//url路径
			    	}else{
			    		mappingQuery.setUrl(info.getPatternsCondition().toString().replaceAll("\\[|\\]","")+
				    			"?queryField=xxx&queryMethod=xxx&queryData=xxx&currentPage=1&pageSize=10");//url路径
			    	}
			    	 mappingQuery.setId(i++);
			    	 mappingQuery.setMethodName(method.getMethod().getName());
			    	 mappingQuery.setClassName(method.getMethod().getDeclaringClass().toString());
			    }

			    //处理Query结尾的url的存入集合
			    if(mappingQuery!=null){
			    	if(StringUtils.isNotBlank(moduleName)&&(!"null".equals(moduleName))){
				    	 if(mappingQuery.getUrl().split("/")[1].equals(moduleName)){
				    		 listMapping.add(mappingQuery);
				    	 }
				    }else{
				    	 listMapping.add(mappingQuery);
				    }
			    }
			    Collections.sort(listMapping, new CompareMapper());
			    listMap.put("listMapping", listMapping);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

        String jsonp=request.getParameter("callback");
        if(Tools.notEmpty(jsonp))
        	jsonp=jsonp +"("+Jackson.obj2json(listMap)+")";
        else
        	jsonp=Jackson.obj2json(listMap);
        return jsonp;
    }


    /**
     * 查看项目所有URL对应的模块名
     */
    @RequestMapping(value = "/getModuleNameList")
    public String getModuleNameList(HttpServletRequest request) throws Exception {

    	Map<String, Object> listMap = new HashMap<String, Object>();
        Set<String> setModuleName=null;
		try {
			Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
			setModuleName = new TreeSet<String>();
			for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
			    String moduleName = m.getKey().getPatternsCondition().toString().replaceAll("\\[|\\]","").split("/")[1];
			    if(!"error".equals(moduleName) && !"test".equals(moduleName)&&!"testUtils".equals(moduleName)){
			    	setModuleName.add(moduleName);
			    }
			    listMap.put("setModuleName", setModuleName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

        String jsonp=request.getParameter("callback");
        if(Tools.notEmpty(jsonp))
        	jsonp=jsonp +"("+Jackson.obj2json(listMap)+")";
        else
        	jsonp=Jackson.obj2json(listMap);
        return jsonp;
    }
}

