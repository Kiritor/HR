package com.lcore.core.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lcore.core.service.BaseService;
import com.lcore.core.service.OrganizationService;
import com.lcore.core.view.ModelView;

@Controller
@RequestMapping("/ou")
public class OrganizationController extends ModelView{
	@Resource
	private BaseService baseService;
	@Resource
	private OrganizationService organizationService;
	@RequestMapping("/list")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response) throws Exception{
	   return createLayoutView("ou/ouList", request, response);
	}
	
	@RequestMapping("/getOuList")
	@ResponseBody
	public Map<String,Object>  getOuList(HttpServletRequest request,HttpServletResponse response) throws Exception{
	    Map<String,Object> map = new HashMap<String, Object>();
	    Map map1 = new HashMap<String, Object>();
	    List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	    map1.put("id", "ID");
	    map1.put("ouName","ouName");
	    list.add(map1);
	    map.put("total", 2);
	    map.put("rows",list);
		return map;
	}
}
