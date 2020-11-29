package FactoryExcel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import crawlerEntities.BaseItem;

public class SmartStore {
    private static final Logger LOGGER = LogManager.getLogger();

    private List<BaseItem> massItemList;
    private String dirCsvFilePath;
    private String itemTitlePrefix;
    private HashMap<String, String> storeBaseInfo = new HashMap<String, String>();
    
    public SmartStore(String categoryId, String itemTitlePrefix, List<BaseItem> massItemList, String pathCsv) {
        this.massItemList = massItemList;
        this.dirCsvFilePath = pathCsv;
        this.itemTitlePrefix = itemTitlePrefix;
    }
    
    public void createCSV() throws IOException {
        List<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
        for(BaseItem item : massItemList) {
            rows.add(createRow(item));
        }
        FileWriter csvWriter = new FileWriter(new File(dirCsvFilePath, itemTitlePrefix + ".txt"));

        for (List<String> rowData : rows) {
            csvWriter.append(String.join("$", rowData));
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
        LOGGER.info("file for excel is created!");
    }
    
//    public void createCSV() throws IOException {
//        List<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
//        for(BaseItem item : massItemList) {
//            rows.add(createRow(item));
//        }
//        
//        String[] HEADERS = getHeaders();
//        
//        FileWriter out = new FileWriter(new File(dirCsvFilePath, itemTitlePrefix + ".txt"));
//        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(HEADERS))) {
//             for(ArrayList<String> row : rows) {
//                 printer.printRecord(row.stream().collect(Collectors.joining(",")));
//             }
//        }
//        LOGGER.info("file for excel is created!");
//    }
    
    private String[] getHeaders() {
        List<String> headers = new ArrayList<>();
        for(int i=1; i<67; i++) {
            headers.add(String.valueOf(i));
        }
        String[] myArray = new String[headers.size()];
        return headers.toArray(myArray);
    }
    
    private ArrayList<String> createRow(BaseItem baseItem) {
        ArrayList<String> row = new ArrayList<String>();
        
        //상품상태
        storeBaseInfo.put("A", "신상품");
        row.add("신상품");
        
        //카태고리 id
        storeBaseInfo.put("B", "");
        row.add(baseItem.getCategoryId());
        
        //상품명
        storeBaseInfo.put("C", "");
        row.add(baseItem.getItemFullname());
        
        //판매가
        storeBaseInfo.put("D", "");
        row.add(baseItem.getPriceWonString());
        
        storeBaseInfo.put("E", "10");
        row.add("10");
        
        storeBaseInfo.put("F", "구매대행 특성상 AS는 불가합니다");
        row.add("구매대행 특성상 AS는 불가합니다");
        
        storeBaseInfo.put("G", "49-176-6522-4205 010-6591-1193");
        row.add("010-6591-1193");
        
        //대표이미지
        storeBaseInfo.put("H", "");
        row.add(baseItem.getMainImageFileName());
        
        storeBaseInfo.put("I", "");
        row.add("");
        
        //상품상세정보
        storeBaseInfo.put("J", "");
        row.add(baseItem.getItemImageLinkList());
        
        storeBaseInfo.put("K", "");
        row.add("");
        
        storeBaseInfo.put("L", "");
        row.add("");
        
        storeBaseInfo.put("M", "");
        row.add("");
        
        storeBaseInfo.put("N", "");
        row.add("");
        
        storeBaseInfo.put("O", "");
        row.add("");
        
        storeBaseInfo.put("P", "");
        row.add("");
        
        storeBaseInfo.put("Q", "과세상품");
        row.add("과세상품");
        
        storeBaseInfo.put("R", "Y");
        row.add("Y");
        
        storeBaseInfo.put("S", "Y");
        row.add("Y");
        
        storeBaseInfo.put("T", "04");
        row.add("04");
        
        storeBaseInfo.put("U", "");
        row.add("");
        
        storeBaseInfo.put("V", "");
        row.add("");
        
        storeBaseInfo.put("W", "독일구매대행");
        row.add("독일구매대행");
        
        storeBaseInfo.put("X", "");
        row.add("");
        
        storeBaseInfo.put("Y", "유료");
        row.add("유료");
        
        storeBaseInfo.put("Z", "10000");
        row.add("10000");
        
        storeBaseInfo.put("AA", "선결제");
        row.add("선결제");        
        
        storeBaseInfo.put("AB", "");
        row.add("");
        
        storeBaseInfo.put("AC", "");
        row.add("");
        
        storeBaseInfo.put("AD", "80000");
        row.add("80000");
        
        storeBaseInfo.put("AE", "160000");
        row.add("160000");
        
        storeBaseInfo.put("AF", "");
        row.add("");
        
        storeBaseInfo.put("AG", "N");
        row.add("N");
        
        storeBaseInfo.put("AH", "");
        row.add("");
        
        //즉시할인
        storeBaseInfo.put("AI", "");
        row.add("");
        
        storeBaseInfo.put("AJ", "원");
        row.add("원");
        
        storeBaseInfo.put("AK", "2");
        row.add("2");
        
        storeBaseInfo.put("AL", "개");
        row.add("개");
        
        storeBaseInfo.put("AM", "1000");
        row.add("1000");
        
        storeBaseInfo.put("AN", "원");
        row.add("원");
        
        storeBaseInfo.put("AO", "100");
        row.add("100");
        
        storeBaseInfo.put("AP", "원");
        row.add("원");
        
        storeBaseInfo.put("AQ", "100");
        row.add("100");
        
        storeBaseInfo.put("AR", "500");
        row.add("500");
        
        storeBaseInfo.put("AS", "100");
        row.add("100");
        
        storeBaseInfo.put("AT", "500");
        row.add("500");
        
        storeBaseInfo.put("AU", "100");
        row.add("100");
        
        storeBaseInfo.put("AV", "");
        row.add("");
        
        storeBaseInfo.put("AW", "");
        row.add("");
        
        storeBaseInfo.put("AX", "단독형");
        row.add("단독형");
        
        storeBaseInfo.put("AY", "색상"+ " " + "사이즈");
        row.add("\"색상\n사이즈\"");
        
        //옵션값 색상과 사이즈
        storeBaseInfo.put("AZ", "");
        row.add("\"" + baseItem.getColorListString() + "\n" + baseItem.getSizeListString() + "\"");
        
        storeBaseInfo.put("BA", "");
        row.add("");
        
        storeBaseInfo.put("BB", "");
        row.add("");
        
        storeBaseInfo.put("BC", "");
        row.add("");
        
        storeBaseInfo.put("BD", "");
        row.add("");
        
        storeBaseInfo.put("BE", "");
        row.add("");
        
        storeBaseInfo.put("BF", "");
        row.add("");
        
        storeBaseInfo.put("BG", "");
        row.add("");
        
        storeBaseInfo.put("BH", "");
        row.add("");
        
        storeBaseInfo.put("BI", "");
        row.add("");
        
        storeBaseInfo.put("BJ", "");
        row.add("");
        
        //스터어찜전용상품
        storeBaseInfo.put("BK", "N");
        row.add("N");
        
        storeBaseInfo.put("BL", "");
        row.add("");
        
        storeBaseInfo.put("BM", "");
        row.add("");
        
        storeBaseInfo.put("BN", "");
        row.add("");
        
        return row;
    }
}
