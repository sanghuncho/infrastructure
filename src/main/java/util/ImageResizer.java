package util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;

public class ImageResizer {
    private static final Logger LOGGER = LogManager.getLogger(ImageResizer.class);
    private static String imageFileDir = "C:/Users/sanghuncho/Documents/GKoo_Store_Project/화장품/ecoverde/eyecare/main_images/";
    private static String imageFilePath = imageFileDir + "MADARA Organic Skincare REGENE Optic Lifting Eye Serum.png";
    
    public static void main(String[] args) {
        LOGGER.info("Starts Resizing");
        BufferedImage resizeMe = null;
        try {
            resizeMe = ImageIO.read(new File(imageFilePath));
        } catch (IOException e) {
        }

        Dimension newMaxSize = new Dimension(500, 500);
        if (resizeMe == null) {
            LOGGER.error("Error image cannot be resized:" + imageFilePath);
        } else {
            BufferedImage resizedImg = Scalr.resize(resizeMe, Method.QUALITY,
                    newMaxSize.width, newMaxSize.height);
            try {
                ImageIO.write(resizedImg, "jpg", new File(imageFilePath));
            } catch (IOException e) {
                LOGGER.error("Error writing of Image:" + imageFilePath);
            }
        }
        
        BufferedImage bufferedImage = null;
        try{
            bufferedImage = ImageIO.read(new File(imageFilePath));
            
            int bufferedImageWidth = bufferedImage.getWidth();
            int bufferedImageWidthHalf = bufferedImageWidth/2;
            int filledWithHalf = 250 - bufferedImageWidthHalf; 
            
            BufferedImage newBufferedImage = null;
            if(bufferedImageWidth < 500) {
                newBufferedImage = new BufferedImage(bufferedImage.getWidth() + 2 * filledWithHalf, bufferedImage.getHeight(), bufferedImage.getType());

                Graphics g = newBufferedImage.getGraphics();

                g.setColor(Color.white);
                g.fillRect(0, 0, bufferedImage.getWidth() + 2 * filledWithHalf, bufferedImage.getHeight());
                g.drawImage(bufferedImage, filledWithHalf, 0, null);
                g.dispose();
            } else {
                newBufferedImage = bufferedImage;
            }
            
            ImageIO.write(newBufferedImage, "jpg", new File(imageFilePath));
        } catch(IOException e){
            LOGGER.error("Error running of ImageDownloader:" + imageFilePath, e);
        }
        LOGGER.info("Resizing is finished");
    }
}
