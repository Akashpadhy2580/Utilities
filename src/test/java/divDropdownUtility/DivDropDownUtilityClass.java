package divDropdownUtility;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DivDropDownUtilityClass 
{
	public boolean isMultiSelect(WebElement dd)
	{
		if(dd.getAttribute("class").contains("multiple"))
		{
			return(true);
		}
		else
		{
			return(false);
		}
	}
	
	public int getItemsCount(WebElement dd)
	{
		int value=dd.findElements(By.xpath("child::div[contains(@class,'menu')]/div")).size();
		return(value);
	}
	
	public List<String> getItemsNames(RemoteWebDriver driver, WebElement dd)
	{
		List<String> values=new ArrayList<String>();
		List<WebElement> items=dd.findElements(
				By.xpath("child::div[contains(@class,'menu')]/div"));
		for(WebElement item:items)
		{
		  values.add((String)driver.executeScript("return(arguments[0].textContent);",item));
		}
		return(values);
	}
	
	public void selectItemByIndex(WebElement dd, int x) throws Exception
	{
		dd.click(); //open drop down
		Thread.sleep(5000);
		List<WebElement> items=dd.findElements(
				               By.xpath("child::div[contains(@class,'menu')]/div"));
		if(items.size()<x)
		{
			System.out.println("Wrong index"); 
			System.exit(0); //stop execution forcibly
		}
		else
		{
			items.get(x-1).click(); //1st item index is 0 in collections
		}
	}
	
	public void selectItemByName(WebElement dd, String x) throws Exception
	{
		dd.click(); //open drop down
		Thread.sleep(5000);
		List<WebElement> items=dd.findElements(
				By.xpath("child::div[contains(@class,'menu')]/div"));
		int flag=0;
		for(WebElement item:items)
		{
			if(item.getText().equalsIgnoreCase(x))
			{
				item.click(); //select that item
				flag=1;
				break;
			}
		}	
		if(flag==0)
		{
			System.out.println("Item not fond in drop-down");
			System.exit(0); //stop execution forcibly
		}
	}
	
	public void selectItemsByIndexes(WebElement dd, int[] x) throws Exception
	{
		dd.click(); //open drop down
		Thread.sleep(5000);
		List<WebElement> items=dd.findElements(
				               By.xpath("child::div[contains(@class,'menu')]/div"));
		for(int i=0; i<x.length; i++)
		{
			if(items.size()<x[i])
			{
				System.out.println("Wrong index"); 
				System.exit(0); //stop execution forcibly
			}
			else
			{
				items.get(x[i]-1).click(); //1st item index is 0 in collections
				Thread.sleep(2000);
			}
		}
	}
	
	public void selectItemsByNames(WebElement dd, String[] x) throws Exception
	{
		dd.click(); //open drop down
		Thread.sleep(5000);
		List<WebElement> items=dd.findElements(
				               By.xpath("child::div[contains(@class,'menu')]/div"));
		for(int i=0; i<x.length; i++)
		{
			int flag=0;
			for(WebElement item:items)
			{
				if(item.getText().equalsIgnoreCase(x[i]))
				{
					item.click(); //select that item
					Thread.sleep(5000);
					flag=1;
					break;
				}
			}	
			if(flag==0)
			{
				System.out.println("Item nt foud in drop-down");
				System.exit(0); //stop execution forcibly
			}
		}
	}
	
	public void selectAllItems(WebElement dd) throws Exception
	{
		dd.click(); //open drop down
		Thread.sleep(5000);
		//collect all items
		List<WebElement> items=dd.findElements(
				               By.xpath("child::div[contains(@class,'menu')]/div"));
		//Select all items one by one
		for(WebElement item:items)
		{
			item.click(); //select that item
		}
	}
	
	public void deselectItemsByIndexes(WebElement dd, int[] x) throws Exception
	{
		//collect all selected items only
		List<WebElement> items=dd.findElements(By.xpath("child::a"));
		for(int i=0; i<x.length; i++)
		{
			if(items.size()<x[i])
			{
				System.out.println("Wrong index"); 
				System.exit(0); //stop execution forcibly
			}
			else
			{
				items.get(x[i]-1).findElement(By.xpath("child::i[@class='delete icon']")).click(); 
				Thread.sleep(2000);
			}
		}
	}
	
	public void deselectItemsByNames(WebElement dd, String[] x) throws Exception
	{
		//collect all selected items only
		List<WebElement> items=dd.findElements(By.xpath("child::a"));
		for(int i=0; i<x.length; i++)
		{
			int flag=0;
			for(WebElement item:items)
			{
				if(item.getText().equalsIgnoreCase(x[i]))
				{
					item.findElement(By.xpath("child::i[@class='delete icon']")).click();
					Thread.sleep(5000);
					flag=1;
					//Recollect existing items to avoid stale issue
					items=dd.findElements(By.xpath("child::a")); 
					break;
				}
			}	
			if(flag==0)
			{
				System.out.println("Item not foud in drop-down");
				System.exit(0); //stop execution forcibly
			}
		}
	}
	
	public void deselectAllItems(WebElement dd) throws Exception
	{
		//collect all selected items only
		List<WebElement> items=dd.findElements(By.xpath("child::a"));
		for(WebElement item:items)
		{
			item.findElement(By.xpath("child::i[@class='delete icon']")).click();
			items=dd.findElements(By.xpath("child::a")); //locate again due to stale issue
		}
	}
	
	public String getSelectedItemInSingleSelect(WebElement dd)
	{
		WebElement sitem=dd.findElement(By.xpath("child::div[@class='text']"));
		return(sitem.getText());
	}
	
	public String getFirstSelectedItem(WebElement dd)
	{
		List<WebElement> sitems=dd.findElements(By.xpath("child::a"));
		return(sitems.get(0).getText());
	}
	
	public String getLastSelectedItem(WebElement dd)
	{
		List<WebElement> sitems=dd.findElements(By.xpath("child::a"));
		return(sitems.get(sitems.size()-1).getText());
	}
	
	public List<String> getAllSelectedItems(WebElement dd)
	{
		List<String> l=new ArrayList<String>(); //empty list
		List<WebElement> sitems=dd.findElements(By.xpath("child::a"));
		for(WebElement sitem:sitems)
		{
			l.add(sitem.getText()); //add to list
		}
		return(l);
	}
}
