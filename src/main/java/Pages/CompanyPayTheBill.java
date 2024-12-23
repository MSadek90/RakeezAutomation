package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Utilities.Utility.clickAction;

public class CompanyPayTheBill extends BasePage{



    //Constructor
    public CompanyPayTheBill(WebDriver driver) {
        super(driver);
    }


    //Locators
    By feesButton = By.xpath("//button[@class='button-acceptable']");


    //Actions

    public void clickFeesButton() {
        clickAction(driver, driver.findElement(feesButton));
    }

}
