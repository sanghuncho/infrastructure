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
import crawlerBrandEntities.MassItemMoncler;
import crawlerEntities.BaseItem;
import crawlerEntities.MassItem;
import factoryExcel.SmartStore;
import util.Formatter;
import util.ImageDownloader;

public class CrawlerMoncler {
    private static final Logger LOGGER = LogManager.getLogger(CrawlerMoncler.class);
    //save images and create csv
    public static String DIR_BRAND = "C:/Users/sanghuncho/Documents/GKoo_Store_Project/의류/moncler";
    public static String DIR_BRAND_CATEGORY = DIR_BRAND + "/men_long_jacket";
    public static String HTML_BRAND = DIR_BRAND_CATEGORY + "/moncler_men_long_jacket.html";
    public static String DIR_MAIN_IMAGES = DIR_BRAND_CATEGORY + "/main_images/";
    public static String DIR_CSV_FILE = DIR_BRAND_CATEGORY;
    public enum Gender { MALE, FEMALE, KIDS }
    
    //data for csv
    public static final String BRAND_NAME = "몽클레어";
    public static final String ITEM_CATEGORY = "남성 롱패딩";
    public static final String CATEGORY_ID = "50000837";
    public static Gender CATEGORY_GENDER = Gender.MALE;
    private static final String [] ITEM_SIZE = {"00", "0", "1", "2", "3", "4", "5", "6"};
    
    public static final String ITEM_TITLE_PREFIX = BRAND_NAME + " " + ITEM_CATEGORY;
    public static final List<String> ITEM_SIZE_LIST = Arrays.asList(ITEM_SIZE);
    public static List<String> itemSameTitleTester = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        LOGGER.info("Crawler starts:" + BRAND_NAME + " " + ITEM_CATEGORY);
        /**
         * call from web and not all urls can be gathered
         */
//        String categotyUrl = "https://store.moncler.com/de-de/damen/autumn-winter/kurze-daunenjacken";
//        List<String> itemUrlList = getItemUrlList(categotyUrl);
        
        /**
         * save manually html in local and gthering urls
         */
        List<String> itemUrlList = getItemUrlList();
        
        //String itemUrl = "https://store.moncler.com/de-de/jacken_cod17476499599651498.html#dept=EU_Blazers_Men_AW";
                
        List<MassItem> massItemList = new ArrayList<>();
        int number = 1;
        for(String itemUrl : itemUrlList) {
            MassItem massItem = createMassItem(itemUrl, new MassItem(BRAND_NAME, ITEM_CATEGORY, CATEGORY_ID, CATEGORY_GENDER));
            massItemList.add(massItem);
            LOGGER.info("MassItem is created:" + number);
            number++;
        }
        
        List<BaseItem> baseItemList = new ArrayList<>();
        for(MassItem massItem : massItemList) {
            MassItemMoncler massItemMoncler = new MassItemMoncler(massItem);
            baseItemList.add(massItemMoncler);
        }
        
