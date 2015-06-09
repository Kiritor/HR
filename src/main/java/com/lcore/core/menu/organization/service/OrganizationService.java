package com.lcore.core.menu.organization.service;

import java.util.List;

import com.lcore.core.entity.Organization;
import com.lcore.core.entity.Root;
import com.lcore.core.menu.base.service.BaseService;

public interface OrganizationService extends BaseService{
	/**
	 * 查询组织列表
	 * @param offset      firstRow
	 * @param limit       pageSize:-1表示不分頁
	 * @param sort
	 * @param order
	 * @param key         模糊搜索关键字
	 * @return
	 */
    public abstract List<Root> getOuList(int offset,int limit,String sort,String order,String key);
    /**
     * 添加组织
     * @param org
     * @throws Exception
     */
    public abstract void addOu(Organization org) throws Exception;
    /**
     * 批量删除组织
     * @param ids
     * @throws Exception
     */
    public abstract void deleteOu(List<String> ids) throws Exception;
    
    /**
     * 更新组织
     * @param org
     * @throws Exception
     */
    public abstract void updateOu(Organization org) throws Exception;
    
}
