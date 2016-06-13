package com.lcore.hr.menu.base.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lcore.hr.core.entity.User;
import com.lcore.hr.menu.base.service.BaseService;
import com.lcore.hr.utils.Env;
import com.lcore.hr.utils.GlobalConfigHolder;
import com.lcore.hr.view.ModelView;

@Controller
public class LoginController extends ModelView{
	@Resource
	private BaseService baseService;
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response,
			@RequestParam String userName,@RequestParam String password,Boolean isRemeberMe) throws Exception{
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);  
		Subject subject = SecurityUtils.getSubject(); 
		subject.login(token);
		if(null!=isRemeberMe&&isRemeberMe)
			 token.setRememberMe(true);  
		if(subject.isAuthenticated()){
			 AuthenticationInfo info = new SimpleAuthenticationInfo(userName,password,userName);
			 Subject currentUser = SecurityUtils.getSubject(); 
			 Session session = currentUser.getSession();
			 User user = new User();
			 user.setUserName(userName);
			 user.setPassword(password);
			 Env env = new Env();
			 env.setUser(user);
			 session.setAttribute("env",env);
			 GlobalConfigHolder.setEnv(env);
			 ModelAndView view = createLayoutView("admin/index", request, response);
			 return view;
		}else
			return createSingleView("login/login", request, response);

	}
		
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.getSession().removeAttribute("env");
		GlobalConfigHolder.setEnv(null);
		Subject  subject = SecurityUtils.getSubject();
		subject.logout();
		return createSingleView("login/login", request, response);
	}
	
	@RequestMapping("/loginUrl")
	public ModelAndView loginUrl(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return createSingleView("login/login", request, response);
	}

}
