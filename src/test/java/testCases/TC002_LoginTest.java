package testCases;

import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups={"Sanity","Master"})
	public void verify_login() {
		
		logger.info("******** Starting TC002_LoginTest ******");
		try {
		
		// Home page
		HomePage hp = new HomePage(driver);
		hp.clickmyAccount();
		hp.clickLogin();
		
		// login Page
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		MyAccountPage mac = new MyAccountPage(driver);
		boolean targetPage=mac.isMyAccountPageExists();
		
		Assert.assertEquals(targetPage, true);
		//Assert.assertTrue(targetPage);	
	}
	catch(Exception e) {
		Assert.fail();
	}
		logger.info("******** Finished TC002_LoginTest ******");	
}
}
