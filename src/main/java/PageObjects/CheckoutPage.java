package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;


public class CheckoutPage extends AbstractComponents {
WebDriver driver;
	
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="name")
	WebElement namePayment;
	
	@FindBy(id="creditCard")
	WebElement creditCardPayment;
	
	@FindBy(id="expDate")
	WebElement expDatePayment;
	
	@FindBy(id="cvv")
	WebElement cvvPayment;
	
	@FindBy(css="div[class='my-6'] button[class=' btn-dark ']")
	WebElement payButton;
	
	@FindBy(css=".text-red-500")
	WebElement errorMessage;
	
	public void makePayment(String name,String creditCard,String expDate,String cvv)
	{
		namePayment.sendKeys(name);
		creditCardPayment.sendKeys(creditCard);
		expDatePayment.sendKeys(expDate);
		cvvPayment.sendKeys(cvv);
		payButton.click();
	}
	
	public String getErrorMessage()
	{
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
	}
}
