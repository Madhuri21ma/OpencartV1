package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjets.AccountRegistrationPage;
import pageObjets.HomePage;
import testBase.BaseClass;

import java.util.Locale;

public class TC001_AccountRegistrationTest extends BaseClass {

    @Test(groups = {"Regression","Master"})
    void verify_account_registration() {
        try {
            logger.info("***Starting TC001_AccountRegistrationTest ");
            HomePage hp = new HomePage(driver);
            hp.clickMyAcc();
            logger.info("***clicked on my account");
            logger.debug("***clicked on my account");
            hp.clickRegister();
            logger.info("***clicked on register");
            AccountRegistrationPage accRegPage = new AccountRegistrationPage(driver);
            logger.info("***providing customer details......");
//        accRegPage.setFirstName("Madhuri");
            accRegPage.setFirstName(randomString().toUpperCase(Locale.ROOT));
//        accRegPage.setLastName("Kulkarni");
            accRegPage.setLastName(randomString().toUpperCase(Locale.ROOT));
            accRegPage.setEmail(randomString() + "@gmail.com");
            accRegPage.setTelephone(randomNumber());
            String pass = randomAlphaNumber();
            accRegPage.setPassword(pass);
            accRegPage.setConfirmPassword(pass);

            accRegPage.clickPrivacyCheckbox();
            accRegPage.clickContinue();
            logger.info("***Validating expected message");
            if(accRegPage.getSuccessMessage().equals("Your Account Has Been Created!")){
                Assert.assertTrue(true);
            }else {
                logger.error("test failed");
                logger.debug("debug logs");
                Assert.assertTrue(false);
            }
            Assert.assertEquals(accRegPage.getSuccessMessage(),"Your Account Has Been Created!");
        } catch (Exception e) {

            Assert.fail();
        }
        logger.info("***Finished TC001_AccountRegistrationTest ");
    }

}
