package pageObjets;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
    WebDriver driver;

    public AccountRegistrationPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement firstName_loc;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement lastName_loc;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement email_loc;

    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement telephone_loc;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement password_loc;

    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement confPassword_loc;

    @FindBy(xpath = "//input[@class='btn btn-primary']")
    WebElement continueBtn_loc;

    @FindBy(xpath = "//input[@type='checkbox']")
    WebElement privacyCheckbox;

    @FindBy(css = ".col-sm-9 h1")
    WebElement accountCreationSuccessMessage;

    public void setFirstName(String fName){
        firstName_loc.sendKeys(fName);
    }
    public void setLastName(String lName){
        lastName_loc.sendKeys(lName);
    }
    public void setEmail(String email){
        email_loc.sendKeys(email);
    }
    public void setTelephone(String phnNo){
        telephone_loc.sendKeys(phnNo);
    }
    public void setPassword(String password){
        password_loc.sendKeys(password);
    }
    public void setConfirmPassword(String confPassword){
        confPassword_loc.sendKeys(confPassword);
    }
    public void clickContinue(){
        continueBtn_loc.click();
    }
    public void clickPrivacyCheckbox(){
        privacyCheckbox.click();
    }
    public String getSuccessMessage(){
        return accountCreationSuccessMessage.getText();
    }

}
