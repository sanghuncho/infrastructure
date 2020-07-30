package application;

import java.io.IOException;
import estimation.DeliveryRegister;
import estimation.EbayCalculator;
import estimation.EbayDelivery;
import estimation.TransferMoney;

public class EbayAuctionService {
    public static double excahgeRateEuro = 1420;
    private static String DeliveryCompany = 
            "http://www.ilogexpress.com/login";
    
    public static void main( String[] args ) throws IOException
    {
    	//날짜
        var paramDate = "14.07.2020";
        //정산할 아이템수
        int calculateted_Items_Number = 3;
        //적립금
        var lastSavedMoney = 244700;
        //아이템 구매 사이트
        var paramSite = "www.ebay.de";
        
        //### FIRST ITEM
        //아이템 가격 + 아이템 배송비 == 구매대행 송금액
        double first_ItemPriceEuro = 48.96;
        // 직접 수령
        boolean first_SendToMe = false;
        //이베이 셀러 아이디
        String first_SellerId = "slotcar-de";
        //배송
        String first_ArrivalTitle = "Slotcar";
        //지쿠 아이템아이디
        int firsrt_Gkoo_ItemNumber = 198;
        //아이템 이름
        String first_ItemName = "used 6 Vintage Grundig Speakers";
        //아이템 브랜드 이름
        String first_BrandName = "Grundig";
        //아이템 개수
        int first_NumberItem = 6;
        //결제수단 송금 : 1, 페이팔 : 2
        String first_PaymentArt = "P";
        
        //### SECOND ITEM
        //아이템 가격 + 아이템 배송비 == 구매대행 송금액
        double second_ItemPriceEuro = 48.96;
        // 직접 수령
        boolean second_SendToMe = false;
        //이베이 셀러 아이디
        String second_SellerId = "slotcar-de";
        //배송
        String second_ArrivalTitle = "Slotcar";
        //지쿠 아이템아이디
        int second_Gkoo_ItemNumber = 198;
        //아이템 이름
        String second_ItemName = "used 6 Vintage Grundig Speakers";
        //아이템 브랜드 이름
        String second_BrandName = "Grundig";
        //아이템 개수
        int second_NumberItem = 6;
        //결제수단 송금 : 1, 페이팔 : 2
        String second_PaymentArt = "P";
        
        //### THIRD ITEM
        //아이템 가격 + 아이템 배송비 == 구매대행 송금액
        double third_ItemPriceEuro = 48.96;
        // 직접 수령
        boolean third_SendToMe = false;
        //이베이 셀러 아이디
        String third_SellerId = "slotcar-de";
        //배송
        String third_ArrivalTitle = "Slotcar";
        //지쿠 아이템아이디
        int third_Gkoo_ItemNumber = 198;
        //아이템 이름
        String third_ItemName = "used 6 Vintage Grundig Speakers";
        //아이템 브랜드 이름
        String third_BrandName = "Grundig";
        //아이템 개수
        int third_NumberItem = 6;
        //결제수단 송금 : 1, 페이팔 : 2
        String third_PaymentArt = "P";
        
       
    }
    
    public static void runEbayAuctionService() {

////////////////////////
//// START Parameter ///
///////////////////////
    	
        //아이템 가격 + 아이템 배송비 == 구매대행 송금액
        var paramItemPriceEuro = 48.96;
        // 직접 수령
        boolean sendToMe = false;
        
        //이베이 셀러 아이디
        var paramSellerId = "slotcar-de";
        //배송
        var paramArrivalTitle = "Slotcar";

        //지쿠 아이템아이디
        var paramItemNumber = 198;
        
        //적립금
        var pramLastSaved = 244700;
        
        //아이템 이름
        var paramItemName = "used 6 Vintage Grundig Speakers";
        //아이템 브랜드 이름
        var paramBrandName = "Grundig";
        //아이템 개수
        var paramNumberItem = 6;

        //결제수단 송금 : 1, 페이팔 : 2
        var paramPaymentArt = 2;
        //아이템 구매 사이트
        var paramSite = "www.ebay.de";

        
        ////////////////////
        ////  송금시만 작성  ///
        ///////////////////
        //송금 수취인 이름
        String parmaMoneyReceiver = "Alicia Schneider";
        //IBAN
        String paramIBAN = "DE18513500250005449847";
        //BIC for Check
        String paramBIC = "SKGIDE5F";
        //이베이 아이템 번호
        String paramEbayItemnumber = "174331304562"; 
        
        
//////////////////////
//// END Parameter ///
/////////////////////
        
        //이베이 셀러 배송 메세지 확인
        System.out.println("## 셀러 배송 메세지");
        System.out.println(getDeliverySellerMessage(paramArrivalTitle, sendToMe));
        System.out.println('\n');
        
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
        String arrivalTitle = getArrivalTitle(paramArrivalTitle, sendToMe);
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
        
        DeliveryRegister delRegi = new DeliveryRegister(itemName, brandName, site, numberItem, onePrice, arrivalTitle, sendToMe);
        System.out.println('\n');
        System.out.println("## 배송등록");
        System.out.println(delRegi.convertRowExcel());
        
        if(paramPaymentArt == 1) {
            TransferMoney transMoney = new TransferMoney(parmaMoneyReceiver, paramIBAN, paramItemPriceEuro, paramEbayItemnumber, paramArrivalTitle, paramBIC, sendToMe);
            System.out.println('\n');
            System.out.println("## 송금신청");
            System.out.println(transMoney.convertTransferMoneyData());
        } else {
            System.out.println('\n');
            System.out.println("## 페이팔 결제");
        }
    }
    
    public static String getArrivalTitle(String paramArrivalTitle, boolean sendToMe) {
        String arrivalTitle;
        arrivalTitle = sendToMe ?  "iSYS " +  paramArrivalTitle: "ilogexpress " + paramArrivalTitle;
        return arrivalTitle;
    }
    
    public static String getArrivalTitleForSeller(String paramArrivalTitle, boolean sendToMe) {
        String arrivalTitle;
        arrivalTitle = sendToMe ?  "Sanghun Cho, iSYS sofware GmbH" : "ilogexpress " + paramArrivalTitle;
        return arrivalTitle;
    }
    
    public static String getDeliverySellerMessage(String arrivalTitle, boolean sendToMe) {
        return "Hallo," + '\n' + "Bitte schreiben Sie richtig [ " + getArrivalTitleForSeller(arrivalTitle, sendToMe) +
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