package tk.gustavo.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.concurrent.TimeUnit;

public class Configuration {

    public static WebDriver getChromeInstance(){
        String chromeProperty = "webdriver.chrome.driver";
        if(System.getProperty(chromeProperty) == null) {
            String chromeDriverPath = "C:\\Users\\Gustavo Maciel\\Documents\\Diversos\\Projects\\flask-selenium-test\\driver\\chromedriver.exe";
            System.setProperty(chromeProperty, chromeDriverPath);
        }

        WebDriver driver = new ChromeDriver(OptionsManager.getChromeOptions());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public static String getEmail() {
        return "gm.nunes92@gmail.com";
    }

    public static String getPassword() {
        return "1234";
    }

}
