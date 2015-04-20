package com.lcore.core.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

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
		view.setViewName(fileName);
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
		return view;
	}

	protected ModelAndView createLayoutView(String fileName,
			HttpServletRequest request, HttpServletResponse response) {
		return createLayoutView(fileName, null, request, response);
	}
}
