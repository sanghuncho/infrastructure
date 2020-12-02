package application;

import java.io.IOException;
import java.util.ArrayList;
import ebayService.RowExcelDeliveryWeight;
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
    private static String DeliveryCompany = 
            "http://www.ilogexpress.com/login";
    
    public static void main( String[] args ) throws IOException {
        //TODO : 합포장시 3000원추가 
        
        //배송 날짜
        var paramDate = "2020.11.29";
        
        //적립금
        var pramLastSaved = 294500;
        
        ////////배송상품 1
        String title_one = "Ch38";
        VolumeWeight volumeWeight_one = new VolumeWeight(32, 32, 18);
        double realWeight_one = 2.10;
        double deliveryPrice_one = 12.0;
        System.out.println(title_one + " = " + getDeliveryWeight(volumeWeight_one, realWeight_one, deliveryPrice_one));
        
        //지쿠 아이템번호
        var paramItemNumber = 192;
        //송장번호
        var paramTrackingNumber = "";
        //배송무게
        var paramDeliveryWeight = 4;
        ////////배송상품 1
        
        //--------------------------------------
        
        ////////배송상품 2
        String title_two = "Marbrin";
        VolumeWeight volumeWeight_two = new VolumeWeight(40, 30, 25);
        double realWeight_two = 2.60;
        double deliveryPrice_two = 15.60;
        System.out.println(title_two + " = " + getDeliveryWeight(volumeWeight_two, realWeight_two, deliveryPrice_two));
        
        //지쿠 아이템번호
        var paramItemNumber_se = 194;
        //송장번호
        var paramTrackingNumber_se = "";
        //배송무게
        var paramDeliveryWeight_se = 5;
        ////////배송상품 2
        
        //--------------------------------------
        
        ////////배송상품 3
        String title_three = "Marbin";
        VolumeWeight volumeWeight_three = new VolumeWeight(19, 19, 17);
        double realWeight_three = 1.00;
        double deliveryPrice_three = 6.00;
        System.out.println(title_three + " = " + getDeliveryWeight(volumeWeight_three, realWeight_three, deliveryPrice_three));
        
        //지쿠 아이템번호
        var paramItemNumber_thr = 195;
        //송장번호
        var paramTrackingNumber_thr = "";
        //배송무게
        var paramDeliveryWeight_thr = 1;
        ////////배송상품 3
       
        
        TransferMoney money1 = new TransferMoney(title_one, deliveryPrice_one);
        TransferMoney money2 = new TransferMoney(title_two, deliveryPrice_two);
        TransferMoney money3 = new TransferMoney(title_three, deliveryPrice_three);
      
        ArrayList<TransferMoney> transferMoneyList = new ArrayList<>();
        transferMoneyList.add(money1);
        transferMoneyList.add(money2);
        transferMoneyList.add(money3);
      
        RowExcelDeliveryWeight rowExcelDeliveryWeight = new RowExcelDeliveryWeight(paramDate, pramLastSaved, paramDeliveryWeight,
                paramItemNumber, paramTrackingNumber);
        RowExcelDeliveryWeight rowExcelDeliveryWeight_se = new RowExcelDeliveryWeight(paramDate, rowExcelDeliveryWeight.getLastRemainedMoney(), paramDeliveryWeight_se,
                paramItemNumber_se, paramTrackingNumber_se);
        RowExcelDeliveryWeight rowExcelDeliveryWeight_thr = new RowExcelDeliveryWeight(paramDate, rowExcelDeliveryWeight_se.getLastRemainedMoney(), paramDeliveryWeight_thr,
                paramItemNumber_thr, paramTrackingNumber_thr);
        
        System.out.println("## 배송비 정산내역");
        System.out.println(rowExcelDeliveryWeight.getResult());
        System.out.println(rowExcelDeliveryWeight_se.getResult());
        System.out.println(rowExcelDeliveryWeight_thr.getResult());
        
        System.out.println("\n");
        
        System.out.println(deliveryMoneyTransfer(transferMoneyList));
        
        System.out.println("\n");
        System.out.println("item number : " + paramItemNumber + ", " + paramItemNumber_se + ", " + paramItemNumber_thr);
        System.out.println("배송비 송금 " + paramDate);
    }
    
    public static String getDeliveryWeight(VolumeWeight volumeWeight, double realWeight, double transferBankValue) {
        double vWeight = volumeWeight.getVolumeWeight();
        double basisPrice = transferBankValue - 6;
        double firmaWeight = basisPrice == 0 ? 1 : 1 + (basisPrice/2.4);
        
        StringBuilder sb = new StringBuilder("부피무게: ");  
        sb.append(String.valueOf(vWeight));
        sb.append(", ");
        
        sb.append("실무게: ");
        sb.append(String.valueOf(realWeight));
        sb.append(", ");
        
        sb.append("배송료무게: ");
        sb.append(String.valueOf(firmaWeight));
        sb.append("\n");
        return sb.toString();
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
    
    public static class VolumeWeight {
        private double height;
        private double length;
        private double width;
        
        public VolumeWeight(double height, double length, double width) {
            this.setHeight(height); 
            this.setLength(length);
            this.setWidth(width);
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }

        public double getLength() {
            return length;
        }

        public void setLength(double length) {
            this.length = length;
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(double width) {
            this.width = width;
        }
        
        public double getVolumeWeight() {
            return (height*width*length)/6000;
        }
    }
    
    public static String deliveryMoneyTransfer(ArrayList<TransferMoney> transferMoneyList) {
        double sumMoney = 0;
        for (TransferMoney money : transferMoneyList) {
            sumMoney = sumMoney + money.getValue();
        }
        String sumMoneyStr = String.valueOf(sumMoney);
        
        StringBuilder sb = new StringBuilder("Empfänger  " + "ILOGYOU GmbH");  
        sb.append("\n");
        sb.append("IBAN  " + "DE16500700240135521300");
        sb.append("\n");
        sb.append("Betrag " + sumMoneyStr);
        sb.append("\n");
        sb.append("\n");
        sb.append("\n");
        sb.append(getUsage(transferMoneyList));

        StringBuilder result = new StringBuilder("## 배송비 송금정보");
        result.append('\n');
        result.append(sb.toString());
        result.append('\n');
        result.append('\n');
        result.append("## 이메일내용");
        result.append('\n');
        result.append(getEmailContent(transferMoneyList, sumMoneyStr));
        
       return result.toString();
    }
    
    public static String getUsage(ArrayList<TransferMoney> transferMoneyList) {
        StringBuilder sb = new StringBuilder();  
        for (TransferMoney money : transferMoneyList) {
            sb.append(money.getUsage());
        }
        return sb.toString();
    }
    
    public static String getUsagToEmail(ArrayList<TransferMoney> transferMoneyList) {
        StringBuilder sb = new StringBuilder();  
        for (TransferMoney money : transferMoneyList) {
            sb.append(money.getUsage() + '\n');
        }
        return sb.toString();
    }
    
    public static String getTitles(ArrayList<TransferMoney> transferMoneyList) {
        StringBuilder sb = new StringBuilder();
        for (TransferMoney money : transferMoneyList) {
            sb.append(money.getArrivalTitle() + " ");
        }
        return sb.toString();
    }

    public static String getEmailContent(ArrayList<TransferMoney> transferMoneyList, String sumMoneyStr) {
        StringBuilder sb = new StringBuilder("express@ilogexpress.com");  
        sb.append("\n");
        sb.append("\n");
        sb.append(getTitles(transferMoneyList) + "배송 부탁드립니다");
        sb.append("\n");
        sb.append("\n");
        sb.append("안녕하세요");
        sb.append("\n");
        sb.append("\n");
        sb.append("도착한 물품들 중에");
        sb.append("\n");
        sb.append(getUsagToEmail(transferMoneyList) + "총 " + sumMoneyStr + "€"  +" 를 송금했습니다. 확인후 배송 부탁드리겠습니다.");
        sb.append("\n");
        sb.append("수고하세요");
        sb.append("\n");
        sb.append("\n");
        sb.append("조상훈 드림");
        return sb.toString();
    }
    
    public static class TransferMoney {
        private String arrivalTitle;
        private double value;
        
        public TransferMoney(String arrivalTitle, double value) {
            this.arrivalTitle = arrivalTitle;
            this.value = value;
        }

        public String getArrivalTitle() {
            return arrivalTitle;
        }

        public double getValue() {
            return value;
        }
        
        public String getUsage() {
            return arrivalTitle + "-" + String.valueOf(value) +"Euro" + " ";
        }
    }
}
