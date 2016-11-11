package com.mainiway.cloudcut.common.interceptor;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mainiway.cloudcut.common.constant.Constants;
import com.mainiway.cloudcut.common.utils.Tools;
/**
 * ***************************************************************************
 *模块名 : SQLInjectionInterceptor
 *文件名 : SQLInjectionInterceptor.java
 *创建时间 : 2016年8月23日
 *实现功能 : 防SQL注入拦截
 *作者 : tang
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年8月23日 v0.0.1 tang 创建
 ****************************************************************************
 */
public class SQLInjectionInterceptor implements HandlerInterceptor {
	private static final Logger LOGGER = LoggerFactory.getLogger(SQLInjectionInterceptor.class);
	private String filterWords = "(?i)insert\\s+|delete\\s+|update\\s+|select\\s+|truncate\\s+|drop\\s+|union[\\s+\\(]|\\s+and\\s+|\\s+or\\s+|;|'|<>|!=|=|in\\s*\\(|not\\s+in|exists\\s*\\(|not\\s+exists";

	public SQLInjectionInterceptor() {
	}

	public SQLInjectionInterceptor(String filterWords) {
		if (StringUtils.isNotBlank(filterWords)) {
			this.filterWords = filterWords;
		}

	}

	@Override
	public boolean preHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Map<String, String[]> map = request.getParameterMap();
		Pattern pattern = Pattern.compile(filterWords);
		Matcher matcher = null;
		String[] values = null;
		String jsonStr = null;
		for (String key : map.keySet()) {
			values = map.get(key);
			for (int i = 0; i < values.length; i++) {
				matcher = pattern.matcher(values[i]);
				if (matcher.find()) {
					LOGGER.warn("非法参数值！value=[{}]", matcher.group());
					jsonStr = "{\"reCode\":" + Constants.SENSITIVEVALUE + ",\"reDescr\":\"" + Constants.SENSITIVEVALUEDES + "\"}";
					if (Tools.notEmpty(request.getParameter("callback")))
						jsonStr = request.getParameter("callback") + "(" + jsonStr + ")";
					response.getWriter().println(jsonStr);
					return false;
				}
			}

		}
		return true;
	}

	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
