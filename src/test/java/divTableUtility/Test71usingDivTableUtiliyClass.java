package divTableUtility;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test71usingDivTableUtiliyClass 
{

	public static void main(String[] args) throws Exception 
	{
		//open browser
		WebDriverManager.chromedriver().setup();
		RemoteWebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
				
		//Launch site
		driver.get("https://www.spicejet.com");
		FluentWait<RemoteWebDriver> wait=new FluentWait<RemoteWebDriver>(driver);
		wait.pollingEvery(Duration.ofMillis(500));
		wait.withTimeout(Duration.ofSeconds(20));
		//search flights between given stations
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='From']/following::input[1]"))).sendKeys("Bengaluru (BLR)");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='To']/following::input[1]"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='To']/following::input[1]"))).sendKeys("Delhi (DEL)");
		WebElement e=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Departure Date']")));
		driver.executeScript("arguments[0].scrollIntoView();",e );
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()='24'])[1]"))).click(); //date
		

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-testid='home-page-flight-cta']"))).click(); //wait for flights table

		//flight table developed using <div> tag 
		WebElement dt=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='SpiceMAX']/following::div[2]")));
		DivTableUtilityClass obj=new DivTableUtilityClass();
		int nof=obj.getChildDIVCount(dt);
		System.out.println("Count of flights is "+nof);
		List<WebElement> flights=obj.getChildDIVs(dt);
		for(WebElement flight:flights)
		{
			By b=By.xpath("child::div/div[2]/div[1]//span/parent::div");
			String value=obj.getDIVValue(driver, flight, b);
			String num=value.replaceAll("[^0-9]",""); //replace other than digits with blank
			System.out.println(num);
		}
		 //close site
		driver.close();
	}

}
