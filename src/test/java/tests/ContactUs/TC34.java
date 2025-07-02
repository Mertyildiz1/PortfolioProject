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

public class TC34 {
    @Test(retryAnalyzer = utilities.RetryAnalyzer.class)
    public void test34() throws IOException {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("Contact Us", "Geçersiz Email ile Form Gönderme", "Geçersiz email ile form gönderildiğinde hata mesajı görüntülenmeli", "Mert Yıldız");
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
            extentTest.info("Name alanı başarışı şekilde dolduruldu.");

            // "Email" alanına geçersiz bir e-posta adresi girin.
            locates.contactUsEmailArea.clear();
            locates.contactUsEmailArea.sendKeys(ConfigReader.getProperty("invalidMail"));
            ReusableMethods.bekle(1);
            extentTest.info("Geçersiz email alanı dolduruldu.");

            // "Subject" alanına bir konu girin.
            locates.contactUsSubjectInput.clear();
            locates.contactUsSubjectInput.sendKeys(ConfigReader.getProperty("contactUsSendSubject"));
            ReusableMethods.bekle(1);

            softAssert.assertEquals(ReusableMethods.jsGetInputValue(locates.contactUsSubjectInput), ConfigReader.getProperty("contactUsSendSubject"), "Subject alanı doğru doldurulamadı!");
            extentTest.info("Subject alanı başarılı şekilde dolduruldu.");

            // "Your Message Here" alanına bir mesaj yazın.
            locates.contactUsMessageInput.clear();
            locates.contactUsMessageInput.sendKeys(ConfigReader.getProperty("contactUsSendMessage"));
            ReusableMethods.bekle(1);

            softAssert.assertEquals(ReusableMethods.jsGetInputValue(locates.contactUsMessageInput), ConfigReader.getProperty("contactUsSendMessage"), "Mesaj alanı doğru doldurulamadı!");
            extentTest.info("Mesaj alanı dolduruldu.");

            // "Submit" butonuna tıklayın.
            locates.contactUsSubmitButton.click();
            extentTest.info("Submit butonuna tıklandı.");

            // Hata mesajını doğrulayın.
            String emailValidationMessage = locates.contactUsEmailArea.getAttribute("validationMessage");
            softAssert.assertTrue(emailValidationMessage != null && !emailValidationMessage.isEmpty(), "Email için hata mesajı görüntülenmedi!");
            extentTest.info("Geçersiz email için hata mesajı başarıyla görüntülendi: " + emailValidationMessage);

            // Title'ın değişmediğini doğrulayın.
            String actualTitleAfterSubmit = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualTitleAfterSubmit, expectedContactUsTitle, "Form Gönderildi!");

            extentTest.info("Title'ın değişmediği doğrulandı");

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
