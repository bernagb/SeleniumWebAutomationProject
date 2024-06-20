package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class ProductPage extends AbstractComponents {
	WebDriver driver;
	
	public ProductPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(css=".animation-icon-wrapper")
	WebElement addToCart;
	
	public void addProductToCart()
	{

		addToCart.click();
	}
	

}
