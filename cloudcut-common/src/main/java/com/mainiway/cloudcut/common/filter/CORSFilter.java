package com.mainiway.cloudcut.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * ***************************************************************************
 *模块名 : CORSFilter
 *文件名 : CORSFilter.java
 *创建时间 : Jul 31, 2016
 *实现功能 : 本过滤器自动为每个响应添加跨域资源共享CORS（Cross-Origin Resource Sharing），让Ajax客户端跨域访问
 *作者 : justin
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *Jul 31, 2016 v0.0.1 justin 创建
 ****************************************************************************
 */
@Component
public class CORSFilter extends OncePerRequestFilter {

	@Value("${cross.allowedOrigins}")
	protected String origins;

	protected String[] hosts;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if(hosts==null){
			hosts = origins.split(";");
		}
		String origin = request.getHeader("origin");
		if(StringUtils.isNotBlank(origin)){
			//CORS 的域名白名单
			for(String host:hosts){
				if(host.equals(origin)){
					response.addHeader("Access-Control-Allow-Origin",host);
					response.addHeader("Access-Control-Allow-Credentials", "true");
					break;
				}
			}
		}
		filterChain.doFilter(request, response);
	}
}
