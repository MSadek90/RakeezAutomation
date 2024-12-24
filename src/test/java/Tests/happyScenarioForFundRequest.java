package Tests;
import Pages.*;
import Utilities.LogUtilities;
import Utilities.Utility;
import io.qameta.allure.Description;
import org.openqa.selenium.WindowType;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import static Pages.BasePage.getSuccessMessageWebElement;
import static Properties.ReadDataFromFile.getPropertyValue;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import Listeners.ITest;




@Listeners(ITest.class)
public class happyScenarioForFundRequest extends BaseTest {



    static String adminTabID, companyTabID;


    /*
        As:  a company :
        I :  want to add a new fund order
        So:  I access my dashboard and start adding a new order
     */

    @Test(priority = 0)
    public void companyNewFundOrder() throws InterruptedException, IOException {

        //From menu list I click on FundingRequests (طلبات التمويل)
        new CompanyDashboard(driver)
                .clickFundingRequests();
        LogUtilities.info("The company clicks on the fundRequests button");

        //Then clicking on new fund request (طلب تمويل جديد)
       new CompanyTableDetailsOfFundOrders(driver)
                        .clickNewFundRequest();
        LogUtilities.info("The company clicks on New fundRequest");


       //Assert if the company navigates to access a new fund order page
       assertThat(driver.getCurrentUrl())
                          .isEqualTo(getPropertyValue("Data","expected_Url"));

        Utility.takeScreenShot(driver,"companyNewRequest");

    }



    /*
          As:  a company :
          I :  want to Upload the files in Step_1
          So:  I access the files page and start to uploading them
     */

