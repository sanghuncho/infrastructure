package estimation;

public class TransferMoney {
    private String moneyReceiver;
    private String bankIban;
    private double transferAmountMoney;
    private String usage;
    
    public TransferMoney(String moneyReceiver, String bankIban, double transferAmountMoney, String ebayItemnumber, String arrivalTitle) {
        this.moneyReceiver = moneyReceiver;
        this.bankIban = bankIban;
        this.transferAmountMoney = transferAmountMoney;
        this.usage = getUsageMessage(ebayItemnumber, arrivalTitle);
    }
    
    private String getUsageMessage(String ebayItemnumber, String arrivalTitle) {
        return "Ebay id: arumpark, Artikelnummer: " + ebayItemnumber + ", Lieferadresse: ilogexpress " + arrivalTitle;
    }
    
    public String convertTransferMoneyData() {
        StringBuilder sb = new StringBuilder("Empfänger  " + moneyReceiver);  
        sb.append("\n");
        sb.append("IBAN  " + bankIban);
        sb.append("\n");
        sb.append("Betrag " + String.valueOf(transferAmountMoney));
        sb.append("\n");
        sb.append("\n");
        sb.append("\n");
        sb.append(usage);
        
        return sb.toString();
    }

}
