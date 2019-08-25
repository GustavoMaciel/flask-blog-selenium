package tk.gustavo.tests.junit;

import static org.junit.Assert.*;
import static tk.gustavo.config.Configuration.*;

import org.junit.Before;
import org.junit.Test;

import tk.gustavo.pages.LoginPage;
import tk.gustavo.utils.URL;

/*
* This class refers to the TS01 that can be found at the Tests Report excel file
* Every test in this class will be name after a test case in the Test Suite 01
* e.g. tc001_loginWithValidCredentials
 */
public class LoginTest extends BaseTest{

    private LoginPage loginPage;

    @Before
    public void setUp(){
        driver = getChromeInstance();
        driver.get(URL.LOGIN_URL);
        loginPage = new LoginPage(driver);
    }


    @Test
    public void tc001_loginWithValidCredentials(){
        boolean loggedIn =
                loginPage.login(getEmail(), getPassword())
                .isLoggedIn();

        assertTrue(loggedIn);
    }

    @Test
    public void tc002_loginWithInvalidCredentials(){
        loginPage.login("test@test.com", "yadayada");
        assertTrue(loginPage.hasFlashAlert("//div[@class='alert alert-danger']"));
    }

    @Test
    public void tc003_loginWithInvalidFormatEmail(){
        boolean invalidFeedback = loginPage.login("test", getPassword())
                .hasInvalidFeedback();

        assertTrue(invalidFeedback);
    }


    @Test
    public void tc004_loginWithoutEmail(){
        executeJavaScript(
          "document.getElementsByName('email')[0].removeAttribute('required')"
        );

        boolean invalidFeedback =
            loginPage.fillPassword(getPassword())
            .clickLoginButton()
            .hasInvalidFeedback();
        assertTrue(invalidFeedback);
    }

    @Test
    public void tc005_loginWithoutPassword(){
        executeJavaScript(
                "document.getElementsByName('password')[0].removeAttribute('required')"
        );

        boolean invalidFeedback =
                loginPage.fillEmail(getEmail())
                .clickLoginButton()
                .hasInvalidFeedback();
        assertTrue(invalidFeedback);
    }

    @Test
    public void tc006_accessRestrictedPageWithoutBeingLoggedIn(){
        driver.get(URL.NEW_POST_URL);
        assertTrue(loginPage.hasFlashAlert("//div[@class='alert alert-info']"));
    }

    // Needs improvement
    @Test
    public void tc007_logoutFromService(){
        assertFalse(
                loginPage.login(getEmail(), getPassword())
                .logout()
                .isLoggedIn()
                );
    }

}
