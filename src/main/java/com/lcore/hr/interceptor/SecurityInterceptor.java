package com.lcore.hr.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lcore.hr.utils.Env;
import com.lcore.hr.utils.GlobalConfigHolder;

/**
 * 
 *  安全拦截器:当用户为空的时候
 *     也就是session为null的时候是不能
 *  直接访问功能模块的(页面资源的)，必须返回进行登录
 */
public class SecurityInterceptor implements HandlerInterceptor{

	private static final String BASE_PATH="/hr";
	public void afterCompletion(HttpServletRequest req,
			HttpServletResponse res, Object arg2, Exception arg3)
			throws Exception {
        
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object arg2) throws Exception {
		 HttpSession httpSession = req.getSession(true);
		 //1、请求到登录页面 放行  
		 if(req.getServletPath().equals("")||req.getServletPath().equals("/")||req.getServletPath().endsWith("login")) {  
		        return true;  
		 }  
		 //2、TODO:游客,登出
		 //3、用户已经登录,放行
         Object object = httpSession.getAttribute("user");
         if(object != null&&GlobalConfigHolder.getEnv().user!=null)
         {
        	 return true;
         }
         
         //4、非法登录
         res.sendRedirect(req.getContextPath() + "/");  
		 return false;
         
	}

}
