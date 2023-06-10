package com.qa.pages;

import com.qa.base.TestBase;
import com.qa.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class CartPage extends TestBase {
    TestUtils utils;

    @FindBy(xpath = "//span[@class='a-dropdown-prompt']")
    WebElement cartQuantity;

    @FindBy(xpath = "//span[contains(@class,'sc-product-price')]")
    WebElement cartProductPrice;

    @FindBy(xpath = "//span[@id='sc-subtotal-amount-activecart']//span[contains(@class,'sc-price')]")
    WebElement cartSubtotal;

    public CartPage() throws IOException {
        PageFactory.initElements(driver, this);
        utils = new TestUtils();
    }

    public String getProductNameFromCart() {
        return driver.findElement(By.xpath("//span[@class='a-list-item']//span[contains(@class,'a-truncate-full a-offscreen')]")).getAttribute("innerHTML");
    }

    public String[] getSubtotal() {
        return cartSubtotal.getAttribute("innerHTML").split("AED&nbsp;");

    }

    public String[] getCartProductPrice() {
        return cartProductPrice.getAttribute("innerHTML").split("AED&nbsp;");

    }

    public String getNewSubtotal(String s) {
        String[] b = s.split(".00");
        String[] c = b[0].split(",");
        return c[0] + c[1];
    }

    public String getCartQuantity() {
        return cartQuantity.getAttribute("innerHTML");
    }

    public void assertProductName(String actualProductName, String expectedProductName) throws InterruptedException {
        utils.implicitWait();
        utils.assertElementByText(actualProductName, expectedProductName, "Product Name did not match");
    }

    public void assertProductPrice(String actualProductPrice, String expectedProductPrice) throws InterruptedException {
        utils.implicitWait();
        utils.assertElementByText(actualProductPrice, expectedProductPrice, "Product price did not match");
    }

    public void assertProductQuantity(String actualProductQuantity, String expectedProductQuantity) throws InterruptedException {
        utils.implicitWait();
        utils.assertElementByText(actualProductQuantity, expectedProductQuantity, "Product quantity did not match");
    }

    public void assertProductSubtotal(String actualProductSubtotal, String expectedProductSubtotal) throws InterruptedException {
        utils.implicitWait();
        utils.assertElementByText(actualProductSubtotal, expectedProductSubtotal, "Product subtotal did not match");
    }
}
