package divDropdownUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test78DivDropDownUtilityClass
{
	public static void main(String[] args) throws Exception
	{
		//Demo on single select drop-down
		//open browser
		WebDriverManager.chromedriver().setup();
		RemoteWebDriver driver=new ChromeDriver();
		//Launch site
		driver.get("https://semantic-ui.com/modules/dropdown.html");
		Thread.sleep(5000);
		//Locate a <div> tag drop-down
		WebElement dd=driver.findElement(
				By.xpath("(//option[text()='State'])[1]/ancestor::div[1]"));
		//Get items count and items values
		DivDropDownUtilityClass obj=new DivDropDownUtilityClass();
		//select an item by giving value
		String x="california";
		obj.selectItemByName(dd,x);
		Thread.sleep(5000);
		//get selected item and test it
		String y=obj.getSelectedItemInSingleSelect(dd);
		if(x.equalsIgnoreCase(y))
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






