package com.SauceLabPages;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;

import com.DriverFactory.DriverFactory;

import StoringLocalValue.ScenarioContext;

public class PF_AddOrRemoveItem {
	
	WebDriver driver;
	public PF_AddOrRemoveItem(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, PF_AddOrRemoveItem.this);
	}
	
	public String InvnetoryPageURL = "https://www.saucedemo.com/inventory.html";
	public String DefaultFilter = "Name (A to Z)";
	public String AddtoCartItemName = "";
	
	
	@FindBy(xpath = "//span[@class ='active_option']")
	WebElement ActiveFilter;
	
	@FindBy(xpath ="//Select[@class ='product_sort_container']")
	WebElement SelectFilter;
	
	@FindBy(id = "shopping_cart_container")
	WebElement BtnCart;
	
	@FindBy(id ="checkout")
	WebElement BtnCheckOut;
	
	
	public void VerificationOfInvnetoryPage() throws InterruptedException {
		Thread.sleep(2000);
		String ActualCurrentURL = DriverFactory.getDriver().getCurrentUrl();
		assertEquals(ActualCurrentURL, InvnetoryPageURL, "After Login Page is not Redirect to The Inventory Page");
	}
	
	public void ByDefaultFilteringWhenUserJumptoTheInventoryPage() {
		String ActualByDefaultFilter = ActiveFilter.getText();
		assertEquals(ActualByDefaultFilter, DefaultFilter, "By Default Filter is not properly WOrking");
	}
	
	
	public void AddToCart(String ItemName) throws InterruptedException {
		String ItemsName = ItemName.replace(" ", "-");
		AddtoCartItemName = "add-to-cart-"+ItemsName;
		AddtoCartItemName = AddtoCartItemName.toLowerCase();
		WebElement GetAmountofTheItem = driver.findElement(By.xpath("//button[@id = '"+AddtoCartItemName+"']//parent::div//div[@class = 'inventory_item_price']"));
		String Amount = GetAmountofTheItem.getText();
		Amount = Amount.replace("$", "");
		ScenarioContext.put(ItemName, Amount);
		System.out.println(Amount);
		WebElement BTNAdddToCart = driver.findElement(By.id(AddtoCartItemName));
		Actions action = new Actions(driver);
		action.moveToElement(BTNAdddToCart).build();
		Thread.sleep(1000);
		BTNAdddToCart.click();
		Thread.sleep(1000);
	}
	
	public void C_onTheCartButton() throws InterruptedException {
		BtnCart.click();
		Thread.sleep(1000);
	}
	
	public void VerificationOfthePreviousSelectedItemandAmountisVisibleonCartScreen(String ExpectedItems,String ExpectedAmount) {
		String cartItems = ExpectedItems.replace(" ", "-");
		cartItems = "remove-"+cartItems;
		cartItems = cartItems.toLowerCase();
		try {
			Actions action = new Actions(driver);
		WebElement ActualItemsName =driver.findElement(By.xpath("//button[@id  ='"+cartItems+"']//parent::div//parent::div//a//div"));
		WebElement ItemAmount = driver.findElement(By.xpath("//button[@id  ='"+cartItems+"']//parent::div//div"));
		action.moveToElement(ItemAmount).build();
		assertEquals(ActualItemsName.getText(), ExpectedItems,"Selected Add to Cart Item is not appearing in Cart List");
		String ActualItemAmount = ItemAmount.getText();
		ActualItemAmount = ActualItemAmount.replace("$", "");
		assertEquals(ActualItemAmount, ExpectedAmount ,"Item Amount is not appearing correct");
		}catch(NoSuchElementException X) {
			X.printStackTrace();
			System.out.println(ExpectedItems +": Selected Items is not Appearing in the Add to Cart List");
		}
		
	}
	
	public void RemoveItem(String ItemName) {
		ItemName = ItemName.replace(" ", "-");
		ItemName = "remove-"+ItemName;
		ItemName = ItemName.toLowerCase();
		WebElement BtnRemoveItem = driver.findElement(By.id(ItemName));
		Actions action = new Actions(driver);
		action.moveToElement(BtnRemoveItem).build();
		BtnRemoveItem.click();
	}
	
	public void ClickOnCheckOutButton() {
		BtnCheckOut.click();
	}
	

}
