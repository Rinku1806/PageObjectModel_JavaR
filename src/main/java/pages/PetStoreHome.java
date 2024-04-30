package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class PetStoreHome extends BasePage{

	
	public PetStoreHome(WebDriver driver) {
		super(driver);		
	}


	@FindBy(xpath="//*[@id='Content']/p[1]/a")
	public WebElement enterStore;
	
	public StoreEntry enterStore() {
		click(enterStore);
		return new StoreEntry(driver);
	}
	

}
