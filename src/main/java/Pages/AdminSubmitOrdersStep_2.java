package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.util.List;

import static Utilities.Utility.clickAction;
import static Utilities.Utility.scrollToElement;

public class AdminSubmitOrdersStep_2 extends BasePage {


    protected JavascriptExecutor js;

    //Constructor
    public AdminSubmitOrdersStep_2(WebDriver driver) {
        super(driver);
        js = (JavascriptExecutor) driver;
    }

    //Locators
    By acceptButton = By.xpath("//button[contains(@class, 'accept-button')]");

    By text = By.xpath("//div[contains(@class, 'neasted-list')]/p");

    By sendButton = By.xpath("//button[@class='send-button']");





    //Actions
    public AdminSubmitOrdersStep_2 clickAcceptButton() throws InterruptedException {
        List<WebElement> allTexts = driver.findElements(text);


        for(WebElement element : allTexts) {

        js.executeScript("arguments[0].click();", driver.findElement(RelativeLocator
                .with(acceptButton)
                .toLeftOf(element)));


            scrollToElement(driver, element);


            Thread.sleep(1000);

        }
        return this;
    }

    public void clickSendButton() {
       js.executeScript("arguments[0].click();", driver.findElement(sendButton));
    }
}
