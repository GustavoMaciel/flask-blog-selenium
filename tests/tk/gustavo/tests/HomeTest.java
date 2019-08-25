package tk.gustavo.tests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import pages.HomePage;
import tk.gustavo.config.Configuration;
import tk.gustavo.utils.URL;

public class HomeTest extends BaseTest {
    private HomePage homePage;

    @Before
    public void setUp(){
        driver = Configuration.getChromeInstance();
        driver.get(URL.BASE_URL);
        homePage = new HomePage(driver);
    }

    @Test
    public void containsArticle(){
        WebElement article = homePage.getArticleWithTitle("Godammit");
        System.out.println(article.getText());
    }
}
