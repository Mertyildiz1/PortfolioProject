package tests.Payment;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.github.javafaker.Faker;
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

public class TC30 {
    @Test(retryAnalyzer = utilities.RetryAnalyzer.class)
    public void test30() throws IOException, InterruptedException {

        Faker faker = new Faker();
        Actions actions = new Actions(Driver.getDriver());
        ReusableMethods reusableMethods = new ReusableMethods();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        SoftAssert softAssert = new SoftAssert();
        ReusableMethods.report("Ödeme", "Başarılı Ödeme", "Siparişin başarıyla onaylandığını doğrulama", "Mert Yıldız");
        Locates locates = new Locates();

        try {
            // "https://www.automationexercise.com/products" adresine gidin.
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

            // "Proceed to checkout" butonuna tıklayın
            wait.until(ExpectedConditions.visibilityOf(locates.proceedToCheckoutButton));
            locates.proceedToCheckoutButton.click();

            extentTest.info("Proceed to checkout butonuna tıklandı");

            // Sayfada aşağı inip "Place Order" butonuna basın
            reusableMethods.jsScrollEnd();
            wait.until(ExpectedConditions.visibilityOf(locates.placeOrderButton));
            locates.placeOrderButton.click();

            extentTest.info("Place Order butonuna tıklandı");

            // Ödeme sayfasında kredi kartı bilgilerinin girilebileceği alanların görüntülendiğini doğrulayın
            wait.until(ExpectedConditions.visibilityOf(locates.paymentNameOnCard));
            softAssert.assertTrue(locates.paymentNameOnCard.isDisplayed(), "Name on Card alanı görüntülenemedi!");
            softAssert.assertTrue(locates.paymentNameOnCard.isEnabled(), "Name on Card alanı aktif değil!");
            extentTest.info("Ödeme ekranında Name on Card alanı görüntülendi ve aktif");

            softAssert.assertTrue(locates.paymentCardNumber.isDisplayed(), "Card Number alanı görüntülenemedi!");
            softAssert.assertTrue(locates.paymentCardNumber.isEnabled(), "Card Number alanı aktif değil!");
            extentTest.info("Ödeme ekranında Card Number alanı görüntülendi ve aktif");


            // Kredi kartı bilgilerini doğru formatta ve eksiksiz bir şekilde girin
            locates.paymentNameOnCard.clear();
            locates.paymentNameOnCard.sendKeys(ConfigReader.getProperty("name"));

            locates.paymentCardNumber.clear();
            locates.paymentCardNumber.sendKeys(faker.business().creditCardNumber());

            locates.paymentCardCvc.clear();
            locates.paymentCardCvc.sendKeys(ConfigReader.getProperty("cardCvc"));

            locates.paymentCardExpiryMonth.clear();
            locates.paymentCardExpiryMonth.sendKeys(ConfigReader.getProperty("cardExpiryMonth"));

            locates.paymentCardExpiryYear.clear();
            locates.paymentCardExpiryYear.sendKeys(ConfigReader.getProperty("cardExpiryYear"));

            extentTest.info("Kredi kartı bilgileri doğru formatta ve eksiksiz şekilde girildi");

            // "Pay and Confirm Order" butonuna basın
            locates.payAndConfirmOrder.click();
            extentTest.info("Pay and Confirm Order butonuna tıklandı");

            // Siparişin onaylanıp "Order Placed" mesajının görüntülendiğini doğrulayın.
            Thread.sleep(1500);

            try {
                softAssert.assertTrue(locates.verifySuccessOrder.isDisplayed(),"Mesaj görüntülenemedi!");
            } catch (Exception e) {
                wait.until(ExpectedConditions.visibilityOf(locates.verifySuccessOrder));
                softAssert.assertTrue(locates.verifySuccessOrder.isDisplayed(),"Mesaj görüntülenemedi!");
            }

            String expectedVerifyMessage = ConfigReader.getProperty("orderVerifySuccessMessage");
            String actualVerifyMessage = locates.verifySuccessOrder.getText();
            softAssert.assertEquals(actualVerifyMessage, expectedVerifyMessage);


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