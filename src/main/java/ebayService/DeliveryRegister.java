package ebayService;

import application.EbayAuctionBuyingCalculator.Shipping_Address;

/**
*
* 배송회사 등록 정보 
* 
* @author sanghuncho
*
* @since  27.06.2020
*
*/
public class DeliveryRegister {
    private String itemName;
    private String brandName;
    private String site;
    private int numberItem;
    private double onePrice;
    private String arrivalTitle;
    private Shipping_Address shippingAddress;
    
    public DeliveryRegister(String itemName, String brandName, String site,
            int numerberItem, double onePrice, String arrivalTitle, Shipping_Address shippingAddress) {
        this.itemName = itemName;
        this.brandName = brandName;
        this.site = site;
        this.numberItem = numerberItem;
        this.onePrice = onePrice;
        this.arrivalTitle = arrivalTitle;
        this.shippingAddress = shippingAddress;
    }
    
    public String convertRowExcel() {
        StringBuilder sb = new StringBuilder(itemName);  
        sb.append("\n");
        sb.append(brandName);
        sb.append("\n");
        sb.append(site);
        sb.append("\n");
        sb.append(numberItem);
        sb.append("\n");
        sb.append(onePrice);
        sb.append("\n");
        sb.append(arrivalTitle);
        sb.append("\n");
        sb.append(" ==> check excel file!");
        
        return shippingAddress == Shipping_Address.ILOG ? sb.toString() :"직접 수령";
    }

}
