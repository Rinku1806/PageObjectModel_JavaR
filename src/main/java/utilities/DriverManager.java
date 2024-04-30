package utilities;

import org.openqa.selenium.WebDriver;

public class DriverManager {

	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();

	public static WebDriver getDriver() {

		return tdriver.get();

	}

	public static void setWebDriver(WebDriver driver) {

		tdriver.set(driver);
	}

}
