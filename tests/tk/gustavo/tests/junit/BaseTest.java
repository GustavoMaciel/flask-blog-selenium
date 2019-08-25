package tk.gustavo.tests.junit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import tk.gustavo.utils.URL;

import static tk.gustavo.config.Configuration.getChromeInstance;

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
