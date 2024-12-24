package Utilities;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class Utility {

    /*
    Function  : ClickAction
    Description: Waits for the element to be clickable and performs a click action on it.
    Parameters:
        - By locator: The locator of the element to be clicked.
    Return: Void
    Throws:
        - NoSuchElementException if the element is not found.
        - TimeoutException if the element is not clickable within the specified timeout.
     */
    public static void clickAction(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator))
                    .click();
        } catch (Exception e) {
            System.out.println("Error in ClickAction, Locator is:   " + locator +
                    "   driver URl is :  " + driver.getCurrentUrl());
        }
    }

    /*
  Function  : ClickAction
  Description: Waits for the element to be clickable and performs a click action on it.
  Parameters:
      - WebElement : The element to be clicked.
  Return: Void
  Throws:
      - NoSuchElementException if the element is not found.
      - TimeoutException if the element is not clickable within the specified timeout.
   */
    public static void clickAction(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element))
                    .click();
        } catch (Exception e) {
            System.out.println("Error in ClickAction: " + e.getMessage());
        }
    }



    /*
    Function  : SendKeys
    Description: Waits for the element to be visible and sends the specified text to the element.
    Parameters:
        - By locator: The locator of the input element.
        - String value: The value to be entered into the input field.
    Return: Void
    Throws:
        - NoSuchElementException if the element is not found.
        - TimeoutException if the element is not visible within the specified timeout.
     */
    public static void sendKeys(WebDriver driver, By locator, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            driver.findElement(locator).sendKeys(value);

        } catch (Exception e) {
            System.out.println("Error in SendKeys: " + e.getMessage());
        }
    }

    /*
    the same above but with WebElement
     */

    public static void sendKeys(WebDriver driver, WebElement locator, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(locator))
                    .sendKeys(value);
        } catch (Exception e) {
            System.out.println("Error in SendKeys: " + e.getMessage());
        }
    }

    /*
      Func   : SendKeys to the Input Field with Relative Input filed Above it

      Param  : Webdriver   : driver
               String      : Text            >> The text that above the input field (ارفق شهادة الدخل)
               By          : WithLocator     >> The locator for the input filed
               WebElement  : Locator         >> For each item of the (list.gettext())(ارفق شهادة الدخل)
               String      : value           >> For the filePath

      RetVal : void
     */
    public static void sendKeysToInputFieldWithRelativeTextAboveIt(WebDriver driver
            ,String textAboveInputField
            , By locatorInputField
            , WebElement eachItemOfListOfInputField
            , String filePath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            //wait until each text above the input field to be presented
            wait.until(ExpectedConditions.textToBePresentInElement(eachItemOfListOfInputField,textAboveInputField));

            //send Keys to the input
            driver.findElement(RelativeLocator
                                   .with(locatorInputField)
                                            .below(eachItemOfListOfInputField))
                                                     .sendKeys(filePath);

        } catch (Exception e) {
            System.out.println("Error in SendKeys: " + e.getMessage());
        }
    }



    /*
     Function  : scrollToElement
     Description: Scrolls the page to make the specified element visible in the viewport using JavaScript.
     Parameters:
         - WebDriver driver: The WebDriver instance.
         - By locator: The locator of the element to scroll to.
     Return: Void
     Throws:
         - NoSuchElementException if the element is not found.
         - TimeoutException if the element is not visible within the specified timeout.
     */

    public static void scrollToElement(WebDriver driver, By locator) {
        try {
            // Scroll to the element using JavaScriptExecutor
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));
        } catch (Exception e) {
            System.out.println("Error in scrollToElement: " + e.getMessage());
        }
    }


    /*
  Function  : ClickAction
  Description: Waits for the element to be clickable and performs a click action on it.
  Parameters:
      - WebElement: The element to be clicked.
  Return: Void
  Throws:
      - NoSuchElementException if the element is not found.
      - TimeoutException if the element is not clickable within the specified timeout.
   */
    public static void scrollToElement(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // Scroll to the element using JavaScriptExecutor
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);",element);
        } catch (Exception e) {
            System.out.println("Error in scrollToElement: " + e.getMessage());
        }
    }







    public static String getTimeStamp()
    {
        return new SimpleDateFormat("yyyy/MM/dd/HH-mm-ssa").format(new Date());
    }

    /*
        Taking ScreenShots

     */

    public static void takeScreenShot(WebDriver driver, String fileName) {
        try {
            final String pathFile = "Outputs/ScreenShots/";

            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            File destinationFile = new File(pathFile + fileName + ".png");

            FileUtils.copyFile(sourceFile, destinationFile);

            Allure.addAttachment(fileName, Files.newInputStream(Path.of(destinationFile.getPath())));
        }
        catch (Exception e) {
            System.out.println("Error in ScreenShot: " + e.getMessage());
        }

    }




















}