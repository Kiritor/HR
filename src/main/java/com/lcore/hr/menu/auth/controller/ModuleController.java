package com.lcore.hr.menu.auth.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lcore.hr.core.entity.Module;
import com.lcore.hr.core.entity.Root;
import com.lcore.hr.menu.auth.service.ModuleService;
import com.lcore.hr.utils.GeneralBeanOrMapUtils;
import com.lcore.hr.view.ModelView;

@Controller
@RequestMapping("/auth/module")
public class ModuleController extends ModelView {

	@Resource
	ModuleService moduleService;

	@RequestMapping("/addModule")
	@ResponseBody
	public String addModule(HttpServletRequest request,
			HttpServletResponse response, Module module) throws Exception {
		moduleService.addModule(module);
		return "success";
	}

	@RequestMapping("/deleteModule")
	@ResponseBody
	public String deleteModule(HttpServletRequest request,
			HttpServletResponse response,@RequestParam String[] ids) throws Exception {
		List<String> list = new ArrayList<String>();
		for(String id:ids){
			list.add(id);
		}
		moduleService.deleteModule(list);
        return "sucess";
	}
	
	
	@RequestMapping("/getModuleListDropDown")
	@ResponseBody
	public List<Map<String,Object>> getModuleListDropDown(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Map<String,Object>> result = moduleService.getModuleList(0, -1, null, null);
		return result;
	}
	@RequestMapping("/getModuleList")
	@ResponseBody
	public Map<String, Object> getModuleList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> result = moduleService.getModuleList(0, -1, null, null);
		map.put("rows", result);
		map.put("total", result.size());
		return map;
	}

	@RequestMapping("/updateModule")
	@ResponseBody
	public String updateModule(HttpServletRequest request,
			HttpServletResponse response, Module module) throws Exception {
		moduleService.updateModule(module);
		return "success";
	}

	@RequestMapping("/listView")
	public ModelAndView moduleList(HttpServletRequest request,
			HttpServletResponse response) {
		return createLayoutView("auth/module/moduleList", request, response);
	}

	@RequestMapping("/addModuleView")
	public ModelAndView addModuleView(HttpServletRequest request,
			HttpServletResponse response) {
		return createSingleView("auth/module/addModule", request, response);
	}

	@RequestMapping("/updateModuleView")
	public ModelAndView updateModuleView(HttpServletRequest request,
			HttpServletResponse response) {
		return createSingleView("auth/module/updateModule", request, response);
	}
}
