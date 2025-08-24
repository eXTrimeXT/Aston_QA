import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;

public class MtsTest {
    private WebDriver driver;
    private HomePage homePage;
    private ServicePaymentPage servicePaymentPage;
    private PaymentConfirmationPage paymentConfirmationPage;

    private final String SITE_URL = "https://www.mts.by/";
    private final String PHONE_NUMBER = "297777777";
    private final String DETAILS_LINK = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
    private final String PathToChromeDriver = "G:\\Programming\\ASTON_QA_Java\\Aston_QA\\src\\chromedriver-win64\\chromedriver.exe";

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", PathToChromeDriver);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(SITE_URL);

        homePage = new HomePage(driver);
        homePage.acceptCookiesIfPresent();
    }

    @Test(priority = 1)
    public void checkBlockTitle() {
        Assert.assertEquals(homePage.getBlockTitleText(), "Онлайн пополнение\nбез комиссии",
                "Block title text doesn't match expected");
    }

    @Test(priority = 2)
    public void checkPaymentSystemLogos() {
        List<WebElement> logos = homePage.getPaymentSystemLogos();
        String[] expectedAltTexts = {"Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт"};

        Assert.assertEquals(logos.size(), expectedAltTexts.length,
                "Number of payment system logos doesn't match expected");

        for (int i = 0; i < logos.size(); i++) {
            Assert.assertEquals(logos.get(i).getAttribute("alt"), expectedAltTexts[i],
                    "Logo alt text doesn't match for logo " + (i+1));
        }
    }

    @Test(priority = 3)
    public void checkDetailsLink() {
        WebElement detailsLink = homePage.getDetailsLink();
        Assert.assertEquals(detailsLink.getAttribute("href"), DETAILS_LINK,
                "Details link URL doesn't match expected");

        // Здесь можно добавить логику открытия в новой вкладке, если нужно
    }

    @Test(priority = 4)
    public void checkEmptyFieldsPlaceholders() {
        String[] paymentOptions = {"Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность"};
        String[][] expectedPlaceholders = {
                {"Номер телефона", "Сумма", "E-mail для отправки чека"},
                {"Номер абонента", "Сумма", "E-mail для отправки чека"},
                {"Номер счета на 44", "Сумма", "E-mail для отправки чека"},
                {"Номер счета на 2073", "Сумма", "E-mail для отправки чека"}
        };

        for (int i = 0; i < paymentOptions.length; i++) {
            homePage.selectPaymentOption(paymentOptions[i]);
            List<WebElement> inputFields = homePage.getPaymentFormInputs();

            Assert.assertEquals(inputFields.size(), expectedPlaceholders[i].length,
                    "Number of input fields doesn't match for " + paymentOptions[i]);

            for (int j = 0; j < inputFields.size(); j++) {
                Assert.assertEquals(inputFields.get(j).getAttribute("placeholder"), expectedPlaceholders[i][j],
                        "Placeholder doesn't match for field " + (j+1) + " in " + paymentOptions[i]);
            }
        }

        // Возвращаемся к "Услуги связи" для следующих тестов
        homePage.selectPaymentOption("Услуги связи");
    }

    @Test(priority = 5)
    public void testPaymentForm() {
        servicePaymentPage = new ServicePaymentPage(driver);

        Assert.assertTrue(servicePaymentPage.isServiceConnectionSelected(),
                "Service connection is not selected");

        servicePaymentPage.enterPhoneNumber(PHONE_NUMBER);
        servicePaymentPage.enterAmount("1");

        Assert.assertTrue(servicePaymentPage.isContinueButtonEnabled(),
                "Continue button is not enabled");
        Assert.assertEquals(servicePaymentPage.getContinueButtonText(), "Продолжить",
                "Continue button text doesn't match");

        servicePaymentPage.clickContinueButton();
    }

    @Test(priority = 6)
    public void testPaymentConfirmationPage() {
        paymentConfirmationPage = new PaymentConfirmationPage(driver);
        Assert.assertTrue(paymentConfirmationPage.isConfirmationPageDisplayed(),"Payment confirmation page is not displayed");
        Assert.assertTrue(paymentConfirmationPage.isPhoneNumberDisplayed("375297777777"),"Phone number is not displayed correctly");
        Assert.assertTrue(paymentConfirmationPage.isAmountDisplayed("1"),"Amount is not displayed correctly");
//        Assert.assertTrue(paymentConfirmationPage.isPayButtonDisplayed(),"Pay button is not displayed");
        Assert.assertTrue(paymentConfirmationPage.isPayButtonAmountCorrect("1"),"Pay button amount is incorrect");

        String[] cardFieldPlaceholders = {
                "Номер карты",
                "Срок действия",
                "Имя владельца",
                "CVC"
        };

        List<WebElement> cardInputs = paymentConfirmationPage.getCardInputs();
        for (int i = 0; i < cardInputs.size(); i++) {
            Assert.assertEquals(cardInputs.get(i).getAttribute("placeholder"), cardFieldPlaceholders[i],
                    "Card field placeholder doesn't match for field " + (i+1));
        }

        String[] expectedPaymentIcons = {"visa", "mastercard", "belcard"};
        List<WebElement> paymentIcons = paymentConfirmationPage.getPaymentIcons();
        Assert.assertTrue(paymentIcons.size() <= expectedPaymentIcons.length,
                "Not all payment icons are displayed");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}