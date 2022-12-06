package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_08_Custom_Dropdown_List {
   WebDriver driver;
   WebDriverWait explicitWait;
   JavascriptExecutor jsExecutor;
   String projectPath = System.getProperty("user.dir");

   @BeforeClass
   public void beforeClass() {
	System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	driver = new FirefoxDriver();
	
	// Wait để apply cho trạng thái của element (visible: có thể nhìn thấy và thao tác được, invisible: ko nhìn thấy và ko thao tác được, presence: chỉ quan tâm đến HTML có là đc, clickable: wait element có thể click)
	explicitWait = new WebDriverWait(driver, 15);
	
	// Ép kiểu tường minh (Reference casting)
	jsExecutor = (JavascriptExecutor) driver;
	
	// Wait để tìm element (áp dụng cho findElement/ findElements)
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
}
	
    @Test
    public void TC_01_Jquery() {
	driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
	
	By parent = By.id("number-button");
	By child = By.cssSelector("ul#number-menu div");
	
	selectItemInDropdown(parent, child, "5");
	sleepInSecond(3);
	Assert.assertTrue(isElementDisplayed(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']")));
	
	selectItemInDropdown(parent, child, "19");
	sleepInSecond(3);
	Assert.assertTrue(isElementDisplayed(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='19']")));
	
	selectItemInDropdown(parent, child, "10");
	sleepInSecond(3);
	Assert.assertTrue(isElementDisplayed(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='10']")));
	
	selectItemInDropdown(parent, child, "15");
	sleepInSecond(3);
	Assert.assertTrue(isElementDisplayed(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='15']")));
	
    }
    
   
    public void TC_02_ReactJS() {
    	driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
    	
    	By parent = By.cssSelector("i.dropdown.icon");
    	By child = By.cssSelector("div[role='option']>span");
    	
    	selectItemInDropdown(parent, child, "Stevie Feliciano");
    	sleepInSecond(3);
    	Assert.assertTrue(isElementDisplayed(By.xpath("//div[@role='alert' and text()='Stevie Feliciano']")));
    	
    }
    
    
    public void TC_03_VueJS() {
    	driver.get("https://mikerodham.github.io/vue-dropdowns/");
    	
    	By parent = By.cssSelector("li.dropdown-toggle");
    	By child = By.cssSelector("ul.dropdown-menu a");
    	
    	selectItemInDropdown(parent, child, "Second Option");
    	sleepInSecond(3);
    	Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]	")));
	
    }
    
    
    public void TC_04_KendoUI() {
    	driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");
    	
    	//Chờ cho icon loading mất đi trong vòng 15 giây
    	Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span.kd-loader"))));
    
    	//Chờ cho icon loading trong dropdown biến mất trong vòng 15 giây
    	Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span.k-i-loading"))));
    	
    	// Chọn Categories
    	selectItemInDropdown(By.cssSelector("span[aria-owns='categories_listbox']"), By.cssSelector("ul#categories_listbox>li h3"), "Confections");
    	sleepInSecond(3);
    	
    	Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span.k-i-loading"))));
    	
    	// Chọn Products
    	selectItemInDropdown(By.cssSelector("span[aria-owns='products_listbox']"), By.cssSelector("ul#products_listbox>li"), "Chocolade");
    	
    	Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span.k-i-loading"))));
    	
    	// Chọn Address
    	selectItemInDropdown(By.cssSelector("span[aria-owns='shipTo_listbox']"), By.cssSelector("ul#shipTo_listbox>li"), "59 rue de l'Abbaye");
    } 	
   
    public void TC_05_Angular() {
    	driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
    	
    	selectItemInDropdown(By.cssSelector("span[aria-owns='games_options']"), By.cssSelector("ul#games_options>li"), "Basketball");
    	Assert.assertEquals(driver.findElement(By.cssSelector("span[aria-owns='games_options']>input")).getAttribute("aria-label"), "Basketball");
    	
    	selectItemInDropdown(By.cssSelector("span[aria-owns='games_options']"), By.cssSelector("ul#games_options>li"), "Football");
    	Assert.assertEquals(driver.findElement(By.cssSelector("span[aria-owns='games_options']>input")).getAttribute("aria-label"), "Football");
    }
    
	@Test
    public void TC_06_Editable() {
    	driver.get("http://indrimuska.github.io/jquery-editable-select/");
    	
    	selectItemInEditableDropdown(By.cssSelector("div#default-place>input"), By.xpath("//ul[@class='es-list' and @style]/li"), "Nissan");
    	driver.navigate().refresh();
    	
    	selectItemInEditableDropdown(By.cssSelector("div#default-place>input"), By.xpath("//ul[@class='es-list' and @style]/li"), "Land Rover");
    	driver.navigate().refresh();
    	
    	selectItemInEditableDropdown(By.cssSelector("div#default-place>input"), By.xpath("//ul[@class='es-list' and @style]/li"), "Audi");
    }
	
	@Test
    public void TC_07_Editable() {
    	driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
    	
    	selectItemInEditableDropdown(By.cssSelector("input.search"), By.cssSelector("div[role='option'] span"), "Andorra");
    	
    	selectItemInEditableDropdown(By.cssSelector("input.search"), By.cssSelector("div[role='option'] span"), "Aland Islands");
	}
	
	@Test
    public void TC_08_Multiple() {
		driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
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
	
	public boolean isElementDisplayed(By by) {
    	WebElement element = driver.findElement(by);
    	if (element.isDisplayed()) {
    		System.out.println("Element ["+ by +"] is displayed");
    	return true;
    	} else {
    		System.out.println("Element ["+ by +"] is not displayed");
    	return false; 		
    	}
	}
	public void selectItemInDropdown(By parentBy, By childBy, String expectedTextItem) {
		
		// Chờ cho element được phép click
		explicitWait.until(ExpectedConditions.elementToBeClickable(parentBy));
		// Click vào 1 element cho ra tất cả các item
		driver.findElement(parentBy).click();
		
		// Wait cho tất cả element được load ra (có trong HTML/DOM)
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));
		
		// Store lại tất cả element (item của dropdown)
		List<WebElement> allItems = driver.findElements(childBy);
		System.out.println("All item = " + allItems.size());
		
		for (WebElement item : allItems) {
			if (item.getText().equals(expectedTextItem)) {
				if (item.isDisplayed()) { // Nếu item mình chọn nằm trong view (nhìn thấy được) thì click vào
					item.click();
				} else { // Nếu item mình chọn ko nhìn thấy (che bên dưới) thì scroll xuống và click vào
					jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
					item.click();
				}
				break;
			}
			
		}
	}
	public void selectItemInEditableDropdown(By parentBy, By childBy, String expectedTextItem) {
		driver.findElement(parentBy).clear();
		driver.findElement(parentBy).sendKeys(expectedTextItem);
		
		// Chờ cho element được phép click
		explicitWait.until(ExpectedConditions.elementToBeClickable(parentBy));
		// Click vào 1 element cho ra tất cả các item
		driver.findElement(parentBy).click();
		
		// Wait cho tất cả element được load ra (có trong HTML/DOM)
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));
		
		// Store lại tất cả element (item của dropdown)
		List<WebElement> allItems = driver.findElements(childBy);
		System.out.println("All item = " + allItems.size());
		
		for (WebElement item : allItems) {
			if (item.getText().equals(expectedTextItem)) {
				if (item.isDisplayed()) { // Nếu item mình chọn nằm trong view (nhìn thấy được) thì click vào
					item.click();
				} else { // Nếu item mình chọn ko nhìn thấy (che bên dưới) thì scroll xuống và click vào
					jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
					item.click();
				}
				break;
			}
			
		}
	}

}
