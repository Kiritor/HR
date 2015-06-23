package com.lcore.hr.menu.auth.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lcore.hr.core.entity.Role;
import com.lcore.hr.core.entity.Root;
import com.lcore.hr.core.entity.User;
import com.lcore.hr.menu.auth.service.RoleService;
import com.lcore.hr.menu.auth.service.UserService;
import com.lcore.hr.menu.base.service.impl.BaseServiceImpl;

@Service("roleService")
@Transactional
public class RoleServiceImpl extends BaseServiceImpl implements RoleService{

	@Override
	public List<Root> getRoleList(int offset, int limit, String sort,
			String order, String key) {
		String condition = " 1=1 ";
		if (key != null && !"".equals(key)) {
			condition += " and (obj.name like '%" + key + "%' or obj.remark like '%"+key+"%')";
		}
		if (null != sort && !"".equals(sort))
			condition += " order by " + " obj." + sort + "" + "  " + order;
		return this.getPagedObjListWithCondition(Role.class.getSimpleName(),
				condition, offset, limit);
	}

	@Override
	public void addRole(Role role) throws Exception {
		this.saveObj(role);
	}

	@Override
	public void deleteRole(List<String> ids) throws Exception {
		for (String id : ids) {
			if (id != null && !"".equals(id.trim()))
				this.deleteObj(Role.class.getName(), id);
		}
	}

	@Override
	public void updateRole(Role role) throws Exception {
		this.updateObj(role);
	}

	
}
