package tests;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Locates;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static utilities.ReusableMethods.extentReports;
import static utilities.ReusableMethods.extentTest;

public class TC14 {
    @Test
    public void test14() throws IOException {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("Ürünler", "Ürünler listesi", "Ürünler sayfasının başarıyla yüklendiğini ve tüm ürünlerin görüntülendiğini doğrulama", "Mert Yıldız");
        Locates locates = new Locates();

        try {
            // 1- "https://www.automationexercise.com/products" adresine gidin.
            Driver.getDriver().get(ConfigReader.getProperty("productsPageUrl"));
            softAssert.assertTrue(locates.productsPageAllProductsText.isDisplayed());

            String expectedAllProductsText = "ALL PRODUCTS";
            String actualAllProductsText = locates.productsPageAllProductsText.getText();
            softAssert.assertEquals(actualAllProductsText,expectedAllProductsText);

            extentTest.info("Products sayfasına başarılı şekilde gidildi");

            // 2- Sayfanın başlığının "Automation Exercise - All Products" olduğunu doğrulayın.
            String expectedTitle = "Automation Exercise - All Products";
            String actualTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualTitle, expectedTitle, "Sayfa başlığı beklenen gibi değil!");
            extentTest.info("Sayfa başlığı doğrulandı: " + actualTitle);

            // 3- Ürünlerin listelendiğini doğrulayın
            int urunSayisi = locates.productsPageAllProducts.size();
            softAssert.assertTrue(urunSayisi > 0, "Ürün listesi boş!");
            extentTest.info("Ürünler listelendi, toplam ürün: " + urunSayisi);

            // 4- Ürünlerin adının ve fiyatının doğru şekilde görüntülendiğini doğrulayın
            int isimSayisi = locates.productsPageAllProductsName.size();
            int fiyatSayisi = locates.productsPageAllProductsPrice.size();
            softAssert.assertEquals(urunSayisi, isimSayisi);
            softAssert.assertEquals(urunSayisi, fiyatSayisi);

            for (int i = 0; i < urunSayisi; i++) {
                softAssert.assertTrue(locates.productsPageAllProductsName.get(i).isDisplayed(),"Ürün isimleri görüntülenemedi!");
                softAssert.assertTrue(locates.productsPageAllProductsPrice.get(i).isDisplayed(),"Ürün fiyatları görüntülenemedi!");
            }

            extentTest.info("Ürünlerin isimleri ve fiyatları başarılı şekilde görüntülendi");

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