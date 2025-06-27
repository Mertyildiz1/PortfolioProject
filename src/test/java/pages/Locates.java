package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;

import java.util.List;

public class Locates {

    protected WebDriver driver;
    protected Select select;
    protected Actions actions;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;

    public Locates() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//*[.=' Test Cases']")
    public WebElement homePageTestCasesButton;
    //*[text()='Enter Account Information']
    @FindBy(xpath = "//i[@class='fa fa-lock']")
    public WebElement homePageSignUpLoginButton;

    @FindBy(xpath = "//input[@name='name']")
    public WebElement SignUpPageNameArea;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    public WebElement SignUpPageEmailArea;

    @FindBy(xpath = "//button[.='Signup']")
    public WebElement SignUpPageSignUpButton;

    @FindBy(xpath = "//*[.='Enter Account Information']")
    public WebElement enterAccountInfoText;

    @FindBy(id = "id_gender1")
    public WebElement mrsMrSelectRadioBox;

    @FindBy(id = "newsletter")
    public WebElement newsletterCheckBox;

    @FindBy(id = "first_name")
    public WebElement registerPageFirstNameArea;

    @FindBy(id = "mobile_number")
    public WebElement registerPhoneNumberArea;

    @FindBy(xpath = "//*[.='Create Account']")
    public WebElement registerPageCreateAccButton;

    @FindBy(xpath = "//*[.='Account Created!']")
    public WebElement accCreatedText;

    @FindBy(xpath = "//*[.='Continue']")
    public WebElement accCretedContinueButton;

    @FindBy(xpath = "//*[text()=' Logged in as ']")
    public WebElement loggedInAsUser;

    @FindBy(xpath = "//*[.=' Delete Account']")
    public WebElement homePageDeleteAccButton;

    @FindBy(xpath = "//*[.='Account Deleted!']")
    public WebElement accDeletedText;

    @FindBy(xpath = "//a[.='Continue']")
    public WebElement deleteAccContinueButton;

    @FindBy(xpath = "//h2[@class='title text-center']")
    public WebElement featuresItemsText;

    @FindBy(xpath = "//*[text()='Login to your account']")
    public WebElement loginToYourAccText;

    @FindBy(xpath = "//*[@type='email']")
    public WebElement loginPageEmailArea;

    @FindBy(xpath = "//*[text()='Your email or password is incorrect!']")
    public WebElement loginErrorText;

    @FindBy(xpath = "//*[@class='fa fa-lock']")
    public WebElement logoutButton;

    @FindBy(xpath = "//*[text()=' Home']")
    public WebElement homePageHomeButton;

    @FindBy(xpath = "//*[text()='New User Signup!']")
    public WebElement newUserSignUpText;

    @FindBy(xpath = "//*[.='Email Address already exist!']")
    public WebElement alreadyExistText;

    @FindBy(xpath = "//i[@class='fa fa-envelope']")
    public WebElement contactUsButton;

    @FindBy(xpath = "//*[.='Get In Touch']")
    public WebElement getInTouchText;

    @FindBy(css = ".form-control")
    public WebElement contactUsNameInputArea;

    @FindBy(xpath = "(//input[@class='form-control'])[4]")
    public WebElement contactUsFileUpload;

    @FindBy(xpath = "//input[@class='btn btn-primary pull-left submit_form']")
    public WebElement contactUsSubmitButton;

    @FindBy(xpath = "//div[@class='status alert alert-success']")
    public WebElement contactUsSuccessMessage;

    @FindBy(xpath = "//i[@class='fa fa-angle-double-left']")
    public WebElement contactUsHomeButton;

    @FindBy(xpath = "//b[text()='Test Cases']")
    public WebElement testCasePageTestCaseText;

    @FindBy(css = ".material-icons.card_travel")
    public WebElement homePageProductsButton;

    @FindBy(xpath = "//input[@id='search_product']")
    public WebElement productsSearchArea;

    @FindBy(xpath = "//*[.='All Products']")
    public WebElement productsPageAllProductsText;

    @FindBy(xpath = "(//i[@class='fa fa-plus-square'])[1]")
    public WebElement firstProductsViewProductButton;

    @FindBy(xpath = "//*[.='Quantity:']")
    public WebElement productDetailsQuantity;

    @FindBy(xpath = "//*[.='Blue Top']")
    public WebElement productDetailsProductName;

    @FindBy(xpath = "//div[@class='product-information']//p")
    public WebElement productDetailsCategoryName;

    @FindBy(xpath = "//*[.='Rs. 500']")
    public WebElement productDetailsPrice;

