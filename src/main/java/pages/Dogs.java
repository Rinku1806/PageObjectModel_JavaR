package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class Dogs extends BasePage {

	public Dogs(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//*[@id='BackLink']/a")
	public WebElement returnMainMenu;	
	
	@FindBy(xpath="//*[@id='Catalog']/table/tbody")
	public WebElement dogtable;
	
	public DashBoard returnMainMenu() {		
		click(returnMainMenu);		
		return new DashBoard(driver);
	}
	
	

}
