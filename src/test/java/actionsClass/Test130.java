package actionsClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test130
{
	public static void main(String[] args) 
	{
		//Open browser
		WebDriverManager.chromedriver().setup(); 
		RemoteWebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		//Define wait
		FluentWait<RemoteWebDriver> wait=new FluentWait<RemoteWebDriver>(driver);
		wait.withTimeout(Duration.ofSeconds(20));
		wait.pollingEvery(Duration.ofMillis(500));
		//Launch site
		driver.get("https://jqueryui.com/droppable");
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		WebElement e1=driver.findElement(By.id("draggable"));
		WebElement e2=driver.findElement(By.id("droppable"));
		ActionsUtility au=new ActionsUtility(driver,wait);
		au.dragDrop(driver, e1, e2);
		driver.switchTo().defaultContent();
	}
}





