package com.lcore.core.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lcore.core.service.OrganizationService;
import com.lcore.core.view.ModelView;

@Controller
@RequestMapping("/ou")
public class OrganizationController extends ModelView{
	@Resource
	private OrganizationService organizationService;
	@RequestMapping("/list")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response) throws Exception{
	   return createLayoutView("ou/ouList", request, response);
	}
	
	@RequestMapping("/getOuList")
	@ResponseBody
	public Map<String,Object>  getOuList(HttpServletRequest request,HttpServletResponse response,
			@RequestParam int limit,@RequestParam int offset,@RequestParam String search,
			@RequestParam String order,@RequestParam String sort) throws Exception{
		Map<String,Object> map = new HashedMap();
		List list = organizationService.getOuList(offset,limit,sort,order,search);
		map.put("total", 12);
		map.put("rows",list);
		return map;
	}
}
