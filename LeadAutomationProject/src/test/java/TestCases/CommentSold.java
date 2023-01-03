package TestCases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Utilities.CommonMethods;
import Utilities.Driver;
import Utilities.Reader;

public class CommentSold extends CommonMethods{

	@BeforeMethod
	public void setUp() {
		Driver.getDriver();
	}

	@Test
	public void validateRegistration() throws InterruptedException {
		//Register New User
		String randomEmail = CommonMethods.getRandomEmail("commentsold.com");
		registrationPage.registerNewUser(Reader.getValue("firstName"), Reader.getValue("lastName"), randomEmail, Reader.getValue("password"));
		//Validate Welcome Message and User Information in Registration Confirmation Pop-up
		CommonMethods.waitUntilVisible(accountPage.modalTitleRegistrationConfirmation);
		Assert.assertEquals(CommonMethods.getText(accountPage.modalTitleRegistrationConfirmation), "Welcome!");
		Assert.assertEquals(CommonMethods.getAttribute(accountPage.emailInputField), randomEmail);
		Assert.assertTrue(CommonMethods.getAttribute(accountPage.nameInputField).contains(Reader.getValue("firstName")));
		Assert.assertTrue(CommonMethods.getAttribute(accountPage.nameInputField).contains(Reader.getValue("lastName")));
		CommonMethods.click(accountPage.confirmButton);
		//Validate Welcome Message and User Information in Account Page
		Assert.assertEquals(CommonMethods.getText(accountPage.welcomeMessage), "Welcome Back, " + Reader.getValue("firstName"));
		Assert.assertEquals(CommonMethods.getText(accountPage.userEmailAddress), randomEmail);
	}
	
	@Test
	public void registerWithPasswordInvalidLength() throws InterruptedException {
		registrationPage.registerNewUser(Reader.getValue("firstName"), Reader.getValue("lastName"), Reader.getValue("unregisteredEmail"), Reader.getValue("passwordInvalidLength"));
		//Validate Error Message
		Assert.assertEquals(CommonMethods.getText(registrationPage.alertMessage), "The pasword must be at least 8 characters.");
	}
	
	@Test
	public void registerWithPasswordInvalidFormat() throws InterruptedException {
		registrationPage.registerNewUser(Reader.getValue("firstName"), Reader.getValue("lastName"), Reader.getValue("unregisteredEmail"), Reader.getValue("passwordInvalidFormat"));
		//Validate Error Message
		Assert.assertEquals(CommonMethods.getText(registrationPage.alertMessage), "The pasword format is invalid.");
	}
	
	@Test
	public void validateSignIn() throws InterruptedException {
		//Navigate to Sign In Page
		CommonMethods.click(registrationPage.signInLink);
		//User Signs In with Valid Credentials
		signInPage.signIn(Reader.getValue("registeredEmail"), Reader.getValue("password"));
		//Validate Successful Sign In
		Assert.assertEquals(CommonMethods.getText(accountPage.welcomeMessage), "Welcome Back, " + Reader.getValue("firstName"));
		Assert.assertEquals(CommonMethods.getText(accountPage.userEmailAddress), Reader.getValue("registeredEmail"));
	}
	
	@Test
	public void signInWithUnregisteredEmail() throws InterruptedException {
		//Navigate to Sign In Page
		CommonMethods.click(registrationPage.signInLink);
		//User Attempts Sign In with Unregistered Email
		signInPage.signIn(Reader.getValue("unregisteredEmail"), Reader.getValue("password"));
		//Validate Error Message
		Assert.assertEquals(CommonMethods.getText(signInPage.alertMessage), "Sorry. There is no account registered with that email, but you can sign up to create one.");
	}
	
	@Test
	public void signInWithInvalidPassword() throws InterruptedException {
		//Navigate to Sign In Page
		CommonMethods.click(registrationPage.signInLink);
		//User Attempts Sign In with Invalid Password
		signInPage.signIn(Reader.getValue("registeredEmail"), Reader.getValue("passwordInvalidLength"));
		//Validate Error Message
		Assert.assertEquals(CommonMethods.getText(signInPage.alertMessage), "The password you've provided is incorrect for this email.");
	}
	
