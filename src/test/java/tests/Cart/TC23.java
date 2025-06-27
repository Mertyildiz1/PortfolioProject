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

public class TC23 {
    @Test
    public void test23() throws IOException {

        ReusableMethods reusableMethods = new ReusableMethods();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("Sepet", "Sepet sayfası", "Sepetteki ürünlerin doğru bilgilerle görüntülendiğini doğrulama", "Mert Yıldız");
        Locates locates = new Locates();

        try {
            // "https://www.automationexercise.com/products" adresine gidin.
            Driver.getDriver().get(ConfigReader.getProperty("productsPageUrl"));

            String expectedProductsTitle = ConfigReader.getProperty("productsPageTitle");
            String actualProductsTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualProductsTitle, expectedProductsTitle);

            softAssert.assertTrue(locates.productsPageAllProductsText.isDisplayed());
            extentTest.info("Products sayfasına başarılı şekilde gidildi");

            // Herhangi bir ürünü sepete ekleyin
            String expectedProductName = locates.productsPageFirstProductName.getText();
            String expectedProductPrice = locates.productsPageFirstProductPrice.getText();

            reusableMethods.jsScrollElement(locates.firstProductsViewProductButton);
            locates.firstProductsViewProductButton.click();
            extentTest.info(expectedProductName + " ürününün View Product butonuna tıklandı");

            locates.addToCartButtonInViewProduct.click();
            extentTest.info("Add to cart butonuna tıklandı");

            // Ürünün sepete eklendiğine dair "Added" mesajının görüntülendiğini doğrulayın.
            wait.until(ExpectedConditions.visibilityOf(locates.addToCartAddedText));
            softAssert.assertTrue(locates.addToCartAddedText.isDisplayed(), "Added mesajı görüntülenemedi!");

            String expectedAddedText = ConfigReader.getProperty("addedToCartMessage");
            String actualAddedText = locates.addToCartAddedText.getText();
            softAssert.assertEquals(actualAddedText, expectedAddedText);

            extentTest.info("Ürünün sepete eklendiğine dair 'Added' mesajı görüntülendi");

            // Açılan pencerede "Continue Shopping" butonuna tıklayın.
            locates.continueShoppingButton.click();
            extentTest.info("Continue Shopping butonuna tıklandı");

            // Sayfanın üst kısmında bulunan "Cart" (Sepet) bağlantısına tıklayın.
            locates.homePageCartButton.click();
            extentTest.info("Cart bağlantısına tıklandı");

            // Sepet sayfasının yüklendiğini doğrulayın.
            String expectedCartPageTitle = ConfigReader.getProperty("cartPageTitle");
            String actualCartPageTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualCartPageTitle, expectedCartPageTitle);

            softAssert.assertTrue(locates.proceedToCheckoutButton.isDisplayed(), "Proceed to checkout butonu görüntülenemedi!");
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