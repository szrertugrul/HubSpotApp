package com.qa.hubspot.util;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;

import sun.security.action.GetBooleanAction;

/**
 * 
 * @author sezerertugrul
 *
 */

public class ElementUtil extends BasePage {
	
	WebDriver driver;
	WebDriverWait wait;
	JavaScriptUtil javaScriptUtil;
	Properties prop;
	
	public ElementUtil(WebDriver driver){
		this.driver = driver;
		 wait = new WebDriverWait(driver, AppConstants.DEFAULT_TIME);
		 javaScriptUtil = new JavaScriptUtil(driver);
	}
	
	/**
	 *  title wait title
	 * @param title
	 * @return
	 */
	public boolean waitForTitlePresent(String title){
		wait.until(ExpectedConditions.titleIs(title));
		return true;	
	}
	
	/**
	 * to wait element
	 * @param loctor
	 * @return
	 */
	public boolean waitForElementPresent(By locator){
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));  // explicitWait == for element
		return true;
	}
	
	/**
	 * Visibility element
	 * @param locator
	 * @return
	 */
	public boolean waitForelemetVisible(By locator){
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return true; //
	}
	
	/**
	 * to get title
	 * @return
	 */
	public String doGetPageTitle (){
		try {   // to define specific catch blocks to create specific explanations
			return driver.getTitle();
		} catch (Exception e) {
			System.out.println("<<<!!!   Some exception occured while getting the page title    !!!>>>");
		}
		return null;
	}
	
	/**
	 * to {@link GetBooleanAction} element
	 * @param locator
	 * @return
	 */
	public WebElement getElement(By locator){  // locater to connect the elements
		WebElement element = null;
		try {
//			if (waitForElementPresent(locator));  // so getElement method will run with wait concept for every element
			element = driver.findElement(locator);
			if (highlightElement) {
				javaScriptUtil.flash(element);
			}
		} catch (Exception e) {
			System.out.println("<<<!!!   Some exception occured while getting the element    !!!>>>");
		}
		return element;
	}
	
	/**
	 * to click
	 * @param locator
	 */
	public void doClick(By locator){
		try {
			getElement(locator).click();
		} catch (Exception e) {
			System.out.println("<<<!!!   Some exception occured while clicking the element    !!!>>>");
		}
	}
	
	/**
	 * to sen keys
	 * @param locator
	 * @param value
	 */
	public void doSendKey(By locator, String value){  // to send Keys as username, password
		try {
			WebElement element = getElement(locator);
			element.clear(); // to clear the keys after sending
			element.sendKeys(value);
		} catch (Exception e) {
			System.out.println("<<<!!!   Some exception occured while sending the value    !!!>>>");
		}
	}
	
	public boolean doIsDisplayed(By locator){
		try {
			return getElement(locator).isDisplayed();
		} catch (Exception e) {
			System.out.println("<<<!!!   Some exception occured for isDisplayed Method    !!!>>>");
		}
		return false;
	}
	
	public boolean doIsSelected(By locator){
		try {
			return getElement(locator).isSelected();
		} catch (Exception e) {
			System.out.println("<<<!!!   Some exception occured for isSelected Method     !!!>>>");
		}
		return false;
	}
	
	public boolean doIsEnabled(By locator){
		try {
			return getElement(locator).isEnabled();
		} catch (Exception e) {
			System.out.println("<<< Some exception occured for isEnabled Method    !!!>>>");
		}
		return false;
	}
	
	public String doGetText(By locator){
		try {
			return getElement(locator).getText();
		} catch (Exception e) {
			System.out.println("<<<!!!   Some exception occured while getting text from page    !!!>>>");
		}
		return null;
	}
	

}
