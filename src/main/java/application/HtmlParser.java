package application;

import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HtmlParser {
    public static void main( String[] args ) throws IOException {
//        String blogUrl = "http://www.ilogexpress.com/order_main_b2c";
//        Document doc = Jsoup.connect(blogUrl).get();
        File input = new File("src/java/resources/ilogexpress.html");
        Document doc = Jsoup.parse(input, "UTF-8", "");
        
        Element content = doc.getElementById("orderlist_b2c_entry");
        System.out.println(content);
    }
}