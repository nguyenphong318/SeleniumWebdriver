package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.Colors;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_09_Button_Radio_Checkbox {
   WebDriver driver;
   String projectPath = System.getProperty("user.dir");
   JavascriptExecutor jsExecutor;

   @BeforeClass
   public void beforeClass() {
	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	driver = new FirefoxDriver();
	
	jsExecutor = (JavascriptExecutor) driver;
	
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
}
	
   
    public void TC_01_Button() {
	driver.get("https://www.fahasa.com/customer/account/create");
	
	By loginButton = By.cssSelector("button.fhs-btn-login");
	
	driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
	
	//Verify button bị disable
	Assert.assertFalse(driver.findElement(loginButton).isEnabled());
	
	driver.findElement(By.cssSelector("input#login_username")).sendKeys("phong@gmail.com");
	driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
	sleepInSecond(1);
	
	//Verify button enable
	Assert.assertTrue(driver.findElement(loginButton).isEnabled());
	
	driver.navigate().refresh();
	
	driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
	
	//Remove disable attribute của login button
	jsExecutor.executeScript("arguments[0].removeAttribute('disabled');", driver.findElement(loginButton));
	sleepInSecond(2);
	
	//Verify login button với background colour = RED
	String rgbaColor = driver.findElement(loginButton).getCssValue("background-color");
	driver.findElement(loginButton).click();
	System.out.println("RGBA = " + rgbaColor);
	
	String hexaColor = Color.fromString(rgbaColor).asHex().toUpperCase();
	System.out.println("Hexa = " + hexaColor);
	
	Assert.assertEquals(hexaColor, "#C92127");
	Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase(), "#C92127");
	
	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
    }
	
    
    public void TC_02_Radio_Default() {
    driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
    
    By petrolTwo = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
    
    //Verify petrol chưa được chọn
    Assert.assertFalse(driver.findElement(petrolTwo).isSelected());
    
    driver.findElement(petrolTwo).click();
    sleepInSecond(2);
    
    //Verify petrol được chọn
    Assert.assertTrue(driver.findElement(petrolTwo).isSelected());
    
    driver.findElement(petrolTwo).click();
    sleepInSecond(2);
    
    Assert.assertTrue(driver.findElement(petrolTwo).isSelected());
    
    By dieselTwo = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input");
    
    driver.findElement(dieselTwo).click();
    sleepInSecond(2);
    
    //Verify petrol chưa được chọn
    Assert.assertFalse(driver.findElement(petrolTwo).isSelected()); 
    
    //Verify diesel được chọn
    Assert.assertTrue(driver.findElement(dieselTwo).isSelected());
    
    By petrolThree = By.xpath("//label[text()='3.6 Petrol, 191kW']/preceding-sibling::input");
    
    Assert.assertFalse(driver.findElement(petrolThree).isEnabled());
    	
    }
    
    
    public void TC_03_Checkbox_Default() {
    driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
    
    By rearsideCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input");
    
    checktoRadioCheckbox(rearsideCheckbox);
    sleepInSecond(2);
    
   //Verify checkbox được chọn
    Assert.assertTrue(driver.findElement(rearsideCheckbox).isSelected());
    
    By luggageCheckbox = By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::input");
    
    //Verify checkbox ko được chọn
    Assert.assertFalse(driver.findElement(luggageCheckbox).isSelected());
    
    //Ko dùng check checkbox trước thì click: driver.findElement(luggageCheckbox).click();
    checktoRadioCheckbox(luggageCheckbox);
    sleepInSecond(2);
    
    //Verify checkbox được chọn
    Assert.assertTrue(driver.findElement(luggageCheckbox).isSelected());
    
    unchecktoCheckbox(luggageCheckbox);
    sleepInSecond(2);
    
    Assert.assertFalse(driver.findElement(luggageCheckbox).isSelected());
    }
    
    
    public void TC_04_Custom_Radio() {
    driver.get("https://material.angular.io/components/radio/examples");
    
    //Cách 1: Dùng thẻ input nhưng ko click được - có thể dùng verify được
    //By winterRadioButton = By.xpath("//input[@value='Winter']");
        
    //Assert.assertFalse(driver.findElement(winterRadioButton).isSelected());
        
    //driver.findElement(winterRadioButton).click();
    //sleepInSecond(2);
        
    //Assert.assertTrue(driver.findElement(winterRadioButton).isSelected());
    
    //Cách 2: Dùng thẻ span vừa click vừa verify. Thẻ span click được nhưng ko verify được
    By winterRadioButton = By.xpath("//input[@value='Winter']/preceding-sibling::span[contains(@class,'outer')]");
    
    Assert.assertFalse(driver.findElement(winterRadioButton).isSelected());
        
    driver.findElement(winterRadioButton).click();
    sleepInSecond(2);
        
    Assert.assertTrue(driver.findElement(winterRadioButton).isSelected());
    
    //Cách 3: Dùng thẻ span để click, thẻ input để verify
    //By winterRadioSpan = By.xpath("//input[@value='Winter']/preceding-sibling::span[contains(@class,'outer')]");
    //By winterRadioInput = By.xpath("//input[@value='Winter']");
    
    //Assert.assertFalse(driver.findElement(winterRadioSpan).isSelected());
        
    //driver.findElement(winterRadioInput).click();
    //sleepInSecond(2);
        
    //Assert.assertTrue(driver.findElement(winterRadioButton).isSelected());
    
    // Cách 3 có thể dùng nhưng dễ gây hiểu nhầm dùng click của JavaScript click vào element bị ẩn được
    
    // Cách 4: Dùng thẻ input (Click = JavaScriprt và verfy)
    By winterRadioInput = By.xpath("//input[@value='Winter']");
    
    jsExecutor.executeScript("arguments[0].click();", driver.findElement(winterRadioInput));
    sleepInSecond(2);
    
    Assert.assertTrue(driver.findElement(winterRadioInput).isSelected());
    }
    
    
    public void TC_05_Custom_Checkbox() {
    	driver.get("https://material.angular.io/components/checkbox/examples");
    	
    	By checkedCheckbox = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
    	By indeterminateCheckbox = By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input");
    	
    	checktoCheckboxByJS(checkedCheckbox);
    	checktoCheckboxByJS(indeterminateCheckbox);
    	sleepInSecond(2);
    	
    	//Verify checkbox được chọn
    	Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
    	Assert.assertTrue(driver.findElement(indeterminateCheckbox).isSelected());
    	
    	unchecktoCheckboxByJS(checkedCheckbox);
    	unchecktoCheckboxByJS(indeterminateCheckbox);
    	sleepInSecond(2);
    	
    	//Verify checkbox bỏ chọn
    	Assert.assertFalse(driver.findElement(checkedCheckbox).isSelected());
    	Assert.assertFalse(driver.findElement(indeterminateCheckbox).isSelected());
    	
    }
    
   
    public void TC_06_Radio_Checkbox_Google_Docs() {
    	driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
    	
    	By canthoRadio = By.xpath("//div[@aria-label='Cần Thơ']");
    	
    	Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='false']")).isDisplayed());
    	Assert.assertEquals(driver.findElement(canthoRadio).getAttribute("aria-checked"), "false");
    	
    	checktoRadioCheckbox(canthoRadio);
    	sleepInSecond(2);
    	
    	Assert.assertEquals(driver.findElement(canthoRadio).getAttribute("aria-checked"), "true");
    	Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='true']")).isDisplayed());
    	
    	List<WebElement> checkboxes = driver.findElements(By.xpath("//div[@role='checkbox']"));
    	
    	//Click all Element
    	for (WebElement checkbox : checkboxes) {
    		checkbox.click();
    		sleepInSecond(2);
    	}
    	
    	//Verify all
    	for (WebElement checkbox : checkboxes) {
    		Assert.assertEquals(checkbox.getAttribute("aria-checked"), "true");
    	}
    }
    
    @Test
    public void TC_07_Live_Guru() {
    	driver.get("https://live.demoguru99.com/index.php/backendlogin");
    	
    	driver.findElement(By.id("username")).sendKeys("user01");
    	driver.findElement(By.id("login")).sendKeys("guru99com");
    	driver.findElement(By.cssSelector("input[title='Login']")).click();
    	sleepInSecond(5);
    	
    	clickToCheckboxByCustomerName("Automation VN");
    	
    	
    }
    
    public void clickToCheckboxByCustomerName (String customerName) {
    	WebElement customerNameCheckbox = driver.findElement(By.xpath("//td[contains(text(),'" + customerName +"')]/preceding-sibling::td/input"));
    	if (!customerNameCheckbox.isSelected()) {
    		customerNameCheckbox.click();
    	}
    }
    
    public void checktoCheckboxByJS (By by) {
    	if (!driver.findElement(by).isSelected()) {
    		jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
    	}
    	
    }
    
    public void unchecktoCheckboxByJS(By by) {
    	if (driver.findElement(by).isSelected()) {
    		jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
    	}
    }
    
    // Kiểm tra checkbox nếu chưa đc chọn thì mới chọn
    public void checktoRadioCheckbox(By by) {
    	if (!driver.findElement(by).isSelected()) {
    		driver.findElement(by).click();
    	}
    }
    
    public void unchecktoCheckbox(By by) {
    	if (driver.findElement(by).isSelected()) {
    		driver.findElement(by).click();
    	}
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
