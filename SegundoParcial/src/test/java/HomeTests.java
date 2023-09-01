import Pages.HomePage;
import Pages.LoginPage;
import Utilities.DriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class HomeTests extends BaseTest{

    @Test
    public void orderingFromZToA(){
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.selectSortComboBox("Name (Z to A)");
        Assertions.assertTrue(homePage.areProductsInDescendantOrderByName());
    }
}