package UI.POM_Tests;

import java.util.concurrent.atomic.AtomicBoolean;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import UI.TestComponents.BaseTest;
import UI.TestComponents.Listeners;
import smk.UI_Automation.MainUIPage;

public class smk extends BaseTest {
	public MainUIPage mup;

	public smk(MainUIPage mup) {
		this.mup = mup;
	}

	@Test
	public void Stop(int S) throws InterruptedException {
		Thread.sleep(10000);
		// Stop Button
		try {
			if (S != 0) {
				Thread.sleep(S);
				mup.waitForWebElementToAppear(mup.stopButton);
				mup.ClickStopBtn();
			}

		} catch (Exception e) {
			mup.RefreshBtn.click();
			System.out.println("Stop button not found.");
		}

	}

	@Test
	public void Continue(int C) throws InterruptedException {
		// Continue Button
		try {
			if (C != 0) {
				Thread.sleep(C);
				mup.waitForWebElementToAppear(mup.ContinueButton);
				mup.ClickContinueBtn();
			}
		} catch (Exception e) {
			mup.RefreshBtn.click();
			System.out.println("CONTINUE button not found.");
		}

	}

	@Test
	public void Abort(int A) throws InterruptedException {

		// Abort Button
		try {
			if (A != 0) {
				Thread.sleep(A);
				mup.waitForWebElementToAppear(mup.AbortButton);
				mup.ClickAbortbtn();
			}

		} catch (Exception e) {
			mup.RefreshBtn.click();
			System.out.println("Abort button not found.");
		}

	}

	@Test
	public void Resume(int R) throws InterruptedException {
		// Resume Button
		try {
			if (R != 0) {
				Thread.sleep(R);
				mup.waitForWebElementToAppear(mup.ResumeButton);
				mup.ClickResume();
			}
		} catch (Exception e) {
			mup.RefreshBtn.click();
			System.out.println("Resume button not found.");
		}

	}

	@Test
	public void Relese(String workflowName) throws InterruptedException {

		// Check Relese Button found
		try {
			Thread.sleep(20000);
			if (mup.ReleaseButton.isDisplayed()) {
				mup.waitForWebElementToAppear(mup.ReleaseButton);
				mup.ClickRelese();
				mup.RefreshBtn.click();
				System.out.println("Release button Pressed and given workflow");
				Thread.sleep(5000);
				mup.SelectWorkFlow(workflowName);
			}
		} catch (Exception e) {
			if (!mup.stopButton.isDisplayed() && !mup.AbortButton.isDisplayed()) {
				mup.RefreshBtn.click();
				System.out.println("Release button not found");
			}
		}
	}
}