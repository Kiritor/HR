package com.lcore.core.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lcore.core.entity.Root;
import com.lcore.core.entity.User;
import com.lcore.core.service.BaseService;
import com.lcore.core.view.ModelView;

@Controller
public class LoginController extends ModelView{
	@Resource
	private BaseService baseService;
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response,
			@RequestParam String userName,@RequestParam String password) throws Exception{
		String condition = " obj.userName= '"+userName+"' and password='"+password+"'";
		Root root = baseService.getObjByCondition(User.class.getSimpleName(), condition);
		if(root!=null)
		   return createLayoutView("admin/index", request, response);
		else 
		   return createSingleView("login/login", request, response);
	}
		

}
