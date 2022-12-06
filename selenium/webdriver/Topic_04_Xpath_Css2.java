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
import org.testng.annotations.Test;

public class Topic_04_Xpath_Css2 {
	// Khai báo 1 biến đại diện cho Selenium Webdriver
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/basic-form/");
	}

	
	// Click vào my acoount link
	// driver.findElement(By.xpath("//div[@class='footer']//a[@title='My
	// Account']")).click();

	@Test
	public void TC_01_Verify_Text() {
		// 1- Get text của element đó ra => Lưu vào 1 biến
		// Biến này để kiểm tra text mong muốn hay khống -> Java String
		String populationValue = driver.findElement(By.xpath("//div[@id='population'] ")).getText(); 
		System.out.println(populationValue);
		Assert.assertTrue(populationValue.contains("Mongolia: 500-1,000"));
		
		// 2- Xpath check contains text có nằm trong element đó ko
		// Check display cái element có xpath đó (isDisplays)
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='population' and contains(string(),'Mongolia: 500-1,000')]")).isDisplayed());
	
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
