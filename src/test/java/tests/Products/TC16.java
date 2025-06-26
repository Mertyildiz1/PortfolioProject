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

public class TC16 {
    @Test
    public void test16() throws IOException {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("Ürünler", "Arama fonksiyonu doğrulama", "Geçerli bir ürün adı girildiğinde, arama sonuçlarının doğru şekilde filtrelendiğini ve ilgili ürünlerin görüntülendiğini doğrulama", "Mert Yıldız");
        Locates locates = new Locates();

        try {
            // 1- "https://www.automationexercise.com/products" adresine gidin.
            Driver.getDriver().get(ConfigReader.getProperty("productsPageUrl"));

            String expectedProductsTitle = "Automation Exercise - All Products";
            String actualProductsTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualProductsTitle, expectedProductsTitle);

            softAssert.assertTrue(locates.productsPageAllProductsText.isDisplayed());
            extentTest.info("Products sayfasına başarılı şekilde gidildi");

            // 2- "Search Product" alanına geçerli bir ürün adı girin.
            String arananUrun = ConfigReader.getProperty("arananUrun");
            locates.productsSearchArea.clear();
            locates.productsSearchArea.sendKeys(arananUrun);
            extentTest.info("Arama alanına ürün adı girildi: " + arananUrun);

            // 3- Arama butonuna tıklayın(Büyüteç simgesi)
            softAssert.assertTrue(locates.productSearchButton.isDisplayed(), "Arama butonu görünmüyor!");
            locates.productSearchButton.click();
            extentTest.info("Arama butonuna tıklandı");

            // 4- Arama sonucunun görüntülendiğini doğrulayın.
            softAssert.assertTrue(locates.searchedProductText.isDisplayed(), "Arama sonuçları görüntülenmiyor!");
            extentTest.info("Arama sonuçları başarılı şekilde görüntülendi");

            // 5- Arama sonuçlarında listelenen tüm ürünlerin, arama terimiyle ilgili olduğunu doğrulayın
            softAssert.assertTrue(!locates.productsPageAllProductsName.isEmpty(), "Arama sonucunda ürün bulunamadı!");

            int ilgiliUrunSayisi = 0;
            for (int i = 0; i < locates.productsPageAllProductsName.size(); i++) {
                String urunAdiText = locates.productsPageAllProductsName.get(i).getText().toLowerCase();

                if (urunAdiText.contains(arananUrun.toLowerCase())) {
                    ilgiliUrunSayisi++;
                    extentTest.info("İlgili ürün bulundu: " + urunAdiText);
                } else {
                    extentTest.warning("İlgisiz ürün bulundu: " + urunAdiText);
                }
            }

            softAssert.assertTrue(ilgiliUrunSayisi > 0, "Arama terimiyle ilgili ürün bulunamadı!");
            extentTest.info("Toplam " + locates.productsPageAllProductsName.size() + " ürün bulundu, " + ilgiliUrunSayisi + " tanesi arama terimiyle ilgili");

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