import Utilities.DriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    @BeforeEach //Hace que se ejecute antes de cada prueba
    public void setup(){
        DriverManager.getDriver().driver.get("https://www.saucedemo.com/");
        DriverManager.getDriver().driver.manage().window().maximize();
    }

    @AfterAll //Hace que se ejecute despues de cada prueba
    public static void cleanUp(){
        DriverManager.getDriver().driver.close();
    }
}