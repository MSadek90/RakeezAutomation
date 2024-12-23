package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.time.Duration;

import static Utilities.Utility.clickAction;
import static Utilities.Utility.scrollToElement;

public class CompanyTableDetailsOfFundOrders extends BasePage{





    //Constructor
    public CompanyTableDetailsOfFundOrders(WebDriver driver){
       super(driver);
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    //Locators


    //طلب تمويل جديد
    //To scroll to it
    By newFundRequest = By.xpath
            ("//div[contains(@class, 'account_balance')]");

    //To click on newFundRequest
    By newFundRequestButton = By.xpath
            ("//div[contains(@class, 'account_balance')]" +
            "//button[@class='action-reset']");


    By notificationLocator = By.xpath("//li[@class='notification']");

    By completeOrderButton = By.xpath("//button[@class='action-reset']");


    //Actions

    //Access the last button (Complete) of the table that under notification Icon
    public void clickCompleteOrderButton(){
        driver.findElement
                        (RelativeLocator.with(completeOrderButton)
                                            .below(notificationLocator))
                                                                    .click();
    }



    public void clickNewFundRequest() throws InterruptedException {
        // Scroll to the middle of the page
       new Actions(driver).moveToElement(driver.findElement(newFundRequest))
               .perform();

        //Clicking Action
        clickAction(driver, newFundRequestButton);
    }
}
