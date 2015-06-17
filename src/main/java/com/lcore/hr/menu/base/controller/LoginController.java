package com.lcore.hr.menu.base.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lcore.hr.core.entity.Root;
import com.lcore.hr.core.entity.User;
import com.lcore.hr.menu.base.service.BaseService;
import com.lcore.hr.utils.Env;
import com.lcore.hr.view.ModelView;

@Controller
public class LoginController extends ModelView{
	@Resource
	private BaseService baseService;
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response,
			@RequestParam String userName,@RequestParam String password) throws Exception{
		String condition = " obj.userName= '"+userName+"' and password='"+password+"'";
		Root root = baseService.getObjByCondition(User.class.getSimpleName(), condition);
		if(root!=null){
		   request.getSession().setAttribute("user", (User)root);
		   ModelAndView view = createLayoutView("admin/index", request, response);
		   return view;
		}else 
		   return createSingleView("login/login", request, response);
	}
		
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.getSession().removeAttribute("user");
		Env.instance().user = null;
		return createSingleView("login/login", request, response);
	}

}
