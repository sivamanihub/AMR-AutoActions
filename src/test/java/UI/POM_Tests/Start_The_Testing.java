package UI.POM_Tests;

import java.util.Scanner;

import org.testng.annotations.Test;

import UI.TestComponents.BaseTest;

public class Start_The_Testing extends BaseTest{

	
	

	@Test
	public void StartAMRAutomation()
	{	
		Scanner s=new Scanner(System.in);
		System.out.println("Enter your Apeachi URL");
		String url=s.nextLine();
		//String url="http://192.168.68.148/BOT_UI_5.15.1/smk.html";
	    lp.LoginPage(url);	
	}
}
