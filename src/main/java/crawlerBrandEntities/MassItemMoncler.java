package crawlerBrandEntities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import crawlerApp.CrawlerMoncler.Gender;
import crawlerEntities.BaseItem;
import crawlerEntities.MassItem;

public class MassItemMoncler extends BaseItem {
    
    private MassItem massItem;
    private List<String> detailImageUrlList = new ArrayList<>();
    private String companyLogo;
    private int priceWon;
    
    public MassItemMoncler(MassItem massItem) {
        this.massItem = massItem;
        this.companyLogo = super.getCompanyLogoUrl();
        this.priceWon = super.calculatePriceWon(massItem.getItemPriceEuro());
    }
    
    public MassItem getMassItem() {
        return massItem;
    }

    public String getItemImageLinkList() {
        
        for(String image : massItem.getItemDetailImages()) {
            detailImageUrlList.add(transformToHtml(image));
            detailImageUrlList.add(transformToHtmlLogo(companyLogo));
        }
        
        String imagesCommaSeparated = detailImageUrlList.stream().collect(Collectors.joining(","));
        
        //addSizeTable 
        return addAlignment(addTopBottomInfo(addSizeInfo(addBuyingInfo(imagesCommaSeparated), massItem.getGender())));
    }
    
    private String transformToHtml(String itemImage) {
        String result = itemImage.split(".jpg")[0];
        String itemImageFullname = result + ".jpg";
        
        StringBuilder htmlBulder = new StringBuilder();
        htmlBulder.append("<img style=\"padding-bottom: 5px;\" src=\"");
        //htmlBulder.append("<img src=\"");
        htmlBulder.append(itemImageFullname);
        htmlBulder.append("\" />");
        return htmlBulder.toString();
    }
    
    private String transformToHtmlLogo(String itemImage) {
        StringBuilder htmlBulder = new StringBuilder();
        htmlBulder.append("<img width=\"500\" src=\"");
        htmlBulder.append(itemImage);
        htmlBulder.append("\" />");
        return htmlBulder.toString();
    }
    
    //https://moondrive81.cafe24.com/GKoo/gkooStoreInfo.png
    private String addBuyingInfo(String itemImagesHtml) {
        StringBuilder imageBuilder = new StringBuilder();
        imageBuilder.append(itemImagesHtml);
        imageBuilder.append(", ");
        imageBuilder.append("<img style=\"padding-bottom: 10px;\" src=\"https://moondrive81.cafe24.com/GKoo/gkooStoreInfo.png\"/>");
        return imageBuilder.toString();
    }
    
    //men https://moondrive81.cafe24.com/GKoo/women_size_guide_moncler.png
    //women https://moondrive81.cafe24.com/GKoo/women_size_guide_moncler.png
    private String addSizeInfo(String itemImagesHtml, Gender gender) {
        StringBuilder imageBuilder = new StringBuilder();
        imageBuilder.append(itemImagesHtml);
        imageBuilder.append(", ");
        if (gender.equals(Gender.MALE)) {
            imageBuilder.append("<img style=\"padding-bottom: 10px;\" src=\"https://moondrive81.cafe24.com/GKoo/men_size_guide_moncler.png\"/>");
        } else if (gender.equals(Gender.FEMALE)){
            imageBuilder.append("<img style=\"padding-bottom: 10px;\" src=\"https://moondrive81.cafe24.com/GKoo/women_size_guide_moncler.png\"/>");
        }
        return imageBuilder.toString();
    }
    
    private String addTopBottomInfo(String itemImagesHtml) {
        StringBuilder imageBuilder = new StringBuilder();
        imageBuilder.append("<img style=\"padding-bottom: 30px;\" src=\"https://moondrive81.cafe24.com/GKoo/gkoo_info_top.png\"/>");
        imageBuilder.append(", ");
        imageBuilder.append(itemImagesHtml);
        imageBuilder.append(", ");
        imageBuilder.append("<img src=\"https://moondrive81.cafe24.com/GKoo/gkoo_info_bottom.jpg\"/>");
        return imageBuilder.toString();
    }
    
    private String addAlignment(String itemImagesHtml) {
        StringBuilder imageBuilder = new StringBuilder();
        imageBuilder.append("<p style=\"text-align:center;\">");
        imageBuilder.append(itemImagesHtml);
        imageBuilder.append("</p>");
        return imageBuilder.toString();
    }
    
    public void setMassItem(MassItem massItem) {
        this.massItem = massItem;
    }
    
    public List<String> getDetailImageUrlList() {
        return detailImageUrlList;
    }

    public String getPriceWonString() {
        return String.valueOf(priceWon);
    }
    
    public String getColorListString() {
        return super.getListToString(massItem.getItemColors());
    }
    
    public String getSizeListString() {
        return super.getListToString(massItem.getItemSizes());
    }
    
    public String getItemFullname() {
        return massItem.getBrandName() + " " + massItem.getItemCategory() + " " + massItem.getItemTitle();
    }
    
    public String getCategoryId() {
        return massItem.getCategoryId();
    }

    @Override
    public String getMainImageFileName() {
        return massItem.getMainImageFileName();
    }
}