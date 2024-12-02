package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Pipe.SourceChannel;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups={"Sanity","Regression","Master","DataDriven"})
	@Parameters({"os","browser"})
	public void setup(String os, @Optional("chrome") String br) throws IOException
	{
		// loading config.properties
		
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p= new Properties();
		p.load(file);
			
		logger = LogManager.getLogger(this.getClass());			//LOG4J2
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capablilities = new DesiredCapabilities();
			
			//choosing os
			if(os.equalsIgnoreCase("windows"))
			{
				capablilities.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capablilities.setPlatform(Platform.WIN10);
			}
			else
			{
				System.out.println("No matching os");
				return;
				
			}
			
			// choosing browser
			switch(br.toLowerCase())
			{
			case "chrome": capablilities.setBrowserName("chrome"); break;
			case "edge": capablilities.setBrowserName("MicrosoftEdge"); break;
			default: System.out.println("No matching broswer name.."); 
						return;
			}
			driver= new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capablilities);
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			
			System.setProperty("webdriver.chrome.driver","C:\\Eclipse downloads\\chromedriver-win64\\chromedriver.exe");
		switch(br.toLowerCase())
		{
		case "chrome": driver=new ChromeDriver(); break;
		case "edge": driver=new EdgeDriver(); break;
		case "firefox": driver=new FirefoxDriver(); break;
		default : System.out.println("invalid broswer name.."); 
					return;
		}
		}
		
		
		
		//driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL")); // reading url from config.prop
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups={"Sanity","Regression","Master","DataDriven"})
	public void teardown()
	{
		driver.quit();
	}
	
	public String randomString()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
		
	}
	
	public String randomNumber()
	{
		String generatednumber=RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}
	
	public String randomAlphaNumberic()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(5);
		String generatednumber=RandomStringUtils.randomNumeric(5);
		return (generatedstring+"@"+generatednumber);
	}
	
	public String captureScreen(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot =(TakesScreenshot)driver;
		File sourceFile= takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+ tname +"_"+timeStamp+".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	
	}
}
