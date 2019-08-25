package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasePage {

    protected WebDriver driver;
    private boolean loggedIn;
    @FindBy(linkText = "About")
    protected WebElement aboutButton;

    public BasePage(WebDriver driver){
        this.loggedIn = false;
        this.driver = driver;
    }


    public void setLoggedIn(boolean loggedIn){this.loggedIn = loggedIn;}

    public boolean isLoggedIn(){return loggedIn;}

    public boolean hasElement(By searchParameter){
        try {
            driver.findElement(searchParameter);
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public WebElement getElement(By search){
        try {
            return driver.findElement(search);
        }catch (NoSuchElementException e){
            return null;
        }
    }

    /**
     * Seeks a Flash Alert from Flask in the page
     *@return true if the WebDriver is able to find an element with the informed class, which means the login has failed.
     */
    public boolean hasFlashAlert(String xPath){
        return hasElement(By.xpath(xPath));
    }

    /**
     * Seeks a div with the class invalid-feedback in the page
     *@return true if the WebDriver is able to find an element with the informed class.
     */
    public boolean hasInvalidFeedback(){
        return hasElement(By.xpath("//div[@class='invalid-feedback']"));
    }

    public BasePage logout(){
        if(loggedIn) {
            WebElement logoutButton = driver.findElement(By.linkText("Logout"));
            logoutButton.click();
        }
        this.setLoggedIn(false);
        return this;
    }

}
