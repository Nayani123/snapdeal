package seleniumPractiseSessions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day11_Snapdeal {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.Chrome.driver", "./drivers/Chromedriver.exe");
		ChromeDriver driver=new ChromeDriver();
		ChromeOptions option=new ChromeOptions();
		option.addArguments("disable Notifications");
		WebDriverWait wait=new WebDriverWait(driver,30);
		driver.manage().window().maximize();
		
		//1.Go To https://www.snapdeal.com/
		driver.get("https://www.snapdeal.com/");
		
		//2. mouse over on Toys,kids'Fashion &more and click on toys 
		Thread.sleep(2000);

		WebElement toys = driver.findElementByXPath("//span[contains(text(),'Toys, Kids')]");
		Actions mouse=new Actions(driver);
		mouse.moveToElement(toys).perform();
		driver.findElementByXPath("//span[text()='Toys']").click();
		
		
		//3.click Eucational Toys in Toys&Games
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[text()='Educational Toys']")));
		driver.findElementByXPath("//div[text()='Educational Toys']").click();
		
		
		//4.Click on the customer Rating 4 star and up
		Thread.sleep(2000);
		driver.findElementByXPath("//label[@for='avgRating-4.0']").click();
		
		//5.Click the offer as 40-50
		Thread.sleep(2000);
		JavascriptExecutor  js=(JavascriptExecutor) driver;  //page scrolldown
        js.executeScript("window.scrollBy(0,1000)");
        driver.findElementByXPath("//label[@for='discount-40%20-%2050']").click();
        
       
      	//6.check the availability for the pincode
        Thread.sleep(2000);
        driver.findElementByXPath("//input[@placeholder='Enter your pincode']").sendKeys("600097");
        driver.findElementByXPath("//button[@class='pincode-check']").click();
		
		
		//7.Click the Quick view of the first product
        Thread.sleep(2000);
        WebElement firstpdt = driver.findElementByXPath("(//p[@class='product-title'])[1]");
        Actions product=new Actions(driver);
        product.moveToElement(firstpdt).perform();
        driver.findElementByXPath("(//div[contains(text(),'Quick View')])[1]").click();
        
        //8.Click on View Details
        Thread.sleep(2000);
        driver.findElementByXPath("//a[contains(text(),'view details')]").click(); 
		 
        //9.Capture the Price of the product and Delivery charge
        Thread.sleep(2000);
        String price = driver.findElementByXPath("//span[@itemprop='price']").getText();
        System.out.println("product price"+price);
        int Pprice = Integer.parseInt(price);
        
        String avialcharge = driver.findElementByXPath("(//span[@class='availCharges'])[2]").getText();
        System.out.println("charges are:"+avialcharge);
        int AvlChrge = Integer.parseInt(avialcharge.replaceAll("\\D", ""));
        
        
        int Totalprice = Pprice +  AvlChrge;
        Thread.sleep(2000);
        driver.findElementByXPath("//span[text()='add to cart']").click();
        
        //10.Validate the you pay amount matches the sum of (price+deliver charge)
        Thread.sleep(3000);
        String Amount = driver.findElementByXPath("//span[text()='Rs. 468']").getText();
        int TotalAmount = Integer.parseInt(Amount.replaceAll("\\D", ""));
        System.out.println("TotalpayAmount "+TotalAmount);
        if(TotalAmount==Totalprice) {
        	System.out.println("payable amount is matches with delivery charge");
        }else {
        	System.out.println("Amount does not matches");
        }
        
        
		//11.Search for sanitizer
        Thread.sleep(2000);
        driver.findElementByXPath("//input[@class='col-xs-20 searchformInput keyword']").sendKeys("sanitizer",Keys.ENTER);
        
		//12.Click on product "BioAyurveda Neem power Hand Sanitizer
        Thread.sleep(2000);
        driver.findElementByXPath("//p[text()='BioAyurveda Neem Power  Hand Sanitizer 500 mL Pack of 1']").click();
        Set<String> Sanitizer = driver.getWindowHandles();
        List<String> Handsanitizer=new ArrayList<String>(Sanitizer);
        driver.switchTo().window(Handsanitizer.get(1));
        
        
		//13.Capture the Price and DeliveryCharge
        Thread.sleep(2000);
        String Sprice = driver.findElementByXPath("//span[@class='payBlkBig']").getText();
        int SanPrice = Integer.parseInt(Sprice.replaceAll("\\D", ""));
        System.out.println(SanPrice);
        
        Thread.sleep(2000);
        String AvailSprice = driver.findElementByXPath("(//span[@class='availCharges'])[2]").getText();
        int SanAvailprice = Integer.parseInt(AvailSprice.replaceAll("\\D", ""));
        System.out.println(SanAvailprice);
        
        int TotalpriceOfSanitizer=SanPrice + SanAvailprice;
        System.out.println("total price of sanitizer is "+TotalpriceOfSanitizer);
        
		//14.Click on Add to cart
        Thread.sleep(3000);
        driver.findElementByXPath("(//span[text()='ADD TO CART'])[1]").click();
        
		//15.Click cart
        Thread.sleep(3000);
        driver.findElementByXPath("//span[text()='Cart']").click();
        
		//16.Validate the proceed to pay matches the total amount of both the products
        Thread.sleep(3000);
        int TotalFinalPrice = Integer.parseInt(driver.findElementByXPath("//input[@type='button']").getAttribute("value").replaceAll("\\D", ""));
        System.out.println("Finalprice of produts"+TotalFinalPrice);
        
        
         if(TotalFinalPrice == TotalpriceOfSanitizer + TotalAmount) {
        	System.out.println("Final amount Matches");
        }else {
        	System.out.println("Amount not Matched");
        }
        
		//17.Close all the windows
       driver.quit();
		
		

	}

}
