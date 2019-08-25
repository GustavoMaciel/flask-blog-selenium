package tk.gustavo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "confirm_password")
    private WebElement confirmPasswordInput;

    @FindBy(id = "submit")
    private WebElement submitButton;

    public RegisterPage fillUsername(String username){
        usernameInput.clear();
        usernameInput.sendKeys(username);
        return this;
    }
    public RegisterPage fillEmail(String email){
        emailInput.clear();
        emailInput.sendKeys(email);
        return this;
    }

    public RegisterPage fillPassword(String password){
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    public RegisterPage fillconfirmPassword(String password){
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys(password);
        return this;
    }

    public RegisterPage fillBothPasswordFields(String password){
        passwordInput.clear();
        passwordInput.sendKeys(password);
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys(password);
        return this;
    }

    public LoginPage clickSignInButton(){
        submitButton.submit();
        return new LoginPage(driver);
    }

    public LoginPage register(String username, String email, String password){
        return fillUsername(username)
                .fillEmail(email)
                .fillPassword(password)
                .fillconfirmPassword(password)
                .clickSignInButton();
    }


}
