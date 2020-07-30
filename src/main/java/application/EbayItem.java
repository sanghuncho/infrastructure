package application;

public class EbayItem {
	private String purchaseDate;
    private String purchaseSite;
    private double itemPriceEuro;
    // 직접 수령
    private boolean sendToMe;
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
    
    public EbayItem (String purchaseDate, String purchaseSite, double itemPriceEuro, boolean sendToMe, String sellerId, String arrivalTitle, int gkooItemNumber,
     String itemName, String brandName, int numberOfItem, String paymentArt) {
    	this.purchaseDate = purchaseDate;
    	this.purchaseSite = purchaseSite;
    	this.itemPriceEuro = itemPriceEuro;
    	this.sendToMe = sendToMe;
        this.sellerId = sellerId;
        this.arrivalTitle = arrivalTitle;
        this.gkooItemNumber = gkooItemNumber;
        this.itemName = itemName;
        this.brandName = brandName;
        this.numberOfItem = numberOfItem;
        this.paymentArt = paymentArt;
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

	public boolean isSendToMe() {
		return sendToMe;
	}

	public void setSendToMe(boolean sendToMe) {
		this.sendToMe = sendToMe;
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
}
