package com.lcore.hr.menu.auth.controller;

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

import com.lcore.hr.core.entity.Root;
import com.lcore.hr.menu.auth.service.RoleService;
import com.lcore.hr.view.ModelView;

@Controller
@RequestMapping("/auth/role")
public class RoleController extends ModelView{
	
	@Resource
	private RoleService roleService;
	/**
	 * 得到角色列表
	 * @param request
	 * @param response
	 * @param limit
	 * @param offset
	 * @param search
	 * @param order
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getRoleList")
	@ResponseBody
	public Map<String,Object>  getRoleList(HttpServletRequest request,HttpServletResponse response,
			@RequestParam int limit,@RequestParam int offset,@RequestParam String search,
			@RequestParam String order,@RequestParam String sort) throws Exception{
		Map<String,Object> map = new HashedMap();
		List<Root> list = roleService.getRoleList(offset,limit,sort,order,search);
		map.put("total", roleService.getRoleList(0, -1, sort, order, search).size());
		map.put("rows",list);
		return map;
	}
    
	
	
	
	
	/**
	 * 跳转到角色列表也米娜
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView roleList(HttpServletRequest request,HttpServletResponse response) {
		return createLayoutView("role/roleList", request, response);
	}
	
	@RequestMapping("/addRoleView")
	public ModelAndView addRoleView(HttpServletRequest request,HttpServletResponse response) {
		return createSingleView("role/addRole", request, response);
	}
	
}
