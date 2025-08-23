package pageObjets;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy (css = "#input-email")
    WebElement inputEmail;

    @FindBy (css = "#input-password")
    WebElement inputPassword;

    @FindBy (xpath = "//input[@type='submit']")
    WebElement submitBtn;

    public void setEmail(String mail){
        inputEmail.sendKeys(mail);
    }
    public void setPassword(String pwd){
        inputPassword.sendKeys(pwd);
    }
    public void clickSubmitBtn(){
        submitBtn.click();
    }

}
