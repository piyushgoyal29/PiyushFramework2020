package testCases;


import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageRepository.LoginPage;
import utils.CommonUtilities;
import utils.FindBrokenLinks;
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
	
//	@Test(description="Verify login functionality", priority=0)
//	public void validateUserLogin()
//	{
//		loginPage.login(ReadPropertiesFile.getPropertyValue("username"), ReadPropertiesFile.getPropertyValue("password"));
//		String actualURL = commonUtilities.verifyURL();
//		Assert.assertEquals(actualURL, "https://edulence.knowledgelink.tv/dashboard/enrolled", "Validating URL after login");
//	}
	
	@Test(description="Verify if links are broken", priority=1)
	public void validateBrokenLinks() throws IOException
	{
		loginPage.login(ReadPropertiesFile.getPropertyValue("username"), ReadPropertiesFile.getPropertyValue("password"));
	
		SoftAssert softAssert = new SoftAssert();
		List<Integer> responseCode = FindBrokenLinks.verifyLinks();
		for(int i=0;i<responseCode.size();i++)
		{
			softAssert.assertEquals((int)responseCode.get(i), 200);
		}
		softAssert.assertAll();
	}
	
	@AfterMethod
	public void tearDown()
	{
//		commonUtilities.quitBrowser();
	}
}
