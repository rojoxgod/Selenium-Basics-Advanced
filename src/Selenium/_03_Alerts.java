package Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class _03_Alerts {

	public static void main(String[] args) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		
		// System.setProperty("webdriver.chrome.driver", "E:\\work\\qa engineering\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		
		
		
		driver.findElement(By.id("name")).sendKeys("Vitali");
		driver.findElement(By.id("alertbtn")).click();
		
		driver.switchTo().alert().getText();
		Assert.assertTrue(driver.switchTo().alert().getText().contains("Vitali"));
		driver.switchTo().alert().accept();
		
		
		
		driver.findElement(By.id("confirmbtn")).click();
		
		driver.switchTo().alert().dismiss();
		
	}

}
