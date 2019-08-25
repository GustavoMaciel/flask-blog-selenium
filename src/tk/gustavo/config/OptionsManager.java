package tk.gustavo.config;

import org.openqa.selenium.chrome.ChromeOptions;

public class OptionsManager {

    // Get Chrome Options
    public static ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");

        return options;
    }
}
