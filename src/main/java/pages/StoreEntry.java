package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class StoreEntry extends BasePage{

	
	public StoreEntry(WebDriver driver) {
		super(driver);		
	}

	@FindBy(xpath="//*[@id='MenuContent']/a[2]")
	public WebElement SignIn;

	@FindBy(xpath="//input[@name='username']")
	public WebElement enterUserName;
	
	@FindBy(xpath="//input[@name='password']")
	public WebElement enterPassword;
	
	@FindBy(xpath = "//input[@name='signon']")
	public WebElement login;
	
	public DashBoard logontoDashBoard(String username,String password) {
		entertext(enterUserName, username);
		entertext(enterUserName, password);
		click(login);
		return new DashBoard(driver);
	}
	

}
