package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_08_Default_Dropdown_List {
   WebDriver driver;
   String projectPath = System.getProperty("user.dir");
   Select select;

   @BeforeClass
   public void beforeClass() {
	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	driver = new FirefoxDriver();
	
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	driver.manage().window().maximize();
}
	
    @Test
    public void TC_01_Nopcommerce() {
	driver.get("https://demo.nopcommerce.com/");
	
	String firstName = "Phong";
	String lastName = "Nguyen";
	String emailAddress = "phong" + getRandomNumber() + "@gmail.com"  ;
	String day = "31";
	String month = "December";
	String year = "1991";
	String company = "Automation VN";
	String password = "123456";
	
	By genderMaleby = By.id("gender-male");
	By firstNameby = By.id("FirstName");
	By lastNameby = By.id("LastName");
	By dateDropdownBy = By.name("DateOfBirthDay");
	By monthDropdownBy = By.name("DateOfBirthMonth");
	By yearDropdownBy = By.name("DateOfBirthYear");
	By emailBy = By.id("Email");
	By companyBy = By.id("Company");
	
	driver.findElement(By.cssSelector("a.ico-register")).click();
	driver.findElement(genderMaleby).click();
	driver.findElement(firstNameby).sendKeys(firstName);
	driver.findElement(lastNameby).sendKeys(lastName);
	
	select = new Select(driver.findElement(dateDropdownBy));
	
	// Chọn 1 item A
	//select.selectByIndex(15); //Chọn bằng index
	//select.selectByValue("15"); //Chọn bằng value
	select.selectByVisibleText(day); //Chọn bằng text
	
	// Kiểm tra drowpdown có phải multiple hay không
	Assert.assertFalse(select.isMultiple());
	
	// Kiểm tra xem đã chọn dúng item A hay chưa
	Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
	
	// Get ra tổng số item trong dropdown là bao nhiêu sau đó verify bằng bao nhiêu
	Assert.assertEquals(select.getOptions().size(), 32);
	
	select = new Select(driver.findElement(monthDropdownBy));
	select.selectByVisibleText(month);
	
	select = new Select(driver.findElement(yearDropdownBy));
	select.selectByVisibleText(year);
	
	driver.findElement(emailBy).sendKeys(emailAddress);
	driver.findElement(companyBy).sendKeys(company);
	driver.findElement(By.id("Password")).sendKeys(password);
	driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
	driver.findElement(By.id("register-button")).click();
	
	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
	
	driver.findElement(By.xpath("//a[@class='ico-account']")).click();
	
	Assert.assertTrue(driver.findElement(genderMaleby).isSelected());
	Assert.assertEquals(driver.findElement(firstNameby).getAttribute("value"), firstName);
	Assert.assertEquals(driver.findElement(lastNameby).getAttribute("value"), lastName);
	
	select = new Select(driver.findElement(dateDropdownBy));
	Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
	
	select = new Select(driver.findElement(monthDropdownBy));
	Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
	
	select = new Select(driver.findElement(yearDropdownBy));
	Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
	
	Assert.assertEquals(driver.findElement(emailBy).getAttribute("value"), emailAddress);
	Assert.assertEquals(driver.findElement(companyBy).getAttribute("value"), company);
    }
	
    @Test
    public void TC_02_Rode() {
	driver.get("https://www.rode.com/wheretobuy");
	
	select = new Select(driver.findElement(By.id("where_country")));
	
	select.selectByVisibleText("Vietnam");
	driver.findElement(By.id("search_loc_submit")).click();
	
	//Dễ fail
	//Assert.assertEquals(driver.findElement(By.cssSelector("div.result_count>span")).getText(), "29");
	
	//Dễ pass
	Assert.assertTrue(driver.findElement(By.xpath("//div[@class='result_count']/span[text()='29']")).isDisplayed());
	
	List<WebElement> storeName = driver.findElements(By.xpath("//div[@id='search_results']//div[@class='store_name']"));
	Assert.assertEquals(storeName.size(), 29);
	
	for (WebElement store : storeName) {
		System.out.println(store.getText());
	}
    }
	@AfterClass
	public void afterClass() {
	driver.quit();
    }
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
	
	public void sleepInSecond(long timeoutlnSecond) {
		try {
			Thread.sleep(timeoutlnSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
