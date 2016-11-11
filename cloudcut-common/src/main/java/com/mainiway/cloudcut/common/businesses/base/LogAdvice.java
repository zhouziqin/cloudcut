package com.mainiway.cloudcut.common.businesses.base;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.firewall.FirewalledRequest;
import org.springframework.stereotype.Component;

/**
 * ***************************************************************************
 *模块名 : LogAdvice
 *文件名 : LogAdvice.java
 *创建时间 : 2016年5月10日
 *实现功能 : 日志打印
 *作者 : liliangjun
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年5月10日 v0.0.1 liliangjun 创建
 ****************************************************************************
 */
@Component
@Aspect
public class LogAdvice {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private String method;

	@Pointcut("execution(* com.businesses..controller..*(..))||"
			+ "execution(* com.businesses.app..*(..))")
	public void aspect() {
	}

	@Before("aspect()")
	public void before(JoinPoint joinPoint) {
		logger.info("====================================================================================================================================");
		for (Object obj : joinPoint.getArgs()) {
			if(!FirewalledRequest.class.isInstance(obj)){
				method = joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName()+"()";
				logger.info(method+" - REQUEST: " + obj);
			}
		}
 	}

	@AfterReturning(returning="result", pointcut="aspect()")
	public void afterReturning(Object result) {
		logger.info(method+" - RESPONSE: " + result);
	}
}





