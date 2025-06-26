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

public class TC20 {
    @Test
    public void test20() throws IOException {

        Actions actions = new Actions(Driver.getDriver());
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("Ürünler", "Ürün sepete ekleme ve doğrulama", "Ürün listesinden sepete ürün ekleme işleminin doğru çalıştığını ve ürünün sepete doğru biçimde eklendiğini doğrulama", "Mert Yıldız");
        Locates locates = new Locates();
        ReusableMethods reusableMethods = new ReusableMethods();

        try {
            // 1- "https://www.automationexercise.com/products" adresine gidin.
            Driver.getDriver().get(ConfigReader.getProperty("productsPageUrl"));

            String expectedProductsTitle = "Automation Exercise - All Products";
            String actualProductsTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualProductsTitle, expectedProductsTitle);

            softAssert.assertTrue(locates.productsPageAllProductsText.isDisplayed());
            extentTest.info("Products sayfasına başarılı şekilde gidildi");

            // 2- Ürün listesinde bulunan herhangi bir ürünün üzerine gelin ve "Add to cart" butonuna tıklayın.
            actions.moveToElement(locates.firstProductMove).perform();

            softAssert.assertTrue(locates.firstProductAddToCartButton.isDisplayed(), "Add to cart butonu görüntülenemedi!!");
            reusableMethods.jsClick(locates.firstProductAddToCartButton);

            extentTest.info("İlk ürünün Add to cart butonuna tıklandı");

            // 3- Ürünün sepete eklendiğine dair "Added" mesajının görüntülendiğini doğrulayın.
            wait.until(ExpectedConditions.visibilityOf(locates.addToCartAddedText));
            softAssert.assertTrue(locates.addToCartAddedText.isDisplayed(), "Ürün sepete eklendi mesajı görüntülenemedi!!");

            String expectedAddedText = "Added!";
            String actualAddedText = locates.addToCartAddedText.getText();
            softAssert.assertEquals(actualAddedText, expectedAddedText);

            extentTest.info("Ürün sepete eklendi mesajı görüntülendi");

            // Çıkan mesajda "Continue Shopping" butonuna tıklayın.
            softAssert.assertTrue(locates.continueShoppingButton.isDisplayed(), "Continue Shopping butonu görüntülenemedi!");
            locates.continueShoppingButton.click();
            extentTest.info("Continue Shopping butonuna tıklandı");

            // 4- Sayfanın üst kısmında bulunan "Cart" bağlantısına tıklayın.
            softAssert.assertTrue(locates.homePageCartButton.isDisplayed(), "Cart butonu görüntülenemedi!");
            locates.homePageCartButton.click();
            extentTest.info("Cart butonuna tıklandı");

            // 5- Sepet sayfasının yüklendiğini doğrulayın.
            String expectedCartPageTitle = ConfigReader.getProperty("cartPageTitle");
            String actualCartPageTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualCartPageTitle, expectedCartPageTitle);

            wait.until(ExpectedConditions.visibilityOf(locates.shoppingCartTextInCartPage));
            softAssert.assertTrue(locates.shoppingCartTextInCartPage.isDisplayed(), "Sepet sayfası yüklenmedi!");
            extentTest.info("Sepet sayfası başarılı şekilde yüklendi");

            // 6- Sepet sayfasında eklediğiniz ürünün doğru miktarda ve doğru bilgilerle görüntülendiğini doğrulayın.
            String expectedProductName = "Blue Top";
            String actualProductName = locates.productInCart.getText();
            softAssert.assertEquals(actualProductName, expectedProductName, "Eklenen ürün adı yanlış!");

            String expectedProductPrice = "Rs. 500";
            String actualProductPrice = locates.blueTopPriceInCart.getText();
            softAssert.assertEquals(actualProductPrice, expectedProductPrice, "Eklenen ürün fiyatı yanlış!");

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