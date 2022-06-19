package webTableUtility;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebTableUtilityClass 
{
	public List<WebElement> getRows(WebElement wt)
	{
		List<WebElement> rows=wt.findElements(By.xpath("child::tbody/tr"));
		return(rows);
	}
	
	public int getRowsCount(WebElement wt)
	{
		int value=wt.findElements(By.xpath("child::tbody/tr")).size();
		return(value);
	}
	
	public int getColumnsCountInARow(WebElement wt, int rownum)
	{
		int value=wt.findElements(By.xpath("child::tbody/tr["+rownum+"]/td")).size();
		return(value);
	}
	
	public String getCellValue(RemoteWebDriver driver, WebElement wt, int rownum , int colnum)
	{
		WebElement e=wt.findElement(By.xpath("child::tbody/tr["+rownum+"]/td["+colnum+"]"));
		String value=(String)driver.executeScript("return(arguments[0].textContent);", e);
		return(value);
	}
	
	public List<WebElement> getCellChilds(WebElement wt, int rownum , int colnum, By b)
	{
		List<WebElement> eles=wt.findElement(By.xpath("child::tbody/tr["+rownum+"]/td["+colnum+"]"))
																.findElements(b);
		return(eles);
	}
	
	
}
