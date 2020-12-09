package application;

import java.io.IOException;
import java.util.ArrayList;

import application.EbayItem.TransferData;
import ebayApi.EbayBaseData;
import ebayApi.EbayUtil;
import ebayService.DeliveryRegister;
import ebayService.EbayCalculator;
import ebayService.EbayDelivery;
import ebayService.TransferMoney;

// moondrive2012 아이디 구매체크
public class EbayAuctionBuyingCalculator {
    //https://search.naver.com/search.naver?where=nexearch&query=%EC%9C%A0%EB%A1%9C+%ED%99%98%EC%9C%A8&ie=utf8&sm=tab_she&qdt=0
    public static double excahgeRateEuro = 1450;
    private static String DeliveryCompany = 
            "http://www.ilogexpress.com/login";//PLZ 65830
    
    public static void main( String[] args ) throws IOException
    {
        //==========================================================================
        //적립금
        int first_lastSavedMoney = 732900;

        //날짜
        var paramDate = "2020.12.09";

        //이베이 아이템 번호
        String first_EbayItemnumber = "293864192127"; 
        
        //지쿠 아이템아이디
        int first_Gkoo_ItemNumber = 363;

        // 배송지 설정 ILOG, ISYS , MARCHI, MANNHARDT
        Shipping_Address first_Shipping_Address = Shipping_Address.MARCHI;

        //배송
        String first_ArrivalTitle = "GK88";
        
        //결제수단 송금 : T, 페이팔 : P
        String first_PaymentArt = "T";
        
        // input total price manually 
        //double first_ItemPriceEuro = 39.12;
        
        //==========================================================================
        
        //송금 수취인 이름
        String first_MoneyReceiver = "";
        //IBAN
        String first_IBAN = "";
        //BIC for Check
        String first_BIC = "";
        
        EbayBaseData ebayBaseData = EbayUtil.getEbayBaseData(first_EbayItemnumber);
        
        //정산할 아이템수
        int calculateted_Items_Number = 1;
        
        //아이템 구매 사이트
        var purchaseSite = "www.ebay.de";
        
        //### FIRST ITEM
        //아이템 가격 + 아이템 배송비 == 구매대행 송금액
        double first_ItemPriceEuro = ebayBaseData.getItemTotalPrice();
        
        //이베이 셀러 아이디
        String first_SellerId = ebayBaseData.getSellerId();
       
        //아이템 이름
        String first_ItemName = ebayBaseData.getItemName();
        
        //ToDo: google or ? translation to englisch
        //아이템 브랜드 이름
        String first_BrandName = ebayBaseData.getBrandName();
        
        //아이템 개수
        int first_NumberItem = 1;
        
        TransferData first_transferData = new TransferData(first_MoneyReceiver, first_IBAN, first_BIC, first_EbayItemnumber);
        
        EbayItem first_ebayItem = new EbayItem(paramDate, first_lastSavedMoney, purchaseSite, first_ItemPriceEuro,
                first_Shipping_Address, first_SellerId, first_ArrivalTitle, first_Gkoo_ItemNumber,
                first_ItemName, first_BrandName, first_NumberItem, first_PaymentArt, first_transferData);
        
        //### SECOND ITEM
        //아이템 가격 + 아이템 배송비 == 구매대행 송금액
        double second_ItemPriceEuro = 48.76;
        //적립금
        int second_lastSavedMoney = 39800;
        
        // 배송지 설정 ILOG, ISYS , MARCHI, MANNHARDT
        Shipping_Address second_Shipping_Address = Shipping_Address.MARCHI;
        
        //이베이 셀러 아이디
        String second_SellerId = "";
        //배송
        String second_ArrivalTitle = "GK00";
        //지쿠 아이템아이디
        int second_Gkoo_ItemNumber = 0;
        //아이템 이름
        String second_ItemName = "";
        //아이템 브랜드 이름
        String second_BrandName = "";
        //아이템 개수
        int second_NumberItem = 1;
        //결제수단 송금 : T, 페이팔 : P
        String second_PaymentArt = "T";
        //송금 수취인 이름
        String second_MoneyReceiver = "";
        //IBAN
        String second_IBAN = "";
        //BIC for Check
        String second_BIC = "";
        //이베이 아이템 번호
        String second_EbayItemnumber = ""; 
        
        TransferData second_transferData = new TransferData(second_MoneyReceiver, second_IBAN, second_BIC, second_EbayItemnumber);
        
        EbayItem second_ebayItem = new EbayItem(paramDate, second_lastSavedMoney, purchaseSite, second_ItemPriceEuro,
                second_Shipping_Address, second_SellerId, second_ArrivalTitle, second_Gkoo_ItemNumber,
                second_ItemName, second_BrandName, second_NumberItem, second_PaymentArt, second_transferData);
        
        //### THIRD ITEM
        //아이템 가격 + 아이템 배송비 == 구매대행 송금액
        double third_ItemPriceEuro = 48.96;
        //적립금
        int third_lastSavedMoney = 244700;
        
        // 배송지 설정 ILOG, ISYS , MARCHI, MANNHARDT
        Shipping_Address third_Shipping_Address = Shipping_Address.ILOG;
        
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
        //결제수단 송금 : T, 페이팔 : P
        String third_PaymentArt = "P";
        //송금 수취인 이름
        String third_MoneyReceiver = "";
        //IBAN
        String third_IBAN = "";
        //BIC for Check
        String third_BIC = "";
        //이베이 아이템 번호
        String third_EbayItemnumber = ""; 
        
        TransferData third_transferData = new TransferData(third_MoneyReceiver, third_IBAN, third_BIC, third_EbayItemnumber);
        
        EbayItem third_ebayItem = new EbayItem(paramDate, third_lastSavedMoney, purchaseSite, third_ItemPriceEuro,
                third_Shipping_Address, third_SellerId, third_ArrivalTitle, third_Gkoo_ItemNumber,
                third_ItemName, third_BrandName, third_NumberItem, third_PaymentArt, third_transferData);
        
        StringBuilder results = new StringBuilder(); 
        
        ArrayList<EbayItem> ebayitemsList = new ArrayList<EbayItem>();
        ebayitemsList.add(first_ebayItem);
        ebayitemsList.add(second_ebayItem);
        ebayitemsList.add(third_ebayItem);
        
        for(int i=0;i<calculateted_Items_Number;i++) {
            runEbayAuctionService(ebayitemsList.get(i), results);
        }
        System.out.println(results.toString());
    }
    