	@Test
	public void validateRegistrationToCheckOut() throws Exception {
		//Register New User
		String randomEmail = CommonMethods.getRandomEmail("commentsold.com");
		registrationPage.registerNewUser(Reader.getValue("firstName"), Reader.getValue("lastName"), randomEmail, Reader.getValue("password"));
		//Validate Welcome Message and User Information in Registration Confirmation Pop-up
		CommonMethods.waitUntilVisible(accountPage.modalTitleRegistrationConfirmation);
		Assert.assertEquals(CommonMethods.getText(accountPage.modalTitleRegistrationConfirmation), "Welcome!");
		Assert.assertEquals(CommonMethods.getAttribute(accountPage.emailInputField), randomEmail);
		Assert.assertTrue(CommonMethods.getAttribute(accountPage.nameInputField).contains(Reader.getValue("firstName")));
		Assert.assertTrue(CommonMethods.getAttribute(accountPage.nameInputField).contains(Reader.getValue("lastName")));
		CommonMethods.click(accountPage.confirmButton);
		//Validate Welcome Message and User Information in Account Page
		Assert.assertEquals(CommonMethods.getText(accountPage.welcomeMessage), "Welcome Back, " + Reader.getValue("firstName"));
		Assert.assertEquals(CommonMethods.getText(accountPage.userEmailAddress), randomEmail);
		//Add User's Delivery Information
		accountPage.addDeliveryInformation(Reader.getValue("streetAddress"), Reader.getValue("city"), Reader.getValue("state"), Reader.getValue("zipCode"));
		//Validate Shipping Address at Top of Account Page
		CommonMethods.waitUntilVisible(accountPage.shippingAddressDisplayedTop);
		Assert.assertTrue(CommonMethods.getText(accountPage.shippingAddressDisplayedTop).contains(Reader.getValue("streetAddress")));
		Assert.assertTrue(CommonMethods.getText(accountPage.shippingAddressDisplayedTop).contains(Reader.getValue("city")));
		Assert.assertTrue(CommonMethods.getText(accountPage.shippingAddressDisplayedTop).contains(Reader.getValue("state")));
		//Add Item to Cart
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,2000)", "");
		accountPage.addItemToCart(accountPage.blanketAddToCartButton);
		//Save Summary Total
		CommonMethods.waitUntilVisible(accountPage.summaryTotal);
		String summaryTotal = CommonMethods.getText(accountPage.summaryTotal);
		//Add Credit Card Information
		accountPage.addCreditCardInformation(Reader.getValue("ccNumber"), Reader.getValue("expirationDate"), Reader.getValue("cvc"), Reader.getValue("zipCode"));
		//Pay For Item
		accountPage.clickPayWithCardButtonAndOrderConfirmationCloseButton();
		//Navigate to Orders Page
		CommonMethods.click(accountPage.viewOrdersLink);
		CommonMethods.waitUntilVisible(ordersPage.ordersPageTitle);
		//Validate Number of Orders
		Assert.assertEquals(CommonMethods.numberOfElements(ordersPage.individualOrders), 1);
		//Validate Order Information
		Assert.assertEquals(CommonMethods.getText(ordersPage.titleOfFirstItemInMostRecentOrder), "Fuzzy Throw Blanket *Final Sale*");
		Assert.assertEquals(CommonMethods.getText(ordersPage.streetAddressOfMostRecentOrder), Reader.getValue("streetAddress"));
		Assert.assertTrue(CommonMethods.getText(ordersPage.cityAndStateOfMostRecentOrder).contains(Reader.getValue("city")));
		Assert.assertTrue(CommonMethods.getText(ordersPage.cityAndStateOfMostRecentOrder).contains(Reader.getValue("state")));
		Assert.assertEquals(CommonMethods.getText(ordersPage.totalOfMostRecentOrder), summaryTotal);
		Assert.assertEquals(CommonMethods.getText(ordersPage.statusOfMostRecentOrder), "Paid");
		//Navigate to Account Page
		CommonMethods.click(ordersPage.backToAccountLink);
		//Log Out
		CommonMethods.click(accountPage.logoutLink);	
		//Log Back In
		driver.get(Reader.getValue("signInUrl"));
		signInPage.signIn(randomEmail, Reader.getValue("password"));
		CommonMethods.waitUntilVisible(accountPage.shopTitle);
		//Add Item to Cart
		js.executeScript("window.scrollBy(0,2000)", "");
		accountPage.addItemToCart(accountPage.bathMatAddToCartButton);
		//Save Summary Total Before Applying Coupon Code
		CommonMethods.waitUntilVisible(accountPage.summaryTotal);
		String summaryTotalBeforeCouponCode = CommonMethods.getText(accountPage.summaryTotal);
		summaryTotalBeforeCouponCode = summaryTotalBeforeCouponCode.substring(1);
		double summaryTotalBeforeCouponCodeAsDouble = Double.parseDouble(summaryTotalBeforeCouponCode);
		//Add Coupon Code
		accountPage.addCouponCode(Reader.getValue("couponCode"));
		//Save Summary Total After Applying Coupon Code
		String summaryTotalAfterCouponCode = CommonMethods.getText(accountPage.summaryTotal);
		summaryTotalAfterCouponCode = summaryTotalAfterCouponCode.substring(1);
		double summaryTotalAfterCouponCodeAsDouble = Double.parseDouble(summaryTotalAfterCouponCode);
		Assert.assertEquals(summaryTotalAfterCouponCodeAsDouble, summaryTotalBeforeCouponCodeAsDouble - 5);
		//Pay For Item
		accountPage.clickPayWithCardButtonAndOrderConfirmationCloseButton();
		//Navigate to Orders Page
		CommonMethods.click(accountPage.viewOrdersLink);
		CommonMethods.waitUntilVisible(ordersPage.ordersPageTitle);
		//Validate Number of Orders
		Assert.assertEquals(CommonMethods.numberOfElements(ordersPage.individualOrders), 2);
		//Validate Order Information
		Assert.assertEquals(CommonMethods.getText(ordersPage.titleOfFirstItemInMostRecentOrder), "You Look Good Bath Mat");
		Assert.assertEquals(CommonMethods.getText(ordersPage.streetAddressOfMostRecentOrder), Reader.getValue("streetAddress"));
		Assert.assertTrue(CommonMethods.getText(ordersPage.cityAndStateOfMostRecentOrder).contains(Reader.getValue("city")));
		Assert.assertTrue(CommonMethods.getText(ordersPage.cityAndStateOfMostRecentOrder).contains(Reader.getValue("state")));
		Assert.assertEquals(CommonMethods.getText(ordersPage.totalOfMostRecentOrder), "$" + summaryTotalAfterCouponCode);
		Assert.assertEquals(CommonMethods.getText(ordersPage.statusOfMostRecentOrder), "Paid");
		//Navigate to Account Page
		CommonMethods.click(ordersPage.backToAccountLink);
		//Log Out
		CommonMethods.click(accountPage.logoutLink);
	}
	
	@AfterMethod
	public void closeDown() {
		Driver.tearDown();
	}
}
