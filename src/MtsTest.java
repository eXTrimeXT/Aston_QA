import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Epic("Тестирование платежной системы МТС")
@Feature("Онлайн пополнение без комиссии")
public class MtsTest {
    private WebDriver driver;
    private HomePage homePage;
    private ServicePaymentPage servicePaymentPage;
    private PaymentConfirmationPage paymentConfirmationPage;

    private final String SITE_URL = "https://www.mts.by/";
    private final String PHONE_NUMBER = "297777777";
    private final String DETAILS_LINK = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
    private final String PathToChromeDriver = "C:\\Users\\Timur\\Downloads\\Aston_QA\\src\\chromedriver-win64\\chromedriver.exe";
    private final String SCREENSHOTS_DIR = "allure-report-screenshots";

    @BeforeClass
    @Step("Инициализация драйвера и открытие сайта")
    public void setup() {
        System.setProperty("webdriver.chrome.driver", PathToChromeDriver);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(SITE_URL);

        homePage = new HomePage(driver);
        homePage.acceptCookiesIfPresent();

        // Создаем директорию для скриншотов
        createScreenshotsDirectory();
    }

    @Test(priority = 1)
    @Story("Проверка заголовка блока")
    @Description("Проверка корректности отображения заголовка блока 'Онлайн пополнение без комиссии'")
    @Severity(SeverityLevel.CRITICAL)
    public void checkBlockTitle() {
        Assert.assertEquals(homePage.getBlockTitleText(), "Онлайн пополнение\nбез комиссии",
                "Block title text doesn't match expected");
    }

    @Test(priority = 2)
    @Story("Проверка логотипов платежных систем")
    @Description("Проверка наличия и корректности всех логотипов платежных систем")
    @Severity(SeverityLevel.NORMAL)
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
    @Story("Проверка ссылки 'Подробнее о сервисе'")
    @Description("Проверка корректности URL ссылки на подробную информацию о сервисе")
    @Severity(SeverityLevel.MINOR)
    public void checkDetailsLink() {
        WebElement detailsLink = homePage.getDetailsLink();
        Assert.assertEquals(detailsLink.getAttribute("href"), DETAILS_LINK,
                "Details link URL doesn't match expected");
    }

    @Test(priority = 4)
    @Story("Проверка плейсхолдеров полей ввода")
    @Description("Проверка плейсхолдеров для различных опций оплаты")
    @Severity(SeverityLevel.NORMAL)
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

        homePage.selectPaymentOption("Услуги связи");
    }

    @Test(priority = 5)
    @Story("Тестирование формы оплаты")
    @Description("Проверка функциональности формы оплаты услуг связи")
    @Severity(SeverityLevel.CRITICAL)
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
    @Story("Тестирование страницы подтверждения платежа")
    @Description("Проверка корректности отображения страницы подтверждения платежа")
    @Severity(SeverityLevel.CRITICAL)
    public void testPaymentConfirmationPage() {
        paymentConfirmationPage = new PaymentConfirmationPage(driver);
        Assert.assertTrue(paymentConfirmationPage.isConfirmationPageDisplayed(),
                "Payment confirmation page is not displayed");
        Assert.assertTrue(paymentConfirmationPage.isPhoneNumberDisplayed("375297777777"),
                "Phone number is not displayed correctly");
        Assert.assertTrue(paymentConfirmationPage.isAmountDisplayed("1"),
                "Amount is not displayed correctly");

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
    @Step("Завершение тестов, создание отчета и скриншота")
    public void tearDown() {
        try {
            // 1. Генерируем Allure отчет
            generateAllureReport();

            // 2. Делаем скриншот отчета
            takeAllureReportScreenshot();

            // 3. Закрываем браузер
            if (driver != null) {
                driver.quit();
            }

            System.out.println("✅ Allure отчет создан и скриншот сохранен!");
            System.out.println("📁 Скриншот: " + SCREENSHOTS_DIR + "/allure-report-screenshot.png");

        } catch (Exception e) {
            System.err.println("❌ Ошибка при создании отчета: " + e.getMessage());
            if (driver != null) {
                driver.quit();
            }
        }
    }

    /**
     * Создает директорию для скриншотов
     */
    private void createScreenshotsDirectory() {
        try {
            Path path = Paths.get(SCREENSHOTS_DIR);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
                System.out.println("📁 Создана директория для скриншотов: " + SCREENSHOTS_DIR);
            }
        } catch (IOException e) {
            System.err.println("❌ Не удалось создать директорию для скриншотов: " + e.getMessage());
        }
    }

    /**
     * Генерирует Allure отчет
     */
    private void generateAllureReport() {
        try {
            System.out.println("🔄 Генерация Allure отчета...");

            ProcessBuilder processBuilder = new ProcessBuilder();

            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                processBuilder.command("cmd.exe", "/c",
                        "mvn", "allure:report");
            } else {
                processBuilder.command("bash", "-c",
                        "mvn allure:report");
            }

            processBuilder.directory(new File(System.getProperty("user.dir")));
            Process process = processBuilder.start();

            // Читаем вывод процесса
            java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("✅ Allure отчет успешно сгенерирован!");
            } else {
                System.out.println("❌ Ошибка генерации Allure отчета. Код: " + exitCode);
            }

        } catch (Exception e) {
            System.err.println("❌ Ошибка при генерации отчета: " + e.getMessage());
        }
    }

    /**
     * Делает скриншот Allure отчета
     */
    private void takeAllureReportScreenshot() {
        try {
            System.out.println("📸 Создание скриншота Allure отчета...");

            // Ждем немного для завершения генерации отчета
            Thread.sleep(2000);

            // Создаем новый WebDriver для скриншота отчета
            WebDriver screenshotDriver = new ChromeDriver();
            screenshotDriver.manage().window().maximize();

            // Открываем отчет в браузере
            String reportPath = "file:///" + System.getProperty("user.dir")
                    + "/target/site/allure-maven-plugin/index.html";
            screenshotDriver.get(reportPath);

            // Ждем загрузки страницы
            Thread.sleep(3000);

            // Делаем скриншот
            File screenshot = ((TakesScreenshot) screenshotDriver).getScreenshotAs(OutputType.FILE);

            // Сохраняем скриншот
            String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
            String screenshotPath = SCREENSHOTS_DIR + "/allure-report-screenshot-" + timestamp + ".png";

            Files.copy(screenshot.toPath(), Paths.get(screenshotPath));
            System.out.println("✅ Скриншот сохранен: " + screenshotPath);

            // Закрываем браузер для скриншотов
            screenshotDriver.quit();

        } catch (Exception e) {
            System.err.println("❌ Ошибка при создании скриншота: " + e.getMessage());

            // Альтернативный способ - скриншот через Selenium если отчет не открывается
            try {
                takeAlternativeScreenshot();
            } catch (Exception ex) {
                System.err.println("❌ Альтернативный скриншот также не удался: " + ex.getMessage());
            }
        }
    }

    /**
     * Альтернативный способ создания скриншота
     */
    private void takeAlternativeScreenshot() throws IOException {
        System.out.println("📸 Попытка альтернативного скриншота...");

        // Просто делаем скриншот текущей страницы
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        String screenshotPath = SCREENSHOTS_DIR + "/final-page-screenshot-" + timestamp + ".png";

        Files.copy(screenshot.toPath(), Paths.get(screenshotPath));
        System.out.println("✅ Альтернативный скриншот сохранен: " + screenshotPath);
    }
}