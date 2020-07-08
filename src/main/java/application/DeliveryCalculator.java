package application;

import java.io.IOException;
import util.Formatter;

/**
* 배송후 배송비 정산
* 
* @author sanghuncho
*
* @since  27.06.2020
*
*/
public class DeliveryCalculator {
    public static final int ONE_KG_PRICE = 13000;
    public static final int PRO_KG_PRICE = 4000;
    public static void main( String[] args ) throws IOException {
        //TODO : 합포장시 3000원추가 
        //배송 날짜
        var paramDate = "06.07.2020";
        
        //적립금
        var pramLastSaved = 674200;
        
        //지쿠 아이템번호0
        var paramItemNumber = 139;
        
        //송장번호
        var paramTrackingNumber = "";
        
        //배송무게
        var paramDeliveryWeight = 6;
        
        System.out.println("## 배송비 정산내역");
        System.out.println(convertRowExcelDeliveryWeight(paramDate, pramLastSaved, paramDeliveryWeight,
                paramItemNumber, paramTrackingNumber));
    }
    
    public static String convertRowExcelDeliveryWeight(String deliveryDate, int lastSaved,
            int deliveryWeight, int itemNumber, String trackingNumber) {
        int deliveryPrice = (ONE_KG_PRICE + (deliveryWeight-1)*PRO_KG_PRICE);
        int lastRemainedMoney = lastSaved - deliveryPrice;
        //날짜
        StringBuilder sb = new StringBuilder(deliveryDate);  
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
