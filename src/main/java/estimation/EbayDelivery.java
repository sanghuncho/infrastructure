package estimation;

/**
* 배송내역
* @author sanghuncho
*
* @since  27.06.2020
*
*/
public class EbayDelivery {
    private int inputItemNumber;
    private String arrivalTitle;
    private String paymentArt;
    private String sellerId;
    private String brandName;
    private String site;
    private int numberItem;
    private double onePrice;
    private String itemName;
    
    public EbayDelivery (int inputItemNumber, String arrivalTitle, String paymentArt ,String sellerId,
            String brandName, String site, int numberItem, double inputItemPriceEuro, String itemName, double onePrice) {
        this.inputItemNumber = inputItemNumber;
        this.arrivalTitle = arrivalTitle;
        this.paymentArt = paymentArt;
        this.sellerId = sellerId;
        this.brandName = brandName;
        this.site = site;
        this.numberItem = numberItem;
        this.onePrice = onePrice;
        this.itemName = itemName;
    }

    public String convertRowExcel() {
        //날짜
        StringBuilder delivery = new StringBuilder(String.valueOf(inputItemNumber));  
        delivery.append(", ");
        
        //빈칸 아이템 사진
        delivery.append(", ");
        
        //도착이름
        delivery.append(arrivalTitle);
        delivery.append(", ");
        
        //부피무게
        delivery.append(", ");
        
        //배송비
        delivery.append(", ");
        
        //결제수단
        if(paymentArt == "T") {
            delivery.append("송금");
            delivery.append(", ");
            
            delivery.append(", ");
        } else if (paymentArt == "P") {
            delivery.append("페이팔");
            delivery.append(", ");
            
            delivery.append("OK");
            delivery.append(", ");
        } else {
            delivery.append("Error Payment");
            delivery.append(", ");
        }
        
        //독일내 배송
        delivery.append(", ");
        
        //독일내 도착
        delivery.append(", ");
        
        //한국 출발
        delivery.append(", ");
        
        //셀러아이디
        delivery.append(sellerId);
        delivery.append(", ");
        
        //메모
        delivery.append(", ");
        
        //아이템 이름
        delivery.append(itemName);
        delivery.append(", ");
        
        //아이템 브랜드 이름
        delivery.append(brandName);
        delivery.append(", ");
        
        //사이트
        delivery.append(site);
        delivery.append(", ");
        
        //수량
        delivery.append(numberItem);
        delivery.append(", ");
        
        //단가
        delivery.append(onePrice);
        
        ////배송내역 프린트
        return delivery.toString();
    }
}
