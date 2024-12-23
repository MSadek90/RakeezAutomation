package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static Utilities.Utility.*;

public class CompanyUploadFilesStep_2 extends BasePage{


    JavascriptExecutor js;

    //Constructor
    public CompanyUploadFilesStep_2(WebDriver driver) {
       super(driver);
        this.js = (JavascriptExecutor) driver;
    }


    //Locators
    By sendFundOrderButton = By.xpath("//button[@role='button']");



    By uploadingDoneButton = By.xpath("//div[contains(@class, 'filepond-upload-fund')]" +
            "//span[@class='filepond--assistant']");

    By textAboveUploadingDoneButton = By.xpath("//label[text()='خطاب بيان للشركة']");



    //Actions
    public CompanyUploadFilesStep_2 uploadFilesStep_2() throws InterruptedException {

        //Locators
        String filePath = System.getProperty("user.dir") + "/src/Attachments/AssertJ_Summary.pdf";



        //Loop
        for(int index = 1; index <= 3; index++){


            //Scrolling to the element
            scrollToElement(driver,By.xpath("//div[@class='first-way-company']" +
                    "[" + index + "]"));


            //FindElements
            driver.findElement(By.xpath("//input" +
                            "[@name='file[sama_files_"+index+"]']"))
                  //SendKeys
                  .sendKeys(filePath);

            Thread.sleep(1000);


        }

        //Assert the last file is already uploaded before clicking on the Upload Button
        WebElement element = driver.findElement(RelativeLocator.with(uploadingDoneButton)
                .below(textAboveUploadingDoneButton));

        new WebDriverWait(driver,Duration.ofSeconds(5))
                .until(ExpectedConditions.textToBePresentInElement(element, "تم الرفع"));



        //Return
          return this;
    }



    public void clickUploadButton() throws InterruptedException {
        WebElement element = driver.findElement(sendFundOrderButton);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(element));
        js.executeScript("arguments[0].click();", element) ;
    }



}