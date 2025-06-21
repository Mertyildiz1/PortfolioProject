package tests;

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

public class TC07 {
    @Test
    public void test07() throws IOException {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        Faker faker = new Faker();
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("Giriş", "Başarılı giriş", "Geçerli e-posta ve parola ile giriş işleminin başarıyla tamamlandığını doğrulama", "Mert Yıldız");
        Locates locates = new Locates();

        try {
            // "https://www.automationexercise.com/login" adresine gidin.
            Driver.getDriver().get(ConfigReader.getProperty("loginPageUrl"));
            String expectedTitle = "Automation Exercise - Signup / Login";
            String actualTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualTitle,expectedTitle);
            extentTest.info("Sayfaya başarılı bir şekilde gidildi");

            // "Email Address" alanına geçerli e-posta adresi girin.
            locates.loginPageEmailArea.sendKeys(ConfigReader.getProperty("mail"));
            extentTest.info("Email alanına geçerli email girildi");

            // "Password" alanına geçerli parolayı girin.
            locates.loginPagePasswordArea.sendKeys(ConfigReader.getProperty("password"));
            extentTest.info("Password alanına geçerli parola girildi");

            // "Login" butonuna tıklayın.
            locates.loginPageLoginButton.click();
            extentTest.info("Login butonuna click yapıldı");

            // Başarılı şekilde giriş yapıldığını doğrulayın.
            softAssert.assertTrue(locates.loggedInAsUser.isDisplayed());
            softAssert.assertTrue(locates.homePageHomeButton.isDisplayed());
            softAssert.assertNotEquals(Driver.getDriver().getTitle(),expectedTitle);

            extentTest.info("Başarılı şekilde giriş yapıldığı doğrulandı");

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

