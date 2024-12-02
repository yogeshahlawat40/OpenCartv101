package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage
{

	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtemail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txttelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConiformPassword;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement privacyAgree;
	
	@FindBy(xpath="//label[normalize-space()='Yes']")
	WebElement subscibenewsletter;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	
	
	public void setFirstName(String fname)
	{
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txtLastName.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txtemail.sendKeys(email);
	}
	
	public void setTelephone(String phone)
	{
		txttelephone.sendKeys(phone);
	}
	
	
	public void setPassword(String pass)
	{
		txtPassword.sendKeys(pass);
	}
	
	public void setConiformPassword(String pass)
	{
		txtConiformPassword.sendKeys(pass);
	}
	
	public void setSubcribenewsletter()
	{
		subscibenewsletter.click();
	}
	
	public void setPrivayBtn()
	{
		privacyAgree.click();
	}
	public void clickContinue()
	{
		//btnContinue.click();
		
		btnContinue.submit();
		
	}
	
	public String getConfirmationMsg() {
		try {
			return (msgConfirmation.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
		
	}
}
