package Selenium;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class _04_GroceryCart {

	public static void main(String[] args) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		
		// System.setProperty("webdriver.chrome.driver", "E:\\work\\qa engineering\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		
		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
				
		driver.get("https://rahulshettyacademy.com/seleniumPractise/");
		
		String[] groceryToBuy = {"Brocolli", "Cucumber", "Beans", "Tomato"};
		
		addItems(driver,groceryToBuy);
		applyPromo(driver);
		
		
		
	}
	
	public static void addItems(WebDriver driver,String[] name) {

		List<WebElement> groceryList = driver.findElements(By.cssSelector("h4.product-name"));
		
		for(int j = 0; j < name.length; j++ ) {
		
			for(int i = 0; i < groceryList.size(); i++) {
				
				if(groceryList.get(i).getText().contains(name[j])){
					
					driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
	
				}	
			}
		}
	}


	private static void applyPromo(WebDriver driver) {
		
		driver.findElement(By.xpath("//img[@alt='Cart']")).click();
		driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Enter promo code']")).sendKeys("rahulshettyacademy");
		driver.findElement(By.className("promoBtn")).click();
		
		//explicit wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("promoInfo")));
		
		String actual = driver.findElement(By.className("promoInfo")).getText();
		String excepted = "Code applied ..!";
		Assert.assertEquals(actual, excepted);
		
		driver.findElement(By.xpath("//button[text()='Place Order']")).click();
		
		Select dropdown = new Select(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/select")));
		dropdown.selectByVisibleText("Italy");
		
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//button[text()='Proceed']")).click();

	}
	
}



