package com.mainiway.cloudcut.user.businesses.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;


/**
 * ***************************************************************************
 *模块名 : MyShiroRealm
 *文件名 : MyShiroRealm.java
 *创建时间 : 2016年10月13日
 *实现功能 : 自定义对拦截处理
 *作者 : Administrator
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年10月13日 v0.0.1 Administrator 创建
 ****************************************************************************
 */
@Component("MyShiroRealm")
public class MyShiroRealm extends AuthorizingRealm{
	public MyShiroRealm() {
		setName("UserRealm");
		// 采用MD5加密
		setCredentialsMatcher(new HashedCredentialsMatcher("md5"));
    }
    //权限资源角色
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		
		return null;
	}
    //验证当前登录的Subject  
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		return null;
	}
}
