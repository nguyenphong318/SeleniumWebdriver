package basic;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_01_Data_Type {
	
	public static void main(String args) {
		
		//Kiểu dữ liệu nguyên thủy (Primitive Type)
		//Number
		//Integer: Số nguyên không dấu
		//byte/short/int/long
		byte bNumber = 5;
		short sNumber = 100;
		int studentNumber = 1000;
		long timeout = 20000;
		
		//Số thực (có dấu)
		//float/double
		float studentPoint = 8.5f;
		double employeeSlary = 28.5d;
		
		//Char
		//char(kí tự)
		char c = 'C';
		
		//Boolean
		//boolean
		boolean status = true;
		status = false;
		
		//Kiểu dữ liệu tham chiếu (Reference Type)
		//String
		String studentName = "Automation FC";
		String studentAddress = new String ("123abc");
		
		//Array (Tập hợp kiểu dữ liệu giống nhau)
		String[] studentNames = {"Nguyen Van Nam","Le Van An","Ngo Si Lien"};
		
		//Class
		WebDriver driver = new FirefoxDriver();
		 
		
		//Interface
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		
		//Collection (Set/Queue/List)
		//1 Element
		WebElement emailTextbox = driver.findElement(By.id(""));
		
		//n Elements
		WebElement buttons = driver.findElement(By.tagName("button"));
		
	}

}
