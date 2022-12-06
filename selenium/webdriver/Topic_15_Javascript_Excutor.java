package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_15_Javascript_Excutor {
   WebDriver driver;
   String projectPath = System.getProperty("user.dir");
   JavascriptExecutor jsExecutor; 

   @BeforeClass
   public void beforeClass() {
	//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	//driver = new FirefoxDriver();
	
	System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
	driver = new ChromeDriver();
	
	jsExecutor = (JavascriptExecutor) driver;
	
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
}
	
    @Test
    public void TC_01_() {
    	navigateToUrlByJS("http://live.demoguru99.com/");
    	
    	String liveGuruDomain = (String) executeForBrowser("return document.domain;");
    	Assert.assertEquals(liveGuruDomain, "live.demoguru99.com");
    	
    	String homePageUrl = (String) executeForBrowser("return document.URL;");
    	Assert.assertEquals(homePageUrl, "http://live.demoguru99.com/");
    	
    	hightlightElement("//a[text()='Mobile']");
    	clickToElementByJS("//a[text()='Mobile']");
    	
    	hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
    	clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
    	
    	String innerTextValue = getInnerText();
    	Assert.assertTrue (innerTextValue.contains("Samsung Galaxy was added to your shopping cart."));
    	Assert.assertTrue (areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
    	
    	hightlightElement("//a[text()='Customer Service']");
    	clickToElementByJS("//a[text()='Customer Service']");
    	Assert.assertEquals(executeForBrowser("return document.title;"), "Customer Service");
    	
    	hightlightElement("//input[@id='newsletter']");
    	scrollToElementOnTop("//input[@id='newsletter']");
    	sleepInSecond(3);
    	
    	hightlightElement("//input[@id='newsletter']");
    	sendkeyToElementByJS("//input[@id='newsletter']", "testing" + getRandomNumber() + "@gmail.com");
    	sleepInSecond(3);
    	
    	hightlightElement("//button[@title='Subscribe']");
    	clickToElementByJS("//button[@title='Subscribe']");
    	
    	Assert.assertTrue (getInnerText().contains("Thank you for your subscription."));
    	Assert.assertTrue (areExpectedTextInInnerText("Thank you for your subscription."));
    	
    	navigateToUrlByJS("http://demo.guru99.com/v4/");
    	sleepInSecond(3);
    	
    	Assert.assertEquals(executeForBrowser("return document.domain;"), "demo.guru99.com");
    }
    
    @Test
    public void TC_02_() {
        driver.get("https://login.ubuntu.com/");
        
        driver.findElement(By.xpath("//button[@data-qa-id='login_button']")).click();
        Assert.assertEquals(getElementValidationMessage("//form[@id='login-form']//input[@id='id_email']"), "Vui lòng điền vào trường này.");
        
        
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
	
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		if (status) {
			return true;
		}
		return false;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

}
