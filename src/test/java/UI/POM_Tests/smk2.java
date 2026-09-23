package UI.POM_Tests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicBoolean;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import UI.TestComponents.BaseTest;
import UI.TestComponents.Listeners;
import smk.UI_Automation.MainUIPage;

public class smk2 extends BaseTest {

    private static final int STOP_DELAY = 5000;
    private static final int CONTINUE_DELAY = 2000;
    private static final int ABORT_DELAY = 6000;
    private static final int RESUME_DELAY = 2000;
    private static final String WORKFLOW = "1.2.1";

    private final AtomicBoolean stopRequested = new AtomicBoolean(false);

    private void startConsoleListener() {
        Thread listener = new Thread(() -> {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    if (line.trim().equalsIgnoreCase("stop")) {
                        System.out.println(">>> Stop command received. Finishing current cycle and exiting...");
                        stopRequested.set(true);
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Console listener error: " + e.getMessage());
            }
        });
        listener.setDaemon(true); // won't prevent JVM shutdown on its own
        listener.start();
    }

    @Test(timeOut = 7200000) // optional safety net
    public void StartAMRAutomation() throws InterruptedException {
        startConsoleListener();
        System.out.println(">>> Type 'stop' and press Enter at any time to end the run gracefully.");

        String url = "http://192.168.68.148/BOT_UI_5.15.1/smk.html";
        String AMRName = "smk";
        String serveripadress = "192.168.68.148";

        MainUIPage mup = lp.LoginPage(url, AMRName, serveripadress);
        mup.DynimicJBkBtn();
        mup.DynimicExitBtn.click();
        mup.SelectWorkFlow(WORKFLOW);

        int cycle = 0;
        while (!stopRequested.get()) {
            cycle++;
            ExtentTest cycleNode = Listeners.getTest().createNode("Cycle #" + cycle);

            Thread.sleep(10000);
            if (stopRequested.get()) break;

            runAction(cycleNode, "Stop", STOP_DELAY, mup, () -> {
                mup.waitForWebElementToAppear(mup.stopButton);
                mup.ClickStopBtn();
            });
            if (stopRequested.get()) break;

            runAction(cycleNode, "Continue", CONTINUE_DELAY, mup, () -> {
                mup.waitForWebElementToAppear(mup.ContinueButton);
                mup.ClickContinueBtn();
            });
            if (stopRequested.get()) break;

            runAction(cycleNode, "Abort", ABORT_DELAY, mup, () -> {
                mup.waitForWebElementToAppear(mup.AbortButton);
                mup.ClickAbortbtn();
            });
            if (stopRequested.get()) break;

            runAction(cycleNode, "Resume", RESUME_DELAY, mup, () -> {
                mup.waitForWebElementToAppear(mup.ResumeButton);
                mup.ClickResume();
            });
            if (stopRequested.get()) break;

            runReleaseCheck(cycleNode, mup);

            Listeners.flushReport();
        }

        System.out.println("Total cycles completed: " + cycle);
        Listeners.flushReport();
    }

    @FunctionalInterface
    private interface Step {
        void run() throws Exception;
    }

    private void runAction(ExtentTest parent, String name, int delay, MainUIPage mup, Step step) {
        ExtentTest node = parent.createNode(name);
        try {
            if (delay != 0) Thread.sleep(delay);
            step.run();
            node.log(Status.PASS, name + " completed");
        } catch (Exception e) {
            node.log(Status.FAIL, name + " failed: " + e.getMessage());
            try {
                mup.RefreshBtn.click();
            } catch (Exception refreshEx) {
                node.log(Status.WARNING, "Refresh also failed: " + refreshEx.getMessage());
            }
        }
    }

    private void runReleaseCheck(ExtentTest parent, MainUIPage mup) {
        ExtentTest node = parent.createNode("Release");
        try {
            Thread.sleep(20000);
            if (mup.ReleaseButton.isDisplayed()) {
                mup.waitForWebElementToAppear(mup.ReleaseButton);
                mup.ClickRelese();
                mup.RefreshBtn.click();
                node.log(Status.PASS, "Release pressed, workflow re-selected");
                Thread.sleep(5000);
                mup.SelectWorkFlow(WORKFLOW);
            } else {
                node.log(Status.INFO, "Release button not shown this cycle");
            }
        } catch (Exception e) {
            try {
                if (!mup.stopButton.isDisplayed() && !mup.AbortButton.isDisplayed()) {
                    mup.RefreshBtn.click();
                    node.log(Status.FAIL, "Release button not found: " + e.getMessage());
                } else {
                    node.log(Status.INFO, "Release not applicable this cycle");
                }
            } catch (Exception inner) {
                node.log(Status.WARNING, "Could not verify visibility: " + inner.getMessage());
            }
        }
    }
}