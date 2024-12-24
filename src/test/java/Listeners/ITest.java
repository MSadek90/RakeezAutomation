package Listeners;

import Utilities.Utility;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static Pages.BasePage.driver;

public class ITest implements ITestListener {


    public void onTestSuccess(ITestResult result) {
         System.out.println(result.getMethod().getMethodName() + " >>>>> passed");
         Utility.takeScreenShot(driver,"file");
    }

    public void onTestFailure(ITestResult result) {
         System.out.println(result.getMethod().getMethodName() + " >>>>> failed");
        Utility.takeScreenShot(driver,"file");
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + " >>>>> skipped");
    }


}
