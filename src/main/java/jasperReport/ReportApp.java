package jasperReport;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import javax.imageio.ImageIO;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;

public class ReportApp {
    public static void main (String[] args) {
        JasperReport jasperReport=null;
        JasperPrint jasperPrint=null;
        
        String productName = "달마이어 프로도모 분쇄커피 500g";
        ReportProducer producer = new ReportProducer();
        producer.create(productName);
        HashMap<String, Object> parameter = (HashMap<String, Object>) producer.getParameters();
        try {
            jasperReport = JasperCompileManager.compileReport("src/main/resources/forms/productDescription.jrxml");
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, new JREmptyDataSource());
            JasperExportManager.exportReportToHtmlFile(jasperPrint, "C:/Users/sanghuncho/Documents/GKoo_Store_Project/커피/달마이어/testDecription.html");
        } catch (JRException e) {
            System.out.println(e);
        }
        
        System.out.println("the report is created!");
        
        Image jasperImage = null;
        try {
            jasperImage = JasperPrintManager.printPageToImage(jasperPrint, 0, 1.6f);
            File outputfile = new File("C:/Users/sanghuncho/Documents/GKoo_Store_Project/커피/달마이어/" + productName + ".png");
            try {
                ImageIO.write((RenderedImage) jasperImage, "png", outputfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
