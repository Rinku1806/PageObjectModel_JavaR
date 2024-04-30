package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class Reptiles extends BasePage {
	

	public Reptiles(WebDriver driver) {
		super(driver);		
	}

		
	@FindBy(xpath="//*[@id='BackLink']/a")
	public WebElement returnMainMenu;	

	@FindBy(xpath="//*[@id='Catalog']/table/tbody")
	public WebElement repstable;
	
	public DashBoard returnMainMenu() {		
		click(returnMainMenu);		
		return new DashBoard(driver);
	}
	
}
