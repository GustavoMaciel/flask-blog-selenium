package tk.gustavo.tests;

import static org.junit.Assert.*;
import static tk.gustavo.config.Configuration.*;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import tk.gustavo.pages.LoginPage;
import tk.gustavo.utils.URL;

/*
* This class refers to the TS01 that can be found at the Tests Report excel file
* Every test in this class will be name after a test case in the Test Suite 01
* e.g. tc001_loginWithValidCredentials
 */
public class LoginTest extends BaseTest{

    @Before
    public void setUpTestSuit(){
        driver = getChromeInstance();
        driver.get(URL.LOGIN_URL);
    }


    @Test
    public void tc001_loginWithValidCredentials(){
        boolean loggedIn = new LoginPage(driver)
                .login(getEmail(), getPassword())
                .isLoggedIn();

        assertTrue(loggedIn);
    }

    @Test
    public void tc002_loginWithInvalidCredentials(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("test@test.com", "yadayada");
        assertTrue(loginPage.hasFlashAlert("//div[@class='alert alert-danger']"));
    }

    @Test
    public void tc003_loginWithInvalidFormatEmail(){
        boolean invalidFeedback = new LoginPage(driver)
                .login("test", getPassword())
                .hasInvalidFeedback();

        assertTrue(invalidFeedback);
    }


    @Test
    public void tc004_loginWithoutEmail(){
        executeJavaScript(
          "document.getElementsByName('email')[0].removeAttribute('required')"
        );

        boolean invalidFeedback = new LoginPage(driver)
                .fillPassword(getPassword())
                .clickLoginButton()
                .hasInvalidFeedback();
        assertTrue(invalidFeedback);
    }

    @Test
    public void tc005_loginWithoutPassword(){
        executeJavaScript(
                "document.getElementsByName('password')[0].removeAttribute('required')"
        );

        boolean invalidFeedback = new LoginPage(driver)
                .fillEmail(getEmail())
                .clickLoginButton()
                .hasInvalidFeedback();
        assertTrue(invalidFeedback);
    }

    @Test
    public void tc006_accessRestrictedPageWithoutBeingLoggedIn(){
        driver.get(getBaseUrl() + "post/new");
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.hasFlashAlert("//div[@class='alert alert-info']"));
    }

    // Needs improvement
    @Test
    public void tc007_logoutFromService(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(getEmail(), getPassword())
                .logout();
        assertFalse(loginPage.isLoggedIn());
    }

}
