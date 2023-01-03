package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.CommonMethods;
import Utilities.Driver;

public class Registration {

	public Registration() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(xpath="//input[@id='customer-first-name']")
	public WebElement firstNameInputField;
	
	@FindBy(xpath="//input[@id='customer-last-name']")
	public WebElement lastNameInputField;

	@FindBy(xpath="//input[@id='customer-email']")
	public WebElement emailInputField;
	
	@FindBy(xpath="//input[@id='customer-password']")
	public WebElement passwordInputField;

	@FindBy(xpath="//button[contains(text(),'Create Account')]")
	public WebElement createAccountButton;
	
	@FindBy(xpath="//a[contains(text(),'Sign In')]")
	public WebElement signInLink;
	
	@FindBy(xpath="//*[@class='alert alert-danger']")
	public WebElement alertMessage;
	
	public void registerNewUser(String firstName, String lastName, String email, String password) {
		CommonMethods.inputText(firstNameInputField, firstName);
		CommonMethods.inputText(lastNameInputField, lastName);
		CommonMethods.inputText(emailInputField, email);
		CommonMethods.inputText(passwordInputField, password);
		CommonMethods.click(createAccountButton);
	}
}
