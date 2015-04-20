package com.lcore.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lcore.core.datamng.BaseDataMng;
import com.lcore.core.entity.Root;
import com.lcore.core.service.BaseService;

@Service("baseService")
@Transactional
public class BaseServiceImpl implements BaseService{

	@Resource
	public BaseDataMng baseDataMng;
	@Override
	public String saveObj(Root root) throws Exception {
		return baseDataMng.saveObj(root);
	}

}
