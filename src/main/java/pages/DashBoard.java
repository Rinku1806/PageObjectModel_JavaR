package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class DashBoard extends BasePage {

	public DashBoard(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(id="WelcomeContent")
	public WebElement welcomeContent;
			
	@FindBy(xpath="//*[@id='SidebarContent']/a[1]")
	public WebElement fish; 
	
	@FindBy(xpath="//*[@id='SidebarContent']/a[2]")
	public WebElement dogs;
	
	@FindBy(xpath="//*[@id='SidebarContent']/a[3]")
	public WebElement cats;
	
	@FindBy(xpath="//*[@id='SidebarContent']/a[4]")
	public WebElement reptiles;
	
	@FindBy(xpath="//*[@id='SidebarContent']/a[5]")
	public WebElement birds;
	
	
	public String getWelcomeContent() {
		
		return welcomeContent.getText();
	}
	
	public Birds gotoBirds() {
		click(birds);
		return new Birds(driver);
	}
	
	public Reptiles gotoReptiles() {
		click(reptiles);
		return new Reptiles(driver);
	}
	
	public Cats gotoCats() {
		click(cats);
		return new Cats(driver);
	}
	public Dogs gotoDogs() {
		click(dogs);
		return new Dogs(driver);
	}
	public Fish gotoFish() {
		click(fish);
		return new Fish(driver);
	}
	
	

}
