package com.lcore.core.menu.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lcore.core.datamng.BaseDataMng;
import com.lcore.core.entity.Root;
import com.lcore.core.menu.base.service.BaseService;

@Service("baseService")
@Transactional
public class BaseServiceImpl implements BaseService{

	@Resource
	private BaseDataMng baseDataMng;
	@Override
	public String saveObj(Root root) throws Exception {
		return baseDataMng.saveObj(root);
	}
	@Override
	public void saveOrUpdate(Root root) throws Exception {
		baseDataMng.saveOrUpdate(root);
	}
	@Override
	public void updateObj(Root root) throws Exception {
		baseDataMng.updateObj(root);
	}
	@Override
	public Root getObj(String className, String id) {
		return baseDataMng.getObj(className, id);
	}
	@Override
	public Root getObj(Class<?> className, String id) {
		return baseDataMng.getObj(className, id);
	}
	@Override
	public void deleteObj(String className, String id) throws Exception {
		baseDataMng.deleteObj(className, id);
	}
	@Override
	public void deleteObj(Class<?> className, String id) throws Exception {
		baseDataMng.deleteObj(className, id);
	}
	@Override
	public Root getObjByCondition(String className, String condition) {
		return baseDataMng.getObjByCondition(className, condition);
	}
	@Override
	public Root getObjByCondition(Class<?> className, String condition) {
		return baseDataMng.getObjByCondition(className, condition);
	}
	@Override
	public List<Root> getObjListByCondition(String className, String condition) {
		// TODO Auto-generated method stub
		return baseDataMng.getObjListByCondition(className, condition);
	}
	@Override
	public List<Root> getObjListByCondition(Class<?> className, String condition) {
		return baseDataMng.getObjListByCondition(className, condition);
	}
	@Override
	public List<Root> getPagedObjListWithCondition(String className,
			String condition, int firstRow, int pageSize) {
		return baseDataMng.getPagedObjListWithCondition(className, condition, firstRow, pageSize);
	}
	@Override
	public List<Root> getPagedObjListWithCondition(Class<?> className,
			String condition, int firstRow, int pageSize) {
		return baseDataMng.getPagedObjListWithCondition(className, condition, firstRow, pageSize);
	}
	@Override
	public long getCountByCondition(String className, String condition) {
		// TODO Auto-generated method stub
		return baseDataMng.getCountByCondition(className, condition);
	}

}
