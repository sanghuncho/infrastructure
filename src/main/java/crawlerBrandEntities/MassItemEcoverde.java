package crawlerBrandEntities;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import crawlerApp.CrawlerEcoverde;
import crawlerEntities.BaseItemCosmetic;
import crawlerEntities.MassItem;
import translator.SolidTranslator;
import translator.TranslateApi;
import util.Formatter;

public class MassItemEcoverde extends BaseItemCosmetic {
    private static final Logger LOGGER = LogManager.getLogger(CrawlerEcoverde.class);

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
        invokeTranslateApi(massItem);
    }
    
    private void invokeTranslateApi(MassItem massItem) {
        String description = massItem.getItemDescription();
        if (description != null) {
            String translatedDescription = "";
            try {
                translatedDescription = TranslateApi.doTranslateDEtoKor(description);
            } catch (FileNotFoundException e) {
                LOGGER.error("TranslatedDescription has error:" + description, e);
            } catch (IOException e) {
                LOGGER.error("TranslatedDescription has error:" + description, e);
            }
            setItemDescriptionKor(translatedDescription);
        } else {
            setItemDescriptionKor("");
            LOGGER.error("Description is not found! No transation:" + massItem.getItemTitle());
        }
        
        String usage = massItem.getItemUsage();
        if(massItem.isGrobalUsage()) {
            setItemUsageKor(usage);
        } else if (usage != null && !massItem.isGrobalUsage()) {
            String translatedUsage = "";
            try {
                translatedUsage = TranslateApi.doTranslateDEtoKor(usage);
            } catch (FileNotFoundException e) {
                LOGGER.error("TranslatedUsage has error:" + usage, e);
            } catch (IOException e) {
                LOGGER.error("TranslatedUsage has error:" + usage, e);
            }
            setItemUsageKor(translatedUsage);
        } else {
            setItemUsageKor("");
            LOGGER.error("Usage is not found! No transation:" + massItem.getItemTitle());
        }
    }
    
    @Override
    public String getCategoryId() {
        return massItem.getCategoryId();
    }

    @Override
    public String getItemFullname() {
        StringBuilder fullnameBd = new StringBuilder();
        //fullnameBd.append(massItem.getBrandName());
        fullnameBd.append(SolidTranslator.getBrandNameKor(massItem.getBrandName()));
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
    
    
    /**
     * raw german description and usage
     * smartstore.createItemRow
     */
    @Override
    public String getItemFullDescriptionDE() {
      StringBuilder result = new StringBuilder();
      result.append(getItemFullNameHtml(getItemFullname()));
      result.append(getItemBrandNameHtml(massItem.getBrandName()));
      result.append(getEmptyLineHtml());
      result.append(getItemDescriptionHtml(Formatter.setLinebreakAfterPunctHtml(massItem.getItemDescription())));
      result.append(getEmptyLineHtml());
      result.append(getItemUsageHtml(Formatter.setLinebreakAfterPunctHtml(massItem.getItemUsage())));
      result.append(getEmptyLineHtml());
      result.append(getItemIngredientHtml(massItem.getItemIngredients()));
      
      return addAlignment(addTopBottomInfo(result.toString()));
    }
    
    /**
     * translated korean description and usage from google translate api
     * smartstore.createItemRow
     */
    @Override
    public String getItemFullDescriptionKOR() {
      StringBuilder result = new StringBuilder();
      result.append(getItemFullNameHtml(getItemFullname()));
      result.append(getItemBrandNameHtml(massItem.getBrandName()));
      result.append(getEmptyLineHtml());
      result.append(getItemDescriptionHtml(Formatter.setLinebreakAfterPunctHtml(getItemDescriptionKor())));
      result.append(getEmptyLineHtml());
      result.append(getItemUsageHtml(Formatter.setLinebreakAfterPunctHtml(getItemUsageKor())));
      result.append(getEmptyLineHtml());
      result.append(getItemIngredientHtml(massItem.getItemIngredients()));
      
      return addAlignment(addTopBottomInfo(result.toString()));
    }

    @Override
    public String getItemFullnameWithPrefix() {
        return "[" + massItem.getItemCategory() + "]" + " " + getItemFullname();
    }

    public String getItemDescriptionKor() {
        return itemDescriptionKor;
    }

    public void setItemDescriptionKor(String itemDescriptionKor) {
        this.itemDescriptionKor = itemDescriptionKor;
    }

    public String getItemUsageKor() {
        return itemUsageKor;
    }

    public void setItemUsageKor(String itemUsageKor) {
        this.itemUsageKor = itemUsageKor;
    }

    @Override
    public String getItemBrandName() {
        return massItem.getBrandName();
    }
}