    @FindBy(xpath = "//*[.='Availability:']")
    public WebElement productDetailsAvailability;

    @FindBy(xpath = "//*[.='Condition:']")
    public WebElement productDetailsCondition;

    @FindBy(xpath = "(//div[@class='product-information']//p)[4]")
    public WebElement productDetailsBrandName;

    @FindBy(id = "submit_search")
    public WebElement productSearchButton;

    @FindBy(xpath = "//h2[@class='title text-center']")
    public WebElement searchedProductText;

    @FindBy(xpath = "(//p)[3]")
    public WebElement searchedProductProductName;

    @FindBy(xpath = "(//span[@class='pull-right'])[8]")
    public WebElement brandBıba;

    @FindBy(xpath = "//*[.='Subscription']")
    public WebElement subscriptionText;

    @FindBy(id = "susbscribe_email")
    public WebElement subscriptionEmailInput;

    @FindBy(id = "subscribe")
    public WebElement subscriptionArrowButton;

    @FindBy(xpath = "//div[@class='alert-success alert']")
    public WebElement subscriptionVerifyAlert;

    @FindBy(xpath = "//*[.=' Cart']")
    public WebElement homePageCartButton;

    @FindBy(xpath = "//*[text()='Rs. 500']")
    public WebElement firstProductMove;

    @FindBy(xpath = "(//a[@class='btn btn-default add-to-cart'])[1]")
    public WebElement firstProductAddToCartButton;

    @FindBy(xpath = "//button[@class='btn btn-success close-modal btn-block']")
    public WebElement continueShoppingButton;

    @FindBy(xpath = "//*[text()='Rs. 400']")
    public WebElement secondProductMove;

    @FindBy(xpath = "(//a[@class='btn btn-default add-to-cart'])[3]")
    public WebElement secondProductAddToCartButton;

    @FindBy(xpath = "//*[text()='View Cart']")
    public WebElement viewCartButton;

    @FindBy(xpath = "//h4//a")
    public List<WebElement> productsInCart;

    @FindBy(xpath = "//h4//a")
    public WebElement productInCart;

    @FindBy(xpath = "(//td[@class='cart_price'])[1]")
    public WebElement blueTopPriceInCart;

    @FindBy(xpath = "(//td[@class='cart_price'])[2]")
    public WebElement menTshirtPriceInCart;

    @FindBy(xpath = "(//button[@class='disabled'])[1]")
    public WebElement firstProductQuantityInCart;

    @FindBy(xpath = "(//button[@class='disabled'])[2]")
    public WebElement secondProductQuantityInCart;

    @FindBy(xpath = "(//p[@class='cart_total_price'])[1]")
    public WebElement cartTotalPriceFirstProduct;

    @FindBy(xpath = "(//p[@class='cart_total_price'])[2]")
    public WebElement cartTotalPriceSecondProduct;

    @FindBy(xpath = "//input")
    public WebElement quantityAreaInViewProduct;

    @FindBy(xpath = "//button[@class='btn btn-default cart']")
    public WebElement addToCartButtonInViewProduct;

    @FindBy(xpath = "//li[@class='active']")
    public WebElement shoppingCartTextInCartPage;

    @FindBy(xpath = "//a[@class='btn btn-default check_out']")
    public WebElement proceedToCheckoutButton;

    @FindBy(xpath = "//div[@class='modal-content']//p")
    public WebElement registerLoginBeforeProceedText;

    @FindBy(xpath = "//*[text()='Register / Login']")
    public WebElement ProceedToCheckoutRegisterLoginButton;

    @FindBy(xpath = "//li[@class='address_firstname address_lastname']")
    public WebElement nameOnBill;

    @FindBy(xpath = "(//li[@class='address_address1 address_address2'])[1]")
    public WebElement componyNameOnBill;

    @FindBy(xpath = "(//li[@class='address_address1 address_address2'])[2]")
    public WebElement addressOnBill;

    @FindBy(xpath = "(//li[@class='address_city address_state_name address_postcode'])[1]")
    public WebElement stateCityZipOnBill;

    @FindBy(xpath = "//li[@class='address_phone']")
    public WebElement cellNumberOnBill;

    @FindBy(xpath = "//textarea[@class='form-control']")
    public WebElement commentAreaBeforePayment;

    @FindBy(name = "name_on_card")
    public WebElement paymentNameOnCard;

    @FindBy(name = "card_number")
    public WebElement paymentCardNumber;

    @FindBy(id = "submit")
    public WebElement payAndConfirmOrder;

    @FindBy(xpath = "(//div//p)[1]")
    public WebElement verifySuccessOrder;

