package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class ReusableMethods {

    protected static Select select;
    protected static Actions actions;
    protected static WebDriverWait wait;
    public static ExtentReports extentReports;
    public static ExtentHtmlReporter extentHtmlReporter;
    public static ExtentTest extentTest;
    public static JavascriptExecutor js;
    public static TakesScreenshot ts;

    //Hard Wait
    public static void bekle(int saniye) {
        try {
            Thread.sleep(1000 * saniye);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //WebDriverWait Visiblity
    public static void visibilityWait(WebElement element, int saniye) {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(saniye));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    //ExtentReport
    public static void report(String raporIsmi, String testAdi, String testAciklamasi, String qa) {
        String tarih = new SimpleDateFormat("_HH_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = System.getProperty("user.dir") + "/src/test/java/resources/reports/" + tarih + ".html";
        extentReports = new ExtentReports();
        extentHtmlReporter = new ExtentHtmlReporter(dosyaYolu);
        extentReports.attachReporter(extentHtmlReporter);
        //Raporda gözükmesini istediğimiz bilgiler için;
        extentReports.setSystemInfo("QA", qa);
        extentReports.setSystemInfo("Browser", "Chrome");
        extentHtmlReporter.config().setReportName(raporIsmi);
        extentHtmlReporter.config().setDocumentTitle("ExtentReport");
        extentTest = extentReports.createTest(testAdi, testAciklamasi);
    }

    //JsExecutor Click
    public void jsClick(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            js = (JavascriptExecutor) Driver.getDriver();
            js.executeScript("arguments[0].click();", element);
        }
    }

    //JsExecutor ScrollElement
    public void jsScrollElement(WebElement element) {
        js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    //JsExecutor ScrollEnd
    public void jsScrollEnd() {
        js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    // Input value'yu JS ile oku
    public static String jsGetInputValue(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        return (String) js.executeScript("return arguments[0].value;", element);
    }

    //WebElement ScreenShot for extentReport
    public static String raporScreenShot() {
        ts = (TakesScreenshot) Driver.getDriver();
        return ts.getScreenshotAs(OutputType.BASE64);
    }
}
