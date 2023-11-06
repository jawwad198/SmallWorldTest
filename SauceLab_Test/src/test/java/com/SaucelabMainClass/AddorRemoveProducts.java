package com.SaucelabMainClass;


import java.util.List;
import java.util.Map;
import com.DriverFactory.DriverFactory;
import com.SauceLabPages.*;
import StoringLocalValue.*;
import io.cucumber.datatable.*;
import io.cucumber.java.en.*;

public class AddorRemoveProducts {
	
	PF_AddOrRemoveItem AddorRemove = new PF_AddOrRemoveItem(DriverFactory.getDriver());
	
	
	@Then("Check by default sorting order from A to Z are selected")
	public void VerificationOfByDefaultFilteringSet() {
		AddorRemove.ByDefaultFilteringWhenUserJumptoTheInventoryPage();
	}
	
	@Then("Add to Cart Some Inventory Items and Before add to cart Get the Amount of that particular Product")
	public void AddToCartSomeInventoryItemsandGetTheAmount(DataTable dataTable) throws InterruptedException {
		List<Map<String, String>> listdata = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> mapdata : listdata) {
			AddorRemove.AddToCart(mapdata.get("ItemsName"));
		}
	}
	
	@Then("Click on the Cart Button which is on the Header top left")
	public void ClickOnTheCart() throws InterruptedException{
		AddorRemove.C_onTheCartButton();
	}
	
	@Then("Verify that all Item which we selected are on the cart List and amount is appearing Correctly")
	public void VerifyTheItemandItemAmountAppearedCorrectly(DataTable dataTable) {
		List<Map<String, String>> listdata = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> mapdata : listdata) {
			AddorRemove.VerificationOfthePreviousSelectedItemandAmountisVisibleonCartScreen(mapdata.get("ItemsName"),ScenarioContext.get(mapdata.get("ItemsName")));
		}
	}
	
	@Then("Remove Some Selected Item")
	public void RemoveSomeSelectedItems(DataTable dataTable) {
		List<Map<String, String>> listdata = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> mapdata : listdata) {
			AddorRemove.RemoveItem(mapdata.get("ItemsName"));
		}
	}
	
	@And("Click On Check Out Button")
	public void ClickOnCheckOutBTN() throws InterruptedException  {
		AddorRemove.ClickOnCheckOutButton();
		Thread.sleep(2000);
	}
	
}




