package crawlerEntities;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseItem {
    private static final String COMPANY_LOGO = "https://moondrive81.cafe24.com/GKoo/gkoo_comany_logo.png";
    private static double excahgeRateEuro = 1400;
    private static int feePercent = 7;
    private double minimumCommision = 13000;
    //2자리리 내림 ex. 10511원? -> 10500원?
    private final int ROUNDED_DIGIT = 3;
    
    protected String getCompanyLogoUrl() {
        return COMPANY_LOGO;
    }
    
    public int calculatePriceWon(double totalPriceEuro) {
        double commision = 0;
        double productPriceWon = excahgeRateEuro*totalPriceEuro;
        if(isMinimumCommision(excahgeRateEuro, totalPriceEuro)) {
            commision = productPriceWon*(feePercent/100);
        } else {
            commision = minimumCommision;
        }
        int ceiledFeeResult = mathCeilDigit(ROUNDED_DIGIT, commision);
        int ceiledProductResult = mathCeilDigit(ROUNDED_DIGIT, productPriceWon);
        return ceiledFeeResult + ceiledProductResult;
    }

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
    
    public String getListToString(List<String> list) {
        return list.stream().collect(Collectors.joining(","));
    }
    
    public abstract String getCategoryId();
    
    public abstract String getItemFullname();
    
    public abstract String getPriceWonString();
    
    public abstract String getColorListString();
    
    public abstract String getSizeListString();
    
    public abstract String getMainImageFileName();
    
    public abstract String getItemImageLinkList();
}
