package com.bu.ist.hello.world;

import java.util.Properties;

import com.bu.ist.hello.world.HelloConfig.DBTYPE;

public class JAXBConfigImpl {
	
	private DBTYPE dbtype = DBTYPE.MYSQL;
	
	private static final String[][] mysqlprops = new String[][]{
		new String[]{"application.host", "localhost"},
		new String[]{"app.context.name", "kc"},
		new String[]{"datasource.driver.name", "com.mysql.jdbc.Driver"},
		new String[]{"datasource.url","jdbc:mysql://localhost:3306/kc?"
				+ "verifyServerCertificate=false"
				+ "&amp;requireSSL=false"
				+ "&amp;useSSL=false"},
		new String[]{"datasource.username", "root"},
		new String[]{"datasource.password", ""},
		new String[]{"datasource.ojb.platform", "MySQL"},
		new String[]{"kc.schemaspy.enabled", "false"}		
	};
	
	private static final String[][] mysqlprops_docker = new String[][]{
		new String[]{"application.host", "kuali_db_mysql"},
		new String[]{"app.context.name", "kc"},
		new String[]{"datasource.driver.name", "com.mysql.jdbc.Driver"},
		new String[]{"datasource.url","jdbc:mysql://kuali_db_mysql:3306/kualicoeusdb?"
				+ "verifyServerCertificate=false"
				+ "&amp;requireSSL=false"
				+ "&amp;useSSL=false"},
		new String[]{"datasource.username", "root"},
		new String[]{"datasource.password", ""},
		new String[]{"datasource.ojb.platform", "MySQL"},
		new String[]{"kc.schemaspy.enabled", "false"}		
	};
	
	private static final String[][] mysqlprops_local_docker = new String[][]{
		new String[]{"application.host", "192.168.56.102"},
		new String[]{"app.context.name", "kc"},
		new String[]{"datasource.driver.name", "com.mysql.jdbc.Driver"},
		new String[]{"datasource.url","jdbc:mysql://192.168.56.102:43306/kualicoeusdb?"
				+ "verifyServerCertificate=false"
				+ "&amp;requireSSL=false"
				+ "&amp;useSSL=false"},
		new String[]{"datasource.username", "root"},
		new String[]{"datasource.password", ""},
		new String[]{"datasource.ojb.platform", "MySQL"},
		new String[]{"kc.schemaspy.enabled", "false"}		
	};
	
	private static final String[][] oracleprops = new String[][]{
		new String[]{"application.host", "usl3.bu.edu"},
		new String[]{"app.context.name", "kc"},
		new String[]{"datasource.driver.name", "oracle.jdbc.driver.OracleDriver"},
		new String[]{"datasource.url","jdbc:oracle:thin:@("
				+ "DESCRIPTION=("
				+ "ADDRESS_LIST=("
				+ "ADDRESS=(PROTOCOL=TCP)"
				+   "(HOST=usl3.bu.edu)"
				+   "(PORT=5803)))"
				+ "(CONNECT_DATA=(SERVICE_NAME=Kuali)))"},
		new String[]{"datasource.username", "kualico"},
		new String[]{"datasource.password", "kualico"},
		new String[]{"datasource.ojb.platform", "Oracle"},
		new String[]{"kc.schemaspy.enabled", "false"}		
	};
	
	private static final String[][] oracleprops_docker = new String[][]{
		new String[]{"application.host", "kuali_db_oracle"},
		new String[]{"app.context.name", "kc"},
		new String[]{"datasource.driver.name", "oracle.jdbc.driver.OracleDriver"},
		new String[]{"datasource.url","jdbc:oracle:thin:@("
				+ "DESCRIPTION=("
				+ "ADDRESS_LIST=("
				+ "ADDRESS=(PROTOCOL=TCP)"
				+   "(HOST=kuali_db_oracle)"
				+   "(PORT=5803)))"
				+ "(CONNECT_DATA=(SERVICE_NAME=Kuali)))"},
		new String[]{"datasource.username", "kualico"},
		new String[]{"datasource.password", "kualico"},
		new String[]{"datasource.ojb.platform", "Oracle"},
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

	public void setDbtype(DBTYPE dbtype) {
		this.dbtype = dbtype;
	}

	public String getProperty(String p) {
		String[][] props;
		switch(dbtype) {
		case MYSQL:
			props = mysqlprops;
			break;
		case MYSQL_DOCKER:
			props = mysqlprops_docker;
			break;
		case MYSQL_LOCAL_DOCKER:
			props = mysqlprops_local_docker;
			break;
		case ORACLE:
			props = oracleprops;
			break;
		case ORACLE_DOCKER:
			props = oracleprops_docker;
			break;
		default:
			props = mysqlprops;
			break;
		}
		for(String[] pair : props) {
			if(pair[0].equals(p)) {
				return pair[1];
			}
		}
		return null;
	}

}
