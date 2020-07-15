package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.ElementUtil;


public class HomePage extends BasePage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	// Locators
	By header = By.id("//i18n-string[contains(text(),'Dashboard Library')]");
	By chooseAccount = By.xpath("//span[contains(text(),'7TP LLC')]");
	By accountName = By.xpath("//span[contains(@class,'account-name')]");
	
	
	// Constructor:
	public HomePage(WebDriver driver){
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	// Methods
	public String getHomePageTitle(){
		return elementUtil.doGetPageTitle();
	}
	
	public void chooseAccount(){
		elementUtil.doClick(chooseAccount);
	}
	
	public String getHomePageHeader(){
		return elementUtil.doGetText(header);
	}
	
	public String getLoggedInUserAccountName(){
		return elementUtil.doGetText(accountName);
	}
		
	}
	
	
