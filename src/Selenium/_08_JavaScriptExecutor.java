package Selenium;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class _08_JavaScriptExecutor {

	public static void main(String[] args) throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		
		// System.setProperty("webdriver.chrome.driver", "E:\\work\\qa engineering\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
				
		driver.get("https://rahulshettyacademy.com/AutomationPractice");
		
		
		
		JavascriptExecutor js = (JavascriptExecutor)driver;

		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(2000);
		
		js.executeScript("document.querySelector('.tableFixHead').scrollBy(0,150)");
		Thread.sleep(1000);
		
		
		
		List<WebElement> values = driver.findElements(By.xpath("//div[@class='tableFixHead']//td[4]"));
		
		int total = 0;
		
		for(int i = 0; i < values.size(); i++) {

			total += Integer.parseInt(values.get(i).getText());
			
		}
		
		System.out.println(total);
		
		int totalExpected = Integer.parseInt(driver.findElement(By.className("totalAmount")).getText().split(":")[1].trim());
		System.out.println(totalExpected);

		Assert.assertEquals(total, totalExpected);
		
		

	}

}
