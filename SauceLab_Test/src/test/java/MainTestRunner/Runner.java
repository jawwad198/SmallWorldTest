package MainTestRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="C:\\SauceLab_Test\\src\\test\\resources\\SauceLab_Feature\\Start_to_Signoff.feature",
glue= {"com.Hooks","com.SaucelabMainClass"},
monochrome = true,
plugin = {"pretty",  "json:target/cucumber/report.json", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
tags = " @SW_InterviewTest"
)

public class Runner{

}


