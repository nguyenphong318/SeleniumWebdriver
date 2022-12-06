package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_16_Upload_File_Part_I {
   WebDriver driver;
   WebDriverWait explicitWait;
   String projectPath = System.getProperty("user.dir");
   String anh1Name = "Anh1.jpg";
   String anh2Name = "Anh2.jpg";
   String anh3Name = "Anh3.jpg";
   String anh1Path = projectPath + "\\UploadFiles\\" + anh1Name;
   String anh2Path = projectPath + "\\UploadFiles\\" + anh2Name;
   String anh3Path = projectPath + "\\UploadFiles\\" + anh3Name;

   @BeforeClass
   public void beforeClass() {  
	//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	//driver = new FirefoxDriver();
	
	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
	driver = new ChromeDriver();
	
	explicitWait = new WebDriverWait(driver, 20);
	
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
}
	
    
    public void TC_01_SendKey_1_File() {
    	driver.get("https://blueimp.github.io/jQuery-File-Upload/");
    	
    	//Load file thành công
    	driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(anh1Path);
    	sleepInSecond(2);
    	driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(anh2Path);
    	sleepInSecond(2);
    	driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(anh3Path);
    	sleepInSecond(2);
    	
    	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + anh1Name + "']")).isDisplayed());
    	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + anh2Name + "']")).isDisplayed());
    	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + anh3Name + "']")).isDisplayed());
    	
    	List<WebElement> uploadButtons = driver.findElements(By.cssSelector("tr.template-upload button.start"));
    	
    	for (WebElement uploadButton : uploadButtons) {
    		uploadButton.click();
    		sleepInSecond(1);		
		}
    	
    	//Very upload file success
    	Assert.assertTrue(driver.findElement(By.cssSelector("p.name>a[title='" + anh1Name + "]")).isDisplayed());
    	Assert.assertTrue(driver.findElement(By.cssSelector("p.name>a[title='" + anh2Name + "]")).isDisplayed());
    	Assert.assertTrue(driver.findElement(By.cssSelector("p.name>a[title='" + anh3Name + "]")).isDisplayed());
	
    }
    
    
    public void TC_02_SendKey_Multiple_File() {
    	driver.get("https://blueimp.github.io/jQuery-File-Upload/");
    	
    	driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(anh1Path + "\n" + anh2Path + "\n" + anh3Path);
    	sleepInSecond(2);
    	
    	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + anh1Name + "']")).isDisplayed());
    	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + anh2Name + "']")).isDisplayed());
    	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + anh3Name + "']")).isDisplayed());
    	
    	List<WebElement> uploadButtons = driver.findElements(By.cssSelector("tr.template-upload button.start"));
    	
    	for (WebElement uploadButton : uploadButtons) {
    		uploadButton.click();
    		sleepInSecond(1);		
		}
    	
    	//Very upload file success
    	Assert.assertTrue(driver.findElement(By.cssSelector("p.name>a[title='" + anh1Name + "]")).isDisplayed());
    	Assert.assertTrue(driver.findElement(By.cssSelector("p.name>a[title='" + anh2Name + "]")).isDisplayed());
    	Assert.assertTrue(driver.findElement(By.cssSelector("p.name>a[title='" + anh3Name + "]")).isDisplayed());
    }
    
    @Test
    public void TC_03_Go_File() {
    	driver.get("https://gofile.io/uploadFiles");
    	
    	driver.findElement(By.xpath("//input[@id='uploadFile-Input']")).sendKeys(anh1Path + "\n" + anh2Path + "\n" + anh3Path);
    	sleepInSecond(2);
    	
    	//Wait cho các loading icon biến mất của các file upload
    	explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress"))));
    	
    	//Wait icon loading biến mất
    	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#mainContent i.fa-spinner")));
    	
    	// Wait cho message hiển thị
    	explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h5[contains(text(),'Your files have been successfully uploaded')]")));
    	
    	Assert.assertTrue(driver.findElement(By.xpath("//h5[contains(text(),'Your files have been successfully uploaded')]")).isDisplayed());
    	
    	driver.get(driver.findElement(By.xpath("//a[@id='rowUploadSuccess-downloadPage']")).getAttribute("href"));
    	
    	Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + anh1Name + "']")).isDisplayed());
    	Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + anh2Name + "']")).isDisplayed());
    	Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + anh3Name + "']")).isDisplayed());
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
