package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class HomePage extends AbstractComponents {
	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Start Shopping")
	WebElement startShopping;
	
	public ShoppingPage goToShoppingPage()
	{
		startShopping.click();
		return new ShoppingPage(driver);
	}
	
	public void goTo()
	{
		driver.get("https://timberworld.netlify.app/");
	}
	

}
