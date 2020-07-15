package com.qa.hubspot.util;

public class Credentials { // we'll use the class for positive (happy) test cases
	
	String appUserName;
	String appPassword;
	
	public Credentials(String appUserName, String appPassword){
		this.appPassword = appPassword;
		this.appUserName = appUserName;
	}

	/**
	 * 
	 * @return
	 */
	public String getAppUserName() {
		return appUserName;
	}

	/**
	 * 
	 * @param appUserName
	 */
	public void setAppUserName(String appUserName) {
		this.appUserName = appUserName;
	}

	/**
	 * 
	 * @return
	 */
	public String getAppPassword() {
		return appPassword;
	}

	/**
	 * 
	 * @param appPassword
	 */
	public void setAppPassword(String appPassword) {
		this.appPassword = appPassword;
	}
	

}
