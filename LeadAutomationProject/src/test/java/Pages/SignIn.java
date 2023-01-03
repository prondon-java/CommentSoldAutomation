package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.CommonMethods;
import Utilities.Driver;

public class SignIn {

	public SignIn() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(xpath="//input[@id='email']")
	public WebElement emailAddressInputField;
	
	@FindBy(xpath="//input[@id='password']")
	public WebElement passwordInputField;
	
	@FindBy(xpath="//button[text()='Log In']")
	public WebElement logInButton;
	
	@FindBy(xpath="//a[@class='sign-up-link']")
	public WebElement signUpLink;
	
	@FindBy(xpath="//*[@class='alert alert-danger']")
	public WebElement alertMessage;
	
	public void signIn(String email, String password) {
		CommonMethods.inputText(emailAddressInputField, email);
		CommonMethods.inputText(passwordInputField, password);
		CommonMethods.click(logInButton);
	}
}
