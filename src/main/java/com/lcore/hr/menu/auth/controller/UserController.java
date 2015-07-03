package com.lcore.hr.menu.auth.controller;

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

import com.lcore.hr.core.entity.Root;
import com.lcore.hr.core.entity.User;
import com.lcore.hr.menu.auth.service.UserService;
import com.lcore.hr.view.ModelView;

@Controller
@RequestMapping("/auth/user")
public class UserController extends ModelView{
	
	@Resource
	private UserService userService;
	
	/**
	 * 得到人员列表
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
	@RequestMapping("/getUserList")
	@ResponseBody
	public Map<String,Object>  getUserList(HttpServletRequest request,HttpServletResponse response,
			@RequestParam int limit,@RequestParam int offset,@RequestParam String search,
			@RequestParam String order,@RequestParam String sort) throws Exception{
		Map<String,Object> map = new HashedMap();
		List<Root> list = userService.getUserList(offset,limit,sort,order,search);
		map.put("total", userService.getUserList(0, -1, sort, order, search).size());
		map.put("rows",list);
		return map;
	}
	
	@RequestMapping("/addUser")
	@ResponseBody
	public String addUser(HttpServletRequest request,HttpServletResponse response,
			User user) throws Exception{
	    userService.addUser(user);
	    return "success";
	}
	
	@RequestMapping("/deleteUser")
	@ResponseBody
	public String deleteOu(HttpServletRequest request,HttpServletResponse response,
			@RequestParam String ids) throws Exception{
		String[] idA = ids.split(",");
		List<String> idList = new ArrayList<String>();
		for(int i=0;i<idA.length;i++){
			idList.add(idA[i]);
		}
        userService.deleteUser(idList);
        return "success";
	}
	@RequestMapping("/updateUser")
	@ResponseBody
	public String deleteOu(HttpServletRequest request,HttpServletResponse response,
			 User user) throws Exception{
        userService.updateUser(user);
        return "success";
	}
	
	
	/**
	 * 跳转到人员管理界面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/list")
    public ModelAndView userList(HttpServletRequest request,HttpServletResponse response){
    	return createLayoutView("auth/user/userList", request, response);
    }
	
	/**
	 * 跳转到添加用户页面
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addUserView")
	public ModelAndView addUserView(HttpServletRequest request,HttpServletResponse response) throws Exception{
       return createSingleView("auth/user/addUser", request, response);
	}
	
	/**
	 * 跳转到更新用户页面
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateUserView")
	public ModelAndView updateUserView(HttpServletRequest request,HttpServletResponse response) throws Exception{
       return createSingleView("auth/user/updateUser", request, response);
	}
}
