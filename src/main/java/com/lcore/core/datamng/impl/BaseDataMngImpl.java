package com.lcore.core.datamng.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lcore.core.datamng.BaseDataMng;
import com.lcore.core.entity.Root;
import com.lcore.core.utils.Env;
import com.lcore.core.utils.TemplateFactory;


@Repository("baseDataMng")
@Transactional
public class BaseDataMngImpl implements BaseDataMng {

	public BaseDataMngImpl() {

	}

	protected HibernateTemplate getHibernateTemplate() {
		return TemplateFactory.getHibernateTemplateById("hibernateTemplate");
	}

	protected Session getSession() {
		return getHibernateTemplate().getSessionFactory().getCurrentSession();
	}

	public String saveObj(Root root) throws Exception {
		//压入创建时间,更新时间
		root.setCreateTime(new Date());    
		root.setUpdateTime(new Date());
		//压入创建人,更新人
		root.setCreater(Env.instance().user.getUserName());
		root.setUpdater(Env.instance().user.getUserName());
		return (String) getHibernateTemplate().save(root);
	}

	public void saveOrUpdate(Root root) throws Exception {
        getHibernateTemplate().saveOrUpdate(root);
	}

	public void updateObj(Root root) throws Exception {
		//压入更新时间
		root.setUpdateTime(new Date());
		//压入更新人
		root.setUpdater(Env.instance().user.getUserName());
        getHibernateTemplate().update(root);
	}

	public Root getObj(String className, String id) {
		return (Root) getHibernateTemplate().get(className, id);
	}
	public Root getObj(Class<?> className, String id) {
		return (Root)getHibernateTemplate().get(className, id);
	}

	public void deleteObj(String className, String id) throws Exception {
		Root root = getObj(className, id);
	    getHibernateTemplate().delete(root);
	}

	public void deleteObj(Class<?> className, String id) throws Exception {
		Root root = getObj(className, id);
		getHibernateTemplate().delete(root);
	}

	public Root getObjByCondition(String className, String condition) {
        List<Root> list = getObjListByCondition(className, condition);
        return  (list!=null&&list.size()>0?list.get(0):null);
	}

	public Root getObjByCondition(Class<?> className, String condition) {
		return getObjByCondition(className.getSimpleName(), condition);
	}
	@SuppressWarnings("unchecked")
	public List<Root> getObjListByCondition(String className, String condition) {
		String queryString = "";
		if(condition!=null&&!condition.trim().isEmpty()){
			queryString = (new StringBuffer(" from ")).append(className)
					.append(" as obj where 1=1 and ( ").append(condition)
					.append(" )").toString();
		}else
			queryString  +=  "from "+className+" as obj where 1=1";
		Query query = getSession().createQuery(queryString);
		return query.list();
	}

	public List<Root> getObjListByCondition(Class<?> className, String condition) {
		return getObjListByCondition(className.getSimpleName(),condition);
	}

	/**
	 * HQL
	 */
	public List<Root> getPagedObjListWithCondition(String className,
			String condition, int firstRow, int pageSize) {
		String queryString = "";
		if(condition!=null&&!condition.trim().isEmpty()){
			queryString = (new StringBuffer(" from ")).append(className)
					.append(" as obj where 1=1 and  ").append(condition).toString();
		}
		else
			queryString  +=  "from "+className+" as obj where 1=1";
 		Query query = getSession().createQuery(queryString);
		if(pageSize!=-1) {
		  query.setMaxResults(pageSize);
		  query.setFirstResult(firstRow);
		}
		return query.list();
	}

	public List<Root> getPagedObjListWithCondition(Class<?> className,
			String condition, int firstRow, int pageSize) {
		return getPagedObjListWithCondition(className.getClass().getSimpleName(), condition, firstRow, pageSize);
	}
	
	public long getCountByCondition(String className,String condition){
		return getObjListByCondition(className, condition).size();
	}
}
