package timberworld.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.ProductPage;
import PageObjects.ShoppingPage;
import PageObjects.SignInPage;
import timberworld.TestComponents.BaseTest;

public class SubmitOrder2Test extends BaseTest {
	
	
	@Test(dataProvider="getData")
	public void SubmitOrder2(String email, String password, String productName, String name, String credit, String expd, String cvv) throws InterruptedException
	{
		
		SignInPage signinPage = homepage.goToSignin();
		signinPage.signin(email, password);
		ShoppingPage shoppage = homepage.goToShoppingPage();
		shoppage.livingRoomCategory();
		shoppage.getProductList();
		ProductPage productpage = shoppage.goToProductPage(productName);
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
		return new Object[][] {{"bernagokcebilge@gmail.com", "Abcd1234","Incanto Italian Attitude Couches Sofa","berna","44444","01/28","111"}};
	}

}
