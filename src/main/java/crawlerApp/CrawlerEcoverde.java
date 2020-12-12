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
import crawlerBrandEntities.MassItemEcoverde;
import crawlerEntities.BaseItemCosmetic;
import crawlerEntities.MassItem;
import factoryExcel.SmartStore;
import util.Formatter;
import util.GrobalDefined;
import util.ImageDownloader;

public class CrawlerEcoverde {
    private static final Logger LOGGER = LogManager.getLogger(CrawlerEcoverde.class);
    public static String DIR_BRAND = "C:/Users/sanghuncho/Documents/GKoo_Store_Project/화장품/ecoverde";
    public static String DIR_BRAND_CATEGORY = DIR_BRAND + "/eyecare/";
    public static String DIR_MAIN_IMAGES = DIR_BRAND_CATEGORY + "main_images/";
    public static String DIR_POI_FILE = DIR_BRAND_CATEGORY;

    public static final String ITEM_CATEGORY = "아이케어";
    //public static final String CATEGORY_ID = "50000441";
    public static final String CATEGORY_ID = "[56168] 뷰티>스킨>크림>아이크림";
    
    public static void main(String[] args) throws Exception {
        LOGGER.info("CrawlerEcoverde starts ===>>> " + ITEM_CATEGORY);
        
        /** crawler */
        createAllMassItem();
        
        /** To test create one massItem */
        //createEachMassItem();
    }
    
    private static void createAllMassItem() throws Exception {
        String categotyUrl = "https://www.ecco-verde.de/gesicht/augen?";
        List<String> itemUrlList = getItemUrlList(categotyUrl);
        
        List<MassItem> massItemList = new ArrayList<>();
        int number = 1;
        for(String itemUrl : itemUrlList) {
            MassItem massItem = createMassItem(itemUrl, new MassItem("", ITEM_CATEGORY, CATEGORY_ID));
            massItemList.add(massItem);
            LOGGER.info("MassItem is created:" + number);
            //number++;
            break;
        }
        
        List<BaseItemCosmetic> baseItemCosmeticList = new ArrayList<>();
        for(MassItem massItem : massItemList) {
            MassItemEcoverde massItemEcoverde = new MassItemEcoverde(massItem);
            baseItemCosmeticList.add(massItemEcoverde);
        }
        
        SmartStore smartStore = new SmartStore(CATEGORY_ID, ITEM_CATEGORY, null, DIR_POI_FILE, baseItemCosmeticList);
        smartStore.createExcel();
        
        LOGGER.info("CrawlerEcoverde is end <<<=== "  + ITEM_CATEGORY);
    }
    
    private static void createEachMassItem() throws Exception {
        String categotyUrl = "https://www.ecco-verde.de/gesicht/augen?";
        List<String> itemUrlList = getItemUrlList(categotyUrl);
        
        String itemUrl = "https://www.ecco-verde.de//lavera/neutral-augencreme";
        MassItem massItem = createMassItem(itemUrl, new MassItem("", ITEM_CATEGORY, CATEGORY_ID));
    }
    
    public static MassItem createMassItem(final String itemUrl, MassItem item) throws Exception {
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
        
        //6. downloading main image
        try {
            downloadingMainImage(itemBrand, itemTitle, itemUrl, item);
        } catch (IOException e) {
            LOGGER.error("Error downloadingMainImage:" + itemUrl);
        }
        return item;
    }
    
    public static void downloadingMainImage(final String itemBrand, final String itemTitle, final String itemUrl, MassItem item) throws IOException {
        Objects.requireNonNull(itemUrl);

        Document doc = Jsoup.connect(itemUrl).get();
        
        Element body = doc.body();
        
        Elements elements = body.getElementsByClass("bigslider__img");
        //System.out.println(itemBrand + " " + itemTitle);
        //System.out.println(elements.get(0).attr("href"));
        
        /**  save the main image process  */ 
        
        String mainImageUrl = elements.get(0).attr("href");
        String itemFullName = itemBrand + " " + itemTitle;
        item.setMainImageName(itemFullName);
        savingMainImage(itemFullName, DIR_MAIN_IMAGES, mainImageUrl);
    }
    
    private static void savingMainImage(final String imageName, String directory, final String imageUrl) {
        ImageDownloader.runWithResizing(imageName, directory, imageUrl, 500, 500);
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
        String volume = null;
        if(elements.size() == 0) {
            volume = "";
            LOGGER.warn("Volume of item is not known:" + itemUrl);
        } else {
            String rawVolume = elements.get(0).text();
            volume = Formatter.splitAfterWord(rawVolume, "Inhalt:").get(1);
        }
        
        item.setItemVolume(volume);
        return volume;
    }
    
    public static void extractItemDescription(final String itemUrl, MassItem item) throws Exception {
        Objects.requireNonNull(itemUrl);

        Document doc = Jsoup.connect(itemUrl).get();
        
        Element body = doc.body();
        //System.out.println(body);
        
        Elements elements = body.getElementsByClass("product-description-content");
        //System.out.println(elements.get(0).getElementsByTag("p"));
        //System.out.println(elements.get(0).text());
        //item.setItemPriceEuro(validPrice);
        String orgDescription = elements.get(0).text();
        
        String description=null; 
        String origUsage="";
        if (orgDescription.contains("Anwendung:")) {
            description = Formatter.splitAfterWord(orgDescription, "Anwendung:").get(0);
            origUsage = Formatter.splitAfterWord(orgDescription, "Anwendung:").get(1);
            String usage = origUsage.charAt(0) == ' ' ? origUsage.substring(1) : origUsage;
            item.setItemUsage(usage);
        } else {
            description = orgDescription;
            String grobalUsage = GrobalDefined.grobalDefinedUsage.get(item.getItemCategory());
            if (grobalUsage == null) {
                throw new Exception("Grobal usage is not defined:" + item.getItemCategory());
            }
            item.setItemUsage(grobalUsage);
            item.setGrobalUsage(true);
            LOGGER.warn("Usage of item is not found, therefore grobal usage has used:" + itemUrl);
        }
        
        item.setItemDescription(description);
        //System.out.println(description);
        //System.out.println(usage);
    }
    
    public static String extractItemIngredients(final String itemUrl, MassItem item) throws IOException {
        Objects.requireNonNull(itemUrl);
        Document doc = Jsoup.connect(itemUrl).get();
        Element body = doc.body();
        //System.out.println(body);
        
        Elements elements = body.getElementsByClass("product-ingredients inci");
        //System.out.println(elements.get(0).text());
        String ingredients = elements.get(0).text();
        item.setItemIngredients(ingredients);
        return ingredients;
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
            String url = "https://www.ecco-verde.de" + link.getElementsByClass("product__title").get(0).getElementsByClass("productVariants").attr("href");
            //System.out.println("https://www.ecco-verde.de/" + link.getElementsByClass("product__title").get(0).getElementsByClass("productVariants").attr("href"));
            itemUrls.add(url);
        }
        
        return itemUrls;
    }
}
