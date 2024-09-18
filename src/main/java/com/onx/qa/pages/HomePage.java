package com.onx.qa.pages;

import com.aventstack.extentreports.Status;
import com.onx.qa.base.ListenerTest;
import com.onx.qa.base.ListenerTest;
import com.onx.qa.base.ThreadLocalDriver;
import com.onx.qa.utility.WebElements;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends ListenerTest {

    WebElements webElements = new WebElements();;
    JavascriptExecutor executor = (JavascriptExecutor) ThreadLocalDriver.getDriver();
    WebDriver driver;

    public HomePage() {
        //driver = this.driver;
        //PageFactory.initElements(driver, this);
    }

    /*@FindBy(xpath="//input[@name='loginfmt']") WebElement EmailId;
    @FindBy(xpath="//input[@type='submit']") WebElement SubmitButton;
    @FindBy(xpath="//input[@name='passwd']") WebElement Password;
    @FindBy(xpath="//div[@id='usernameError']") WebElement ErrorMessage;
    @FindBy(xpath="//div[@role='heading']") WebElement AuthenticationHeading;
    @FindBy(xpath="//a[@id='cantAccessAccount']") WebElement CantAccessAccountLink;
    @FindBy(xpath="//div[@id='loginHeader']") List<WebElement> LoginHeader;
    @FindBy(xpath="//div[text()='Work or school account']") List<WebElement> WorkOrSchoolText;
    @FindBy(xpath="//div[text()='Personal account']") List<WebElement> PersonalAccountText;
    @FindBy(xpath="//a[@id='idA_PWD_ForgotPassword']") WebElement ForgotMyPassword;
    @FindBy(xpath="//input[@id='ContentPlaceholderMainContent_TextBoxUserIdentifier']") List<WebElement> ForgotMyPasswordEmailID;
    @FindBy(xpath="//a[@id='ContentPlaceholderMainContent_ButtonNext']") List<WebElement> ForgotMyPasswordNextButton;
    @FindBy(xpath="//a[@id='ContentPlaceholderMainContent_ButtonCancel']") List<WebElement> ForgotMyPasswordCancelButton;
    @FindBy(xpath="//button[@class='backButton']") WebElement EmailIDBackButton;
    @FindBy(xpath="//div[text()='Sign in']") List<WebElement> SignInHeader;*/

    private String SearchBox = "//input[@id='twotabsearchtextbox']";
    private String SearchButton = "//input[@id='nav-search-submit-button']";
    private String NoResults = "//span[text()='No results for ']";
    private String Refinement = "//div[@id='s-refinements']";
    private String MenuButton = "//a[@id='nav-hamburger-menu']";

    private String HeaderSignIn = "//a[@id='nav-link-accountList']";
    private String Email = "//input[@name='email']" ;
    private String SigninContinueButton = "//span[@id='continue']";
    private String PasswordField = "//input[@id='ap_password']" ;
    private String SignInSubmit = "//span[@id='auth-signin-button']";

    private String NameValidationAfterSignIn = "//span[contains(text(),'devi')]";

    private String BestSellers = "//a[text()='Best Sellers']";
    private String CollectionProducts = "//li[@class='a-carousel-card']";
    private String AddToCartButton = "//button[@title='Add to Cart']";
    private String GoToCartButton = "(//a[contains(text(),'Go to Cart')])[2]";
    private String AddedToCartText = "//h1[contains(text(),'Added to Cart')]";
    private String BuyNowButton = "//input[@id='buy-now-button']";
    private String SigninTextAfterClickingBuyNowButton = "//h1[contains(text(),' Sign in or create account')]";
    private String CheckoutText = "//h1[contains(text(),'Checkout')]";
    private String YourOrders = "//h2[contains(text(),'Your Orders')]/parent::div";
    private String YourOrdersText = "//div[contains(text(),'Looks like you haven')]";
    private String ProceedToBuy = "//input[@name='proceedToRetailCheckout']";
    private String AddNewAddress = "//a[@id='add-new-address-popover-link']";
    private String Fname = "//input[@name='address-ui-widgets-enterAddressFullName']";
    private String PhoneNumber = "//input[@name='address-ui-widgets-enterAddressPhoneNumber']";
    private String PostalCode = "//input[@name='address-ui-widgets-enterAddressPostalCode']";
    private String AddressLine1 = "//input[@name='address-ui-widgets-enterAddressLine1']";
    private String City = "//input[@name='address-ui-widgets-enterAddressCity']";
    private String UseThisAddress = "(//input[@class='a-button-input'])[2]";
    private String CashOnDelivery = "(//input[@name='ppw-instrumentRowSelection'])[5]";
    private String UseThisPayment = "//input[@name='ppw-widgetEvent:SetPaymentPlanSelectContinueEvent']";
    private String PlaceYourOrder = "//input[@name='placeYourOrder1']";
    private String Logo = "//div[@id='nav-logo']";


    public void clickProceedToBuy() throws InterruptedException {
        webElements.clickElement(ProceedToBuy);
        Thread.sleep(1000);
        test.log(LogStatus.INFO, "Clicked on Procced To Buy button");
        /*executor.executeScript("arguments[0].click();", AddNewAddress);
        Fname.sendKeys("Devi R");
        PhoneNumber.sendKeys("9962905981");
        PostalCode.sendKeys("600099");
        AddressLine1.sendKeys("No 143, 4th Part, 5th Street, Puzhal");
        City.sendKeys("Chennai");*/
        test.log(LogStatus.INFO, "Entered all the details");
        webElements.clickElement(UseThisAddress); Thread.sleep(3000);
        webElements.clickElement(CashOnDelivery); Thread.sleep(3000);
        webElements.clickElement(UseThisPayment); Thread.sleep(3000);
        //webElements.clickElement(PlaceYourOrder); Thread.sleep(3000);
    }

    public void searchProduct(Boolean action, String keyword) throws InterruptedException {
        webElements.type(SearchBox,keyword);
        webElements.clickElement(SearchButton);
        if(action){
            webElements.elementPresent(Refinement);
        }
        if(action.equals(false)){
            webElements.waitForElementToClick(NoResults);
        }
        webElements.clear(SearchBox); Thread.sleep(1000);
        webElements.clickElement(Logo); Thread.sleep(1000);
    }

    public void clickHeaderSignIn() {
        webElements.clickElement(HeaderSignIn);
        test.addScreenCapture(getScreenShot());
    }

    public void amazonSignIn(String UserName, String Password){
        webElements.type(Email, UserName);
        webElements.clickElement(SigninContinueButton);
        webElements.type(PasswordField, Password);
        webElements.clickElement(SignInSubmit);
    }

    public void validationAfterSignIn(){
        if(webElements.waitForElementToClick(NameValidationAfterSignIn)){
            test.log(LogStatus.PASS, "User Name displayed after successful signin");
            test.addScreenCapture(getScreenShot());
        }
        else{
            test.log(LogStatus.FAIL, "User Name is not displayed after successful signin");
            test.addScreenCapture(getScreenShot());
        }
    }

    public void validationOfCheckoutPageAfterSignIn(){
        if(webElements.waitForElementToClick(CheckoutText)){
            test.log(LogStatus.PASS, "Checkout Page displayed after successful signin");
            test.addScreenCapture(getScreenShot());
        }
        else{
            test.log(LogStatus.FAIL, "Checkout Page is not displayed after successful signin");
            test.addScreenCapture(getScreenShot());
        }
    }

    public void clickOnBestSellersCollection(){
        webElements.jsClickElement(BestSellers);
    }

    public void selectProductAndClickOnAddToCart(){
        webElements.clickElement(CollectionProducts);
        webElements.clickElement(AddToCartButton);
        webElements.waitForElementToClick(GoToCartButton);
        webElements.clickElement(GoToCartButton);
    }

    public void selectProductAndClickOnBuyNow(){
        webElements.clickElement(CollectionProducts);
        webElements.clickElement(BuyNowButton);
        webElements.waitForElementToClick(SigninTextAfterClickingBuyNowButton);
    }

    public void clickYourOrderAndValidate0Orders(){
        webElements.jsClickElement(YourOrders);
        webElements.elementPresent(YourOrdersText);
    }

    public void clickOnYourOrders(){
        webElements.jsClickElement(YourOrders);
    }


/*
    */
/**
     * email Field Validation
     * @param value
     *//*

    public String invalidEmailFieldValidation(String value) throws InterruptedException {
        Thread.sleep(3000);
        webElements.waitForElementToClick(EmailId);
        Thread.sleep(1000);
        EmailId.sendKeys(value);
        Thread.sleep(1000);
        SubmitButton.click();
        Thread.sleep(1000);
        return ErrorMessage.getText();
    }

    */
/**
     * valid Email Field Validation
     * @param value
     * @return
     *//*

    public boolean validEmailFieldValidation(String value) {
        try{
            Thread.sleep(3000);
            webElements.waitForElementToClick(EmailId);
            EmailId.sendKeys(value);
            SubmitButton.click();
            return webElements.waitForElementToClick(Password);
        }
        catch(Exception e){
            return webElements.waitForElementToClick(Password);
        }
    }

    */
/**
     * invalid Password Field Validation
     * @param value
     * @return
     *//*

    public String invalidPasswordFieldValidation(String value) {
        try{
            Thread.sleep(3000);
            webElements.waitForElementToClick(Password);
            Password.sendKeys(value);
            SubmitButton.click();
        }
        catch(Exception e){
            return "";
        }
        return ErrorMessage.getText();
    }

    */
/**
     * valid Email Password Field Validation
     * @param UserName
     * @param Password
     * @return
     *//*

    public boolean validEmailPasswordFieldValidation(String UserName, String Password) {
        try{
            Thread.sleep(3000);
            webElements.waitForElementToClick(EmailId);
            EmailId.sendKeys(UserName);
            SubmitButton.click();
            EmailId.sendKeys(Password);
            SubmitButton.click();
            return webElements.waitForElementToClick(AuthenticationHeading);
        }
        catch(Exception e){
            return webElements.waitForElementToClick(AuthenticationHeading);
        }
    }

    */
/**
     * click Access Account Link
     *//*

    public void clickAccessAccountLink(){
        CantAccessAccountLink.click();
    }

    */
/**
     * cant Access Account Link
     *//*

    public void cantAccessAccountLink(){

        if(webElements.elementPresent(LoginHeader)){
            System.out.println("Login Header is Present");
        }
        else {
            test.addScreenCapture(getScreenShot());
            test.log(LogStatus.FAIL, "Login Header not Present");
        }

        if(webElements.elementPresent(WorkOrSchoolText)){
            System.out.println("Work Or School text is Present");
        }
        else {
            test.addScreenCapture(getScreenShot());
            test.log(LogStatus.FAIL, "Work Or School text not Present");
        }

        if(webElements.elementPresent(PersonalAccountText)){
            System.out.println("Personal Account Text is Present");
        }
        else {
            test.addScreenCapture(getScreenShot());
            test.log(LogStatus.FAIL, "Personal Account Text not Present");
        }
    }

    */
/**
     *set EmailId
     * @param userName
     *//*

    public void setEmailId(String userName) throws InterruptedException {
        webElements.waitForElementToClick(EmailId);
        EmailId.sendKeys(userName);
        SubmitButton.click();
        webElements.waitForElementToClick(SubmitButton);
    }

    */
/**
     * click Forgot My Password Link
     *//*

    public void clickForgotMyPasswordLink(){
        ForgotMyPassword.click();
        webElements.waitForElementToClick(ForgotMyPasswordEmailID.get(0));
    }

    */
/**
     * forgot My Password Page Button Validation
     *//*

    public void forgotMyPasswordPageButtonValidation(){
        if(webElements.elementPresent(ForgotMyPasswordEmailID)){
            System.out.println("Forgot My Password EmailID is Present");
        }
        else {
            test.addScreenCapture(getScreenShot());
            test.log(LogStatus.FAIL, "Forgot My Password EmailID not Present");
        }

        if(webElements.elementPresent(ForgotMyPasswordNextButton)){
            System.out.println("Forgot My Password Next button is Present");
        }
        else {
            test.addScreenCapture(getScreenShot());
            test.log(LogStatus.FAIL, "Forgot My Password Next button not Present");
        }

        if(webElements.elementPresent(ForgotMyPasswordCancelButton)){
            System.out.println("Forgot My Password Cancel button is Present");
        }
        else {
            test.addScreenCapture(getScreenShot());
            test.log(LogStatus.FAIL, "Forgot My Password Cancel button not Present");
        }
    }

    */
/**
     * click Email Back Button
     *//*

    public void clickEmailBackButton(){
        EmailIDBackButton.click();
    }

    */
/**
     * sign In Header Valdiation
     *//*

    public void signInHeaderValdiation(){;
        webElements.waitForElementToClick(CantAccessAccountLink);
        if(webElements.elementPresent(SignInHeader)){
            System.out.println("SignIn Header is Present");
        }
        else {
            test.addScreenCapture(getScreenShot());
            test.log(LogStatus.FAIL, "SignIn Header not Present");
        }
    }

    public void validateWeDidtHearFromYouAlert(){

    }
*/

}
