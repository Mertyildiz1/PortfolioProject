package tests.Login;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.github.javafaker.Faker;
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

public class TC13 {
    @Test
    public void test13() throws IOException {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        Faker faker = new Faker();
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("Logout", "Başarılı logout", "Giriş yapmış bir kullanıcının çıkış işleminin başarıyla tamamlandığını ve tekrar giriş yapması gerektiğini doğrulama", "Mert Yıldız");
        Locates locates = new Locates();

        try {
            // "https://www.automationexercise.com/login" adresine gidin.
            Driver.getDriver().get(ConfigReader.getProperty("loginPageUrl"));

            String expectedTitle = ConfigReader.getProperty("loginPageTitle");
            String actualTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualTitle, expectedTitle);

            extentTest.info("Sayfaya başarılı bir şekilde gidildi");

            // Geçerli bir kullanıcı adı ve parola ile giriş yapın.
            locates.loginPageEmailArea.sendKeys(ConfigReader.getProperty("mail"));
            extentTest.info("Email Address alanına geçerli mail girildi : " + ConfigReader.getProperty("mail"));

            locates.loginPagePasswordArea.sendKeys(ConfigReader.getProperty("password"));
            extentTest.info("Password alanına geçerli şifre girildi");

            // "Login" butonuna tıklayın.
            locates.loginPageLoginButton.click();
            extentTest.info("Login butonuna tıklandı.");

            // Anasayfaya yönlendirildiğinizden emin olun.
            wait.until(ExpectedConditions.urlToBe(ConfigReader.getProperty("homePageUrl")));
            locates.homePageHomeButton.isDisplayed();

            String expectedHomePageTitle = "Automation Exercise";
            String actualHomePageTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualHomePageTitle, expectedHomePageTitle);

            extentTest.info("Anasayfaya başarılı şekilde yönlendirildi");

            // Sağ üst köşede kullanıcı adınızın görüntülendiğini doğrulayın.
            String expectedLoggedName = "Logged in as " + ConfigReader.getProperty("name");
            String actualLoggedName = locates.loggedInAsUser.getText();
            softAssert.assertEquals(actualLoggedName, expectedLoggedName);

            extentTest.info("Logged in as kısmında kullanıcı adının düzgün biçimde gözüktüğü doğrulandı : " + actualLoggedName);

            // "Logout" butonuna tıklayın
            locates.logoutButton.click();
            extentTest.info("Logout butonuna tıklandı");

            // Giriş sayfasına yönlendirildiğini doğrulayın.
            wait.until(ExpectedConditions.urlToBe(ConfigReader.getProperty("loginPageUrl")));

            String expectedTitleAfterLogout = ConfigReader.getProperty("loginPageTitle");
            String actualTitleAfterLogout = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualTitleAfterLogout, expectedTitleAfterLogout);

            softAssert.assertTrue(locates.loginToYourAccText.isDisplayed());

            extentTest.info("Giriş sayfasına başarıyla yönlendirildiği doğrulandı");

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
