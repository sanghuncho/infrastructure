package estimation;

public class TransferMoney {
    private String moneyReceiver;
    private String bankIban;
    private String bic;
    private double transferAmountMoney;
    private String usage;
    
    public TransferMoney(String moneyReceiver, String bankIban, double transferAmountMoney,
            String ebayItemnumber, String arrivalTitle, String bic, boolean sendToMe) {
        this.moneyReceiver = moneyReceiver;
        this.bankIban = bankIban;
        this.transferAmountMoney = transferAmountMoney;
        this.usage = getUsageMessage(ebayItemnumber, arrivalTitle, sendToMe);
        this.bic = bic;
        
    }
    
    private String getUsageMessage(String ebayItemnumber, String arrivalTitle, boolean sendToMe) {
        String deliveryAddr = sendToMe ? "Sanghun Cho, isys software GmbH" : "ilogexpress " + arrivalTitle;
        return "Ebay id: arumpark, Artikelnummer: " + ebayItemnumber + ", Lieferadresse:" + deliveryAddr;
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
        sb.append("\n");
        sb.append("\n");
        sb.append("BIC: " + bic);
        
        
        return sb.toString();
    }

}
