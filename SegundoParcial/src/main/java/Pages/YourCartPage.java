package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YourCartPage {
    WebDriver driver;

    @FindBy(className = "inventory_item_name")
    List<WebElement> productNames;

    @FindBy(className = "inventory_item_price")
    List<WebElement> productPrice;

    @FindBy(className = "cart_item")
    List<WebElement> cartList;

    @FindBy(className = "shopping_cart_badge")
    WebElement cartBadge;

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    public YourCartPage(WebDriver dirver){
        this.driver = dirver;
        PageFactory.initElements(driver, this);
    }

    public boolean isProductNameDisplayed(String product){
        for(WebElement element: productNames){
            if(element.getText().equalsIgnoreCase(product)){
                return true;
            }
        }
        return false;
    }

    public boolean isProductPriceDisplayed(String product){
        for(WebElement element: productPrice){
            if(element.getText().equalsIgnoreCase(product)){
                return true;
            }
        }
        return false;
    }

    public boolean isProductPriceCorrect(HashMap<String, String> inventory){
        for(WebElement element: cartList){

            String actualProductName = element.findElement(By.className("inventory_item_name")).getText();
            String actualProductPrice = element.findElement(By.className("inventory_item_price")).getText();

            String expectedProductPrice = inventory.get(actualProductName);

            if(actualProductPrice.equalsIgnoreCase(expectedProductPrice)){
                return true;
            }
        }
        return false;
    }

    public boolean isCartBadgeDisplayed() {
        int listSize = cartList.size();

        if (listSize == 0) {
            return true;
        } else if (listSize == Integer.parseInt(cartBadge.getText())) {
            return true;
        } else {
            return false;
        }
    }

    public void removeProduct(String product){
        //Sauce Labs Bike Light
        //remove-sauce-labs-bike-light
        String productLoweCase = product.toLowerCase();
        productLoweCase = productLoweCase.replace(" ","-");
        String removeProductId = "remove-" + productLoweCase;
        WebElement removeButton = driver.findElement(By.id(removeProductId));
        removeButton.click();
    }

    public void clickOnCheckoutButton(){
        checkoutButton.click();
    }

}