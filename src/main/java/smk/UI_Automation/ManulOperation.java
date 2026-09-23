package smk.UI_Automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import smk.AbstractComponents.AbstractClass;

public class ManulOperation extends AbstractClass {

	
	WebDriver driver;

	public ManulOperation(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@class='ManualFrontL']")
	public WebElement ManulFront;
	@FindBy(xpath = "//button[@class='ManualBackL']")
	public WebElement ManulBack;
	@FindBy(xpath = "//button[@class='ManualClockL']")
	public WebElement ManulClock;
	@FindBy(xpath = "//button[@class='ManualAnticlockL']")
	public WebElement ManulAntiClock;
	
	@FindBy(xpath = "//input[contains(@class,'ManualFrontTextBox')]")
	public WebElement ManulFrontBox;
	@FindBy(xpath = "//input[contains(@class,'ManualBack()')]")
	public WebElement ManulBackBox;
	@FindBy(xpath = "//input[contains(@class,'ManualClockTextBox')]")
	public WebElement ManulClockBox;
	@FindBy(xpath = "//input[contains(@class,'ManualAnticlockTextBox')]")
	public WebElement ManulAntiClockBox;
	
	@FindBy(xpath ="//button[@onmousedown='ManualGoCommand()']")
	public WebElement ManulGo;
	
	@FindBy(xpath="//button[@class='AUTOButton']")
	public WebElement AutoWBtn;
	
	@FindBy(xpath = "//button[@class='MANUALButton']")
	public WebElement ManulBtn;
	
			
	
	
	
	
	
	
	
	
}
