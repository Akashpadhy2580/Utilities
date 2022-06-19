package webTableUtility;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test68usingWebTableUtilityClass 
{

	public static void main(String[] args) throws Exception 
	{
		//open browser
		WebDriverManager.chromedriver().setup();
		RemoteWebDriver driver=new ChromeDriver();
		
		//Launch site
		driver.get("https://www.gmail.com");
		Thread.sleep(5000);
		
		//do login
		driver.findElement(By.name("identifierId")).sendKeys("prac.dot@gmail.com");
		driver.findElement(By.xpath("//span[text()='Next']/parent::button")).click();
		Thread.sleep(10000);
		driver.findElement(By.name("password")).sendKeys("Akash@123");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		Thread.sleep(100000);
		
		int rmc=0;
		int urmc=0;
		while(true)
		{
		
			//locate mail-box table
			WebElement wt=driver.findElement(By.xpath("(//table)[7]"));
			//create an object of utility class
			WebTableUtilityClass obj=new WebTableUtilityClass();
			List<WebElement> rows=obj.getRows(wt);
			for(WebElement row:rows)
			{
				WebElement temp =row.findElement(By.xpath("child::td[4]/div[1]"));
				String value=(String)driver.executeScript("return(arguments[0].textContent);", temp);
				if(value.startsWith("unread"))
				{
					urmc++;
				}
				else
				{
					rmc++;
				}
			}
			//click on next to go to next page
			try
			{
				String x=driver.findElement(By.xpath("//div[@aria-label='Older']")).getAttribute("aria-disabled");
				if(x.equals("true"))
				{
					break;
				}
			}
			catch(Exception ex)
			{
				driver.findElement(By.xpath("//div[@aria-label='Older']")).click();
				Thread.sleep(2000);
			}
		}
		System.out.println("Count of read mails: "+rmc);
		System.out.println("Count of unread mails: "+urmc);
		
		//close site
		driver.close();
	}
}
