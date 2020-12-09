package ebayApi;

import util.Formatter;

public class EbayBaseData {
    private String sellerId;
    private String itemName;
    private String brandName;
    private String itemPrice;
    private String shippingServiceCost;
    
    public EbayBaseData(String itemName, String brandName, String itemPrice, String sellerId) {
        this.itemName = itemName;
        this.brandName = brandName;
        this.itemPrice = itemPrice;
        this.sellerId = sellerId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getItemName() {
        return Formatter.formatWithoutComma(itemName);
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getShippingServiceCost() {
        return shippingServiceCost;
    }

    public void setShippingServiceCost(String shippingServiceCost) {
        this.shippingServiceCost = shippingServiceCost;
    }
    
    public double getItemTotalPrice() {
        double priceDb = Double.valueOf(itemPrice);
        double shippingPriceDb = Double.valueOf(shippingServiceCost);
        
        return priceDb + shippingPriceDb;
    }
}
