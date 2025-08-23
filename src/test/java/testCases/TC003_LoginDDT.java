package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjets.BasePage;
import pageObjets.HomePage;
import pageObjets.LoginPage;
import pageObjets.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
    @Test (dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "Datadriver")
    public void verify_loginDDT(String emailId, String pwd, String exp) {
        logger.info("*****Starting TC003_LoginDDT********");
        try {
//        HomePage
            HomePage hp = new HomePage(driver);
            hp.clickMyAcc();
            hp.clickLogin();
//        LoginPage
            LoginPage lp = new LoginPage(driver);
            lp.setEmail(emailId);
            lp.setPassword(pwd);
            lp.clickSubmitBtn();

            //My Account
            MyAccountPage mp = new MyAccountPage(driver);
            boolean targetPage = mp.isMyAccPageExist();
            //data valid: login success-test pass - logout
//                       login failed- test failed
            //data invalid: login success-test fail - logout
//                       login failed- test pass


            if (exp.equalsIgnoreCase("valid")) {
                if (targetPage == true) {
                    mp.clickLogout();
                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }
            }
            if (exp.equalsIgnoreCase("invalid")) {
                if (targetPage == true) {
                    mp.clickLogout();
                    Assert.assertTrue(false);
                }
            } else {
                Assert.assertTrue(true);
            }
        }catch (Exception e){
            Assert.fail();
        }
        logger.info("********Finished TC003_LoginDDT*********");
    }

}
