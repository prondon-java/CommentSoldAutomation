package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.Driver;

public class Orders {
	
	public Orders() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(xpath="//h1[text()='Orders']")
	public WebElement ordersPageTitle;
	
	@FindBy(xpath="//*[@class='orders']")
	public WebElement individualOrders;
	
	@FindBy(xpath="((//*[@class='orders'])[1]//span[@class='title'])[1]")
	public WebElement titleOfFirstItemInMostRecentOrder;
	
	@FindBy(xpath="((//*[@class='orders'])[1]//span[contains(text(),'Shipping To')]/following-sibling::span)[1]")
	public WebElement streetAddressOfMostRecentOrder;
	
	@FindBy(xpath="((//*[@class='orders'])[1]//span[contains(text(),'Shipping To')]/following-sibling::span)[2]")
	public WebElement cityAndStateOfMostRecentOrder;
	
	@FindBy(xpath="(//*[@class='orders'])[1]//div[@class='total']/span[@class='value']")
	public WebElement totalOfMostRecentOrder;
	
	@FindBy(xpath="(//*[@class='orders'])[1]//div[@class='order-status']")
	public WebElement statusOfMostRecentOrder;
	
	@FindBy(xpath="//*[@class='back']")
	public WebElement backToAccountLink;
}

