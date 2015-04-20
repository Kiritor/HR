package com.lcore.core.utils;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class HibernateTemplateManger{
	
	private HibernateTemplateManger(){
		
	}
	
	public static HibernateTemplateManger getInstance(){
		if(templateManger == null){
			templateManger = new HibernateTemplateManger();
		}
		return templateManger;
	}
	
	protected HibernateTemplate getHibernateTemplate(String templateId){
		cacheHibernateTemplate(templateId);
		return template;
	}

	private synchronized void cacheHibernateTemplate(String templateName) {
		HibernateTemplate ht = (HibernateTemplate) SpringFactory.getObject(templateName);
		template = ht;
	}
	private static HibernateTemplateManger templateManger = null;
	private static HibernateTemplate template = null;
}
