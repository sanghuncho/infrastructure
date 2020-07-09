package application;

import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {
    public static void main( String[] args ) throws IOException {
//        String blogUrl = "http://www.ilogexpress.com/order_main_b2c";
//        Document doc = Jsoup.connect(blogUrl).get();
        String title = "ilogexpress V2ap";
        File input = new File("src/java/resources/ilogexpress.html");
        Document doc = Jsoup.parse(input, "UTF-8", "");
        
        Element content = doc.getElementById("orderlist_b2c_all_entries");
        Elements links = content.getElementsByAttributeValue("id", "orderlist_b2c_entry");
        //System.out.println(links);
        //Elements links = content.getElementsByAttributeValue("data-index", "1");
        for (Element link : links) {
            Element element = link.getElementById("orderlist_b2c_rx_memo");
            Elements subs = element.getElementsByClass("col-sm-9 right_on_mobile");
            Elements result = null;
            for (Element sub : subs) {
                result = sub.getElementsMatchingText(title);
            }
            //System.out.println(result.);
        }
    }
}
