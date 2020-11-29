package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class ImageDownloader {
    
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
            e.printStackTrace();
        }
    }
    
    public static void runWithResizing(final String imageName, final String imageDir, final String imageUrl) {
        //see EbayFotoDownloader
    }
}
