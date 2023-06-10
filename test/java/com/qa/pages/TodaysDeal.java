package com.qa.pages;

import com.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class TodaysDeal extends TestBase {

    @FindBy(xpath = "//h4[contains(text(),'Categories')]//following::input[2]")
    WebElement secondCategory;

    @FindBy(xpath = "//*[@id='grid-main-container']/div[3]/div/div[1]/div/div")
    WebElement firstCategory;

    @FindBy(xpath = "//*[contains(@id,'octopus')]/ul/li[1]/span/div/div[2]/div[1]/a")
    WebElement firstProduct;

    public TodaysDeal() throws IOException {
        PageFactory.initElements(driver, this);
    }

    public void navigateToSecondCategory() {
        secondCategory.click();
    }

    public void navigateToFirstItemInCategory() {
        firstCategory.click();
    }

    public void navigateToFirstProduct() {
        firstProduct.click();
    }
}
