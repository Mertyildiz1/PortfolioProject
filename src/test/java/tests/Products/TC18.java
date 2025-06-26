package tests.Products;

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

public class TC18 {
    @Test
    public void test18() throws IOException {

        ReusableMethods reusableMethods = new ReusableMethods();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("Ürünler", "Kategori filtreleme doğrulama", "Belirli bir kategori seçildiğinde, ürün listesinin doğru şekilde filtrelendiğini ve sadece o kategoriye ait ürünlerin görüntülendiğini doğrulama", "Mert Yıldız");
        Locates locates = new Locates();

        try {
            // 1- "https://www.automationexercise.com/products" adresine gidin.
            Driver.getDriver().get(ConfigReader.getProperty("productsPageUrl"));

            String expectedProductsTitle = "Automation Exercise - All Products";
            String actualProductsTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualProductsTitle, expectedProductsTitle);

            softAssert.assertTrue(locates.productsPageAllProductsText.isDisplayed());
            extentTest.info("Products sayfasına başarılı şekilde gidildi");

            // 2- Sol tarafta bulunan kategori filtreleme bölümünden Women > Dress kategorisini seçin.
            softAssert.assertTrue(locates.leftSideBarCategories.isDisplayed(), "Kategoriler görünmüyor!");
            softAssert.assertTrue(locates.womenCategoriesSelection.isDisplayed(), "Women kategorisi görünmüyor!");
            locates.womenCategoriesSelection.click();
            extentTest.info("Women kategorisi açıldı");

            locates.dressSelectionAtWomenTab.click();
            extentTest.info("Women > Dress kategorisi seçildi");

            // Sayfanın yüklenmesini bekleyin.
            wait.until(ExpectedConditions.visibilityOf(locates.productsPageAllProductsName.getFirst()));
            extentTest.info("Kategori sayfası başarılı şekilde yüklendi");

            // 3- Ürün listesinin filtrelendiğini ve sadece seçilen kategoriye ait ürünlerin görüntülendiğini doğrulayın.
            int urunSayisi = locates.productsPageAllProductsName.size();
            softAssert.assertTrue(urunSayisi > 0, "Filtrelenen ürün listesi boş!");
            extentTest.info("Gösterilen ürün sayısı: " + urunSayisi);

            // Ürünlerin görünürlüğünü ve kategorilerini kontrol et
            for (int i = 0; i < urunSayisi; i++) {

                softAssert.assertTrue(locates.productsPageAllProductsName.get(i).isDisplayed(), "Ürün ismi görüntülenemedi!");
                softAssert.assertTrue(locates.productsPageAllProductsPrice.get(i).isDisplayed(), "Ürün fiyatı görüntülenemedi!");

            }
            extentTest.info("Tüm ürünlerin isminin ve fiyatının görünürlüğü doğrulandı");

            // Detay sayfasına gidip kategori kontrolü
            for (int j = 0; j < urunSayisi; j++) {
                reusableMethods.jsClick(locates.allProductsViewProductButton.get(j));

                String actualCategoryName = locates.productDetailsCategoryName.getText();
                String expectedCategoryName = "Category: Women > Dress";
                softAssert.assertEquals(actualCategoryName,expectedCategoryName);

                Driver.getDriver().navigate().back();
                wait.until(ExpectedConditions.visibilityOf(locates.productsPageAllProductsName.getFirst()));
            }

            extentTest.info("Tüm ürünlerin kategorisinin seçilen kategori ile aynı olduğu doğrulandı");

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