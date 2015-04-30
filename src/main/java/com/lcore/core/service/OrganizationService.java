package com.lcore.core.service;

import java.util.List;

import com.lcore.core.entity.Organization;
import com.lcore.core.entity.Root;

public interface OrganizationService extends BaseService{
    public abstract List<Root> getOuList(int offset,int limit,String sort,String order,String key);
    public abstract void addOu(Organization org) throws Exception;
    public abstract void deleteOu(List<String> ids) throws Exception;
}
