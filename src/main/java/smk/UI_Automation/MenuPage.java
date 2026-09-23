package smk.UI_Automation;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.ResourceBundle.Control;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import smk.AbstractComponents.AbstractClass;

public class MenuPage extends AbstractClass {

	WebDriver driver;

	public MenuPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='configPasswordInput']")
	WebElement SettingPsw;

	@FindBy(xpath = "//div[contains(@class,'selectize-input')]")
	WebElement MapChangeBox;

	@FindBy(id = "mapNameInput-selectized")
	WebElement MapInput;

	@FindBy(xpath = "//button[@onclick='validatePassword()']")
	WebElement submitMenuPopUp;

	@FindBy(xpath = "//button[@class='saveConfig-btn']")
	WebElement saveConfigbtn;

	@FindBy(xpath = "//button[@class='closeConfig-btn']")
	WebElement closebtn;

	@FindBy(xpath = "//fieldset[.//legend[normalize-space()='Other Configurations']]//label[@for='disableManualMode']")
	WebElement manulBtn;
	
	@FindBy(xpath="//button[@class='popup-proceed-btn']")
	WebElement Proceed;

	public void ChangingMap(String MapName) {
		SettingPsw.sendKeys("hachidori123");
		submitMenuPopUp.click();
		Actions actions = new Actions(driver);

		actions.scrollByAmount(0, 100).perform();

//		MapChangeBox.click();
//
//	    MapInput.sendKeys(MapName);
//	    MapInput.sendKeys(Keys.ENTER);
		manulBtn.click();
		saveConfigbtn.click();
		closebtn.click();

	}

	public void EnableManulBtn() {
		SettingPsw.sendKeys("hachidori123");
		submitMenuPopUp.click();
		Actions actions = new Actions(driver);

		actions.scrollByAmount(0, 200).perform();
		
//		waitForWebElementToAppear(manulBtn);
//		manulBtn.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
        if(!manulBtn.isSelected())
        {
        	System.out.println("alredy selected");
        }else {
	    js.executeScript("arguments[0].click();", manulBtn);
	    System.out.println("Checkbox selected: " + manulBtn.isSelected());
        }
		saveConfigbtn.click();
		Proceed.click();
		closebtn.click();
	}

}
