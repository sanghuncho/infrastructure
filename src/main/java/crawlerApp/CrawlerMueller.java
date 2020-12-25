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
import crawlerBrandEntities.MassItemConverter;
import crawlerEntities.BaseItemCosmetic;
import crawlerEntities.MassItem;
import factoryExcel.Coopang;
import factoryExcel.SmartStore;
import util.Formatter;
import util.GrobalDefined;
import util.ImageDownloader;

public class CrawlerMueller {
    
    public static String DIR_BRAND = "C:/Users/sanghuncho/Documents/GKoo_Store_Project/화장품/mueller";
    public final static String BRAND_NAME_DE = "ANNEMARIE BÖRLIND";
    public final static String BRAND_NAME_KOR = "안네마리보린";
    public static String DIR_BRAND_CATEGORY = DIR_BRAND + "/" + BRAND_NAME_DE + "/";
    public static String DIR_MAIN_IMAGES = DIR_BRAND_CATEGORY + "main_images/";
    public static String DIR_EXCEL_FILE = DIR_BRAND_CATEGORY;
    public static String MUELER = "https://www.mueller.de";
    private static final Logger LOGGER = LogManager.getLogger();
    
    public static void main(String[] args) throws Exception {
        LOGGER.info("CrawlerMueller starts ===>>> " + BRAND_NAME_KOR);
        
        /** To create block massItem */
        createBlockMassItemReady();
    }
    
    private static void createBlockMassItemReady() throws Exception {
        List<String> itemUrlList = new ArrayList<>();
        List<MassItem> massItemList = new ArrayList<>();
        
        itemUrlList.add("https://www.mueller.de/p/annemarie-boerlind-hyaluron-augenpads-mit-sofort-effekt-2078774/");
        MassItem massItem_1 = new MassItem("히알루론 아이패드", "50000441", "6개", 0, GrobalDefined.grobalDefinedUsage.get("아이패드"));
        massItemList.add(massItem_1);
        
//        itemUrlList.add("https://www.ecco-verde.de/alva/for-him-bodylotion");
//        MassItem massItem_2 = new MassItem(BRAND_NAME, "남성용 바디로션", "50000297", "175ml", 100, null);
//        massItemList.add(massItem_2);
//        
//        itemUrlList.add("https://www.ecco-verde.de/alva/hornhaut-balsam");
//        MassItem massItem_3 = new MassItem(BRAND_NAME, "풋케어", "50000440", "30ml", 0, null);
//        massItemList.add(massItem_3);
        
        createBlockMassItem(itemUrlList, massItemList);
    }
    
    private static void createBlockMassItem(List<String> itemUrlList, List<MassItem> massItemList) throws Exception {
        for(int i=0; i<itemUrlList.size(); i++) {
            extractBlockMassItem(itemUrlList.get(i), massItemList.get(i));
            LOGGER.info("MassItem is created:" + i);
        }
        
        List<BaseItemCosmetic> baseItemCosmeticList = new ArrayList<>();
        for(MassItem massItem : massItemList) {
            MassItemConverter massItemConverter = new MassItemConverter(massItem);
            baseItemCosmeticList.add(massItemConverter);
        }
        
        SmartStore smartStore = new SmartStore(BRAND_NAME_KOR, baseItemCosmeticList);
        smartStore.createExcelBlock(CrawlerMueller.DIR_EXCEL_FILE);
        
        //Coopang API 사용등록
//        Coopang coopang = new Coopang(BRAND_NAME_KOR, baseItemCosmeticList);
//        coopang.createExcelBlockEcoverde();
        
        LOGGER.info("CrawlerEcoverde is end <<<=== "  + BRAND_NAME_KOR);
    }
    
    public static void extractBlockMassItem(final String itemUrl, MassItem item) throws Exception {

        //1. extractItemTitle 
        try {
            extractItemPrice(itemUrl, item);
            //System.out.println(itemTitle);
        } catch (IOException e) {
            LOGGER.error("Error extractItemPrice:" + itemUrl);
        }
        
        //2. extractItem
        try {
            extractItemDescription(itemUrl, item);
            //System.out.println(itemTitle);
        } catch (IOException e) {
            LOGGER.error("Error extractItemDescription:" + itemUrl);
        }
        
        //3. extractItemIngredients 
        try {
            extractItemIngredients(itemUrl, item);
            //System.out.println(itemTitle);
        } catch (IOException e) {
            LOGGER.error("Error extractItemIngredients:" + itemUrl);
        }
        
        //5. downloading main image
        try {
            downloadingMainImage(item.getItemTitle(), itemUrl, item);
        } catch (IOException e) {
            LOGGER.error("Error downloadingMainImage:" + itemUrl);
        }
        
        //6. get itemTitleDE
        item.setItemTitleDE(extractItemTitleDE(itemUrl, item));
    }
    
