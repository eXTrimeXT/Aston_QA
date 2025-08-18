import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class HomePage extends BasePage {
    @FindBy(xpath = "//*[@id='pay-section']/div/div/div[2]/section/div/h2")
    private WebElement blockTitle;

    @FindBy(xpath = "//div[@class='pay__partners']//img")
    private List<WebElement> paymentSystemLogos;

    @FindBy(xpath = "//a[contains(text(), 'Подробнее о сервисе')]")
    private WebElement detailsLink;

    @FindBy(xpath = "//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[1]/div[1]/div[2]/button")
    private WebElement paymentOptionsDropdown;

    @FindBy(xpath = "//*[@id='pay-section']/div/div/div[2]/section/div/div[1]/div[1]/div[2]/ul/li")
    private List<WebElement> paymentOptionsList;

    @FindBy(xpath = "//form[contains(@class, 'pay-form') and contains(@class, 'opened')]//input")
    private List<WebElement> paymentFormInputs;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getBlockTitleText() {
        return blockTitle.getText();
    }

    public List<WebElement> getPaymentSystemLogos() {
        return paymentSystemLogos;
    }

    public WebElement getDetailsLink() {
        return detailsLink;
    }

    public void selectPaymentOption(String optionName) {
        click(paymentOptionsDropdown);
        for (WebElement option : paymentOptionsList) {
            if (option.getText().equals(optionName)) {
                click(option);
                break;
            }
        }
    }

    public List<WebElement> getPaymentFormInputs() {
        return paymentFormInputs;
    }
}