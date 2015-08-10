package com.lcore.hr.menu.base.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lcore.hr.core.datamng.BaseDataMng;
import com.lcore.hr.core.entity.Root;
import com.lcore.hr.menu.base.service.BaseService;

@Service("baseService")
@Transactional
public class BaseServiceImpl implements BaseService{

	@Resource
	private BaseDataMng baseDataMng;
	@Override
	public String save(Root root) throws Exception {
		return baseDataMng.saveObj(root);
	}
	@Override
	public void saveOrUpdate(Root root) throws Exception {
		baseDataMng.saveOrUpdate(root);
	}
	@Override
	public void update(Root root) throws Exception {
		baseDataMng.updateObj(root);
	}
	@Override
	public Root getObjById(String className, String id) {
		return baseDataMng.getObj(className, id);
	}
	@Override
	public Root getObjById(Class<?> className, String id) {
		return baseDataMng.getObj(className, id);
	}
	@Override
	public void delete(String className, String id) throws Exception {
		baseDataMng.deleteObj(className, id);
	}
	@Override
	public void delete(Class<?> className, String id) throws Exception {
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
	@Override
	public List<Root> getObjListByHql(String hql, List<Object> objs) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Root> getObjListByHql(String hql, List<Object> objs,
			int firstRow, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Map<String, Object>> getObjListBySql(String sql,
			List<Object> objs) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Map<String, Object>> getObjListBySql(String sql,
			List<Object> objs, int firstRow, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateBySql(String sql, List<Object> objs) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteBySql(String sql, List<Object> objs) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
