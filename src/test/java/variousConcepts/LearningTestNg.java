package variousConcepts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LearningTestNg {

	WebDriver driver;
	
	// Element List
		By USER_NAME_FIELD = By.xpath("//input[@id='username']");
		By PASSWORD_FIELD = By.xpath("//input[@id='password']");
		By SIGN_IN_BUTTON_FIELD = By.xpath("//button[@name='login']");
		By DASHBOARD_HEADER_FIELD = By.xpath("//h2[text()=' Dashboard ']");
		By CUSTOMER_MENU_FIELD = By.xpath("//span[contains(text(), 'Customers')]");
		By ADD_CUSTOMER_MENU_FIELD = By.xpath("//a[contains(text(), 'Add Customer')]");
		By ADD_CUSTOMER_HEADER_FIELD = By.xpath("//h5[contains(text(), 'Add Contact')]");
		By FULL_NAME_FIELD = By.xpath("//*[@id=\"account\"]");
		By COMPANY_DROPDOWN_FIELD = By.xpath("//select[@id='cid']");
		By EMAIL_FIELD = By.xpath("//*[@id=\"email\"]");
		By COUNTRY_DROPDOWN_FIELD = By.xpath("//select[@id='country']");

		// Test/Mock Data
		String userName = "demo@techfios.com";
		String password = "abc123";
		String fullName = "selenium";
		String company = "Techfios";
		String email = "demo@techfios.com";
		String country = "Afghanistan";
@BeforeMethod
	public void init() {
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		//driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		 driver = new ChromeDriver(options);
		
		driver.manage().deleteAllCookies();
		driver.get("https://techfios.com/billing/?ng=admin/");
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
@Test
	public void logintest() {
		driver.findElement(USER_NAME_FIELD).sendKeys(userName);
		driver.findElement(PASSWORD_FIELD).sendKeys(password);
		driver.findElement(SIGN_IN_BUTTON_FIELD).click();
	}
}
