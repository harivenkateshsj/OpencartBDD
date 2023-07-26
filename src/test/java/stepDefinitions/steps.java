package stepDefinitions;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import org.junit.Assert;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataReader;

public class steps {
	WebDriver driver;
	ResourceBundle rb;
	Logger logger;
	HomePage hp;
	LoginPage lp;
	MyAccountPage myAcc;
	String br;
	List<HashMap<String, String>> datamap; //Data driven
	 
	@Before
	public void setup()
	{
		rb = ResourceBundle.getBundle("config");
		br = rb.getString("browser");
		logger = LogManager.getLogger(this.getClass());
	}
	
	@After
	public void tearDown(Scenario scenario) throws InterruptedException
	{
		System.out.println("Scenario status ===>" + scenario.getStatus());
		if(scenario.isFailed())
		{
			TakesScreenshot ts = (TakesScreenshot) driver;
			byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());
		}
		Thread.sleep(2000);
		driver.close();
	}
	
	@Given("User launch browser")
	public void user_launch_browser()
	{
		if(br.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(br.equals("edge"))
		{
			driver = new EdgeDriver();
		}
		else
		{
			driver = new FirefoxDriver();
		}	
	}
	
	@Given("Redirect to {string}")
	public void redirect(String url)
	{
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}
	
	@When("Navigate to My Account")
	public void navigate()
	{
	  hp = new HomePage(driver);
	  hp.myAccount();
	  logger.info("My Account is clicked");
	}

	@When("Click Login")
	public void login()
	{
		hp.login();
		logger.info("Login is clicked");
	}
	
	@When("User enter email as {string} and password as {string}")
	public void emailPassword(String email, String password)
	{
		lp = new LoginPage(driver);
		lp.setEmail(email);
		logger.info("Email Address is entered");
		lp.setPassword(password);
		logger.info("Password is entered");
	}
	
	@When("Click Login button")
	public void clickLogin()
	{
		lp.clickLogin();
		logger.info("Login Button is clicked");
	}

	@Then("user navigate to My Account Page")
	public void verifyMyAccount()
	{
		myAcc = new MyAccountPage(driver);
		boolean actualStatus = myAcc.msgInMyAccPage();
		if(actualStatus)
		{
			Assert.assertTrue(true);
			logger.info("Login Success");
		}
		else
		{
			Assert.assertTrue(false);
			logger.error("Login Failed");
		}
	}
	
	 //*******   Data Driven By Excel test method    **************
    @Then("Check User navigates to MyAccount Page by passing Email and Password with excel row {string}")
    public void dataDrivenTestExcel(String rows) throws InterruptedException
    {
        datamap=DataReader.data(System.getProperty("user.dir")+"\\testData\\Opencart_LoginData.xlsx", "Sheet1");

        int index=Integer.parseInt(rows)-1;
        String email= datamap.get(index).get("username");
        String pwd= datamap.get(index).get("password");
        String exp_res= datamap.get(index).get("res");

        lp=new LoginPage(driver);
        lp.setEmail(email);
        lp.setPassword(pwd);

        lp.clickLogin();
        try
        {
        	MyAccountPage myaccpage=new MyAccountPage(driver);
            boolean targetpage=myaccpage.msgInMyAccPage();
            
            if(exp_res.contains("Valid"))
            {
                if(targetpage==true)
                {
                    
                	myaccpage.clickLogout();
                    Assert.assertTrue(true);
                }
                else
                {
                    Assert.assertTrue(false);
                }
            }

            if(exp_res.contains("Invalid"))
            {
                if(targetpage==true)
                {
                	myaccpage.clickLogout();
                    Assert.assertTrue(false);
                }
                else
                {
                   Assert.assertTrue(true);
                }
            }
           
        }
        catch(Exception e)
        {
        	
            Assert.assertTrue(false);
        }
        
        
    }

}
