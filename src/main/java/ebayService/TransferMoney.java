package ebayService;

import application.EbayAuctionBuyingCalculator.Shipping_Address;

public class TransferMoney {
    private String moneyReceiver;
    private String bankIban;
    private String bic;
    private double transferAmountMoney;
    private String usage;
    
    public TransferMoney(String moneyReceiver, String bankIban, double transferAmountMoney,
            String ebayItemnumber, String arrivalTitle, String bic, Shipping_Address shippingAddress) {
        this.moneyReceiver = moneyReceiver;
        this.bankIban = bankIban;
        this.transferAmountMoney = transferAmountMoney;
        this.usage = getUsageMessage(ebayItemnumber, arrivalTitle, shippingAddress);
        this.bic = bic;
    }
    
    private String getUsageMessage(String ebayItemnumber, String arrivalTitle, Shipping_Address shippingAddress) {
        String deliveryAddr="";
        switch(shippingAddress) {
            case ILOG:
                deliveryAddr = "ilogexpress " + arrivalTitle;
                break;
            case MARCHI:
                deliveryAddr = "Sanghun Cho, Marchionini straße.7";
                break;
            case ISYS:
                deliveryAddr = "Sanghun Cho, iSYS software GmbH";
                break;
        }
        return "Ebay id: arumpark, Artikelnummer: " + ebayItemnumber + ", Lieferadresse:" + deliveryAddr;
    }
    
    public String convertTransferMoneyData() {
        StringBuilder sb = new StringBuilder("Empfänger:  " + moneyReceiver);  
        sb.append("\n");
        sb.append("IBAN:  " + bankIban);
        sb.append("\n");
        sb.append("BIC: " + bic);
        sb.append("\n");
        sb.append("Betrag:  " + String.valueOf(transferAmountMoney));
        sb.append("\n");
        sb.append("\n");
        sb.append(usage);
        sb.append("\n");
        sb.append("\n");
        sb.append("Check!! --> Name, IBAN, BIC, Betrag, Termin, Lieferadresse");
        sb.append("\n");
        sb.append("\n");
        sb.append("송금 신청후 check bezahlt markiert in ebay!!");
        sb.append("\n");
        sb.append(" ==> check excel file!");
        
        return sb.toString();
    }

    public String getMoneyReceiver() {
        return moneyReceiver;
    }

    public void setMoneyReceiver(String moneyReceiver) {
        this.moneyReceiver = moneyReceiver;
    }

    public String getBankIban() {
        return bankIban;
    }

    public void setBankIban(String bankIban) {
        this.bankIban = bankIban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public double getTransferAmountMoney() {
        return transferAmountMoney;
    }

    public void setTransferAmountMoney(double transferAmountMoney) {
        this.transferAmountMoney = transferAmountMoney;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }
}