    @Test(priority = 1, dependsOnMethods = {"companyNewFundOrder"})
    public void companyUploadFiles() throws InterruptedException, AWTException {


        //Send the files and submit them
        new CompanyUploadFilesStep_1(driver)
                .sendFundFiles()
                            .clickSendFundFilesButton();
        LogUtilities.info("The company send files in the first step then click on the send button");



        assertThat(getSuccessMessageWebElement()
                                          .getText())
                                                   .isEqualTo
                        ("تم استلام طلبك بنجاح جاري العمل على طلب التمويل الخالص بك");

        Utility.takeScreenShot(driver,"companySendFiles");


       Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_MINUS);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_MINUS);


        //Assign the tab ID of the company to (shift to the admin tab)
        companyTabID = driver.getWindowHandle();
    }




   /*
        As:  an Admin
        I :  want to Login and access Admin Dashboard
    */

    @Test(priority = 2,dependsOnMethods = {"companyUploadFiles"})
    public void adminLogin() throws InterruptedException, IOException {

        //Switch to Admin Tab
       driver.switchTo().newWindow(WindowType.TAB);

       //Navigate to Admin URL
       driver.get(getPropertyValue("Data","AdminUrl"));



        //Login Process
        new AdminLogin(driver)
                .enterEmail(getPropertyValue("Data","AdminEmail"))
                      .enterPassword(getPropertyValue("Data","AdminPassword"))
                                                                        .clickLogin();
        LogUtilities.info("The Admin Login to his Dashboard");

        //Enter Otp
        new AdminLogin(driver)
                .enterOTP()
                      .submitOTP();
        LogUtilities.info("The Admin Send OTP After Login");


        //Assign the current Admin Tab
        adminTabID = driver.getWindowHandle();
    }






    /*
        As:  an Admin
        I :  want to Approve all files Requests From the Company
        So:  I access Admin Dashboard and start to submit all files Requests
    */

    @Test(priority = 3, dependsOnMethods = {"adminLogin"})
    public void adminConfirmOrdersStep_1() throws InterruptedException {


        //Access Admin Dashboard and click on (شركات) then (طلبات التمويل)
        new AdminDashboard (driver)
                .clickCompanyButton()
                          .clickFundOrderButton();
        LogUtilities.info("The Admin Clicks on fundRequests from Admin Dashboard");


        //Click on the details of the last order the company created
       new OrderTableDetailsPage(driver)
                .clickOrderDetailsButton();
       LogUtilities.info("The Admin Clicks on the requestDetails button");


      //Access the order page and submit them then click on send button
       new AdminSubmitOrdersStep_1 (driver)
                .submitAllOrders()
                         .clickSendButton();
       LogUtilities.info("The Admin Submit the Requests of Step_1");


        assertThat(getSuccessMessageWebElement()
                                       .getText())
                                               .isEqualTo
                        ("تم الحفظ بنجاح");

       //Assign the current Admin TaB
        adminTabID = driver.getWindowHandle();

    }





    /*
        As:  a Company
        I :  want to continue Step 2 of the upload files
        So:  I access the company Dashboard and continue Step 2
    */



    @Test(priority = 4,dependsOnMethods = {"adminConfirmOrdersStep_1"})
    public void companyContinueUploadFilesStep_2() throws InterruptedException {

        //Switch to the company Tab
       driver.switchTo().window(companyTabID);

        //Refresh The Page
        driver.navigate().refresh();


        //Complete Step 2 of upload files
        new CompanyTableDetailsOfFundOrders(driver)
                .clickCompleteOrderButton();
        LogUtilities.info("The company clicks on the complete button to continue Step_2");



        //Upload files then submit them
         new CompanyUploadFilesStep_2(driver)
                .uploadFilesStep_2()
                             .clickUploadButton();
        LogUtilities.info("The company send files in the second step then click on the send button");


        //Assert if the uploading process done successfully
        assertThat(getSuccessMessageWebElement()
                                              .getText())
                .isEqualTo("تم استكمال الخطوة الثانية من طلب التمويل الخاص ب :user");


        //Assign the current Company Tab ID
        companyTabID = driver.getWindowHandle();
    }




    /*
    As:  an Admin
    I :  want to approve all Requests of Step 2 of the fund Requests
    So:  I access the Admin dashboard and start approving them
    */

    @Test(priority = 5,dependsOnMethods = {"companyContinueUploadFilesStep_2"})
    public void adminApproveRequestsStep_2() throws InterruptedException {


        //Switch To Admin Dashboard
        driver.switchTo().window(adminTabID);

       //Refresh the page
        driver.navigate().refresh();


        //Click on the details of the order (تفاصيل الطلب)
        new OrderTableDetailsPage(driver)
               .clickOrderDetailsButton();
        LogUtilities.info("The Admin clicks on requestDetails button");

        //Navigate to the order page and accept them then click on send button
        new AdminSubmitOrdersStep_2 (driver)
                .clickAcceptButton()
                        .clickSendButton();
        LogUtilities.info("The Admin Submit the Requests of Step_2");

        //Assert if the all orders have been approved
        assertThat(getSuccessMessageWebElement()
                                               .getText())
                                                         .isEqualTo
                        ("تم الحفظ بنجاح");
    }



    /*
       As:  a company
       I :  want to Click on confirm Button To Access Step_3 page
       So:  I access the Company dashboard and click on Confirm

     */


    @Test(priority = 6,dependsOnMethods = {"adminApproveRequestsStep_2"})
    public void companyConfirmThenMoveToStep_3() throws InterruptedException {



        //Switch to company Dashboard
        driver.switchTo().window(companyTabID);

        //Refresh the page
        driver.navigate().refresh();


        //Click on (Confirm) Button
        new OrderTableDetailsPage(driver)
                .clickConfirmOrderButton();
        LogUtilities.info("The company clicks on confirm button to move to Step_3");


        //Send button at the end of the page
        new AdminSubmitOrdersStep_2 (driver)
                .clickSendButton();
        LogUtilities.info("The company Clicks on send button to move to Step_3");


        //Assign the Company Tab
        companyTabID = driver.getWindowHandle();
    }



    /*
       As:  a company
       I :  want to continue Step_3
       So:  I access the Company dashboard and start the process

     */
    @Test(priority = 7,dependsOnMethods = {"companyConfirmThenMoveToStep_3"})
    public void companyContinueStep_3() throws InterruptedException, IOException {

        new CompanyUploadFilesStep_3(driver)
                .selectFundOrder(getPropertyValue("Data","fundOrderName"))
                       .enterCalendar(getPropertyValue("Data","calendar"))
                              .enterInvoiceAmount(getPropertyValue("Data","invoiceAmount"))
                                           .attachInvoice()
                                                   .selectInvoiceType("محفظة عمولات الدراسة")
                                                                .clickAddButton();

        LogUtilities.info("The Company Continue Step 3 Clicks on Add button");


        //Assert if the  process have been done successfully
        assertThat(getSuccessMessageWebElement()
                                              .getText())
                                                          .isEqualTo
                        ("تم الإضافة بنجاح");
    }

    /*
       As:  a company
       I :  want to pay the bill
       So:  I access the Company dashboard and start the process

     */

    @Test(priority = 8, dependsOnMethods = {"companyContinueStep_3"})
    public void companyPayTheFees() throws InterruptedException, IOException {


        //Click on Pay button
         new CompanyPayTheBill(driver)
                .clickFeesButton();

        //Assert if the payment process have been done successfully
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(new CompanyUploadFilesStep_2(driver)
                .getSuccessMessageWebElement()
                .getText(),"تم الإضافة بنجاح");

        softAssert.assertAll();

        //Enter Otp
        new AdminLogin(driver)
                .enterOTP()
                     .submitOTP();



        //Assert if the payment process have been done successfully
        assertThat(new CompanyUploadFilesStep_2(driver)
                .getSuccessMessageWebElement()
                .getText()).isEqualTo("لقد تم ارسال رمز التحقق علي رقمك الخاص");

    }



}
