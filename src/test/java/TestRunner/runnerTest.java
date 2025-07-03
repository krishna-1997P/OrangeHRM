package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions
(
		//features="/src/test/java/Features/employeeApi.feature",
		features = "src//test//java//FeaturesFiles//db.feature",
		glue = {"StepDefination"},
		dryRun = false,
		monochrome = true
		
		
		)
public class runnerTest extends AbstractTestNGCucumberTests
{
								

}
