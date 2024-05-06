package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.CartPage;
import PageObjects.SignInPage;



public class AbstractComponents {
	
WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".btn-dark")
	WebElement signinButton;
	
	@FindBy(xpath="//div[@class='end__icon']//*[name()='svg']")
	WebElement cartButton;
	
	public SignInPage goToSignin()
	{
		signinButton.click();
		return new SignInPage(driver);
	}
	
	public CartPage goToCartPage()
	{
		cartButton.click();
		return new CartPage(driver);
	}
	
	public void waitForElementToAppear(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void waitForWebElementToBeClickable(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

}
