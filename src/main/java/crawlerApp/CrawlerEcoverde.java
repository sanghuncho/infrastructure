package crawlerApp;

import java.io.File;
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
import crawlerBrandEntities.MassItemMoncler;
import crawlerEntities.BaseItem;
import crawlerEntities.MassItem;
import factoryExcel.SmartStore;
import util.Formatter;

public class CrawlerEcoverde {
    private static final Logger LOGGER = LogManager.getLogger(CrawlerEcoverde.class);
    public static String DIR_BRAND = "C:/Users/sanghuncho/Documents/GKoo_Store_Project/의류/moncler";
    public static String DIR_BRAND_CATEGORY = DIR_BRAND + "/men_long_jacket";
    public static String HTML_BRAND = DIR_BRAND_CATEGORY + "/moncler_men_long_jacket.html";
    
    public static final String BRAND_NAME = "비오텀";
    public static final String ITEM_CATEGORY = "아이케어";
    public static final String CATEGORY_ID = "50000837";
    
    public static void main(String[] args) throws IOException {
        //LOGGER.info("Crawler starts:" + BRAND_NAME + " " + ITEM_CATEGORY);
        /**
         * call from web and not all urls can be gathered
         */
        String categotyUrl = "https://www.ecco-verde.de/gesicht/augen?";
        List<String> itemUrlList = getItemUrlList(categotyUrl);
        
        String itemUrl = "https://www.ecco-verde.de//lavera/neutral-augencreme";
        MassItem massItem = createMassItem(itemUrl, new MassItem("", ITEM_CATEGORY, CATEGORY_ID));
    }
    
    public static MassItem createMassItem(final String itemUrl, MassItem item) {
        //pipeline
        
        //1. extractItemBrand 
        String itemBrand = "";
        try {
            itemBrand = extractItemBrand(itemUrl, item);
            //System.out.println(itemTitle);
        } 
        catch (IOException e) {
            LOGGER.error("Error extractItemBrand:" + itemUrl);
        }
        
        //2. extractItemTitle 
        String itemTitle = "";
        try {
            itemTitle = extractItemTitle(itemUrl, item);
            //System.out.println(itemTitle);
        } catch (IOException e) {
            LOGGER.error("Error extractItemTitle:" + itemUrl);
        }
        
        //3. extractItemTitle 
        double itemPriceEuro = 0;
        try {
            itemPriceEuro = extractItemPrice(itemUrl, item);
            //System.out.println(itemTitle);
        } catch (IOException e) {
            LOGGER.error("Error extractItemPrice:" + itemUrl);
        }
        
        //4. extractItemVolume 
        String itemVolume;
        try {
            itemVolume = extractItemVolume(itemUrl, item);
            //System.out.println(itemTitle);
        } catch (IOException e) {
            LOGGER.error("Error extractItemVolume:" + itemUrl);
        }
        
        //5. extractItem
        try {
            extractItemDescription(itemUrl, item);
            //System.out.println(itemTitle);
        } catch (IOException e) {
            LOGGER.error("Error extractItemDescription:" + itemUrl);
        }
        
        //5. extractItemIngredients 
        try {
            extractItemIngredients(itemUrl, item);
            //System.out.println(itemTitle);
        } catch (IOException e) {
            LOGGER.error("Error extractItemIngredients:" + itemUrl);
        }
        
//        
//        //3. downloading main image
//        try {
//            //downloadingMainImage(itemTitle, itemUrl, item);
//        } catch (IOException e) {
//            LOGGER.error("Error downloadingMainImage:" + itemUrl);
//        }
//        
//        //3. extractItemColors, baseImages
//        try {
//            //extractItemColors(itemUrl, item);
//        } catch (IOException e) {
//            LOGGER.error("Error extractItemColors:" + itemUrl);
//        }
//        
//        //4. extractDetailImages
//        //extractDetailImages(itemUrl, item);
//        
//        //4. extractItemPrice
//        try {
//            //extractItemPrice(itemUrl, item);
//        } catch (IOException e) {
//            LOGGER.error("Error extractItemPrice:" + itemUrl);
//        }
        
        //5. setitemSize
        //item.setItemSizes(ITEM_SIZE_LIST);
        
        return item;
    }
    
    public static String extractItemBrand(final String itemUrl, MassItem item) throws IOException {
        Objects.requireNonNull(itemUrl);

        Document doc = Jsoup.connect(itemUrl).get();
        
        Element body = doc.body();
        //System.out.println(body);
        
        Elements elements = body.getElementsByClass("product-page-heading");
        //System.out.println(elements.get(0).getElementsByClass("brand-name").text());
        final String brandName = elements.get(0).getElementsByClass("brand-name").text();
        String validBrandName = "";
        if (brandName == "") {
            validBrandName = "no brand";
            LOGGER.error("the brand name is not known! -  " + itemUrl);
        } else {
            validBrandName = brandName; 
        }

        item.setBrandName(brandName);
        return validBrandName;
    }
    
