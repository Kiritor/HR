package com.lcore.hr.menu.base.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.lcore.hr.view.ModelView;

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
	
	@RequestMapping("/lang")
    @ResponseBody
    public String lang(HttpServletRequest request,HttpServletResponse response) {
        String langType = request.getParameter("langType");
        if (langType.equals("zh")) {
            Locale locale = new Locale("zh", "CN");
            //request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
            new CookieLocaleResolver().setLocale(request, response, locale);
        } else if (langType.equals("en")) {
            Locale locale = new Locale("en", "US");
            //request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
            new CookieLocaleResolver().setLocale(request, response, locale);
        } else
        	new CookieLocaleResolver().setLocale(request, response, LocaleContextHolder.getLocale());
            //request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, LocaleContextHolder.getLocale());
        return null;
    }

}
