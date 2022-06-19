package divDropdownUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test77DivDropDownUtilityClass
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
		//Check for Single-select or Multi-select
		DivDropDownUtilityClass obj=new DivDropDownUtilityClass();
		if(obj.isMultiSelect(dd))
		{
			System.out.println("Multi select drop-down");
		}
		else
		{
			System.out.println("Single select drop-down");
		}
		//select multiple items by giving names
		obj.selectAllItems(dd);
		Thread.sleep(5000);
		//de-select multiple items by giving names
		obj.deselectAllItems(dd);
	}
}