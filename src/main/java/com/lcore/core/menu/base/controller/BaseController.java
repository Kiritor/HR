package com.lcore.core.menu.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lcore.core.view.ModelView;

@Controller
public class BaseController extends ModelView{
	
	@RequestMapping("/")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response){
		ModelAndView view = createSingleView("login/login", request, response);
		return view;
	}
	
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response){
		ModelAndView view = createLayoutView("admin/index", request, response);
		return view;
	}

}
