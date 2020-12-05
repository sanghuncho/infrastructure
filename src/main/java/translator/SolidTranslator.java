package translator;

import java.util.HashMap;
import java.util.Objects;

public class SolidTranslator {
    public static HashMap<String, String> itemBrandTranslator = new HashMap<String, String>();
    
    public static void initBrandTranslator() {
        itemBrandTranslator.put("logona", "로고나");
        itemBrandTranslator.put("weleda", "벨레다");
    }
    
    public static String getBrandNameKor(String brandName) {
        Objects.nonNull(brandName);
        String brandNameKor = itemBrandTranslator.get(brandName.toLowerCase());
        return brandNameKor == null ? brandName : brandNameKor;
    }
    
    public static boolean hasBrandNameKor(String brandName) {
        Objects.nonNull(brandName);
        itemBrandTranslator.get(brandName.toLowerCase());
        return itemBrandTranslator.get(brandName) != null;
    }
}
