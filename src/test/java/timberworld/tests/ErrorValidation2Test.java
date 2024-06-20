package timberworld.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.ProductPage;
import PageObjects.SignInPage;
import timberworld.TestComponents.BaseTest;

public class ErrorValidation2Test extends BaseTest {
	/*String email = "bernagokcebilge@gmail.com";
	String password = "Abcd1234";
	String productName = "Queen Bed";*/
	@Test(dataProvider="getData")
	public void ErrorValidation2(String email, String password, String productName, String name, String credit, String expd, String cvv) throws InterruptedException
	{
		SignInPage signinPage = homepage.goToSignin();
		signinPage.signin(email, password);
		Thread.sleep(3000);
		homepage.searchProduct();
		ProductPage productpage = homepage.clickonProduct(productName);
		productpage.addProductToCart();
		CartPage cartpage = productpage.goToCartPage();
		CheckoutPage checkoutpage = cartpage.goToCheckoutPage();
		checkoutpage.makePayment(name,credit,expd,cvv);
		String errorMessage = checkoutpage.getErrorMessage();
		Assert.assertTrue(errorMessage.equalsIgnoreCase("Enter a valid credit card number."));
	}
	@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"bernagokcebilge@gmail.com", "Abcd1234","Queen Bed","berna","44444","01/28","111"}};
	}

}
