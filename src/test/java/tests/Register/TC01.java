package tests.Register;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
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

public class TC01 {
    @Test
    public void test01() throws IOException {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        Faker faker = new Faker();
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("Kayıt", "Başarılı Kayıt", "Geçerli e-posta ve parola ile kayıt işleminin doğrulanması", "Mert Yıldız");
        Locates locates = new Locates();


        try {
            // "https://www.automationexercise.com/login" adresine gidin.
            Driver.getDriver().get(ConfigReader.getProperty("loginPageUrl"));

            String actualTitle = Driver.getDriver().getTitle();
            String expectedTitle = ConfigReader.getProperty("loginPageTitle");
            softAssert.assertEquals(actualTitle, expectedTitle);

            extentTest.info("Sayfaya başarılı bir şekilde gidildi");

            // Name alanına geçerli bir isim girin.
            locates.SignUpPageNameArea.sendKeys(ConfigReader.getProperty("name"));

            // "Email Address" alanına geçerli ve daha önce kullanılmamış bir email girin.
            locates.SignUpPageEmailArea.sendKeys(faker.internet().emailAddress());

            // "Sign Up" butonuna tıklayın.
            locates.SignUpPageSignUpButton.click();

        /*
        "Enter Account Information" sayfası açılacaktır. Bu sayfada:
                       1- "Title" bölümünde bir başlık seçin (örneğin, "Mr.").
                       2- "Password" alanına geçerli bir parola girin.
                       3- "Date of Birth" bölümünde geçerli bir doğum tarihi seçin.
                       4- "First name" alanına geçerli bir ad girin.
                       5- "Last name" alanına geçerli bir soyad girin.
                       6- "Address" alanına geçerli bir adres girin.
                       7- "Country" bölümünden geçerli bir ülke seçin.
                       8- "State" alanına geçerli bir şehir girin.
                       9- "City" alanına geçerli bir ilçe girin.
                       10- "Zipcode" alanına geçerli bir posta kodu girin.
                       11- "Mobile Number" alanına geçerli bir telefon numarası girin.
                       12- "Create Account" butonuna tıklayın.
        */

            softAssert.assertTrue(locates.enterAccountInfoText.isDisplayed());

            locates.mrsMrSelectRadioBox.click();
            locates.mrsMrSelectRadioBox.sendKeys(Keys.TAB,
                    Keys.TAB,
                    ConfigReader.getProperty("password"),
                    Keys.TAB,
                    String.valueOf(faker.random().nextInt(1, 30)),
                    Keys.TAB,
                    "July",
                    Keys.TAB,
                    String.valueOf(faker.random().nextInt(1950, 2015)));

            locates.registerPageFirstNameArea.sendKeys(
                    faker.name().firstName(),
                    Keys.TAB,
                    faker.name().lastName(),
                    Keys.TAB,
                    faker.company().name(),
                    Keys.TAB,
                    faker.address().fullAddress(),
                    Keys.TAB,
                    Keys.TAB,
                    faker.address().country(),
                    Keys.TAB,
                    faker.address().state(),
                    Keys.TAB,
                    faker.address().city(),
                    Keys.TAB,
                    faker.address().zipCode());

            locates.registerPhoneNumberArea.sendKeys(faker.phoneNumber().phoneNumber());

            locates.registerPageCreateAccButton.click();
            // "ACCOUNT CREATED!"" mesajı görüntülenmelidir.
            softAssert.assertTrue(locates.accCreatedText.isDisplayed());
            extentTest.info("Başarılı bir şekilde kayıt olundu");


            // "Continue" butonuna tıklayın
            locates.accCretedContinueButton.click();

            // Kullanıcı otomatik olarak giriş yapmış olmalıdır.
            softAssert.assertTrue(locates.homePageHomeButton.isDisplayed());

            // Kullanıcı anasayfaya yönlendirilmeli.
            String actualHomeTitle = Driver.getDriver().getTitle();
            String expectedHomeTitle = ConfigReader.getProperty("homePageTitle");
            softAssert.assertEquals(actualHomeTitle, expectedHomeTitle);
            extentTest.info("Kullanıcı anasayfaya başarılı bir şekilde yönlendirildi.");

            // Üst kısımda kullanıcının adı ""Logged in as "" olarak görüntülenmelidir.
            softAssert.assertTrue(locates.loggedInAsUser.isDisplayed());
            softAssert.assertTrue(locates.loggedInAsUser.getText().contains(ConfigReader.getProperty("name")));
            extentTest.info("Kayıt olurken kullanılan kullanıcı ismi anasayfada doğru şekilde görüntülendi");

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
