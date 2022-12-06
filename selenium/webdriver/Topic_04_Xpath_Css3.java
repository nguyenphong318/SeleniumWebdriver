package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Css3 {
	// Khai báo 1 biến đại diện cho Selenium Webdriver
	WebDriver driver;

	String name, emailAddress, password, phone;
	// Action
	By nameTextboxBy = By.id("txtFirstname");
	By emailTextboxBy = By.id("txtEmail");
	By confirmEmailTextboxBy = By.id("txtCEmail");
	By passwordTextboxBy = By.id("txtPassword");
	By confirmPasswordTextboxBy = By.id("txtCPassword");
	By phoneTextboxBy = By.id("txtPhone");
	By registerButtonBy = By.xpath("//form[@id='frmLogin']//button");

	// Error
	By nameErrorMsgBy = By.id("txtFirstname-error");
	By emailErrorMsgBy = By.id("txtEmail-error");
	By confirmEmailErrorMsgBy = By.id("txtCEmail-error");
	By passwordErrorMsgBy = By.id("txtPassword-error");
	By confirmPasswordErrorMsgBy = By.id("txtCPassword-error");
	By phoneErrorMsgBy = By.id("txtPhone-error");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		name = "Phong Nguyen"; 
		emailAddress = "phong@gmail.com"; 
		password = "123456" ; 
		phone = "0869009691";
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC_01_Empty() {
		driver.findElement(registerButtonBy).click();

		Assert.assertEquals(driver.findElement(nameErrorMsgBy).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(emailErrorMsgBy).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(confirmEmailErrorMsgBy).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(passwordErrorMsgBy).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(confirmPasswordErrorMsgBy).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Vui lòng nhập số điện thoại.");

	}
	
	@Test
	public void TC_02_Invalid_Email() {
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys("123@123.234@");
		driver.findElement(confirmEmailTextboxBy).sendKeys("123@123.234@");
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirmPasswordTextboxBy).sendKeys(password);
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(emailErrorMsgBy).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(confirmEmailErrorMsgBy).getText(), "Email nhập lại không đúng");
	}
	
	@Test
	public void TC_03_Incorrect_Confirm_Email() {
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextboxBy).sendKeys("automation@gmail.com");
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirmPasswordTextboxBy).sendKeys(password);
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(confirmEmailErrorMsgBy).getText(), "Email nhập lại không đúng");
	}
	
	@Test
	public void TC_04_Password_Less_Than_6_Chars() {
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys("123");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("123");
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(passwordErrorMsgBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(confirmPasswordErrorMsgBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	}
	
	@Test
	public void TC_05_Incorrect_Confirm_Password() {
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys("123456");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("654321");
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(confirmPasswordErrorMsgBy).getText(), "Mật khẩu bạn nhập không khớp");
	}
	
	@Test
	public void TC_06_Invalid_Phone() {
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys("123456");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("123456");
		driver.findElement(phoneTextboxBy).sendKeys(emailAddress);
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Vui lòng nhập con số");
		
		// Clear dữ liệu đã nhập đi để nhập lại
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(phoneTextboxBy).sendKeys("0987654");
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Số điện thoại phải từ 10-11 số.");
		
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(phoneTextboxBy).sendKeys("098765432100");
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Số điện thoại phải từ 10-11 số.");
		
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(phoneTextboxBy).sendKeys("123456");
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
