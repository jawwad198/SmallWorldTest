package com.SauceLabPages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class PF_LoginPage {

	WebDriver driver;
	public PF_LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, PF_LoginPage.this);
	}
	
	@FindBy(id ="user-name")
	WebElement Txt_UserName;
	
	@FindBy(id ="password")
	WebElement Txt_Password;
	
	@FindBy(id = "login-button")
	WebElement Btn_Login;
	
	@FindBy(xpath = "//div[@class = 'error-message-container error']//h3")
	WebElement ValidationMsg;
	
	@FindBy(xpath = "//div[@class = 'error-message-container error']//h3//button[@class = 'error-button']")
	WebElement Btn_RemoveValidation;
	
	@FindBy(xpath = "//div[@id = 'root']//div//div[text() = 'Swag Labs']")
	WebElement LoginPage;
	
	public void VerifythatTheURlRedirectTotheLoginPage() {
		String ActucalLoginPage = LoginPage.getAttribute("class");
		assertEquals(ActucalLoginPage, "login_logo", "URL Doesn't Redirect to the Login Page");
	}
	
	public void Enter_UserName(String UserName) {
		Txt_UserName.clear();
		Txt_UserName.sendKeys(UserName);
	}
	

	public void Enter_Password(String Password) {
		Txt_Password.clear();
		Txt_Password.sendKeys(Password);
	}
	
	public void C_LoginBTN() throws InterruptedException {
		Btn_Login.click();
		Thread.sleep(1000);
	}
	
	public void LogintoSwagsLab(String UserName, String Enter_Password) throws InterruptedException {
		Enter_UserName(UserName);
		Thread.sleep(1000);
		Enter_Password(Enter_Password);
		Thread.sleep(1000);
		C_LoginBTN();
	}
	
	public void LoginBtnValidationMsgVerification(String ExpectedValidationMsg) throws InterruptedException {
		String ActualValidationMsg = ValidationMsg.getText();
		assertEquals(ActualValidationMsg, ExpectedValidationMsg, "Wrong Validation is Appearing");
		Thread.sleep(1000);
		Btn_RemoveValidation.click();
	}
	
	
	
}
