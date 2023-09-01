import Pages.HomePage;
import Pages.LoginPage;
import Utilities.DriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTests extends BaseTest {

    @Test
    public void loginSuccessTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        Assertions.assertTrue(homePage.pageTitleIsDisplayed());
    }

    @Test
    public void loginFailed(){
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("wrong_user");
        loginPage.setPasswordTextBox("wrong_password");
        loginPage.clickOnLoginButton();

        Assertions.assertTrue(loginPage.isErrorTextDisplayed("Epic sadface: Username and password do not match any user in this service"));
    }

}