package tk.gustavo.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TLDriverFactory {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public synchronized static void setTlDriver(String browser){
        if(System.getProperty("webdriver.chrome.driver") == null){
            System.setProperty(
                    "webdriver.chrome.driver",
                    "C:\\Users\\Gustavo Maciel\\Documents\\Diversos\\Projects\\flask-selenium-test\\driver\\chromedriver.exe"
            );
        }
        if(browser.equalsIgnoreCase("chrome")){
            tlDriver = ThreadLocal.withInitial(
                    () -> new ChromeDriver(OptionsManager.getChromeOptions())
            );
        }
    }

    public synchronized static WebDriver getTLDriver(){
        return tlDriver.get();
    }

}
