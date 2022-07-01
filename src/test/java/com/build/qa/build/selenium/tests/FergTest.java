package com.build.qa.build.selenium.tests;

import org.junit.Assert;
import org.junit.Test;

import com.build.qa.build.selenium.framework.BaseFramework;
import com.build.qa.build.selenium.pageobjects.homepage.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.time.Duration.ofSeconds;

public class FergTest extends BaseFramework {

	/**
	 * Extremely basic test that outlines some basic
	 * functionality and page objects as well as assertJ
	 */
	@Test
	public void navigateToHomePage() {
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver, wait);

		softly.assertThat(homePage.onHomePage())
			.as("The website should load up with the Build.com desktop theme.")
			.isTrue();
	}

	/**
	 * Search for the Moen m6702bn from the search bar
	 * @assert: That the product page we land on is what is expected by checking the product brand and product id
	 * @difficulty Easy
	 */
	@Test
	public void searchForProductLandsOnCorrectProduct() {

		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver, wait);
		JavascriptExecutor js = (JavascriptExecutor)driver;


		js.executeScript("arguments[0].click();", homePage.searchBox);
		homePage.searchBox.sendKeys("Moen m6702bn");
		js.executeScript("arguments[0].click();", homePage.magnify);

		softly.assertThat(homePage.onHomePage())
				.as("The page has Part #M6702BN present")
				.isTrue();

	}

	/**
	 * Go to the Bathroom Sinks category directly
	 * (https://www.ferguson.com/category/bathroom-plumbing/bathroom-faucets/bathroom-sink-faucets/_/N-zbq4i3)
	 * and add the second product on the search results (Category Drop) page to the cart.
	 * @assert: the product that is added to the cart is what is expected
	 * @difficulty Easy-Medium
	 */
	@Test
	public void addProductToCartFromCategoryDrop() {

		driver.get(getConfiguration("HOMEPAGE"));
		driver.manage().window().maximize();

		HomePage homePage = new HomePage(driver, wait);
		JavascriptExecutor js = (JavascriptExecutor)driver;


		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", homePage.bRoomSinkFaucets);
		js.executeScript("arguments[0].click();", homePage.bRoomSinkFaucets);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", homePage.secondSinkChoice);
		String faucetName = homePage.secondSinkTitle.getText();

		js.executeScript("arguments[0].click();", homePage.addThisType2Cart);

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(homePage.popUpCartAdd));
		js.executeScript("arguments[0].click();", homePage.popUpCartAdd);

		wait.until(ExpectedConditions.elementToBeClickable(homePage.cart));
		js.executeScript("arguments[0].click();", homePage.cart);
		String purchaseName = homePage.cartPurchase.getText();

		System.out.println(faucetName);
		System.out.println(purchaseName);

		Assert.assertEquals(faucetName, purchaseName);

	}

	/**
	 * Add two different finishes of a product (such as Moen m6702bn) to cart,
	 * change the quantity of each finish on the cart page
	 * @assert that the product and cart total update as expected when the quantity is changed
	 * @difficulty Medium-Hard
	 */
	@Test
	public void addMultipleCartItemsAndChangeQuantity() {
		// TODO: Implement this test
	}

	/**
	 * Go to a category drop page (such as Bathroom Faucets) and narrow by
	 * at least two filters (facets), e.g: Finish=Chromes and Brand=Brizo
	 * @assert that the correct filters are being narrowed, and the result count
	 * is correct, such that each facet selection is narrowing the product count.
	 * @difficulty Hard
	 */
	@Test
	public void facetNarrowBysResultInCorrectProductCounts() {
		// TODO: Implement this test
	}
}
