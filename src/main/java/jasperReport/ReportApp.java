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
    
    private static final String googleTranslate = "https://translate.google.com/";

    public static void main (String[] args) {
        JasperReport jasperReport=null;
        JasperPrint jasperPrint=null;
        
        String productName = "게볼 메드 리피트 풋크림 125ml";
        String DIR_FILE = "C:/Users/sanghuncho/Documents/GKoo_Store_Project/화장품/게볼/";
        ReportProducer producer = new ReportProducer();
        producer.create(productName);
        HashMap<String, Object> parameter = (HashMap<String, Object>) producer.getParameters();
        try {
            jasperReport = JasperCompileManager.compileReport("src/main/resources/forms/gehwol.jrxml");
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, new JREmptyDataSource());
            JasperExportManager.exportReportToHtmlFile(jasperPrint, DIR_FILE + productName  + ".html");
            //JasperExportManager.exportReportToXmlFile(jasperPrint, DIR_FILE + productName, false);
        } catch (JRException e) {
            System.out.println(e);
        }
        
        System.out.println("the report is created!");
        
        Image jasperImage = null;
        try {
            jasperImage = JasperPrintManager.printPageToImage(jasperPrint, 0, 1.6f);
            File outputfile = new File(DIR_FILE + productName + ".png");
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
