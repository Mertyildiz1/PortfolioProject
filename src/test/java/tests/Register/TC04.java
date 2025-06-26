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

public class TC04 {
    @Test
    public void test04() throws IOException {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        Faker faker = new Faker();
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("Kayıt", "Duplicate Email", "Kayıtlı olan bir mail adresi ile tekrar kayıt işleminin başarısız olduğunu ve hata mesajının görüntülendiğini doğrulama", "Mert Yıldız");
        Locates locates = new Locates();

        try {
            // "https://www.automationexercise.com/login" adresine gidin.
            Driver.getDriver().get(ConfigReader.getProperty("loginPageUrl"));
            extentTest.info("Sayfaya başarılı bir şekilde gidildi");

            // Name alanına geçerli bir isim girin.
            locates.SignUpPageNameArea.sendKeys(ConfigReader.getProperty("name"));
            extentTest.info("Name alanına geçerli isim girildi :"+ConfigReader.getProperty("name"));

            // "Email Address" alanına daha önce kayıt olmuş bir email adresi girin.
            locates.SignUpPageEmailArea.sendKeys(ConfigReader.getProperty("myMail"));
            extentTest.info("Daha önce kayıt olunmuş mail adresi 'Email Address' alanına girildi");

            // "Sign Up" butonuna tıklayın.
            locates.SignUpPageSignUpButton.click();
            extentTest.info("Sign Up butonuna tıklandı");

            // Çıkan hata mesajını doğrulayın.
            ReusableMethods.visibilityWait(locates.alreadyExistText,15);
            softAssert.assertTrue(locates.alreadyExistText.isDisplayed());
            extentTest.info("Hata mesajı doğrulandı");

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
