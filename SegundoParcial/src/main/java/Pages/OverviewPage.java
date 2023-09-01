package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;

public class OverviewPage {
    WebDriver driver;

    @FindBy(id = "finish")
    WebElement finishButton;

    @FindBy(xpath = "//span[text()='Checkout: Overview']")
    WebElement pageTitle;

    @FindBy(className = "cart_item")
    List<WebElement> cartList;

    @FindBy(css = ".summary_info_label.summary_total_label")
    WebElement totalImport;

    @FindBy(className = "summary_tax_label")
    WebElement tax;

    public OverviewPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnFinishButton(){
        finishButton.click();
    }

    public boolean isPageTitleDisplayed(){
        boolean pageTitleDisplayed = pageTitle.isDisplayed();
        return pageTitleDisplayed;
    }

    public boolean isTotalDisplayedCorrect(){
        Double totalPrice = 0.0;
        Double totalImportDouble = Double.parseDouble(totalImport.getText().replaceAll("[^\\d.]", ""));
        Double taxDouble = Double.parseDouble(tax.getText().replaceAll("[^\\d.]", ""));

        for(WebElement element: cartList){
            String productPrice = element.findElement(By.className("inventory_item_price")).getText().replace("$","");
            Double actualProductPrice = Double.parseDouble(productPrice);
            totalPrice += actualProductPrice;
        }

        totalPrice += taxDouble;

        if (totalPrice.equals(totalImportDouble) ){
            return true;
        }

        return false;
    }


}
