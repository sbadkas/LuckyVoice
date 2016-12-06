package commonlib;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pages.LoginPage;

public class FunctionGenerator {

	private WebDriver driver;
	private Properties prop;
	protected LoginPage loginpage;

	protected WebDriver getDriver() {
		driver = new FirefoxDriver();
		driver.get(getPropertyFromFile("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}

	private Properties getFileContent() {
		// load config.propeties file to read the data
		FileInputStream fs;
		try {
			fs = new FileInputStream("config.properties");
			prop = new Properties();
			prop.load(fs);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return prop;
	}

	private String getPropertyFromFile(String property) {
		prop = getFileContent();
		return prop.getProperty(property);
	}

	protected void waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	protected void selectListByVisibleText(WebElement listName, String value) {
		Select setValue = new Select(listName);
		setValue.selectByVisibleText(value);
	}

	protected void assertEqualsForInput(WebElement element, String expected) {
		String actual = element.getAttribute("value");
		Assert.assertEquals(actual, expected, "Assertion failed");
	}

	protected void assertEqualsForList(WebElement element, String expected) {
		String actual = new Select(element).getFirstSelectedOption().getText();
		Assert.assertEquals(actual, expected, "Assertion failed");
	}

	protected void sendInput(WebElement element, String input) {
		element.clear();
		element.sendKeys(input);
		System.out.println("Input data: " + input);
	}

	protected void loginToLuckyVoice() {
		// Initialize page factory
		loginpage = PageFactory.initElements(driver, LoginPage.class);

		loginpage.loginLink.click();
		waitForElementToBeClickable(loginpage.txtEmail);
		loginpage.txtEmail.sendKeys(getPropertyFromFile("username"));
		loginpage.txtPassword.sendKeys(getPropertyFromFile("password"));
		loginpage.btnLogin.click();
		System.out.println("User is logged in");

		// Verify that online player appears after logging in to the system
		waitForElementToBeClickable(loginpage.lvPlayer);
		System.out.println("Online player is present");
	}
}
