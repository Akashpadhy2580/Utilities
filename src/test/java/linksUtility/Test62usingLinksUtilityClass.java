package linksUtility;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test62usingLinksUtilityClass 
{

	public static void main(String[] args) throws Exception 
	{
		//open browser
		WebDriverManager.chromedriver().setup();
		RemoteWebDriver driver=new ChromeDriver();
		
		//Launch site
		driver.get("https://www.gmail.com");
		Thread.sleep(5000);
		//get all broken links in home page
		callMethods(driver);
		//do login
		driver.findElement(By.name("identifier")).sendKeys("prac.dot");
		driver.findElement(By.xpath("//span[text()='Next']/parent::button")).click();
		Thread.sleep(10000);
		driver.findElement(By.name("password")).sendKeys("Akash@123");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		Thread.sleep(10000);
		//find broken links in compose page
		callMethods(driver);
		//do logout
		driver.findElement(By.xpath("//a[starts-with(@aria-label,'Google Account')]/img")).click();
		Thread.sleep(5000);
		driver.findElement(By.linkText("Sign out")).click();
		Thread.sleep(5000);
		//find broken links in re-login page
		callMethods(driver);
		
		//close site
		driver.close();

	}
	
	public static void callMethods(RemoteWebDriver driver)
	{
		LinksUtilityClass obj=new LinksUtilityClass(driver);
		System.out.println("Count of all links: "+obj.getLinksCount());
		//find broken links
		List<String> rs=obj.getBrokenLinks();
		System.out.println("Count of broken links: "+rs.size());
		for(String r:rs)
		{
			System.out.println(r);
		}
	}
}
