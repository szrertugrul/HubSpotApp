package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;

public class LoginPage extends BasePage{
	
	WebDriver driver;
	
	// Locators
	By emailId = By.id("username");
	By password = By.id("password");
	By loginButton = By.id("loginBtn");
	By signUp = By.linkText("Sign up");
	
	// Constructor
	public LoginPage(WebDriver driver){
		this.driver = driver;
	}
	
	// Actions == Methods
	public String getPageTitle(){
		return driver.getTitle();
	}
	public boolean checkSignUpLink(){
		return driver.findElement(signUp).isDisplayed();
	}
	public void doLogin(String username, String pwd){
		driver.findElement(emailId).sendKeys(username);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginButton).click();
	}

}
