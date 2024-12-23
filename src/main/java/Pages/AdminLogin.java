package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.time.Duration;


import static Properties.ReadDataFromFile.getPropertyValue;
import static Utilities.Utility.*;

public class AdminLogin extends BasePage {



    //Constructor
    public AdminLogin(WebDriver driver){
        super(driver);
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    //Locators

    //Email Field
    By emailLocator = By.xpath("//input[@id='email']");

    //Password Field
    By passwordLocator = By.xpath("//input[@id='password']");


    //Button Login
    By loginScroll = By.xpath("//div[@class='forget-pass']");

    By loginLocator = By.xpath("//button[text()='تسجيل دخول']");


    //OTP
    By otpSendButton = By.xpath("//button[@class='send-now']");




    //Actions

    public AdminLogin enterEmail(String email) {
        sendKeys(driver,emailLocator, email);
        return this;
    }

    public AdminLogin enterPassword(String password) throws InterruptedException {

        sendKeys(driver,passwordLocator, password);
        return this;
    }

    public void clickLogin() throws InterruptedException {
        scrollToElement(driver,loginScroll);
        Thread.sleep(500);
        clickAction(driver,loginLocator);
    }

    public AdminLogin enterOTP() throws IOException {
        for (int index = 0 ; index<6 ; index++) {
            sendKeys(driver, By.xpath("(//div[@id='otp']//input)[" + (index + 1) + "]")
                    , getPropertyValue("Data","OTP"));
        }
        return this;
    }

    public void submitOTP() throws InterruptedException {
        clickAction(driver, otpSendButton);
    }




}