    public static void runEbayAuctionService(EbayItem ebayitem, StringBuilder results) {

////////////////////////
//// START Parameter ///
///////////////////////
        var paramDate = ebayitem.getPurchaseDate();
        //아이템 가격 + 아이템 배송비 == 구매대행 송금액
        var paramItemPriceEuro = ebayitem.getItemPriceEuro();

        // 배송지 설정 ILOG, ISYS , MARCHI, MANNHARDT
        Shipping_Address shippingAddress = ebayitem.getShippingAddress();
        
        //이베이 셀러 아이디
        var paramSellerId = ebayitem.getSellerId();
        //배송
        var paramArrivalTitle = ebayitem.getArrivalTitle();

        //지쿠 아이템아이디
        var paramItemNumber = ebayitem.getGkooItemNumber();
        
        //적립금
        var pramLastSaved = ebayitem.getLastSavedMoney();
        
        //아이템 이름
        var paramItemName = ebayitem.getItemName();
        //아이템 브랜드 이름
        var paramBrandName = ebayitem.getBrandName();
        //아이템 개수
        var paramNumberItem = ebayitem.getNumberOfItem();

        //결제수단 송금 : 1, 페이팔 : 2
        var paramPaymentArt = ebayitem.getPaymentArt();
        //아이템 구매 사이트
        var paramSite = "www.ebay.de";

        
        ////////////////////
        ////  송금시만 작성  ///
        ///////////////////
        //송금 수취인 이름
        String parmaMoneyReceiver = ebayitem.getTransferData().getMoneyReceiver();
        //IBAN
        String paramIBAN = ebayitem.getTransferData().getIban();
        //BIC for Check
        String paramBIC = ebayitem.getTransferData().getBic();
        //이베이 아이템 번호
        String paramEbayItemnumber = ebayitem.getTransferData().getEbayItemnumber(); 
        
        
//////////////////////
//// END Parameter ///
/////////////////////
        
        //이베이 셀러 배송 메세지 확인
        results.append("## 셀러 배송 메세지");
        results.append('\n');
        results.append(getDeliverySellerMessage(paramArrivalTitle, shippingAddress));
        results.append('\n');
//        System.out.println("## 셀러 배송 메세지");
//        System.out.println(getDeliverySellerMessage(paramArrivalTitle, sendToMe));
//        System.out.println('\n');
        
        //정산내역 변수
        String inputDate = paramDate;
        double inputItemPriceEuro = paramItemPriceEuro;
        int inputItemNumber = paramItemNumber;
        int lastSaved = pramLastSaved;
        String itemName = paramItemName;
        
        EbayCalculator calc = 
                new EbayCalculator(inputDate, inputItemPriceEuro, inputItemNumber, lastSaved, itemName);
        results.append('\n');
        results.append("## 정산내역");
        results.append('\n');
        results.append(calc.convertRowExcel());
//        System.out.println("## 정산내역");
//        System.out.println(calc.convertRowExcel());
              
        //배송내역 변수
        String arrivalTitle = getArrivalTitle(paramArrivalTitle, shippingAddress);
        String paymentArt = paramPaymentArt; //결제수단 송금 - 1, 페이팔 - 2
        String sellerId = paramSellerId;
        String brandName = paramBrandName;
        String site = paramSite;
        int numberItem = paramNumberItem;
        double onePrice = priceFormat(inputItemPriceEuro/numberItem);
               
        EbayDelivery delivery = new EbayDelivery(inputItemNumber, arrivalTitle, paymentArt, sellerId,
                brandName, site, numberItem, inputItemPriceEuro, itemName, onePrice);
        results.append('\n');
        results.append('\n');
        results.append("## 배송내역");
        results.append('\n');
        results.append(delivery.convertRowExcel());
//        System.out.println('\n');
//        System.out.println("## 배송내역");
//        System.out.println(delivery.convertRowExcel());
        
        DeliveryRegister delRegi = new DeliveryRegister(itemName, brandName, site, numberItem, onePrice, arrivalTitle, shippingAddress);
        results.append('\n');
        results.append('\n');
        results.append("## 배송등록");
        results.append('\n');
        results.append(delRegi.convertRowExcel());
//        System.out.println('\n');
//        System.out.println("## 배송등록");
//        System.out.println(delRegi.convertRowExcel());
        
        if(paramPaymentArt == "T") {
            TransferMoney transMoney = new TransferMoney(parmaMoneyReceiver, paramIBAN, paramItemPriceEuro, 
            		paramEbayItemnumber, paramArrivalTitle, paramBIC, shippingAddress);
            results.append('\n');
            results.append('\n');
            results.append("## 송금신청");
            results.append('\n');
            results.append(transMoney.convertTransferMoneyData());
//            System.out.println('\n');
//            System.out.println("## 송금신청");
//            System.out.println(transMoney.convertTransferMoneyData());
        } else if(paramPaymentArt == "P") {
        	results.append('\n');
        	results.append('\n');
        	results.append("## 페이팔 결제");
        	results.append('\n');
        	results.append('\n');
//            System.out.println('\n');
//            System.out.println("## 페이팔 결제");
        } else {
            System.out.println("Error for Payment");
        }
    }
    
