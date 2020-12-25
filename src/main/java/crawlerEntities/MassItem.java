package crawlerEntities;

import java.util.List;
import crawlerApp.CrawlerEcoverde;
import crawlerApp.CrawlerMoncler.Gender;
import util.GrobalDefined;

public class MassItem {
    
    private String brandNameDE;
    private String brandNameKor;
    private String itemCategory;
    private String categoryId;
    private String itemTitle;
    private Gender gender;
    private double itemPriceEuro;
    private double itemPriceWon;
    private String mainImageName;
    private List<String> itemDetailImages;
    private List<String> baseImages;
    //clothes proeprties
    private List<String> itemColors;
    private List<String> itemSizes;
    //cosmetic proeprties
    private String itemVolume;
    private String itemDescription;
    private String itemUsage;
    private boolean grobalUsage;
    private String itemIngredients;
    private int extraDeliveryFee;
    private String itemTitleDE;

    /**
     * Clothes
     * 
     * @param brandname
     * @param itemcategory
     * @param categoryid
     * @param gender
     */
    public MassItem(String brandname, String itemcategory, String categoryid, Gender gender) {
        this.brandNameDE = brandname;
        this.itemCategory = itemcategory;
        this.categoryId = categoryid;
        this.gender = gender;
    }
    
    public MassItem(String itemTitle, String categoryid, String itemVolume, int extraDeliveryFee, String usage) {
        this.brandNameDE =  CrawlerEcoverde.BRAND_NAME_DE;
        this.itemTitle = CrawlerEcoverde.BRAND_NAME_KOR + " " + itemTitle;
        this.categoryId = categoryid;
        this.itemVolume = itemVolume;
        //this.itemCategory = GrobalDefined.categoryUsage.get(categoryid).getCategory();
        this.extraDeliveryFee = extraDeliveryFee;
        this.itemUsage = usage == null ? GrobalDefined.categoryUsage.get(categoryid).getUsage() : usage;
    }
    
    public MassItem(String itemcategory) {
        this.itemCategory = itemcategory;
        this.grobalUsage = false; //default
    }
    
    public String getBrandNameDE() {
        return brandNameDE;
    }

    public void setBrandNameDE(String brandNameDE) {
        this.brandNameDE = brandNameDE;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public double getItemPriceEuro() {
        return itemPriceEuro;
    }

    public void setItemPriceEuro(double itemPriceEuro) {
        this.itemPriceEuro = itemPriceEuro;
    }

    public double getItemPriceWon() {
        return itemPriceWon;
    }

    public void setItemPriceWon(double itemPriceWon) {
        this.itemPriceWon = itemPriceWon;
    }

    public List<String> getItemDetailImages() {
        return itemDetailImages;
    }

    public void setItemDetailImages(List<String> itemDetailImages) {
        this.itemDetailImages = itemDetailImages;
    }

    public List<String> getItemColors() {
        return itemColors;
    }

    public void setItemColors(List<String> itemColors) {
        this.itemColors = itemColors;
    }

    public List<String> getItemSizes() {
        return itemSizes;
    }

    public void setItemSizes(List<String> itemSizes) {
        this.itemSizes = itemSizes;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getMainImageName() {
        return mainImageName;
    }

    public void setMainImageName(String mainImageName) {
        this.mainImageName = mainImageName;
    }
    
    public String getMainImageFileName() {
        return mainImageName + ".jpg";
    }
    
    public String toString() {
        StringBuilder str = new StringBuilder(); 
        str.append(""); 
        
        return str.toString();
    }

    public List<String> getBaseImages() {
        return baseImages;
    }

    public void setBaseImages(List<String> baseImages) {
        this.baseImages = baseImages;
    }

    public Gender getGender() {
        return gender;
    }

    public String getItemVolume() {
        return itemVolume;
    }

    public void setItemVolume(String itemVolume) {
        this.itemVolume = itemVolume;
    }

    
    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemUsage() {
        return itemUsage;
    }

    public void setItemUsage(String itemUsage) {
        this.itemUsage = itemUsage;
    }

    public String getItemIngredients() {
        return itemIngredients;
    }

    public void setItemIngredients(String itemIngredients) {
        this.itemIngredients = itemIngredients;
    }

    public boolean isGrobalUsage() {
        return grobalUsage;
    }

    public void setGrobalUsage(boolean grobalUsage) {
        this.grobalUsage = grobalUsage;
    }

    public int getExtraDeliveryFee() {
        return extraDeliveryFee;
    }

    public void setExtraDeliveryFee(int extraDeliveryFess) {
        this.extraDeliveryFee = extraDeliveryFee;
    }

    public String getItemTitleDE() {
        return itemTitleDE;
    }

    public void setItemTitleDE(String itemTitleDE) {
        this.itemTitleDE = itemTitleDE;
    }

    public String getBrandNameKor() {
        return brandNameKor;
    }

    public void setBrandNameKor(String brandNameKor) {
        this.brandNameKor = brandNameKor;
    }
}