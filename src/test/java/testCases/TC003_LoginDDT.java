package testCases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="DataDriven")		// getting data provider from different class
	public void verify_loginDDT(String email, String pwd, String exp)
	{
	
	logger.info("******** Starting TC003_LoginDDT ******");
	try {
		
	
	HomePage hp = new HomePage(driver);
	hp.clickmyAccount();
	hp.clickLogin();
	
	// login Page
	LoginPage lp = new LoginPage(driver);
	lp.setEmail(email);
	lp.setPassword(pwd);
	lp.clickLogin();


	MyAccountPage mac = new MyAccountPage(driver);
	boolean targetPage=mac.isMyAccountPageExists();
	
/*
 * Data is valid    -- login success -- test pass -- logout
 * Data is valid   -- login failed -- test failed   
 * 
 * Data is invalid    -- login success -- test failed -- logout
 * Data is invalid   -- login failed -- test  pass  
 * 
 */
	if(exp.equalsIgnoreCase("Valid"))
	{
		if(targetPage==true)
		{
			Assert.assertTrue(true);
			mac.clickLogout();
		}
		else
		{
			Assert.assertTrue(false);
		}
	}
	
	if(exp.equalsIgnoreCase("Invalid"))
	{
		if(targetPage==true)
		{
			mac.clickLogout();
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
		Assert.fail();
	}
	logger.info("******** Finished TC003_LoginDDT ******");
	}
}
	
