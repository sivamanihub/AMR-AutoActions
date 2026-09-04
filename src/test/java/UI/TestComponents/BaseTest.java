package UI.TestComponents;

import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

import smk.UI_Automation.LandingPage;

public class BaseTest {

	WebDriver driver;
	LandingPage lp;
	public WebDriver initializeDriver()
	{
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
		
	}
	
	@BeforeMethod
	public LandingPage LandingPage(String url)
	{
		driver=initializeDriver();
		lp= new LandingPage(driver);
		lp.LoginPage(url);
		return lp;
	}
	
}
