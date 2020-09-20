package application;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TrackingNumberParser {
    private static final String DIR_HTML = "C:/Users/sanghuncho/Documents/Gkoo/ILogYou Express.html";
    public static void main( String[] args ) throws IOException {
    	List<String> itemTitleList = 
    			Arrays.asList(
//    			        "pauls", //Heinos 150, 161 :OK
//    			        "profan",//156 :OK
//    			        "mechanic", //157 :OK
//    			        "Atun", //186, 187 :OK
//    			        "Sabine", //141 :OK
//    			        "Losti", //158 OK
    			        //"Didez", //145, 146, 149 :OK
    			        //"Phono", //159 :OK
    			        //"Tube4", //160 :OK
    			        //"Trackis", //163 :OK
    			        //"V2ap", //164 :OK
    			        //"Ronja", //165 :OK
    			        //"Kurprinz", //167 :OK
    			        //"Schneebaer", //166 :OK
//    			        "Mibu21-1", //151-1 :OK
//    			        "Mibu21-2", //151-2 :OK
    			        "prolo-2", // 같은 이름으로 2개 홈페이지 확인
    			        "ilogexpress-2", //134-2 jugmusik
    					"Prolo",
    					"Sixty",
    					"Andreas",
    					"VintagePapa",
    					"Ohmcounter",
    					"Xmpoint",
    					"Jingsaw",//??
    					""
    					);
    	
    	StringBuilder results = new StringBuilder();
    	
    	for(int i=0; i< itemTitleList.size(); i++) {
    		getDeliveryData(itemTitleList.get(i), results);
    	}
    	
    	System.out.println(results.toString());
    }
    
    private static void getDeliveryData(String itemTitle, StringBuilder results) throws IOException {
    	String title = "ilogexpress " + itemTitle;
    	File input = new File(DIR_HTML);
        Document doc = Jsoup.parse(input, "UTF-8", "");
        
        Element content = doc.getElementById("orderlist_b2c_all_entries");
        Elements links = content.getElementsByAttributeValue("id", "orderlist_b2c_entry");
        int searchNumber = 0;
        for(int k = 0; k<links.size(); k++) {
            Element element = links.get(k).getElementById("orderlist_b2c_rx_memo");
            Elements subs = element.getElementsByClass("col-sm-9 right_on_mobile");
            for (int i = 0; i<subs.size(); i++) {
            	if (subs.get(i).text().matches(title)) {
            		searchNumber = k;
            	}
            }
        }
        
        for(int k = 0; k<links.size(); k++) {
        	if(k == searchNumber) {
        		results.append("아이템: " + title);
        		results.append('\n');
        		Element elementStatus = links.get(k).getElementById("orderlist_b2c_shippingstatus");
        		results.append("배송상태: " + elementStatus.text());
        		results.append('\n');
        		
        		Element elementTracking = links.get(k).getElementById("orderlist_b2c_waybillno");
                Elements trackingNr = elementTracking.getElementsByClass("col-sm-9 right_on_mobile");
                results.append("송장번호: " + trackingNr.text());
        		results.append('\n');
                
        	    Element element = links.get(k).getElementById("orderlist_b2c_packing");
                Elements subs = element.getElementsByClass("col-sm-9 right_on_mobile");
                results.append(subs.text());
        		results.append('\n');
                
                Element elementPack = links.get(k).getElementById("orderlist_b2c_order_rate");
                Elements subsPack = elementPack.getElementsByClass("col-sm-9 right_on_mobile");
                results.append(subsPack.text());
        		results.append('\n');
        		results.append('\n');
        		results.append('\n');
        	}
        }
    }
}
