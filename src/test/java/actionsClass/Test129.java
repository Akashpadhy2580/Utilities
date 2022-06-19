package actionsClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test129
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
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q"))).sendKeys("bse sensex",Keys.ENTER);
		//move to specific location of element
		WebElement e=wait.until(ExpectedConditions.visibilityOfElementLocated(
		  By.xpath("((//*[text()='BSE SENSEX'])[2]/following::*[name()='svg']//*[name()='g'])[1]")));
		ActionsUtility au=new ActionsUtility(driver,wait);
		au.focusOnElementLocation(e,200,10);
	}
}





