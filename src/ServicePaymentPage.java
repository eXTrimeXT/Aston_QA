import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ServicePaymentPage extends BasePage {
    @FindBy(xpath = "//span[@class='select__now' and text()='Услуги связи']")
    private WebElement selectedService;

    @FindBy(id = "connection-phone")
    private WebElement phoneInput;

    @FindBy(id = "connection-sum")
    private WebElement sumInput;

    @FindBy(xpath = "//*[@id='pay-connection']/button")
    private WebElement continueButton;

    public ServicePaymentPage(WebDriver driver) {
        super(driver);
    }

    public boolean isServiceConnectionSelected() {
        return isElementDisplayed(selectedService);
    }

    public void enterPhoneNumber(String phone) {
        type(phoneInput, phone);
    }

    public void enterAmount(String amount) {
        type(sumInput, amount);
    }

    public void clickContinueButton() {
        click(continueButton);
    }

    public boolean isContinueButtonEnabled() {
        return continueButton.isEnabled();
    }

    public String getContinueButtonText() {
        return continueButton.getText();
    }
}