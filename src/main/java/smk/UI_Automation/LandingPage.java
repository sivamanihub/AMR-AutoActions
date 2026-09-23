package smk.UI_Automation;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import smk.AbstractComponents.AbstractClass;

public class LandingPage extends AbstractClass{
	
	 WebDriver driver;	 
	 public MainUIPage mup;
	 public LandingPage(WebDriver driver)
	 {
		 super(driver);
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	 }
	 
	 @FindBy(xpath="//input[@id='botIdInput']")
	 WebElement BotId;
	 
	 @FindBy(xpath ="//button[@onclick='submitBotId()']")
     WebElement submitBtnBotId; 	
	
	 @FindBy(xpath ="//input[@id='localServerInput']")
     WebElement ServerIp; 
	 @FindBy(xpath ="//button[@onclick='submitLocalServer()']")
     WebElement submitBtnSerIp;
	 
	 @FindBy(xpath="//button[@id='proceedBtn']")
	 WebElement proceedConfig;
	 
	public MainUIPage LoginPage(String url, String AMRName, String serveripadress)
	{

		
	 	driver.get(url);
	 	BotId.sendKeys(AMRName);
	 	submitBtnBotId.click();
	 	ServerIp.sendKeys(serveripadress);
	 	submitBtnSerIp.click();
	    try {
	    	if(proceedConfig.isDisplayed())
	    	{
	    		proceedConfig.click();
	    	}
	    }catch(Exception e)
	    {
	    	System.out.println("bot not configured");
	    }
	    
	 	mup=new MainUIPage(driver);
	 	return mup;
	 	
	}
	

}
