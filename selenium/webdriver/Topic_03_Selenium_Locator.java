package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_03_Selenium_Locator {
	// Khai báo 1 biến đai diện cho Selenium Webdriver
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		// Mở trình duyệt lên
		
		// Set timeout cho element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		// Mở application lên (AUT/SUT)
				driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	} 
	
	@Test
	public void TC_01_FindElement() {
		// Single element ( tìm 1 element)
		WebElement loginButton = driver.findElement(By.className(""));
		loginButton.click();
		
		driver.findElement(By.className("")).getText();
		
		// find Element: tìm element By.xxx vs locator nào Action j lên element: click/ sendkey/ getText/...
		
		// Multiple element (tìm nhiều element) List<WebElement>
		List<WebElement> buttons = driver.findElements(By.className(""));
		buttons.get(0).click();
	}
	
	@Test
	public void TC_02_ID() {
		driver.findElement(By.id("send2")).click();
		
		//Verify error massage xuất hiện
		Assert.assertTrue(driver.findElement(By.id("advice-require-entry-email")).isDisplayed());
	}
	
	@Test
	public void TC_03_Class() {
		driver.navigate().refresh();
		
		driver.findElement(By.className("validate-password")).sendKeys("123456789");
		
	}
	
	@Test
	public void TC_04_Name() {
		driver.navigate().refresh();
		
		driver.findElement(By.name("send")).click();
		
		//Verify error massage xuáº¥t hiá»‡n
		Assert.assertTrue(driver.findElement(By.id("advice-require-entry-email")).isDisplayed());
		
	}
	
	@Test
	public void TC_05_Tagname() {
		// Láº¥y háº¿t táº¥t cáº£ Ä‘Æ°á»�ng link táº¡i mÃ n hÃ¬nh nÃ y sau Ä‘Ã³ getText ra
		List<WebElement> loginPageLinks = driver.findElements(By.tagName("a"));
		for (WebElement webElement : loginPageLinks) 
			System.out.println(webElement.getText());
			
	}
	
	@Test
	public void TC_06_LinkText() {
		driver.navigate().refresh();
		
		driver.findElement(By.linkText("Forgot Your Password?")).click();
		
		Assert.assertTrue(driver.findElement(By.id("email_address")).isDisplayed());
	}
	
	@Test
	public void TC_07_PartialLinkText() {
		driver.findElement(By.partialLinkText("Back to")).click();
		
		Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());
		
	}
	
	@Test
	public void TC_08_CSS() {
		driver.findElement(By.cssSelector("#email")).sendKeys("phong@gmail.com");
		driver.findElement(By.cssSelector("input[name='login[password]']")).sendKeys("123456789");
	}
	
	@Test
	public void TC_09_Xpath() {
		driver.navigate().refresh();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("phong@gmail.com");
		driver.findElement(By.xpath("input[name='login[password]']")).sendKeys("123456789");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
