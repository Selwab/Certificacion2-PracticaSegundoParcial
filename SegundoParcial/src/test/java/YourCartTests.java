import Pages.HomePage;
import Pages.LoginPage;
import Pages.YourCartPage;
import Utilities.DriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class YourCartTests extends BaseTest{
    @Test
    public void verifyYourCartDetailsWhenProductIsAdded() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Fleece Jacket");
        homePage.addProductToCart("Sauce Labs Bike Light");

        homePage.clickOnShoppingCartButton();

        YourCartPage yourCartPage = new YourCartPage(DriverManager.getDriver().driver);
        Assertions.assertTrue(yourCartPage.isProductNameDisplayed("Sauce Labs Fleece Jacket"));
        Assertions.assertTrue(yourCartPage.isProductNameDisplayed("Sauce Labs Bike Light"));

    }

    @Test
    public void verifyProductoIsRemovefFromYoutCartPage() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Fleece Jacket");
        homePage.addProductToCart("Sauce Labs Bike Light");

        homePage.clickOnShoppingCartButton();

        YourCartPage yourCartPage = new YourCartPage(DriverManager.getDriver().driver);
        yourCartPage.removeProduct("Sauce Labs Fleece Jacket");

        Assertions.assertFalse(yourCartPage.isProductNameDisplayed("Sauce Labs Fleece Jacket"));
    }
}