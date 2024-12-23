package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static Utilities.Utility.*;

public class CompanyUploadFilesStep_3 extends BasePage{


    protected WebDriverWait wait;
    protected JavascriptExecutor js;





    //Constructor
    public CompanyUploadFilesStep_3(WebDriver driver) {
       super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.js = (JavascriptExecutor) driver;
    }







    //Locators



    /*
    Locators for FundOrder DropDownList
     */
    By labelAboveFundOrderField = By.xpath("//label[text()='طلب التمويل']");

    By dropDownListFundOrder = By.xpath("//select[contains(@class,'select-page')]");



    /*
    Locators for calendar
     */
    By labelAboveCalendar =By.xpath("//label[contains(text(), 'تاريخ الاستحقاق')]");
    By calendar = By.xpath("//input[@type='text']");



    /*
    Locators for Invoice amount field
     */
    By labelAboveInvoiceAmountField = By.xpath("//label[contains(text(), 'قيمة مبلغ الفاتورة')]");

    By invoiceAmountField = By.xpath("//input[@type='number']");


    /*
    Locators for Invoice Attachment
     */
    By labelAboveInvoiceAttachment = By.xpath("//label[contains(text(), 'ارفق الفاتورة')]");

    By invoiceAttachmentField = By.xpath("//input[@type='file']");


    /*
    Locators for Rakeez Invoice
     */

    By labelAboveRakeezInvoiceField = By.xpath("//label[contains(text(), 'محافظ ركيز')]");

    By dropDownListRakeezInvoice = By.xpath("//select[contains(@class,'select-page')]");


    /*
    Locators for add button
     */
    By addButton = By.xpath("//button[@class='save']");


    By uploadingDoneButton = By.xpath("//div[@class='receipt-upload']" +
            "//span[@class='filepond--assistant']");


    //Actions


    public CompanyUploadFilesStep_3 selectFundOrder(String orderName) {

        Select select = new Select(findRelativeElement(driver, dropDownListFundOrder, labelAboveFundOrderField));

        select.selectByValue(orderName);

        return this;
    }


    public CompanyUploadFilesStep_3 enterCalendar(String date) {
        WebElement dateField = findRelativeElement(driver, calendar, labelAboveCalendar);
        sendKeys(driver,dateField, date);
        return this;
    }

    public CompanyUploadFilesStep_3 enterInvoiceAmount(String amount) {

        sendKeys(driver,
                findRelativeElement(driver, invoiceAmountField, labelAboveInvoiceAmountField),
                amount);

        return this;
    }


    public CompanyUploadFilesStep_3 attachInvoice() throws InterruptedException {
        String filePath = System.getProperty("user.dir") + "/src/Attachments/AssertJ_Summary.pdf";


        driver.findElement(By.xpath("//input[@type='file']"))
                .sendKeys(filePath);






        return this;
    }

    public CompanyUploadFilesStep_3 selectInvoiceType(String type) {
        Select select = new Select(findRelativeElement
                (driver, dropDownListRakeezInvoice, labelAboveRakeezInvoiceField));
        select.selectByVisibleText(type);
        return this;
    }

    public void clickAddButton() throws InterruptedException {
        //wait the file to be uploaded before clicking on the Send Button
        WebElement element = driver.findElement(RelativeLocator.with(uploadingDoneButton)
                .below(driver.findElement(labelAboveInvoiceAttachment)));

        new WebDriverWait(driver,Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(element, "تم الرفع"));


       js.executeScript("arguments[0].click();", driver.findElement(addButton));
    }



    private WebElement findRelativeElement(WebDriver driver, By locator, By textAboveLocator) {
        return driver.findElement(RelativeLocator
                .with(locator)
                .below(textAboveLocator));
    }

}
