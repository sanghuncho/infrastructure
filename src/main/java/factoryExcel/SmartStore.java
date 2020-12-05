package factoryExcel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import crawlerApp.CrawlerEcoverde;
import crawlerEntities.BaseItem;
import crawlerEntities.BaseItemCosmetic;
 
public class SmartStore {
    private static final Logger LOGGER = LogManager.getLogger();

    private List<BaseItem> massItemList;
    private List<BaseItemCosmetic> massItemCosmeticList;
    private String dirCsvFilePath;
    private String itemTitlePrefix;
    private HashMap<String, String> storeBaseInfo = new HashMap<String, String>();
    
    public SmartStore(String categoryId, String itemTitlePrefix, List<BaseItem> massItemList,
                String pathCsv, List<BaseItemCosmetic> massItemCosmeticList) {
        this.massItemList = massItemList;
        this.dirCsvFilePath = pathCsv;
        this.itemTitlePrefix = itemTitlePrefix;
        this.massItemCosmeticList = massItemCosmeticList;
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
    
    public static void main(String [] args) {
        //createPOI();
    }
    
    public void createPOI() {
        LOGGER.info("Creating POI starts...");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(itemTitlePrefix);
        
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
//        data.put("1", new Object[] {"상품상태", "카테고리ID", "상품명", "판매가", "재고수량", "AS안내", "AS전화", "대표이미지", "추가이미지", "상세정보", "상품코드", "바코드", "제조사", "브랜드", "제조일자", "유효일자", 
//                "부가세", "미성년자", "구매평 노출여부", "원산지 코드",  "수입사",   "복수원산지 여부",    "원산지 직접입력",    "배송방법",    "배송비 유형",   "기본배송비",   "배송비 결제방식", 
//                "조건부무료-상품판매가합계",   "수량별부과-수량",    "반품배송비 ",  "교환배송비", "지역별 차등배송비 정보",   "별도설치비",   "판매자 특이사항",    "즉시할인 값", "즉시할인 단위", 
//                "복수구매할인 조건 값",  "복수구매할인 조건 단위",    "복수구매할인 값",     "복수구매할인 단위",   "상품구매시 포인트 지급 값", "상품구매시 포인트 지급 단위",  "텍스트리뷰 작성시 지급 포인트", "포토/동영상 리뷰 작성시 지급 포인트 ", "한달사용텍스트리뷰 작성시 지급 포인트",
//                "한달사용 포토/동영상리뷰 작성시 지급 포인트", "톡톡친구/스토어찜고객", "리뷰 작성시 지급 포인트",  "무이자 할부 개월 ", "사은품", "옵션형태 ", "옵션명", "옵션값", "옵션가", "옵션 재고수량", 
//                "추가상품명",  "추가상품값",  "추가상품가",   "추가상품 재고수량",   "상품정보제공고시 품명", "상품정보제공고시 모델명",  "상품정보제공고시 인증허가사항",  "상품정보제공고시 제조자",    "스토어찜회원 전용여부", "문화비 소득공제", "ISBN", "독립출판"
//        });
        
        for (int i=0;i<massItemCosmeticList.size();i++) {
            Object createdItemRow[] = createItemRow(massItemCosmeticList.get(i));
            data.put(String.valueOf(i+1), createdItemRow);
        }
        
        Object[][] datatypes = {
                {"상품상태", "카테고리ID", "상품명", "판매가", "재고수량", "AS안내", "AS전화", "대표이미지", "추가이미지", "상세정보", "상품코드", "바코드", "제조사", "브랜드", "제조일자", "유효일자", 
                        "부가세", "미성년자", "구매평 노출여부", "원산지 코드",  "수입사",   "복수원산지 여부",    "원산지 직접입력",    "배송방법",    "배송비 유형",   "기본배송비",   "배송비 결제방식", 
                        "조건부무료-상품판매가합계",   "수량별부과-수량",    "반품배송비 ",  "교환배송비", "지역별 차등배송비 정보",   "별도설치비",   "판매자 특이사항",    "즉시할인 값", "즉시할인 단위", 
                        "복수구매할인 조건 값",  "복수구매할인 조건 단위",    "복수구매할인 값",     "복수구매할인 단위",   "상품구매시 포인트 지급 값", "상품구매시 포인트 지급 단위",  "텍스트리뷰 작성시 지급 포인트", "포토/동영상 리뷰 작성시 지급 포인트 ", "한달사용텍스트리뷰 작성시 지급 포인트",
                        "한달사용 포토/동영상리뷰 작성시 지급 포인트", "톡톡친구/스토어찜고객", "리뷰 작성시 지급 포인트",  "무이자 할부 개월 ", "사은품", "옵션형태 ", "옵션명", "옵션값", "옵션가", "옵션 재고수량", 
                        "추가상품명",  "추가상품값",  "추가상품가",   "추가상품 재고수량",   "상품정보제공고시 품명", "상품정보제공고시 모델명",  "상품정보제공고시 인증허가사항",  "상품정보제공고시 제조자",    "스토어찜회원 전용여부", "문화비 소득공제", "ISBN", "독립출판"
                },
        };

        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
               Cell cell = row.createCell(cellnum++);
               if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(CrawlerEcoverde.DIR_POI_FILE + itemTitlePrefix + ".xlsx");
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.info("POI is created");//https://howtodoinjava.com/java/library/readingwriting-excel-files-in-java-poi-tutorial/
    }
    
    private Object[] createItemRow(BaseItemCosmetic item) {
        Object itemRow[] = new Object[66];
        //상품상태
        itemRow[0] = "신상품";
        //카태고리 id
        itemRow[1] = item.getCategoryId();
        //상품명
        itemRow[2] = item.getItemFullnameWithPrefix();
        //판매가
        itemRow[3] = item.getPriceWonString();
        //재고수량
        itemRow[4] = 10;
        //A/S안내내용
        itemRow[5] = "구매대행 특성상 AS는 불가합니다";
        //A/S 전화번호
        itemRow[6] = "010-6591-1193";
        //대표 이미지 파일명
        itemRow[7] = item.getMainImageFileName();
        //추가 이미지 파일명
        itemRow[8] = "";
        //상품 상세정보
        itemRow[9] = item.getItemFullDescription();
        //판매자 상품코드
        itemRow[10] = "";
        //판매자 바코드
        itemRow[11] = "";
        //제조사
        itemRow[12] = "";
        //브랜드
        itemRow[13] = "";
        //제조일자
        //itemRow[14] = "";
        //유효일자
        //itemRow[15] = "";
        //부가세
        itemRow[16] = "과세상품";
        //미성년자
        itemRow[17] = "Y";
        //구매평 노출여부
        itemRow[18] = "Y";
        //원산지 코드
        itemRow[19] = "04";
        //수입사
        itemRow[20] = "";
        //복수원산지 여부
        itemRow[21] = "";
        //원산지 직접입력
        itemRow[22] = "독일구매대행";
        //배송방법
        itemRow[23] = "";
        //배송비 유형
        itemRow[24] = "유료";
        //기본배송비
        itemRow[25] = "8000";
        //배송비 결제방식
        itemRow[26] = "선결제";
        //조건부무료-상품판매가합계
        itemRow[27] = "";
        //수량별부과-수량
        //itemRow[28] = "";
        //반품배송비
        itemRow[29] = "80000";
        //교환배송비
        itemRow[30] = "160000";
        //지역별 차등배송비 정보
        itemRow[31] = "";
        //별도설치비
        itemRow[32] = "N";
        //판매자 특이사항
        itemRow[33] = "";
        //즉시할인 값
        itemRow[34] = "";
        //즉시할인 단위
        itemRow[35] = "원";
        //복수구매할인 조건 값
        itemRow[36] = "2";
        //복수구매할인 조건 단위
        itemRow[37] = "개";
        //복수구매할인 값
        itemRow[38] = "500";
        //복수구매할인 단위
        itemRow[39] = "원";
        //상품구매시 포인트 지급 값
        itemRow[40] = "100";
        //상품구매시 포인트 지급 단위
        itemRow[41] = "원";
        //텍스트리뷰 작성시 지급 포인트
        itemRow[42] = "100";
        //포토/동영상 리뷰 작성시 지급 포인트
        itemRow[43] = "500";
        //한달사용 텍스트리뷰 작성시 지급 포인트
        itemRow[44] = "100";
        //한달사용포토/동영상리뷰 작성시 지급 포인트
        itemRow[45] = "500";
        //톡톡친구/스토어찜고객
        itemRow[46] = "100";
        //무이자 할부 개월
        //itemRow[47] = "";
        //사은품
        itemRow[48] = "";
        //옵션형태
        itemRow[49] = "";
        //옵션명
        itemRow[50] = "";
        //옵션값
        itemRow[51] = "";
        //옵션가
        itemRow[52] = "";
        //옵션 재고수량
        itemRow[53] = "";
        //추가상품명
        itemRow[54] = "";
        //추가상품값
        itemRow[55] = "";
        //추가상품가
        itemRow[56] = "";
        //추가상품 재고수량
        itemRow[57] = "";
        //상품정보제공고시 품명
        itemRow[58] = "";
        //상품정보제공고시 모델명
        itemRow[59] = "";
        //상품정보제공고시 인증허가사항
        itemRow[60] = "";
        //상품정보제공고시 제조자
        itemRow[61] = "";
        //스토어찜회원 전용여부
        itemRow[62] = "N";
        //문화비 소득공제
        itemRow[63] = "";
        //ISBN
        itemRow[64] = "";
        //독립출판
        itemRow[65] = "";
        return itemRow;
    }
    
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
        
        //재고수량
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
