package com.mainiway.cloudcut.common.exception;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mainiway.cloudcut.common.beans.RetObject;
import com.mainiway.cloudcut.common.constant.Constants;
import com.mainiway.cloudcut.util.Jackson;

/**
 * ***************************************************************************
 *模块名 : AllExceptionResolver
 *文件名 : AllExceptionResolver.java
 *创建时间 : 2016年8月3日
 *实现功能 : 异常统一处理
 *作者 : tang
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年8月3日 v0.0.1 tang 创建
 ****************************************************************************
 */
@Component
public class AllExceptionResolver implements HandlerExceptionResolver {
	private Logger log = LoggerFactory.getLogger(AllExceptionResolver.class);

	@Override
	public ModelAndView resolveException(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		log.error("AllExceptionResolver...",ex);
		PrintWriter printWriter = null;
		try {
			RetObject ro = new RetObject();
			if(ex instanceof JsonParseException ||ex instanceof JsonMappingException||ex instanceof IllegalArgumentException){
				ro.setReCode(Constants.JSONFORMATERROR);
//				ro.setReDescr(Constants.JSONFORMATERRORDES+" Error reason is: "+ex.getMessage());
				ro.setReDescr(Constants.JSONFORMATERRORDES);
			}else if(ex instanceof DBOperationException){
				ro.setReCode(Constants.DBOPERATEFAILURE);
//				ro.setReDescr(Constants.DBOPERATEFAILUREDES+" Error reason is: "+ex.getMessage());
				ro.setReDescr(Constants.DBOPERATEFAILUREDES);
			} else{
				ro.setReCode(Constants.EXCEPTIONSYSTEM);
//				ro.setReDescr(ex.getMessage());
				ro.setDescr();
			}
			String ret = Jackson.obj2json(ro);
			printWriter = response.getWriter();
			printWriter.write(ret);
			printWriter.flush();
		} catch (Exception e) {
			log.error("printWriter error!", e);
		}
		finally {
			if (printWriter != null) {
				try {
					printWriter.close();
				} catch (Exception e) {
					printWriter = null;
				}
			}
		}
		return new ModelAndView();
	}

}
