package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.CommonMethods;
import Utilities.Driver;

public class Account {

	public Account() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(xpath="//h1[@id='modalLabel' and text()='Welcome!']")
	public WebElement modalTitleRegistrationConfirmation;
	
	@FindBy(xpath="//*[@class='email-input-containers']/input")
	public WebElement emailInputField;
	
	@FindBy(xpath="//*[@class='default-input coupon-input']")
	public WebElement nameInputField;
	
	@FindBy(xpath="//button[@id='pink-button']")
	public WebElement confirmButton;
	
	@FindBy(xpath="//*[@class='welcome']")
	public WebElement welcomeMessage;
	
	@FindBy(xpath="//*[text()='Email Address']/following-sibling::div")
	public WebElement userEmailAddress;
	
	@FindBy(xpath="//input[@id='one']")
	public WebElement deliveryRadioButton;
	
	@FindBy(xpath="//input[@autocomplete='address-line1']")
	public WebElement streetAddressInputField;
	
	@FindBy(xpath="//input[@id='locality']")
	public WebElement cityInputField;
	
	@FindBy(xpath="//select[@name='state']")
	public WebElement stateDropdown;
	
	@FindBy(xpath="//input[@autocomplete='postal-code']")
	public WebElement zipCodeInputField;
	
	@FindBy(xpath="//button[@id='save-button']")
	public WebElement saveAddressButton;
	
	@FindBy(xpath="//*[@id='credit-card']")
	public WebElement creditCardRadioButton;

	@FindBy(xpath="//*[@id=\"card-element\"]//iframe")
	public WebElement creditCardIframe;
	
	@FindBy(xpath="//input[@name='cardnumber']")
	public WebElement creditCardNumberInputField;
	
	@FindBy(xpath="//input[@name='exp-date']")
	public WebElement creditCardExpirationDateInputField;
	
	@FindBy(xpath="//input[@name='cvc']")
	public WebElement creditCardCVCInputField;
	
	@FindBy(xpath="//input[@name='postal']")
	public WebElement creditCardZipCodeInputField;
	
	@FindBy(xpath="//button[@id='stripe-button']")
	public WebElement addCardButton;
	
	@FindBy(xpath="//*[contains(text(),'Shipping Address')]/../..//div[contains(text(),'Add ')]")
	public WebElement shippingAddressAddButton;
	
	@FindBy(xpath="//*[contains(text(),'Shipping Address')]/following-sibling::div")
	public WebElement shippingAddressDisplayedTop;
	
	@FindBy(xpath="//h1[text()='Shop']")
	public WebElement shopTitle;
	
	@FindBy(xpath="//*[text()='Fuzzy Throw Blanket *Final Sale*']/..//*[contains(text(),'Add To Cart')]")
	public WebElement blanketAddToCartButton;
	
	@FindBy(xpath="//*[text()='You Look Good Bath Mat']/..//*[contains(text(),'Add To Cart')]")
	public WebElement bathMatAddToCartButton;
	
	@FindBy(xpath="//*[@id='save-button']")
	public WebElement selectOptionAddToCartButton;
	
	@FindBy(xpath="//button[@class='_cta-button']")
	public WebElement payWithCardButton;

	@FindBy(xpath="//h1[contains(text(),'Order Confirmation')]")
	public WebElement modalTitleOrderConfirmation;
	
	@FindBy(xpath="//button[contains(text(),'Close')]")
	public WebElement orderConfirmationCloseButton;
	
	@FindBy(xpath="//span[contains(text(),'View Orders')]")
	public WebElement viewOrdersLink;
	
	@FindBy(xpath="//button[text()='Add Code ']")
	public WebElement addCodeButton;
	
	@FindBy(xpath="//*[@class='default-input coupon-input']")
	public WebElement couponCodeInputField;
	
	@FindBy(xpath="//button[text()=' Apply ']")
	public WebElement applyCodeButton;
	
	@FindBy(xpath="//button[text()='Edit Code ']")
	public WebElement editCodeButton;
	
	@FindBy(xpath="//h2[contains(text(),'Coupon Code')]")
	public WebElement displayedCouponCode;
	
	@FindBy(xpath="//span[text()='Total']/following-sibling::span")
	public WebElement summaryTotal;
	
	@FindBy(xpath="//span[text()=' Logout ']")
	public WebElement logoutLink;
	
	public void addDeliveryInformation(String streetAddress, String city, String state, String zipCode) {
		CommonMethods.waitUntilVisible(deliveryRadioButton);
		CommonMethods.click(deliveryRadioButton);
		CommonMethods.waitUntilVisible(streetAddressInputField);
		CommonMethods.inputText(streetAddressInputField, streetAddress);
		CommonMethods.inputText(cityInputField, city);
		CommonMethods.selectByValue(stateDropdown, state);
		CommonMethods.inputText(zipCodeInputField, zipCode);
		CommonMethods.click(saveAddressButton);
	}
	
	public void addCreditCardInformation(String ccNumber, String expirationDate, String cvc, String zipCode) {
		CommonMethods.click(creditCardRadioButton);
		CommonMethods.waitUntilVisible(creditCardIframe);
		CommonMethods.switchToIframeByWebElement(creditCardIframe);
		CommonMethods.inputText(creditCardNumberInputField, ccNumber);
		CommonMethods.inputText(creditCardExpirationDateInputField, expirationDate);
		CommonMethods.inputText(creditCardCVCInputField, cvc);
		CommonMethods.inputText(creditCardZipCodeInputField, zipCode);
		CommonMethods.switchToDefaultContent();
		CommonMethods.click(addCardButton);
	}
	
	public void addCouponCode(String couponCode) {
		CommonMethods.waitUntilVisible(addCodeButton);
		CommonMethods.click(addCodeButton);
		CommonMethods.waitUntilVisible(couponCodeInputField);
		CommonMethods.inputText(couponCodeInputField, couponCode);
		CommonMethods.click(applyCodeButton);
		CommonMethods.waitUntilVisible(editCodeButton);
	}
	
	public void addItemToCart(WebElement element) {
		CommonMethods.waitUntilVisible(element);
		CommonMethods.click(element);
		CommonMethods.waitUntilVisible(selectOptionAddToCartButton);
		CommonMethods.click(selectOptionAddToCartButton);
	}
	
	public void clickPayWithCardButtonAndOrderConfirmationCloseButton() {
		CommonMethods.waitUntilVisible(payWithCardButton);
		CommonMethods.click(payWithCardButton);
		CommonMethods.waitUntilVisible(modalTitleOrderConfirmation);
		CommonMethods.click(orderConfirmationCloseButton);
	}
}
