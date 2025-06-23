package tests;

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

public class TC15 {
    @Test
    public void test15() throws IOException {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("Ürün Detay", "Ürün detay sayfası doğrulama", "Herhangi bir ürünün detay sayfasına tıklandığında, ürün detay bilgilerinin doğru şekilde görüntülendiğini doğrulama", "Mert Yıldız");
        Locates locates = new Locates();

        try {
            // 1- "https://www.automationexercise.com/products" adresine gidin.
            Driver.getDriver().get(ConfigReader.getProperty("productsPageUrl"));

            String expectedProductsTitle = "Automation Exercise - All Products";
            String actualProductsTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualProductsTitle, expectedProductsTitle);

            softAssert.assertTrue(locates.productsPageAllProductsText.isDisplayed());
            extentTest.info("Products sayfasına başarılı şekilde gidildi");

            // 2- Listelenen ürünlerden herhangi birinin üstündeki "View Product" butonuna tıklayın.
            String urunAdi = locates.productsPageFirstProductName.getText();
            String urunFiyati = locates.productsPageFirstProductPrice.getText();

            softAssert.assertTrue(locates.firstProductsViewProductButton.isDisplayed(), "View Product butonu görünmüyor!");
            locates.firstProductsViewProductButton.click();
            extentTest.info("İlk ürünün View Product butonuna tıklandı");

            // 3- Ürün detay sayfasının yüklendiğini doğrulayın.
            softAssert.assertTrue(locates.productDetailsQuantity.isDisplayed(), "Ürün detay sayfası yüklenmedi!");
            extentTest.info("Ürün detay sayfası başarılı şekilde yüklendi");

            // 4- Ürün adının ve fiyatının doğru şekilde görüntülendiğini doğrulayın.
            softAssert.assertTrue(locates.productDetailsProductName.isDisplayed(), "Ürün adı görüntülenmiyor!");
            softAssert.assertTrue(locates.productDetailsPrice.isDisplayed(), "Ürün fiyatı görüntülenmiyor!");

            String sepettekiUrunAdi = locates.productDetailsProductName.getText();
            String sepettekiUrunFiyati = locates.productDetailsPrice.getText();
            softAssert.assertEquals(sepettekiUrunAdi, urunAdi);
            softAssert.assertEquals(sepettekiUrunFiyati, urunFiyati);

            extentTest.info("Ürün fiyatı ve isminin doğru şekilde görüntülendiği doğrulandı");
            extentTest.info("Ürün adı: " + urunAdi + " | Ürün fiyatı: " + urunFiyati);

            // Ek kontroller - Ürün detay sayfasındaki diğer bilgilerin görünürlüğü
            softAssert.assertTrue(locates.productDetailsCategoryName.isDisplayed(), "Ürün kategorisi görüntülenmiyor!");
            softAssert.assertTrue(locates.productDetailsAvailability.isDisplayed(), "Ürün stok durumu görüntülenmiyor!");
            softAssert.assertTrue(locates.productDetailsCondition.isDisplayed(), "Ürün durumu görüntülenmiyor!");
            softAssert.assertTrue(locates.productDetailsBrandName.isDisplayed(), "Ürün markası görüntülenmiyor!");

            extentTest.info("Ürün detay sayfasındaki tüm bilgiler başarılı şekilde görüntülendi");

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