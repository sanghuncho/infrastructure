package application;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {
	private static final Pattern pattern = Pattern.compile("[^\\d]*[\\d]+[^\\d]+([\\d]+)");
	
    public static void main( String[] args ) throws IOException {
        String title = "ilogexpress V2ap";
        File input = new File("src/java/resources/ilogexpress.html");
        Document doc = Jsoup.parse(input, "UTF-8", "");
        
        Element content = doc.getElementById("orderlist_b2c_all_entries");
        Elements links = content.getElementsByAttributeValue("id", "orderlist_b2c_entry");
        for(int k = 0; k<links.size(); k++) {
            Element element = links.get(k).getElementById("orderlist_b2c_rx_memo");
            Elements subs = element.getElementsByClass("col-sm-9 right_on_mobile");
            Elements result = null;
            for (Element sub : subs) {
                result = sub.getElementsMatchingText(title);
            }
            for (int i = 0; i<subs.size(); i++) {
            	//System.out.println(subs.get(i).text() + ": " + i);
            	if (subs.get(i).text().matches("ilogexpress V2ap")) {
            		System.out.println(k);
            	}
            }
        }
        
        for(int k = 0; k<links.size(); k++) {
        	if(k == 38) {
        		Element element = links.get(k).getElementById("orderlist_b2c_packing");
                Elements subs = element.getElementsByClass("col-sm-9 right_on_mobile");
                System.out.println(subs.text());
                String weightSet = subs.text();
                Matcher m = pattern.matcher(weightSet);

                // if an occurrence if a pattern was found in a given string...
                if (m.find()) {
                    System.out.println(m.group(2)); // second matched digits
                }
                
                
                Element elementPack = links.get(k).getElementById("orderlist_b2c_order_rate");
                Elements subsPack = elementPack.getElementsByClass("col-sm-9 right_on_mobile");
                System.out.println(subsPack.text());
                String packingPrice = subsPack.text();
        	}
        }
    }
}
