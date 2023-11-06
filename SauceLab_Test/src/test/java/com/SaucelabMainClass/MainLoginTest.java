package com.SaucelabMainClass;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.DriverFactory.DriverFactory;
import com.SauceLabPages.*;
import com.Util.ConfigReader;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class MainLoginTest {
	
	private PF_LoginPage LP = new PF_LoginPage(DriverFactory.getDriver());
	private PF_AddOrRemoveItem FP = new PF_AddOrRemoveItem(DriverFactory.getDriver());
	private ConfigReader configReader;
	Properties prop;
	
	public void getProperty() {
		configReader = new ConfigReader();
		prop = configReader.init_prop();
	}
	
	@Given("Without Entering any Data click on Login Btn")
	public void ClickOnLoginBTNWIthoutEnterAnyData() throws InterruptedException {
		LP.C_LoginBTN();
	}
	
	@Then("Verify that the UserName Validation is appeared")
	public void VerificationofLoginBTNVerificationWIthoutEnteranydata(DataTable dataTable) throws InterruptedException {
		List<String> ExpectedValidationMsg = dataTable.asList();
		LP.LoginBtnValidationMsgVerification(ExpectedValidationMsg.get(0));
	}
	
	@Then("Enter the UserName and Click on Login Btn")
	public void InputheUserName() throws InterruptedException {
		LP.Enter_UserName("InvalidUser");
		LP.C_LoginBTN();
	}
	
	@Then("Verify that the Password Required Validation is Appeared")
	public void Verify_that_the_Password_Required_Validation_is_Appeared(DataTable dataTable) throws InterruptedException {
		List<String> ExpectedValidationMsg = dataTable.asList();
		LP.LoginBtnValidationMsgVerification(ExpectedValidationMsg.get(0));
	}

	@Then("Enter the UserName and Password and Verify that the validation is working properly for ENtering Wrong user Name and Password")
	public void TestTheLoginFuncionalityWithTheHelpOfDecisionTable(DataTable dataTable) throws InterruptedException {
		List<Map<String, String>> listdata = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> mapdata : listdata) {
			LP.LogintoSwagsLab(mapdata.get("UserName"), mapdata.get("Password"));
			LP.LoginBtnValidationMsgVerification(mapdata.get("ValidationMsg"));			
		}
	}
	
	@Then("Verify that Case Sensitive is working")
	public void CaseSensitiveValidationVerification(DataTable dataTable) throws InterruptedException {
		List<String> GettingData = dataTable.asList();
		LP.LogintoSwagsLab(GettingData.get(0), GettingData.get(1));
		LP.LoginBtnValidationMsgVerification(GettingData.get(2));
	}
	
	@Then("Enter the Correct UserName Password and Login to the Application")
	public void EnterTheCorrectCredentials() throws InterruptedException {
		getProperty();
		LP.LogintoSwagsLab(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@And("Verify that the user is redirect to the Inventory Page")
	public void VerifythatTheUserwillRedirecttoTheInventoryPage()  throws InterruptedException{
		FP.VerificationOfInvnetoryPage();
	}
}