        //System.out.println(massItemMoncler.getItemImageLinkList());
        //SmartStore smartStore = new SmartStore(CATEGORY_ID, ITEM_TITLE_PREFIX, baseItemList, DIR_CSV_FILE, null);
        //smartStore.createCSV();
        //create csv file https://www.baeldung.com/apache-commons-csv
        LOGGER.info("Crawling is end:" + BRAND_NAME + " " + ITEM_CATEGORY);
    }
    
    public static MassItem createMassItem(final String itemUrl, MassItem item) {
        //pipeline
        
        //1. extractItemTitle 
        String itemTitle = "";
        try {
            itemTitle = extractItemTitle(itemUrl, item);
            //System.out.println(itemTitle);
        } catch (IOException e) {
            LOGGER.error("Error extractItemTitle:" + itemUrl);
        }
        
        //2. downloading main image
        try {
            downloadingMainImage(itemTitle, itemUrl, item);
        } catch (IOException e) {
            LOGGER.error("Error downloadingMainImage:" + itemUrl);
        }
        
        //3. extractItemColors, baseImages
        try {
            extractItemColors(itemUrl, item);
        } catch (IOException e) {
            LOGGER.error("Error extractItemColors:" + itemUrl);
        }
        
        //4. extractDetailImages
        extractDetailImages(itemUrl, item);
        
        //4. extractItemPrice
        try {
            extractItemPrice(itemUrl, item);
        } catch (IOException e) {
            LOGGER.error("Error extractItemPrice:" + itemUrl);
        }
        
        //5. setitemSize
        item.setItemSizes(ITEM_SIZE_LIST);
        
        return item;
    }
    
    public static void extractItemColors(final String itemUrl, MassItem item) throws IOException {
        Objects.requireNonNull(itemUrl);

        Document doc = Jsoup.connect(itemUrl).get();
        
        Element body = doc.body();
        //System.out.println(body);
        
        //Elements elements = body.getElementsByClass("HTMLListColorSelector");
        //System.out.println(doc.getElementsByTag("li"));
        
        Elements elementsTag = doc.getElementsByTag("li");
        
        Elements elementTagList = new Elements();
        List<String> colorsList = new ArrayList<>();
        List<String> baseImageList = new ArrayList<>();
        
        for(Element tag : elementsTag) {
            if(tag.hasAttr("data-ytos-color-identifier")) {
                //System.out.println(tag);
                elementTagList.add(tag);
            }
        }
        
        for(Element color : elementTagList) {
            colorsList.add(color.attr("data-ytos-color-identifier"));
            //System.out.println(color.attr("data-ytos-color-identifier"));
            baseImageList.add(color.getElementsByTag("img").attr("src"));
            //System.out.println(color.getElementsByTag("img"));
        }
        
        item.setItemColors(colorsList);
        item.setBaseImages(baseImageList);
    }
    
    public static void extractItemPrice(final String itemUrl, MassItem item) throws IOException {
        Objects.requireNonNull(itemUrl);

        Document doc = Jsoup.connect(itemUrl).get();
        
        Element body = doc.body();
        //System.out.println(body);
        
        Elements elements = body.getElementsByClass("item__top__price ");
        String priceEuro = "";
        if (elements == null || elements.size() == 0) {
            priceEuro = "0";
            LOGGER.error("the price is not known! - " + item.getItemTitle() + ":" + itemUrl);
        } else {
            priceEuro = elements.get(0).getElementsByClass("itemPrice").text();
        }
            
        //System.out.println(elements.get(0).getElementsByClass("itemPrice").text());
        
        //formatted price
        
        item.setItemPriceEuro(Double.valueOf(Formatter.deleteNonDigits(priceEuro)));
    }
    
    public static void extractDetailImages(final String itemUrl, MassItem item) {
        Objects.requireNonNull(itemUrl);

        Document doc = null;
        try {
            doc = Jsoup.connect(itemUrl).get();
        } catch (IOException e) {
            LOGGER.error("Error connection jsoup:" + itemUrl);
        }
        
        Element body = doc.body();
        //System.out.println(body);
        // f r e d b 
        Elements elements = body.getElementsByClass("image");
        Elements images = elements.get(0).getElementsByClass("pdp__showcase__product-picture__desktop__product-image__selected-shot");
        
        //String imageSet = images.get(0).attr("srcset").toString();
        String imageSet = images.get(0).attr("src").toString();
        List<String> imageList = Arrays.asList(imageSet.split(",", -1));
        
        List<String> formattedImageList = new ArrayList<>();
                
        //for(String image : imageList) {
        for(String image : item.getBaseImages()) {
            //System.out.println(image.split(" ")[0]);
            String frontImage = image.split(" ")[0];
            String baseImage = frontImage.split("_1")[0];
            
            formattedImageList.add(baseImage+"_13_f");
            formattedImageList.add(baseImage+"_13_r");
            formattedImageList.add(baseImage+"_13_d");
            formattedImageList.add(baseImage+"_13_a");
            formattedImageList.add(baseImage+"_13_b");
            formattedImageList.add(baseImage+"_13_e");
        }
        
        item.setItemDetailImages(formattedImageList);
    }
    
    public static void downloadingMainImage(final String itemTitle, final String itemUrl, MassItem item) throws IOException {
        Objects.requireNonNull(itemUrl);

        Document doc = Jsoup.connect(itemUrl).get();
        
        Element body = doc.body();
        //System.out.println(body);
        
        Elements elements = body.getElementsByClass("image");
        
        /**  save the main image process  */ 
        Elements images = elements.get(0).getElementsByClass("pdp__showcase__product-picture__desktop__product-image__selected-shot");
        //System.out.println(elements.get(0).getElementsByClass("pdp__showcase__product-picture__desktop__product-image__selected-shot"));
        String mainImageUrl = images.get(0).attr("src").toString();
        savingMainImage(itemTitle, DIR_MAIN_IMAGES, mainImageUrl);
        
        item.setMainImageName(itemTitle);
    }
    
    /**
     * saving the main image in directory -> gathering the detail images as link
     * 
     * @param itemUrl
     * @param item
     * @return
     * @throws IOException
     */
    public static List<MassItem> extractItemImages(final String itemUrl, MassItem item) throws IOException {
        Objects.requireNonNull(itemUrl);

        Document doc = Jsoup.connect(itemUrl).get();
        
        Element body = doc.body();
        System.out.println(body);
        
        Elements elements = body.getElementsByClass("image");
         
        Elements images = elements.get(0).getElementsByClass("pdp__showcase__product-picture__desktop__product-image__selected-shot");
        //System.out.println(elements.get(0).getElementsByClass("pdp__showcase__product-picture__desktop__product-image__selected-shot"));
        String mainImageUrl = images.get(0).attr("src").toString();
        
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
    
    public static String extractItemTitle(final String itemUrl, MassItem item) throws IOException {
        Objects.requireNonNull(itemUrl);

        Document doc = Jsoup.connect(itemUrl).get();
        
        Element body = doc.body();
        //System.out.println(body);
        
        Elements elements = body.getElementsByClass("item__top__base-info");
        String itemTitle = elements.get(0).getElementsByClass("item__top__base-info__title").text();
        //System.out.println(elements.get(0).getElementsByClass("item__top__base-info__title").text());
        
        String validTitle = getValidItemTitle(itemTitle);
        item.setItemTitle(validTitle);
        return validTitle;
    }
    
    private static String getValidItemTitle(String itemTitle) {
        Objects.requireNonNull(itemTitle);
        
        String validTitle = "";
        //List<String> matchedItems = itemSameTitleTester.stream().filter(title -> title.contains(itemTitle)).collect(Collectors.toList());;
        List<String> matchedItems = new ArrayList<>();
        for(String title : itemSameTitleTester) {
            if(title.contains(itemTitle)) {
                matchedItems.add(title);
            }
        }
        int matchedItemSize = matchedItems.size();
        if(matchedItemSize>0) {
            validTitle = itemTitle + "_" + String.valueOf(matchedItemSize+1);
        } else {
            validTitle = itemTitle;
        }
        
        itemSameTitleTester.add(validTitle);
        return validTitle;
    }
    
    public static void savingMainImage(final String imageName, String directory, final String imageUrl) {
        ImageDownloader.run(imageName, directory, imageUrl);
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
        List<String> itemUrls = new ArrayList<>();
        File input = new File(HTML_BRAND);
        Document doc = Jsoup.parse(input, "UTF-8", "");
        Element body = doc.body();
        Elements elements = body.getElementsByClass("product-images");
        
        
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
