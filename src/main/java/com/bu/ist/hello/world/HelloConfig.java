package com.bu.ist.hello.world;

public class HelloConfig {

	private String content;
	
	public HelloConfig() {
		try {
			setContent();
		} 
		catch (Exception e) {
			content = Util.stackTraceToString(e);
		}
		if(content == null) {
			content = "No configuration file found";
		}
	}

	private void setContent() {
		
	}
	
	public String getContent() {
		return content;
	}
}
