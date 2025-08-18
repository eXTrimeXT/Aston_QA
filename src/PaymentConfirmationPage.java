import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.asserts.Assertion;

import java.util.List;

public class PaymentConfirmationPage extends BasePage {
    @FindBy(xpath = "/html/body/div[8]/div/iframe")
    private WebElement confirmationTitle;


    @FindBy(xpath = "//div[contains(@class, 'confirmation__description')]//span")
    private List<WebElement> confirmationDetails;

    @FindBy(xpath = "//button[contains(@class, 'confirmation__button')]")
    private WebElement payButton;

    @FindBy(xpath = "//form[contains(@class, 'card-form')]//input")
    private List<WebElement> cardInputs;

    @FindBy(xpath = "//div[contains(@class, 'card__payment-systems')]//span")
    private List<WebElement> paymentIcons;

    @FindBy(xpath = "//input[@type='checkbox' and contains(@id, 'card-save')]")
    private WebElement saveCardCheckbox;

    @FindBy(xpath = "//label[contains(@for, 'card-save')]")
    private WebElement saveCardLabel;

    public PaymentConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public boolean isConfirmationPageDisplayed() {
        return isElementDisplayed(confirmationTitle);
    }

    public boolean isPhoneNumberDisplayed(String phone) {
        for (WebElement detail : confirmationDetails) {
            if (detail.getText().contains(phone)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAmountDisplayed(String amount) {
        for (WebElement detail : confirmationDetails) {
            if (detail.getText().contains(amount)) {
                return true;
            }
        }
        return false;
    }

    public boolean isPayButtonDisplayed() {
        return isElementDisplayed(payButton);
    }

    public boolean isPayButtonAmountCorrect(String amount) {
        return payButton.getText().contains(amount);
    }

    public List<WebElement> getCardInputs() {
        return cardInputs;
    }

    public List<WebElement> getPaymentIcons() {
        return paymentIcons;
    }

    public boolean isSaveCardCheckboxDisplayed() {
        return isElementDisplayed(saveCardCheckbox);
    }

    public String getSaveCardLabelText() {
        return saveCardLabel.getText();
    }
}