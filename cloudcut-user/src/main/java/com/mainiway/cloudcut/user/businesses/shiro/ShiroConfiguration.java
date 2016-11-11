package com.mainiway.cloudcut.user.businesses.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;


/**
 * ***************************************************************************
 *模块名 : ShiroConfigyration
 *文件名 : ShiroConfigyration.java
 *创建时间 : 2016年10月14日
 *实现功能 : shiro配置类 用于替代XML的配置
 *作者 : Administrator
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年10月14日 v0.0.1 Administrator 创建
 ****************************************************************************
 */
@Configuration
public class ShiroConfiguration {
	
	
	 @Bean
	    public EhCacheManager getEhCacheManager() {  
	        EhCacheManager em = new EhCacheManager();  
	        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");  
	        return em;  
	    }  

	    @Bean(name = "myShiroRealm")
	    public MyShiroRealm myShiroRealm(EhCacheManager cacheManager) {  
	        MyShiroRealm realm = new MyShiroRealm(); 
	        realm.setCacheManager(cacheManager);
	        realm.setCredentialsMatcher(hashedCredentialsMatcher()); 
	        return realm;
	    }  
	    
	    /**
	     * ====================================================================
	     *函 数 名： @param securityManager
	     *函 数 名： @return
	     *功 能：   shiro 的web过滤器factory 
	    ----------------------------------------------------------------------
	     *修改记录 ：
	     *日 期  版本 修改人 修改内容
	     *2016年10月14日 v0.0.1 Administrator 创建
	    ====================================================================
	     */
	    @Bean(name = "shiroFilter")
	    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
	    	ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
	    	//shiro 的核心安全接口 
	    	shiroFilterFactoryBean.setSecurityManager(securityManager);
	    	//要求登录的时的连接(可根据项目的URL进行替换) 非必需的属性默认会自动寻找WEB工程根目录下的/login.jsp页面
	    	shiroFilterFactoryBean.setLoginUrl("/login/checkLogin");
            //登陆成功或跳转的逻辑
	    	//shiroFilterFactoryBean.setSuccessUrl("/index"); 
	    	//用户访问未对其授权的资源时,所显示的连接
	    	//shiroFilterFactoryBean.setUnauthorizedUrl("/pages/403"); 
	    	
	    	Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
             //authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
	    	filterChainDefinitionMap.put("/logout", "logout");
	    	filterChainDefinitionMap.put("/webui/**", "anon");
	    	filterChainDefinitionMap.put("/login", "anon");
	    	filterChainDefinitionMap.put("/**", "authc");
	    	shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
			return shiroFilterFactoryBean;
	    }
	    
        //注册securityManager
	    @Bean 
        public DefaultWebSecurityManager securityManager(EhCacheManager cacheManager) { 
	    	DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(); 
	    	securityManager.setRealm(myShiroRealm(cacheManager)); 
	    	securityManager.setCacheManager(getEhCacheManager());
	    	return securityManager;
	    }
	    
	    
        //加密算法
	    public HashedCredentialsMatcher hashedCredentialsMatcher(){ 
	    	HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
	    	hashedCredentialsMatcher.setHashAlgorithmName("md5");//使用MD5加密
	    	hashedCredentialsMatcher.setHashIterations(2);//散列次数 比如两次 就是md5加密两次
			return hashedCredentialsMatcher;
	    }
	    //开启shiro的注解 需借助springAOP的扫描使用shiro的注解类  需配置以下两个bean
	    @Bean
	    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
	        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
	        daap.setProxyTargetClass(true);
	        return daap;
	    }
	    @Bean
	    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
	        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
	        aasa.setSecurityManager(securityManager);
	        return aasa;
	    }
	    //shiro生命周期处理
	    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
	    	return new LifecycleBeanPostProcessor(); 
	    }
	    /**
		 * @see DefaultWebSessionManager
		 * @return  session 控制
		 */
		/*@Bean(name="sessionManager")
		public DefaultWebSessionManager defaultWebSessionManager() {
			DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
			sessionManager.setCacheManager(getEhCacheManager());
			sessionManager.setGlobalSessionTimeout(1800000);
			sessionManager.setDeleteInvalidSessions(true);
			sessionManager.setSessionValidationSchedulerEnabled(true);
			sessionManager.setDeleteInvalidSessions(true);
			return sessionManager;
	   }*/
	    
}
