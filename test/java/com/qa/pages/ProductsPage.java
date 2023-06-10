package com.qa.pages;

import com.qa.base.TestBase;
import com.qa.utils.TestUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class ProductsPage extends TestBase {
    TestUtils utils;

    @FindBy(id = "quantity")
    WebElement quantityDropDown;

    @FindBy(id = "add-to-cart-button")
    WebElement addToCartButton;

    @FindBy(id = "productTitle")
    WebElement productNameElement;

    @FindBy(xpath = "//*[@id='corePrice_feature_div']/div/span/span[1]")
    WebElement priceElement;

    public ProductsPage() throws IOException {
        PageFactory.initElements(driver, this);
        utils = new TestUtils();
    }

    public void selectQuantity(String quantity) {
        utils.selectByValue(quantityDropDown, quantity);

    }

    public void addToCart(String quantity) throws InterruptedException {
        selectQuantity(quantity);
        utils.implicitWait();
        addToCartButton.click();

    }

    public String getProductName() {
        return productNameElement.getText().trim();
    }

    public String[] getPrice() {
        return priceElement.getAttribute("innerHTML").split("AED");

    }

    public String getNewPrice(String pp) {
        String[] anotherPrice = pp.split(".00");
        return anotherPrice[0];

    }

}
