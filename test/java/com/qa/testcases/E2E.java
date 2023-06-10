package com.qa.testcases;

import com.qa.base.TestBase;
import com.qa.listeners.ExtentReportListener2;
import com.qa.pages.*;
import com.qa.utils.TestUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.*;

import java.io.IOException;

//@Listeners({ExtentReportListener2.class})
public class E2E extends TestBase {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    TodaysDeal todaysDeal;
    ProductsPage productsPage;
    CartPage cartPage;
    TestUtils utils;

    public E2E() throws IOException {
        super();
    }

    @BeforeClass
    public void setup() throws IOException, InterruptedException {
        initialization();
        try {
            dashboardPage = new DashboardPage();
            loginPage = new LoginPage();
            todaysDeal = new TodaysDeal();
            productsPage = new ProductsPage();
            cartPage = new CartPage();
            utils = new TestUtils();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Verify that user cannot log in with valid but not registered email", priority = 1)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that user cannot log in with valid but not registered email")
    @Story("Scenario: ")
    public void verifyUserLoginValidation() throws IOException, InterruptedException {
        dashboardPage.navigateToSigninPage();
        loginPage.login(properties.getProperty("email"));
        String actualText = loginPage.getLoginEmailValidationText();
        utils.assertElementByText(actualText, properties.getProperty("loginValidationText"), "Validation text did not match");
    }


    @Test(description = "Verify that Items are added to cart correctly", priority = 2)
    @Severity(SeverityLevel.NORMAL)
    @Story("Scenario: 2")
    public void verifyAddToCart() throws IOException, InterruptedException {

        driver.get(properties.getProperty("url"));
        dashboardPage.navigateToTodaysDeal();
        todaysDeal.navigateToSecondCategory();
        utils.implicitWait();
        todaysDeal.navigateToFirstItemInCategory();
        utils.implicitWait();
        todaysDeal.navigateToFirstProduct();

        String productName = productsPage.getProductName();
        String[] productPrice = productsPage.getPrice();
        String formattedProductPrice = productsPage.getNewPrice(productPrice[1]);
        String productQuantity = properties.getProperty("quantity");
        Integer productSubtotal = Integer.parseInt(formattedProductPrice) * Integer.parseInt(productQuantity);
        productsPage.addToCart(properties.getProperty("quantity"));
        dashboardPage.navigateToCart();
        utils.implicitWait();

        String cartProductName = cartPage.getProductNameFromCart();
        String cartProductQuantity = cartPage.getCartQuantity();
        String[] cartPrice = cartPage.getCartProductPrice();
        String[] cartSubtotal = cartPage.getSubtotal();
        String formattedCartSubtotal = cartPage.getNewSubtotal(cartSubtotal[1]);

        cartPage.assertProductName(cartProductName, productName);
        cartPage.assertProductPrice(cartPrice[1], productPrice[1]);
        cartPage.assertProductQuantity(cartProductQuantity, productQuantity);
        cartPage.assertProductSubtotal(productSubtotal.toString(), formattedCartSubtotal);
    }

    @Test(description = "Verify that you cannot see 'Your Orders and 'Your Addresses' pages if you are not logged in. But you can see “Your Lists” intro screen", priority = 3)
    @Severity(SeverityLevel.NORMAL)
    @Story("Scenario: 3")
    public void pagesVerification() throws InterruptedException {
        dashboardPage.navigateToYourOrders();
        utils.verifyPageTitle(properties.getProperty("signInPageTitle"), "Sign in page did not open");
        driver.navigate().back();
        dashboardPage.navigateToYourAddress();
        utils.verifyPageTitle(properties.getProperty("signInPageTitle"), "Address page did not open");
        driver.navigate().back();
        dashboardPage.navigateToYourLists();
        utils.verifyPageUrl(properties.getProperty("listPageUrl"), "List intro page did not open");
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
