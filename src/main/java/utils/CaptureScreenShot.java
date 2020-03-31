package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreenShot extends CommonUtilities
{
	public String getScreenshot(String screenshotName) 
	{
		
		//To retrieve the current date and time.
		String timeStamp = GetDateAndTime.currentDateAndTime();

		//Convert WebDriver object into TakeScreenshot
		TakesScreenshot screenShot =((TakesScreenshot)driver);
		
		//Call getScreenshotAs method to create an image file
		File sourceFile= screenShot.getScreenshotAs(OutputType.FILE);

		String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" +screenshotName +"_"+timeStamp +".png";
				
		try {
			FileUtils.copyFile(sourceFile, new File(screenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenshotPath;
	}
}
