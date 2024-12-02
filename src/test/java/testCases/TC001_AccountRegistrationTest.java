package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass {


	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("***********  Started TC001_AccountRegistrationTest ********");
		try 
		{
		HomePage hp = new HomePage(driver);
		
		hp.clickmyAccount();
		logger.info("Clicked on MyAccount Link");
		hp.clickRegister();
		logger.info("Clicked on Register Link");
		
		AccountRegistrationPage acc_reg = new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details ...");
		acc_reg.setFirstName(randomString().toUpperCase());
		acc_reg.setLastName(randomString().toUpperCase());
		acc_reg.setEmail(randomString()+"@gmail.com");		// randomly generated the email
		acc_reg.setTelephone(randomNumber());
		
		String passowrd = randomAlphaNumberic();
		acc_reg.setPassword(passowrd);
		acc_reg.setConiformPassword(passowrd);
		acc_reg.setSubcribenewsletter();
		acc_reg.setPrivayBtn();
		acc_reg.clickContinue();
		
		logger.info("Validating expected message .. ");
		String confmsge = acc_reg.getConfirmationMsg();
		
		Assert.assertEquals(confmsge, "Your Account Has Been Created!");
		
		}
		catch(Exception e){
			logger.error("Test failed ..");
			logger.error("Debug logs ..");
			Assert.fail();
		}
		logger.info("***********  Finished TC001_AccountRegistrationTest ********");
	}
	
	
}
