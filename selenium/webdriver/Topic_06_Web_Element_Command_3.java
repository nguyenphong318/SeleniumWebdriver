package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_06_Web_Element_Command_3 {
   WebDriver driver;
   String projectPath = System.getProperty("user.dir");
   
   String firstName, lastName, fullName, email, password;

   @BeforeClass
   public void beforeClass() {
	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
	driver = new ChromeDriver();
	
	firstName = "John";
	lastName = "Wick";
	fullName = firstName+ ""+lastName;
	email = "john" + getRandomNumber() + "@gmail.com";
	password = "123456";
		
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	driver.manage().window().maximize();
}
	
 
    public void TC_01_Sign_Up_Validate() {
	driver.get("https://login.mailchimp.com/signup/");
	
	driver.findElement(By.cssSelector("#email")).sendKeys("automation@gmail.vn");
	driver.findElement(By.cssSelector("#new_username")).sendKeys("automationclub");
	
	By passwordTextbox = By.id("new_password");
	By signupButton = By.id("create-account");
	By newsletterCheckbox = By.id("marketing_newsletter");
	
	//Click vào checkbox
	driver.findElement(newsletterCheckbox).click();
	
	//Lower case
	driver.findElement(passwordTextbox).sendKeys("auto");
	
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")).isDisplayed());
	Assert.assertFalse(driver.findElement(signupButton).isEnabled());
	
	//Upper case
	driver.findElement(passwordTextbox).clear();
	driver.findElement(passwordTextbox).sendKeys("AUTO");
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")).isDisplayed());
	Assert.assertFalse(driver.findElement(signupButton).isEnabled());
	
	//Number
	driver.findElement(passwordTextbox).clear();
	driver.findElement(passwordTextbox).sendKeys("123456");
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed' and text()='One number']")).isDisplayed());
	Assert.assertFalse(driver.findElement(signupButton).isEnabled());
	
	//Special character
	driver.findElement(passwordTextbox).clear();
	driver.findElement(passwordTextbox).sendKeys("@@@###");
	Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']")).isDisplayed());
	Assert.assertFalse(driver.findElement(signupButton).isEnabled());
	
	//Full valid data
	driver.findElement(passwordTextbox).clear();
	driver.findElement(passwordTextbox).sendKeys("Auto12345!!!");
	
	Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")).isDisplayed());
	Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")).isDisplayed());
	Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed' and text()='One number']")).isDisplayed());
	Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']")).isDisplayed());
	Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")).isDisplayed());
	Assert.assertTrue(driver.findElement(signupButton).isEnabled());
	Assert.assertTrue(driver.findElement(newsletterCheckbox).isSelected());
    }
	
    @Test
    public void TC_02_LiveGuru_Register() {
    driver.get("http://live.demoguru99.com/");
    
    driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
    driver.findElement(By.xpath("//a[@title='Create an Account']")).click();  //CSS: a[title='Create an Account']
    
    driver.findElement(By.id("firstname")).sendKeys(firstName);
    driver.findElement(By.id("lastname")).sendKeys(lastName);
    driver.findElement(By.id("email_address")).sendKeys(email);
    driver.findElement(By.id("password")).sendKeys(password);
    driver.findElement(By.id("confirmation")).sendKeys(password);
    
    driver.findElement(By.xpath("//button[@title='Register']"));
    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
    
    //Cách 1
    String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p")).getText();
    Assert.assertTrue(contactInformation.contains(fullName));
    Assert.assertTrue(contactInformation.contains(email));
    
    //Cách 2
    Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p[contains(string(),'"+fullName+"')]")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p[contains(string(),'"+email+"')]")).isDisplayed());
    
    driver.findElement(By.xpath("//a/span[text()='Account']")).click();
    driver.findElement(By.xpath("//a[@title='Log Out']")).click();
    }
    
    @Test
    public void TC_03_LiveGuru_Login() {
    driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
    
    driver.findElement(By.id("email")).sendKeys("email");
    driver.findElement(By.name("login[password]")).sendKeys("password");
    driver.findElement(By.id("send2")).click();
    
    Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
    String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p")).getText();
    Assert.assertTrue(contactInformation.contains(fullName));
    Assert.assertTrue(contactInformation.contains(email));
    
    }
	@AfterClass
	public void afterClass() {
	driver.quit();
    }

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
}
