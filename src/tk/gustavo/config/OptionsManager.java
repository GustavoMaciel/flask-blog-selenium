package tk.gustavo.config;

import org.openqa.selenium.chrome.ChromeOptions;

class OptionsManager {

    // Get Chrome Options
    static ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");

        return options;
    }
}
