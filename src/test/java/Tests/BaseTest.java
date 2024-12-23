package Tests;

import Pages.*;
import Utilities.LogUtilities;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.awt.*;
import java.io.IOException;

import static Properties.ReadDataFromFile.getPropertyValue;


public class BaseTest {

   public WebDriver driver;
   BasePage basePage;






    @BeforeTest
    public void setUp() throws IOException, AWTException {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        basePage = new BasePage(driver);



        driver.get(getPropertyValue("Data","url"));
        LogUtilities.info("The url is opened");
    }


    @AfterTest
    public void tearDown() {
        driver.quit();
    }


}
