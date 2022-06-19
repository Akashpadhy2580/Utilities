package actionsClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test128
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
		driver.get("http://www.google.co.in");
		ActionsUtility au=new ActionsUtility(driver,wait);
		//move to element
		WebElement e1=driver.findElement(By.linkText("Images"));
		au.focusOnElementCenter(e1);
		//Right click on element
		WebElement e2=driver.findElement(By.name("q"));
		au.rightClickOnElement(e2);
		//Double click on element
		WebElement e3=driver.findElement(By.linkText("Gmail"));
		au.doubleClickOnElement(e3);
	}
}





