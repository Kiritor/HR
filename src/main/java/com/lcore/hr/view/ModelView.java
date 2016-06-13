package com.lcore.hr.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.lcore.hr.core.entity.User;
import com.lcore.hr.utils.Env;
import com.lcore.hr.utils.GlobalConfigHolder;

/**
 * 视图model
 * @author LCore
 *
 */
public class ModelView {
	/**
	 * 单页视图model
	 * @param fileName
	 * @param request
	 * @param response
	 * @return
	 */
	protected ModelAndView createSingleView(String fileName,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		view.addObject("basePath",GlobalConfigHolder.getProperty("basePath"));
		view.setViewName(fileName);
		view.addObject("Env",GlobalConfigHolder.getEnv());
		return view;
	}

	/**
	 * 布局页视图model
	 * @param fileName
	 * @param layout
	 * @param request
	 * @param response
	 * @return
	 */
	protected ModelAndView createLayoutView(String fileName, String layout,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		if (layout != null && !layout.equals("")) {
			view.setViewName(layout);
		} else
			view.setViewName("common/layout");
		view.addObject("header_path", "common/header.vm");
		view.addObject("left_path", "common/left.vm");
		view.addObject("content_path", fileName + ".vm");
		String path = GlobalConfigHolder.getProperty("basePath");
		view.addObject("basePath",GlobalConfigHolder.getProperty("basePath"));
		view.addObject("Env",GlobalConfigHolder.getEnv());
		return view;
	}

	protected ModelAndView createLayoutView(String fileName,
			HttpServletRequest request, HttpServletResponse response) {
		return createLayoutView(fileName, null, request, response);
	}
}
