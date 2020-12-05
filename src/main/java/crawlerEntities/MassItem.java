package crawlerEntities;

import java.util.List;
import crawlerApp.CrawlerMoncler.Gender;

public class MassItem {
    
    private String brandName;
    private String itemCategory;
    private String categoryId;
    private String itemTitle;
    private Gender gender;
    private double itemPriceEuro;
    private double itemPriceWon;
    private String mainImageName;
    private List<String> itemDetailImages;
    private List<String> baseImages;
    private List<String> itemColors;
    private List<String> itemSizes;
    private String itemVolume;
    private String itemDescription;
    private String itemUsage;
    private String itemIngredients;
    
    public MassItem(String brandname, String itemcategory, String categoryid, Gender gender) {
        this.brandName = brandname;
        this.itemCategory = itemcategory;
        this.categoryId = categoryid;
        this.gender = gender;
    }
    
    public MassItem(String brandname, String itemcategory, String categoryid) {
        this.brandName = brandname;
        this.itemCategory = itemcategory;
        this.categoryId = categoryid;
    }
    
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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

    //<p style="text-align:center;"><img style="padding-bottom: 20px;" src="https://cdn.yoox.biz/41/41982122le_13_f.jpg" />, 
    //<img src="https://cdn.yoox.biz/41/41982122le_13_r.jpg"/></p>
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
}