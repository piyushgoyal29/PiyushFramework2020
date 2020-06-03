package utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FindBrokenLinks extends CommonUtilities
{
	public static List<Integer> verifyLinks() throws IOException
	{
		List<WebElement> totalLinks = driver.findElements(By.tagName("a"));
		/*
		 * Above code will get all the anchor tags. Now by using this anchor tags, 
		 * we will fetch the URL present inside the href attribute. 
		 * However, there might be some anchor tags:
		 * 	1. without href attribute-> so we will filter those anchor tags
		 * 	2. with href attribute but the value of that attribute might contain javascript code instead of the URL-> so we will filter those anchor tags
		 * 	3. with href attribute but empty value -> so we will filter those anchor tags
		 * Now, we will create a new LIST which has all the anchor tags excluded in it.
		 */
		
		List<WebElement> actualLinks = new ArrayList<WebElement>(); 
		
		for(int i=0; i<totalLinks.size(); i++)
		{
			if((!totalLinks.get(i).getAttribute("href").isEmpty()) && totalLinks.get(i).getAttribute("href") != null && (!totalLinks.get(i).getAttribute("href").contains("javascript")))
			{
				actualLinks.add(totalLinks.get(i));
			}
		}
		
		List<Integer> responseCode = new ArrayList<>();
		for(int j=0;j<actualLinks.size();j++)
		{
			System.out.println("---->"+actualLinks.get(j).getAttribute("href"));
			
			//Create a object of URL class and pass URL of each href attribute
			URL url = new URL(actualLinks.get(j).getAttribute("href"));
			
			//We need to make a connection with the URL using openConnection() method and cast it into HttpURLConnection object
			HttpURLConnection huc = (HttpURLConnection)url.openConnection();
			
			//Now call connect() method to make a final connection with the URL.
			huc.connect();
			
			responseCode.add(huc.getResponseCode());
			
//			If we want to get response messages like OK, Page not found, etc then use below code.
//			String responseMessage = huc.getResponseMessage();
		}
		return responseCode;
	}
	
}
