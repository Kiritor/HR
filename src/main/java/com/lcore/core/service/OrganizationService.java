package com.lcore.core.service;

import java.util.List;

import com.lcore.core.entity.Root;

public interface OrganizationService extends BaseService{
    public abstract List<Root> getOuList();
 }
