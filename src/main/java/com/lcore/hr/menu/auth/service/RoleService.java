package com.lcore.hr.menu.auth.service;

import java.util.List;

import com.lcore.hr.core.entity.Role;
import com.lcore.hr.core.entity.Root;
import com.lcore.hr.core.entity.User;

public interface RoleService {
	/**
	 * 查询角色列表
	 * @param offset      firstRow
	 * @param limit       pageSize:-1表示不分頁
	 * @param sort
	 * @param order
	 * @param key         模糊搜索关键字
	 * @return
	 */
    public abstract List<Root> getRoleList(int offset,int limit,String sort,String order,String key);
    /**
     * 添加角色
     * @param org
     * @throws Exception
     */
    public abstract void addRole(Role role) throws Exception;
    /**
     * 批量删除角色
     * @param ids
     * @throws Exception
     */
    public abstract void deleteRole(List<String> ids) throws Exception;
    
    /**
     * 更新角色
     * @param org
     * @throws Exception
     */
    public abstract void updateRole(Role role) throws Exception;
    
    /**
     * 根据角色id得到用户id列表
     * @param roleId
     * @return
     * @throws Exception
     */
    public List<String> getUserIdsByRoleId(String roleId) throws Exception;
    
    /**
     * 根据roleId删除关系
     * @param roleId
     * @throws Exception
     */
    public void deleteUserIdsByRoleId(String roleId) throws Exception;
    
    /**
     * 批量维护角色和用户的关系
     * @param roleId
     * @param userIds
     * @throws Exception
     */
    public void updateRoleToUserRel(String roleId,String[] userIds) throws Exception;
}
