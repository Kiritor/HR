package com.lcore.core.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lcore.core.service.BaseService;
import com.lcore.core.view.ModelView;

@Controller
public class LoginController extends ModelView{
	@Resource
	private BaseService baseService;
	@RequestMapping("login")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return createLayoutView("admin/index", request, response);	
	}
		

}
