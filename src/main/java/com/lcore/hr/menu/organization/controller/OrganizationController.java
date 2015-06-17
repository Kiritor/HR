package com.lcore.hr.menu.organization.controller;

import java.util.ArrayList;
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

import com.lcore.hr.entity.Organization;
import com.lcore.hr.menu.organization.service.OrganizationService;
import com.lcore.hr.view.ModelView;

@Controller
@RequestMapping("/ou")
public class OrganizationController extends ModelView{
	@Resource
	private OrganizationService organizationService;
	
	@RequestMapping("/getOuList")
	@ResponseBody
	public Map<String,Object>  getOuList(HttpServletRequest request,HttpServletResponse response,
			@RequestParam int limit,@RequestParam int offset,@RequestParam String search,
			@RequestParam String order,@RequestParam String sort) throws Exception{
		Map<String,Object> map = new HashedMap();
		List list = organizationService.getOuList(offset,limit,sort,order,search);
		map.put("total", organizationService.getOuList(0, -1, sort, order, search).size());
		map.put("rows",list);
		return map;
	}
	@RequestMapping("/addOu")
	@ResponseBody
	public String addOu(HttpServletRequest request,HttpServletResponse response,
			Organization org) throws Exception{
	    organizationService.addOu(org);
	    return "success";
	}
	@RequestMapping("/deleteOu")
	@ResponseBody
	public String deleteOu(HttpServletRequest request,HttpServletResponse response,
			@RequestParam String ids) throws Exception{
		String[] idA = ids.split(",");
		List<String> idList = new ArrayList<String>();
		for(int i=0;i<idA.length;i++){
			idList.add(idA[i]);
		}
        organizationService.deleteOu(idList);
        return "success";
	}
	@RequestMapping("/updateOu")
	@ResponseBody
	public String deleteOu(HttpServletRequest request,HttpServletResponse response,
			 Organization org) throws Exception{
        organizationService.updateOu(org);
        return "success";
	}
	
	/**
	 * 跳转到组织管理界面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception{
	   return createLayoutView("ou/ouList", request, response);
	}
	/**
	 * 跳转到添加组织界面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addOuView")
	public ModelAndView addOuView(HttpServletRequest request,HttpServletResponse response) throws Exception{
	   return createSingleView("ou/addOu", request, response);
	}
	
	/**
	 * 跳转到更新组织界面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateOuView")
	public ModelAndView updateOuView(HttpServletRequest request,HttpServletResponse response) throws Exception{
	   return createSingleView("ou/updateOu", request, response);
	}
	
}
