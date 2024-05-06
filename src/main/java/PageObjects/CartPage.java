package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


import AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cart-item__product")
	List<WebElement> cartProducts;
	
	@FindBy(css=".cart-item__product")
	WebElement cartProduct;
	
	@FindBy(linkText="Check out")
	WebElement checkoutButton;
	
	
	public Boolean productVerify(String productName)
	{
		waitForElementToAppear(cartProduct);
		Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	public CheckoutPage goToCheckoutPage()
	{
		checkoutButton.click();
		return new CheckoutPage(driver);
	}

}
