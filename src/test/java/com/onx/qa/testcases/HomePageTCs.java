package com.onx.qa.testcases;
import com.onx.qa.base.ListenerTest;
import com.onx.qa.pages.HomePage;
import com.onx.qa.utility.WebElements;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import com.onx.qa.base.ThreadLocalDriver;

public class HomePageTCs extends ListenerTest {

    HomePage homepage = new HomePage();;
    WebElements webelements = new WebElements();

    @Test(testName = "TC001 - Amazon_End To End Scenario")
    public void TC001_Amazon_EndToEndScenario() throws InterruptedException {
        Thread.sleep(3000);
        homepage.clickHeaderSignIn();

        //Signin
        homepage.amazonSignIn(prop.getProperty("userName"), prop.getProperty("password"));
        homepage.validationAfterSignIn();

        //Search Functionality Validation
        homepage.searchProduct(false, "hggggggggggggggggggggggggggggj");
        homepage.searchProduct(true, "flowers");

        //Add Product from Collection and Checkout
        Thread.sleep(1000);
        homepage.clickOnBestSellersCollection();
        homepage.selectProductAndClickOnAddToCart();
        homepage.clickProceedToBuy();

        driver.get("https://www.amazon.in");
        homepage.clickHeaderSignIn();
        /*homepage.clickOnYourOrders();
        test.log(LogStatus.PASS, "We are able to see the place order in Your Orders Page");*/
    }

    //@Test(testName = "TC002 - Search Invalid Keyword")
    public void TC002_SearchInvalidKeyword() throws InterruptedException {
        Thread.sleep(3000);
        homepage.searchProduct(false, "hggggggggggggggggggggggggggggj");
    }

    //@Test(testName = "TC003 - Successful Sign In")
    public void TC003_SuccessfulSignIn(){
        homepage.clickHeaderSignIn();
        homepage.amazonSignIn(prop.getProperty("userName"), prop.getProperty("password"));
        homepage.validationAfterSignIn();
    }

    //@Test(testName = "TC004 - VerifyAddToCartFunctionality")
    public void TC004_VerifyAddToCartFunctionality(){
        homepage.clickOnBestSellersCollection();
        homepage.selectProductAndClickOnAddToCart();
    }

    //@Test(testName = "TC005 - VerifyBuyNowFunctionality")
    public void TC005_VerifyBuyNowFunctionality(){
        homepage.clickOnBestSellersCollection();
        homepage.selectProductAndClickOnBuyNow();
        homepage.amazonSignIn(prop.getProperty("userName"), prop.getProperty("password"));
        homepage.validationOfCheckoutPageAfterSignIn();
    }

    //@Test(testName = "TC006 - TrackOrderValidation")
    public void TC006_TrackOrderValidation(){
        homepage.clickHeaderSignIn();
        homepage.amazonSignIn(prop.getProperty("userName"), prop.getProperty("password"));
        homepage.clickHeaderSignIn();
        homepage.clickYourOrderAndValidate0Orders();

    }

    @AfterMethod()
    public void tearDown() {
        driver.quit();
    }
}
