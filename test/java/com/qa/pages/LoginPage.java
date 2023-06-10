package com.qa.pages;

//import com.qa.listeners.ExtentReportListener2;

import com.qa.base.TestBase;
import com.qa.utils.TestUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class LoginPage extends TestBase {
//    public static final By notFoundTextCheck = By.className("a-list-item");

    TestUtils utils;

    @FindBy(name = "email")
    WebElement email;

    @FindBy(id = "ap_password")
    WebElement password;

    @FindBy(id = "continue")
    WebElement continueButton;

    @FindBy(id = "signInSubmit")
    WebElement signInButton;

    @FindBy(className = "a-list-item")
    WebElement notFoundTextCheck;

    public LoginPage() throws IOException {
        PageFactory.initElements(driver, this);
        utils = new TestUtils();
    }

    public void login(String uname) throws IOException, InterruptedException {
        utils.enterText(email, uname);
        utils.clickElement(continueButton);
//        utils.enterText(password,pass);
//        utils.clickElement(signInButton);
//        utils.implicitWait();
    }

    public String getLoginEmailValidationText() {
        return utils.getText(notFoundTextCheck);
    }


}
