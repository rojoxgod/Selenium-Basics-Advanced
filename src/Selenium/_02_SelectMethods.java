package Selenium;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class _02_SelectMethods {

	public static void main(String[] args) throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		
		// System.setProperty("webdriver.chrome.driver", "E:\\work\\qa engineering\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		
		
	
		// Dropdowns with SELECT tag
		WebElement ddSelect = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
		Select dropdown = new Select(ddSelect);
		dropdown.selectByIndex(3);
		dropdown.getFirstSelectedOption().getText();
		dropdown.selectByVisibleText("AED");
		
		
	
		driver.findElement(By.id("divpaxinfo")).click();
		Thread.sleep(1000);
		
		int counter = 0;
		while(counter < 3) {
			driver.findElement(By.id("hrefIncAdt")).click();
			counter++;
		}
		
		driver.findElement(By.id("btnclosepaxoption")).click();
		
		
		
		// xpath parent child construction
		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_originStation1_CTNR']//a[@value='BLR']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']//a[@value='MAA']")).click();
		
		// calendar handling
		
		driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/table/tbody/tr[2]/td[3]/a")).click();
		
		if(driver.findElement(By.id("Div1")).getAttribute("style").contains("opacity: 0.5")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
		
		
		
		
		// AutoSuggestive dropdown
		driver.findElement(By.id("autosuggest")).sendKeys("ind");
		Thread.sleep(1000);
		
		List<WebElement> search = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));
		
		for(int i = 0; i < search.size(); i++) {
			
			if(search.get(i).getText().equals("India")){
			search.get(i).click();
			}
		}
		
	
		
		// checkbox count
		driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily")).click();
		driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily")).isSelected();
		Assert.assertTrue(driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily")).isSelected());
		
		List<WebElement> checkbox = driver.findElements(By.xpath("//input[@type='checkbox']"));
		int actual = checkbox.size();
		int expected = 6;
		Assert.assertEquals(actual, expected);
		
		
		
		
		driver.findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();
		
		
	}

}
