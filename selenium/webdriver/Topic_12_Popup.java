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


public class Topic_12_Popup {
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
	
    
    public void TC_01_() {
	driver.get("https://ngoaingu24h.vn/");
	
	By loginPopup = By.cssSelector("div#modal-login-v1");
			
	//Verify popup không hiển thị
	Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
	
	//Click to Đăng nhập button
	driver.findElement(By.xpath("//button[@class='login_ icon-before']")).click();
	sleepInSecond(2);
	
	//Verify popup hiển thị
	Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
	
	driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationfc");
	driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationfc");
	driver.findElement(By.cssSelector("button.btn-login-v1")).click();
	sleepInSecond(2);
	
	Assert.assertEquals(driver.findElement(By.cssSelector("div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
    }
    
    
    public void TC_02_Random_Popup_In_Dom() {
	driver.get("https://blog.testproject.io/");
	sleepInSecond(60);
	
	By mailPopup = By.cssSelector("div..mailch-wrap");
	
	//Nếu hiển thị thì close popup
	if (driver.findElement(mailPopup).isDisplayed()) {
		System.out.println("Popup hiển thị và close đi");
		driver.findElement(By.cssSelector("div.#close-mailch")).click();
		sleepInSecond(2);
		Assert.assertFalse(driver.findElement(mailPopup).isDisplayed());
	}
	
	driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("Selenium");
	driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
	
	//Verify tất cả các post title có từ khóa Selenium
	List<WebElement> postTitles = driver.findElements(By.cssSelector("h3.post-title>a"));
	System.out.println("All post title = " + postTitles.size());
	
	for (WebElement postTitle : postTitles) {
		Assert.assertTrue(postTitle.getText().contains("Selenium"));
		
	}
	
    }
    
    @Test
    public void TC_03_Random_Popup_Not_In_Dom() {
	driver.get("https://shopee.vn/");
	
	By shopeePopupBy = By.cssSelector("div.shopee-popup__container");
	
	List<WebElement> shopeePopupElement = driver.findElements(shopeePopupBy);
	
	if (shopeePopupElement.size() > 0) {
		System.out.println("Popup hiển thị và close đi");
		driver.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
		sleepInSecond(2);
	} else {
		System.out.println("Popup ko hiển thị và qua step sau");
		
	Assert.assertEquals(driver.findElements(shopeePopupBy).size(), 0);
	
	}
	
	driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("Macbook Pro");
	driver.findElement(By.cssSelector("button.btn-solid-primary")).click();
	
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
