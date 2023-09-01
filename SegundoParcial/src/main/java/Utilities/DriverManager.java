package Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
    //Encargada de crear una instancia del WebManager y retornarla
    private static DriverManager instance;
    public WebDriver driver;

    private DriverManager(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }

    public static DriverManager getDriver(){
        //Aplicar un singletone para evitar que se cree mas de una instancia
        if(instance == null){
            instance = new DriverManager();
        }
        return instance;
    }
}