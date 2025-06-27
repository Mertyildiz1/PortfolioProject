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

public class TC19 {
    @Test
    public void test19() throws IOException {

        ReusableMethods reusableMethods = new ReusableMethods();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("Ürünler", "Polo marka filtreleme", "Polo markası seçildiğinde sadece Polo ürünlerinin listelendiğini doğrulama", "Mert Yıldız");
        Locates locates = new Locates();

        try {
            // 1- "https://www.automationexercise.com/products" adresine gidin.
            Driver.getDriver().get(ConfigReader.getProperty("productsPageUrl"));

            String expectedProductsTitle = ConfigReader.getProperty("productsPageTitle");
            String actualProductsTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualProductsTitle, expectedProductsTitle);

            softAssert.assertTrue(locates.productsPageAllProductsText.isDisplayed());
            extentTest.info("Products sayfasına başarılı şekilde gidildi");

            // 2- Sol tarafta bulunan marka filtreleme bölümünden Polo markasını seçin.
            softAssert.assertTrue(locates.brandPoloSelection.isDisplayed(),"Polo Markası görüntülenemedi");
            locates.brandPoloSelection.click();
            extentTest.info("Polo markası başarılı şekilde seçildi");

            // 3- Ürün listesinin filtrelendiğini ve sadece seçilen markaya ait ürünlerin görüntülendiğini doğrulayın.
            softAssert.assertTrue(locates.productsPageAllProductsName.size() > 0, "Polo markasına ait ürün bulunamadı!");

            int urunSayisi = locates.productsPageAllProductsName.size();
            extentTest.info("Polo markasına ait filtrelenen ürün sayısı : "+urunSayisi);

            for (int i = 0; i < urunSayisi ; i++) {

                reusableMethods.jsClick(locates.allProductsViewProductButton.get(i));

                String actualBrandName = locates.productDetailsBrandName.getText();
                String expectedBrandName = "Polo";
                softAssert.assertTrue(actualBrandName.contains(expectedBrandName));

                Driver.getDriver().navigate().back();
                wait.until(ExpectedConditions.visibilityOf(locates.productsPageAllProductsName.get(0)));
            }


            extentTest.pass("Tüm listelenen ürünlerin Polo markasına ait olduğu doğrulandı.");
            softAssert.assertAll();

        } catch (AssertionError | Exception e) {
            String screenshot = ReusableMethods.raporScreenShot();
            extentTest.fail("Test başarısız sonuçlandı!", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
            throw e;
        } finally {
            extentReports.flush();
        }
    }
} 