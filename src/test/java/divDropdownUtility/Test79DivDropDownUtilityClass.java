package divDropdownUtility;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test79DivDropDownUtilityClass
{
	public static void main(String[] args) throws Exception
	{
		//Demo on multi select drop-down
		//open browser
		WebDriverManager.chromedriver().setup();
		RemoteWebDriver driver=new ChromeDriver();
		//Launch site
		driver.get("https://semantic-ui.com/modules/dropdown.html");
		Thread.sleep(5000);
		//Locate a <div> tag drop-down
		WebElement dd=driver.findElement(
				By.xpath("//a[@id='multiple-selection']/following-sibling::div"));
		//select multiple items by giving names
		DivDropDownUtilityClass obj=new DivDropDownUtilityClass();
		String x[]= {"CSS", "HTML", "Ember", "Angular"};
		obj.selectItemsByNames(dd, x);
		Thread.sleep(5000);
		System.out.println(obj.getFirstSelectedItem(dd));
		System.out.println(obj.getLastSelectedItem(dd));
		//Get and test all selected items
		List<String> y=obj.getAllSelectedItems(dd);
		if(Arrays.asList(x).equals(y))
		{
			System.out.println("Test passed");
		}
		else
		{
			System.out.println("Test failed");
		}
		//close site
		driver.close();
	}
}