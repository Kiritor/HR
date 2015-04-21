package com.lcore.core.service.impl;

import java.util.List;

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
	@Override
	public void saveOrUpdate(Root root) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateObj(Root root) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Root getObj(String className, String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Root getObj(Class<?> className, String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void deleteObj(String className, String id) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteObj(Class<?> className, String id) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Root getObjByCondition(String className, String condition) {
		return baseDataMng.getObjByCondition(className, condition);
	}
	@Override
	public Root getObjByCondition(Class<?> className, String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<?> getObjListByCondition(String className, String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<?> getObjListByCondition(Class<?> className, String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<?> getPagedObjListWithCondition(String className,
			String condition, int firstRow, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<?> getPagedObjListWithCondition(Class<?> className,
			String condition, int firstRow, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
