import Pages.HomePage;
import Pages.LoginPage;
import Pages.YourCartPage;
import Utilities.DriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class ExamTests extends BaseTest{

    @Test
    public void examTest() throws InterruptedException {
        //1
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        Assertions.assertTrue(homePage.pageTitleIsDisplayed());

        //3
        homePage.addProductToCart("Sauce Labs Backpack");
        homePage.addProductToCart("Sauce Labs Bike Light");
        homePage.addProductToCart("Sauce Labs Bolt T-Shirt");
        homePage.addProductToCart("Sauce Labs Onesie");

        HashMap<String,String> dictionary = homePage.getProductPriceInformation();

        //4
        homePage.clickOnShoppingCartButton();

        //5
        YourCartPage yourCartPage = new YourCartPage(DriverManager.getDriver().driver);
        Assertions.assertTrue(yourCartPage.isProductNameDisplayed("Sauce Labs Backpack"));
        Assertions.assertTrue(yourCartPage.isProductNameDisplayed("Sauce Labs Bike Light"));
        Assertions.assertTrue(yourCartPage.isProductNameDisplayed("Sauce Labs Bolt T-Shirt"));
        Assertions.assertTrue(yourCartPage.isProductNameDisplayed("Sauce Labs Onesie"));

        //6
        /*
        Assertions.assertTrue(yourCartPage.isProductPriceDisplayed("$29.99"));
        Assertions.assertTrue(yourCartPage.isProductPriceDisplayed("$9.99"));
        Assertions.assertTrue(yourCartPage.isProductPriceDisplayed("$15.99"));
        Assertions.assertTrue(yourCartPage.isProductPriceDisplayed("$7.99"));
        */

        Assertions.assertTrue(yourCartPage.isProductPriceCorrect(dictionary));


        //7
        Assertions.assertTrue(yourCartPage.isCartBadgeDisplayed());

        //8
        yourCartPage.removeProduct("Sauce Labs Backpack");
        yourCartPage.removeProduct("Sauce Labs Bike Light");
        yourCartPage.removeProduct("Sauce Labs Bolt T-Shirt");
        yourCartPage.removeProduct("Sauce Labs Onesie");

        //9
        Assertions.assertFalse(yourCartPage.isProductNameDisplayed("Sauce Labs Backpack"));
        Assertions.assertFalse(yourCartPage.isProductNameDisplayed("Sauce Labs Bike Light"));
        Assertions.assertFalse(yourCartPage.isProductNameDisplayed("Sauce Labs Bolt T-Shirt"));
        Assertions.assertFalse(yourCartPage.isProductNameDisplayed("Sauce Labs Onesie"));

        //10
        Assertions.assertTrue(yourCartPage.isCartBadgeDisplayed());
    }
}
