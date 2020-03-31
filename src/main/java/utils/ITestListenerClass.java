package utils;


import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ITestListenerClass extends CommonUtilities implements ITestListener 
{
	public ExtentReports extentReports;
	public ExtentTest extentTest;
	public CaptureScreenShot captureScreenShot;
	
	public void onStart(ITestContext context) {
		log.info("##############################################################");
		log.info("Starting execution of Test Suite");
		String timeStamp = GetDateAndTime.currentDateAndTime();
		extentReports = new ExtentReports(System.getProperty("user.dir")+"\\Reports\\ExtentReports_"+timeStamp+".html", true, DisplayOrder.NEWEST_FIRST);
		extentReports.addSystemInfo("Host Name", "Piyush Windows");
		extentReports.addSystemInfo("User Name", "Piyush Automation World");
		extentReports.addSystemInfo("Environment", "QA");
		
	}
	
	public void onTestStart(ITestResult result) {
		log.info("==============================================================");
		log.info("Executing Test Case : "+result.getName());
		extentTest = extentReports.startTest(result.getName());
//		extentReports.startTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		log.info("Successfully Executed Test Case : "+result.getName());
		log.info("==============================================================");
		
		extentTest.log(LogStatus.PASS, "Test Case Name: " + result.getName());
		extentReports.endTest(extentTest);
	}

	public void onTestFailure(ITestResult result) //ITestResult is a class which provides the details on the result. Like the name of the method which failed, class details, etc.
	{
		log.info("Test Case execution Failed : "+result.getName());
		log.info("==============================================================");
		
//		Object currentClass = result.getInstance();
//		WebDriver driver = ((CommonUtilities) currentClass).getDriver();
		
		//To retrieve the current date and time.
		String timeStamp = GetDateAndTime.currentDateAndTime();
		//Convert WebDriver object into TakeScreenshot
		TakesScreenshot screenShot =((TakesScreenshot)driver);
		//Call getScreenshotAs method to create an image file
		
		String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" +result.getName() +"_"+timeStamp +".png";
		System.out.println(screenshotPath);		
		
		File sourceFile= screenShot.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(sourceFile, new File(screenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		String screenshotPath = captureScreenShot.getScreenshot(result.getName());
		extentTest.log(LogStatus.FAIL, "Test Case Name: "+result.getName()); //to add name in extent report
		extentTest.log(LogStatus.FAIL, "Exception/Error details: "+result.getThrowable()); //to add error/exception in extent report
		extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
		extentReports.endTest(extentTest);

	}

	public void onTestSkipped(ITestResult result) {
		log.info("Skipping Test Case : "+result.getName());	
		log.info("==============================================================");
		extentTest.log(LogStatus.SKIP, "Test Case Name: " + result.getName());
		extentReports.endTest(extentTest);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onFinish(ITestContext context) {
		log.info("Finished execution of Test Suite");
		log.info("##############################################################");
		extentReports.flush();
//		extentReports.close();
	}

}
