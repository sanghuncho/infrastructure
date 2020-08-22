package ebayService;

import application.EbayAuctionBuyingCalculator;
import application.EbayAuctionBuyingCalculator.BuyingserviceCommision;

public class EbayCalculator {
   
    private static final int ROUNDED_DIGIT = 2;
    
    public String date;
    public double itemPriceEuro;
    public int itemNumber;
    public int lastSaved;
    public String itemName;
    
    public EbayCalculator(String date, double itemPriceEuro, int itemNumber, int lastSaved, String itemName){
        this.date = date;
        this.itemPriceEuro = itemPriceEuro;
        this.itemNumber = itemNumber;
        this.lastSaved = lastSaved;
        this.itemName = itemName;
    }
    
    public String getDate() {
        return this.date;
    }
    
    public String getItemName() {
        return this.itemName;
    }
    
    public String getPriceEuroUnit() {
        return String.valueOf(this.itemPriceEuro) + "€";
    }
    
    public double getPriceEuro() {
        return this.itemPriceEuro;
    }
    
    public int getLastSavedMoney() {
        return this.lastSaved;
    }
    
    public int getItemNumber() {
        return this.itemNumber;
    }
    
    public String convertRowExcel() {
        String outputDate = date;
        int lastSavedMoney = lastSaved;
        int outputItemPriceKorea = (int)getItemPriceKorea(itemPriceEuro);
        int outputCommision = getCommision(EbayAuctionBuyingCalculator.excahgeRateEuro, itemPriceEuro);
        int resultItemPrice = (outputItemPriceKorea + outputCommision);
        int lastRemainedMoney = (lastSavedMoney - resultItemPrice);
        
        //날짜
        StringBuilder sb = new StringBuilder(outputDate);  
        sb.append(", ");
                
        //입금액
        sb.append(", ");
        
        //적립금(보유금)
        sb.append(String.valueOf(lastSavedMoney) + "원");
        sb.append(", ");

        //최종정산금액
        sb.append(String.valueOf(lastRemainedMoney) + "원");
        sb.append(", ");

        //경매 및 구매 총금액
        sb.append(String.valueOf(resultItemPrice) + "원");
        sb.append(", ");

        //국제배송비 
        sb.append(", ");
        
        //아이템 가격
        sb.append(String.valueOf(outputItemPriceKorea) + "원");
        sb.append(", ");

        //아이템+독일배송비 유로
        sb.append(getPriceEuroUnit());
        sb.append(", ");

        //수수료
        sb.append(outputCommision + "원");
        sb.append(", ");
        
        //빈칸
        sb.append(", ");
        
        //지쿠 아이템번호
        sb.append(itemNumber);
        sb.append(", ");
        
        //아이템 사진
        sb.append(", ");
        
        //아이템 이름
        sb.append(itemName);
        
        ////정산내역 프린트
        return sb.toString();

    }
    
    public double getItemPriceKorea(double itemPriceEuro) {
        double result = itemPriceEuro*(EbayAuctionBuyingCalculator.excahgeRateEuro);
        int ceiledResult = mathCeilDigit(ROUNDED_DIGIT, result);
        return ceiledResult;
    }
    
    public int getCommision(double currentEurToKRW, double totalPriceEuro) {
        BuyingserviceCommision commision = new BuyingserviceCommision();
        return commision.getResult(currentEurToKRW, totalPriceEuro);
    }
    
    public static int mathCeilDigit(int digit, double price) {
        int power = (int) Math.pow(10, digit);
        int newPrice = (int) Math.ceil(price/power);
        return (newPrice*power);
    }
}
