package crawlerBrandEntities;

import java.util.ArrayList;
import java.util.List;
import crawlerEntities.BaseItemCosmetic;
import crawlerEntities.MassItem;

public class MassItemEcoverde extends BaseItemCosmetic {

    private MassItem massItem;
    private String companyLogo;
    private int priceWon;
    private String itemBrandNameKor;
    private String itemTitleNameKor;
    private String itemDescriptionKor;
    private String itemUsageKor;
    
    public MassItemEcoverde(MassItem massItem) {
        this.massItem = massItem;
        this.priceWon = super.calculatePriceWon(massItem.getItemPriceEuro());
    }
    
    @Override
    public String getCategoryId() {
        return massItem.getCategoryId();
    }

    @Override
    public String getItemFullname() {
        StringBuilder fullnameBd = new StringBuilder();
        fullnameBd.append(massItem.getBrandName());
        fullnameBd.append(" ");
        fullnameBd.append(massItem.getItemTitle());
        fullnameBd.append(" ");
        fullnameBd.append(massItem.getItemVolume());
        return fullnameBd.toString();
    }

    @Override
    public String getPriceWonString() {
        return String.valueOf(priceWon);
    }

    @Override
    public String getMainImageFileName() {
        return massItem.getMainImageFileName();
    }

    public MassItem getMassItem() {
        return massItem;
    }

    public void setMassItem(MassItem massItem) {
        this.massItem = massItem;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public int getPriceWon() {
        return priceWon;
    }

    public void setPriceWon(int priceWon) {
        this.priceWon = priceWon;
    }
    
//    public String getItemFullDescription() {
//        return super.getItemFullDescription(getItemFullname(), massItem.getBrandName(), 
//                massItem.getItemDescription(), massItem.getItemUsage(), massItem.getItemIngredients());
//    }
    @Override
    public String getItemFullDescription() {
      StringBuilder result = new StringBuilder();
      result.append(getItemFullNameHtml(getItemFullname()));
      result.append(getItemBrandNameHtml(massItem.getBrandName()));
      result.append(getEmptyLineHtml());
      result.append(getItemDescriptionHtml(massItem.getItemDescription()));
      result.append(getEmptyLineHtml());
      result.append(getItemUsageHtml(massItem.getItemUsage()));
      result.append(getEmptyLineHtml());
      result.append(getItemIngredientHtml(massItem.getItemIngredients()));
      
      return result.toString();
        
//        return super.getItemFullDescription(getItemFullname(), massItem.getBrandName(), 
//                massItem.getItemDescription(), massItem.getItemUsage(), massItem.getItemIngredients());
    }

    @Override
    public String getItemFullnameWithPrefix() {
        return "[" + massItem.getItemCategory() + "]" + " " + getItemFullname();
    }
    
    // <p style="text-align: center;"><strong>르네휘테르 쿠비시아 샴푸 600ml</strong></p>
    // <p style="text-align: center;"><strong>르네휘테르</strong></p>
    // <p style="text-align: center;">&nbsp;</p>
    // <p style="text-align: center;"><strong>아이템 </strong><strong>설명</strong></p>
    // <p style="text-align: center;">&nbsp;</p>
    // <p style="text-align: center;"><strong>사용법</strong></p>
    // <p style="text-align: center;">적당량을 덜어서 두피와 모발에 골고루 발라 마사지 하듯 부드럽게 거품을 내어 깨끗하게 헹궈주세요.</p>
}