    public static String extractItemTitleDE(final String itemUrl, MassItem item) throws IOException {
        Objects.requireNonNull(itemUrl);

        Document doc = Jsoup.connect(itemUrl).get();
        
        Element body = doc.body();
        //System.out.println(body);
        Elements elements = body.getElementsByClass("mu-sale-box__headline");
        System.out.println(elements.get(0).text());
        String validItemTitle = elements.get(0).text();
        if(validItemTitle.length() > 30) {
            LOGGER.warn("Edit the lenght under 30: " + validItemTitle);
        }
        return validItemTitle;
    }
    
    public static void downloadingMainImage(final String itemTitle, final String itemUrl, MassItem item) throws IOException {
        Objects.requireNonNull(itemUrl);

        Document doc = Jsoup.connect(itemUrl).get();
        
        Element body = doc.body();
        
        Elements elements = body.getElementsByClass("mu-product-gallery__image");
        System.out.println(elements.get(0).attr("src"));
        //System.out.println(itemBrand + " " + itemTitle);
        //System.out.println(elements.get(0).attr("href"));
        
        /**  save the main image process  */ 
        
        String mainImageUrl = elements.get(0).attr("src");
        item.setMainImageName(itemTitle);
        savingMainImage(itemTitle, DIR_MAIN_IMAGES, mainImageUrl);
    }
    
    private static void savingMainImage(final String imageName, String directory, final String imageUrl) {
        ImageDownloader.runWithResizing(imageName, directory, imageUrl, 500, 500);
    }
    
    public static void extractItemIngredients(final String itemUrl, MassItem item) throws IOException {
        Objects.requireNonNull(itemUrl);
        Document doc = Jsoup.connect(itemUrl).get();
        Element body = doc.body();
        //System.out.println(body);
        
        Elements elements = body.getElementsByClass("mu-table__body");
        String rawIngredients = elements.get(0).getElementsByClass("mu-table__row mu-table__row--inverted").get(2).getElementsByClass("mu-table__cell").text();
        String ingredients = Formatter.splitAfterWord(rawIngredients, "Inhaltsstoffe ").get(1);
        //System.out.println(Formatter.splitAfterWord(rawIngredients, "Inhaltsstoffe ").get(1));
        item.setItemIngredients(ingredients);
    }
    
    public static void extractItemPrice(final String itemUrl, MassItem item) throws IOException {
        Objects.requireNonNull(itemUrl);

        Document doc = Jsoup.connect(itemUrl).get();
        
        Element body = doc.body();
        //System.out.println(body);
        
        Elements elements = body.getElementsByClass("mu-flex-container");
        //System.out.println(elements.get(0).getElementsByClass("mu-price-box__price mu-price-box__price--big ").text());
        
        String validItemPrice = "";
        String price = Formatter.deleteNonDigits(elements.get(0).getElementsByClass("mu-price-box__price mu-price-box__price--big ").text());
        System.out.println(price);
        if(price == "0" || price == "") {
            validItemPrice = "0";
            LOGGER.error("Error price is not known:" + itemUrl);
        } else {
            validItemPrice = price;
        }
        
        double validPrice = Double.valueOf(validItemPrice);
        item.setItemPriceEuro(validPrice);
    }
    
    public static void extractItemDescription(final String itemUrl, MassItem item) throws Exception {
        Objects.requireNonNull(itemUrl);
        Document doc = Jsoup.connect(itemUrl).get();
        Element body = doc.body();
        //System.out.println(body);
        
        Elements elements = body.getElementsByClass("mu-product-description__text");
        //System.out.println(elements.get(0).getElementsByTag("p"));
        //System.out.println(elements.get(0).text());
        //item.setItemPriceEuro(validPrice);
        String orgDescription = elements.get(0).text();
        //System.out.println(orgDescription);
        item.setItemDescription(orgDescription);
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
