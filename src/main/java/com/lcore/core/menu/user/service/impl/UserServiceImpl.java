package com.lcore.core.menu.user.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lcore.core.entity.Organization;
import com.lcore.core.entity.Root;
import com.lcore.core.entity.User;
import com.lcore.core.menu.base.service.impl.BaseServiceImpl;
import com.lcore.core.menu.user.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Override
	public List<Root> getUserList(int offset, int limit, String sort,
			String order, String key) {

		String condition = " 1=1 ";
		if (key != null && !"".equals(key)) {
			condition += " and obj.userName like '%" + key + "%'";
		}
		if (null != sort && !"".equals(sort))
			condition += " order by " + " obj." + sort + "" + "  " + order;
		return this.getPagedObjListWithCondition(User.class.getSimpleName(),
				condition, offset, limit);
	}

	@Override
	public void addUser(User user) throws Exception {
		this.saveObj(user);

	}

	@Override
	public void deleteUser(List<String> ids) throws Exception {
		for (String id : ids) {
			if (id != null && !"".equals(id.trim()))
				this.deleteObj(User.class.getName(), id);
		}
	}

	@Override
	public void updateUser(User user) throws Exception {
		this.updateObj(user);
	}

}
