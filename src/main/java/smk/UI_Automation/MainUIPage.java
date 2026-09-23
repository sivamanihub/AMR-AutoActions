package smk.UI_Automation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import smk.AbstractComponents.AbstractClass;

public class MainUIPage extends AbstractClass {

	WebDriver driver;
	

	public MainUIPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@class='DJSBack']")
	public WebElement DynimicBackBtn;
	
	@FindBy(xpath="//button[@class='ExitDynamicJobSequence']")
	public WebElement DynimicExitBtn;
	
	@FindBy(xpath="//button[@class='small-node-button']")
	List<WebElement> workflows;

	@FindBy(xpath="//button[@class='AutoGoButton']")
	public WebElement Gobtn;
	@FindBy(xpath="//button[@class='AutoStopButton']")
	public WebElement stopButton;
	@FindBy(xpath="//button[@class='AutoContinueButton']")
	public WebElement ContinueButton;
	@FindBy(xpath="//button[@class='AutoAbortButton']")
	public WebElement AbortButton;
	@FindBy(xpath="//*[@id=\"LastAbortedPopUpModal\"]/div/div/div/div[2]/button[1]")
	public WebElement ResumeButton;
	@FindBy(xpath="//button[@id='HoldReleaseButton']")
	public WebElement ReleaseButton;
	
    @FindBy(xpath="//*[@id=\"LastAbortedPopUpModal\"]/div/div/div/div[2]/button[2]")
    public WebElement Nobtn;
	
	@FindBy(xpath="//button[@class='REFRESH']")
	public WebElement RefreshBtn;
	
	By bkbtn=By.xpath("//button[@class='DJSBack']");
	By stopbtn=By.xpath("//button[@class='AutoStopButton']");
	By contineubtn=By.xpath("//button[@class='AutoContinueButton']");
	By Abortbtn=By.xpath("//button[@class='AutoAbortButton']");
	By Resumebtn=By.xpath("//*[@id=\"LastAbortedPopUpModal\"]/div/div/div/div[2]/button[1]");
	By ReleseBtn=By.xpath("//button[@id='HoldReleaseButton']");
	By NoBtn=By.xpath("//*[@id=\\\"LastAbortedPopUpModal\\\"]/div/div/div/div[2]/button[2]");

	public void DynimicJBkBtn()
	{
		waitForElementToAppear(bkbtn);
		DynimicBackBtn.click();
	}
	public void DynimicJob()
	{
		DynimicExitBtn.click();
	}	
	public void SelectWorkFlow(String WorkFlowName)
	{
		//Stream<WebElement> wf=workflows.stream().filter(s->s.getText().equalsIgnoreCase(WorkFlowName));
		
		waitForListWebElementToAppear(workflows);
		WebElement workflow = workflows.stream()
		        .filter(s -> s.getText().equalsIgnoreCase(WorkFlowName))
		        .findFirst()
		        .orElseThrow(() -> new RuntimeException("Workflow not found: " + WorkFlowName));

		workflow.click();
		Gobtn.click();
	}
	
	public void ClickStopBtn()
	{
		waitForElementToAppear(stopbtn);
		stopButton.click();
	}
	public void ClickContinueBtn()
	{
		waitForElementToAppear(contineubtn);
		ContinueButton.click();
	}
	public void ClickAbortbtn()
	{
	   waitForElementToAppear(Abortbtn);
	   AbortButton.click();
	}
	public void ClickResume()
	{
		waitForElementToAppear(Resumebtn);
		ResumeButton.click();
	}
	public void ClickRelese()
	{
		waitForElementToAppear(ReleseBtn);
		ReleaseButton.click();
	}
	public void ClickNo()
	{
		waitForElementToAppear(NoBtn);
		Nobtn.click();
	}
	
	
	
}
