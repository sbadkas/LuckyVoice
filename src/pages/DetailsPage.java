package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DetailsPage {

	@FindBy(linkText = "App")
	public WebElement appLink;

	@FindBy(xpath = ".//*[@id='nav-account'][contains(@title,'Hey')]")
	public WebElement heyYouLink;

	@FindBy(id = "id_name_0")
	public WebElement firstName;

	@FindBy(id = "id_name_1")
	public WebElement lastName;

	@FindBy(id = "id_email")
	public WebElement email;

	@FindBy(id = "id_dob_0")
	public WebElement day;

	@FindBy(id = "id_dob_1")
	public WebElement month;

	@FindBy(id = "id_dob_2")
	public WebElement year;

	@FindBy(id = "id_mobile_tel")
	public WebElement mobile;

	@FindBy(id = "id_country")
	public WebElement country;

	@FindBy(id = "id_postcode")
	public WebElement postcode;

	@FindBy(xpath = ".//input[@value='Save']")
	public WebElement btnSave;

	@FindBy(linkText = "Log out")
	public WebElement logoutLink;
}
