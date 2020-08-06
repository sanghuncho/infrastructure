package opencv;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.opencv.core.Core;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author sanghuncho
 * 
 * Refernece intall https://docs.opencv.org/2.4/doc/tutorials/introduction/java_eclipse/java_eclipse.html
 * downloading opencv https://opencv.org/releases/
 * 
 * windows/preferences
 * java/Build path/user libraries/new
 * opencd
 * add external jars
 * native library locaktion/edit
 * C:\OpenCV-2.4.6\build\java\x64
 * 
 * build path/add library
 * user library/opencv
 * 
 * https://docs.opencv.org/2.4/doc/tutorials/introduction/java_eclipse/java_eclipse.html
 */
public class AutoBidingApp {
    public static final int INPUT_BID_HEIGHT = 40;
    public static final int BROWSER_TAB_HEIGHT = 50;
    
    public static final int BID_BTN_WIDTH_CENTER = 36;
    public static final int BID_BTN_HEIGHT_CENTER = 16;
    public static String link = 
            "https://www.ebay.de/itm/Apple-Watch-Series-5-GPS-Nike-44mm-Space-Grey-Top-Zustand/233668129103?_trkparms=aid%3D777001%26algo%3DDISCO.FEED%26ao%3D1%26asc%3D225116%26meid%3D9ba0d9546a6548cb812e13c8fe3f0629%26pid%3D100651%26rk%3D1%26rkt%3D1%26mehot%3Dpp%26itm%3D233668129103%26pmt%3D0%26noa%3D1%26pg%3D2380057%26algv%3DPersonalizedTopicsRefactorWithNSFWFilter%26brand%3DApple&_trksid=p2380057.c100651.m4497&_trkparms=pageci%3A4670ed76-d72c-11ea-8772-a672c6ece175%7Cparentrq%3Abf227b191730a0f11d1dba41ffffddfd%7Ciid%3A1";
    public static int BID_VALUE = 155;
    public static String buyItemHtml = "C:/Users/sanghuncho/Documents/Gkoo/buyItem.html";
    public static void main(String[] args) throws ParseException, IOException, AWTException {
//      try {
//          screenshot();
//      } catch (IOException e) {
//          e.printStackTrace();
//      }
      Timer t = new Timer();
      t.schedule(new TimerTask() {
            public void run() {
                System.out.println("runnable");
                try {
                    runAutoBidding();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
        }, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-08-03 04:49:50"));
        
        
        
        
//      Robot robot = new Robot();
//      robot.mouseMove(913, 613);
         download(link);
         waitFor(3000);
         screenshot();
         runAutoBidding();
    }
    
    public static void download(String urlString) throws IOException {
        URL url = new URL(urlString);
        try(
           BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
           BufferedWriter writer = new BufferedWriter(new FileWriter(buyItemHtml));
        ) {
           String line;
           while ((line = reader.readLine()) != null) {
              writer.write(line);
           }
           System.out.println("Page downloaded.");
        }
     }
    
    public static void screenshot() throws IOException {
         System.setProperty("webdriver.chrome.driver", "C:/GKoo/chromedriver.exe");
         WebDriver driver=new ChromeDriver();
         driver.manage().window().maximize();
         //driver.get(link);
         driver.get(buyItemHtml);
         File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         FileUtils.copyFile(src, new File("C:/Users/sanghuncho/Documents/Gkoo/screenshot.jpg"));
         driver.close();
    }
    
    public static void waitFor(int miliseconds) {
        try{
          Thread.sleep(miliseconds);
      } catch(InterruptedException e){ }
    }
    
    public static void runAutoBidding() throws IOException {
//      openWeb(link);
//        
//        try{
//            Thread.sleep(10000);
//        } catch(InterruptedException e){ }
//        
//        getScreenshot();
        //screenshot();
        
      try{
          Thread.sleep(3000);
      } catch(InterruptedException e){ }
        
        openWeb(link);
        
        try{
            Thread.sleep(10000);
        } catch(InterruptedException e){ }
        
        if (!detectButton()) {
            screenshot();
            
            try{
                Thread.sleep(10000);
            } catch(InterruptedException e){ }
            
            detectButton();
        }
    }
    
    public static void doKeypressValue(int bidValue, Robot bot) {
        char [] chars = String.valueOf(bidValue).toCharArray();
        int length = chars.length;
        for (char value : chars) {
            switch(value){
                case '0':
                    bot.keyPress(KeyEvent.VK_0);
                    break;
                case '1':
                    bot.keyPress(KeyEvent.VK_1);
                    bot.keyRelease(KeyEvent.VK_1);
                    break;
                case '2':
                    bot.keyPress(KeyEvent.VK_2);
                    break;
                case '3':
                    bot.keyPress(KeyEvent.VK_3);
                    break;
                case '4':
                    bot.keyPress(KeyEvent.VK_4);
                    break;
                case '5':
                    bot.keyPress(KeyEvent.VK_5);
                    bot.keyRelease(KeyEvent.VK_5);
                    break;
                case '6':
                    bot.keyPress(KeyEvent.VK_6);
                    break;
                case '7':
                    bot.keyPress(KeyEvent.VK_7);
                    break;
                case '8':
                    bot.keyPress(KeyEvent.VK_8);
                    break;
                case '9':
                    bot.keyPress(KeyEvent.VK_9);
                    break;
                default:
                    System.out.println("i liegt nicht zwischen null und drei");
                    break;
                }
        }
    }
    
    public static void setCursorInput(double x, double y, int bidValue) throws AWTException {
        Robot robot = new Robot();
        int intX = (int) x;
        int intY = (int) y;
        robot.mouseMove(intX, intY - INPUT_BID_HEIGHT);
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e){ }
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        doKeypressValue(bidValue, robot);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
    
    public static void setCursorBid(double x, double y) throws AWTException {
        Robot robot = new Robot();
        int intX = (int) x;
        int intY = (int) y;
        robot.mouseMove(intX + BID_BTN_WIDTH_CENTER, intY - BID_BTN_HEIGHT_CENTER);
        
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e){ }
        robot.mouseMove(intX, intY+5);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
    
    public static void setCursorConfirm(double x, double y) throws AWTException {
        Robot robot = new Robot();
        int intX = (int) x;
        int intY = (int) y;
        //170. 36
        robot.mouseMove(intX + 85, intY + 18);
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e){ }
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
    
    private static void openWeb(String link) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(link));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static void getScreenshot() {
        BufferedImage image = null;
        try {
            image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        } catch (HeadlessException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        try {
            ImageIO.write(image, "jpg", new File("C:/Users/sanghuncho/Documents/Gkoo/screenshot.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("screenshot is created!");
    }
    
    private static boolean detectButton() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat source=null;
        Mat template=null;
        String filePath = "C:/Users/sanghuncho/Documents/Gkoo/";
        source = Imgcodecs.imread(filePath+"screenshot.jpg");
        template = Imgcodecs.imread("src/java/resources/bidButton.jpg");
        //template = Imgcodecs.imread("src/java/resources/confirmButton.jpg");
    
        Mat outputImage=new Mat();    
        int machMethod=Imgproc.TM_CCOEFF_NORMED;
        Imgproc.matchTemplate(source, template, outputImage, machMethod);
    
        MinMaxLocResult mmr = Core.minMaxLoc(outputImage);
        Point matchLoc = mmr.maxLoc;
        
        System.out.println(matchLoc.x);
        System.out.println(matchLoc.y);
        
        try {
            setCursorInput(matchLoc.x, matchLoc.y - BROWSER_TAB_HEIGHT, BID_VALUE);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        
        try {
            setCursorBid(matchLoc.x, matchLoc.y - BROWSER_TAB_HEIGHT);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        
        try{
            Thread.sleep(3000);
        } catch(InterruptedException e){ }
        
        getScreenshotConfirm();
        
        try{
            Thread.sleep(3000);
        } catch(InterruptedException e){ }
        
        Mat sourceConfirm = null;
        Mat templateConfirm = null;
        sourceConfirm = Imgcodecs.imread(filePath + "screenshotConfirm.jpg");
        templateConfirm = Imgcodecs.imread("src/java/resources/confirmButton.jpg");
    
        Mat outputImageConfirm = new Mat();    
        int machMethodConfirm = Imgproc.TM_CCOEFF_NORMED;
        Imgproc.matchTemplate(sourceConfirm, templateConfirm, outputImageConfirm, machMethodConfirm);
    
        MinMaxLocResult mmrConfirm = Core.minMaxLoc(outputImageConfirm);
        Point matchLocConfirm = mmrConfirm.maxLoc;
        
        boolean finished = false;
        if(mmrConfirm.maxVal > 0.85) {
            try {
                setCursorConfirm(matchLocConfirm.x, matchLocConfirm.y);
                finished = true;
            } catch (AWTException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("confirm button can not be found: " + "[" + mmrConfirm.maxVal + "]" );
            System.out.println("link: " + link);
            System.out.println("Bid: " + BID_VALUE);
            finished = false;
        }
        return finished;
    }
    
    private static void getScreenshotConfirm() {
        BufferedImage image = null;
        try {
            image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        } catch (HeadlessException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        try {
            ImageIO.write(image, "jpg", new File("C:/Users/sanghuncho/Documents/Gkoo/screenshotConfirm.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("screenshotConfirm is created!");
    }
}
