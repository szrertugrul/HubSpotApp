package com.qa.hubspot.base;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {  	// we use Options Functions to implement some extra functions such as headless mode which are not supported by chromeDriver
														// assertselenium.com/java/list-of-chrome-driver-command-line-arguments/
	
	public ChromeOptions co;
	public FirefoxOptions fo;
	public Properties prop;
	
	public OptionsManager(Properties prop){
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions(){
		co = new ChromeOptions();
		if (prop.getProperty("incognito").equals("yes")) co.addArguments("--incognito"); 
		if (prop.getProperty("headless").equals("yes")) co.addArguments("--headless"); 
		
		return co;
	}
	
	public FirefoxOptions getFireFoxOptions(){
		fo = new FirefoxOptions();
		if (prop.getProperty("headless").equals("yes")) fo.addArguments("--headless"); 
		
		return fo;
	}
	

}
