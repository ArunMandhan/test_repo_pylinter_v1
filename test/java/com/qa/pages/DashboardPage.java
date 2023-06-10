package com.qa.pages;

import com.qa.base.TestBase;

import com.qa.utils.TestUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class DashboardPage extends TestBase {
    TestUtils utils;
    Actions actions = new Actions(driver);

    @FindBy(id = "nav-link-accountList")
    WebElement signInLink;

    @FindBy(xpath = "//a[contains(@href,'deals')]")
    WebElement todaysDealLink;

    @FindBy(id = "nav-cart")
    WebElement cart;

    @FindBy(xpath = "//a[@data-csa-c-slot-id='nav-link-accountList']")
    WebElement accountsAndLists;

    @FindBy(id = "nav_prefetch_yourorders")
    WebElement yourOrdersLink;

    @FindBy(id = "nav_prefetch_youraddresses")
    WebElement yourAddressesLink;

    @FindBy(xpath = "//span[contains(text(),'Your Lists')]")
    WebElement yourListsLink;

    @FindBy(xpath = "//span[@id='attachSiNoCoverage']//span//input")
    WebElement noThanksButton;

    public DashboardPage() throws IOException {
        PageFactory.initElements(driver, this);
        utils = new TestUtils();
    }

    public void navigateToSigninPage() {
        signInLink.click();
    }

    public void navigateToTodaysDeal() {
        todaysDealLink.click();
    }

    public void navigateToCart() {
        utils.isElementPresent(noThanksButton);
        noThanksButton.click();
        cart.click();
    }

    public void navigateToYourOrders() throws InterruptedException {
        utils.isElementVisible(cart);
        noThanksButton.click();
        actions.moveToElement(accountsAndLists).perform();
        utils.isElementVisible(yourAddressesLink);
        yourOrdersLink.click();
    }

    public void navigateToYourAddress() throws InterruptedException {
        utils.implicitWait();
        actions.moveToElement(accountsAndLists).perform();
        utils.implicitWait();
        yourAddressesLink.click();
    }

    public void navigateToYourLists() throws InterruptedException {
        utils.implicitWait();
        actions.moveToElement(accountsAndLists).perform();
        utils.implicitWait();
        yourListsLink.click();
    }

}
