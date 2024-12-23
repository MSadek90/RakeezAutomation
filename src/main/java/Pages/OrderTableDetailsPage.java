package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

import static Utilities.Utility.clickAction;

public class OrderTableDetailsPage extends BasePage{








    //Constructor
    public OrderTableDetailsPage(WebDriver driver) {
        super(driver);
    }








    //Locators
    By orderDetailsButton = By.xpath("//*[@id=\"DataTable\"]/table/tbody/tr[1]/td[12]/div[2]/a");

    By continueOrderButton = By.xpath("//a[contains(@href, 'https://rs.rakeez.sa/admins/funding-requests/')]");

    By notificationButton = By.xpath("//div[@class='information-rakeez-login']");

    By confirmButton = By.xpath("//a[contains(@href, 'https://rs.rakeez.sa/admins/funding-requests-approve')]");










    //Actions
    public void clickOrderDetailsButton() {
        clickAction(driver, orderDetailsButton);
    }



    public void clickConfirmOrderButton() {
        WebElement element = driver.findElement(RelativeLocator.with(confirmButton)
                .below(notificationButton));

        element.click();
    }



}


