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

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String productName = "Sofa Vissle";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.get("https://timberworld.netlify.app/");
		driver.findElement(By.cssSelector(".btn-dark")).click();
		driver.findElement(By.id("email")).sendKeys("bernagokcebilge@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Abcd1234");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		driver.findElement(By.linkText("Start Shopping")).click();
		driver.findElement(By.linkText("Living room")).click();
		//find the product according to name given
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(7));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-card__article")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".product-card__article"));
		
		WebElement prod = products.stream().filter(product-> product.findElement(By.cssSelector("p:nth-child(2)"))
				.getText().equals(productName)).findFirst().orElse(null);
		prod.click();
		//add to cart
		driver.findElement(By.cssSelector(".animation-icon-wrapper ")).click();
		driver.findElement(By.xpath("//div[@class='end__icon']//*[name()='svg']")).click();
		
		//check the cart

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart-item__product")));
		String productMatch = driver.findElement(By.cssSelector(".cart-item__product")).getText();
		Assert.assertTrue(productMatch.equalsIgnoreCase(productName));

		driver.findElement(By.linkText("Check out")).click();
		driver.findElement(By.id("name")).sendKeys("berna");
		driver.findElement(By.id("creditCard")).sendKeys("44444444");
		driver.findElement(By.id("expDate")).sendKeys("01/28");
		driver.findElement(By.id("cvv")).sendKeys("111");
		driver.findElement(By.cssSelector("div[class='my-6'] button[class=' btn-dark ']")).click();
		//check the error message
		String errorMessage = driver.findElement(By.cssSelector(".text-red-500")).getText();
		Assert.assertTrue(errorMessage.equalsIgnoreCase("Enter a valid credit card number."));
		driver.close();

		
	
		

	}

}