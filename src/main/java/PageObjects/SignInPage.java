package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class SignInPage extends AbstractComponents {
WebDriver driver;
	
	public SignInPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="email")
	WebElement userEmail;
	
	@FindBy(id="password")
	WebElement userPassword;
	
	@FindBy(css="button[type='submit']")
	WebElement submit;
	
	public HomePage signin(String email, String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		return new HomePage(driver);
		
	}
	
	public String getErrorMessage()
	{
		String errorMsg = driver.findElement(By.cssSelector(".text-red-500")).getText();
		return errorMsg;
	}

}
