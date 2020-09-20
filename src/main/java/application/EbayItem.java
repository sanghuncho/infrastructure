package application;

import application.EbayAuctionBuyingCalculator.Shipping_Address;
import ebayService.TransferMoney;

public class EbayItem {
	private String purchaseDate;
	private int lastSavedMoney;
    private String purchaseSite;
    private double itemPriceEuro;
    // 직접 수령
    private Shipping_Address shippingAddress;
    //이베이 셀러 아이디
    private String sellerId;
    //배송
    private String arrivalTitle;
    //지쿠 아이템아이디
    private int gkooItemNumber;
    //아이템 이름
    private String itemName;
    //아이템 브랜드 이름
    private String brandName;
    //아이템 개수
    private int numberOfItem;
    //결제수단 송금 : 1, 페이팔 : 2
    private String paymentArt;
    
    private TransferData transferData;
    
    public EbayItem (String purchaseDate, int lastSavedMoney, String purchaseSite, double itemPriceEuro, Shipping_Address shippingAddress, 
            String sellerId, String arrivalTitle, int gkooItemNumber, String itemName,
            String brandName, int numberOfItem, String paymentArt, TransferData transferData) {
    	this.purchaseDate = purchaseDate;
    	this.lastSavedMoney = lastSavedMoney;
    	this.purchaseSite = purchaseSite;
    	this.itemPriceEuro = itemPriceEuro;
    	this.setShippingAddress(shippingAddress);
        this.sellerId = sellerId;
        this.arrivalTitle = arrivalTitle;
        this.gkooItemNumber = gkooItemNumber;
        this.itemName = itemName;
        this.brandName = brandName;
        this.numberOfItem = numberOfItem;
        this.paymentArt = paymentArt;
        this.transferData = transferData;
    }

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getPurchaseSite() {
		return purchaseSite;
	}

	public void setPurchaseSite(String purchaseSite) {
		this.purchaseSite = purchaseSite;
	}

	public double getItemPriceEuro() {
		return itemPriceEuro;
	}

	public void setItemPriceEuro(double itemPriceEuro) {
		this.itemPriceEuro = itemPriceEuro;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getArrivalTitle() {
		return arrivalTitle;
	}

	public void setArrivalTitle(String arrivalTitle) {
		this.arrivalTitle = arrivalTitle;
	}

	public int getGkooItemNumber() {
		return gkooItemNumber;
	}

	public void setGkooItemNumber(int gkooItemNumber) {
		this.gkooItemNumber = gkooItemNumber;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public int getNumberOfItem() {
		return numberOfItem;
	}

	public void setNumberOfItem(int numberOfItem) {
		this.numberOfItem = numberOfItem;
	}

	public String getPaymentArt() {
		return paymentArt;
	}

	public void setPaymentArt(String paymentArt) {
		this.paymentArt = paymentArt;
	}

    public int getLastSavedMoney() {
        return lastSavedMoney;
    }

    public void setLastSavedMoney(int lastSavedMoney) {
        this.lastSavedMoney = lastSavedMoney;
    }
    
    public TransferData getTransferData() {
        return transferData;
    }

    public void setTransferData(TransferData transferData) {
        this.transferData = transferData;
    }

    public Shipping_Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Shipping_Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public static class TransferData {
        private String moneyReceiver;
        //IBAN
        private String iban;
        //BIC for Check
        private String bic;
        //이베이 아이템 번호
        private String ebayItemnumber;
        
        public TransferData(String moneyReceiver, String iban, String bic, String ebayItemnumber) {
            this.moneyReceiver = moneyReceiver;
            this.iban = iban;
            this.bic = bic;
            this.ebayItemnumber = ebayItemnumber;
        }

        public String getMoneyReceiver() {
            return moneyReceiver;
        }

        public void setMoneyReceiver(String moneyReceiver) {
            this.moneyReceiver = moneyReceiver;
        }

        public String getIban() {
            return iban;
        }

        public void setIban(String iban) {
            this.iban = iban;
        }

        public String getBic() {
            return bic;
        }

        public void setBic(String bic) {
            this.bic = bic;
        }

        public String getEbayItemnumber() {
            return ebayItemnumber;
        }

        public void setEbayItemnumber(String ebayItemnumber) {
            this.ebayItemnumber = ebayItemnumber;
        }
    }
}