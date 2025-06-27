package tests.ContactUs;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Locates;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.io.IOException;
import java.time.Duration;

import static utilities.ReusableMethods.extentReports;
import static utilities.ReusableMethods.extentTest;

public class TC32 {
    @Test
    public void test32() throws IOException {

        ReusableMethods reusableMethods = new ReusableMethods();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("İletişim", "İletişim Formu Görüntüleme", "İletişim formu sayfasının ve tüm alanlarının başarıyla yüklendiğini doğrulama", "Mert Yıldız");
        Locates locates = new Locates();

        try {
            // "https://www.automationexercise.com/contact_us" adresine gidin.
            Driver.getDriver().get(ConfigReader.getProperty("contactUsUrl"));

            String expectedContactUsTitle = ConfigReader.getProperty("contactUsTitle");
            String actualContactUsTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualContactUsTitle, expectedContactUsTitle);

            softAssert.assertTrue(locates.getInTouchText.isDisplayed());
            extentTest.info("Contact Us sayfasına başarılı şekilde gidildi");

            // İletişim formunun görüntülendiğini doğrulayın.
            wait.until(ExpectedConditions.visibilityOf(locates.getInTouchText));
            softAssert.assertTrue(locates.getInTouchText.isDisplayed(), "Get In Touch başlığı görüntülenemedi!");

            extentTest.info("İletişim formu sayfası başarıyla yüklendi");

            // Name, Email, Subject, Your Message Here ve Dosya Seç alanlarının görüntülendiğini doğrulayın.
            softAssert.assertTrue(locates.contactUsNameInputArea.isDisplayed(), "Name alanı görüntülenemedi!");
            softAssert.assertTrue(locates.contactUsNameInputArea.isEnabled(), "Name alanı aktif değil!");

            softAssert.assertTrue(locates.contactUsEmailArea.isDisplayed(), "Email alanı görüntülenemedi!");
            softAssert.assertTrue(locates.contactUsEmailArea.isEnabled(), "Email alanı aktif değil!");

            softAssert.assertTrue(locates.contactUsSubjectInput.isDisplayed(), "Subject alanı görüntülenemedi!");
            softAssert.assertTrue(locates.contactUsSubjectInput.isEnabled(), "Subject alanı aktif değil!");

            softAssert.assertTrue(locates.contactUsMessageInput.isDisplayed(), "Your Message Here alanı görüntülenemedi!");
            softAssert.assertTrue(locates.contactUsMessageInput.isEnabled(), "Your Message Here alanı aktif değil!");

            softAssert.assertTrue(locates.contactUsFileUpload.isDisplayed(), "Dosya Seç alanı görüntülenemedi!");
            softAssert.assertTrue(locates.contactUsFileUpload.isEnabled(), "Dosya Seç alanı aktif değil!");

            extentTest.info("Tüm iletişim formu alanları başarıyla görüntülendi ve aktif");

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