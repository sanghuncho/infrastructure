package application;

import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {
    public static void main( String[] args ) throws IOException {
        String title = "ilogexpress V2ap";
        File input = new File("src/java/resources/ilogexpress.html");
        Document doc = Jsoup.parse(input, "UTF-8", "");
        
        Element content = doc.getElementById("orderlist_b2c_all_entries");
        Elements links = content.getElementsByAttributeValue("id", "orderlist_b2c_entry");
        int searchNumber = 0;
        for(int k = 0; k<links.size(); k++) {
            Element element = links.get(k).getElementById("orderlist_b2c_rx_memo");
            Elements subs = element.getElementsByClass("col-sm-9 right_on_mobile");
            Elements result = null;
            for (Element sub : subs) {
                result = sub.getElementsMatchingText(title);
            }
            for (int i = 0; i<subs.size(); i++) {
            	//System.out.println(subs.get(i).text() + ": " + i);
            	if (subs.get(i).text().matches("ilogexpress Tonecontrol")) {
            		System.out.println(k);
            		searchNumber = k;
            	}
            }
        }
        
        for(int k = 0; k<links.size(); k++) {
        	if(k == searchNumber) {
        	    Element elementTracking = links.get(k).getElementById("orderlist_b2c_waybillno");
                Elements trackingNr = elementTracking.getElementsByClass("col-sm-9 right_on_mobile");
                System.out.println("송장번호" + trackingNr.text());
                
        	    Element element = links.get(k).getElementById("orderlist_b2c_packing");
                Elements subs = element.getElementsByClass("col-sm-9 right_on_mobile");
                System.out.println(subs.text());
                
                Element elementPack = links.get(k).getElementById("orderlist_b2c_order_rate");
                Elements subsPack = elementPack.getElementsByClass("col-sm-9 right_on_mobile");
                System.out.println(subsPack.text());
        	}
        }
    }
}
