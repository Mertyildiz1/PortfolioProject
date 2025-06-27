package tests.Cart;

import com.aventstack.extentreports.MediaEntityBuilder;
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

public class TC26 {
    @Test
    public void test26() throws IOException {

        ReusableMethods reusableMethods = new ReusableMethods();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("Sepet", "Boş sepet mesajı", "Sepetin boş olduğu durumda, 'Cart is empty!' mesajının görüntülendiğini doğrulama", "Mert Yıldız");
        Locates locates = new Locates();

        try {
            // "https://www.automationexercise.com/view_cart" adresine gidin.
            Driver.getDriver().get(ConfigReader.getProperty("cartPageUrl"));

            String expectedCartPageTitle = ConfigReader.getProperty("cartPageTitle");
            String actualCartPageTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualCartPageTitle, expectedCartPageTitle);

            softAssert.assertTrue(locates.cartIsEmptyText.isDisplayed());

            extentTest.info("Cart sayfasına başarılı şekilde gidildi");

            // Sepet sayfasının yüklendiğini doğrulayın.
            wait.until(ExpectedConditions.visibilityOf(locates.shoppingCartTextInCartPage));

            softAssert.assertTrue(locates.shoppingCartTextInCartPage.isDisplayed(), "Shopping Cart texti görüntülenemedi!");

            extentTest.info("Sepet sayfası başarılı şekilde yüklendi");

            // Sepet sayfasında "Cart is empty!" mesajının görüntülendiğini doğrulayın.
            wait.until(ExpectedConditions.visibilityOf(locates.cartIsEmptyText));
            softAssert.assertTrue(locates.cartIsEmptyText.isDisplayed(), "Sepet boş mesajı görüntülenemedi!");

            String expectedEmptyCartText = ConfigReader.getProperty("cartIsEmptyText");
            String actualEmptyCartText = locates.cartIsEmptyText.getText();

            softAssert.assertTrue(actualEmptyCartText.contains(expectedEmptyCartText), "Sepet boş mesajı görüntülenemedi!");

            extentTest.info("Sepet boş olarak güncellendiği doğrulandı : " + actualEmptyCartText);

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
