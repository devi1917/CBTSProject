package com.onx.qa.base;

import org.openqa.selenium.WebDriver;

public class ThreadLocalDriver {

    private static ThreadLocal<WebDriver> threadDriver =  new ThreadLocal<>();

    public ThreadLocalDriver(WebDriver driver){
        driver = (WebDriver) threadDriver;
    }

    public synchronized static void setDriver(WebDriver driver){
        threadDriver.set(driver);
    }

    public synchronized static WebDriver getDriver(){
        return threadDriver.get();
    }
}
