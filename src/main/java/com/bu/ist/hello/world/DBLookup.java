package com.bu.ist.hello.world;

public class DBLookup {

	private HelloConfig cfg;
	
	@SuppressWarnings("unused")
	private DBLookup() { /* Restrict private constructor */ }
	
	public DBLookup(HelloConfig cfg) {
		this.cfg = cfg;
	}

	public String getComment() {
		return "Type some sql and click submit";
	}
	
}
