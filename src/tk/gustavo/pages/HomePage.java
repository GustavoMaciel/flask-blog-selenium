package tk.gustavo.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePage extends BasePage{

    public HomePage(WebDriver driver){
        super(driver);
    }

    public WebElement getArticleWithTitle(String title){
        String xPath = String.format(
                "//*[@class='article-title' and contains(text(), '%s')]/ancestor::div[@class='media-body']",
                title
        );
        WebElement article = getElement(By.xpath(xPath));
        return article;
    }

    public AboutPage clickAboutButton(){
        aboutButton.click();
        return new AboutPage(driver);
    }


}
