package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Css {
	// Khai báo 1 biến đại diện cho Selenium Webdriver
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	
	// Click vào my acoount link
	// driver.findElement(By.xpath("//div[@class='footer']//a[@title='My
	// Account']")).click();

	@Test
	public void TC_01_Login_Empty_Email_And_Password() {
		// Nhập dữ liệu vào 1 textbox
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.name("login[password]")).sendKeys("");

		// Click vào button
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		// Get error massage của email và password
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),
		"This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),
		"This is a required field.");
	}

	@Test
	public void TC_02_Login_Invalid_Email() {
		// Refresh page
		driver.navigate().refresh();

		// Nhập dữ liệu vào 1 textbox
		driver.findElement(By.id("email")).sendKeys("1234@123.321");
		driver.findElement(By.name("login[password]")).sendKeys("123456");

		// Click vào button
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		// Get error massage của email
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),
		"Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_03_Login_Invalid_Password() {
		// Refresh page
		driver.navigate().refresh();

		// Nhập dữ liệu vào 1 textbox
		driver.findElement(By.id("email")).sendKeys("phong@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123");

		// Click vào button
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		// Get error massage của password
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),
		"Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_04_Login_Incorrect_Email() {
		// Refresh page
		driver.navigate().refresh();

		// Nhập dữ liệu vào 1 textbox
		driver.findElement(By.id("email")).sendKeys("phong123123@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123456");

		// Click vào button
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		// Get error massage của password
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),
		"Invalid login or password.");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
