package application;

import java.io.IOException;
import java.util.ArrayList;

/**
* 배송비 송금폼 도이치방크 / 배송회사 이메일 내용
* 여러개 한번에 송금, 관련 내용 이메일 메세지
* 
* @author sanghuncho
*
* @since  27.06.2020
* 
* TODO Logger
*
*/
public class DeliveryMoneyTransfer {
    
    public static void main( String[] args ) throws IOException {
        
        TransferMoney money1 = new TransferMoney("Sabine", 43.3);
//        TransferMoney money2 = new TransferMoney("Hoppla", 14.4);
//        TransferMoney money3 = new TransferMoney("Johanna", 16.8);
        
        ArrayList<TransferMoney> transferMoneyList = new ArrayList<>();
        transferMoneyList.add(money1);
//        transferMoneyList.add(money2);
//        transferMoneyList.add(money3);
        
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

        System.out.println("## 배송비 송금정보");
        System.out.println(sb.toString());
        System.out.println('\n');
        System.out.println("## 이메일내용");
        System.out.println(getEmailContent(transferMoneyList, sumMoneyStr));
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


