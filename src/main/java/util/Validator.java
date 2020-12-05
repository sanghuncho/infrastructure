package util;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import crawlerApp.CrawlerEcoverde;

public class Validator {
    private static final Logger LOGGER = LogManager.getLogger(CrawlerEcoverde.class);

    public static List<String> uniqueNameTester = new ArrayList<>();
    
    public static String getUniqueName(String orgName) {
        List<String> matchedItems = new ArrayList<>();
        for(String uniqueName : uniqueNameTester) {
            if(uniqueName.contains(orgName)) {
                matchedItems.add(orgName);
            }
        }
        int matchedItemSize = matchedItems.size();
        String validUniqueName = "";
        if(matchedItemSize > 0) {
            validUniqueName = orgName + "_" + String.valueOf(matchedItemSize+1);
            LOGGER.warn("please control same itemname:" + validUniqueName);
        } else {
            validUniqueName = orgName;
        }
        
        uniqueNameTester.add(validUniqueName);
        return validUniqueName;
    }
}
