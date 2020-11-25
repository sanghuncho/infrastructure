package crawlerApp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import crawlerEntities.MassItem;

public class CrawlerMoncler {
    public static String CHROME_DRIVER_PATH =  "C:/Users/sanghuncho/Programme/";
    public static String HTML_BRAND = "C:/Users/sanghuncho/Documents/GKoo_Store_Project/의류/moncler.html";
    public static String DIR_MAIN_IMAGES = "C:/Users/sanghuncho/Documents/GKoo_Store_Project/의류/moncler.html";
    
    public static final String BRAND_NAME = "몽클레어";
    public static final String ITEM_CATEGORY = "여성 숏자켓";
    public static final String CATEGORY_ID = "50000814";
    
    private static final Logger LOGGER = LogManager.getLogger();
    
    public static void main(String[] args) throws IOException {
        String categotyUrl = "https://store.moncler.com/de-de/damen/autumn-winter/kurze-daunenjacken";
        List<String> itemUrlList = getItemUrlList(categotyUrl);
        
        String itemUrl = "https://store.moncler.com/de-de/kurze-jacken_cod16301891330574505.html#dept=EU_Short_Down_Jackets_Women_AW";
        MassItem massItem = createMassItem(itemUrl, new MassItem(BRAND_NAME, ITEM_CATEGORY, CATEGORY_ID));
                
        //extractItemImages(itemUrl);
        
        //List<String> extractItemBaseInfo = extractItemTitle(itemUrl);
        
        //extractItemPrice(itemUrl);
        
        //extractItemColors(itemUrl);
        
        //extractItemSize(itemUrl);
        
    }
    
    public static MassItem createMassItem(final String itemUrl, MassItem item) {
        //pipeline
        
        //1. extractItemImage
        
        //2. extractItemTitle
        
        //3. extractItemPrice
        
        //4. extractItemColors
        
        //5. extractItemSize
        
        return item;
    }
    
    //saving the main image in directory -> gathering the detail images as link
    public static List<MassItem> extractItemImages(final String itemUrl, MassItem item) throws IOException {
        Objects.requireNonNull(itemUrl);

        Document doc = Jsoup.connect(itemUrl).get();
        
        Element body = doc.body();
        System.out.println(body);
        System.out.println("--------------");
        Elements elements = body.getElementsByClass("image");
        //System.out.println(elements.get(0).getElementsByClass("pdp__showcase__product-picture__desktop__product-image__selected-shot"));
        Elements images = elements.get(0).getElementsByClass("pdp__showcase__product-picture__desktop__product-image__selected-shot");
        //System.out.println(images.get(0).attr("src").toString());
        //System.out.println(images.get(0).attr("srcset").toString());
        String imageSet = images.get(0).attr("srcset").toString();
        List<String> convertedCountriesList = Arrays.asList(imageSet.split(",", -1));
        for(String image : convertedCountriesList) {
            //System.out.println(image.split(" ")[0]);
        }
        //System.out.println(convertedCountriesList.get(0).split(" ")[0]);
        
        return null;
   }
    
    public static void savingMainImage(final String itemUrl, String directory) {
        
    }
    
    public static void extractItemSize(final String brandUrl) throws IOException {
        Objects.requireNonNull(brandUrl);

        Document doc = Jsoup.connect(brandUrl).get();
        
        Element body = doc.body();
        //System.out.println(body);
        
        Elements elements = body.getElementsByClass("HTMLListColorSelector");
        //System.out.println(doc.getElementsByTag("li"));
        Elements elementsTag = doc.getElementsByTag("li");
        
        Elements elementTagList = new Elements();
        
        for(Element tag : elementsTag) {
            if(tag.hasAttr("data-ytos-color-identifier")) {
                //System.out.println(tag);
                elementTagList.add(tag);
                //System.out.println("=============");
            }
        }
        
        for(Element color : elementTagList) {
            System.out.println(color.attr("data-ytos-color-identifier"));
        }
    }
    
    public static void extractItemColors(final String brandUrl) throws IOException {
        Objects.requireNonNull(brandUrl);

        Document doc = Jsoup.connect(brandUrl).get();
        
        Element body = doc.body();
        //System.out.println(body);
        
        Elements elements = body.getElementsByClass("HTMLListColorSelector");
        //System.out.println(doc.getElementsByTag("li"));
        Elements elementsTag = doc.getElementsByTag("li");
        
        Elements elementTagList = new Elements();
        
        for(Element tag : elementsTag) {
            if(tag.hasAttr("data-ytos-color-identifier")) {
                //System.out.println(tag);
                elementTagList.add(tag);
                //System.out.println("=============");
            }
        }
        
        for(Element color : elementTagList) {
            System.out.println(color.attr("data-ytos-color-identifier"));
        }
    }
    
    public static void extractItemPrice(final String brandUrl) throws IOException {
        Objects.requireNonNull(brandUrl);

        Document doc = Jsoup.connect(brandUrl).get();
        
        Element body = doc.body();
        //System.out.println(body);
        
        Elements elements = body.getElementsByClass("item__top__price ");
        //item__top__base-info
        System.out.println(elements.get(0).getElementsByClass("itemPrice").text());
    }
    
