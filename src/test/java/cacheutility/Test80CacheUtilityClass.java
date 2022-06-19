package cacheutility;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test80CacheUtilityClass
{
	public static void main(String[] args) throws Exception
	{
		//get a word from keyboard
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a word");
		String word=sc.nextLine();
		sc.close();
		
		//open browser
		WebDriverManager.chromedriver().setup();
		RemoteWebDriver driver=new ChromeDriver();
		//Launch site
		driver.get("https://google.co.in");
		Thread.sleep(5000);
		 //locate cache element after entering a value
		driver.findElement(By.name("q")).sendKeys(word);
		Thread.sleep(10000);
		WebElement cache=driver.findElement(By.xpath("(//div[@role='presentation']/ul[@role='listbox'])[1]"));
		Thread.sleep(5000);
		//get count of all suggestions
		CacheUtilityClass obj=new CacheUtilityClass();
		System.out.println(obj.getItemsCount(cache));
		Thread.sleep(5000);

		//get all suggestions
		List<String> suggestions=obj.getAllSuggestions(cache);
		for(String suggestion:suggestions)
		{
			System.out.println(suggestion);
		}
		
		//verify cache
		if(obj.isValidCache(cache, word))
		{
			System.out.println("Test Passed");
		}
		else
		{
			System.out.println("Test Failed");
			List<String> mismatches=obj.getMismatchedSuggestions(cache, word);
			for(String mismatch:mismatches)
			{
				System.out.println(mismatch);
			}
		}
		
		//close site
		driver.close();
	}
}