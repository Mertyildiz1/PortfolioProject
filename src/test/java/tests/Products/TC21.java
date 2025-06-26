package tests.Products;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.interactions.Actions;
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

public class TC21 {
    @Test
    public void test21() throws IOException {

        ReusableMethods reusableMethods = new ReusableMethods();
        Actions actions = new Actions(Driver.getDriver());
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("Ürünler", "Ürün Detaydan Sepete Ekleme", "Ürün detay sayfasından sepete ürün ekleme işleminin doğru şekilde çalıştığını ve ürünün sepete eklendiğini doğrulama", "Mert Yıldız");
        Locates locates = new Locates();

        try {
            // "https://www.automationexercise.com/products" adresine gidin.
            Driver.getDriver().get(ConfigReader.getProperty("productsPageUrl"));

            String expectedProductsTitle = "Automation Exercise - All Products";
            String actualProductsTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualProductsTitle, expectedProductsTitle);

            softAssert.assertTrue(locates.productsPageAllProductsText.isDisplayed());
            extentTest.info("Products sayfasına başarılı şekilde gidildi");

            // Listelenen ürünlerden herhangi birinin üstündeki "View Product" butonuna tıklayın.
            String expectedProductName = locates.productsPageFirstProductName.getText();
            String expectedProductPrice = locates.productsPageFirstProductPrice.getText();

            reusableMethods.jsScrollElement(locates.firstProductsViewProductButton);
            locates.firstProductsViewProductButton.click();
            extentTest.info(expectedProductName + " ürününün View Product butonuna tıklandı");

            // Ürün detay sayfasının yüklendiğini doğrulayın.
            String expectedDetailsTitle = "Automation Exercise - Product Details";
            String actualDetailsTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualDetailsTitle, expectedDetailsTitle);

            // Detay sayfasında ürün adı ve fiyatı kontrolü
            String detailPageProductName = locates.productDetailsProductName.getText();
            String detailPageProductPrice = locates.productDetailsPrice.getText();
            softAssert.assertTrue(detailPageProductName.trim().equalsIgnoreCase(expectedProductName.trim()), "Detay sayfasındaki ürün adı yanlış!");
            softAssert.assertEquals(detailPageProductPrice.trim(), expectedProductPrice.trim(), "Detay sayfasındaki ürün fiyatı yanlış!");

            extentTest.info("Seçilen ürün adı: " + expectedProductName + " | Seçilen ürün fiyatı: " + expectedProductPrice);
            extentTest.info("Detay sayfasında ürün adı: " + detailPageProductName + " | Ürün fiyatı: " + detailPageProductPrice);
            extentTest.info("Ürün detay sayfası başarılı şekilde yüklendi,seçilen ürünün detay sayfasında da doğru isim ve fiyatla görüntülendiği doğrulandı");

            // "Add to cart" butonuna tıklayın.
            locates.addToCartButtonInViewProduct.click();
            extentTest.info("Add to cart butonuna tıklandı");

            // Ürünün sepete eklendiğine dair "Added" mesajının görüntülendiğini doğrulayın.
            wait.until(ExpectedConditions.visibilityOf(locates.addToCartAddedText));
            softAssert.assertTrue(locates.addToCartAddedText.isDisplayed(), "Added mesajı görüntülenemedi!");

            String expectedAddedText = "Added!";
            String actualAddedText = locates.addToCartAddedText.getText();
            softAssert.assertEquals(actualAddedText, expectedAddedText);

            extentTest.info("Ürünün sepete eklendiğine dair 'Added' mesajı görüntülendi");

            // "View Cart" bağlantısına tıklayın.
            locates.viewCartButton.click();
            extentTest.info("View Cart butonuna tıklandı");

            // Sepet sayfasının yüklendiğini doğrulayın.
            String expectedCartPageTitle = ConfigReader.getProperty("cartPageTitle");
            String actualCartPageTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualCartPageTitle, expectedCartPageTitle);

            softAssert.assertTrue(locates.shoppingCartTextInCartPage.isDisplayed(), "Shopping Cart texti görüntülenemedi!");
            extentTest.info("Sepet sayfası başarılı şekilde yüklendi");

            // Sepet sayfasında eklediğiniz ürünün doğru miktarda ve doğru bilgilerle görüntülendiğini doğrulayın.
            String actualProductName = locates.productInCart.getText();
            softAssert.assertTrue(actualProductName.trim().equalsIgnoreCase(expectedProductName.trim()), "Eklenen ürün adı yanlış!");

            String actualProductPrice = locates.blueTopPriceInCart.getText();
            softAssert.assertEquals(actualProductPrice.trim(), expectedProductPrice.trim(), "Eklenen ürün fiyatı yanlış!");

            int expectedQuantity = 1;
            int actualQuantity = Integer.parseInt(locates.firstProductQuantityInCart.getText());
            softAssert.assertEquals(actualQuantity, expectedQuantity);

            extentTest.info("Eklenen ürün adı: " + actualProductName + " | Eklenen ürün fiyatı: " + actualProductPrice + " | Eklenen ürün adedi : " + actualQuantity);

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