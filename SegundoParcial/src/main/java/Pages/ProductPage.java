package Pages;

import Utilities.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;

public class ProductPage {
    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]")
    WebElement productNameTextBox;

    @FindBy(className = "inventory_details_price")
    WebElement productPriceTextBox;

    @FindBy(xpath = "//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[2]")
    WebElement productDescriptionTextBox;

    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory")
    WebElement addToCartButton;

    public ProductPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isProductInformationCorrect(HashMap<String, Product> inventory){
        String expectedProductName = inventory.get(productNameTextBox.getText()).name;
        Double expectedProductPrice = inventory.get(productNameTextBox.getText()).price;
        String expectedProductDescription = inventory.get(productNameTextBox.getText()).description;
        String expectedProductCartState = inventory.get(productNameTextBox.getText()).cartState;

        String productNameText = productNameTextBox.getText();
        Double productPriceDouble = Double.parseDouble(productPriceTextBox.getText().replace("$",""));
        String productDescriptionText = productDescriptionTextBox.getText();
        String productCartState = addToCartButton.getText();

        if(productNameText.equalsIgnoreCase(expectedProductName) && productPriceDouble.equals(expectedProductPrice)
                && productDescriptionText.equalsIgnoreCase(expectedProductDescription)
                && productCartState.equalsIgnoreCase(expectedProductCartState)){
            return true;
        }

        return false;
    }

    public String getProductId(String productName){
        String productNameLowerCase = productName.toLowerCase();
        productNameLowerCase = productNameLowerCase.replace(" ","-");
        String addToCartButtonId = "add-to-cart-";
        addToCartButtonId += productNameLowerCase;
        return addToCartButtonId;
    }
}
