package com.mainiway.cloudcut.user.businesses.usermanager.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mainiway.cloudcut.common.beans.JsonEnvEntity;
import com.mainiway.cloudcut.user.businesses.usermanager.service.LoginService;
import com.mainiway.cloudcut.util.Jackson;
/**
 * shiro登录
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/login")
public class LoginConyroller {
	@Autowired
	LoginService loginService;
	@RequestMapping("/checkLogin")
	public String checkLogin(HttpSession session,HttpServletRequest request, HttpServletResponse response,JsonEnvEntity jsonEnv) throws Exception
	{
		Map<String,Object> paramMap = Jackson.json2map(jsonEnv.getParam());
		String PASSWORD = (String)paramMap.get("PASSWORD");
		String USER_CODE = (String)paramMap.get("USER_CODE");
		UsernamePasswordToken upt = new UsernamePasswordToken(USER_CODE, PASSWORD);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(upt);
			//subject.hasRole("");  权限控制   subject.isPermitted
			//result = loginService.checkLogin(session, request, response, jsonEnv);
		} catch (Exception e) {
			e.printStackTrace();
			//重定向
			//rediect.addFlashAttribute("errorText", "您的账号或密码输入错误!");
			return "/login";
		}
		return "/index";
  }
	//拥有user:del 才能进行删除操作
	/*@RequestMapping("/checkLogin")
	@RequiresPermissions("user:del")  
	public boolean regist(){
		return loginService
	}*/
}
