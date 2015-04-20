package com.lcore.core.utils;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCTemplateManager {
   private JDBCTemplateManager(){
	   
   }
   
   public static JDBCTemplateManager getInstance(){
	   if(jdbcTemplateManager == null){
		   jdbcTemplateManager = new JDBCTemplateManager();
	   }
	   return jdbcTemplateManager;
   }
   
   private synchronized JdbcTemplate createTemplate(String templateId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String dataSourceName = templateId;
		DataSource dataSource =  (DataSource) SpringFactory.getObject(dataSourceName);
		jdbcTemplate.setDataSource(dataSource);
		return jdbcTemplate;
	}
   
   protected JdbcTemplate getTemplate(String templateId){
	  return createTemplate(templateId);
   }
   
   private static JDBCTemplateManager jdbcTemplateManager= null;
}
