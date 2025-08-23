package pageObjets;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver){
     super(driver);
    }

    @FindBy (xpath = "//i[@class='fa fa-user']")
    WebElement myAccount_loc;

    @FindBy (xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[text()='Register']")
    WebElement regLink_loc;

    @FindBy (xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[text()='Login']")
    WebElement loginLink_loc;

    public void clickMyAcc(){
        myAccount_loc.click();
    }

    public void clickRegister(){
        regLink_loc.click();
    }

    public void clickLogin(){
        loginLink_loc.click();
    }
}
