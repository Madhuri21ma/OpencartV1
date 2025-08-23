package pageObjets;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

    public MyAccountPage(WebDriver driver){
        super(driver);
    }

    @FindBy (xpath = "//h2[text()='My Account']")
    WebElement myAccText;

    @FindBy (linkText = "Logout")
    WebElement logoutBtn;

    public boolean isMyAccPageExist(){
        try {
            return (myAccText.isDisplayed());
        }catch (Exception e){
         return false;
        }
    }
    public void clickLogout(){
        logoutBtn.click();
    }
}
