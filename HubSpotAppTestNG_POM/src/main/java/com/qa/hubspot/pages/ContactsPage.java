package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.ElementUtil;
import com.qa.hubspot.util.JavaScriptUtil;

public class ContactsPage extends BasePage{
	
	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil javaScriptUtil;
	
	
	By createContactButton = By.xpath("(//button[@type='button']//span[text()='Create contact'])[position()=1]");
	By createContactFormButton = By.xpath("(//button[@type='button']//span[text()='Create contact'])[position()=2]");
	By emailBox = By.xpath("//input[@data-field='email']");
	By firstNameBox = By.xpath("//input[@data-field='firstname']");
	By lastNameBox = By.xpath("//input[@data-field='lastname']");
	By jobTitleBox = By.xpath("//input[@data-field='jobtitle']");
	
	
	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		javaScriptUtil = new JavaScriptUtil(driver);
	}
	
	public String getContactsPageTitle(){
		elementUtil.waitForTitlePresent("Contacts");
		return elementUtil.doGetPageTitle();
	}
	
	public void createNewContact(String email, String firstName, String lastName, String jobTitle){
		elementUtil.doClick(createContactButton);
		elementUtil.doSendKey(emailBox, email);
		elementUtil.doSendKey(firstNameBox, firstName);
		elementUtil.doSendKey(lastNameBox, lastName);
		elementUtil.doSendKey(jobTitleBox, jobTitle);
		javaScriptUtil.clickElementByJS(elementUtil.getElement(createContactFormButton)); // 
		
	}

}
