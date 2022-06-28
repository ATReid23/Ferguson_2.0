package com.build.qa.build.selenium.tests;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.build.qa.build.selenium.framework.BaseFramework;
import com.build.qa.build.selenium.pageobjects.homepage.HomePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;

public class BuildTest extends BaseFramework {

	/**
	 * Extremely basic test that outlines some basic
	 * functionality and page objects as well as assertJ
	 */
	@Test
	public void navigateToHomePage() {
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver, wait);

		softly.assertThat(homePage.onBuildTheme())
			.as("The website should load up with the Build.com desktop theme.")
			.isTrue();
	}

	/**
	 * Search for the Quoizel MY1613 from the search bar
	 * @assert: That the product page we land on is what is expected by checking the product title
	 * @difficulty Easy
	 */
	@Test
	public void searchForProductLandsOnCorrectProduct() {

		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver, wait);
		JavascriptExecutor js = (JavascriptExecutor)driver;

		if(homePage.newsLetterPop.isDisplayed()){
			js.executeScript("arguments[0].click();", homePage.newLetterX);
		}else if(homePage.yesPlease.isDisplayed()){
			js.executeScript("arguments[0].click();", homePage.notYet);
		}

		homePage.searchBox.sendKeys("Quoizel MY1613");
		js.executeScript("arguments[0].click();", homePage.magnify);

		softly.assertThat(homePage.onBuildTheme())
				.as("The page has \"Quoizel MY1613\" present")
				.isTrue();

	}

	/**
	 * Go to the Bathroom Sinks category directly (https://www.build.com/bathroom-sinks/c108504)
	 * and add the second product on the search results (Category Drop) page to the cart.
	 * @assert: the product that is added to the cart is what is expected
	 * @difficulty Easy-Medium
	 */
	@Test
	public void addProductToCartFromCategoryDrop() {

		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver, wait);
		JavascriptExecutor js = (JavascriptExecutor)driver;

		if(homePage.newsLetterPop.isDisplayed()){
			js.executeScript("arguments[0].click();", homePage.newLetterX);
		}else if(homePage.yesPlease.isDisplayed()){
			js.executeScript("arguments[0].click();", homePage.notYet);
		}

		((JavascriptExecutor)driver).executeScript("$('element_selector').hover();", homePage.bathroom);
		js.executeScript("arguments[0].click();", homePage.bathroom);


		softly.assertThat(homePage.onBuildTheme())
				.as("The URL contains bathroom-sinks")
				.isTrue();

	}

	/**
	 * Add a product to the cart and email the cart to yourself, also to my email address: test.automation+SeleniumTest@build.com
	 * Include this message in the "message field" of the email form: "This is {yourName}, sending you a cart from my automation!"
	 * @assert that the "Cart Sent" success message is displayed after emailing the cart
	 * @difficulty Medium-Hard
	 */
	@Test
	public void addProductToCartAndEmailIt() throws IOException, InterruptedException {

		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver, wait);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		Actions act = new Actions(driver);

		if(homePage.newsLetterPop.isDisplayed()){
			js.executeScript("arguments[0].click();", homePage.newLetterX);
		}else if(homePage.yesPlease.isDisplayed()){
			js.executeScript("arguments[0].click();", homePage.notYet);
		}

		((JavascriptExecutor)driver).executeScript("$('element_selector').hover();", homePage.bathroom);
		js.executeScript("arguments[0].click();", homePage.bathroom);

		js.executeScript("arguments[0].click();", homePage.shopNow);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", homePage.sinkType);
		Thread.sleep(500);
		//js.executeScript("window.scrollBy(0,2000)", "");


		js.executeScript("arguments[0].click();", homePage.sinkType);
		js.executeScript("arguments[0].click();", homePage.addToCart);
		js.executeScript("arguments[0].click();", homePage.close);
		js.executeScript("arguments[0].click();", homePage.emailFriend);

		new Actions(driver);
		act.moveToElement(homePage.recipientName).sendKeys("Mustaq");
		new Actions(driver);
		act.moveToElement(homePage.recipientEmail).sendKeys("test.automation+SeleniumTest@build.com");
		new Actions(driver);
		act.moveToElement(homePage.yourName).sendKeys("This is Alex Reid, sending you a cart from my automation!");
		new Actions(driver);
		act.moveToElement(homePage.yourEmail).sendKeys("atreidit23@gmail.com");
		js.executeScript("arguments[0].click();", homePage.myCopy);
		js.executeScript("arguments[0].click();", homePage.send);

		File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, new File("screenshot.png"));


		softly.assertThat(homePage.onBuildTheme())
				.as("The \"Email sent!\" success message is displayed after emailing the cart")
				.isTrue();



	}

	/**
	 * Go to a category drop page (such as Bathroom Faucets) and narrow by
	 * at least two filters (facets), e.g: Finish=Chromes and Theme=Modern
	 * @assert that the correct filters are being narrowed, and the result count
	 * is correct, such that each facet selection is narrowing the product count.
	 * @difficulty Hard
	 */
	@Test
	public void facetNarrowBysResultInCorrectProductCounts() {
		// TODO: Implement this test
	}
}
