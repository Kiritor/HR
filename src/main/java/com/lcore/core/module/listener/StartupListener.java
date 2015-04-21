package com.lcore.core.module.listener;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.lcore.core.utils.GlobalConfigHolder;
import com.lcore.core.utils.HibernateTemplateManger;

/**
 * @author Peter
 * @version 2013-11-19
 */
public class StartupListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {}

	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		try {
			initSys();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}

	private void initSys() throws IOException {
		GlobalConfigHolder.init();
	}

}
