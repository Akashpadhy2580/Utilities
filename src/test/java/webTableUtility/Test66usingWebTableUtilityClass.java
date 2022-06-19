package webTableUtility;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test66usingWebTableUtilityClass 
{

	public static void main(String[] args) throws Exception 
	{
		//open browser
		WebDriverManager.chromedriver().setup();
		RemoteWebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		//Launch site
		driver.get("https://www.espncricinfo.com/series/australia-in-pakistan-2021-22-1288300/pakistan-vs-australia-1st-test-1288310/full-scorecard");
		Thread.sleep(15000);
		
		//locate Score table
		WebElement wt=driver.findElement(By.xpath("//h5[contains(text(),'Australia 1st INNINGS')]/following::table[1]"));
		
		//declare utility class object
		WebTableUtilityClass obj=new WebTableUtilityClass();
		int nor=obj.getRowsCount(wt);
		System.out.println(nor);
		System.out.println(obj.getColumnsCountInARow(wt, 5));
	
		//skip alternate rows, which have borders
		for(int i=1 ;i<nor ;i=i+2)
		{
			
			List<WebElement> batsman=obj.getCellChilds(wt, i, 1, By.xpath("child::a"));
			String name=(String)driver.executeScript("return(arguments[0].textContent);", batsman.get(0));
			String status;
			try
			{
				List<WebElement> out=obj.getCellChilds(wt, i, 2,  By.xpath("child::span"));
				status=(String)driver.executeScript("return(arguments[0].textContent);", out.get(0));
			}
			catch(Exception ex)
			{
				status=obj.getCellValue(driver, wt, i, 2);
			}
			String x1=obj.getCellValue(driver, wt, i ,3);
			String x2=obj.getCellValue(driver, wt, i ,4);
			String x3=obj.getCellValue(driver, wt, i ,5);
			String x4=obj.getCellValue(driver, wt, i ,6);
			String x5=obj.getCellValue(driver, wt, i ,7);
			String x6=obj.getCellValue(driver, wt, i ,8);
			
			System.out.println(name+ " --->" +status+ " --->" +x1+ " --->" +x2+ " --->" +x3+ " --->" +x4+ " --->" +x5+ " --->" +x6 );
		}
		//close site
		driver.close();

	}
}
