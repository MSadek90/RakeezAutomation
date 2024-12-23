package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;
import java.util.List;

import static Utilities.Utility.*;

public class CompanyUploadFilesStep_1 extends BasePage{


    JavascriptExecutor js;
    WebDriverWait wait;


    public CompanyUploadFilesStep_1(WebDriver driver) {
       super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.js = (JavascriptExecutor) driver;
    }







    //Locators

    By dateIndex_2 = By.xpath("//input[@type='date']");

    By sendFundFilesButton = By.xpath("//button[@class='action-get-now']");

    By fileLocator = By.xpath("//input[@type='file']");

    By textLocator = By.xpath("//label[@for='exampleInputtext1']");

    By uploadingDoneButton = By.xpath("//div[contains(@class, 'filepond-upload-fund')]" +
            "//span[@class='filepond--assistant']");








    public CompanyUploadFilesStep_1 sendFundFiles() throws AWTException, InterruptedException {

        /*To get file Path*/
        final String filePath = System.getProperty("user.dir") + "/src/Attachments/AssertJ_Summary.pdf";


         /*List that get all text to obtain the input to pass them files*/
         List<WebElement> allTexts = driver.findElements(textLocator);


       for(WebElement element : allTexts) {

           //Scrolling to each element*/
           scrollToElement(driver, element);


           //Send Keys to the Input filed
           sendKeysToInputFieldWithRelativeTextAboveIt
                   (driver,element.getText(),fileLocator,element,filePath);

           //To send the date
           if(element.getText().equals("ارفق شهادة الزكاة والدخل"))
           {
               sendKeys(driver,dateIndex_2,"12/10/2025");
           }
           Thread.sleep(1000);

       }

        //wait the last file to be uploaded before clicking on the Send Button
        WebElement element = driver.findElement(RelativeLocator.with(uploadingDoneButton)
                .below(allTexts.get(allTexts.size() - 1)));

        new WebDriverWait(driver,Duration.ofSeconds(5))
                .until(ExpectedConditions.textToBePresentInElement(element, "تم الرفع"));


        return this;
    }



    public void clickSendFundFilesButton() throws InterruptedException {
        js.executeScript("arguments[0].click();", driver.findElement(sendFundFilesButton));
    }


}
