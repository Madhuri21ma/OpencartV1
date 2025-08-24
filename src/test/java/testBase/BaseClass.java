package testBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.formula.atp.Switch;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverInfo;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.apache.logging.log4j.LogManager;   //specific import
import org.apache.logging.log4j.Logger;       //specific import
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {
    public static WebDriver driver;
    public Logger logger;    // Log4j
    public Properties prop;

    @BeforeClass(groups = {"Sanity", "Regression", "Master"})
    @Parameters({"OS", "browser"})
    public void setUp(String OS, String browser) throws IOException {
        prop = new Properties();
        FileReader fr = new FileReader(".//src/test/resources/config.properties");
        prop.load(fr);

        logger = LogManager.getLogger(this.getClass());
        if (prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            //os
            if (OS.equalsIgnoreCase("Windows")) {
                capabilities.setPlatform(Platform.WIN11);
            } else if (OS.equalsIgnoreCase("mac")) {
                capabilities.setPlatform(Platform.MAC);
            } else {
                System.out.println("No Matching OS");
                return;
            }
            //browser
            switch (browser.toLowerCase()) {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;
                case "firefox":
                    capabilities.setBrowserName("firefox");
                    break;
                default:
                    capabilities.setBrowserName("No matching browser");
                    return;
            }
            driver = new RemoteWebDriver(new URL("http://192.168.1.34:4444"), capabilities);
        }
        if (prop.getProperty("execution_env").equalsIgnoreCase("local")) {
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                default:
                    System.out.println("invalid browser name");
                    return;
//                return will exit from entire execution
            }
        }
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.get("http://localhost/OpenCartSite/");
        driver.get(prop.getProperty("appURL2"));
        driver.manage().window().maximize();
    }

    @AfterClass (groups = {"Sanity","Regression","Master"})
    public void tearDown(){
        driver.close();
    }

    public String randomString(){
        return RandomStringUtils.randomAlphabetic(5);
    }
    public String randomNumber(){
        return RandomStringUtils.randomNumeric(5);
    }
    public String randomAlphaNumber(){
        return RandomStringUtils.randomAlphanumeric(5);
    }
    public String captureScreen(String tname) throws IOException{
        String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mmss").format(new Date());
        TakesScreenshot ts= (TakesScreenshot) driver;
        File srcFile=ts.getScreenshotAs(OutputType.FILE);
        String targetFilePath= System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp;
        File targetFile= new File(targetFilePath);
        srcFile.renameTo(targetFile);
        return targetFilePath;

    }

}
