package com.onx.qa.utility;

import com.onx.qa.base.ListenerTest;
import com.onx.qa.base.ThreadLocalDriver;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class WebElements extends ListenerTest {


    /**
     * click Element
     * @param xpath
     * @throws InterruptedException
     */
    public boolean clickElement(String xpath) {
        try{
            WebElement element = ThreadLocalDriver.getDriver().findElement(By.xpath(xpath));
            element.click();
            test.log(LogStatus.PASS, "Click action is performed for "+element.toString());
            return true;
        }
        catch(Exception e){
            test.log(LogStatus.FAIL, "Click action is not performed due to "+e.getMessage()+test.addScreenCapture(getScreenShot()));
            return false;
        }
    }

    /**
     * java script Click Element
     * @param xpath
     */
    public void jsClickElement(String xpath) {
        try{
            JavascriptExecutor executor = (JavascriptExecutor) ThreadLocalDriver.getDriver();
            WebElement element = ThreadLocalDriver.getDriver().findElement(By.xpath(xpath));
            executor.executeScript("arguments[0].click();", element);
        }
        catch(Exception e){
            test.log(LogStatus.FAIL, "Click action is not performed due to "+e.getMessage()+test.addScreenCapture(getScreenShot()));
        }
    }

    /**
     * wait for Element To Click
     * @param xpath
     */
    public boolean waitForElementToClick(String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            WebElement element = ThreadLocalDriver.getDriver().findElement(By.xpath(xpath));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            test.log(LogStatus.PASS, "Element is available to click");
            return true;
        }
        catch(Exception e){
            test.log(LogStatus.FAIL, "Click action is not performed due to "+e.getMessage()+test.addScreenCapture(getScreenShot()));
            return false;
        }
    }

    /**
     * element Present
     * @param xpath
     * @return
     */
    public boolean elementPresent(String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            List<WebElement> element = ThreadLocalDriver.getDriver().findElements(By.xpath(xpath));
            if(element.size()!=0){
                test.log(LogStatus.PASS, "Element is present "+xpath.toString());
                return true;
            }
            else{
                test.log(LogStatus.FAIL, "Element is not present"+test.addScreenCapture(getScreenShot()));
                return false;
            }
        }
        catch(Exception e){
            return false;
        }
    }

    /**
     *
     * @param xpath
     * @param value
     * @return
     */
    public boolean type(String xpath, String value){
        WebElement element = ThreadLocalDriver.getDriver().findElement(By.xpath(xpath));
        try{
            element.sendKeys(value);
            test.log(LogStatus.PASS, "Value typed "+xpath.toString());
            return true;
        }
        catch(Exception e){
            test.log(LogStatus.FAIL, "Element is not able to perform type action due to"+e.getMessage()+test.addScreenCapture(getScreenShot()));
            return false;
        }
    }

    /**
     *
     * @param xpath
     * @return
     */
    public boolean clear(String xpath){
        WebElement element = ThreadLocalDriver.getDriver().findElement(By.xpath(xpath));
        try{
            element.clear();
            test.log(LogStatus.PASS, "Value cleared "+xpath.toString());
            return true;
        }
        catch(Exception e){
            test.log(LogStatus.FAIL, "Value has not been cleared due to"+e.getMessage()+test.addScreenCapture(getScreenShot()));
            return false;
        }

    }


}
