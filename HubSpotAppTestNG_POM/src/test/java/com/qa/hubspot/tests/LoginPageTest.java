package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.LoginPage;

public class LoginPageTest {
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	
	@BeforeTest
	public void setUp(){
		basePage = new BasePage();
		prop = basePage.initProperties();
		String browserName = prop.getProperty("browser");
		driver = basePage.initDriver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		
	}
	
	@Test(priority=1,description="get page title as HubSpot Login")
	public void verifyPageTitleTest() throws InterruptedException{
		Thread.sleep(5000);
		String loginPageTitle = loginPage.getPageTitle();
		System.out.println("Login page title is \"" + loginPageTitle + "\"");
		Assert.assertEquals(loginPageTitle, "HubSpot Login", "Login page title is incorrect!");
	}
	
	@Test(priority=2, description="sign up link is displayed or not")
	public void verifySignUpLink(){
		Assert.assertTrue(loginPage.checkSignUpLink());
	}
	
	@Test(priority=3, description="invalid username and password for the login page")
	public void loginTest(){
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}

}
