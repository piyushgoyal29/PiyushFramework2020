package utils;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import utils.ReadPropertiesFile;

public class CommonUtilities 
{
	public static WebDriver driver;
	public Logger log = Logger.getLogger(CommonUtilities.class);
	public Actions actions;
	
	public WebDriver invokeBrowser() 
	{			
		String browserName = ReadPropertiesFile.getPropertyValue("browser");
		
		if(browserName.contains("chrome"))
		{
			log.info("Start: Trying to invoke Chrome Browser");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\BrowserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			log.info("Finish: Successfully invoked Chrome Browser");
		}
		
		else if(browserName.contains("firefox"))
		{
			log.info("Start: Trying to invoke Firefox Browser");
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\BrowserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			log.info("Finish: Successfully invoked Firefox Browser");
		}
		
		else if(browserName.contains("internet"))
		{
			log.info("Start: Trying to invoke IE Browser");
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\BrowserDrivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			log.info("Finish: Successfully invoked IE Browsery");
		}
		
		try
		{
			log.info("Start: Trying to maximize browser");
			driver.manage().window().maximize();
			log.info("End: Successfully maximized browser window");
		}
		catch(Exception e)
		{
			log.info("End: Browser was already maximized");
			//Browser is already maximized
		}
		
//		webDriverListener = new WebDriverEventListenerClass();
//		eventFiringDriver = new EventFiringWebDriver(driver);
//		eventFiringDriver.register(webDriverListener);
//		driver = eventFiringDriver;
		
		driver.manage().deleteAllCookies();
//		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		String url = ReadPropertiesFile.getPropertyValue("url");
		log.info("Start: Trying to navigate to URL- "+url);
		driver.get(url);
		log.info("End: Successfully navigated to URL- "+url);
		return driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	
	public WebElement locateElement(By locatorValue)
	{
		log.info("Start: Trying to locate element- "+locatorValue);
		WebElement element = driver.findElement(locatorValue);
		log.info("End: Successfully located element- "+locatorValue);
		return element;
	}
	
	public void enterData(By locatorValue, String data)
	{
		log.info("Start: Trying to insert data- "+data +" in field - "+locatorValue);
//		locateElement(locatorValue).clear();
		locateElement(locatorValue).sendKeys(data);
		log.info("End: Successfully inserted data- "+data +" in field - "+locatorValue);
	}
	
	public void clickOnElement(By locatorValue)
	{
		log.info("Start: Trying to click element- "+locatorValue);
		locateElement(locatorValue).click();
		log.info("End: Successfully clicked on element- "+locatorValue);
	}

	public Actions createActionsObject()
	{
		return actions = new Actions(driver);
	}
	public void moveCursorToElement(By acceptLoginPopup)
	{
		log.info("Start: Trying to move to element- "+acceptLoginPopup);
		createActionsObject().moveToElement(locateElement(acceptLoginPopup)).click().build().perform();
		log.info("End: Successfully moved to element- "+acceptLoginPopup);
	}
	
	public void dragAndDropMovement(By source, By destination)
	{
		log.info("Start: Trying to drag from element - " +source +" to element- "+destination);
		createActionsObject().dragAndDrop(locateElement(source), locateElement(destination)).build().perform();
		log.info("End: Successfully moved from element - " +source +" to element- "+destination);
	}
	
	public String verifyTitle()
	{
		log.info("Fetching the title of the webpage");
		return driver.getTitle();
	}
	
	public String verifyURL()
	{
		log.info("Fetching the URL of the webpage");
		return driver.getCurrentUrl();
	}
	
	public void quitBrowser()
	{
		log.info("Start: Trying to close browser");
		driver.quit();
		log.info("End: Successfully closed browser");
	}
}
