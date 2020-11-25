package crawlerEntities;

import java.util.List;

public class MassItem {
    
    private String brandName;
    private String itemCategory;
    private String categoryId;
    private String itemTitle;
    private double itemPriceEuro;
    private double itemPriceWon;
    private String itemMainImage;
    private List<String> itemDetailImages;
    private List<String> itemColors;
    private List<String> itemSizes;
    
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

    public String getItemMainImage() {
        return itemMainImage;
    }

    public void setItemMainImage(String itemMainImage) {
        this.itemMainImage = itemMainImage;
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
}