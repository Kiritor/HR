package com.lcore.core.menu.base.service;

import java.util.List;

import com.lcore.core.entity.Root;

public interface BaseService {
	/**
	 * 保存对象
	 * @param root
	 * @return
	 * @throws Exception
	 */
	public abstract String saveObj(Root root) throws Exception;

	/**
	 * 保存或者更新对象
	 * @param root
	 * @throws Exception
	 */
	public abstract void saveOrUpdate(Root root) throws Exception;

	/**
	 * 更新对象
	 * @param root
	 * @throws Exception
	 */
	public abstract void updateObj(Root root) throws Exception;

	/**
	 * 通过id和实体名字获取对象
	 * @param id
	 * @param className
	 * @return
	 */
	public abstract Root getObj(String className, String id);
	
	/**
	 * 通过id和实体获取对象
	 * @param id
	 * @param className
	 * @return
	 */
	public abstract Root getObj(Class<?> className,String id);
	
	/**
	 * 通过id和实体名字删除纪录
	 * @param id
	 * @param className
	 * @return
	 * @throws Exception
	 */
	public abstract void deleteObj(String className,String id) throws Exception;
	
	/**
	 * 通过id和实体删除对象
	 * @param id
	 * @param className
	 * @return
	 * @throws Exception
	 */
	public abstract void deleteObj(Class<?> className,String id) throws Exception;
	
	/**
	 * 根据条件表达式和实体名得到单条纪录
	 * @param className
	 * @param condition
	 * @return
	 */
	public abstract Root getObjByCondition(String className,String condition);
	
	/**
	 * 根据条件表达式和实体得到单条纪录
	 * @param className
	 * @param condition
	 * @return
	 */
	public abstract Root getObjByCondition(Class<?> className,String condition);
	
	/**
	 * 根据条件表达式和实体名得到集合纪录
	 * @param className
	 * @param condition
	 * @return
	 */
	public abstract List<Root> getObjListByCondition(String className,String condition);
	
	/**
	 * 根据条件表达式和实体得到集合纪录
	 * @param className
	 * @param condition
	 * @return
	 */
	public abstract List<Root> getObjListByCondition(Class<?> className,String condition);

	/**
	 * 根据条件得到分页纪录
	 * @param className
	 * @param condition
	 * @param firstRow
	 * @param pageSize
	 * @return
	 */
    public abstract List<Root> getPagedObjListWithCondition(String className,String condition,int firstRow,int pageSize);

    /**
     * 根据查询条件得到分页纪录
     * @param className
     * @param condition
     * @param firstRow
     * @param pageSize
     * @return
     */
    public abstract List<Root> getPagedObjListWithCondition(Class<?> className,String condition,int firstRow,int pageSize);
    
    /**
     * 根据查询条件获取总的条数
     * @param className
     * @param condition
     * @return
     */
    public long getCountByCondition(String className,String condition);
}
