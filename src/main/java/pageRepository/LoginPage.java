package pageRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.CommonUtilities;

public class LoginPage extends CommonUtilities
{
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	private	By emailAddress = By.id("email");
	private By password = By.id("password");
	private By signInBtn = By.xpath("//button[@type='submit']");
	private By acceptLoginPopup = By.xpath("//button[@ng-click='submit()']");
	
	public WebElement getEmailAddress()
	{
		return locateElement(emailAddress);
	}
	
	public WebElement getPassword()
	{
		return locateElement(password);
	}
	
	public WebElement getSignInBtn()
	{
		return locateElement(signInBtn);
	}
	
	public WebElement getAcceptLoginPopup()
	{
		return locateElement(acceptLoginPopup);
	}
	
	public void login(String receivedEmailAddress, String receivedPassword)
	{
		enterData(emailAddress, receivedEmailAddress);
		enterData(password, receivedPassword);
		clickOnElement(signInBtn);
		clickOnElement(acceptLoginPopup);
	}
}
