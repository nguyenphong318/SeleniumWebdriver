package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Topic_02_Run_On_Browser {

public class Topic_00_Template {
   WebDriver driver;
   String projectPath = System.getProperty("user.dir");

   @BeforeClass
   public void beforeClass() {
	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	driver = new FirefoxDriver();
	
	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
	driver = new ChromeDriver();
	
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	
}
	
    @Test
    public void TC_01_() {
	driver.get("");
	
    }
	
	@AfterClass
	public void afterClass() {
	driver.quit();
    }

}
}