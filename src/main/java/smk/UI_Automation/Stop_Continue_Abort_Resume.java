package smk.UI_Automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import smk.AbstractComponents.AbstractClass;

public class Stop_Continue_Abort_Resume extends AbstractClass {
	public WebDriver driver;

	
	public Stop_Continue_Abort_Resume(WebDriver driver) {
		super(driver);
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}

	public MainUIPage mup = new MainUIPage(driver);

	public void Stop(int S) throws InterruptedException {

		
	}

	public void Continue(int C) throws InterruptedException {
		
	}

	public void Abort(int A) throws InterruptedException {
		
	}

	public void Resume(int R) throws InterruptedException {
		
	}

	public void Relese(String workflowName) throws InterruptedException {
		

	}
}
