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

public class TC06 {
    @Test(retryAnalyzer = utilities.RetryAnalyzer.class)
    public void test06() throws IOException {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        Faker faker = new Faker();
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("Kayıt", "Başarısız Kayıt", "Email Address alanı boş bırakıldığında kayıt işleminin başarısız olduğunu doğrulama", "Mert Yıldız");
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

            extentTest.info("Name alanına geçerli isim girildi : " + ConfigReader.getProperty("name"));

            // "Email Address" alanını boş bırakın.
            locates.SignUpPageEmailArea.clear();

            extentTest.info("Email Address alanı boş bırakıldı/temizlendi");

            // "Sign Up" butonuna tıklayın.
            locates.SignUpPageSignUpButton.click();
            extentTest.info("Sign up butonuna click yapıldı");

            // Email inputunun hata mesajını doğrula
            String emailValidationMessage = locates.SignUpPageEmailArea.getAttribute("validationMessage");
            System.out.println("Email validation message: " + emailValidationMessage);
            softAssert.assertTrue(emailValidationMessage != null && !emailValidationMessage.isEmpty(), "Email için hata mesajı görüntülenmedi!");
            extentTest.info("Boş bırakılan Email Address alanı için hata mesajı doğrulandı: " + emailValidationMessage);

            // Title'ın değişmediğini doğrula
            ReusableMethods.bekle(1);
            String currentTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(currentTitle, initialTitle);
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
