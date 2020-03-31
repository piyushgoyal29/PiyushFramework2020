

package utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class KeywordDrivenDataProcess 
{
	String filePath;
	String sheetName;
	Object[][] data;
	WebDriver driver;
	
	private By getElementLocator(String locatorType, String locatorValue)
	{
		switch(locatorType)
		{
			case "id": 
					return By.id(locatorValue);

			case "name": 
					return By.name(locatorValue);
			case "className": 
					return By.className(locatorValue);			
			case "tagName": 
					return By.tagName(locatorValue);			
			case "cssSelector": 
					return By.cssSelector(locatorValue);
			case "xpath": 
					return By.xpath(locatorValue);
			default:
					return By.id(locatorValue);
		}
	}

	public void performAction(String filePath, String sheetName) throws IOException
	{
		ReadExcelSheet obj1 = new ReadExcelSheet();
		data = obj1.getCellData(filePath, sheetName);
		int j=0;
		for(int i=0; i<data.length; i++)
		{
			String testStep = data[i+1][j].toString();
			String locatorType = data[i+1][j+1].toString();
			String locatorValue = data[i+1][j+2].toString();
			String action = data[i+1][j+3].toString();
			String value = data[i+1][j+4].toString();
//			System.out.println(action);
//			System.out.println(locatorType);
//			System.out.println(locatorValue);
//			System.out.println(value);
			switch(action)
			{
				case "openBrowser" :
						System.setProperty("webdriver.chrome.driver", "D:\\Piyush\\RadicalAutomation\\RadicalSoftwares\\chromedriver_win32_B39\\chromedriver.exe");
						driver = new ChromeDriver();
						driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
						break;
				case "enterUrl" :
						driver.get(value);
						break;
				case "click": 
						driver.findElement(getElementLocator(locatorType, locatorValue)).click();
						break;
				case "enterText":
						driver.findElement(getElementLocator(locatorType, locatorValue)).clear(); 
						driver.findElement(getElementLocator(locatorType, locatorValue)).sendKeys(value);	
						break;
				case "close": 
						driver.close();
						break;
				case "isDisplayed":
						driver.findElement(getElementLocator(locatorType, locatorValue)).isDisplayed();
						break;
				case "":
						driver.findElement(getElementLocator(locatorType, locatorValue)).click();
						break;
				default:
						break;
			}
			
		}
	}
}
