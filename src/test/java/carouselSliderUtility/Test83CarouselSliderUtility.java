package carouselSliderUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test83CarouselSliderUtility 
{

	public static void main(String[] args) throws Exception 
	{
		//open browser
		WebDriverManager.chromedriver().setup();
		RemoteWebDriver driver=new ChromeDriver();
		
		//launch site
		driver.get("https://www.ebay.com/");
		Thread.sleep(5000);
		
		//locate carousel slider
		WebElement slider=driver.findElement(By.xpath("//ul[@class='carousel__list']"));
		Thread.sleep(5000);
		
		//testing(common code)
		CarouselSliderUtility obj=new CarouselSliderUtility();
		System.out.println(obj.getCountOfSlides(slider));
		System.out.println(obj.getTypeOfSlider(slider));
		System.out.println(slider.getCssValue("transform"));
		if(obj.areSlidesMovingAutomaticallyInSlider(slider))
		{
			System.out.println("Moving automatically");
			System.out.println(slider.getCssValue("transition"));
			System.out.println(obj.getSlideMoveDuration(slider));
			System.out.println(obj.getslideMovingStyle(slider));
			System.out.println(obj.getDelayBetweenSlidesMove(slider));
			System.out.println(obj.getMovingDirectionOfSlidesInSlider(slider));
		}
		else
		{
			System.out.println("Not Moving automatically");
		}
		
		//close site
		driver.close();
	}

}
