package GenericMethod;


import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Base {
	public  WebDriver driver;
	public String url;
	public JavascriptExecutor js;
	//public GM gm;
	//Homepage home;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver" , "C:\\Program Files\\SeleniumTool\\chromedriver.exe");
		//ChromeOptions options = new ChromeOptions();
		//options.setPageLoadStrategy(PageLoadStrategy.NONE);
		// Instantiate the chrome driver
		 driver = new ChromeDriver();
		 url = "https://www.expedia.com/";
		 js = (JavascriptExecutor)driver;
		// js.executeScript("window.location = 'https://www.expedia.com/ ';");
		// home= new Homepage(driver);
	    //    gm = new GM(driver);
		 driver.get(url);
		 driver.manage().window().maximize();
		 driver.manage().deleteAllCookies();
		 driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
	     driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

    @AfterMethod
    public void close() throws InterruptedException, Exception {
        Thread.sleep(10000);
        driver.quit();
    }

	  //clicks
  public void clickByCss(String locator) {
      driver.findElement(By.cssSelector(locator)).click();
  }

  public void clickByXpath(String locator) {
	driver.findElement(By.xpath(locator)).click();
  }

  public String clickByXpath(String locator, boolean getParameters) {
      String base_url = " ";
      driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
      try {
          driver.findElement(By.xpath(locator)).click();
      } catch (NoSuchElementException ex) {
          ex.printStackTrace();
      }
      if (getParameters)
          driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
      String new_url = driver.getCurrentUrl();
      if (!new_url.equals(base_url) && new_url.contains(base_url)) {
          String changed_part_url = new_url.substring(base_url.length());
          System.out.println(changed_part_url);
          return changed_part_url;
      } else if (new_url.equals(base_url) && !new_url.contains(base_url)) {
          System.out.println(new_url);
          return new_url;
      } else if (new_url.equals(base_url))
          System.out.println(new_url);
      return null;

  }

  public void clickByLinkedText(String locator) {
      driver.findElement(By.linkText(locator)).click();
  }

  public void clickByElement(String locator) {
      try {
          driver.findElement(By.name(locator)).click();
      } catch (Exception ex1) {
          try {
              driver.findElement(By.className(locator)).click();
          } catch (Exception ex2) {
              driver.findElement(By.id(locator)).click();
          }
      }
  }

  //click n clear
  public void clickNClearbyXpath(String xpath) {
      driver.findElement(By.xpath(xpath)).clear();
  }

  //typeNenter
  public void typeByCssNEnter(String locator, String value) {
      driver.findElement(By.cssSelector(locator)).sendKeys(value, Keys.ENTER);
  }

  public void typeByXpathNEnter(String locator, String value) {
      driver.findElement(By.xpath(locator)).sendKeys(value, Keys.ENTER);
  }

  public void typeOnInputFieldNEnter(String locator, String value) {
      try {
          driver.findElement(By.className(locator)).sendKeys(value, Keys.ENTER);
      } catch (Exception ex) {
          driver.findElement(By.id(locator)).sendKeys(value, Keys.ENTER);
      }
  }

  //type
  public void typeByCss(String locator, String value) {
      driver.findElement(By.cssSelector(locator)).sendKeys(value);
  }

  public void typeByXpath(String locator, String value) {
      driver.findElement(By.xpath(locator)).sendKeys(value);
  }

  public void typeOnInputField(String locator, String value) {
      try {
          driver.findElement(By.className(locator));
      } catch (Exception ex) {
          driver.findElement(By.id(locator));
      }
  }

  //clear
  public void clearInputFieldbyCSS(String locator) {
      driver.findElement(By.cssSelector(locator)).clear();
  }

  //get text
  public String getTextByCss(String locator) {
      String st = driver.findElement(By.cssSelector(locator)).getText();
      return st;
  }

  public String getTextByXpath(String locator) {
      String st = driver.findElement(By.xpath(locator)).getText();
      return st;
  }

  public String getTextByIDorName(String locator) {
      try {
          String st = driver.findElement(By.id(locator)).getText();
          return st;
      } catch (Exception ex) {
          String st = driver.findElement(By.name(locator)).getText();
          return st;
      }
  }

  //navigate
  public void navigateBack() {
      driver.navigate().back();
  }

  public void navigateForward() {
      driver.navigate().forward();
  }

  //sleep
  public void sleepFor(int sec) throws InterruptedException {
      Thread.sleep(sec * 1000);
  }

  //webElements
  public List<String> getTextFromWebElements(String type, String locator) {
      List<WebElement> element;
      List<String> text = new ArrayList<String>();
      element = getElementList(type, locator);
      for (WebElement we : element) {
          text.add(we.getText());
      }
      return text;
  }

  //new tab
  public void openNewTab(String urlLink) {
      String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
      driver.findElement(By.linkText(urlLink)).sendKeys(selectLinkOpeninNewTab);
      ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
      driver.switchTo().window(tabs.get(0));

  }

  public List<WebElement> getElementsByXpathforList(String Xpath) {
      List<WebElement> value = driver.findElements(By.xpath(Xpath));
      return value;
  }

  public List<WebElement> getElementsByCSSforList(String CSS) {
      List<WebElement> value = driver.findElements(By.cssSelector(CSS));
      return value;
  }

  public List<WebElement> getElementList(String type, String locator) {
      type = type.toLowerCase();
      List<WebElement> elementList = new ArrayList<WebElement>();
      if (type.equals("id")) {
          elementList = driver.findElements(By.id(locator));
      } else if (type.equals("name")) {
          elementList = driver.findElements(By.name(locator));
      } else if (type.equals("xpath")) {
          elementList = driver.findElements(By.xpath(locator));
      } else if (type.equals("css")) {
          elementList = driver.findElements(By.cssSelector(locator));
      } else if (type.equals("classname")) {
          elementList = driver.findElements(By.className(locator));
      } else if (type.equals("tagname")) {
          elementList = driver.findElements(By.tagName(locator));
      } else if (type.equals("linktext")) {
          elementList = driver.findElements(By.linkText(locator));
      } else if (type.equals("partiallinktext")) {
          elementList = driver.findElements(By.partialLinkText(locator));
      } else {
          System.out.println("Locator type not supported");
      }
      if (elementList.isEmpty()) {
          System.out.println("Element not found with " + type + ": " + locator);

      } else {
          System.out.println(elementList);
      }
      return elementList;
  }

  //mouseHOver
  public void mouseHoverNClickbyXC(String locator, String linkText) {
      try {
          Actions action = new Actions(driver);
          action.moveToElement(driver.findElement(By.cssSelector(locator))).build().perform();
          Thread.sleep(3000);
          driver.findElement(By.linkText(linkText)).click();
      } catch (Exception ex) {
          System.out.println("First attempt has been done, This is second try");
          Actions action = new Actions(driver);
          action.moveToElement(driver.findElement(By.xpath(locator))).build().perform();
          driver.findElement(By.linkText(linkText)).click();
      }
  }

  //switch tabs
  public void switchTabs(Integer toClose, Integer toKeep) {
      ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
      driver.switchTo().window(tabs.get(toClose));
      driver.close();
      driver.switchTo().window(tabs.get(toKeep));

  }

  //implicity_wait
  public void implicityWait() {
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  //get url
  public String getCurrentPageUrl() {
      String url = driver.getCurrentUrl();
      return url;
  }

  //handling Alert
  public void okAlert() {
      Alert alert = driver.switchTo().alert();
      alert.accept();
  }

  public void cancelAlert() {
      Alert alert = driver.switchTo().alert();
      alert.dismiss();

  }

  //explicit wait
  public void explicitWaitByXpath(String locator, int second) {
      WebDriverWait w = new WebDriverWait(driver, second);
      w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	
  }

  //iframe Handle --window for popup window, frame for iframe,default content for going back to home window
  public void iframeHandle(WebElement element) {
      driver.switchTo().frame(element);
  }

  //go back to home
  public void goBackToHomeWindow() {
      driver.switchTo().defaultContent();
  }

  //get Links
  public void getLinks(String locator) {
      driver.findElement(By.linkText(locator)).findElement(By.tagName("a")).getText();
  }

  //Taking Screen shots
  public void takeScreenShot() throws IOException {
      File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(file, new File("screenShots.png"));
  }

  //upload files
  public void upLoadFile(String locator, String path) {
      driver.findElement(By.cssSelector(locator)).sendKeys(path);
      /* path example to upload a file/image
         path= "C:\\Users\\rrt\\Pictures\\ds1.png";
       */
  }//clear Input

  public void clearInputByCSS(String locator) {
      driver.findElement(By.cssSelector(locator)).clear();
  }

  //clear Input
  public void clearInputByXpath(String locator) {
      driver.findElement(By.xpath(locator)).clear();
  }
  //get time

  //keys Input
  public void keysInput(String locator) {
      driver.findElement(By.cssSelector(locator)).sendKeys(Keys.ENTER);
  }//convert to string

  private Date getTime(long millis) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeInMillis(millis);
      return calendar.getTime();
  }

  //mouse hover
  public void mouseHoverByXpath(String locator) {
      try {
          WebElement element = driver.findElement(By.xpath(locator));
          Actions action = new Actions(driver);
          Actions hover = action.moveToElement(element);
      } catch (Exception ex) {
          System.out.println("First attempt has been done, This is second try");
          WebElement element = driver.findElement(By.cssSelector(locator));
          Actions action = new Actions(driver);
          action.moveToElement(element).perform();

      }

  }

  //Synchronization
  public void waitUntilClickAble(By locator) {
      WebDriverWait wait = new WebDriverWait(driver, 10);
      WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
  }

  public void waitUntilVisible(By locator) {
      WebDriverWait wait = new WebDriverWait(driver, 10);
      WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  public void waitUntilSelectable(By locator) {
      WebDriverWait wait = new WebDriverWait(driver, 10);
      boolean element = wait.until(ExpectedConditions.elementToBeSelected(locator));
  }

  public void clickWhenReady(By locator, int timeout) {
      try {
          WebElement element = null;
          System.out.println("Waiting for a max: " + timeout + " seconds for element to be clickable.");
          WebDriverWait wait = new WebDriverWait(driver, timeout);
          element = wait.until(ExpectedConditions.elementToBeClickable(locator));
          element.click();
          System.out.println("Element clicked on web page");
      } catch (Exception e) {
          System.err.println("Element not found on web page.");
      }
  }

  public WebElement waitForElement(By locator, int timeout) {
      WebElement element = null;
      try {
          System.out.println("Waiting for a max: " + timeout + " seconds for element to be available.");
          WebDriverWait wait = new WebDriverWait(driver, timeout);
          element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
          System.out.println("Element found on web page");
      } catch (Exception e) {
          System.err.println("Element not found on web page.");
      }
      return element;
  }

  public boolean isElementPresent(String type, String locator) {
      List<WebElement> elementList = getElementList(type, locator);
      int size = elementList.size();
      if (size > 0) {
          return true;
      } else {
          return false;
      }
  }

  public List<WebElement> clickableLinks() {
      List<WebElement> linksToClick = new ArrayList<WebElement>();
      List<WebElement> elements = driver.findElements(By.tagName("a"));
      elements.addAll(driver.findElements(By.tagName("img")));

      for (WebElement we : elements) {
          if (we.getAttribute("href") != null) {
              linksToClick.add(we);
          }
      }
      return linksToClick;
  }

  public String getLinkStatus(URL url) {
      try {
          HttpURLConnection http = (HttpURLConnection) url.openConnection();
          http.connect();
          String responseMessage = http.getResponseMessage();
          http.disconnect();
          return responseMessage;
      } catch (Exception e) {
          return e.getMessage();
      }
  }

  public void selectOptionByVisibleText(WebElement element, String value) {
      Select select = new Select(element);
      select.selectByVisibleText(value);
  }

  
}




