package tests.Products;

import com.aventstack.extentreports.MediaEntityBuilder;
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

public class TC17 {
    @Test
    public void test17() throws IOException {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("Ürünler", "Geçersiz Ürün Arama", "Geçersiz bir ürün adı girildiğinde, arama sonuçlarının boş olduğunu doğrulama", "Mert Yıldız");
        Locates locates = new Locates();

        try {
            // 1- "https://www.automationexercise.com/products" adresine gidin.
            Driver.getDriver().get(ConfigReader.getProperty("productsPageUrl"));

            String expectedProductsTitle = ConfigReader.getProperty("productsPageTitle");
            String actualProductsTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualProductsTitle, expectedProductsTitle);

            softAssert.assertTrue(locates.productsPageAllProductsText.isDisplayed());
            extentTest.info("Products sayfasına başarılı şekilde gidildi");

            // 2- "Search Product" alanına geçersiz bir ürün adı girin.
            String gecersizUrun = ConfigReader.getProperty("gecersizUrunAdi");
            locates.productsSearchArea.clear();
            locates.productsSearchArea.sendKeys(gecersizUrun);
            extentTest.info("Arama alanına geçersiz ürün adı girildi: " + gecersizUrun);

            // 3- Arama butonuna tıklayın(Büyüteç simgesi)
            softAssert.assertTrue(locates.productSearchButton.isDisplayed(), "Arama butonu görünmüyor!");
            locates.productSearchButton.click();
            extentTest.info("Arama butonuna tıklandı");

            // 4- Arama sonuçlarının boş olduğunu doğrulayın.
            boolean sonucBos = locates.productsPageAllProductsName.isEmpty();
            softAssert.assertTrue(sonucBos, "Geçersiz ürün adı ile sonuç döndü!");
            extentTest.info("Arama sonuçlarının boş olduğu doğrulandı.");

            softAssert.assertAll();
            extentTest.pass("Test başarılı bir şekilde sonuçlandı");
        } catch (AssertionError | Exception e) {
            String screenshot = ReusableMethods.raporScreenShot();
            extentTest.fail("Test başarısız sonuçlandı!", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
            throw new RuntimeException(e);
        } finally {
            extentReports.flush();
        }
    }
} 