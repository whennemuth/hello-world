package com.bu.ist.hello.world;

import java.util.Properties;

//import org.kuali.rice.core.api.config.property.ConfigContext;
//import org.kuali.rice.core.impl.config.property.JAXBConfigImpl;
import org.springframework.util.ResourceUtils;

public class HelloConfig {

	public static enum DBTYPE {
		MYSQL, MYSQL_DOCKER, MYSQL_LOCAL_DOCKER, ORACLE, ORACLE_DOCKER
	};

	private static final String CONFIG_FILE_PATH = ResourceUtils.CLASSPATH_URL_PREFIX + "META-INF/kc-config.xml";
	private JAXBConfigImpl config;
	private static final String[] overriddenPropertyNames = new String[] {
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
		this(null, DBTYPE.MYSQL);
	}
		
	public HelloConfig(String env, DBTYPE dbtype) {
		try {
			if(env != null) {
				this.env = env;
				System.setProperty("environment", env);
			}
	        // Stop Quartz from "phoning home" on every startup
	        System.setProperty("org.terracotta.quartz.skipUpdateCheck", "true");
	        if(initialize(dbtype)) {
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
	
	private boolean initialize(DBTYPE dbtype) throws Exception {
		Properties baseProps = new Properties();
        baseProps.putAll(System.getProperties());

        config = new JAXBConfigImpl(CONFIG_FILE_PATH, baseProps);
        config.setDbtype(dbtype);

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
		for(String p : overriddenPropertyNames) {
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
		HelloConfig cfg = new HelloConfig("dev", DBTYPE.ORACLE);
		System.out.println(cfg.getOverridenProperties());
	}
}
