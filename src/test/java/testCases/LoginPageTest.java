package testCases;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageRepository.LoginPage;
import utils.CommonUtilities;
import utils.ReadPropertiesFile;

public class LoginPageTest 
{
	WebDriver driver;
	CommonUtilities commonUtilities = new CommonUtilities();
	LoginPage loginPage;
	
	@BeforeMethod
	public void testSetUp()
	{
		driver = commonUtilities.invokeBrowser();
		loginPage = new LoginPage(driver);
	}
	
	@Test(description="Verify login functionality", priority=0)
	public void validateUserLogin()
	{
		loginPage.login(ReadPropertiesFile.getPropertyValue("username"), ReadPropertiesFile.getPropertyValue("password"));
	}
//	
//	@Test(description="Verify Failed functionality", priority=1)
//	public void validateUserLoginFail()
//	{
//		loginPage.login(ReadPropertiesFile.getPropertyValue("username"), ReadPropertiesFile.getPropertyValue("password"));
//		Assert.assertTrue(false);
//	}
//	
//	@Test(description="Verify Skip functionality", dependsOnMethods="validateUserLoginFail")
//	public void validateUserLoginSKIP()
//	{
//		loginPage.login(ReadPropertiesFile.getPropertyValue("username"), ReadPropertiesFile.getPropertyValue("password"));
//	}
	
	@AfterMethod
	public void tearDown()
	{
		commonUtilities.log.info("Start: Trying to close browser");
		driver.quit();
		commonUtilities.log.info("End: Successfully closed browser");
	}
}
