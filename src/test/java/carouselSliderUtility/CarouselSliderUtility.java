package carouselSliderUtility;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CarouselSliderUtility
{
	public int getCountOfSlides(WebElement slider)
	{
		int value=0;
		if(slider.getTagName().equalsIgnoreCase("ul") || 
				                  slider.getTagName().equalsIgnoreCase("ol"))
		{
			value=slider.findElements(By.xpath("child::li")).size();
		}
		else if(slider.getTagName().equalsIgnoreCase("div"))
		{
			List<WebElement> temp1=slider.findElements(By.xpath("child::div"));
			List<WebElement> temp2=new ArrayList<WebElement>();
			for(WebElement e:temp1)
			{
				try
				{
					if(!e.getAttribute("class").contains("clone"))
				    {
						if(e.getAttribute("data-clone").equals("false"))
						{
							temp2.add(e); //data-clone is false to an element
						}
				    }
				}
				catch(Exception ex)
				{
					temp2.add(e); //no data-clone attribute to an element, means visible
				}
			}
			value=temp2.size();
		}
		else
		{
			System.out.println("Unknown tag");
			System.exit(0);
		}
		return(value);
	}
	public String getTypeOfSlider(WebElement slider)
	{
		//"transform" and "transform-style" are CSS properties
		String value=slider.getCssValue("transform");
		if(value.equals("none"))
		{
			//take transform-style property value
			value=slider.getCssValue("transform-style");
		}
		//verification
		if(value.contains("3d"))
		{
			return("3D slider");
		}
		else
		{
			return("2D slider");
		}
	}
	public boolean areSlidesMovingAutomaticallyInSlider(WebElement slider)
	{
		String value=slider.getCssValue("transform");
		if(value.equals("none"))
		{
			//take transform-style property value
			value=slider.getCssValue("transform-style");
		}
		//verification
		if(value.contains("3d"))
		{
			String[] y=value.split(",");
			y[3]=y[3].trim();
			y[7]=y[7].trim();
			y[11]=y[11].trim();
			float tx=Float.parseFloat(y[3]);
			float ty=Float.parseFloat(y[7]);
			float tz=Float.parseFloat(y[11]);
			if(tx==0 && ty==0 && tz==0)
			{
			   return(false); //not moving automatically
			}	
			else
			{
				return(true); //moving automatically
			}
		}
		else
		{
			String[] y=value.split(",");
			y[4]=y[4].trim();
			y[5]=y[5].trim();
			y[5]=y[5].replace(")","");
			float tx=Float.parseFloat(y[4]);
			float ty=Float.parseFloat(y[5]);
			if(tx==0 && ty==0)
			{
			   return(false); //not moving automatically
			}
			else
			{
				return(true); //moving automatically
			}
		}
	}
	public String getMovingDirectionOfSlidesInSlider(WebElement slider)
	{
		String value=slider.getCssValue("transform");
		if(value.contains("3d"))
		{
			String[] y=value.split(",");
			y[3]=y[3].trim();
			y[7]=y[7].trim();
			y[11]=y[11].trim();
			float tx=Float.parseFloat(y[3]);
			float ty=Float.parseFloat(y[7]);
			float tz=Float.parseFloat(y[11]);
			if(tx<0 && ty==0 && tz==0)
			{
				return("Slides are moving from Right to left");
			}
			else if(tx>0 && ty==0 && tz==0)
			{
				return("Slides are moving from Left to Right");
			}
			else if(tx==0 && ty<0 && tz==0)
			{
				return("Slides are moving from Bottom to top");
			}
			else if(tx==0 && ty>0 && tz==0) 
			{
				return("Slides are moving from Top to bottom");
			}
			else if(tx==0 && ty==0 && tz<0)
			{
				return("Slides are moving from back to front");
			}
			else if(tx==0 && ty==0 && tz>0) 
			{
				return("Slides are moving from front to back");
			}
			else //tx, ty and tz all are zero
			{
				return("Not moving");
			}
		}
		else
		{
			String[] p=value.split(","); //comma(,) is separator
			p[4]=p[4].trim(); //remove unwanted spaces
			p[5]=p[5].trim(); //remove unwanted spaces
			p[5]=p[5].replace(")",""); //remove ")" symbol
			float tx=Float.parseFloat(p[4]);
			float ty=Float.parseFloat(p[5]);
			if(tx<0 && ty==0)
			{
				return("Slides are moving from Right to left");
			}
			else if(tx>0 && ty==0)
			{
				return("Slides are moving from Left to Right");
			}
			else if(tx==0 && ty<0)
			{
				return("Slides are moving from Bottom to top");
			}
			else if(tx==0 && ty>0) 
			{
				return("Slides are moving from Top to bottom");
			}
			else //tx and ty both are zero
			{
				return("Not moving");
			}
		}
	}
	public float getSlideMoveDuration(WebElement slider)
	{
		String x=slider.getCssValue("transition");
		String[] y=x.split(" ");
		y[1]=y[1].trim();
		y[1]=y[1].replace("s","");
		float duration=Float.parseFloat(y[1]);
		return(duration);
	}
	public String getslideMovingStyle(WebElement slider)
	{
		String x=slider.getCssValue("transition");
		String[] y=x.split(" ");
		y[2]=y[2].trim();
		if(y[2].equalsIgnoreCase("ease-in"))
		{
			return("Slow start");
		}
		else if(y[2].equalsIgnoreCase("ease-out"))
		{
			return("Slow end");
		}
		else
		{
			return("Slow start and slow end");
		}
	}
	public float getDelayBetweenSlidesMove(WebElement slider)
	{
		String x=slider.getCssValue("transition");
		String[] y=x.split(" ");
		y[3]=y[3].replaceAll("[s][,]?","");
		float delay=Float.parseFloat(y[3]);
		return(delay);
	}
}
