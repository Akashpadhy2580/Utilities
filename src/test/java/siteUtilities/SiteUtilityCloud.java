package siteUtilities;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;

public class SiteUtilityCloud
{
	//Operational methods
	public RemoteWebDriver openBrowser(String browsername, String osname) throws Exception
	{
		String un="Akash2580";
		String akey="0376a441-94e6-4b85-a816-e94a63cd28db";
		String endpoint="http://"+un+":"+akey+"@ondemand.saucelabs.com:80/wd/hub";
		URL u=new URL(endpoint);
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setBrowserName(browsername);
		if(osname.equalsIgnoreCase("mac"))
		{
			dc.setCapability("platform",Platform.MAC);
		}
		else if(osname.equalsIgnoreCase("linux"))
		{
			dc.setCapability("platform",Platform.LINUX);
		}
		else if(osname.contains("10"))
		{
			dc.setCapability("platform",Platform.WIN10);
		}
		else
		{
			dc.setCapability("platform",Platform.VISTA);
		}
		RemoteWebDriver	driver=new RemoteWebDriver(u,dc);  
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
				url="http://www.google.com";
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
