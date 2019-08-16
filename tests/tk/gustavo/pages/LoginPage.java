package tk.gustavo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage fillEmail(String usuario) {
        driver.findElement(By.name("email")).sendKeys(usuario);
        return this;
    }

    public LoginPage fillPassword(String senha) {
        driver.findElement(By.name("password")).sendKeys(senha);
        return this;
    }

    public HomePage clickLoginButton() {
        driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
        return new HomePage(driver);
    }

    public HomePage login(String email, String password){
        return fillEmail(email).fillPassword(password).clickLoginButton();
    }


    /**
     * Seeks a Flash Alert from Flask in the page
     *@return true if the WebDriver is able to find an element with the informed class, which means the login has failed.
     */
    @Override
    public boolean hasFlashAlert(String xPath){
        try {
            driver.findElement(By.xpath(xPath));
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

}
