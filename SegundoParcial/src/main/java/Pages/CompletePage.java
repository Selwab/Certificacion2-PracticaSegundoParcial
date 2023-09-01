package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CompletePage {

    WebDriver driver;

    @FindBy(id = "back-to-products")
    WebElement backHomeButton;

    @FindBy(className = "title")
    WebElement pageTitle;

    public CompletePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnBackHomeButton(){
        backHomeButton.click();
    }

    public boolean isPageTitleDisplayed(){
        boolean pageTitleDisplayed = pageTitle.isDisplayed();
        return pageTitleDisplayed;
    }
}
