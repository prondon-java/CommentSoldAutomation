package Utilities;

import Pages.Account;
import Pages.Orders;
import Pages.SignIn;
import Pages.Registration;

public class PageInitializer extends Driver{
	
	public static Registration registrationPage;
	public static SignIn signInPage;
	public static Account accountPage;
	public static Orders ordersPage;
	

	public static void initialize() {
		registrationPage = new Registration();
		signInPage = new SignIn();
		accountPage = new Account();
		ordersPage = new Orders();
	}
}
