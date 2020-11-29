package util;

public class Formatter {

    public static String convertKoreaCurrency(int value) {
        return value + "원";
    }
    
    //1.550,00
    public static String deleteNonDigits(String priceEuro) {
        String editedPrice1 = priceEuro.replaceAll("[^0-9\\,]","");
        String editedPrice2  = editedPrice1.replaceAll("[\\,]",".");
        return editedPrice2;
    }
}
