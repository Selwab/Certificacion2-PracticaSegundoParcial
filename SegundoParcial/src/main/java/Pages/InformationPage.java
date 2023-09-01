package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InformationPage {
    WebDriver driver;

    @FindBy(id = "first-name")
    WebElement firstNameTextBox;

    @FindBy(id = "last-name")
    WebElement lastNameTextBox;

    @FindBy(id = "postal-code")
    WebElement postalCodeTextBox;

    @FindBy(xpath = "//span[text()='Checkout: Your Information']")
    WebElement pageTitle;

    @FindBy(id = "continue")
    WebElement continueButton;

    @FindBy(css = ".error-message-container.error")
    WebElement informationErrorMessage;

    public InformationPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setFirstNameTextBox(String firstName){
        firstNameTextBox.sendKeys(firstName);
    }

    public void setLastNameTextBox(String lastName){
        lastNameTextBox.sendKeys(lastName);
    }

    public void setPostalCodeTextBox(String postalCode){
        postalCodeTextBox.sendKeys(postalCode);
    }

    public boolean isPageTitleDisplayed(){
        boolean pageTitleDisplayed = pageTitle.isDisplayed();
        return pageTitleDisplayed;
    }

    public void clickOnContinueButton(){
        continueButton.click();
    }

    public boolean isErrorTextDisplayed(String error){
        String actualErrorMessage = informationErrorMessage.getText();

        if(error.equalsIgnoreCase(actualErrorMessage)){
            return true;
        } else {
            return false;
        }
    }
}
