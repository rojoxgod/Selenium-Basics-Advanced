package Selenium;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class _06_TabsManagement {

	public static void main(String[] args) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		
		// System.setProperty("webdriver.chrome.driver", "E:\\work\\qa engineering\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
				
		standartTabsManager(driver);
		
		newTabsManager(driver);

	}
	
	public static void standartTabsManager(WebDriver driver) {
		
		driver.get("https://rahulshettyacademy.com/loginpagePractise/");
		
		
		driver.findElement(By.className("blinkingText")).click();
		
		//switching between tabs
		
		Set<String> tabs = driver.getWindowHandles();
		Iterator<String> iterator = tabs.iterator();
		String parentID = iterator.next();
		String childID = iterator.next();
		
		driver.switchTo().window(childID);
		String email = driver.findElement(By.xpath("//p[@class='im-para red']//strong")).getText();
		
		driver.switchTo().window(parentID);
		driver.findElement(By.id("username")).sendKeys(email);
		
	}

	public static void newTabsManager(WebDriver driver) {
		
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://rahulshettyacademy.com/");
		
		Set<String> tabs = driver.getWindowHandles();
		Iterator<String> iterator = tabs.iterator();
		
		String parentID = iterator.next();
		
		//driver.switchTo().window(childID);
		
		String result = driver.findElement(By.xpath("(//div[@class='upper-box']/h2/a)[1]")).getText();
		
		driver.switchTo().window(parentID);
		
		driver.findElement(By.xpath("//div[@class='form-group']/input[@name='name']")).sendKeys(result);
		
		
	}
}
