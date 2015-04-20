package com.lcore.core.utils;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class TemplateFactory {
	
   private TemplateFactory(){
	   
   }
   
   public static HibernateTemplate getHibernateTemplateById(String templateId){
	   return  HibernateTemplateManger.getInstance().getHibernateTemplate(templateId);
   }
   
   public static JdbcTemplate getJDBCTemplateById(String templateId) {
		return JDBCTemplateManager.getInstance().getTemplate(templateId);
	}
}
