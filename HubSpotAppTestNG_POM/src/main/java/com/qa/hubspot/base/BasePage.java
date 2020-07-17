package com.qa.hubspot.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.hubspot.util.AppConstants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	WebDriver driver;
	Properties prop;
	public static boolean highlightElement;
	public OptionsManager optionsManager;
	
	public WebDriver initDriver(String browserName){
		
		highlightElement = prop.getProperty("highlight").equals("yes") ? true : false;  // to get the key of highlight from config.properties file
		
		optionsManager = new OptionsManager(prop);
		
		System.out.println("Browser name is " + browserName);
		
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(optionsManager.getChromeOptions());
			
		}else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(optionsManager.getFireFoxOptions());
			
		}else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			driver = new SafariDriver();
			
		}else {
			System.out.println("Browser name \"" + browserName + "\" is not found!");
		}
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		
		return driver;
	}
	
	public Properties initProperties(){
		prop = new Properties();
		String path = null;
		String env = null;
		
		try {
			env = System.getProperty("env");
			if (env.equals("qa")) {
				path = "./src/main/java/com/qa/hubspot/config/config.qa.properties";                       //		.  = 	/Users/sezerertugrul/git/HubSpotApp/HubSpotAppTestNG_POM
			}else if (env.equals("stg")) {
				path = "./src/main/java/com/qa/hubspot/config/config.stg.properties";
			}
		} catch (Exception e) {
			path = "./src/main/java/com/qa/hubspot/config/config.properties";
		}
		
		try {
			FileInputStream ip = new FileInputStream(path);      //
			prop.load(ip);
		} catch (FileNotFoundException e) {
			System.out.println("Some kind of issue(s) happened with config properties... Correct the file");
		}catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}
