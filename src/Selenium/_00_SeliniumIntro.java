package Selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class _00_SeliniumIntro {

	public static void main(String[] args) {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		// to allow CORS only for chrome browser
		
		System.setProperty("webdriver.chrome.driver", "E:\\work\\qa engineering\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);
		// to set webdriver only for chrome browser
		
		driver.get("https://www.rahulshettyacademy.com/");
		
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		
		driver.close(); // closes only invoked tab
		driver.quit(); // closes all tabs
		
	}

}
