package testCases;


import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageRepository.LoginPage;
import utils.CommonUtilities;
import utils.ReadExcelSheet;
import utils.ReadPropertiesFile;

public class DataDrivenLoginPageTest 
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
	
	@Test(dataProvider="MyDataProvider")
	public void enterDetails(String firstName, String lastName) throws InterruptedException{
		loginPage.login(firstName,lastName);
		Thread.sleep(2000);
		driver.close();
	}
	
	
	@DataProvider(name="MyDataProvider")
	public Object[][] getData() throws IOException
	{
		String filePath = ReadPropertiesFile.getPropertyValue("excelFilePathDD");
		String sheetName = ReadPropertiesFile.getPropertyValue("excelSheetNameDD");
		ReadExcelSheet obj1 = new ReadExcelSheet();
		Object[][] data = obj1.getCellData(filePath, sheetName);
		
		return data;
	}
	
	@AfterMethod
	public void tearDown()
	{
		commonUtilities.log.info("Start: Trying to close browser");
		driver.quit();
		commonUtilities.log.info("End: Successfully closed browser");
	}
}
