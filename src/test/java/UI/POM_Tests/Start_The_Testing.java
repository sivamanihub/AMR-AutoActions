package UI.POM_Tests;

import java.time.Duration;
import java.util.Scanner;

import org.testng.annotations.Test;

import UI.TestComponents.BaseTest;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import smk.UI_Automation.MainUIPage;
import smk.UI_Automation.MenuPage;
import smk.UI_Automation.Stop_Continue_Abort_Resume;

public class Start_The_Testing extends BaseTest {

	@Test
	public void StartAMRAutomation() throws InterruptedException {
//		Scanner s=new Scanner(System.in);
//		System.out.println("Enter your Apachi URL");
//		String url=s.nextLine();
//		System.out.println("Enter your AMR Name");
//		String AMRName=s.nextLine();
//		System.out.println("Enter your sever ip address");
//		String serveripadress=s.nextLine();
//		System.out.println("Enter your MapName");
//		String MapName=s.nextLine();
//		System.out.println("Enter your workflowName");
//		String workflowName=s.nextLine();
//		System.out.println("AMR stop time (mins):");
//		int S=s.nextInt();
//		System.out.println("AMR stop time (mins):");
//		int C=s.nextInt();
//		System.out.println("AMR stop time (mins):");
//		int A=s.nextInt();
//		System.out.println("AMR stop time (mins):");
//		int R=s.nextInt();

		int S = 5*1000, C = 2*1000, A = 5*1000, R = 2*1000;
		String url = "http://192.168.68.148/BOT_UI_5.15.1/smk.html";
//		String AMRName = "LWB-20";
		String AMRName = "APPU4.2";//smk
		String serveripadress = "192.168.68.148";
		//String MapName = "1.2.1";
		String workflowName = "testing";

		MainUIPage mup= lp.LoginPage(url, AMRName, serveripadress);
		mup.DynimicJBkBtn();
		//MenuPage mp = mup.gotoMenuPage();
		//mp.ChangingMap(MapName);
		mup.DynimicExitBtn.click();
		mup.SelectWorkFlow(workflowName);
		while (true) {
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
}