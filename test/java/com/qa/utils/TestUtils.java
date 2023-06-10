package com.qa.utils;

import com.qa.base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestUtils extends TestBase {

    public TestUtils() throws IOException {
    }

    public String getScreenshot(String methodName) {
        File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshots/" + methodName + ".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(src, destination);
        } catch (IOException e) {
            System.out.println("Capture Failed " + e.getMessage());
        }
        return path;
    }

    public void verifyPageTitle(String pageTitle, String text) {
        Assert.assertEquals(driver.getTitle(), pageTitle, text + " ");
    }

    public void verifyPageUrl(String pageUrl, String text) {
        Assert.assertEquals(driver.getCurrentUrl(), pageUrl, text + " ");
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public void enterText(WebElement element, String text) {
        element.sendKeys(text);
    }

    public void implicitWait() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
    }

    public void isElementVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element.toString())));
    }

    public void isElementPresent(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element.toString())));
    }

    public void assertElementByText(String actual, String expected, String text) {
        Assert.assertEquals(actual, expected, "Validation text did not match: " + text);
    }

    public String getText(WebElement element) {
        return element.getText().trim();
    }

    public void selectByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

}
