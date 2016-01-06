package com.lcore.hr.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class GlobalConfigHolder {

    private static Map<String, String> propertiesMap = new HashMap<String, String>();

    private static Env env = null;

    public static Env getEnv() {
		return env;
	}

	public static void setEnv(Env env) {
		GlobalConfigHolder.env = env;
	}

	public static void init() throws IOException {
        InputStream inputStream = GlobalConfigHolder.class.getClassLoader().getResourceAsStream("sys.properties");
        Properties p = new Properties();
        p.load(inputStream);
        for (Object key : p.keySet()) {
            GlobalConfigHolder.setProperty((String) key, (String) p.get(key));
        }
    }

    public static Map<String, String> getPropertiesMap() {
        return propertiesMap;
    }

    public static void setPropertiesMap(Map<String, String> propertiesMap) {
        GlobalConfigHolder.propertiesMap = propertiesMap;
    }

    public static void setProperty(String name, String object) {
        propertiesMap.put(name, object);
    }

    public static String getProperty(String name) {
        return propertiesMap.get(name);
    }
}
