package com.lcore.hr.menu.auth.service;

import java.util.List;
import java.util.Map;

import com.lcore.hr.core.entity.Module;

public interface ModuleService {
	public abstract void addModule(Module module) throws Exception;
	public abstract List<Map<String,Object>> getModuleList(int offset,int limit,String sort,String order) throws Exception;
	public abstract void deleteModule(List<String> ids) throws Exception;
	public abstract void updateModule(Module module) throws Exception;
}
