package Selenium;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;


public class _11_Streams {
	 
	public static void main(String[] args) {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		
		// System.setProperty("webdriver.chrome.driver", "E:\\work\\qa engineering\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);
		
		ArrayList<String> names = new ArrayList<String>();
		names.add("John");
		names.add("Arut");
		names.add("Albert");
		names.add("Rick");
		names.add("Aaron");
		
		ArrayList<String> result = new ArrayList<String>();
		int count = 0;
		
		for(int i = 0; i < names.size(); i++) {
			
			if(names.get(i).charAt(0) == 'A') {
				
				result.add(names.get(i));
				count++;
				
			}
		}
		
		System.out.println(result);
		System.out.println(count);
		
		
		
		streamFilter();
		
		streamMap();
		
		streamCollect();
		
		streamExercise(driver);
		
		streamSearchBar(driver);
	}
	
	public static void streamFilter() {
		
		ArrayList<String> names = new ArrayList<String>();
		names.add("John");
		names.add("Arut");
		names.add("Albert");
		names.add("Rick");
		names.add("Aaron");
		
		ArrayList<String> result = (ArrayList<String>) names.stream().filter(s -> s.charAt(0) == 'A').collect(Collectors.toList());
		System.out.println(result);
		System.out.println(result.size());
		
		
		//instead of creating arraylist and use .stream(). We can directly create a stream
		Long count = Stream.of("John","Arut","Albert","Rick","Aaron").filter(s -> s.charAt(0) == 'A').count();
		System.out.println(count);
		
		
		//with adding filtred s to a new declared ArrayList
		ArrayList<String> res = new ArrayList<String>();
		names.stream().filter(s -> s.length() > 4).forEach(s -> res.add(s));
		System.out.println(res);
		
		
		//with limit
		ArrayList<String> res1 = new ArrayList<String>();
		names.stream().filter(s -> s.length() > 4).limit(1).forEach(s -> res1.add(s));
		System.out.println(res1);
		
	}
	
	public static void streamMap() {
		
		Stream.of("John","Arut","Albert","Rick","Aaron").filter(s -> s.endsWith("t")).map(s -> s.toUpperCase()).forEach(s -> System.out.println(s));
		
		List<String> names = Arrays.asList("John","Arut","Albert","Rick","Aaron");
		names.stream().map(s -> s.toLowerCase()).filter(s -> s.startsWith("a")).sorted().forEach(s -> {s.toUpperCase();
			System.out.println(s);} );
		List<String> names1 = Arrays.asList("Victor","Vitali","Dima","Ivan","Alex");
		
		
		//merging 2 arrays
		Stream<String> namesTot = Stream.concat(names.stream(), names1.stream());
		List<String> result = new ArrayList<String>();
		namesTot.forEach(s -> result.add(s));
		System.out.println(result);
		
		
		List<String> names3 = Arrays.asList("Victor","Vitali","Dima","Ivan","Alex");
		boolean flag = names3.stream().anyMatch(s -> s.equalsIgnoreCase("Vitali"));
		Assert.assertTrue(flag);
		System.out.println(flag);
		
	}
	
	public static void streamCollect() {
		
		List<String> collectResult = Stream.of("John","Arut","Albert","Rick","Aaron").filter(s -> s.endsWith("t"))
																					 .map(s -> s.toUpperCase())
																					 .collect(Collectors.toList());
		
		System.out.println(collectResult);
		
		
		
		
		List<Integer> numbers = Arrays.asList(3, 2, 2, 7, 5, 1, 9, 7);
		
		List<Integer> filtredAndSortedNumbers = numbers.stream().sorted()
																.distinct()
																.collect(Collectors.toList());
		
		System.out.println(filtredAndSortedNumbers);
	}
	
	public static void streamExercise(WebDriver driver) {
		
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		
		
		driver.findElement(By.xpath("//span[text()='Veg/fruit name']")).click();
		
		//order and check if it's ordered
		List<WebElement> products = driver.findElements(By.xpath("//td[1]"));
		List<String> elements = products.stream().map(s -> s.getText()).collect(Collectors.toList());
		List<String> SortedElements = elements.stream().sorted().collect(Collectors.toList());
		
		Assert.assertTrue(elements.equals(SortedElements));	
		
		//get single price
		List<String> price = new ArrayList<>();
		
		while(price.size() < 1) {
			
			List<WebElement> rows = driver.findElements(By.xpath("//td[1]"));
			price = rows.stream().filter(s -> s.getText().contains("Rice")).map(s -> getPrice(s)).collect(Collectors.toList());
			
			if(price.size() < 1) driver.findElement(By.xpath("//a[@aria-label='Next']")).click();
			
		}
			
		//checking if result is correct
		String[] expected = {"37"};
		Assert.assertEquals(price, Arrays.asList(expected));
		System.out.println(price);
		System.out.println(Arrays.asList(expected));
		
	}
	
	public static String getPrice(WebElement s) {
		//extract a price
		String priceValue = s.findElement(By.xpath("following-sibling::td[1]")).getText();
		
		return priceValue;
		
	}
	
	public static void streamSearchBar(WebDriver driver) {
		
		//search for product
		driver.findElement(By.id("search-field")).sendKeys("Apple");
		List<WebElement> foundedProducts = driver.findElements(By.xpath("//td[1]"));
		
		List<String> namesProducts = foundedProducts.stream().map(s -> s.getText()).collect(Collectors.toList());
		
		 for(int i = 0; i < foundedProducts.size(); i++) {	
			System.out.println(foundedProducts.get(i).getText());
		} 
		
		String[] test = {"Apple", "Pineapple"};
		Assert.assertEquals(Arrays.asList(test), namesProducts);
 		
	}
	
}
