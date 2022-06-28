package com.build.qa.build.selenium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.framework.BaseFramework;

public abstract class BasePage extends BaseFramework {
	
	public BasePage(WebDriver driver, Wait<WebDriver> wait) { 
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
	}

	//Pop Up Prompt

	@FindBy(xpath = "//div[@id='newsletter-modal']")
	public WebElement newsLetterPop;
	@FindBy(xpath = "//button[@tabindex='-1']")
	public WebElement newLetterX;

	@FindBy(xpath = "//button[contains(text(),'Not Yet')]")
	public WebElement notYet;
	@FindBy(xpath = "//button[contains(text(),'Yes Please')]")
	public WebElement yesPlease;

	//Search
	@FindBy(id = "search_txt")
	public WebElement searchBox;
	@FindBy(xpath = "//button[@class='button-primary search-site-search']")
	public WebElement magnify;

	@FindBy(xpath = "//div[@data-automation='no-search-results-page']//h1")
	public WebElement searchResult;

	//Bathroom
	@FindBy(xpath = "//a[@data-category-id='108412']")
	public WebElement bathroom;
	@FindBy(xpath = "//a[@data-tracking='nav:menu:category:Bathroom:Bathroom Sinks']")
	public WebElement sinks;

	//Purchase
	@FindBy(xpath = "//button[contains(text(),'Shop Now')]")
	public WebElement shopNow;

	@FindBy(xpath = "//img[@alt='Miseno']")
	public WebElement sinkType;

	@FindBy(xpath = "//button[@data-automation='add-to-cart-button']")
	public WebElement addToCart;
	@FindBy(xpath = "//div[@class='flex flex-column flex-row-ns items-center justify-center h-100']//div//div")
	public WebElement shopCart;
	@FindBy(xpath = "//div[@class='absolute right-0 top-0']")
	public WebElement close;
	@FindBy(xpath = "//button[@aria-label='Email to a friend']")
	public WebElement emailFriend;

	@FindBy(xpath = "//button[@aria-label='Email to a friend']")
	public WebElement recipientName;
	@FindBy(xpath = "//input[@name='recipientEmail']")
	public WebElement recipientEmail;
	@FindBy(xpath = "//input[@name='name']")
	public WebElement yourName;
	@FindBy(xpath = "//input[@name='email']")
	public WebElement yourEmail;
	@FindBy(xpath = "//input[@name='shouldSendCopy']")
	public WebElement myCopy;
	@FindBy(xpath = "//button[contains(text(),'Send to a Friend')]")
	public WebElement send;


}
