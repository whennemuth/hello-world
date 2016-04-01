package com.bu.ist.hello.world;

import java.util.Properties;

public class JAXBConfigImpl {

	private static final String[][] props = new String[][]{
		new String[]{"application.host", "localhost"},
		new String[]{"app.context.name", "kc"},
		new String[]{"datasource.driver.name", "com.mysql.jdbc.Driver"},
		new String[]{"datasource.url","jdbc:mysql://localhost:3306/kc?verifyServerCertificate=false&amp;requireSSL=false&amp;useSSL=false"},
		new String[]{"datasource.username", "root"},
		new String[]{"datasource.password", ""},
		new String[]{"datasource.ojb.platform", "MySQL"},
		new String[]{"kc.schemaspy.enabled", "false"}		
	};
	
	public JAXBConfigImpl(String configFilePath, Properties baseProps) {
		// TODO Auto-generated constructor stub
	}

	public void parseConfig() {
		// TODO Auto-generated method stub
		
	}

	public void putProperties(Properties properties) {
		// TODO Auto-generated method stub
		
	}

	public String getProperty(String p) {
		for(String[] pair : props) {
			if(pair[0].equals(p)) {
				return pair[1];
			}
		}
		return null;
	}

}
