package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import utilities.DriverManager;

public class BasePage {

	public static WebDriver driver;

	public BasePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getPageTitle() {
		return driver.getTitle();
	}	

	public void click(WebElement element) {
		element.click();
	}

	public void entertext(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}

	public void gotoPreviousPage() {

		driver.navigate().back();
	}

	public static WebDriver getDriver() {

		return DriverManager.getDriver();
	}

}
