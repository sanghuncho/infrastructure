package application;

import java.io.IOException;
import estimation.DeliveryRegister;
import estimation.EbayCalculator;
import estimation.EbayDelivery;
import estimation.TransferMoney;

public class EbayAuctionService {
    public static double excahgeRateEuro = 1420;
    
    public static void main( String[] args ) throws IOException
    {
        //날짜
        String paramDate = "26.06.2020";
        
        //아이템 가격 + 아이템 배송비 == 구매대행 송금액
        double paramItemPriceEuro = 105;
        
        //지쿠 아이템아이디
        int paramItemNumber = 164;
        
        //적립금
        int pramLastSaved =  1048600;
        
        //아이템 이름
        String paramItemName = "Isophon DKT 11 used speaker";
        //아이템 브랜드 이름
        String paramBrandName = "ISOPHON";
        
        //이베이 셀러 아이디
        String paramSellerId = "v2apeter";
        //배송
        String paramArrivalTitle = "V2ap";

        //결제수단 송금 : 1, 페이팔 : 2
        int paramPaymentArt = 1;
        //아이템 구매 사이트
        String paramSite = "www.ebay.de";
        //아이템 개수
        int paramNumberItem = 1;
        
        
        //송금시만 작성
        //송금 수취인 이름
        String parmaMoneyReceiver = "Peter Gräbner";
        //IBAN
        String paramIBAN = "DE97254621600253898900";
        //이베이 아이템 번호
        String paramEbayItemnumber = "193535869286"; 
        
        
        //정산내역 변수
        String inputDate = paramDate;
        double inputItemPriceEuro = paramItemPriceEuro;
        int inputItemNumber = paramItemNumber;
        int lastSaved = pramLastSaved;
        String itemName = paramItemName;
        
        EbayCalculator calc = 
                new EbayCalculator(inputDate, inputItemPriceEuro, inputItemNumber, lastSaved, itemName);
        System.out.println("## 정산내역");
        System.out.println(calc.convertRowExcel());
              
        //배송내역 변수
        String arrivalTitle = "ilogexpress " + paramArrivalTitle;
        int paymentArt = paramPaymentArt; //결제수단 송금 - 1, 페이팔 - 2
        String sellerId = paramSellerId;
        String brandName = paramBrandName;
        String site = paramSite;
        int numberItem = paramNumberItem;
        double onePrice = priceFormat(inputItemPriceEuro/numberItem);
               
        EbayDelivery delivery = new EbayDelivery(inputItemNumber, arrivalTitle, paymentArt, sellerId,
                brandName, site, numberItem, inputItemPriceEuro, itemName, onePrice);
        System.out.println('\n');
        System.out.println("## 배송내역");
        System.out.println(delivery.convertRowExcel());
        
        System.out.println('\n');
        System.out.println("## 셀러 배송 메세지");
        System.out.println(getDeliverySellerMessage(paramArrivalTitle));

        DeliveryRegister delRegi = new DeliveryRegister(itemName, brandName, site, numberItem, onePrice, arrivalTitle);
        System.out.println('\n');
        System.out.println("## 배송등록");
        System.out.println(delRegi.convertRowExcel());
        
        TransferMoney transMoney = new TransferMoney(parmaMoneyReceiver, paramIBAN, paramItemPriceEuro, paramEbayItemnumber, paramArrivalTitle);
        System.out.println('\n');
        System.out.println("## 송금신청");
        System.out.println(transMoney.convertTransferMoneyData());
    }
    
    public static String getDeliverySellerMessage(String arrivalTitle) {
        return "Hallo," + '\n' + "Bitte schreiben Sie richtig [ ilogexpress " + arrivalTitle +
                " ] auf Empfänger im Paket, " + '\n' + "damit unserer Mitarbeiter dieses Paket sortieren kann." + '\n' + "Danke im Voraus!" + '\n' + "Mit freundlichen Grüßen";
    }
    
    public static double priceFormat(double price) {
        double roundPrice = Math.round(100.0 * price) / 100.0;
        return roundPrice;
    }
    
    public static class BuyingserviceCommision {
        //수수료 퍼센트
        private double feePercent = 10;
        
        //최저 수수료비용
        private double minimumCommision = 13000;
        
        //한화
        private double currentEurToKRW;
        
        private double totalPriceEuro;
        
        //2자리리 내림 ex. 10511원? -> 10500원?
        private final int ROUNDED_DIGIT = 2;
        
        public int getResult(double currentEurToKRW, double totalPriceEuro) {
            double result = 0;
            if(isMinimumCommision(currentEurToKRW, totalPriceEuro)) {
                result = (currentEurToKRW*totalPriceEuro)*(feePercent/100);
            } else {
                result = minimumCommision;
            }
            int ceiledResult = mathCeilDigit(ROUNDED_DIGIT, result);
            return ceiledResult;
        }
        
        // 최저수수료 체�?�
        private boolean isMinimumCommision(double currentEurToKRW, double totalPriceEuro) {
            double commision = (currentEurToKRW*totalPriceEuro)*(feePercent/100);
            if(commision >= minimumCommision) {
                return true;
            } else {
                return false;
            }
        }
        
        private int mathCeilDigit(int digit, double price) {
            int power = (int) Math.pow(10, digit);
            int newPrice = (int) Math.round(price/power);
            return (newPrice*power);
        }
    }
}
