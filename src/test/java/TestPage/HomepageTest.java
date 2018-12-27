package TestPage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import GenericMethod.Base;


public class HomepageTest extends Base {
	
/*
	@Test
	public void testmethod() throws InterruptedException {
		String st = getTextByXpath("//*[@id=\"primary-header-package\"]");
		System.out.println(st);
		Thread.sleep(1000);
		
	}	*/
	@Test
	public void FilloutBox() throws InterruptedException {
		typeByXpathNEnter("//input[@id=\"package-origin-hp-package\"]", "Miami");
	    typeByXpathNEnter("//input[@id=\"package-destination-hp-package\"]", "Paris");
	    //Click on Calendar
		clickByXpath("//input[@id=\"package-departing-hp-package\"]");
		clickByXpath("//div[@class= 'datepicker-cal-month'][position()=2]//button[@type='button'and @data-day='3']");
		
		// gm.typeByXpath("//input[@id=\"package-returning-hp-package\"]", "12/04/2018");
		    clickByXpath("//input[@type=\"checkbox\" and @id=\"partialHotelBooking-hp-package\"]");
		    WebElement element = driver.findElement(By.xpath("//select[@id=\"package-advanced-preferred-class-hp-package\" and @class ='gcw-storeable gcw-show-with-value']"));
		    Select sel = new Select(element);
	    	sel.selectByVisibleText("First class");
	    	Thread.sleep(2000);
			
	    	System.out.println("print the list of option");
	   
	    	
	    	List<WebElement>options = sel.getOptions();
	    	int size = options.size();
	    	 for(int i=0;i<size ; i++) {
	    		 String optionName = options.get(i).getText();
	    		 System.out.println(optionName);
	    	 }
	    	//Select sel = new Select(element);
	       //sel.selectByVisibleText("First class");
	    	
		
	}
}
	//using explicit wait
	/*@Test
	public void myTrip() throws InterruptedException {
		clickByXpath("//a[@id=\"header-itineraries\"and @class='nav-tab']");
		     WebDriverWait w = new WebDriverWait(driver, 3);
		     WebElement email = w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"unified-itin-emailId\"and @type='text']")));
		     email.sendKeys("errror123@gmail.com");
	   // explicitWaitByXpath("//input[@id=\"unified-itin-emailId\"and @type='text']",3);
		//typeByXpath("//input[@id=\"unified-itin-emailId\"and @type='text']","errror123@gmail.com");
		 Thread.sleep(1000);
		
	}*/
	
  /*   @Test
     public void scroll() throws InterruptedException {
    	 js.executeScript("window.scrollBy(0, 300);");
    	
    	js.executeScript("window.scrollBy(0, -2000);");
    	clickByXpath("//*[@id=\"expedia-links\"]/li[2]/a");
    	Thread.sleep(1000);
        navigateBack();
        // js.executeScript("arguments[0].scrollIntoView(true);", element);
    	// Thread.sleep(2000);
    	// js.executeScript("window.scrollBy(0, -200);");
     }
	
    	
    	
     }
	
		
		
		
		
		
		
		
		
		
		
		
		/*WebElement element = gm.getElement("//input[@id=\"package-origin-hp-package\"] ", "xpath");
		element.sendKeys("New York");
		WebElement met = gm.getElement("//*[@id=\"package-destination-hp-package\"]", "xpath");
		met.sendKeys("Bangladesh");*/
	
