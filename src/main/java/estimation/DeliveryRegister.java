package estimation;

public class DeliveryRegister {
    private String itemName;
    private String brandName;
    private String site;
    private int numberItem;
    private double onePrice;
    private String arrivalTitle;
    
    public DeliveryRegister(String itemName, String brandName, String site,
            int numerberItem, double onePrice, String arrivalTitle) {
        this.itemName = itemName;
        this.brandName = brandName;
        this.site = site;
        this.numberItem = numerberItem;
        this.onePrice = onePrice;
        this.arrivalTitle = arrivalTitle;
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
        
        return sb.toString();
    }

}
