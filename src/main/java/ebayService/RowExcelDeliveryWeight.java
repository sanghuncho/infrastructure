package ebayService;

import util.Formatter;

public class RowExcelDeliveryWeight {
    public static final int ONE_KG_PRICE = 13000;
    public static final int PRO_KG_PRICE = 4000;
    
    private int lastRemainedMoney;
    private int deliveryPrice;
    private String deliveryDate;
    private int lastSaved;
    private String trackingNumber;
    private int itemNumber;
    private int deliveryWeight;
    
    public RowExcelDeliveryWeight(String deliveryDate, int lastSaved,
            int deliveryWeight, int itemNumber, String trackingNumber) {
        this.deliveryDate = deliveryDate;
        this.lastSaved = lastSaved;
        this.trackingNumber = trackingNumber;
        this.itemNumber = itemNumber;
        this.deliveryWeight = deliveryWeight;
        this.deliveryPrice = (ONE_KG_PRICE + (deliveryWeight-1)*PRO_KG_PRICE);
        this.lastRemainedMoney = lastSaved - deliveryPrice;
    }
    
    public int getLastRemainedMoney() {
        return lastRemainedMoney;
    }
    
    public String getResult() {
        StringBuilder sb = new StringBuilder(this.deliveryDate);  
        sb.append(", ");
        
        //입금액
        sb.append(", ");
        
        //적립금, 보유금
        sb.append(Formatter.convertKoreaCurrency(lastSaved));
        sb.append(", ");
        
        //최종정산금액
        sb.append(Formatter.convertKoreaCurrency(lastRemainedMoney));
        sb.append(", ");
        
        //경매 및 구매 총금액
        sb.append(", ");
        
        //국제배송비
        sb.append(Formatter.convertKoreaCurrency(deliveryPrice));
        sb.append(", ");
        
        //아이템 가격
        sb.append(", ");
        
        //아이템+독일배송비 유로
        sb.append(", ");

        //수수료
        sb.append(", ");
        
        //빈칸
        sb.append(", ");
        
        //지쿠 아이템번호
        sb.append(itemNumber);
        sb.append(", ");
        
        //아이템 사진
        sb.append(", ");
        
        //아이템 이름
        sb.append(", ");
        
        //송장번호
        if(trackingNumber != "") {
            sb.append(trackingNumber);
        }
        sb.append(", ");
        
        //실, 부피무게
        sb.append(deliveryWeight + "kg");
        
        return sb.toString();
    }
}
