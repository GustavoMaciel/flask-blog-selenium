package tk.gustavo.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.concurrent.TimeUnit;

public class Configuration {
    public static String baseUrl = "http://127.0.0.1:5000/";
    public static String email = "gm.nunes92@gmail.com";
    public static String password = "1234";
    public static String chromeDriverPath =
            "C:\\Users\\Gustavo Maciel\\Documents\\Diversos\\Projects\\flask-selenium-test\\driver\\chromedriver.exe";

    private static WebDriver driver;

    public static WebDriver getChromeInstance(){
        System.setProperty("webdriver.chrome.driver", getChromeDriverPath());

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static String getChromeDriverPath() {
        return chromeDriverPath;
    }

    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return password;
    }

}
