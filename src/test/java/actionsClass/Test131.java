package actionsClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test131
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
        driver.get("https://www.w3schools.com/css/css_tooltip.asp");
        ActionsUtility au=new ActionsUtility(driver,wait);
        WebElement e1=
        		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("BOOTSTRAP")));
		System.out.println(au.getStaticToolTipText(e1));
		WebElement e2=wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[contains(text(),'Top')]")));
		System.out.println(au.getDynamicToolTipText(e2));
		//close site
		driver.close();
	}
}





