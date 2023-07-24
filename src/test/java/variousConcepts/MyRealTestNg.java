package variousConcepts;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyRealTestNg {

	WebDriver driver;
	/*
	 * String browesr; String url;
	 */
	// Element list
	By USER_NAME_FIELD = By.xpath("//input[@id='username']");
	By PASSWORD_FIELD = By.xpath("//input[@id='password']");
	By SIGNIN_BUTTON_FIELD = By.xpath("//button[@name='login']");
	By DASHBOARD_HEADER_FIELD = By.xpath("//h2[text()=' Dashboard ']");
	By CUSTOMER_FIELD = By.xpath("//span[text()='Customers']");
	By ADD_CUSTOMER_FIELD = By.xpath("//a [text()='Add Customer']");
	By ADD_CONTACT_HEADER_FIELD=By.xpath("//h5[text()='Add Contact']");
	
	By FULLNAME_FIELD=By.xpath("//input[@id='account']");
	By COMPANY_FIELD=By.xpath("//select[@id='cid']");
	By EMAIL_FIELD=By.xpath("//input[@id='email']");
	By PHONE_FIELD=By.xpath("//input[@id='phone']");
	By COUNTRY_FIELD=By.xpath("//select[@id='country']");

	// mock/test data
	String user_name = "demo@techfios.com";
	String password = "abc123";
	String fullname = "Abu";
	String comapny = "Apple";
	String email = "abu@gmail.com";
	String  phone= "2435431290";
	String country = "Zambia";

	/*
	 * @BeforeClass public void readConfig() { try { InputStream input = new
	 * FileInputStream("src\\test\\java\\config\\config.properties"); Properties
	 * prop = new Properties(); prop.load(input); browesr =
	 * prop.getProperty(browesr); url = prop.getProperty(url);
	 * 
	 * } catch (IOException e) { e.printStackTrace(); }
	 * 
	 * }
	 */

	@BeforeMethod
	public void init() throws InterruptedException {

		/*
		 * if (browesr.equalsIgnoreCase("chrome")) {
		 * System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		 * ChromeOptions options = new ChromeOptions();
		 * options.addArguments("--remote-allow-origins=*"); driver = new
		 * ChromeDriver(options); } else if (browesr.equalsIgnoreCase("edge")) {
		 * System.setProperty("webdriver.chrome.driver", "drivers\\msedgedriver.exe");
		 * EdgeOptions options = new EdgeOptions();
		 * options.addArguments("--remote-allow-origins=*"); driver = new
		 * EdgeDriver(options); }
		 */

		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		// driver = new ChromeDriver(); for some reason this is not working, so I //
		// used // the next 3 lines to launch the driver 
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);

		driver.manage().deleteAllCookies();
		driver.get("https://techfios.com/billing/?ng=admin/");
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test(priority=1)
	public void login() {
		driver.findElement(USER_NAME_FIELD).sendKeys(user_name);
		driver.findElement(PASSWORD_FIELD).sendKeys(password);
		driver.findElement(SIGNIN_BUTTON_FIELD).click();

		Assert.assertEquals(driver.findElement(DASHBOARD_HEADER_FIELD).getText(), "Dashboard",
				"Dashboard header field is not found!!");

	}
	@Test(priority=2)
	public void addCustomer() {
		login();
		
		driver.findElement(CUSTOMER_FIELD).click();
		driver.findElement(ADD_CUSTOMER_FIELD).click();
		Assert.assertEquals(driver.findElement(ADD_CONTACT_HEADER_FIELD).getText(), "Add Contact", "Add contact header field is not avialable!!");
		
		driver.findElement(FULLNAME_FIELD).sendKeys(fullname);
		dropDown(driver.findElement(COMPANY_FIELD), comapny);
		driver.findElement(EMAIL_FIELD).sendKeys(generateRandomNum(999) +email);
		driver.findElement(PHONE_FIELD).sendKeys(phone+ generateRandomNum(99));
		dropDown(driver.findElement(COUNTRY_FIELD), country);
		
	}

	private void dropDown(WebElement locator, String value) {
		Select sel= new Select(locator);
		sel.selectByVisibleText(value);
	}

	private int generateRandomNum(int boundNum) {
		Random rnd= new Random();
		int generatedNum=rnd.nextInt(boundNum);
		return generatedNum;
	}
}
