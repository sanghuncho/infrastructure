package auctionApi;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class AutoAuction {
        public static void main( String[] args ) throws IOException, InterruptedException, AWTException {

            //invokeSelenium();
            
            //invokdeBot();
            
            setCursor();
            
            //openDefaultBrowser();
        }
        
        public static void openDefaultBrowser() throws AWTException {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.ebay.de/itm/Leitz-Trinovid-8x20-C-Fernglas-mit-Leder-Kocher/143650031359?_trkparms=aid%3D111001%26algo%3DREC.SEED%26ao%3D1%26asc%3D225116%26meid%3De32fdcccf0b94b34846b8337cfd2497f%26pid%3D100675%26rk%3D1%26rkt%3D4%26mehot%3Dnone%26sd%3D143650031359%26itm%3D143650031359%26pmt%3D0%26noa%3D1%26pg%3D2380057%26brand%3DLeitz&_trksid=p2380057.c100675.m4236&_trkparms=pageci%3A41c4d37e-c1de-11ea-a7a8-8e0bb6599916%7Cparentrq%3A338306e41730a12afe348dfaffed7782%7Ciid%3A1"));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }
        
        public static void setCursor() throws AWTException {
            Robot robot = new Robot();
            robot.mouseMove(630 + 140, 517 + 20);
        }
        
        public static void invokdeBot(int height) throws AWTException {
            //int height = 534;
            Robot robot = new Robot();
            robot.mouseMove(700, height);    
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.keyPress(KeyEvent.VK_3);
            robot.keyPress(KeyEvent.VK_4);
            //robot.keyPress(KeyEvent.VK_3);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            
            robot.mouseMove(700, height + 50);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//            robot.keyRelease(KeyEvent.VK_H);
//            robot.keyPress(KeyEvent.VK_E);
//            robot.keyRelease(KeyEvent.VK_E);
//            robot.keyPress(KeyEvent.VK_L);
//            robot.keyRelease(KeyEvent.VK_L);
//            robot.keyPress(KeyEvent.VK_L);
//            robot.keyRelease(KeyEvent.VK_L);
//            robot.keyPress(KeyEvent.VK_O);
//            robot.keyRelease(KeyEvent.VK_O);
            
     
        }
        
        public static void invokeSelenium() throws AWTException {
//          System.setProperty("webdriver.gecko.driver","C:/Users/sanghuncho/Programme/geckodriver.exe"); 
//          WebDriver driver = new FirefoxDriver(); 
          System.setProperty("webdriver.chrome.driver","C:/Users/sanghuncho/Programme/chromedriver.exe");
          String link = "https://www.ebay.de/itm/Huawei-MateBook-X-Pro-2019-Notebook-Intel-Core-i5-GeForce-MX-250-512GB-SSD-8GB/184346527148?hash=item2aebe8c5ac:g:~QQAAOSwZEde-k4-";
          WebDriver driver = new ChromeDriver();
          driver.manage().window().maximize();
          //driver.get("https://www.ebay.de/");
          driver.get(link);
          System.out.println("start click");

          //Thread.sleep(3000);
//          String product="324214590535";
//          WebElement searchfield = driver.findElement(By.id("gh-ac"));//img imgWr2
//          searchfield.click();
//          searchfield.sendKeys(product);
//          
//          WebElement searchbutton = driver.findElement(By.id("gh-btn"));
//          searchbutton.click();
//
//          WebElement prodLink = driver.findElement(By.className("imgWr2"));
//          prodLink.click();

          //String value = "203";
          WebElement bidField = driver.findElement(By.id("MaxBidId"));
          Point po = bidField.getLocation();
          System.out.println(po.getX());
          System.out.println(po.getY());
          int height = po.getY();
          driver.close();
          
          try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
          
          invokdeBot(height);
//          bidField.click();
//          bidField.sendKeys(value);
//          WebElement offerButton = driver.findElement(By.id("bidBtn_btn"));
//          offerButton.click();
          
        }
}
