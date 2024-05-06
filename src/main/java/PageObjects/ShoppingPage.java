package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class ShoppingPage extends AbstractComponents{
WebDriver driver;
	
	public ShoppingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".product-card__article")
	List<WebElement> products;
	
	@FindBy(css=".product-card__article")
	WebElement product1;
	
	@FindBy(linkText="Living room")
	WebElement livingRoom;
	
	
	public void livingRoomCategory()
	{
		livingRoom.click();
	}
	

	public List<WebElement> getProductList()
	{
		waitForElementToAppear(product1);
		return products;
	}
	
	public ProductPage goToProductPage(String productName)
	{
		WebElement prod = products.stream().filter(product-> product.findElement(By.cssSelector("p:nth-child(2)"))
				.getText().equals(productName)).findFirst().orElse(null);
		prod.click();
		return new ProductPage(driver);
	}
	
	

}
