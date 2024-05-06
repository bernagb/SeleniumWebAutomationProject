package timberworld.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AbstractComponents.AbstractComponents;
import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.HomePage;
import PageObjects.ProductPage;
import PageObjects.ShoppingPage;
import PageObjects.SignInPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import timberworld.TestComponents.BaseTest;
import timberworld.TestComponents.Retry;


public class SubmitOrderTest extends BaseTest {
	/*String productName = "Sofa Vissle";
	String email = "bernagokcebilge@gmail.com";
	String password = "Abcd1234";*/
	@Test(dataProvider="getData",retryAnalyzer=Retry.class)
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
		SignInPage signinPage = homepage.goToSignin();
		signinPage.signin(input.get("email"), input.get("password"));
		ShoppingPage shoppage = homepage.goToShoppingPage();
		shoppage.livingRoomCategory();
		shoppage.getProductList();
		ProductPage productpage = shoppage.goToProductPage(input.get("productName"));
		productpage.addProductToCart();
		CartPage cartpage = productpage.goToCartPage();
		CheckoutPage checkoutpage = cartpage.goToCheckoutPage();
		checkoutpage.makePayment(input.get("name"), input.get("credit"), input.get("expd"), input.get("cvv"));
		String errorMessage = checkoutpage.getErrorMessage();
		Assert.assertTrue(errorMessage.equalsIgnoreCase("Enter a valid credit card number."));
		
	}
	@DataProvider
	public Object[][] getData() throws IOException
	{	
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+ "\\src\\test\\java\\timberworld\\data\\SubmitOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

	
	/*@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"bernagokcebilge@gmail.com", "Abcd1234","Sofa Vissle","berna","44444","01/28","111"}};
	}*/
	


}
