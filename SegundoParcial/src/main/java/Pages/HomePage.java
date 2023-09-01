package Pages;

import Utilities.DriverManager;
import Utilities.Product;
import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class HomePage {
    WebDriver driver;

    @FindBy(className = "app_logo")
    WebElement pageTitle;

    @FindBy(className = "product_sort_container")
    WebElement sortComboBox;

    @FindBy(className = "shopping_cart_link")
    WebElement shoppingCartButton;

    @FindBy(id = "react-burger-menu-btn")
    WebElement burgerButton;

    @FindBy(className = "inventory_item")
    List<WebElement> inventoryItems;

    @FindBy(xpath = "//span[text()='Products']")
    WebElement productTitle;

    @FindBy(css = "a[href='https://twitter.com/saucelabs']")
    WebElement twitterLinkButton;

    @FindBy(className = "inventory_item_name")
    List<WebElement> productNameButtons;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean pageTitleIsDisplayed(){
        boolean pageTitleIsDisplayed = pageTitle.isDisplayed();
        return pageTitleIsDisplayed;
    }

    public void selectSortComboBox(String option){
        Select selectObject = new Select(sortComboBox);
        selectObject.selectByVisibleText(option);
    }

    public boolean areProductsInDescendantOrderByName(){
        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
        List<String> actualProdcutNames = new ArrayList<>();

        for(WebElement element: products){
            actualProdcutNames.add(element.getText());
        }
        boolean isSorted = Ordering.natural().reverse().isOrdered(actualProdcutNames);
        if(isSorted){
            return true;
        }else{
            return false;
        }
    }

    //Método que permite agregar cualquier producto
    public void addProductToCart(String productName){
        //Nombre del producto: Sauce Labs Fleece Jacket
        //id boton: add-to-cart-sauce-labs-fleece-jacket
        String productNameLowerCase = productName.toLowerCase();
        productNameLowerCase = productNameLowerCase.replace(" ","-");
        String addToCartId = "add-to-cart-";
        addToCartId = addToCartId + productNameLowerCase;
        WebElement addToCartButton = driver.findElement(By.id(addToCartId));
        addToCartButton.click();
    }

    public void clickOnShoppingCartButton(){
        shoppingCartButton.click();
    }

    public void clickOnBurgerButton(){
        burgerButton.click();
    }

    public void clickOnLogoutLink(){
        //Es necesario un waiter
        WebElement logoutLink = new WebDriverWait(DriverManager.getDriver().driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));
        logoutLink.click();
    }

    public HashMap<String,String> getProductPriceInformation(){
        HashMap<String,String> inventory = new HashMap<>();

        for(WebElement element : inventoryItems){
            String productName = element.findElement(By.className("inventory_item_name")).getText();
            String productPrice = element.findElement(By.className("inventory_item_price")).getText();
            inventory.put(productName, productPrice);
        }
        return inventory;
    }

    public String getProductId(String productName){
        String productNameLowerCase = productName.toLowerCase();
        productNameLowerCase = productNameLowerCase.replace(" ","-");
        String addToCartButtonId = "add-to-cart-";
        addToCartButtonId += productNameLowerCase;
        return addToCartButtonId;
    }

    public HashMap<String, Product> getProductInformation(){
        HashMap<String,Product> inventory = new HashMap<>();

        for(WebElement element : inventoryItems){
            String productName = element.findElement(By.className("inventory_item_name")).getText();
            Double productPrice = Double.parseDouble(element.findElement(By.className("inventory_item_price")).getText().replace("$",""));
            String productDescription = element.findElement(By.className("inventory_item_desc")).getText();
            //Obtener Id del boton a partir del nombre
            String productCartStateId = getProductId(productName);
            String productCartState = element.findElement(By.id(productCartStateId)).getText();
            Product product = new Product(productName, productPrice, productDescription, productCartState);
            inventory.put(productName, product);
        }
        return inventory;
    }


    public boolean isProductTitleDisplayed(){
        boolean pageTitleDisplayed = productTitle.isDisplayed();
        return pageTitleDisplayed;
    }

    public void clickOnProductNameButton(String product){
        for(WebElement element: productNameButtons){
            String productName = element.getText();

            if(productName.equalsIgnoreCase(product)){
                element.click();
                break;
            }
        }
    }

    public void clickTwitterLinkButton(){
        twitterLinkButton.click();
    }

    public boolean twitterLinkRedirection() throws InterruptedException {

        String expectedURL = twitterLinkButton.getAttribute("href");

        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driverWait.until(ExpectedConditions.numberOfWindowsToBe(2));

        String initialWindow = driver.getWindowHandle();

        //Coleccion de ventanas
        Set<String> windowsSet = driver.getWindowHandles();
        windowsSet.remove(initialWindow);
        driver.switchTo().window(windowsSet.iterator().next());

        String actualURL = driver.getCurrentUrl();

        boolean isRedirectionCorrect = actualURL.equals(expectedURL);

        driver.close();
        driver.switchTo().window(initialWindow);

        return isRedirectionCorrect;
    }

}