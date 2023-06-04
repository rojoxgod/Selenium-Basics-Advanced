package Selenium;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class _09_Screenshot {
	
	public static void main(String[] args) throws IOException{
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		
		// System.setProperty("webdriver.chrome.driver", "E:\\work\\qa engineering\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		
		EntirePageScreenshot(driver);
		
		WebElementScreenshot(driver);
		
		
	}
	
	public static void EntirePageScreenshot(WebDriver driver) throws IOException {
		
		driver.get("https://google.com");
		
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("D:\\Games\\screenshot.png"));
		
	}
	
	public static void WebElementScreenshot(WebDriver driver) throws IOException {
		
		driver.get("https://google.com");
		driver.findElement(By.xpath("//div[@class='QS5gu sy4vM']")).click();
		driver.findElement(By.className("gLFyf")).sendKeys("Hello World");
		
		//screenshot of specific webelement
		File screenshot = driver.findElement(By.className("SDkEP")).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("D:\\Games\\screenshot.png"));
		
		//screenshot of html webelement (entire page)
		File screenshot1 = driver.findElement(By.tagName("html")).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot1, new File("D:\\Games\\screenshot1.png"));
		
		
	}
	
}
	