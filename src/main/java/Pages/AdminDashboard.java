package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Utilities.Utility.clickAction;

public class AdminDashboard extends BasePage {





    //Constructor
    public AdminDashboard(WebDriver driver){

       super(driver);
    }


    //Locators
    /*
    Note: PLZ Try To change it
     */
    By companiesLocator = By.xpath("/html/body/div[3]/div[2]/div/div[1]/div/ul/li[4]/button");

    By fundOrderLocator= By.linkText("طلبات التمويل");




    //Actions
    public AdminDashboard clickCompanyButton() throws InterruptedException {
        clickAction(driver, companiesLocator);
        return this;
    }

    public void clickFundOrderButton(){
        clickAction(driver, fundOrderLocator);
    }




}
