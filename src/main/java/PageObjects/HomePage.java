package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
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
	
	@FindBy(css="input[placeholder='Search']")
	WebElement searchBox;
	
	@FindBy(css=".search-icon")
	WebElement searchButton;
	
	@FindBy(css=".search-box-element__text")
	List<WebElement> products;
	
	public List<WebElement> searchProduct()
	{
		searchBox.sendKeys("bed");
		searchButton.click();
		return products;
	}
	public ProductPage clickonProduct(String productName)
	{
		WebElement product = products.stream().filter(prod->prod.findElement(By.cssSelector("p:nth-child(1)")).getText().equals(productName))
				.findFirst().orElse(null);
		product.click();
		return new ProductPage(driver);
	}
	
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