    public static String getArrivalTitle(String paramArrivalTitle, Shipping_Address shippingAddress) {
        String title = null;
        switch(shippingAddress) {
            case ILOG:
                title = "ilogexpress " + paramArrivalTitle;
                break;
            case MARCHI:
                title = "Marchi " + paramArrivalTitle;
                break;
            case ISYS:
                title = "iSYS " + paramArrivalTitle;
                break;
            case MANNHARDT:
                title = "Mannhardt " + paramArrivalTitle;
                break;
        }
        return title;
    }
    
    public static String getArrivalAddressILOG(String paramArrivalTitle) {
        return "Hallo," + '\n' 
                + "Bitte schreiben Sie richtig folgende Adresse und Empfänger auf dem Paket, damit unserer Mitarbeiter dieses Paket sortieren kann." + '\n'
                + "Empfänger: ilogexpress " + paramArrivalTitle + '\n'
                + "Adresse:" + '\n'
                + "Richard Wagner straße.12" + '\n'
                + "D-65830 Kriftel" + '\n'
                + "Deutschland" + '\n'
                + "Danke im Voraus!" + '\n' 
                + "Mit freundlichen Grüßen";
    }
    
    public static String getArrivalAddressISYS(String paramArrivalTitle) {
        return "Hallo," + '\n' 
                + "Bitte schreiben Sie richtig folgende Adresse und Empfänger auf dem Paket, damit unserer Mitarbeiter dieses Paket sortieren kann." + '\n'
                + "Empfänger: Sanghun Cho, iSYS sofware GmbH" + '\n'
                + "Adresse:" + '\n'
                + "Grillparzer straße.10" + '\n'
                + "D-81675 München" + '\n'
                + "Deutschland" + '\n'
                + "Danke im Voraus!" + '\n' 
                + "Mit freundlichen Grüßen";
    }
    
