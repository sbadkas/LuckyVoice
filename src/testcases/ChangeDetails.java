package testcases;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import commonlib.FunctionGenerator;
import pages.DetailsPage;

public class ChangeDetails extends FunctionGenerator {

	protected WebDriver driver;
	protected Map<String, String> testdata;
	protected DetailsPage detailspage;

	@BeforeTest
	public void startup() {
		// Create and initialize driver
		System.out.println("Starting browser...");
		driver = getDriver();
		loginToLuckyVoice();
		loadTestData();
	}

	@Test
	public void changeAndVerifyAccountDetails() {
		
		// Navigate to account details page
		navigateToDetailsPage();

		waitForElementToBeClickable(detailspage.firstName);

		// Update account details
		sendInput(detailspage.firstName, testdata.get("firstname"));
		sendInput(detailspage.lastName, testdata.get("lastname"));
		sendInput(detailspage.email, testdata.get("email"));
		selectListByVisibleText(detailspage.day, testdata.get("day"));
		selectListByVisibleText(detailspage.month, testdata.get("month"));
		selectListByVisibleText(detailspage.year, testdata.get("year"));
		sendInput(detailspage.mobile, testdata.get("mobile"));
		selectListByVisibleText(detailspage.country, testdata.get("country"));
		sendInput(detailspage.postcode, testdata.get("postcode"));

		// Save account details
		detailspage.btnSave.click();
		System.out.println("New details are saved");

		//Logout from LuckyVoice
		navigateToDetailsPage();
		detailspage.logoutLink.click();
		System.out.println("User has logged out");

		// Navigate to account details page
		loginToLuckyVoice();
		navigateToDetailsPage();

		// Verify that account details are updated successfully
		assertEqualsForInput(detailspage.firstName, testdata.get("firstname"));
		assertEqualsForInput(detailspage.lastName, testdata.get("lastname"));
		assertEqualsForInput(detailspage.email, testdata.get("email"));
		assertEqualsForList(detailspage.day, testdata.get("day"));
		assertEqualsForList(detailspage.month, testdata.get("month"));
		assertEqualsForList(detailspage.year, testdata.get("year"));
		assertEqualsForInput(detailspage.mobile, testdata.get("mobile"));
		assertEqualsForList(detailspage.country, testdata.get("country"));
		assertEqualsForInput(detailspage.postcode, testdata.get("postcode"));
		System.out.println("New details are successfully updated");
	}

	private void loadTestData() {

		/* There are various ways to load the data eg. Excel files
		 * XML files, Text Files, CSV Files.
		 * For simplicity, I am using a HashMap.
		 */
		testdata = new HashMap<String, String>();

		testdata.put("firstname", "John");
		testdata.put("lastname", "Doe");
		testdata.put("email", "sharmila@test.luckyvoice.com");
		testdata.put("day", "10");
		testdata.put("month", "May");
		testdata.put("year", "1950");
		testdata.put("mobile", "99887766");
		testdata.put("country", "France");
		testdata.put("postcode", "E12345");
	}

	private void navigateToDetailsPage() {
		detailspage = PageFactory.initElements(driver, DetailsPage.class);
		detailspage.appLink.click();
		System.out.println("Clicked on App link");
		
		waitForElementToBeClickable(detailspage.heyYouLink);
		detailspage.heyYouLink.click();
		System.out.println("Clicked on Hey link");
	}

	@AfterTest
	public void cleanup() {
		System.out.println("Quiting browser...");
		driver.close();
		driver.quit();
		System.out.println("Test exited successfully");
	}
}
