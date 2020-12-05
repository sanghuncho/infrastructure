package util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import crawlerApp.CrawlerEcoverde;

public class ImageDownloader {
    private static final Logger LOGGER = LogManager.getLogger(ImageDownloader.class);

    public static void run(final String imageName, final String imageDir, final String imageUrl) {
        BufferedImage image =null;
        String imageAddr = null;
        String imageFullname = "";
        try{
            URL url =new URL(imageUrl);
            imageFullname = imageName + ".jpg";
            image = ImageIO.read(url);
            imageAddr = imageDir + imageFullname;
            ImageIO.write(image, "jpg", new File(imageAddr));
        } catch(IOException e){
            LOGGER.error("Error running of ImageDownloader:" + imageUrl, e);
        }
    }
    
    public static void runWithResizing(final String imageName, final String imageDir, final String imageUrl, int width, int height) {
        BufferedImage image =null;
        String imageAddr = null;
        String imageFullname = "";
        try{
            URL url =new URL(imageUrl);
            imageFullname = imageName + ".jpg";
            image = ImageIO.read(url);
            imageAddr = imageDir + imageFullname;
            ImageIO.write(image, "jpg", new File(imageAddr));
        } catch(IOException e){
            LOGGER.error("Error running of ImageDownloader:" + imageUrl, e);
        }
        
        BufferedImage resizeMe = null;
        try {
            resizeMe = ImageIO.read(new File(imageAddr));
        } catch (IOException e) {
        }
        Dimension newMaxSize = new Dimension(255, 255);
        if (resizeMe == null) {
            LOGGER.error("Error image is not downloaded:" + imageUrl);
        } else {
            BufferedImage resizedImg = Scalr.resize(resizeMe, Method.QUALITY,
                    newMaxSize.width, newMaxSize.height);
            try {
                ImageIO.write(resizedImg, "jpg", new File(imageAddr));
            } catch (IOException e) {
                LOGGER.error("Error resizing of ImageDownloader:" + imageUrl);
            }
        }
    }
    
    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }
}
