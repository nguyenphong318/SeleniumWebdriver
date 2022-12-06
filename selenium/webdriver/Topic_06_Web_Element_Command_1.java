package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_06_Web_Element_Command_1 {
   WebDriver driver;
   String projectPath = System.getProperty("user.dir");

   @BeforeClass
   public void beforeClass() {
	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	driver = new FirefoxDriver();
	
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	
}
	
    @Test
    public void TC_01_() {
	//WebBrowser Command/Method/API Khai báo thông qua hàm driver
    	
    //WebElement Command/Method/API khai báo thông qua hàm driver.findElement
    // 1 sử dụng 1 lần
    driver.findElement(By.name("login")).click();
    
    // 2 sử dụng nhiều lần
    WebElement emailTextbox = driver.findElement(By.id("email"));
    emailTextbox.clear();
    emailTextbox.sendKeys("afc@gmail.com");
    emailTextbox.isDisplayed();
    
    WebElement element = driver.findElement(By.id(""));
    
    // Xóa dữ liệu trong editable field (textbox/textarea/dropdown)
    element.clear(); //**
    
    // Nhập dữ liệu vào editable field (textbox/textarea/dropdown)
    element.sendKeys(""); //**
    element.sendKeys(Keys.ENTER); //**
    
    // Click vào button/link/radio/checkbox/image..
    element.click(); //**
    
    // Trả về dữ liệu nằm trong attribute của element
    element.getAttribute("placeholder"); //**
    
    // Lấy ra thuộc tính của element (font/size/color...)
    element.getCssValue("background"); //**
    // Lấy ra màu rgba convert về hexa sau đó verify
    
    //GUI
    element.getLocation();
    element.getRect();
    element.getSize();
    
    // Tên thẻ HTML
    // Dùng By.id/class/css/name
    // Đầu ra của step này bằng đầu vào của step kia
    element = driver.findElement(By.cssSelector("#save-info-button"));
    String saveButtonTagname = element.getTagName();
    driver.findElement(By.xpath("//" + saveButtonTagname + "[@name='email']"));
    
    // Nối chuỗi
    String addressName = "123 Ly Thuong Kiet";
    String cityName = "Ho Chi Minh";
    System.out.println(addressName + cityName);
    System.out.println(addressName.concat(cityName));
    System.out.println(addressName + "-" + cityName);
    
    // Lấy text của header/link/label/error massage/success
    element.getText(); //**
    
    // Kiểm tra 1 element có hiển thị hay không? (Người dùng nhìn thấy và thao tác được)
    element.isDisplayed(); //**
    
    // Kiểm tra 1 element có thể thao tác được hay ko (Ko bị disable/ko phải là readonly field)
    element.isEnabled(); //**
    
    // Kiểm tra 1 element đã được chọn hay chưa (radio/checkbox/dropdown)
    element.isSelected(); //**
    
    // Submit vào 1 form
    element.submit();
    }
	
	@AfterClass
	public void afterClass() {
	driver.quit();
    }

}
