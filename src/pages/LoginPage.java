package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	@FindBy(id = "nav-account")
	public WebElement loginLink;

	@FindBy(id = "id_email")
	public WebElement txtEmail;

	@FindBy(id = "id_password")
	public WebElement txtPassword;

	@FindBy(xpath = ".//input[@value='Log in']")
	public WebElement btnLogin;

	@FindBy(id = "lv-player-footer")
	public WebElement lvPlayer;
}
