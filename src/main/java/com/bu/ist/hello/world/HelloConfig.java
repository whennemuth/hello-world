package com.bu.ist.hello.world;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.kuali.rice.core.api.config.property.ConfigContext;
import org.kuali.rice.core.impl.config.property.JAXBConfigImpl;
import org.springframework.util.ResourceUtils;

public class HelloConfig {

	private static final String CONFIG_FILE_PATH = ResourceUtils.CLASSPATH_URL_PREFIX + "META-INF/kc-config.xml";
	private JAXBConfigImpl config;
	private static final String[] overridenPropertyNames = new String[] {
			"application.host",	
			"app.context.name",	
			"datasource.driver.name",
			"datasource.url",	
			"datasource.username",	
			"datasource.password",	
			"datasource.ojb.platform",	
			"kc.schemaspy.enabled"
	};
	private String overridenProperties;
	private String env;
	
	public HelloConfig() {
		this(null);
	}
		
	public HelloConfig(String env) {
		try {
			if(env != null) {
				this.env = env;
				System.setProperty("environment", env);
			}
	        // Stop Quartz from "phoning home" on every startup
	        System.setProperty("org.terracotta.quartz.skipUpdateCheck", "true");
	        if(initialize()) {
	        	setOverridenProperties();
	        }
		} 
		catch (Exception e) {
			overridenProperties = Util.stackTraceToString(e);
		}
		if(overridenProperties == null) {
			overridenProperties = "No configuration file found";
		}
	}
	
	private boolean initialize() throws Exception {
		Properties baseProps = new Properties();
        baseProps.putAll(System.getProperties());

        config = new JAXBConfigImpl(CONFIG_FILE_PATH, baseProps);

        try {
            config.parseConfig();
	        config.putProperties(System.getProperties());
	        ConfigContext.init(config);
        } 
        catch (Exception e) {
        	overridenProperties = Util.stackTraceToString(e);
        	return false;
        }
        return true;
	}

	private void setOverridenProperties() {
		StringBuilder s = new StringBuilder();
		for(String p : overridenPropertyNames) {
			String prop = config.getProperty(p);
			prop = Util.isEmpty(prop) ? "[EMPTY!]" : prop;
			s.append(p).append(" = ").append(prop).append("\r\n");
		}
		overridenProperties = s.toString();
	}

	public String getOverridenProperties() {
		return overridenProperties;
	}
	
	public String getProperty(String key) {
		return config.getProperty(key);
	}
	
	public static void main(String[] args) throws Exception {
//		System.out.println("System.getenv(\"user.home\") = " + System.getenv("user.home"));
//		System.out.println("System.getProperty(\"user.home\") = " + System.getProperty("user.home"));
//		System.out.println("System.getenv(\"windows_tracing_logfile\") = " + System.getenv("windows_tracing_logfile"));
//		System.out.println("System.getProperty(\"windows_tracing_logfile\") = " + System.getProperty("windows_tracing_logfile"));
//		System.out.println("System.getenv(\"JAVA_HOME\") = " + System.getenv("JAVA_HOME"));
//		System.out.println("System.getenv(\"MAVEN_HOME\") = " + System.getenv("MAVEN_HOME"));
//		if(true)
//			return;
		HelloConfig cfg = new HelloConfig("dev");
		System.out.println(cfg.getOverridenProperties());
	}
}
