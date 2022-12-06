package webdriver;

import java.awt.Point;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_05_Web_Browser_Command_1 {

@Test
public class Topic_00_Template {
   WebDriver driver;
   String projectPath = System.getProperty("user.dir");

   @BeforeClass
   public void beforeClass() {
	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	driver = new FirefoxDriver();
	
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	
}
	
    public void TC_01_Browser() {
    // Mở ra page Url
	driver.get("https://www.messenger.com/"); //hay dùng
	
	// Đóng 1 tab đang active
	driver.close();
	
	// Đóng trình duyêt (ko care có bao nhiêu tab or window đang mở)
	driver.quit(); //hay dùng
	
	// Lấy ra ID hiện tại của window/tab đang active
	String messengerID = driver.getWindowHandle();
	
	// Lấy ra tất cả ID hiện tại của window/tab đang active
	Set<String> allIDs = driver.getWindowHandles();
	
	// Switch/ Nhảy đến 1 tab/window nào đó
	driver.switchTo().window(messengerID); //hay dùng
	
	// Tìm ra 1 element vs locator nào đó
	WebElement emailTextbox = driver.findElement(By.id("")); //hay dùng
	emailTextbox.clear();
	emailTextbox.sendKeys("");
	
	// Tìm ra tất cả element vs tất cả locator nào đó
	List<WebElement>textboxes = driver.findElements(By.id(""));
	
	// Trả về URL của page hiện tại
	String homePageUrl = driver.getCurrentUrl(); //hay dùng 
	
	// Trả về HTML source của page hiện tại
	String homePageSource = driver.getPageSource();
	
	// Trả về page title của page hiện tại
	String homePageTitle = driver.getTitle();
	
	// Thêm, xóa, cookie của page
	driver.manage().deleteAllCookies();
	
	// Build framework: Get ra log của browser
	driver.manage().logs().getAvailableLogTypes();
	
	// Chờ cho việc tìm element 1000m = 1s
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); //hay dùng
	
	// Chờ cho 1 page được load thành công (Option không bắt buộc)
	driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
	
	// Chờ cho 1 script được excute thành công (Option không bắt buộc)
	driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
	
	// Mở browser full màn hình
	driver.manage().window().fullscreen();
	
	// Mở browser maximize màn hình
	driver.manage().window().maximize(); //hay dùng
	
	// Lấy ra kích thước hiện tại của browser (cao,rộng)
	driver.manage().window().setSize(new Dimension(1920,1080));
	
	// Back to page
	driver.navigate().back();
	
	// Forward to page
	driver.navigate().forward();
	
	// Tải lại trang
	driver.navigate().refresh();
	
	// Keep history
	driver.navigate().to("");
	
	// Window/Tab
	// Allert
	// Frame/Iframe
	driver.switchTo().alert();      //hay dùng
	driver.switchTo().window("");   //hay dùng
	driver.switchTo().frame("");    //hay dùng
    }
    
    public void TC_02_Element() {
	
    }
	
	@AfterClass
	public void afterClass() {
	driver.quit();
    }

}
}