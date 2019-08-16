package tk.gustavo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public boolean hasFlashAlert(String xPath){
        return false;
    }

    /**
     * Seeks a div with the class invalid-feedback in the page
     *@return true if the WebDriver is able to find an element with the informed class.
     */
    public boolean hasInvalidFeedback(){
        try{
            driver.findElement(By.xpath("//div[@class='invalid-feedback']"));
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public void logout(){
        WebElement logoutButton = driver.findElement(By.linkText("Logout"));
        logoutButton.click();
    }

    public boolean isLoggedIn(){
        try {
            WebElement logoutButton = driver.findElement(By.linkText("Logout"));
            return true;
        } catch (NoSuchElementException e){
            return false;
        }

    }

}
