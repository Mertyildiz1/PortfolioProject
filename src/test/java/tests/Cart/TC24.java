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

public class TC24 {
    @Test(retryAnalyzer = utilities.RetryAnalyzer.class)
    public void test24() throws IOException {
        ReusableMethods reusableMethods = new ReusableMethods();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("Sepet", "Detaydan miktar artırıp sepete ekleme", "Ürün detay sayfasında miktar artırılıp sepete eklenince, sepette miktar ve toplam fiyatın doğru görüntülendiğini doğrulama", "Mert Yıldız");
        Locates locates = new Locates();

        try {
            // "https://www.automationexercise.com/products" adresine gidin.
            Driver.getDriver().get(ConfigReader.getProperty("productsPageUrl"));

            String expectedProductsTitle = ConfigReader.getProperty("productsPageTitle");
            String actualProductsTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualProductsTitle, expectedProductsTitle);

            softAssert.assertTrue(locates.productsPageAllProductsText.isDisplayed());

            extentTest.info("Products sayfasına başarılı şekilde gidildi");

            // Herhangi bir ürünün üzerindeki "View Product" butonuna tıklayın
            String expectedProductName = locates.productsPageFirstProductName.getText();
            String expectedProductPrice = locates.productsPageFirstProductPrice.getText();

            reusableMethods.jsScrollElement(locates.firstProductsViewProductButton);
            locates.firstProductsViewProductButton.click();

            extentTest.info(expectedProductName + " ürününün View Product butonuna tıklandı");

            // "Quantity" kısmından ürün miktarını arttırın.
            wait.until(ExpectedConditions.visibilityOf(locates.quantityAreaInViewProduct));
            locates.quantityAreaInViewProduct.clear();
            locates.quantityAreaInViewProduct.sendKeys("4");

            extentTest.info("Ürün miktarı 4 olarak ayarlandı");

            // "Add to cart" butonuna tıklayın
            locates.addToCartButtonInViewProduct.click();
            extentTest.info("Add to cart butonuna tıklandı");

            // Sepete eklendiğine dair bir bilgilendirme çıktığını doğrulayın
            wait.until(ExpectedConditions.visibilityOf(locates.addToCartAddedText));
            softAssert.assertTrue(locates.addToCartAddedText.isDisplayed(), "Added mesajı görüntülenemedi!");

            String expectedAddedText = ConfigReader.getProperty("addedToCartMessage");
            String actualAddedText = locates.addToCartAddedText.getText();
            softAssert.assertEquals(actualAddedText, expectedAddedText);

            extentTest.info("Ürünün sepete eklendiğine dair 'Added' mesajı görüntülendi");

            // "Continue Shopping" butonuna tıklayın
            locates.continueShoppingButton.click();
            extentTest.info("Continue Shopping butonuna tıklandı");

            // Sayfanın üst kısmında bulunan "Cart" butonuna tıklayın
            locates.homePageCartButton.click();
            extentTest.info("Cart butonuna tıklandı");

            // Sepet sayfasında listelenen ürünün doğru miktarda ve doğru fiyatta hesaplandığını doğrulayın
            String expectedCartPageTitle = ConfigReader.getProperty("cartPageTitle");
            String actualCartPageTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualCartPageTitle, expectedCartPageTitle);

            softAssert.assertTrue(locates.shoppingCartTextInCartPage.isDisplayed(), "Shopping Cart texti görüntülenemedi!");

            extentTest.info("Sepet sayfası başarılı şekilde yüklendi");

            // Ürün adı, fiyatı ve miktarının kontrolü
            String actualProductName = locates.productInCart.getText();
            softAssert.assertTrue(actualProductName.trim().equalsIgnoreCase(expectedProductName.trim()), "Eklenen ürün adı yanlış!");

            String actualProductPrice = locates.blueTopPriceInCart.getText();
            softAssert.assertEquals(actualProductPrice.trim(), expectedProductPrice.trim(), "Eklenen ürün fiyatı yanlış!");

            int expectedQuantity = 4;
            int actualQuantity = Integer.parseInt(locates.firstProductQuantityInCart.getText());
            softAssert.assertEquals(actualQuantity, expectedQuantity, "Ürün miktarı yanlış!");

            // Toplam fiyat kontrolü
            String expectedTotalPrice = ConfigReader.getProperty("totalPriceFor4Items");
            String actualTotalPrice = locates.cartTotalPriceFirstProduct.getText().replace(" ", "");
            softAssert.assertEquals(actualTotalPrice, expectedTotalPrice, "Toplam fiyat yanlış!");

            extentTest.info("Eklenen ürün adı: " + actualProductName + " | Eklenen ürün fiyatı: " + actualProductPrice + " | Eklenen ürün adedi : " + actualQuantity + " | Toplam fiyat: " + actualTotalPrice);

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
