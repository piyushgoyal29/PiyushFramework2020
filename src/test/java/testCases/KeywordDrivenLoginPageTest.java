package testCases;


import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageRepository.LoginPage;
import utils.CommonUtilities;
import utils.KeywordDrivenDataProcess;
import utils.ReadExcelSheet;
import utils.ReadPropertiesFile;

public class KeywordDrivenLoginPageTest 
{
	WebDriver driver;
	
	@Test()
	public void login() throws InterruptedException{
		String filePath = ReadPropertiesFile.getPropertyValue("excelFilePathKD");
		String sheetName = ReadPropertiesFile.getPropertyValue("excelSheetNameKD");
		KeywordDrivenDataProcess process = new KeywordDrivenDataProcess();
		try {
			process.performAction(filePath, sheetName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
