package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_17_Wait_1 {
   WebDriver driver;
   WebDriverWait explicitWait;
   String projectPath = System.getProperty("user.dir");

   @BeforeClass
   public void beforeClass() {
	//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	//driver = new FirefoxDriver();
	
	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
	driver = new ChromeDriver();
	
	explicitWait = new WebDriverWait(driver, 20);

	driver.manage().window().maximize();
	
}
	
    
    public void TC_01_Visble() {
    	driver.get("https://www.facebook.com/");
    	
    	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']"))).click();
    	
    	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']"))).sendKeys("phong@gmail.com");
    	
    	//Wait cho element được visable/display trước khi thao tác
    	WebElement confirmEmailTextbox = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	
    	confirmEmailTextbox.sendKeys("phong@gmail.com");
	
    }
    
    
    public void TC_02_Invisble_Not_In_DOM() {
    	driver.get("https://www.facebook.com/");
    	
    	//Element ko có hiển thị: Ko có trên UI + có trong DOM
    	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
    	
    }
    
    @Test
    public void TC_03_Presence_In_UI() {
    	driver.get("https://www.facebook.com/");
    	
    	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']"))).click();
    	
    	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']"))).sendKeys("phong@gmail.com");
    	
    	explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
    
    }
    
    @Test
    public void TC_03_Presence_Not_In_UI() {
    	driver.get("https://www.facebook.com/");
    	
    	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']"))).click();
    	
    	
    	explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
    
    }
    	
	@AfterClass
	public void afterClass() {
	driver.quit();
    }
	
	public void sleepInSecond(long timeoutlnSecond) {
		try {
			Thread.sleep(timeoutlnSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
