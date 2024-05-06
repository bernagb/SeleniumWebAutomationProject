package timberworld.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.SignInPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import timberworld.TestComponents.BaseTest;

public class ErrorValidationTest extends BaseTest {
	
	@Test
	public void ErrorValidation()
	{
		
		SignInPage signinPage = homepage.goToSignin();
		signinPage.signin("bernagokcebilge@gmail.com", "abcd1234");
		Assert.assertEquals("Your password or email is incorrect", signinPage.getErrorMessage());
	}
	


	
		
		
	

}
