package application;

import java.io.IOException;
import util.Formatter;

public class EbayDeposit {
    public static void main( String[] args ) throws IOException {
        //입금 날짜
        String paramDate = "26.07.2020";
        
        //입금액
        int paramDepositMoney = 1000000;
        
        //적립금
        int pramLastSaved = 44300;
        
        System.out.println("## 입금내역");
        System.out.println(convertRowExcelDeposit(paramDate, paramDepositMoney, pramLastSaved));
    }
    
    public static String convertRowExcelDeposit(String depositDate, int depositMoney, int lastSaved) {
        int lastRemainedMoney = (lastSaved + depositMoney);
        
        //날짜
        StringBuilder sb = new StringBuilder(depositDate);  
        sb.append(", ");
        
        sb.append(Formatter.convertKoreaCurrency(depositMoney));
        sb.append(", ");
        
        sb.append(Formatter.convertKoreaCurrency(lastSaved));
        sb.append(", ");
        
        sb.append(Formatter.convertKoreaCurrency(lastRemainedMoney));
        sb.append(", ");
        
        return sb.toString();
        
    }
}
