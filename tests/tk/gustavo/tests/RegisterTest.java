package tk.gustavo.tests;

import org.junit.Before;
import org.junit.Test;
import pages.RegisterPage;
import tk.gustavo.utils.NameGenerator;
import tk.gustavo.utils.URL;

import static org.junit.Assert.assertTrue;
import static tk.gustavo.config.Configuration.getChromeInstance;

public class RegisterTest extends BaseTest {

    private String password = "1234";
    private String username;
    private String email;

    private RegisterPage registerPage;

    @Before
    public void setUp(){
        driver = getChromeInstance();
        driver.get(URL.REGISTER_URL);
        registerPage = new RegisterPage(driver);

        String randomFirstName = NameGenerator.generateName();
        String randomLastName = NameGenerator.generateName();
        username = randomFirstName + randomLastName;
        email = randomFirstName + "." + randomLastName + "@gmail.com";
    }

    @Test
    public void tc001_registerWithCorrectCredentials(){
        assertTrue(
                registerPage.register(username, email, password)
                        .hasFlashAlert("//div[@class='alert alert-success']")
        );
    }

    @Test
    public void tc002_registerWithCorrectCredentialsAndLogIn(){
        boolean isLoggedIn = registerPage.register(username, email, password)
                .login(email, password)
                .isLoggedIn();

        assertTrue(isLoggedIn);
    }

    @Test
    public void tc003_registerWithAlreadyExistentUsername(){
        assertTrue(
                registerPage.register("gustavo", email, password)
                        .hasInvalidFeedback()
        );
    }

    @Test
    public void tc004_registerWithAlreadyExistentEmail(){
        assertTrue(
                registerPage.register(username, "gm.nunes92@gmail.com", password)
                        .hasInvalidFeedback()
        );
    }

    @Test
    public void tc005_registerWithoutUsername(){
        executeJavaScript(
                "document.getElementsByName('username')[0].removeAttribute('required')"
        );
        assertTrue(
                registerPage.fillBothPasswordFields(password)
                        .fillEmail(email)
                        .clickSignInButton()
                        .hasInvalidFeedback()
        );

    }

    @Test
    public void tc006_registerWithoutEmail(){
        executeJavaScript(
                "document.getElementsByName('email')[0].removeAttribute('required')"
        );
        assertTrue(
                registerPage.fillBothPasswordFields(password)
                        .fillUsername(username)
                        .clickSignInButton()
                        .hasInvalidFeedback()
        );

    }

    @Test
    public void tc007_registerWithoutPassword(){
        executeJavaScript(
                "document.getElementsByName('password')[0].removeAttribute('required')"
        );
        assertTrue(
                registerPage.fillconfirmPassword(password)
                        .fillUsername(username)
                        .fillEmail(email)
                        .clickSignInButton()
                        .hasInvalidFeedback()
        );
    }

    @Test
    public void tc008_registerWithoutConfirmPassword(){
        executeJavaScript(
                "document.getElementsByName('confirm_password')[0].removeAttribute('required')"
        );
        assertTrue(
                registerPage.fillPassword(password)
                        .fillUsername(username)
                        .fillEmail(email)
                        .clickSignInButton()
                        .hasInvalidFeedback()
        );
    }

    @Test
    public void tc009_registerWithUnmatchingPasswords(){
        assertTrue(
                registerPage.fillEmail(email)
                        .fillUsername(username)
                        .fillPassword(password)
                        .fillconfirmPassword("4123")
                        .clickSignInButton()
                        .hasInvalidFeedback()
        );
    }

    @Test
    public void tc010_registerWithWayTooBigUsername(){
        assertTrue(
                registerPage.register(username+email+username+username+email+username,email, password)
                        .hasInvalidFeedback()
        );
    }
}
