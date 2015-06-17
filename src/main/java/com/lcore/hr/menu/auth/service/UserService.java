package com.lcore.hr.menu.auth.service;

import java.util.List;

import com.lcore.hr.core.entity.Root;
import com.lcore.hr.core.entity.User;
import com.lcore.hr.menu.base.service.BaseService;

public interface UserService extends BaseService{
	/**
	 * 查询用户列表
	 * @param offset      firstRow
	 * @param limit       pageSize:-1表示不分頁
	 * @param sort
	 * @param order
	 * @param key         模糊搜索关键字
	 * @return
	 */
    public abstract List<Root> getUserList(int offset,int limit,String sort,String order,String key);
    /**
     * 添加组织
     * @param org
     * @throws Exception
     */
    public abstract void addUser(User user) throws Exception;
    /**
     * 批量删除用户
     * @param ids
     * @throws Exception
     */
    public abstract void deleteUser(List<String> ids) throws Exception;
    
    /**
     * 更新用户
     * @param org
     * @throws Exception
     */
    public abstract void updateUser(User user) throws Exception;
    
}
