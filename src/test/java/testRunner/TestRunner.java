package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		//features= {".//Features/"},
        features= {".//Features/Login.feature"},
        //features= {".//Features/LoginDDT.feature"},
        //features= {".//Features/LoginDDTExcel.feature"},
        //features= {".//Features/Login.feature",".//Features/AccountRegistration.feature"},
        //features="@target/rerun.txt",   // Runs only failures
		glue = "stepDefinitions", 
		plugin = { 
				  "pretty", 
				  "html:reports/myreport.html",
				  "json:reports/myreport.json" ,
				  "rerun:target/rerun.txt",    //Mandatory to capture failures
				 }, 
		monochrome = true, 
		dryRun = false,
	    tags = "@Sanity"	//Scenarios tagged with @Sanity,
		//tags = "@Regression"	//Scenarios tagged with @Regression
	  //tags = "@Sanity and @Regression"	//Scenarios tagged with both @Sanity and @Regression
	  //tags = "@Sanity or @Regression"	 //Scenarios tagged with either @Sanity or @Regression
	  //tags = "@Sanity and not @Regression", //Scenarios tagged with @Sanity but not tagged with @Regression
		)

public class TestRunner {

}
