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

	//Search
	@FindBy(xpath = "//input[@class='text-input search react-search-input-normal js-reload-value']")
	public WebElement searchBox;
	@FindBy(xpath = "//a[@href='javascript:;']")
	public WebElement magnify;


	//Bathroom
	@FindBy(xpath = "//a[@data-category-id='108412']")
	public WebElement bathroom;
	@FindBy(xpath = "//li[@data-di-id='di-id-17c8e940-b366171']//div")
	public WebElement sinks;

	//Purchase

	@FindBy(xpath = "//a[@href='/category/bathroom-plumbing/bathroom-faucets/bathroom-sink-faucets/_/N-zbq4i3?icid=mrch_hmpg_cat_featured-categories_bathroom-sink-faucets']")
	public WebElement bRoomSinkFaucets;

	@FindBy(xpath = "//div[@id='sku7289400']//ancestor::li[@data-id='1']")
	public WebElement secondSinkChoice;
	@FindBy(xpath = "(//div[@id='sku7289400']//ancestor::li[@data-id='1']//div[@class='sr-content-box']//a[@class='link-gray'])[1]")
	public WebElement secondSinkTitle;

	@FindBy(xpath = "//div[@id='sku7289400']//ancestor::p[@data-placement='2']")
	public WebElement addThisType2Cart;
	@FindBy(xpath = "//form[@method='post']//div[@class='modal-footer']//button[2]")
	public WebElement popUpCartAdd;
	@FindBy(xpath = "(//a[@href='https://www.ferguson.com/shoppingCart'])[1]")
	public WebElement cart;


	@FindBy(xpath = "//div[@class='cl-name']//a")
	public WebElement cartPurchase;







}
