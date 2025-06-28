package tests.Cart;

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

public class TC25 {
    @Test(retryAnalyzer = utilities.RetryAnalyzer.class)
    public void test25() throws IOException {

        Actions actions = new Actions(Driver.getDriver());
        ReusableMethods reusableMethods = new ReusableMethods();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("Sepet", "Sepetten ürün silme", "Sepetteki ürünlerin silinebildiğini ve sepetin doğru şekilde güncellendiğini doğrulama", "Mert Yıldız");
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
            reusableMethods.jsScrollElement(locates.firstProductsViewProductButton);

            String expectedProductName = locates.productsPageFirstProductName.getText();

            actions.moveToElement(locates.firstProductMove).perform();
            wait.until(ExpectedConditions.visibilityOf(locates.firstProductAddToCartButton));
            reusableMethods.jsClick(locates.firstProductAddToCartButton);

            extentTest.info(expectedProductName + " ürününün Add to cart butonuna tıklandı");

            // Sepete eklendiğine dair bir uyarı mesajının çıktığını doğrulayın
            wait.until(ExpectedConditions.visibilityOf(locates.addToCartAddedText));
            softAssert.assertTrue(locates.addToCartAddedText.isDisplayed(), "Added mesajı görüntülenemedi!");

            String expectedAddedText = "Added!";
            String actualAddedText = locates.addToCartAddedText.getText();
            softAssert.assertEquals(actualAddedText, expectedAddedText);

            extentTest.info("Ürünün sepete eklendiğine dair 'Added' mesajı görüntülendi");

            // Açılan pencerede "Continue Shopping" butonuna tıklayın
            wait.until(ExpectedConditions.visibilityOf(locates.continueShoppingButton));
            locates.continueShoppingButton.click();

            extentTest.info("Continue Shopping butonuna tıklandı");

            // Sayfanın üst kısmında bulunan "Cart" (Sepet) bağlantısına tıklayın
            locates.homePageCartButton.click();
            extentTest.info("Cart bağlantısına tıklandı");

            // Sepet sayfasının yüklendiğini doğrulayın
            String expectedCartPageTitle = ConfigReader.getProperty("cartPageTitle");
            String actualCartPageTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualCartPageTitle, expectedCartPageTitle);

            softAssert.assertTrue(locates.shoppingCartTextInCartPage.isDisplayed(), "Shopping Cart texti görüntülenemedi!");

            extentTest.info("Sepet sayfası başarılı şekilde yüklendi");

            // Sepet sayfasında, ürünü silmek için kullanılan "X" işaretine tıklayın
            wait.until(ExpectedConditions.visibilityOf(locates.xButtonInCart));
            locates.xButtonInCart.click();

            extentTest.info("X işaretine tıklanarak ürün silindi");

            // Ürünün sepetten silindiğini ve sepetin doğru şekilde güncellendiğini doğrulayın
            wait.until(ExpectedConditions.visibilityOf(locates.cartIsEmptyText));
            softAssert.assertTrue(locates.cartIsEmptyText.isDisplayed(), "Sepet boş mesajı görüntülenemedi!");

            String expectedEmptyCartText = ConfigReader.getProperty("cartIsEmptyText");
            String actualEmptyCartText = locates.cartIsEmptyText.getText();

            softAssert.assertTrue(actualEmptyCartText.contains(expectedEmptyCartText), "Sepet boş mesajı görüntülenemedi!");

            extentTest.info("Sepet boş olarak güncellendiği doğrulandı : " + actualEmptyCartText);

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
