package siteUtilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

public class WebSiteUtility
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
	
	public FluentWait<RemoteWebDriver> defineWait(RemoteWebDriver driver, long max, long interval) throws Exception
	{
		FluentWait<RemoteWebDriver> wait=new FluentWait<RemoteWebDriver>(driver);
		wait.withTimeout(Duration.ofSeconds(max));
		wait.pollingEvery(Duration.ofMillis(interval));
		return(wait);
	}
	
	public void launchSite(RemoteWebDriver driver, String url) throws Exception
	{
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	public String visiblePageScreenshot(RemoteWebDriver driver) throws Exception
	{
		SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		Date dt=new Date();
		String fn=System.getProperty("user.dir")+"\\target\\"+sf.format(dt)+".png";
		File dest=new File(fn); //create a new file in HDD
		File src=driver.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src,dest);
		return(dest.getAbsolutePath());
	}
	
	public String fullPageScreenshot(RemoteWebDriver driver) throws Exception
	{
		SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		Date dt=new Date();
		String fn=System.getProperty("user.dir")+"\\target\\"+sf.format(dt)+".png";
		File dest=new File(fn); //create a new file in HDD
		AShot as=new AShot();
	    ShootingStrategy shs=ShootingStrategies.viewportPasting(1000);
	    Screenshot ss=as.shootingStrategy(shs).takeScreenshot(driver);
	    ImageIO.write(ss.getImage(),"PNG",dest); 
	    return(dest.getAbsolutePath());
	}
	
	public void closeSite(RemoteWebDriver driver)
	{
		driver.quit();
	}
}











