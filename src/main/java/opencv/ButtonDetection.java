package opencv;

import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;
import org.opencv.core.Core;

public class ButtonDetection {
    public static final int INPUT_BID_HEIGHT = 30;
    
    public static final int BID_BTN_WIDTH_CENTER = 36;
    public static final int BID_BTN_HEIGHT_CENTER = 16;
    public static String link = "https://www.ebay.de/itm/Apple-Watch-Series-1-42mm-Aluminiumgehause-in-Space-Grau-mit-Lederarmband/333646020726?epid=228618880&hash=item4daeda4076%3Ag%3AY0cAAOSwWXBfAjKz&LH_Auction=1";
    public static int bidValue = 41;
    public static void main(String[] args) {
        
        openWeb(link);
        
        try{
            Thread.sleep(3000);
        } catch(InterruptedException e){ }
        
        getScreenshot();
        
        try{
            Thread.sleep(3000);
        } catch(InterruptedException e){ }
        
        detectButton();
        
    }
    
    public static void setCursorInput(double x, double y) throws AWTException {
        Robot robot = new Robot();
        int intX = (int) x;
        int intY = (int) y;
        robot.mouseMove(intX, intY - INPUT_BID_HEIGHT);
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e){ }
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.keyPress(KeyEvent.VK_4);
        robot.keyPress(KeyEvent.VK_1);
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
    
    private static void detectButton() {
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
            setCursorInput(matchLoc.x, matchLoc.y);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        
        try {
            setCursorBid(matchLoc.x, matchLoc.y);
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
        
        if(mmrConfirm.maxVal > 0.85) {
            try {
                setCursorConfirm(matchLocConfirm.x, matchLocConfirm.y);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("confirm button can not be found: " + "[" + mmrConfirm.maxVal + "]" );
            System.out.println("link: " + link);
            System.out.println("Bid: " + bidValue);
        }
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
