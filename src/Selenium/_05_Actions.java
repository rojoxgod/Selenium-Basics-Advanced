package Selenium;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class _05_Actions {

	public static void main(String[] args) throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		
		// System.setProperty("webdriver.chrome.driver", "E:\\work\\qa engineering\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
				
		driver.get("https://www.amazon.it/");
		driver.findElement(By.id("sp-cc-accept")).click();
		
		Actions act = new Actions(driver);
		
		act.moveToElement(driver.findElement(By.id("twotabsearchtextbox"))).click().keyDown(Keys.SHIFT).sendKeys("hello").doubleClick().build().perform();
		
		Thread.sleep(1500);
		act.moveToElement(driver.findElement(By.id("nav-link-accountList"))).build().perform();
		
		act.moveToElement(driver.findElement(By.id("nav-link-accountList"))).contextClick().build().perform();
	
		
	}
	
}
