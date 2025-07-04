package tests.Register;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Locates;
import utilities.ConfigReader;
import utilities.DataProviderClass;
import utilities.Driver;
import utilities.ReusableMethods;

import java.io.IOException;
import java.time.Duration;

import static utilities.ReusableMethods.extentReports;
import static utilities.ReusableMethods.extentTest;

public class TC03 {

    @Test(dataProvider = "invalidEmailData", dataProviderClass = DataProviderClass.class, retryAnalyzer = utilities.RetryAnalyzer.class)
    public void test03(String invalidEmail) throws IOException {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("Kayıt", "Kayıt Maili Geçersiz Karakterler", "Email adresinde kabul edilmeyen karakterler ile kayıt olmayı denerken hata mesajı alındığını doğrulama", "Mert Yıldız");
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
            locates.SignUpPageEmailArea.sendKeys(invalidEmail);
            extentTest.info("Geçersiz email adresi girildi: " + invalidEmail);

            // Email inputunun hata mesajını doğrula
            String emailValidationMessage = locates.SignUpPageEmailArea.getAttribute("validationMessage");
            System.out.println("Email validation message: " + emailValidationMessage);
            softAssert.assertTrue(emailValidationMessage != null && !emailValidationMessage.isEmpty(), "Email için hata mesajı görüntülenmedi!");
            extentTest.info("Geçersiz mail için hata mesajı doğrulandı: " + emailValidationMessage);

            // "Sign Up" butonuna tıklayın.
            locates.SignUpPageSignUpButton.click();

            // Title'ın değişmediğini doğrulayın
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