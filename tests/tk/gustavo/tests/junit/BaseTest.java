package tk.gustavo.tests.junit;

import org.junit.After;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    protected WebDriver driver;

    @After
    public void tearDownTestSuit(){
        driver.quit();
    }

    protected void executeJavaScript(String script){
        ((JavascriptExecutor) driver).executeScript(
                script
        );
    }

}
