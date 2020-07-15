package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;

public class LoginPageTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	Credentials userCred;
	
	@BeforeTest
	public void setUp(){
		basePage = new BasePage();	
		prop = basePage.initProperties();
		String browserName = prop.getProperty("browser");
		driver = basePage.initDriver(browserName);
		driver.get(prop.getProperty("url"));	
		loginPage = new LoginPage(driver);	
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test(priority=1,description="get page title as HubSpot Login", enabled=true)
	public void verifyPageTitleTest(){
		String loginPageTitle = loginPage.getPageTitle();
		System.out.println("Login page title is " + loginPageTitle);
		Assert.assertEquals(loginPageTitle, AppConstants.LOGIN_PAGE_TITLE, "Login page title is incorrect!");
	}
	
	@Test(priority=2, description="sign up link is displayed or not", enabled=true)
	public void verifySignUpLink(){
		Assert.assertTrue(loginPage.checkSignUpLink());
	}
	
	@Test(priority=3, description="valid username and password for the login page", enabled=true)
	public void loginTest(){
		HomePage homePage = loginPage.doLogin(userCred); // we're using doLogin in HomePage Class
		String accountName = homePage.getLoggedInUserAccountName();
		System.out.println("logged in account name: " + accountName);
		Assert.assertEquals(accountName, prop.getProperty("accountName"));
	}
	
	@DataProvider 		//
									// https://www.toolsqa.com/testng/testng-dataproviders/
	public Object[][] getLoginInvalidData(){
		
		Object data [][] = {{"sezer@icloud.com", "test123456"}, 
											{"jimy@gmail.com", " "}, 
											{" ", "test12345"}, 
											{"yummy", "yummy"}, 
											{" ", " "}};
		return data;
	}
	
	@Test(priority=4, dataProvider = "getLoginInvalidData", enabled = false)
	public void login_invalidTestCase(String username, String pwd){	
		
		userCred.setAppUserName(username);
		userCred.setAppPassword(pwd);
		loginPage.doLogin(userCred);
		
		
		Assert.assertTrue(loginPage.checkLoginErrorMessage());
		
	}
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}

}
