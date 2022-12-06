package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_14_Windows_Tab {
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
	
  
    public void TC_01_ID() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	
	//Lấy ra id của windown/tab mà driver đang tương tác
	String parentPageID = driver.getWindowHandle();
	System.out.println("Parent Page ID = " + parentPageID);
	
	driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
	sleepInSecond(3);
	
	//Mở ra 2 tab lấy ra ID của tất cả các tab. Set ko cho lưu trùng dữ liệu
	//Set<String> allWindowns = driver.getWindowHandles();
	
	//for (String window : allWindowns) {
		//System.out.println(window);
		//kiểm tra id tab có bằng với id của parent page ko ko bằng thì switch qua google page
		//if (!window.equals(parentPageID))  {
			//driver.switchTo().window(window);
			//break;
		//}	
	//}
	// Switch qua google page sau khi khai báo chung
	switchToWindowByID(parentPageID);
	
	String googlePageID = driver.getWindowHandle();
	System.out.println("Google Page ID = " + googlePageID);
	
	driver.findElement(By.name("q")).sendKeys("selenium");
	driver.findElement(By.name("btnK")).click();
	
	//Switch về lại parent page
	//for (String window : allWindowns) {
		//System.out.println(window);
		//if (window.equals(parentPageID))  {
			//driver.switchTo().window(window);
		//}
    //}
	
	//Switch về lại parent page sau khi khai báo chung
	switchToWindowByID(googlePageID);
    }
    
    public void TC_02_Title() {
    driver.get("https://automationfc.github.io/basic-form/index.html");
    
    driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
    
    //Nhảy qua tab google 
    switchToWindowByPageTitle("Google");
    
    driver.findElement(By.name("q")).sendKeys("selenium");
	driver.findElement(By.name("btnK")).click();
	
	//Nhảy về tab parent
	switchToWindowByPageTitle("SELENIUM WEBDRIVER FORM DEMO");
	
	driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
	
	//Nhảy qua tab facebook
	switchToWindowByPageTitle("Facebook - Đăng nhập hoặc đăng ký"); 
	
    }
	@Test
	public void TC_03_Kyna() {
	driver.get("https://kyna.vn/");
	
	String parentPageID = driver.getWindowHandle();
	
	By salePopupBy = By.cssSelector("div.fancybox-inner img");
	
	List<WebElement> salePopupElement = driver.findElements(salePopupBy);
	
	if (salePopupElement.size() > 0) {
		System.out.println("Popup hiển thị và close đi");
		driver.findElement(By.cssSelector("a.fancybox-close")).click();
		sleepInSecond(2);
	} else {
		System.out.println("Popup ko hiển thị và qua step sau");
	}
	
	//Click vào facebook link
	driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='facebook']")).click();
	
	switchToWindowByPageTitle("Kyna.vn - Trang chủ | Facebook");
	Assert.assertEquals(driver.findElement(By.cssSelector("h1#seo_h1_tag span")).getText(), "Kyna.vn");
	
	//Switch về trang ban đầu
	switchToWindowByPageTitle("Kyna.vn - Học online cùng chuyên gia");
	
	//Click vào youtube link
	driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='youtube']")).click();
	
	switchToWindowByPageTitle("Kyna.vn - YouTube");
	
	closeAllWindowWithoutParent(parentPageID);
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
	
	//Dùng cho <2 tab/window
	public void switchToWindowByID (String windownPageID) {
		//Lấy ra tất cả ID đang có của tab/window
		Set<String> allWindowns = driver.getWindowHandles();
		
		//Dùng vòng lặp duyệt qua từng ID
		for (String window : allWindowns) {
			// Nếu như ID nào khác với ID truyền vào thì switch qua
			if (!window.equals(windownPageID))  {
				driver.switchTo().window(window);
			}
	    }
	}
	
	//Dùng cho >= 2 tab/window
	public void switchToWindowByPageTitle (String expectedPageTitle) {
		//Lấy ra tất cả ID đang có của tab/window
		Set<String> allWindowns = driver.getWindowHandles();
		
		//Dùng vòng lặp duyệt qua từng ID
		for (String window : allWindowns) {
			//Cho switch qua từng tab trước
			driver.switchTo().window(window);
			sleepInSecond(2);
			
			//Kiểm tra pagetitle switch qua có bằng cái mong muốn ko
			String actualPageTitle = driver.getTitle().trim();
			if (actualPageTitle.equals(expectedPageTitle)) {
				break;	
			}	
		}
	}
	
	public void closeAllWindowWithoutParent (String parentPageID) {
		//Lấy ra tất cả ID đang có của tab/window
		Set<String> allWindowns = driver.getWindowHandles();
		
		//Dùng vòng lặp duyệt qua từng ID
		for (String window : allWindowns) {
			// Nếu như ID nào khác với ID truyền vào thì switch qua
			if (!window.equals(parentPageID))  {
				driver.switchTo().window(window);
				sleepInSecond(2);
				
				//Chỉ đóng tab/window đang active driver vẫn đang đứng ở tab/window cuối cùng bị đóng
				driver.close();
			}
		}
		
		//Switch qua parent ID
		driver.switchTo().window(parentPageID);
		sleepInSecond(2);
	}

}
