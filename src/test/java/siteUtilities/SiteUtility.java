package siteUtilities;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SiteUtility
{
	//Operational methods
	public RemoteWebDriver openBrowser(String browsername)
	{
		RemoteWebDriver driver;
		if(browsername.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();  
		}
		else if(browsername.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(browsername.equalsIgnoreCase("opera"))
		{
			WebDriverManager.operadriver().setup();
			driver=new OperaDriver();
		}
		else if(browsername.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else
		{
			//Set IE browser zoom level to 100% manually
			//System.setProperty("webdriver.ie.driver","path of iedriverserver.exe software");
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();	
		}
		return(driver);
	}
	
	public FluentWait<RemoteWebDriver> defineWait
	                                   (RemoteWebDriver driver, int max, long interval) 
	{
		FluentWait<RemoteWebDriver> wait=new FluentWait<RemoteWebDriver>(driver);
		wait.withTimeout(Duration.ofSeconds(max));
		wait.pollingEvery(Duration.ofMillis(interval));
		return(wait);
	}
	
	public void launchSite(RemoteWebDriver driver, String env) throws Exception
	{
		String url=null;
		switch(env)
		{
			case "QA":
				url="http://www.google.co.in";
				break;
			case "STG":
				url="http://www.google.co.in";
				break;
			case "DEV":
				url="http://www.google.co.in";
				break;
		}
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	public void closeSite(RemoteWebDriver driver)
	{
		driver.quit();
	}

}
