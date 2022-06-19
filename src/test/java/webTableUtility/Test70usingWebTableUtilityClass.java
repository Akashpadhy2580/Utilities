package webTableUtility;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test70usingWebTableUtilityClass 
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
		driver.findElement(By.name("identifier")).sendKeys("prac.dot@gmail.com");
		driver.findElement(By.xpath("//span[text()='Next']/parent::button")).click();
		Thread.sleep(10000);
		driver.findElement(By.name("password")).sendKeys("Akash@123");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		Thread.sleep(50000);
		
		int dmc=0;
		while(true)   //infinite loop
		{
			//locate table in current page 
			WebElement wt=driver.findElement(By.xpath("(//table)[7]"));
			//create object to utility class
			WebTableUtilityClass obj=new WebTableUtilityClass();
			List<WebElement> mails=obj.getRows(wt);
			//go to each row
			for(int i=0; i<mails.size(); i++)
			{
				WebElement temp=mails.get(i).findElement(By.xpath("child::td[4]/div[2]/span[1]/span"));
				String value=temp.getAttribute("email");
				if(value.equals("no-reply@accounts.google.com"))
				{
					//Delete current mail by selecting check box and click on delete
					WebElement cb=mails.get(i).findElement(By.xpath("child::td[2]/div[@role='checkbox']"));
					driver.executeScript("arguments[0].scrollIntoView();", cb);
					cb.click();
					Thread.sleep(2000);
					//click on delete
					mails.get(i).findElement(By.xpath("(child::td[9]/ul/li)[2]")).click();
					Thread.sleep(2000);
					//close output msg banner
					driver.findElement(By.className("bBe")).click();
					Thread.sleep(2000);
					//relocate rows in table once again to avoid stale issue
					mails=obj.getRows(wt);
					i--; //to cover next mail, who replaced deleted mail
					dmc++; //increase deleted mail count
				}
			}
			//click on next to go to next page
			try
			{
				String x=driver.findElement(By.xpath("//div[@aria-label='Older']")).getAttribute("aria-disabled");
				if(x.equals("true"))
				{
					break; //terminate from infinite loop because we are in last page
				}
			}
			catch(Exception ex)
			{
				driver.findElement(By.xpath("//div[@aria-label='Older']")).click();
				Thread.sleep(2000);
			}
		
		}
		System.out.println(dmc);
		//close site
		driver.close();
	}
}