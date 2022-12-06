package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_13_Frame_Iframe {
   WebDriver driver;
   String projectPath = System.getProperty("user.dir");

   @BeforeClass
   public void beforeClass() {
	//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	//driver = new FirefoxDriver();
	
	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
	driver = new ChromeDriver();
	
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
}
	
    
    public void TC_01_Iframe() {
    	driver.get("https://kyna.vn/");
    	
    	By salePopupBy = By.cssSelector("div.fancybox-inner img");
    	
    	List<WebElement> salePopupElement = driver.findElements(salePopupBy);
    	
    	if (salePopupElement.size() > 0) {
    		System.out.println("Popup hiển thị và close đi");
    		driver.findElement(By.cssSelector("a.fancybox-close")).click();
    		sleepInSecond(2);
    	} else {
    		System.out.println("Popup ko hiển thị và qua step sau");
    	}
    	
    	//Switch vào iframe chứa element trước
    	driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));
    	
    	String kynaFanpageLike = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText(); 
    	System.out.println(kynaFanpageLike);
    	
    	//Quay về trang ban đầu
    	driver.switchTo().defaultContent();
    	
    	//Verify button Đăng nhập hiển thị
    	Assert.assertTrue(driver.findElement(By.cssSelector("a.login-btn")).isDisplayed());
	
    }
	
   
    public void TC_02_Iframe() {
    	driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
    	
    	//Switch vào iframe youtube player
    	driver.switchTo().frame(driver.findElement(By.cssSelector("iframe.youtube-player")));
    	
    	String videoArticle = driver.findElement(By.cssSelector("a.ytp-title-link")).getText();
    	
    	System.out.println(videoArticle);
    	
    	Assert.assertEquals(videoArticle, "[Online 10] - Topic 01 (Intro Course/ Outline/ Target/ Rule)");
    	
    	//Switch về trang ban đầu
    	driver.switchTo().defaultContent();
    	
    	//Switch vào iframe google document
    	driver.switchTo().frame(driver.findElement(By.cssSelector("div.post-content iframe[src*='docs.google.com']")));
    	
    	Assert.assertEquals(driver.findElement(By.cssSelector("div.exportFormTitle")).getText(), "KHÓA HỌC SELENIUM AUTOMATION TESTING");
    	
    	driver.switchTo().defaultContent();
    	
    	Assert.assertEquals(driver.findElement(By.cssSelector("h1..post-title")).getText(), "[Training Online] – Fullstack Selenium WebDriver Framework in Java (Livestream)");
    
    }
    
    @Test
    public void TC_03_Frame() {
    	driver.get("https://netbanking.hdfcbank.com/netbanking/");
    	
    	//Switch vào frame của login page
    	driver.switchTo().frame(driver.findElement(By.name("login_page")));
    	
    	driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("automationfc");
    	driver.findElement(By.cssSelector("a.login-btn")).click();
    	
    	Assert.assertTrue(driver.findElement(By.cssSelector("input[name='fldPassword']")).isDisplayed());
    	
    	driver.findElement(By.xpath("//a[text()='Terms and Conditions']")).click();
    	
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
