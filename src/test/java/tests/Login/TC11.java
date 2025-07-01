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

public class TC11 {
    @Test(retryAnalyzer = utilities.RetryAnalyzer.class)
    public void test11() throws IOException {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        Faker faker = new Faker();
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("Giriş", "Başarısız giriş", "Password alanı boş bırakıldığında giriş işleminin başarısız olduğunu ve hata mesajını doğrulama", "Mert Yıldız");
        Locates locates = new Locates();

        try {
            // "https://www.automationexercise.com/login" adresine gidin.
            Driver.getDriver().get(ConfigReader.getProperty("loginPageUrl"));

            String expectedTitle = ConfigReader.getProperty("loginPageTitle");
            String actualTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualTitle, expectedTitle);

            extentTest.info("Sayfaya başarılı bir şekilde gidildi");

            // "Email Address" alanına geçerli mail girin.
            locates.loginPageEmailArea.sendKeys(ConfigReader.getProperty("mail"));
            extentTest.info("Email Address alanına geçerli mail girildi");

            // "Password" alanını boş bırakın.
            locates.loginPagePasswordArea.clear();
            extentTest.info("Password alanı boş bırakıldı");

            // "Login" butonuna tıklayın.
            locates.loginPageLoginButton.click();
            extentTest.info("Login butonuna tıklandı.");

            // Password inputunun hata mesajını doğrula
            String passwordValidationMessage = locates.loginPagePasswordArea.getAttribute("validationMessage");
            System.out.println("Password validation message: " + passwordValidationMessage);
            softAssert.assertTrue(passwordValidationMessage != null && !passwordValidationMessage.isEmpty(), "Password için hata mesajı görüntülenmedi!");
            extentTest.info("Boş bırakılan password alanı için hata mesajı doğrulandı: " + passwordValidationMessage);

            // Title'ın değişmediğini doğrula
            ReusableMethods.bekle(1);
            String currentTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(currentTitle, actualTitle);
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


