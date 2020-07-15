package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;

public class HomePageTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	Credentials  userCred;
	
	
	@BeforeTest	
	public void setUp() throws InterruptedException{
		basePage = new BasePage();
		prop = basePage.initProperties();
		String browserName = prop.getProperty("browser");
		driver = basePage.initDriver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver); // to reach the doLogin method in the loginPage class
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.doLogin(userCred);
		Thread.sleep(5000);
//		homePage.chooseAccount();		
	}
	
	@Test(priority=1, description="Verify Home Page Title")	
	public void verifyHomePageTitleTest(){
		String title = homePage.getHomePageTitle();
		System.out.println("Home page title is: " + title);
		Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE);	
	}
	
	@Test(priority=2, description="Verify Home Page Header")
	public void verifyHomePageHeaderTest(){
		String header = homePage.getHomePageHeader();
		System.out.println("Home page header is: " + header);
		Assert.assertEquals(header, AppConstants.HOME_PAGE_HEADER);
	}
	
	@Test(priority=3, description=" Verify Account Name")
	public void verifyLoggedInUserTest(){
		String accountName= homePage.getLoggedInUserAccountName();
		System.out.println("Account name is: " + accountName);
		Assert.assertEquals(accountName, "7TP LLC");
	}
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}
	

}