    public static String getArrivalAddressMARCHI(String paramArrivalTitle) {
        return "Hallo," + '\n' 
                + "Bitte schreiben Sie richtig folgende Adresse und Empfänger auf dem Paket" + '\n'
                + "Empfänger: Sanghun Cho" + '\n'
                + "Adresse:" + '\n'
                + "Marchionini straße.7, Dachgeshoss(" + paramArrivalTitle + ")" + '\n'
                + "D-81377 München" + '\n'
                + "Deutschland" + '\n'
                + "Danke im Voraus!" + '\n' 
                + "Mit freundlichen Grüßen";
    }
    
    public static String getArrivalAddressMANNAHRDT(String paramArrivalTitle) {
        return "Hallo," + '\n' 
                + "Bitte schreiben Sie richtig folgende Adresse und Empfänger auf dem Paket" + '\n'
                + "Empfänger: Sanghun Cho, c/o Park" + '\n'
                + "Adresse:" + '\n'
                + "Mannhardt straße.10, 4 Stock(" + paramArrivalTitle + ")" + '\n'
                + "D-80538 München" + '\n'
                + "Deutschland" + '\n'
                + "Danke im Voraus!" + '\n' 
                + "Mit freundlichen Grüßen";
    }
    
    
    public static String getDeliverySellerMessage(String arrivalTitle, Shipping_Address shippingAddress) {
        String message = null;
        switch(shippingAddress) {
            case ILOG:
                message = getArrivalAddressILOG(arrivalTitle);
                break;
            case MARCHI:
                message = getArrivalAddressMARCHI(arrivalTitle);
                break;
            case ISYS:
                message = getArrivalAddressISYS(arrivalTitle);
                break;
            case MANNHARDT:
                message = getArrivalAddressMANNAHRDT(arrivalTitle);
                break;
        }
        return message;
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
    
    public enum Shipping_Address {
        ILOG, ISYS, MARCHI, MANNHARDT
    }
}