import Pages.*;
import Utilities.DriverManager;
import Utilities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.TreeMap;

public class PracticeTests extends BaseTest{

    // SL-10:Verificar la compra exitosa de un producto cuando la información es válida
    @Test
    public void purchaseSuccessTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        Assertions.assertTrue(homePage.pageTitleIsDisplayed());

        homePage.addProductToCart("Sauce Labs Backpack");
        homePage.addProductToCart("Sauce Labs Bike Light");
        homePage.addProductToCart("Sauce Labs Bolt T-Shirt");
        homePage.addProductToCart("Sauce Labs Onesie");

        homePage.clickOnShoppingCartButton();

        YourCartPage yourCartPage = new YourCartPage(DriverManager.getDriver().driver);
        Assertions.assertTrue(yourCartPage.isProductNameDisplayed("Sauce Labs Backpack"));
        Assertions.assertTrue(yourCartPage.isProductNameDisplayed("Sauce Labs Bike Light"));
        Assertions.assertTrue(yourCartPage.isProductNameDisplayed("Sauce Labs Bolt T-Shirt"));
        Assertions.assertTrue(yourCartPage.isProductNameDisplayed("Sauce Labs Onesie"));

        yourCartPage.clickOnCheckoutButton();

        InformationPage informationPage = new InformationPage(DriverManager.getDriver().driver);
        Assertions.assertTrue(informationPage.isPageTitleDisplayed());

        informationPage.setFirstNameTextBox("Juan");
        informationPage.setLastNameTextBox("Perez");
        informationPage.setPostalCodeTextBox("1234");

        informationPage.clickOnContinueButton();

        OverviewPage overviewPage = new OverviewPage(DriverManager.getDriver().driver);
        Assertions.assertTrue(overviewPage.isPageTitleDisplayed());

        overviewPage.clickOnFinishButton();

        CompletePage completePage = new CompletePage(DriverManager.getDriver().driver);
        Assertions.assertTrue(completePage.isPageTitleDisplayed());
        Thread.sleep(5000);
        completePage.clickOnBackHomeButton();

        Assertions.assertTrue(homePage.isProductTitleDisplayed());

        Thread.sleep(2000);
    }
    //SL-12:Verificar el cálculo correcto del total de compra

    @Test
    public void verifyTotalImport() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        Assertions.assertTrue(homePage.pageTitleIsDisplayed());

        HashMap<String,String> dictionary = homePage.getProductPriceInformation();

        homePage.addProductToCart("Sauce Labs Backpack");
        homePage.addProductToCart("Sauce Labs Bike Light");
        homePage.addProductToCart("Sauce Labs Bolt T-Shirt");
        homePage.addProductToCart("Sauce Labs Onesie");

        homePage.clickOnShoppingCartButton();

        YourCartPage yourCartPage = new YourCartPage(DriverManager.getDriver().driver);
        Assertions.assertTrue(yourCartPage.isProductNameDisplayed("Sauce Labs Backpack"));
        Assertions.assertTrue(yourCartPage.isProductNameDisplayed("Sauce Labs Bike Light"));
        Assertions.assertTrue(yourCartPage.isProductNameDisplayed("Sauce Labs Bolt T-Shirt"));
        Assertions.assertTrue(yourCartPage.isProductNameDisplayed("Sauce Labs Onesie"));

        yourCartPage.clickOnCheckoutButton();

        InformationPage informationPage = new InformationPage(DriverManager.getDriver().driver);
        Assertions.assertTrue(informationPage.isPageTitleDisplayed());

        informationPage.setFirstNameTextBox("Juan");
        informationPage.setLastNameTextBox("Perez");
        informationPage.setPostalCodeTextBox("1234");

        informationPage.clickOnContinueButton();

        OverviewPage overviewPage = new OverviewPage(DriverManager.getDriver().driver);
        Assertions.assertTrue(overviewPage.isPageTitleDisplayed());

        Assertions.assertTrue(overviewPage.isTotalDisplayedCorrect());
    }

    //SL-17:Verificar el funcionamiento de el enlace a Twitter
    @Test
    public void verifyTwitterLink() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        Assertions.assertTrue(homePage.pageTitleIsDisplayed());

        homePage.clickTwitterLinkButton();

        Assertions.assertTrue(homePage.twitterLinkRedirection());
    }

    @Test
    public void verifyProductInformationDisplayed() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);

        HashMap<String, Product> inventory = homePage.getProductInformation();

        homePage.clickOnProductNameButton("Sauce Labs Backpack");

        ProductPage productPage = new ProductPage(DriverManager.getDriver().driver);

        Assertions.assertTrue(productPage.isProductInformationCorrect(inventory));
    }

    @Test
    public void purchaseFailTestEmptyFirstName() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        Assertions.assertTrue(homePage.pageTitleIsDisplayed());

        homePage.addProductToCart("Sauce Labs Backpack");
        homePage.addProductToCart("Sauce Labs Bike Light");
        homePage.addProductToCart("Sauce Labs Bolt T-Shirt");
        homePage.addProductToCart("Sauce Labs Onesie");

        homePage.clickOnShoppingCartButton();

        YourCartPage yourCartPage = new YourCartPage(DriverManager.getDriver().driver);
        Assertions.assertTrue(yourCartPage.isProductNameDisplayed("Sauce Labs Backpack"));
        Assertions.assertTrue(yourCartPage.isProductNameDisplayed("Sauce Labs Bike Light"));
        Assertions.assertTrue(yourCartPage.isProductNameDisplayed("Sauce Labs Bolt T-Shirt"));
        Assertions.assertTrue(yourCartPage.isProductNameDisplayed("Sauce Labs Onesie"));

        yourCartPage.clickOnCheckoutButton();

        InformationPage informationPage = new InformationPage(DriverManager.getDriver().driver);
        Assertions.assertTrue(informationPage.isPageTitleDisplayed());

        informationPage.setLastNameTextBox("Perez");
        informationPage.setPostalCodeTextBox("1234");

        informationPage.clickOnContinueButton();

        Assertions.assertTrue(informationPage.isErrorTextDisplayed("Error: First Name is required"));
    }
}
