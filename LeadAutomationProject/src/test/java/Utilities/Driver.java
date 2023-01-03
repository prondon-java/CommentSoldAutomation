package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {

	public static WebDriver driver;

	public static WebDriver getDriver() {

		if (driver == null) {

			switch (Reader.getValue("browser")) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.get(Reader.getValue("registerUrl"));
				driver.manage().window().maximize();
				PageInitializer.initialize();
				break;

			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				driver.get(Reader.getValue("registerUrl"));
				driver.manage().window().maximize();
				PageInitializer.initialize();
				break;
			}
		}
		return driver;
	}

	public static void tearDown() {
		if (driver != null) {
			driver.close();
			driver = null;
		}
	}
}
