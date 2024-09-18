package com.onx.qa.base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ListenerTest implements ITestListener {
    public static WebDriver driver;
    public static Properties prop;
    public static ExtentSparkReporter sparkReporter;
    public static ExtentReports extentReporter;
    public static ExtentTest test;

    public ListenerTest(){
        try {
            prop = new Properties();
            String filepath = System.getProperty("user.dir");
            FileInputStream inputstm = new FileInputStream("/Users/devi.raghu/IdeaProjects/CBTSProject/src/main/java/com/onx/qa/config/qa.properties");
            prop.load(inputstm);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart(ITestContext context){
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\src\\test\\reports\\MyReport.html");
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Regression Testing");
        sparkReporter.config().setTheme(Theme.DARK);
        extentReporter = new ExtentReports(".\\src\\test\\reports\\CBTS_Automation_Report.html");
        //extentReporter.attachReporter(sparkReporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
        String browserName = prop.getProperty("browser");

        if(browserName.equals("chrome")){
            driver = WebDriverManager.chromedriver().create();
        }
        else if(browserName.equals("firefox")){
            driver = WebDriverManager.firefoxdriver().create();
        }
        else if(browserName.equals("edge")){
            driver = WebDriverManager.edgedriver().create();
        }
        else if(browserName.equals("internetExplorer")){
            driver = WebDriverManager.iedriver().create();
        }
        else{
            driver = WebDriverManager.safaridriver().create();
        }
        ThreadLocalDriver.setDriver(driver);
        //ExtentTest extentTest = this.extentReporter.startTest(result.getMethod().getMethodName());
        //ThreadLocalExtent.setExtent(extentTest);

        ThreadLocalDriver.getDriver().manage().window().maximize();
        ThreadLocalDriver.getDriver().manage().deleteAllCookies();
        ThreadLocalDriver.getDriver().manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ThreadLocalDriver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        test = extentReporter.startTest(result.getName());
        test.log(LogStatus.INFO,"Test Started");
        ThreadLocalDriver.getDriver().get(prop.getProperty("url"));
        test.log(LogStatus.INFO,"URL Is Loading");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ThreadLocalDriver.getDriver().navigate().refresh();
    }

    @Override
    public void onTestSuccess(ITestResult result){
        //test = extentReporter.createTest(result.getName());
        //reportMethods.reportPass(result.getName());
        test.log(LogStatus.PASS, "Test case Passed is "+ result.getName());

        ThreadLocalExtent.endReport();
        ThreadLocalDriver.getDriver().close();
    }

    @Override
    public void onTestFailure(ITestResult result){
        //test = extentReporter.createTest(result.getName());
        //reportMethods.reportFail(result.getName());
        test.log(LogStatus.FAIL, "Test case Passed is "+ result.getName());

        ThreadLocalExtent.endReport();
        ThreadLocalDriver.getDriver().close();
    }

    @Override
    public void onTestSkipped(ITestResult result){
        //test = extentReporter.createTest(result.getName());
        test.log(LogStatus.SKIP, "Test case Skipped is "+ result.getName());

        ThreadLocalExtent.endReport();
        ThreadLocalDriver.getDriver().close();
    }

    @Override
    public void onFinish(ITestContext context){
        this.extentReporter.flush();
    }

    public static String getScreenShot() {
        try {
            String var10000 = timeStamp();
            String filePath = var10000 + random(1, 999) + ".png";
            String returnPath = "./screenshot/" + filePath;
            String screenShotFileName = ".\\src\\test\\reports\\Screenshot\\" + filePath;
            TakesScreenshot scrShot = (TakesScreenshot) ThreadLocalDriver.getDriver();
            File SrcFile = (File)scrShot.getScreenshotAs(OutputType.FILE);
            File desti = new File(screenShotFileName);
            FileUtils.copyFile(SrcFile, desti);
            return returnPath;
        } catch (Exception e) {
            return null;
        }
    }

    public static int random(int min, int max) {
        //min = min-1;
        max = max + 1;
        Random ran = new Random();
        int x = ran.nextInt(max - min) + min;
        return x;
    }

    public static String timeStamp() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Timestamp today;
        today = new Timestamp(date.getTime());
        String time = sdf.format(today);
        return time;
    }


}
