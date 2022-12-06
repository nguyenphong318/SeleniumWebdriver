package webdriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_10_Alert {
   WebDriver driver;
   Alert alert;
   WebDriverWait explicitWait;
   String projectPath = System.getProperty("user.dir");
   String authenChromeAutoIT = projectPath + "\\AutoIT\\authen_chrome.exe";
   String authenFirefoxAutoIT = projectPath + "\\AutoIT\\authen_firefox.exe";

   @BeforeClass
   public void beforeClass() {
	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
	driver = new ChromeDriver();
	explicitWait = new WebDriverWait(driver, 15);
	
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
}
	
    
    public void TC_01_Accept_Alert() {
    	driver.get("http://demo.guru99.com/v4/");
    	
    	driver.findElement(By.name("btnLogin")).click();
    	sleepInSecond(2);
    	
    	//Wait cho alert xuất hiện trong vòng 15 giây và switch qua alert luôn
    	alert = explicitWait.until(ExpectedConditions.alertIsPresent());
    	
    	//swtich to: Alert/Windown/Tab/Frame/Iframe. Switch qua 1 alert
    	//alert = driver.switchTo().alert();
    	
    	//Get text của alert và verify
    	Assert.assertEquals(alert.getText(), "User or Password is not valid");
    	
    	//Accept alert: alert biến mất (OK)
    	alert.accept();
    	
    	System.out.println(alert.getText());
    }
    
    public void TC_02_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        	
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        sleepInSecond(2);
        
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        
        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        
        alert.accept();
        
        Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You clicked an alert successfully");
    }
    
    
    public void TC_03_Confirm_Alert() {
    	 driver.get("https://automationfc.github.io/basic-form/index.html");
    	 
    	 driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
    	 sleepInSecond(2);
    	 
    	 alert = explicitWait.until(ExpectedConditions.alertIsPresent());
    	 
    	 Assert.assertEquals(alert.getText(), "I am a JS Confirm");
    	 
    	 alert.accept();
    	 
    	 Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You clicked: Ok");
    	 
    	 driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
    	 sleepInSecond(2);
    	 
    	 alert = explicitWait.until(ExpectedConditions.alertIsPresent());
    	 
    	 Assert.assertEquals(alert.getText(), "I am a JS Confirm");
    	 
    	 alert.dismiss();
    	 
    	 Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You clicked: Cancel");
    }
	
    
    public void TC_04_Promt_Alert() {
    	 driver.get("https://automationfc.github.io/basic-form/index.html");
    	 
    	 driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
    	 sleepInSecond(2);
    	 
    	 alert = explicitWait.until(ExpectedConditions.alertIsPresent());
    	 
    	 Assert.assertEquals(alert.getText(), "I am a JS Prompt");
    	 
    	 String addressName = "Ho Chi Minh";
    	 
    	 alert.sendKeys(addressName);
    	 sleepInSecond(4);
    	 
    	 alert.accept();
    	 
    	 Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You entered: " + addressName);
    	 
    	 driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
    	 sleepInSecond(2);
    	 
    	 alert = explicitWait.until(ExpectedConditions.alertIsPresent());
    	 
    	 Assert.assertEquals(alert.getText(), "I am a JS prompt");
    	 
    	 alert.sendKeys(addressName);
    	 sleepInSecond(4);
    	 
    	 alert.dismiss();
    	 
    	 Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You entered: null");
    }
    
    public void TC_05_Authentication_Alert() {
    	String username = "admin";
    	String password = "admin";
    	String url = "http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth";
    	
    	driver.get(url);
    	
    	Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials')]")).isDisplayed());
    }
    
    public void TC_07_Authentication_Alert_AutoIT() throws IOException {
    	String username = "admin";
    	String password = "admin";
    	String url = "http://the-internet.herokuapp.com/basic_auth";
    	
    	if (driver.toString().contains("chrome")) {
    		Runtime.getRuntime().exec(new String[] { authenChromeAutoIT, username, password });
    	} else {
    		Runtime.getRuntime().exec(new String[] { authenChromeAutoIT, username, password });
    	}
    		
    	driver.get(url);
    	
    	Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials')]")).isDisplayed());
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
