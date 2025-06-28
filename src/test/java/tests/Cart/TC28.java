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

public class TC28 {
    @Test(retryAnalyzer = utilities.RetryAnalyzer.class)
    public void test28() throws IOException {

        Actions actions = new Actions(Driver.getDriver());
        ReusableMethods reusableMethods = new ReusableMethods();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("Sepet", "Ödeme geçişi testi", "Giriş yapmış kullanıcı sepetten adres ve ürün detay sayfasına geçebilmeli", "Mert Yıldız");
        Locates locates = new Locates();

        try {
            // 1- "https://www.automationexercise.com/products" adresine gidin.
            Driver.getDriver().get(ConfigReader.getProperty("productsPageUrl"));

            String expectedProductsTitle = ConfigReader.getProperty("productsPageTitle");
            String actualProductsTitle = Driver.getDriver().getTitle();
            softAssert.assertEquals(actualProductsTitle, expectedProductsTitle);
            softAssert.assertTrue(locates.productsPageAllProductsText.isDisplayed());
            extentTest.info("Products sayfasına başarılı şekilde gidildi");

            // Giriş yap
            locates.homePageSignUpLoginButton.click();
            wait.until(ExpectedConditions.visibilityOf(locates.loginToYourAccText));

            locates.loginPageEmailArea.clear();
            locates.loginPageEmailArea.sendKeys(ConfigReader.getProperty("mail"));

            locates.loginPagePasswordArea.clear();
            locates.loginPagePasswordArea.sendKeys(ConfigReader.getProperty("password"));

            locates.loginPageLoginButton.click();

            wait.until(ExpectedConditions.visibilityOf(locates.loggedInAsUser));
            softAssert.assertTrue(locates.loggedInAsUser.isDisplayed(), "Kullanıcı giriş yapamadı!");

            extentTest.info("Kullanıcı başarılı şekilde giriş yaptı");

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

            String expectedAddedText = ConfigReader.getProperty("addedToCartMessage");
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

            // Sepet sayfasında "Proceed to checkout" butonuna tıklayın
            wait.until(ExpectedConditions.visibilityOf(locates.proceedToCheckoutButton));
            locates.proceedToCheckoutButton.click();

            extentTest.info("Proceed to checkout butonuna tıklandı");

            // Adres ve ürün detay sayfasına geçildiğini doğrula
            wait.until(ExpectedConditions.visibilityOf(locates.checkoutAddressDetailsText));
            softAssert.assertTrue(locates.checkoutAddressDetailsText.isDisplayed(), "Adres ve ürün detay sayfasına geçilemedi!");

            extentTest.info("Kullanıcı adres ve ürün detay sayfasına başarılı şekilde geçti");

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
