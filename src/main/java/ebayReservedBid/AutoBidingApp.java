package ebayReservedBid;

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
import java.util.Arrays;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.XML;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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
    private static final Logger LOGGER = LogManager.getLogger();
    public static String FILE_PATH = "C:/Users/sanghuncho/Documents/Gkoo/";
    public static String CHROME_DRIVER_PATH = "C:/Users/sanghuncho/Programme/";
    public static String BID_ITEM_HTML = "C:/Users/sanghuncho/Documents/Gkoo/bidItem.html";
    
    public static final int INPUT_BID_HEIGHT = 40;
    public static final int BROWSER_TAB_HEIGHT = 2;
    
    public static final int BID_BTN_WIDTH_CENTER = 36;
    public static final int BID_BTN_HEIGHT_CENTER = 16;
    
    private final boolean TEST_VERSION = true;
    private static final int SECOND_1 = 1000;
    private static final int SECOND_2 = 2000;
    private static final int SECOND_5 = 5000;
    private static final int MINUTE_1 = SECOND_1*60;
    private static final int MINUTE_2 = MINUTE_1*2;
    
    private String itemNumberEbay;
    private int bidValue;
    
    public AutoBidingApp(String itemNumberEbay, int bidValue) {
        this.itemNumberEbay = itemNumberEbay;
        this.bidValue = bidValue;
    }
    
    public String getItemNumber() {
        return itemNumberEbay;
    }
    
    public void setItemNumber(String itemNumberEbay) {
         this.itemNumberEbay = itemNumberEbay;
    }
    
    public void setBidValue(int bidValue) {
        this.bidValue = bidValue;
    }
    
    public int getBidValue() {
        return bidValue;
    }
    
    public void run() throws IOException {
        String item_url = retrieveProductURLForNaturalSearch(itemNumberEbay);
        openWeb(item_url);
        waitFor(TEST_VERSION ? SECOND_5 : MINUTE_2);
        getScreenshot();
        
        startAutoBidding(bidValue);
    }
    
    public static void main(String[] args) throws ParseException, IOException, AWTException, URISyntaxException {
         String itemNumberEbay = "184391078882";
         String item_url = retrieveProductURLForNaturalSearch(itemNumberEbay);
         int bidValue = 250;
         
         //String item_url = "https://www.ebay.de/itm/Apple-Watch-Series-4-44mm-Edelstahlgehaeuse-Space-Schwarz-Sportarmband-/254682192681";
         //download(item_url);
         //openWebDownload(item_url);
         //waitFor(MINUTE_1);
         
         openWeb(item_url);
         waitFor(SECOND_5);
         getScreenshot();
         
         startAutoBidding(bidValue);
    }
    
    private static String retrieveProductURLForNaturalSearch(String itemNumberEbay) throws IOException {
        String shopping_url="https://open.api.ebay.com/shopping?callname=GetSingleItem&responseencoding=XML&appid=SanghunC-gkoo-PRD-1c8e8a00c-ff329d94&siteid=77&version=863&ItemID=" + itemNumberEbay + "&IncludeSelector=Details,ShippingCosts";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }));
        headers.setContentType(MediaType.APPLICATION_JSON);
   
        HttpEntity<String> entity = new HttpEntity<String>(headers);
 
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(shopping_url, 
                HttpMethod.GET, entity, String.class);
 
        String result = response.getBody();
        JSONObject soapDatainJsonObject = XML.toJSONObject(result);
        //System.out.println(result);
        JSONObject itemObjectJson = soapDatainJsonObject.getJSONObject("GetSingleItemResponse");
        JSONObject itemObjectContentJson = itemObjectJson.getJSONObject("Item");
        LOGGER.info("ItemURL: " + itemObjectContentJson.getString("ViewItemURLForNaturalSearch"));
        return itemObjectContentJson.getString("ViewItemURLForNaturalSearch");
    }
    
    public static void download(String urlString) throws IOException {
        URL url = new URL(urlString);
        try(
           BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
           BufferedWriter writer = new BufferedWriter(new FileWriter(BID_ITEM_HTML));
        ) {
           String line;
           while ((line = reader.readLine()) != null) {
              writer.write(line);
           }
           LOGGER.trace("Page downloaded.");
        }
     }
    
    public static void openWebDownload(String urlString) throws IOException, URISyntaxException {
        URI uri = new URI(urlString);
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(uri);
            } catch (IOException e) {
                LOGGER.error("Error openWeb");
            }
        }
        waitFor(SECOND_1);
        
        try(
           BufferedReader reader = new BufferedReader(new InputStreamReader(uri.toURL().openStream()));
           BufferedWriter writer = new BufferedWriter(new FileWriter(BID_ITEM_HTML));
        ) {
           String line;
           while ((line = reader.readLine()) != null) {
              writer.write(line);
           }
           LOGGER.trace("Page downloaded.");
        }
     }
    
    public static void screenshotWebDriver(String itemUrl) throws IOException {
         System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH + "chromedriver.exe");
         WebDriver driver = new ChromeDriver();
         driver.manage().window().maximize();
         driver.get(itemUrl);
         //driver.get(BID_ITEM_HTML);
         waitFor(MINUTE_1);
         File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         FileUtils.copyFile(src, new File(FILE_PATH + "screenshot.jpg"));
         driver.close();                 
    }
    
    private static void openWeb(String link) {
        LOGGER.info("ready to open web");
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(link));
            } catch (IOException e) {
                LOGGER.error("Error openWeb");
            } catch (URISyntaxException e) {
                LOGGER.error("Error openWeb");
            }
        }
    }
    
    private static void getScreenshot() {
        LOGGER.info("ready to screenshot");
        BufferedImage image = null;
        try {
            image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        } catch (HeadlessException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        try {
            ImageIO.write(image, "jpg", new File(FILE_PATH + "screenshot.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.info("screenshot is created!");
    }
    
    public static void waitFor(int miliseconds) {
        try{
          Thread.sleep(miliseconds);
      } catch(InterruptedException e){ 
          LOGGER.trace("wait for error");
      }
    }
    
    public static void startAutoBidding(int bidValue) throws IOException {
        doDetectButtonAndBid(bidValue);
    }
    
    public static void doKeypressValue(int bidValue, Robot bot) {
        char [] chars = String.valueOf(bidValue).toCharArray();
        for (char value : chars) {
            switch(value){
                case '0':
                    bot.keyPress(KeyEvent.VK_0);
                    bot.keyRelease(KeyEvent.VK_0);
                    break;
                case '1':
                    bot.keyPress(KeyEvent.VK_1);
                    bot.keyRelease(KeyEvent.VK_1);
                    break;
                case '2':
                    bot.keyPress(KeyEvent.VK_2);
                    bot.keyRelease(KeyEvent.VK_2);
                    break;
                case '3':
                    bot.keyPress(KeyEvent.VK_3);
                    bot.keyRelease(KeyEvent.VK_3);
                    break;
                case '4':
                    bot.keyPress(KeyEvent.VK_4);
                    bot.keyRelease(KeyEvent.VK_4);
                    break;
                case '5':
                    bot.keyPress(KeyEvent.VK_5);
                    bot.keyRelease(KeyEvent.VK_5);
                    break;
                case '6':
                    bot.keyPress(KeyEvent.VK_6);
                    bot.keyRelease(KeyEvent.VK_6);
                    break;
                case '7':
                    bot.keyPress(KeyEvent.VK_7);
                    bot.keyRelease(KeyEvent.VK_7);
                    break;
                case '8':
                    bot.keyPress(KeyEvent.VK_8);
                    bot.keyRelease(KeyEvent.VK_8);
                    break;
                case '9':
                    bot.keyPress(KeyEvent.VK_9);
                    bot.keyRelease(KeyEvent.VK_9);
                    break;
                default:
                    System.out.println("i liegt nicht zwischen null und drei");
                    break;
                }
        }
    }
    
    public static void moveCursorInputBidValue(double x, double y, int bidValue) throws AWTException {
        Robot robot = new Robot();
        int intX = (int) x;
        int intY = (int) y;
        int bidIntY = intY - INPUT_BID_HEIGHT;
        robot.mouseMove(intX, bidIntY);
        LOGGER.info("mouse moved to X:" + intX + ", Y: " + bidIntY);
        //robot.mouseMove(intX, intY);
        waitFor(SECOND_1);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        doKeypressValue(bidValue, robot);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
    
    public static void clickCursorOnBidButton(double x, double y) throws AWTException {
        Robot robot = new Robot();
        int intX = (int) x;
        int intY = (int) y;
        robot.mouseMove(intX + BID_BTN_WIDTH_CENTER, intY - BID_BTN_HEIGHT_CENTER);
        waitFor(SECOND_1);
        robot.mouseMove(intX, intY+5);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
    
    public static void setCursorConfirm(double x, double y) throws AWTException {
        Robot robot = new Robot();
        int intX = (int) x;
        int intY = (int) y;
        robot.mouseMove(intX + 85, intY + 18);
        waitFor(SECOND_1);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
    
    private static boolean doDetectButtonAndBid(int bidValue) {
        LOGGER.info("do detect button and bid");
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat source=null;
        Mat template=null;
        source = Imgcodecs.imread(FILE_PATH + "screenshot.jpg");
        template = Imgcodecs.imread("src/java/resources/bidButton.jpg");
    
        Mat outputImage=new Mat();    
        int machMethod=Imgproc.TM_CCOEFF_NORMED;
        Imgproc.matchTemplate(source, template, outputImage, machMethod);
    
        MinMaxLocResult mmr = Core.minMaxLoc(outputImage);
        Point matchLoc = mmr.maxLoc;
        
        //System.out.println(matchLoc.x);
        //System.out.println(matchLoc.y);
        
        try {
            //moveCursorInputBidValue(matchLoc.x, matchLoc.y - BROWSER_TAB_HEIGHT, bidValue);
            moveCursorInputBidValue(matchLoc.x, matchLoc.y, bidValue);
        } catch (AWTException e) {
            LOGGER.error("Error moveCursorInputBidValue");
        }
        
        try {
            //clickCursorOnBidButton(matchLoc.x, matchLoc.y - BROWSER_TAB_HEIGHT);
            clickCursorOnBidButton(matchLoc.x, matchLoc.y);
        } catch (AWTException e) {
            LOGGER.error("Error clickCursorOnBidButton");
        }
        
        waitFor(SECOND_2);
        
        saveScreenshotConfirm();
        
        waitFor(SECOND_2);
        
        return checkConfirmMessage();
    }
    
    private static boolean checkConfirmMessage() {
        Mat sourceConfirm = null;
        Mat templateConfirm = null;
        sourceConfirm = Imgcodecs.imread(FILE_PATH + "screenshotConfirm.jpg");
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
                LOGGER.error("Error checkConfirmMessage");
            }
        } else {
            LOGGER.error("confirm button can not be found: " + "[" + mmrConfirm.maxVal + "]" );
            finished = false;
        }
        return finished;
    }
    
    private static void saveScreenshotConfirm() {
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
            LOGGER.error("Error saveScreenshotConfirm");
        }
        LOGGER.info("screenshotConfirm is created!");
    }
    
    public static class AutoBidingAppBuilder {
        private String itemNumberEbay;
        private int bidValue;
        
        public AutoBidingAppBuilder() {}
        
        public AutoBidingAppBuilder(String itemNumberEbay, int bidValue) {
            this.itemNumberEbay = itemNumberEbay;
            this.bidValue = bidValue;
        }
        
        public AutoBidingAppBuilder withItemNumber(String itemNumberEbay) {
            this.itemNumberEbay = itemNumberEbay;
            return this;
        }
        
        public AutoBidingAppBuilder withBidValue(int bidValue) {
            this.bidValue = bidValue;
            return this;
        }
        
        public AutoBidingApp build() {
            AutoBidingApp app = new AutoBidingApp(itemNumberEbay, bidValue);
            return app;
        }
    }
}