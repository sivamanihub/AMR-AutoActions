package smk.UI_Automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import smk.AbstractComponents.AbstractClass;

public class LandingPage extends AbstractClass{
	
	 WebDriver driver;
	 
	 public LandingPage(WebDriver driver)
	 {
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	 }
	 
	 
	public void LoginPage(String url)
	{
	 	driver.get(url);
	}

}
