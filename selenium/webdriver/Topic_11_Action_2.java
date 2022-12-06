package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_11_Action_2 {
   WebDriver driver;
   JavascriptExecutor jsExecutor;
   String projectPath = System.getProperty("user.dir");
   Actions action;

   @BeforeClass
   public void beforeClass() {
	//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	//driver = new FirefoxDriver();
	
	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
	driver = new ChromeDriver();	
	action = new Actions(driver);
	
	jsExecutor = (JavascriptExecutor) driver;
	
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
}
	
    @Test
    public void TC_01_Right_Clik() {
	driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	
	action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
	sleepInSecond(3);
	
	action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
	sleepInSecond(3);
	
	Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
	
	action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
	sleepInSecond(2);
	
	driver.switchTo().alert().accept();
	sleepInSecond(2);
	
	Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
    
    }
    
    
    public void TC_02_Drag_Drop_HTML4() {
	driver.get("https://automationfc.github.io/kendo-drag-drop/");
	
	WebElement smallCircle = driver.findElement(By.id("draggable"));
	WebElement bigCircle = driver.findElement(By.id("droptarget"));
	
	Assert.assertEquals(bigCircle.getText(), "Drag the small circle here.");
	
	action.dragAndDrop(smallCircle, bigCircle).perform();
	sleepInSecond(3);
	
	Assert.assertEquals(bigCircle.getText(), "You did great!");
    }
    
    @Test
    public void TC_03_Drag_Drop_HTML5() {
	driver.get("https://automationfc.github.io/drag-drop-html5/");
	
	WebElement columnA = driver.findElement(By.id("column-a"));
	WebElement columnB = driver.findElement(By.id("column-b"));
	
	action.dragAndDrop(columnA, columnB).perform();
	sleepInSecond(3);
	
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
