package com.lcore.core.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lcore.core.entity.Organization;
import com.lcore.core.entity.Root;
import com.lcore.core.service.OrganizationService;

@Service("organizationService")
@Transactional
public class OrganizationServiceImpl extends BaseServiceImpl implements OrganizationService {

	@Override
	public List<Root> getOuList() {
		return (List<Root>) getObjListByCondition(Organization.class.getSimpleName(), null);
	}

}
