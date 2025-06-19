package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    //Pencere Geçiş
    public static void window(int index) {
        Driver.getDriver().switchTo().window(Driver.getDriver().getWindowHandles().toArray()[index].toString());
    }


    //Alert Accept
    public static void alertOK() {
        Driver.getDriver().switchTo().alert().accept();
    }

    //WebDriverWait Visiblity
    public static void visibilityWait(WebElement element, int saniye) {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(saniye));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    //WebDriverWait InVisiblity
    public static void InvisibilityWait(WebElement element, int saniye) {
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(saniye));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    //WebDriverWait VisiblityLocate
    public void visibilityOfElementLocated(By locate, int saniye) {
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(saniye))
                .until(ExpectedConditions.visibilityOfElementLocated(locate));
    }

    //Download Wait
    public void downloadWait(String dosyaYolu) {
        for (int i = 0; i < 100; i++) {
            if (Files.exists(Paths.get(dosyaYolu))) {
                break;
            }
            bekle(1);
        }
    }

    //File Delete
    public void dosyaSil(String dosyaYolu) {
        try {
            Files.delete(Paths.get(dosyaYolu));
        } catch (IOException e) {
            System.out.println("Dosya Bulunamadi");
        }
    }

    //Upload File
    public void upload(String dosyaYolu) {
        try {
            StringSelection stringSelection = new StringSelection(dosyaYolu);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null); //Panoya kopyaladı --> ctrl+c
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            bekle(1);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
            bekle(1);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    //ScreenShot
    public static void screenShot() {
        String tarih = new SimpleDateFormat("_HH_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = System.getProperty("user.dir") + "/src/test/java/resources/screenShot/" + tarih + ".png";
        ts = (TakesScreenshot) Driver.getDriver();
        bekle(1);
        try {
            Files.write(Paths.get(dosyaYolu),ts.getScreenshotAs(OutputType.BYTES));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //WebElement ScreenShot
    public void webElementScreenShot(WebElement element) {
        String tarih = new SimpleDateFormat("_HH_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = System.getProperty("user.dir") + "/src/test/java/resources/webElementScreenShot/" + tarih + ".png";
        bekle(1);
        try {
            Files.write(Paths.get(dosyaYolu), element.getScreenshotAs(OutputType.BYTES));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //WebElement ScreenShot for extentReport
    public static String raporScreenShot() {
        ts = (TakesScreenshot) Driver.getDriver();
        return ts.getScreenshotAs(OutputType.BASE64);
    }

    //WebElement ScreenShot for extentReport
    public String raporWEScreenShot(WebElement element) {
        return element.getScreenshotAs(OutputType.BASE64);
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

    //JsExecutor Home
    public void jsScrollHome() {
        js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollTo(0,-document.body.scrollHeight)");
    }
    //JsExecutor SendKeys
    public void jsSendKeys(String text,WebElement element) {
        js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].value='"+text+"'",element);
    }
}
