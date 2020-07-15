package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ElementUtil;

public class LoginPage extends BasePage{
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	// Locators
	By emailId = By.id("username");
	By password = By.id("password");
	By loginButton = By.id("loginBtn");
	By signUp = By.linkText("Sign up");
	By loginErrorText = By.cssSelector("div.private-alert__inner");
	
	// Constructor
	public LoginPage(WebDriver driver){
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	// Actions == Methods
	public String getPageTitle(){
		return elementUtil.doGetPageTitle();
	}
	
	public boolean checkSignUpLink(){
		return elementUtil.doIsDisplayed(signUp);
	}
	
	public HomePage doLogin(Credentials userCred){
		elementUtil.doSendKey(emailId, userCred.getAppUserName());
		elementUtil.doSendKey(password, userCred.getAppPassword());
		elementUtil.doClick(loginButton);
		
		return new HomePage(driver);
	}
	
	public boolean checkLoginErrorMessage(){
		return elementUtil.doIsDisplayed(loginErrorText);
	}

}
