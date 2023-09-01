import Pages.HomePage;
import Pages.LoginPage;
import Utilities.DriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LogoutTests extends BaseTest{
    @Test
    public void verifyUserCanLogOut() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.clickOnBurgerButton();
        homePage.clickOnLogoutLink();

        Assertions.assertTrue(loginPage.isLoginButtonDisplayed());
        Thread.sleep(5000);
    }
}