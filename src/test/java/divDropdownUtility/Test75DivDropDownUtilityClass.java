package divDropdownUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test75DivDropDownUtilityClass
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
		//select multiple items by giving indexes
		int x[]= {2, 4, 6, 8};
		obj.selectItemsByIndexes(dd, x);
		Thread.sleep(5000);
		//de-select multiple items by giving indexes
		int y[]= {1, 3};
		obj.deselectItemsByIndexes(dd, y);
		Thread.sleep(5000);
	}
}






