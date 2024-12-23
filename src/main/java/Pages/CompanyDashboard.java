package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Utilities.Utility.clickAction;
import static Utilities.Utility.scrollToElement;





public class CompanyDashboard extends BasePage{


    public CompanyDashboard(WebDriver driver) {
        super(driver);
    }

    //Locators

    //طلبات التمويل
    By fundingRequests = By.linkText("طلبات التمويل");

    //Actions


    public CompanyDashboard clickFundingRequests(){
         clickAction(driver, fundingRequests);
         return this;
    }


}
