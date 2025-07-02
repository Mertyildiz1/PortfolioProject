package tests.ContactUs;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Locates;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.awt.*;
import java.io.IOException;
import java.time.Duration;

import static utilities.ReusableMethods.extentReports;
import static utilities.ReusableMethods.extentTest;

public class TC40 {
    @Test()
    public void test40() throws IOException {

        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        ReusableMethods reusableMethods = new ReusableMethods();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("Contact Us", "İletişim Formu", "Dosya seç butonunun doğru şekilde çalıştığını ve dosya yüklenebildiğini doğrulama", "Mert Yıldız");
        Locates locates = new Locates();

        try {
            // "https://www.automationexercise.com/contact_us" adresine gidin.
            Driver.getDriver().get(ConfigReader.getProperty("contactUsUrl"));

            String expectedContactUsTitle = ConfigReader.getProperty("contactUsTitle");
            String actualContactUsTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualContactUsTitle, expectedContactUsTitle);

            softAssert.assertTrue(locates.getInTouchText.isDisplayed());
            extentTest.info("Contact Us sayfasına başarılı şekilde gidildi");

            // Name alanına bir isim girin.
            wait.until(ExpectedConditions.visibilityOf(locates.contactUsNameInputArea));
            locates.contactUsNameInputArea.clear();
            locates.contactUsNameInputArea.sendKeys(ConfigReader.getProperty("name"));

            ReusableMethods.bekle(1);
            softAssert.assertEquals(ReusableMethods.jsGetInputValue(locates.contactUsNameInputArea), ConfigReader.getProperty("name"), "Name alanı doğru doldurulamadı!");

            extentTest.info("Name alanı başarıyla dolduruldu ve doğrulandı.");

            // "Email" alanına geçerli bir e-posta adresi girin.
            locates.contactUsEmailArea.clear();
            locates.contactUsEmailArea.sendKeys(ConfigReader.getProperty("mail"));

            ReusableMethods.bekle(1);
            softAssert.assertEquals(ReusableMethods.jsGetInputValue(locates.contactUsEmailArea), ConfigReader.getProperty("mail"), "Email alanı doğru doldurulamadı!");

            extentTest.info("Email alanı başarıyla dolduruldu ve doğrulandı.");

            // "Subject" alanına bir konu girin.
            locates.contactUsSubjectInput.clear();
            locates.contactUsSubjectInput.sendKeys(ConfigReader.getProperty("contactUsSendSubject"));

            ReusableMethods.bekle(1);
            softAssert.assertEquals(ReusableMethods.jsGetInputValue(locates.contactUsSubjectInput), ConfigReader.getProperty("contactUsSendSubject"), "Subject alanı doğru doldurulamadı!");

            extentTest.info("Subject alanı başarıyla dolduruldu ve doğrulandı.");

            // "Your Message Here" alanına bir mesaj yazın.
            locates.contactUsMessageInput.clear();
            locates.contactUsMessageInput.sendKeys(ConfigReader.getProperty("contactUsSendMessage"));

            ReusableMethods.bekle(1);
            softAssert.assertEquals(ReusableMethods.jsGetInputValue(locates.contactUsMessageInput), ConfigReader.getProperty("contactUsSendMessage"), "Mesaj alanı doğru doldurulamadı!");

            extentTest.info("Mesaj alanı başarıyla dolduruldu ve doğrulandı.");

            // "Dosya seç" butonuna tıklayın ve bir dosya yükleyin.
            String difPath = System.getProperty("user.home");
            String commonPath = "\\Downloads\\test.txt";
            String filePath = difPath + commonPath;

            locates.contactUsFileUpload.sendKeys(filePath);

            extentTest.info("Dosya forma eklendi");

            // Dosyanın eklendiğini doğrulayın
            String fileValue = (String) js.executeScript("return arguments[0].value;", locates.contactUsFileUpload);

            softAssert.assertTrue(fileValue.contains("test.txt"), "Dosya yüklenemedi!");

            extentTest.info("Dosyanın forma eklendiği başarılı şekilde doğrulandı");


            softAssert.assertAll();
            extentTest.pass("Test başarılı bir şekilde sonuçlandı");
        } catch (AssertionError | Exception e) {
            String screenshot = ReusableMethods.raporScreenShot();
            extentTest.fail("Test başarısız sonuçlandı!", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
            throw e;
        } finally {
            extentReports.flush();
        }
    }
}
