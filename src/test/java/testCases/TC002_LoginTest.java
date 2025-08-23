package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjets.HomePage;
import pageObjets.LoginPage;
import pageObjets.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
    @Test (groups = {"Sanity","Master"})
    public void verify_login(){
        logger.info("Starting TC_001_LoginTest");
        try {

            HomePage hp = new HomePage(driver);
            hp.clickMyAcc();
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);

            lp.setEmail(prop.getProperty("email"));
            lp.setPassword(prop.getProperty("password"));
            lp.clickSubmitBtn();

            //My Account
            MyAccountPage mp = new MyAccountPage(driver);
            boolean targetPage = mp.isMyAccPageExist();
            Assert.assertTrue(targetPage);
        }catch (Exception e){
            Assert.fail();
        }
        logger.info("******Finished TC_001_LoginTest*********");




    }
}
