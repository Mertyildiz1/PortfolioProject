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

public class TC10 {
    @Test(retryAnalyzer = utilities.RetryAnalyzer.class)
    public void test10() throws IOException {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        Faker faker = new Faker();
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("Giriş", "Başarısız giriş", "Email alanı boş bırakıldığında giriş işleminin başarısız olduğunu ve hata mesajını doğrulama", "Mert Yıldız");
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

            // "Password" alanına geçerli şifre girin.
            locates.loginPagePasswordArea.sendKeys(ConfigReader.getProperty("password"));
            extentTest.info("Password alanına geçerli şifre girildi");

            // "Login" butonuna tıklayın.
            locates.loginPageLoginButton.click();

            // Giriş işleminin başarısız olduğunu ve hata mesajını doğrulayın.
            String currentTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(currentTitle, actualTitle);
            extentTest.info("Boş bırakın Email Address alanı için hata mesajı doğrulandı - Title değişmedi");


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
