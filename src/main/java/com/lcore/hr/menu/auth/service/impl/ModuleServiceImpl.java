package com.lcore.hr.menu.auth.service.impl;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lcore.hr.core.entity.Module;
import com.lcore.hr.core.entity.Root;
import com.lcore.hr.menu.auth.service.ModuleService;
import com.lcore.hr.menu.base.service.impl.BaseServiceImpl;
import com.lcore.hr.utils.GeneralBeanOrMapUtils;

@Service("moduleService")
@Transactional
public class ModuleServiceImpl extends BaseServiceImpl implements ModuleService{

	@Override
	public void addModule(Module module) throws Exception {
        this.save(module);		
	}

	@Override
	public List<Map<String,Object>> getModuleList(int offset, int limit, String sort,
			String order) throws Exception {
		List<Root> roots =  this.getObjListByCondition(Module.class.getName(), null);
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		String pId ="";
		Map<String,Object> temp = new HashMap<String, Object>();
		for(Root root:roots) {
			temp = GeneralBeanOrMapUtils.convertBean2Map(root);
			pId = temp.get("parentId")==null?"":temp.get("parentId").toString();
			if(pId!=null&&!"".equals(pId)){
				Module r = (Module)this.getObjById(Module.class.getName(), pId);
				temp.put("parentName", r.getModuleName());
			}
			resultList.add(temp);
		}
		return resultList;
	}

	@Override
	public void deleteModule(List<String> ids) throws Exception {
		for (String id : ids) {
			if (id != null && !"".equals(id.trim()))
				this.delete(Module.class.getName(), id);
		}
	}

	@Override
	public void updateModule(Module module) throws Exception {
		this.update(module);
	}
 
}