    public static List<String> extractItemTitle(final String brandUrl) throws IOException {
        Objects.requireNonNull(brandUrl);

        Document doc = Jsoup.connect(brandUrl).get();
        
        Element body = doc.body();
        //System.out.println(body);
        Elements elements = body.getElementsByClass("item__top__base-info");
        //item__top__base-info
        System.out.println(elements.get(0).getElementsByClass("item__top__base-info__title").text());
        
        return null;
   }
    
    public static List<MassItem> extractItemImages(final String brandUrl) throws IOException {
        Objects.requireNonNull(brandUrl);

        Document doc = Jsoup.connect(brandUrl).get();
        
        Element body = doc.body();
        System.out.println(body);
        System.out.println("--------------");
        Elements elements = body.getElementsByClass("image");
        //System.out.println(elements.get(0).getElementsByClass("pdp__showcase__product-picture__desktop__product-image__selected-shot"));
        Elements images = elements.get(0).getElementsByClass("pdp__showcase__product-picture__desktop__product-image__selected-shot");
        //System.out.println(images.get(0).attr("src").toString());
        //System.out.println(images.get(0).attr("srcset").toString());
        String imageSet = images.get(0).attr("srcset").toString();
        List<String> convertedCountriesList = Arrays.asList(imageSet.split(",", -1));
        for(String image : convertedCountriesList) {
            //System.out.println(image.split(" ")[0]);
        }
        //System.out.println(convertedCountriesList.get(0).split(" ")[0]);
        
        return null;
   }
    
   public static List<String> extractItemImagesAlternative(final String brandUrl) throws IOException {
        Objects.requireNonNull(brandUrl);

        Document doc = Jsoup.connect(brandUrl).get();
        
        Element body = doc.body();
        System.out.println(body);
        System.out.println("--------------");
        Elements elements = body.getElementsByClass("image");
        //System.out.println(elements.get(0).getElementsByClass("pdp__showcase__product-picture__desktop__product-image__selected-shot"));
        Elements images = elements.get(0).getElementsByClass("pdp__showcase__product-picture__desktop__product-image__selected-shot");
        //System.out.println(images.get(0).attr("src").toString());
        //System.out.println(images.get(0).attr("srcset").toString());
        String imageSet = images.get(0).attr("srcset").toString();
        List<String> convertedCountriesList = Arrays.asList(imageSet.split(",", -1));
        for(String image : convertedCountriesList) {
            //System.out.println(image.split(" ")[0]);
        }
        //System.out.println(convertedCountriesList.get(0).split(" ")[0]);
        
        return null;
    }
    
    /**
     * Not all items can be saved because of lazy loading
     * 
     * @param brandUrl
     * @return
     * @throws IOException
     */
    public static List<String> getItemUrlList(final String brandUrl) throws IOException {
        Objects.requireNonNull(brandUrl);
        savePage(brandUrl);
        
        File input = new File(HTML_BRAND);
        Document doc = Jsoup.parse(input, "UTF-8", "");
        //Document doc = Jsoup.connect(brandUrl).get();
        
        Element body = doc.body();
        Elements elements = body.getElementsByClass("product-images");
        
        for(Element elem : elements) {
            //Element elem = elements.get(1);
            //LOGGER.debug(elem);
            System.out.println("----");
            //LOGGER.debug(elem.getElementsByTag("a"));
            Elements productsImages = elem.getElementsByTag("a");
            System.out.println("----");
            System.out.println("Item image Link: " + productsImages.get(0).getElementsByClass("product-images__link").attr("href"));
            //System.out.println("Item title: " + productsImages.get(0).getElementsByClass("product-images__link").get(0).getElementsByClass("plpProductImage").get(0).getAllElements().get(0).getElementsByClass("itemImage frontImage loaded").attr("title"));
        }
        return null;
    }
    
    /**
     * To get all items the html page should be manually saved in local directory - FILE_MONCLER
     * 
     * @return the list of each item url
     * @throws IOException
     */
    public static List<String> getItemUrlList() throws IOException {        
        File input = new File(HTML_BRAND);
        Document doc = Jsoup.parse(input, "UTF-8", "");
        Element body = doc.body();
        Elements elements = body.getElementsByClass("product-images");
        
        List<String> itemUrls = new ArrayList<>();
        
        for(Element elem : elements) {
            //Element elem = elements.get(1);
            //LOGGER.debug(elem);
            //System.out.println("----");
            //LOGGER.debug(elem.getElementsByTag("a"));
            Elements productsImages = elem.getElementsByTag("a");
            String url = productsImages.get(0).getElementsByClass("product-images__link").attr("href");
            //System.out.println("----");
            //System.out.println("Item image Link: " + productsImages.get(0).getElementsByClass("product-images__link").attr("href"));
            //System.out.println("Item title: " + productsImages.get(0).getElementsByClass("product-images__link").get(0).getElementsByClass("plpProductImage").get(0).getAllElements().get(0).getElementsByClass("itemImage frontImage loaded").attr("title"));
            
            itemUrls.add(url);
        }
        
        return itemUrls;
    }
    
    public static String savePage(final String URL) throws IOException {
        String line = "", all = "";
        URL myUrl = null;
        BufferedReader in = null;
        FileWriter fWriter = new FileWriter(HTML_BRAND);
        try {
            myUrl = new URL(URL);
            in = new BufferedReader(new InputStreamReader(myUrl.openStream()));

            while ((line = in.readLine()) != null) {
                all += line;
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        System.out.println(all);
        fWriter.write(all);
        fWriter.close();
        
        return all;
    }
}
