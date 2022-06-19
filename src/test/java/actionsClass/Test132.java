package actionsClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test132
{
	public static void main(String[] args)
	{
		//slider
		//Open browser
		WebDriverManager.chromedriver().setup(); 
		RemoteWebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		//Define wait
		FluentWait<RemoteWebDriver> wait=new FluentWait<RemoteWebDriver>(driver);
		wait.withTimeout(Duration.ofSeconds(20));
		wait.pollingEvery(Duration.ofMillis(500));
		//Launch site
		driver.get("https://jqueryui.com/slider");
		//Horizontal slider
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		WebElement e1=driver.findElement(By.id("slider"));
		ActionsUtility au=new ActionsUtility(driver,wait);
		au.moveSlider(e1, 100, 0);
		au.moveSlider(e1,-100, 0);
		driver.switchTo().defaultContent();
		//Vertical slider
		wait.until(ExpectedConditions.elementToBeClickable(
				                 By.linkText("Vertical slider"))).click();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		WebElement e2=wait.until(ExpectedConditions.elementToBeClickable(
				                                   By.id("slider-vertical")));
		au.moveSlider(e2, 0, 100);
		au.moveSlider(e2, 0, -100);
		driver.switchTo().defaultContent();
	}
}