    public static String extractItemTitle(final String itemUrl, MassItem item) throws IOException {
        Objects.requireNonNull(itemUrl);

        Document doc = Jsoup.connect(itemUrl).get();
        
        Element body = doc.body();
        //System.out.println(body);
        
        Elements elements = body.getElementsByClass("product-page-heading");
        String toRemoveBrand = elements.get(0).getElementsByClass("brand-name").text();
        String itemTitleWithBrand = elements.get(0).getElementsByTag("h1").text();
        String itemTitle = "";
        
        if (itemTitleWithBrand.contains(toRemoveBrand)) {
            itemTitle = itemTitleWithBrand.replaceAll(toRemoveBrand, "");
        }
        
        String validItemTitle = "";
        if(itemTitle.charAt(0) == ' ') {
            validItemTitle = itemTitle.substring(1);
        } else {
            validItemTitle = itemTitle;
        }
        
        //System.out.println(validItemTitle);
        
        item.setItemTitle(validItemTitle);
        return validItemTitle;
    }
    
    public static double extractItemPrice(final String itemUrl, MassItem item) throws IOException {
        Objects.requireNonNull(itemUrl);

        Document doc = Jsoup.connect(itemUrl).get();
        
        Element body = doc.body();
        //System.out.println(body);
        
        Elements elements = body.getElementsByClass("price");
        //System.out.println(Formatter.deleteNonDigits(elements.get(0).text()));
        String price = Formatter.deleteNonDigits(elements.get(0).text());
        String validItemPrice = "";
        if(price == "0" || price == "") {
            validItemPrice = "0";
            LOGGER.error("Error price is not known:" + itemUrl);
        } else {
            validItemPrice = price;
        }
        
        //System.out.println(validItemPrice);
        double validPrice = Double.valueOf(validItemPrice);
        item.setItemPriceEuro(validPrice);
        return validPrice;
    }
    
    public static String extractItemVolume(final String itemUrl, MassItem item) throws IOException {
        Objects.requireNonNull(itemUrl);

        Document doc = Jsoup.connect(itemUrl).get();
        
        Element body = doc.body();
        //System.out.println(body);
        
        Elements elements = body.getElementsByClass("variant-content");
        //System.out.println(elements.get(0).text());
        //item.setItemPriceEuro(validPrice);
        String volume = elements.get(0).text();
        
        item.setItemVolume(volume);
        return volume;
    }
    
    public static void extractItemDescription(final String itemUrl, MassItem item) throws IOException {
        Objects.requireNonNull(itemUrl);

        Document doc = Jsoup.connect(itemUrl).get();
        
        Element body = doc.body();
        //System.out.println(body);
        
        Elements elements = body.getElementsByClass("product-description-content");
        //System.out.println(elements.get(0).getElementsByTag("p"));
        //System.out.println(elements.get(0).text());
        //item.setItemPriceEuro(validPrice);
        String orgDescription = elements.get(0).text();
        
        String description=""; 
        String origUsage="";
        if (orgDescription.contains("Anwendung:")) {
            description = Formatter.splitAfterWord(orgDescription, "Anwendung:").get(0);
            origUsage = Formatter.splitAfterWord(orgDescription, "Anwendung:").get(1);
        } else {
            description = orgDescription;
            LOGGER.warn("item usage is not found:" + itemUrl);
        }
        
        String usage = origUsage.charAt(0) == ' ' ? origUsage.substring(1) : origUsage;
        item.setItemDescription(description);
        item.setItemUsage(usage);
        System.out.println(description);
        System.out.println(usage);
    }
    
    public static String extractItemIngredients(final String itemUrl, MassItem item) throws IOException {
        Objects.requireNonNull(itemUrl);

        Document doc = Jsoup.connect(itemUrl).get();
        
        Element body = doc.body();
        //System.out.println(body);
        
        //Elements elements = body.getElementsByClass("variant-content");
        //System.out.println(elements.get(0).text());
        //item.setItemPriceEuro(validPrice);
        //String volume = elements.get(0).text();
        
        //item.setItemVolume(volume);
        return "";
    }
    
    public static List<String> getItemUrlList(final String categotyUrl) throws IOException {
        Objects.requireNonNull(categotyUrl);                
        List<String> itemUrls = new ArrayList<>();
        
        //File input = new File(HTML_BRAND);
        //Document doc = Jsoup.parse(input, "UTF-8", "");
        Document doc = Jsoup.connect(categotyUrl).get();
        
        Element body = doc.body();
        //System.out.println(body.toString());
        Elements elements = body.getElementsByClass("grid-view ga-productlist");
        
        //product link
        //System.out.println(elements.get(0).getElementsByClass("product-v2  w4of ga-product").get(0));
        //Element elementLink = elements.get(0).getElementsByClass("product-v2  w4of ga-product").get(0);
        Elements elementLinks = elements.get(0).getElementsByClass("product-v2  w4of ga-product");
        //System.out.println(elements.get(0).getElementsByClass("product-v2  w4of ga-product").get(0));
        for(Element link : elementLinks) {
            String url = "https://www.ecco-verde.de/" + link.getElementsByClass("product__title").get(0).getElementsByClass("productVariants").attr("href");
            //System.out.println("https://www.ecco-verde.de/" + link.getElementsByClass("product__title").get(0).getElementsByClass("productVariants").attr("href"));
            itemUrls.add(url);
        }
        
        return itemUrls;
    }
}
