package UI.POM_Tests;

import java.util.Scanner;

import org.testng.TestNG;
import org.testng.annotations.Test;

import smk.UI_Automation.MainUIPage;

public class sivamani {

	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
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
		String AMRName = "smk";
		String serveripadress = "192.168.68.148";
		String MapName = "1.2.1";
		//String workflowName = "0-3";
		TestNG testng = new TestNG();
		int count =0;
        while(count<10) {
        testng.setTestClasses(new Class[] {
            smk.class,
           
          
        });

        testng.run();
        count++;
        }
			
	}
	
}
