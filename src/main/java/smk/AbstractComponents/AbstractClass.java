package smk.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import smk.UI_Automation.*;

public class AbstractClass {
	public MenuPage mp;
	public ManulOperation mnp;
	public Stop_Continue_Abort_Resume scar;
	WebDriver driver;
	

	public AbstractClass(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//img[@alt='Settings']")
	WebElement MenuBtn;


	@FindBy(xpath = "//button[@class='MANUALButton']")
	WebElement ManulBtn;
	public void waitForElementToAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
	
	public void waitForWebElementToAppear(WebElement findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(findBy));

	}
	
	public void waitForListWebElementToAppear(List<WebElement> findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfAllElements(findBy));

	}
	
	public MenuPage gotoMenuPage()
	{
		MenuBtn.click();
		mp=new MenuPage(driver);
		return mp;
	}
	public Stop_Continue_Abort_Resume StopAbortOperation()
	{
		scar=new Stop_Continue_Abort_Resume(driver);
		return scar;
	}
	
	public ManulOperation ManulOperation()
	{
		//ManulBtn.click();
		return mnp=new ManulOperation(driver);
		
	}
	
	
}
