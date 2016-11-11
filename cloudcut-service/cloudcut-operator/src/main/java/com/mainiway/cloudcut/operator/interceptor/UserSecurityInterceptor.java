package com.mainiway.cloudcut.operator.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mainiway.cloudcut.common.constant.Constants;
import com.mainiway.cloudcut.common.redis.RedisService;
import com.mainiway.cloudcut.common.utils.Tools;

/**
 * ***************************************************************************
 *模块名 : UserSecurityInterceptor
 *文件名 : UserSecurityInterceptor.java
 *创建时间 : 2016年5月10日
 *实现功能 : 用户认证拦截器
 *作者 : liliangjun
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年5月10日 v0.0.1 liliangjun 创建
 ****************************************************************************
 */
@Order(Ordered.LOWEST_PRECEDENCE)
public class UserSecurityInterceptor implements HandlerInterceptor {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	RedisService redisCacheService;

	public UserSecurityInterceptor() {
	}

	public UserSecurityInterceptor(RedisService redisCacheService)
	{
		this.redisCacheService=redisCacheService;
	}

	@Override
	public boolean preHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String jsonStr = null;
//		String ticket = CookieUtils.getCookieValue(request, LoginConst.COOKIE_NAME);
//
//		//1.根据Cookie验证是否有ticket
//		if (Tools.isEmpty(ticket)) {
//			jsonStr = "{\"reCode\":2010,\"reDescr\":\"登录校验失败，请登录后再试(COOKIE_NAME is null)!\"}";
//			if (Tools.notEmpty(request.getParameter("callback")))
//				jsonStr = request.getParameter("callback") + "(" + jsonStr + ")";
//			response.getWriter().println(jsonStr);
//			logger.error("=====用户未登录======");
//			return false;
//		}
//		//2.判断redis中该用户类型是否为个人用户
//		UserEntity user = redisCacheService.get(String.valueOf(ticket),
//				new TypeReference<UserEntity>(){});
//		if(user==null){
//			jsonStr = "{\"reCode\":2010,\"reDescr\":\"登录校验失败，请登录后再试(isLogin is false)!\"}";
//			if (Tools.notEmpty(request.getParameter("callback")))
//				jsonStr = request.getParameter("callback") + "(" + jsonStr + ")";
//			response.getWriter().println(jsonStr);
//			logger.error("=====用户未登录或登录超时======");
//			return false;
//		}
		Object user = request.getSession().getAttribute(Constants.SESSION_USER);
		if (user == null) {
			jsonStr = "{\"reCode\":2010,\"reDescr\":\"登录超时或未登录，请重新登录!\"}";
			if (Tools.notEmpty(request.getParameter("callback")))
				jsonStr = request.getParameter("callback") + "(" + jsonStr + ")";
			response.getWriter().println(jsonStr);
			logger.info("=====用户登录超时或未登录======");
			return false;
		}
		return true; // 只有返回true才会继续向下执行，返回false取消当前请求
	}

	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// System.out.println("请求处理之后进行调用，但是在视图被渲染之前(Controller方法调用之后)");
	}

	@Override
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// System.out.println("在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行(主要是用于进行资源清理工作)");
	}
}
