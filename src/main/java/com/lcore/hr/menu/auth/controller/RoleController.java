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

import com.lcore.hr.core.entity.Role;
import com.lcore.hr.core.entity.Root;
import com.lcore.hr.menu.auth.service.RoleService;
import com.lcore.hr.menu.auth.service.UserService;
import com.lcore.hr.utils.GeneralBeanOrMapUtils;
import com.lcore.hr.view.ModelView;

@Controller
@RequestMapping("/auth/role")
public class RoleController extends ModelView {

	@Resource
	private RoleService roleService;
	
	@Resource
	private UserService userService;

	/**
	 * 得到角色列表
	 * 
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
	public Map<String, Object> getRoleList(HttpServletRequest request,
			HttpServletResponse response, @RequestParam int limit,
			@RequestParam int offset, @RequestParam String search,
			@RequestParam String order, @RequestParam String sort)
			throws Exception {
		Map<String, Object> map = new HashMap<String,Object>();
		List<Root> list = roleService.getRoleList(offset, limit, sort, order,
				search);
		map.put("total", roleService.getRoleList(0, -1, sort, order, search)
				.size());
		map.put("rows", list);
		return map;
	}

	
	/**
	 * 得到角色列表(角色分配)
	 * 
	 * @param request
	 * @param response
	 * @param search
	 * @param order
	 * @param sort
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/roleList")
	@ResponseBody
	public List<Map<String, Object>> roleList(HttpServletRequest request,
			HttpServletResponse response,  @RequestParam String search,
			@RequestParam String order, @RequestParam String sort)
			throws Exception {
		Map<String, Object> map = new HashMap<String,Object>();
		List<Root> list = roleService.getRoleList(0, -1, sort, order,
				search);
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		for(Root root:list){
			result.add(GeneralBeanOrMapUtils.convertBean2Map(root));
		}
		return result;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getUserListByRoleId")
	@ResponseBody
	public Map<String,Object> getUserListByRoleId(HttpServletRequest request,HttpServletResponse response,
			@RequestParam int limit,@RequestParam int offset,@RequestParam String search,
			@RequestParam String order,@RequestParam String sort,
		    String roleId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Root> list = userService.getUserList(offset, limit, sort, order, null);
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		List<String> userIds = roleService.getUserIdsByRoleId(roleId);
		for(Root root:list){
			Map<String,Object> mapUser = GeneralBeanOrMapUtils.convertBean2Map(root);
			if(userIds!=null&&userIds.contains(mapUser.get("id").toString())){
				mapUser.put("selected", true);
			}else
				mapUser.put("selected", false);
			result.add(mapUser);
		}
		map.put("rows", result);
		map.put("total", userService.getUserList(0, -1, sort, order, null).size());
		return map;
	}
	/**
	 * 添加角色
	 * 
	 * @param request
	 * @param response
	 * @param role
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addRole")
	@ResponseBody
	public String addRole(HttpServletRequest request,
			HttpServletResponse response, Role role) throws Exception {
		roleService.addRole(role);
		return "success";
	}

	/**
	 * 删除角色
	 * 
	 * @param request
	 * @param response
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteRole")
	@ResponseBody
	public String deleteRole(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String ids)
			throws Exception {
		String[] idA = ids.split(",");
		List<String> idList = new ArrayList<String>();
		for (int i = 0; i < idA.length; i++) {
			idList.add(idA[i]);
		}
		roleService.deleteRole(idList);
		return "success";
	}

	/**
	 * 更新角色
	 * @param request
	 * @param response
	 * @param role
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateRole")
	@ResponseBody
	public String updateRole(HttpServletRequest request,
			HttpServletResponse response,Role role) throws Exception {
		roleService.updateRole(role);
		return "success";
	}
	
	
	@RequestMapping("/updateRoleToUserRel")
	@ResponseBody
	public String updateRoleToUserRel(HttpServletRequest request,
			HttpServletResponse response,@RequestParam String roleId,
			@RequestParam String[] userIds) throws Exception {
		roleService.updateRoleToUserRel(roleId, userIds);
		return "success";
	}
	
	/**
	 * 跳转到角色列表页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView roleList(HttpServletRequest request,
			HttpServletResponse response) {
		return createLayoutView("auth/role/roleList", request, response);
	}

	/**
	 * 跳转到添加角色界面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addRoleView")
	public ModelAndView addRoleView(HttpServletRequest request,
			HttpServletResponse response) {
		return createSingleView("auth/role/addRole", request, response);
	}
	
	/**
	 * 跳转到更新角色界面
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping("/updateRoleView")
	public ModelAndView updateRoleView(HttpServletRequest request,
			HttpServletResponse response) {
		return createSingleView("auth/role/updateRole",request,response);
	}
	
	
	/**
	 * 跳转到角色分配界面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/roleAllocationView")
	public ModelAndView roleAllocation(HttpServletRequest request,
			HttpServletResponse response) {
		return createLayoutView("auth/role/roleAllocation", request, response);
	}
}
