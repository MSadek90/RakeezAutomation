package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    public static WebDriver driver;
    static By assertSuccessProcess = By.xpath("//span[@class='fl-message']");


    public BasePage(WebDriver driver) {
        this.driver = driver;
    }


    public static WebElement getSuccessMessageWebElement() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(driver.findElement(assertSuccessProcess)));

    }

}