    @FindBy(css = ".cart_quantity_delete")
    public WebElement xButtonInCart;

    @FindBy(xpath = "//span//p")
    public WebElement cartIsEmptyText;

    @FindBy(xpath = "//tr[@class='cart_menu']")
    public WebElement cartPageHeader;

    @FindBy(xpath = "//div[@class='left-sidebar']")
    public WebElement leftSideBarCategories;

    @FindBy(xpath = "(//i[@class='fa fa-plus'])[1]")
    public WebElement womenCategoriesSelection;

    @FindBy(xpath = "//*[text()='Dress ']")
    public WebElement dressSelectionAtWomenTab;

    @FindBy(xpath = "//h2[@class='title text-center']")
    public WebElement womenDressProductsTitle;

    @FindBy(xpath = "(//i[@class='fa fa-plus'])[2]")
    public WebElement menCategoriesSelection;

    @FindBy(xpath = "//*[text()='Tshirts ']")
    public WebElement tshirtSelectionAtMenTab;

    @FindBy(xpath = "//h2[@class='title text-center']")
    public WebElement menTshirtProductsTitle;

    @FindBy(xpath = "//div[@class='brands_products']")
    public WebElement brandsOnProducts;

    @FindBy(xpath = "//*[text()='Polo']")
    public WebElement brandPoloSelection;

    @FindBy(xpath = "//h2[@class='title text-center']")
    public WebElement brandPoloProductsTitle;

    @FindBy(css = "div > img")
    public List<WebElement> productSearchResults;

    @FindBy(xpath = "//*[text()='H&M']")
    public WebElement brandHMSelection;

    @FindBy(xpath = "//h2[@class='title text-center']")
    public WebElement brandHMProductsTitle;

    @FindBy(xpath = "(//*[text()='Blue Top'])[1]")
    public WebElement blueTopTextOnBanner;

    @FindBy(xpath = "//a[text()='Write Your Review']")
    public WebElement writeYourReviewText;

    @FindBy(id = "name")
    public WebElement writeYourReviewNameArea;

    @FindBy(id = "button-review")
    public WebElement writeYourReviewSubmitButton;

    @FindBy(xpath = "//span[text()='Thank you for your review.']")
    public WebElement writeYourReviewSuccessMessage;

    @FindBy(xpath = "//h2[text()='recommended items']")
    public WebElement recommendedItems;

    @FindBy(xpath = "(//a[@data-product-id='1']//i[@class='fa fa-shopping-cart'])[3]")
    public WebElement recommendedItemsBlueTopAddToCartButton;

    @FindBy(xpath = "(//i[@class='fa fa-angle-right'])[2]")
    public WebElement recommendedItemsNextPage;

    @FindBy(xpath = "//*[text()='Download Invoice']")
    public WebElement downloadInvoıceButton;

    @FindBy(xpath = "//a[@class='btn btn-primary']")
    public WebElement orderPlaceContinueButton;

    @FindBy(xpath = "//input[@data-qa='login-password']")
    public WebElement loginPagePasswordArea;

    @FindBy(xpath = "//button[@data-qa='login-button']")
    public WebElement loginPageLoginButton;

    @FindBy(xpath = "//div[@class='features_items']//div[@class='col-sm-4']")
    public List<WebElement> productsPageAllProducts;

    @FindBy(xpath = "//div[@class='productinfo text-center']//p")
    public List<WebElement> productsPageAllProductsName;

    @FindBy(xpath = "//div[@class='productinfo text-center']//h2")
    public List<WebElement> productsPageAllProductsPrice;

    @FindBy(xpath = "(//div[@class='productinfo text-center']//p)[1]")
    public WebElement productsPageFirstProductName;

    @FindBy(xpath = "(//div[@class='productinfo text-center']//h2)[1]")
    public WebElement productsPageFirstProductPrice;

    @FindBy(xpath = "//a[text()='View Product']")
    public List<WebElement> allProductsViewProductButton;

    @FindBy(xpath = "//div[@class='modal-content']//h4")
    public WebElement addToCartAddedText;

    @FindBy(xpath = "//div[@class='step-one']//h2")
    public WebElement checkoutAddressDetailsText;

    @FindBy(xpath = "//a[text()='Place Order']")
    public WebElement placeOrderButton;

    @FindBy(xpath = "//input[@name='cvc']")
    public WebElement paymentCardCvc;

    @FindBy(css = ".form-control.card-expiry-month")
    public WebElement paymentCardExpiryMonth;

    @FindBy(css = ".form-control.card-expiry-year")
    public WebElement paymentCardExpiryYear;
}
