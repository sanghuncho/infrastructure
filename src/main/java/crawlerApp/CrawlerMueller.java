package crawlerApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlerMueller {
    
    public static String CHROME_DRIVER_PATH =  "C:/Users/sanghuncho/Programme/";
    public static String FILE_PATH = "C:/Users/sanghuncho/Documents/GKoo_Store_Project/화장품/페나텐/images/";
    public static String MUELER = "https://www.mueller.de";
    private static final Logger LOGGER = LogManager.getLogger();
    
    public static void main(String[] args) throws IOException {
        String brandUrl = "https://www.mueller.de/search/?q=weleda&ms=true";
        List<String> itemUrlList = getItemUrlList(brandUrl);
    }
    
    public static List<String> getItemUrlList(final String brandUrl) throws IOException {
        Objects.requireNonNull(brandUrl);
        
        Document doc = Jsoup.connect(brandUrl).get();
        Element body = doc.body();
        Elements elements = body.getElementsByClass("mu-product-list__items");
        Element elem = elements.get(0);
        //LOGGER.debug(elem);
        Elements links = elem.select("a[href]"); // a with href
        //LOGGER.debug(links.get(0));
        List<String> itemUrls = new ArrayList<>();
        for(Element link : links) {
            String linkHref = MUELER + link.attr("href");
            LOGGER.info(linkHref);
            itemUrls.add(linkHref);
        }
        return itemUrls;
   }
}
