package Selenium;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class _01_Locators {

	public static void main(String[] args) throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		
		// System.setProperty("webdriver.chrome.driver", "E:\\work\\qa engineering\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		
		driver.findElement(By.id("inputUsername")).sendKeys("Vitali");
		driver.findElement(By.name("inputPassword")).sendKeys("123123");
		driver.findElement(By.className("signInBtn")).click();
		driver.findElement(By.linkText("Forgot your password?")).click();
		
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("Vitali");
		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("test@test.com");
		driver.findElement(By.xpath("//input[@placeholder='Phone Number']")).sendKeys("322322322");
		driver.findElement(By.cssSelector(".reset-pwd-btn")).click();
		
		
		String password = getPassword(driver);
		
		
		driver.findElement(By.cssSelector(".go-to-login-btn")).click();
		driver.findElement(By.id("inputUsername")).sendKeys("Vitali");
		driver.findElement(By.name("inputPassword")).sendKeys(password);	
		Thread.sleep(500);
		driver.findElement(By.className("signInBtn")).click();
		
		Thread.sleep(1000);
		String actual = (driver.findElement(By.cssSelector("div[class='login-container'] p")).getText());
		String expected = "You are successfully logged in.";
		Assert.assertEquals(actual, expected);
		Thread.sleep(1000);
		driver.findElement(By.className("logout-btn")).click();
		
		
		driver.navigate().to("https://google.com");
		Thread.sleep(1500);
		driver.navigate().back();
		Thread.sleep(1500);
		
		
		driver.close();

	}
	
	public static String getPassword(WebDriver driver) {
		String password = driver.findElement(By.className("infoMsg")).getText();
		int firstSymb = password.indexOf("'");
		password = password.substring(firstSymb+1, password.length());
		int lastSymb = password.indexOf("'");
		password = password.substring(0, lastSymb);
		return password;
	}

}
