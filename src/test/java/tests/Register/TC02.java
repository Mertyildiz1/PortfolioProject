package tests.Register;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.github.javafaker.Faker;
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

public class TC02 {
    @Test
    public void test02() throws IOException {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        Faker faker = new Faker();
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("Kayıt", "Başarısız kayıt", "Geçersiz e-posta adresi ile kayıt işleminin başarısız olmasını doğrulama", "Mert Yıldız");
        Locates locates = new Locates();

        try {
            // "https://www.automationexercise.com/login" adresine gidin.
            Driver.getDriver().get(ConfigReader.getProperty("loginPageUrl"));

            String actualTitle = Driver.getDriver().getTitle();
            String expectedTitle = ConfigReader.getProperty("loginPageTitle");
            softAssert.assertEquals(actualTitle, expectedTitle);

            extentTest.info("Sayfaya başarılı bir şekilde gidildi");

            String initialTitle = Driver.getDriver().getTitle();

            // Name alanına geçerli bir isim girin.
            locates.SignUpPageNameArea.sendKeys(ConfigReader.getProperty("name"));

            // "Email Address" alanına geçersiz formatta email adresi girin.
            locates.SignUpPageEmailArea.sendKeys(ConfigReader.getProperty("invalidMail"));
            extentTest.info("Geçersiz formatta Email Address girildi :" + ConfigReader.getProperty("invalidMail"));


            // "Sign Up" butonuna tıklayın.
            locates.SignUpPageSignUpButton.click();

            // Çıkan hata mesajını doğrulayın.
            ReusableMethods.bekle(1);
            String currentTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(currentTitle, initialTitle);

            extentTest.info("Geçersiz mail için hata mesajı doğrulandı - Title değişmedi");

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
