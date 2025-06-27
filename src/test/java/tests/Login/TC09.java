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

public class TC09 {
    @Test
    public void test09() throws IOException {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        Faker faker = new Faker();
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("Giriş", "Başarısız giriş", "Geçerli e posta adresi ile ancak hatalı parola ile giriş işleminin başarısız olduğunu ve hata mesajının gözüktüğünü doğrulama", "Mert Yıldız");
        Locates locates = new Locates();

        try {
            // "https://www.automationexercise.com/login" adresine gidin.
            Driver.getDriver().get(ConfigReader.getProperty("loginPageUrl"));

            String expectedTitle = ConfigReader.getProperty("loginPageTitle");
            String actualTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualTitle, expectedTitle);

            extentTest.info("Sayfaya başarılı bir şekilde gidildi");

            // "Email Address" alanına geçerli e-posta adresi girin.
            locates.loginPageEmailArea.sendKeys(ConfigReader.getProperty("mail"));
            extentTest.info("Email alanına geçerli email girildi : "+ConfigReader.getProperty("mail"));

            // "Password" alanına geçerli parolayı girin.
            locates.loginPagePasswordArea.sendKeys(ConfigReader.getProperty("invalidPassword"));
            extentTest.info("Password alanına hatalı parola girildi : "+ConfigReader.getProperty("invalidPassword"));

            // "Login" butonuna tıklayın.
            locates.loginPageLoginButton.click();
            extentTest.info("Login butonuna click yapıldı");

            // Giriş işleminin başarısız olduğunu ve hata mesajını doğrulayın. ("Your email or password is incorrect!")
            softAssert.assertTrue(locates.loginErrorText.isDisplayed());

            String expectedErrorText = "Your email or password is incorrect!";
            String actualErrorText = locates.loginErrorText.getText();
            softAssert.assertEquals(actualErrorText, expectedErrorText);

            extentTest.info("Giriş işleminin başarısız olduğu ve hata mesajının gözüktüğü doğrulandı : "+locates.loginErrorText.getText());

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
