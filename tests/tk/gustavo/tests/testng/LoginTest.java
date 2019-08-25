package tk.gustavo.tests.testng;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import tk.gustavo.config.TLDriverFactory;
import tk.gustavo.pages.LoginPage;
import tk.gustavo.utils.URL;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

public class LoginTest extends TestBase {

    private String email = "gm.nunes92@gmail.com";
    private String password = "1234";


    @Test
    public void tc001_loginWithValidCredentials(){
        WebDriver driver = TLDriverFactory.getTLDriver();
        driver.navigate().to(URL.LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);

        assertTrue(
                loginPage.login(email, password)
                .isLoggedIn()
        );

        driver.quit();
    }

    @Test
    public void tc002_loginWithInvalidCredentials(){
        WebDriver driver = TLDriverFactory.getTLDriver();
        driver.navigate().to(URL.LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("test@test.com", "yadayada");
        assertTrue(loginPage.hasFlashAlert("//div[@class='alert alert-danger']"));

        driver.quit();
    }

    @Test
    public void tc003_loginWithInvalidFormatEmail(){
        WebDriver driver = TLDriverFactory.getTLDriver();
        driver.navigate().to(URL.LOGIN_URL);

        boolean invalidFeedback = new LoginPage(driver)
                .login("test", password)
                .hasInvalidFeedback();

        assertTrue(invalidFeedback);

        driver.quit();
    }


    @Test
    public void tc004_loginWithoutEmail(){
        WebDriver driver = TLDriverFactory.getTLDriver();
        driver.navigate().to(URL.LOGIN_URL);

        ((JavascriptExecutor) driver).executeScript(
                "document.getElementsByName('email')[0].removeAttribute('required')"
        );

        boolean invalidFeedback = new LoginPage(driver)
                .fillPassword(password)
                .clickLoginButton()
                .hasInvalidFeedback();
        assertTrue(invalidFeedback);

        driver.quit();
    }

    @Test
    public void tc005_loginWithoutPassword(){
        WebDriver driver = TLDriverFactory.getTLDriver();
        driver.navigate().to(URL.LOGIN_URL);

        ((JavascriptExecutor) driver).executeScript(
                "document.getElementsByName('password')[0].removeAttribute('required')"
        );

        boolean invalidFeedback = new LoginPage(driver)
                .fillEmail(email)
                .clickLoginButton()
                .hasInvalidFeedback();
        assertTrue(invalidFeedback);

        driver.quit();
    }

    @Test
    public void tc006_accessRestrictedPageWithoutBeingLoggedIn(){
        WebDriver driver = TLDriverFactory.getTLDriver();
        driver.navigate().to(URL.BASE_URL + "post/new");
        LoginPage loginPage = new LoginPage(driver);

        assertTrue(loginPage.hasFlashAlert("//div[@class='alert alert-info']"));

        driver.quit();
    }

    // Needs improvement
    @Test
    public void tc007_logoutFromService(){
        WebDriver driver = TLDriverFactory.getTLDriver();
        driver.navigate().to(URL.LOGIN_URL);
        LoginPage loginPage = new LoginPage(driver);

        assertFalse(
                loginPage.login(email, password)
                        .logout()
                        .isLoggedIn()
        );

        driver.quit();
    }

}
