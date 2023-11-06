package com.SaucelabMainClass;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import com.DriverFactory.*;
import io.cucumber.java.en.*;
import com.SauceLabPages.*;
import com.Util.ConfigReader;

public class Background extends ConfigReader{
	
	
	private PF_LoginPage LP = new PF_LoginPage(DriverFactory.getDriver());
	private ConfigReader configReader;
	Properties prop;
	
	public void getProperty() {
		configReader = new ConfigReader();
		prop = configReader.init_prop();
	}
	@SuppressWarnings("deprecation")
	@Given("Open the sauce lab application")
	public void open_the_sauce_lab_application() {
		getProperty();
	    DriverFactory.getDriver().get(prop.getProperty("url"));
	    DriverFactory.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Then("Verify that the Login page is Appeared or not")
	public void verify_that_the_login_page_is_appeared_or_not() {
		LP.VerifythatTheURlRedirectTotheLoginPage();
	}

}
