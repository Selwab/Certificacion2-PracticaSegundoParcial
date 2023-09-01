package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    //Todos los controles y las interacciones del login page
    WebDriver driver;

    @FindBy(id="user-name")
    WebElement userNameTextBox;

    @FindBy(name="password")
    WebElement passwordTextBox;

    @FindBy(css="input[data-test='login-button']")
    WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    WebElement loginErrorMessage;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        //Inicializa y busca todos los elementos declarados como atributos
        PageFactory.initElements(driver, this); //Factory
    }

    public void setUserNameTextBox(String userName){
        userNameTextBox.sendKeys(userName);
    }

    public void setPasswordTextBox(String password){
        passwordTextBox.sendKeys(password);
    }

    public void clickOnLoginButton(){
        loginButton.click();
    }


    public boolean isErrorTextDisplayed(String error){
        String actualErrorMessage = loginErrorMessage.getText();
        //ignoreCase ingnora maysuculas y minusculas
        if(error.equalsIgnoreCase(actualErrorMessage)){
            return true;
        } else {
            return false;
        }
    }

    public boolean isLoginButtonDisplayed(){
        boolean loginButtonIsDisplayed = loginButton.isDisplayed();
        return loginButtonIsDisplayed;
    }

}