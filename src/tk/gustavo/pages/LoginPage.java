package tk.gustavo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(name = "submit")
    private WebElement submitButton;

    public LoginPage fillEmail(String usuario) {
        emailInput.clear();
        emailInput.sendKeys(usuario);
        return this;
    }

    public LoginPage fillPassword(String senha) {
        passwordInput.clear();
        passwordInput.sendKeys(senha);
        return this;
    }

    public BasePage clickLoginButton() {
        submitButton.click();
        BasePage basePage = new BasePage(driver);
        basePage.setLoggedIn(true);
        return basePage;
    }

    public BasePage login(String email, String password){
        this.setLoggedIn(true);
        return fillEmail(email).fillPassword(password).clickLoginButton();
    }

}
