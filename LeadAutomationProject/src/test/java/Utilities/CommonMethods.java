package Utilities;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods extends PageInitializer{

	public static void inputText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);;
	}

	public static String getText(WebElement element) {
		return element.getText();
	}
	
	public static String getAttribute(WebElement element) {
		return element.getAttribute("value");
	}
	
	public static void click(WebElement element) {
		element.click();
	}
	
	public static boolean isDisplayed(WebElement element) {
		return element.isDisplayed();
	}
	
	public static boolean isNotDisplayed(WebElement element) {
	    try{
	       return !element.isDisplayed();
	    }
	    catch(Exception e){
	       return true;
	    }
	}
	
	public static void selectByValue(WebElement element, String value) {
		Select s = new Select(element);
		s.selectByValue(value);
	}
	
	public static WebDriverWait waitObj() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait;
	}

	public static void waitUntilVisible(WebElement element) {
		waitObj().until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void waitUntilNotVisible(WebElement element) {
		waitObj().until(ExpectedConditions.invisibilityOf(element));
	}
	
	public static void waitUntilClickable(WebElement element) {
		waitObj().until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void switchToIframeByWebElement(WebElement element) {
		driver.switchTo().frame(element);
	}
	
	public static void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}
	
	public static int numberOfElements(WebElement element) {
		String elementXpath = element.toString().substring(element.toString().lastIndexOf(" ") + 1, element.toString().lastIndexOf("]"));
		return driver.findElements(By.xpath(elementXpath)).size();
	}
	
	public static String getRandomEmail(String rootDomain) {
		return RandomStringUtils.random(5, true, true) + "@" + rootDomain;
	}
}
