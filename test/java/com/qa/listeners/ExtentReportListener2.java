package com.qa.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.qa.base.ExtentManager;
import com.qa.base.ExtentTestManager;

import com.qa.base.TestBase;
import com.qa.utils.TestUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;

import java.io.IOException;

public class ExtentReportListener2 extends TestBase implements ITestListener{
    public TestUtils testUtils = new TestUtils();

    public ExtentReportListener2() throws IOException {
    }

    public void onStart(ITestContext context) {
        System.out.println("*** Test Suite " + context.getName() + " started ***");
    }

    public void onFinish(ITestContext context) {
        System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
    }

    public void onTestStart(ITestResult result) {
        System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
        ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }

    public void onTestFailure(ITestResult result) {
            String screenshotPath = testUtils.getScreenshot(result.getMethod().getMethodName());
            System.out.println(screenshotPath);


        System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
        ExtentTestManager.getTest().log(Status.FAIL, "Test failed because of "+ result.getThrowable());

////         attach screenshots to report
        try {
            ExtentTestManager.getTest().fail("Screenshot",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (IOException e) {
            ExtentTestManager.getTest().log(Status.FAIL, "Failed to get screenshot" + e.getCause() );
        }
    }



    public void onTestSkipped(ITestResult result) {
        System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
//        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
//        ExtentTestManager.getTest().info(result.getMethod().getMethodName() + " Skipped...");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
    }

}