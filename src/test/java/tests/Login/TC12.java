package tests.Login;

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

public class TC12 {
    @Test
    public void test12() throws IOException {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        Faker faker = new Faker();
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("Giriş", "Başarısız giriş", "Email ve Password alanları boş bırakıldığında giriş işleminin başarısız olduğunu ve hata mesajını doğrulama", "Mert Yıldız");
        Locates locates = new Locates();

        try {
            // "https://www.automationexercise.com/login" adresine gidin.
            Driver.getDriver().get(ConfigReader.getProperty("loginPageUrl"));
            String expectedTitle = ConfigReader.getProperty("loginPageTitle");
            String actualTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualTitle, expectedTitle);
            extentTest.info("Sayfaya başarılı bir şekilde gidildi");

            // "Email Address" alanını boş bırakın.
            locates.loginPageEmailArea.clear();
            extentTest.info("Email Address alanı boş bırakıldı");

            // "Password" alanını boş bırakın.
            locates.loginPagePasswordArea.clear();
            extentTest.info("Password alanı boş bırakıldı");

            // "Login" butonuna tıklayın.
            locates.loginPageLoginButton.click();
            extentTest.info("Login butonuna tıklandı.");

            // Giriş işleminin başarısız olduğunu ve hata mesajını doğrulayın.
            String currentTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(currentTitle, actualTitle);
            extentTest.info("Boş bırakılan alanlar için hata mesajı doğrulandı - Title değişmedi");


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
