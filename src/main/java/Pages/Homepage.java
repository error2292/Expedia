package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericMethod.Base;

public class Homepage extends Base{
	WebDriver driver;
   @FindBy(xpath = "//input[@id=\"package-origin-hp-package\"]")
   WebElement origin;
   
   @FindBy(xpath = "//input[@id=\"package-destination-hp-package\"]")
   WebElement destination;
   
   @FindBy(xpath ="//input[@id=\"package-departing-hp-package\"]")
   WebElement departuredate;
   @FindBy(id ="package-returning-hp-package")
   WebElement returndate;
   
   public Homepage(WebDriver driver) {
	   this.driver = driver;
	   PageFactory.initElements(driver, this);
   }
   public void originTab(String Origin) {
	   origin.sendKeys(Origin);
   }
   public void destinationtab(String Destination) {
	   destination.sendKeys(Destination);
   }
   public void departuredate(String Departure) {
	   departuredate.sendKeys(Departure);
   }
   public void returnDate(String Return) {
	   returndate.sendKeys(Return);
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}
