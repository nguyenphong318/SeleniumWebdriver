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


public class Topic_11_Action_1 {
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
	
    
    public void TC_01_Hover_Textbox() {
	driver.get("https://automationfc.github.io/jquery-tooltip/");
	
	action.moveToElement(driver.findElement(By.id("age"))).perform();
	sleepInSecond(3);
	
	Assert.assertEquals(driver.findElement(By.className("ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
	
    }
    
    
    public void TC_02_Hover_Menu() {
	driver.get("https://www.myntra.com/");
	
	action.moveToElement(driver.findElement(By.xpath("//nav[@class='desktop-navbar']//a[text()='Kids']"))).perform();
	
	//driver.findElement(By.xpath("//nav[@class='desktop-navbar']//a[text()='Home & Bath']")).click();
	
	//Move to element roi click
	action.click(driver.findElement(By.xpath("//nav[@class='desktop-navbar']//a[text()='Home & Bath']"))).perform();
	
	Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Home Bath']")).isDisplayed());
	
    }
    
    
    public void TC_03_Click_And_Hold_Block() {
	driver.get("https://automationfc.github.io/jquery-selectable/");
	
	List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
	
	//Click and hold từ 1 đến 4
	action.clickAndHold(allNumber.get(0)) //Click vào số 1 và giữ chuột
	.moveToElement(allNumber.get(3)) //Di chuột đến số 4
	.release() // Nhả chuột trái ra
	.perform(); // Thực hiện hành động
	
	Assert.assertEquals(driver.findElements(By.cssSelector("ol#selectable>li[class$='ui-selected']")).size(), 4);
    
    }
    
    
    public void TC_04_Click_And_Hold_Random() {
	driver.get("https://automationfc.github.io/jquery-selectable/");
	
	List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
	
	//Nhấn giữ phím CTRL
	action.keyDown(Keys.CONTROL).perform();
	
	//Chọn random các số
	action.click(allNumber.get(0)).click(allNumber.get(2)).click(allNumber.get(4)).click(allNumber.get(6)).click(allNumber.get(8)).perform();
    
	//Nhả phím CTRL
	action.keyUp(Keys.CONTROL).perform();
	
	Assert.assertEquals(driver.findElements(By.cssSelector("ol#selectable>li[class$='ui-selected']")).size(), 5);
    }
    
    @Test
    public void TC_05_Double_Click() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	
	//Scroll tới element
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
	
	action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
	
	Assert.assertEquals(driver.findElement(By.id("demo")).getText(), "Hello Automation Guys!");
	
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
