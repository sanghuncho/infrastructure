package ebayService;

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
    private boolean sendToMe;
    
    public DeliveryRegister(String itemName, String brandName, String site,
            int numerberItem, double onePrice, String arrivalTitle, boolean sendToMe) {
        this.itemName = itemName;
        this.brandName = brandName;
        this.site = site;
        this.numberItem = numerberItem;
        this.onePrice = onePrice;
        this.arrivalTitle = arrivalTitle;
        this.sendToMe = sendToMe;
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
        
        return sendToMe ? "직접 수령" : sb.toString();
    }

}
