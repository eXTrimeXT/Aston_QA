import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

public class MtsTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String SITE_URL = "https://www.mts.by/";
    private final String PHONE_NUMBER = "297777777";
    private final String DETAILS_LINK = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "G:\\Programming\\ASTON_QA_Java\\Aston_QA\\src\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.get(SITE_URL);
        acceptCookiesIfPresent();
    }

    private void acceptCookiesIfPresent() {
        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Принять')]")));
            acceptCookies.click();
        } catch (Exception e) {
            System.out.println("Cookie acceptance button not found or not clickable");
        }
    }

    @Test(priority = 1)
    public void checkBlockTitle() {
        WebElement blockTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='pay-section']/div/div/div[2]/section/div/h2"))
        );
        Assert.assertEquals(blockTitle.getText(), "Онлайн пополнение\nбез комиссии",
                "Block title text doesn't match expected");

    }

    @Test(priority = 2)
    public void checkPaymentSystemLogos() {
        List<WebElement> logos = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//div[@class='pay__partners']//img"))
        );

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
        WebElement detailsLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(), 'Подробнее о сервисе')]"))
        );

        Assert.assertEquals(detailsLink.getAttribute("href"), DETAILS_LINK, "Details link URL doesn't match expected");

        // Сохраняем текущую вкладку
        String originalWindow = driver.getWindowHandle();

        // Используем Actions для открытия ссылки в новой вкладке
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL)
                .click(detailsLink)
                .keyUp(Keys.CONTROL)
                .build()
                .perform();

        // Ждем появления новой вкладки
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // Переключаемся на новую вкладку
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.equals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        // Проверяем URL в новой вкладке
        wait.until(ExpectedConditions.urlToBe(DETAILS_LINK));
        Assert.assertEquals(driver.getCurrentUrl(), DETAILS_LINK, "Details link doesn't lead to correct page");

        // Закрываем новую вкладку и возвращаемся к исходной
        driver.close();
        driver.switchTo().window(originalWindow);
    }

    @Test(priority = 4)
    public void testPaymentForm() {
        // Проверяем, что выбрана "Услуги связи" по умолчанию
        WebElement selectedService = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@class='select__now' and text()='Услуги связи']"))
        );
        Assert.assertTrue(selectedService.isDisplayed(), "Service connection is not selected by default");

        // Вводим номер телефона
        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='connection-phone']"))
        );
        phoneInput.clear();
        phoneInput.sendKeys(PHONE_NUMBER);

        // Вводим сумму (минимальная сумма для проверки)
        WebElement sumInput = driver.findElement(By.xpath("//input[@id='connection-sum']"));
        sumInput.clear();
        sumInput.sendKeys("1");

        // Проверяем кнопку "Продолжить"
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='pay-connection']/button"))
        );
        Assert.assertTrue(continueButton.isEnabled(), "Continue button is not enabled");
        Assert.assertEquals(continueButton.getText(), "Продолжить", "Continue button text doesn't match");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}